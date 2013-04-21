package com.gurushi.loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.gurushi.data.Author;
import com.gurushi.data.Chapter;
import com.gurushi.data.Commentary;
import com.gurushi.data.Scripture;
import com.gurushi.data.Translation;
import com.gurushi.data.Verse;

@Component
public class VedaBaseDotNet extends ScriptureSource {
	
	Author author;
	
	List<Chapter> chapters;
	
	//TODO:remove blank constructor
	public VedaBaseDotNet() {}
	
	public VedaBaseDotNet(String rootDirectory, Scripture sc) {
		super(rootDirectory, sc);
		
		author = new Author();
		author.setName("His Grace Bhakti Vedanta Swami Srila Prabhupada");
		chapters = new ArrayList<Chapter>();
		createChapterLinks();
	}

	private List<Element> GetMatchingChilds(Element parent, String tag) {
		List<Element> found = new ArrayList<Element>();;
		Elements children = parent.children();
		
		int flag = 0;
		
		parent.childNodes().size();
		
		for (Element child : children) {
		  //print("Child tag " + child.tagName());
		  if (tag == null || (tag != null && child.tagName().matches(tag))) {
			  found.add(child);
			  flag = 1;
		  }		  
	
		}
		if(flag == 0) {
		  print("FATAL ERROR: could not find the right tag: " + tag + "\n");
		  return found;				
		}
		
		return found;
		
	}
	
	private List<Element> GetMatchingChildsRange(Element parent, String tag, int start, int end) {
		List<Element> found = new ArrayList<Element>();;
		Elements children = parent.children();
		int flag = 0;
		
		for (Element child : children) {
		  //print("Child tag " + child.tagName());
		  if (child.tagName().matches(tag)) {			  
			  flag++;
			  if (flag >= start && flag <=end) {
			    found.add(child);
			  }
		  }
		}
		if(flag == 0) {
		  print("FATAL ERROR: could not find the right tag: " + tag + "\n");
		  return found;				
		}
		
		return found;
		
	}
	private Element FindNthChild(Element parent, String tag, int num) {
		
		Elements children = parent.children();
		int flag = 0;
		Element found = null;
		for (Element child : children) {
		  //print("Child tag " + child.tagName());
		  if (child.tagName().matches(tag)) {
		    flag++;
		    if (flag == num) {
		    	found = child;
		    	break;
		    }
		  }
		}
		if(flag == 0) {
		  print("FATAL ERROR: could not find the right tag: " + tag + "\n");
		  return null;				
		}
		
		return found;
		
	}
	
	private String getChapterTitle(Element root) {
		
		Elements trs = root.select("tbody > tr");
		Elements tds = trs.get(0).select("td");
		Elements as = tds.get(0).select("a");
		
		
		return as.get(0).text();		
		
	}
	
	private String parseContent(List<Element> ps, String sep_begin, String sep_end) {
		String content = "";
		for (int i = 0; i < ps.size(); i++) {
			String html = ps.get(i).html();
			String html_text = Jsoup.parse(html).text();		
			content = content + sep_begin + html_text + sep_end;	

		}		
		return content;		
	}
	
	private String getVerse(Element root) {   		
		
		List<Element> verse_nodes = root.select("p.c");
		return(parseContent(verse_nodes, "<p>", "</p>"));				
	}
	
	private String getSynonyms(Element root) {
   		
		List<Element> p_t_nodes = root.select("p.t");		
		List<Element> syn = new ArrayList<Element>();
		if (p_t_nodes.get(0) != null) {
		  Element trans = p_t_nodes.get(0).nextElementSibling();
		  syn.add(trans);
		  return(parseContent(syn, "", ""));
		}        
		return "";		
	}
	
