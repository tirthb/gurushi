package com.gurushi;

import java.io.File;

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

public class VedaBaseDotNet {
	File rootDir;

	public VedaBaseDotNet(File dir) {
		rootDir = dir;
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
			//System.out.println(tables.size());
			
			//Elements table_children = doc.select("table table");
			//Elements table_children = doc.select("body > table");
			
			//Elements table_children.get(1)
			//System.out.println(tables.size());
			//System.out.println(tables.get(0).html());
			
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

			
			//tables.removeAll(table_children);
			//System.out.println(tables.size());
			
			//System.out.println(tables.get(1).html());
			
			//Element html = FindNthChild(doc, "html", 1);
			//Element body = FindNthChild(html, "body", 1);
			//Element table = FindNthChild(body, "table", 2);
			/*
			Element tbody = FindNthChild(tables.get(1), "tbody", 1);
			List<Element> trs = GetMatchingChilds(tbody, "tr");
			
			//List<Element> trs = GetMatchingChilds(, "tr");
			
			print("\nRows: " + trs.size());
			List<Element> commentaries12 = GetMatchingChilds(trs.get(0), "td");
			print("\ncommentaries12: " + commentaries12.size());
			List<Element> commentaries34_td = GetMatchingChilds(trs.get(1), "td");
			print("\ncommentaries34_td: " + commentaries34_td.size());
			
			Element commentary1 = FindNthChild(commentaries12.get(0), "center", 1); 
			print("Commentary 1: " + commentary1.text());
			
			Element commentary2 = FindNthChild(commentaries12.get(1), "center", 1); 
			print("Commentary 2: " + commentary2.text());
			
			Element commentary3_div = FindNthChild(commentaries34_td.get(0), "div", 1);
			Element commentary3 = FindNthChild(commentary3_div, "u", 1);
			print("Commentary 3: " + commentary3.text());
			
			Element commentary4 = FindNthChild(commentaries34_td.get(1), "center", 1); 
			print("Commentary 4: " + commentary4.text());
			*/
			
			
		    
			
			
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

	public void loadData() {
		try {
			int chapter;
			for (chapter = 1; chapter <= 18; chapter++) {
				for (int verse = 1;; verse++) {
					String verse_file = rootDir + "/" + chapter + "/" + verse + "/en";
					// System.out.println("file:" + verse_file);
					File file = new File(verse_file);
					
					//File file = new File("F:/Dropbox/www.bhagavad-gita.org/Gita/test.html");
					if (!file.exists())
						break;
					System.out.println(file.getCanonicalPath());
					processHTML(verse_file);					
					//processHTML("F:/Dropbox/www.bhagavad-gita.org/Gita/test.html");
					//if (verse == 10) {
					  //return;
					//}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
