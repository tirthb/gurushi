package com.gurushi.loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gurushi.data.Author;
import com.gurushi.data.Chapter;
import com.gurushi.data.Commentary;
import com.gurushi.data.Scripture;
import com.gurushi.data.Translation;
import com.gurushi.data.Verse;
import com.gurushi.service.ChapterService;
import com.gurushi.service.ScriptureService;
import com.gurushi.service.VerseService;

@Component
public class VedaBaseDotNetGita extends ScriptureSource {
	
	public static final String SOURCE_URL_PREFIX = "http://vedabase.net/bg/"; 
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	Author author;
	List<Chapter> chapters = new ArrayList<Chapter>();
	
	@Autowired
	VerseService vs;
	
	@Autowired
	ChapterService cs;
	
	@Autowired
	ScriptureService ss;
	
	//TODO:remove blank constructor
	public VedaBaseDotNetGita() {}
	
	public VedaBaseDotNetGita(String rootDirectory, Scripture sc) {
		
		super(rootDirectory, sc);
		author = new Author("His Grace Bhakti Vedanta Swami Srila Prabhupada", 
				"http://en.wikipedia.org/wiki/A._C._Bhaktivedanta_Swami_Prabhupada");
	}

	private List<Element> getMatchingChilds(Element parent, String tag) {
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
	
	private List<Element> getMatchingChildsRange(Element parent, String tag, int start, int end) {
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
	private Element findNthChild(Element parent, String tag, int num) {
		
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
			
			String sourceUrl = SOURCE_URL_PREFIX + ch.getNumber() + "/" + verse.getNumber() + "/en";
			
			Translation trans = new Translation(author);
			trans.setText(getTranslation(doc.select("body").get(0)));
			trans.setSourceUrl(sourceUrl);
			
			verse.setTranslation(trans);
			
			logger.debug("Translation: \n" + trans.getText());

			String synonyms = getSynonyms(doc.select("body").get(0));
			String[] words = synonyms.split(";");
			
			System.out.println("Synonyms:");
			for (int i = 0; i < words.length; i++) {
				//System.out.println("Word = " + words[i]);
				String word[] = words[i].split(" — ");
				logger.debug(word[0] + " - " + word[1]);
				verse.addMeaning(word[0], word[1]);
			}
			
			Commentary purport = new Commentary(getPurport(doc.select("body").get(0)), sourceUrl, author);
			verse.addCommentary(purport);	
					
			logger.debug("Purport:\n" + purport.getText());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void print(String msg, Object... args) {
		logger.info(String.format(msg, args));
	}

	private String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}

	private void createChapters() {
		int chapter;
		Chapter ch;
		Chapter previousChapter = null;
		Document doc;
		
		for (chapter = 1; chapter <= 18; chapter++) {
			
			String firstVerse = rootDir + "/" + chapter + "/" + 1 + "/en";
			File input = new File(firstVerse);
			
			try {
				doc = Jsoup.parse(input, "UTF-8", "");
				
				String title = getChapterTitle(doc);
				
				ch = new Chapter(Integer.toString(chapter), title, sc);
				
				ch = cs.save(ch);
				
				chapters.add(ch);
				
				if (chapter == 1) {
					sc.setFirstChapter(ch);
					sc = ss.save(sc);
				}
				
				if (previousChapter != null) {
				  previousChapter.setNextChapter(ch);
				}
				previousChapter = ch;
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadData() {
		
		try {
			
			createChapters();
			
			int chapterIndex = 1;
			Verse currentVerse = null, previousVerse = null;
			
			for (Chapter ch : chapters) {
	
				for (int verseNum = 1;; verseNum++) {
					String verseFile = rootDir + "/" + chapterIndex + "/" + verseNum + "/en";
					
					logger.info("File Being Processed: " + verseFile);
					logger.info("Chapter = " + chapterIndex + " Verse = " + verseNum);
					
					File file = new File(verseFile);
					
					if (!file.exists()) {
						logger.info("File not found: " + file.getCanonicalPath());
						continue;
					}
					
					currentVerse = new Verse(Integer.toString(verseNum), ch);
					
					if (verseNum == 1) {
						ch.setFirstVerse(currentVerse);
					}

					processVerse(ch, currentVerse, verseFile);
					
					currentVerse = vs.save(currentVerse);
					ch = cs.save(ch);
					
					if (previousVerse != null) {
						
						previousVerse.setNextVerse(currentVerse);
						previousVerse = vs.save(previousVerse);
					}
					
					previousVerse = currentVerse;
					
					System.out.println();
					
					//TODO:remove early break
					if (verseNum == 3) break;
				}
				
				chapterIndex++;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