    private String getTranslation(Element root) {
		List<Element> p_t_nodes = root.select("p.t");		
		List<Element> trans = new ArrayList<Element>();
		if (p_t_nodes.get(1) != null) {
		  Element translation = p_t_nodes.get(1).nextElementSibling();
		  trans.add(translation);
		  return(parseContent(trans, "<p>", "</p>"));
		}        
		return "";
   		
	}
    
    private String getPurport(Element root) {
		List<Element> p_t_nodes = root.select("p.t");		
		List<Element> purport = new ArrayList<Element>();
		if (p_t_nodes.size() >= 3) {
		  Element trans = p_t_nodes.get(2).nextElementSibling();			
		  purport.add(trans);
		  
		  Element nextElement = trans;
		  while ((nextElement = trans.nextElementSibling()) != null) {
			 if (nextElement.hasClass("l")) break;
			 trans = nextElement;
		     purport.add(trans);
		  }
		  return(parseContent(purport, "<p>", "</p>"));
		}        
		return "";
	}
    
	private void processVerse(Chapter ch, Verse verse, String file) {
		File input = new File(file);
		Document doc;
		try {
			doc = Jsoup.parse(input, "UTF-8", "");
			
			verse.setText(getVerse(doc.select("body").get(0)));
			System.out.println("Verse: \n" + verse.getText());
			
			
			Translation trans = new Translation(author);
			trans.setText(getTranslation(doc.select("body").get(0)));
			
			verse.setTranslation(trans);
			
			System.out.println("Translation: \n" + trans.getText());

			String synonyms = getSynonyms(doc.select("body").get(0));
			String[] words = synonyms.split(";");
			
			System.out.println("Synonyms:");
			for (int i = 0; i < words.length; i++) {
				//System.out.println("Word = " + words[i]);
				String word[] = words[i].split(" — ");
				System.out.println(word[0] + " - " + word[1]);
				verse.addMeaning(word[0], word[1]);
			}
			
			Commentary purport = new Commentary(getPurport(doc.select("body").get(0)), "http://vedabase.net/bg/" + ch.getNumber() + "/" + verse.getNumber() + "en", author);
			verse.addCommentary(purport);	
					
			System.out.println("Purport: \n" + purport.getText());
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sc.setFirstChapter(chapters.get(0));
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}

	private void createChapterLinks() {
		int chapter;
		Chapter ch;
		Chapter previous_chapter = null;
		Document doc;
		for (chapter = 1; chapter <= 18; chapter++) {
			String first_verse = rootDir + "/" + 1 + "/" + 1 + "/en";
			File input = new File(first_verse);
			try {
				doc = Jsoup.parse(input, "UTF-8", "");
				
				String title = getChapterTitle(doc);
				
				ch = new Chapter(Integer.toString(chapter), title, sc);
				chapters.add(ch);
				
				if (previous_chapter != null) {
				  previous_chapter.setNextChapter(ch);
				}
				previous_chapter = ch;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}		
		
		
	}
	
	
	public void loadData() {
		try {
			int chapter;
			Verse current_verse_obj = null;
			Verse previous_verse_obj = null;
			for (chapter = 1; chapter <= 18; chapter++) {
	
				Chapter ch = chapters.get(chapter - 1);
				
				for (int verse_num = 1;; verse_num++) {
					String verse_file = rootDir + "/" + chapter + "/" + verse_num + "/en";
					File file = new File(verse_file);
					if (!file.exists())
						break;
					
					System.out.println("File Being Processed: " + file.getCanonicalPath());
					System.out.println("Chapter = " + chapter + " Verse = " + verse_num);
					
					current_verse_obj = new Verse(Integer.toString(verse_num), ch);
					
					if (verse_num == 1) {
						ch.setFirstVerse(current_verse_obj);
					}

					processVerse(ch, current_verse_obj, verse_file);
					
					if (previous_verse_obj != null) {
						previous_verse_obj.setNextVerse(current_verse_obj);
						previous_verse_obj = current_verse_obj;
					}
					System.out.println();
					if (verse_num == 3) return;
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
