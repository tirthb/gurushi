package com.gurushi.loader;

import java.io.File;


//import com.gurushi.bo.*

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.text.NumberFormat;

import org.apache.commons.io.FilenameUtils;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.String;
import java.util.Iterator;

import com.gurushi.data.*;

public class VedaBaseDotNet {
	File rootDir;
	
	Scripture sc;
	Author author;

	public VedaBaseDotNet(File dir) {
		rootDir = dir;
		sc = new Scripture("Bhagavad Gita As It Is");
		author = new Author();
		author.setName("His Grace Srila Prabhupada");
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
		  return(parseContent(syn, "<p>", "</p>"));
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
		  return(parseContent(purport, "\n<p>", "</p>"));
		}        
		return "";
	}
    
	private void processHTML(String file) {
		File input = new File(file);
		Document doc;
		try {
			doc = Jsoup.parse(input, "UTF-8", "");

			Elements tables = doc.select("table");

			String title = getChapterTitle(tables.get(0));
			System.out.println("Title: \n" + title);
			
			String verse = getVerse(doc.select("body").get(0));
			System.out.println("Verse: \n" + verse);

			String trans = getTranslation(doc.select("body").get(0));
			System.out.println("Translation: \n" + trans);

			String synonyms = getSynonyms(doc.select("body").get(0));
			System.out.println("Synonyms: \n" + synonyms);
			
			String purport = getPurport(doc.select("body").get(0));
			System.out.println("Purport: \n" + purport);

		} catch (IOException e) {
			e.printStackTrace();
		}
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
/*
	private createChapterLinks() {
		int chapter;
		for (chapter = 1; chapter <= 18; chapter++) {
			String first_verse = rootDir + "/" + 1 + "/" + 1 + "/en";
			File input = new File(first_verse);
			Document doc = Jsoup.parse(input, "UTF-8", "");
			String title = getChapterTitle(doc);
			
			Chapter ch = new Chapter(chapter, title, sc);
		}		
		
		
	}
	*/
	
	public void loadData() {
		try {
			int chapter;
			for (chapter = 1; chapter <= 18; chapter++) {
				/*
				String first_verse = rootDir + "/" + 1 + "/" + 1 + "/en";
				File input = new File(first_verse);
				Document doc = Jsoup.parse(input, "UTF-8", "");
				String title = getChapterTitle(doc);
				
				Chapter ch = new Chapter(chapter, title, sc);
				*/
				for (int verse = 1;; verse++) {
					String verse_file = rootDir + "/" + chapter + "/" + verse + "/en";
					// System.out.println("file:" + verse_file);
					File file = new File(verse_file);
					//File file = new File("F:/Dropbox/www.bhagavad-gita.org/Gita/test.html");
					if (!file.exists())
						break;
					
					System.out.println(file.getCanonicalPath());
					//processHTML(verse_file, chapter, verse);	
					processHTML(verse_file);	
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
