package com.gurushi.loader;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gurushi.dao.ChapterDao;
import com.gurushi.dao.ScriptureDao;
import com.gurushi.dao.UtilDao;
import com.gurushi.dao.VerseDao;
import com.gurushi.domain.Author;
import com.gurushi.domain.Chapter;
import com.gurushi.domain.Commentary;
import com.gurushi.domain.Scripture;
import com.gurushi.domain.Verse;

public class BhagavadGitaDotOrg extends ScriptureSource {
	public static final String SOURCE_URL_PREFIX = "http://www.bhagavad-gita.org/Gita";
	final Logger logger = LoggerFactory.getLogger(BhagavadGitaDotOrg.class);

	Author author;
	List<Chapter> chapters = new ArrayList<Chapter>();

	@Autowired
	VerseDao vdao;

	@Autowired
	ChapterDao cdao;

	@Autowired
	ScriptureDao sdao;
	
	@Autowired
	UtilDao utilDao;	

	Author authorSridhara;
	Author authorMadhava;
	Author authorRamanuja;
	Author authorKesava;
	
	String group_verse;
	int start_group_verse = 0;
	int end_group_verse = 0;
	int verse_offset = 0;
	
	// TODO:remove blank constructor
	public BhagavadGitaDotOrg() {
	}

	public BhagavadGitaDotOrg(String rootDirectory, Scripture sc) {		
		super(rootDirectory, sc);	 
			
		
		
		
	}

	private List<Element> GetMatchingChilds(Element parent, String tag) {
		List<Element> found = new ArrayList<Element>();
		;
		Elements children = parent.children();
		int flag = 0;

		for (Element child : children) {
			// print("Child tag " + child.tagName());
			if (child.tagName().matches(tag)) {
				found.add(child);
				flag = 1;
			}
		}
		if (flag == 0) {
			print("Warning: could not find the right tag: " + tag + "\n");
			return found;
		}

		return found;

	}

	private Element FindNthChild(Element parent, String tag, int num) {

		Elements children = parent.children();
		int flag = 0;
		Element found = null;
		for (Element child : children) {
			// print("Child tag " + child.tagName());
			if (child.tagName().matches(tag)) {
				flag++;
				if (flag == num) {

					found = child;
					break;
				}
			}
		}
		if (flag == 0) {
			print("FATAL ERROR: could not find the right tag: " + tag + "\n");
			return null;
		}

		return found;

	}

	private String parseContent(List<Element> ps, String sep_begin, String sep_end) {
		String content = "";
		for (int i = 0; i < ps.size(); i++) {
			String html = ps.get(i).html();
			//html.replaceAll("\\%", " percentage");
			if (html.contains("%")) {
			  continue;	
			
			}
			print("Fragment : " + html);
			String html_text = Jsoup.parse(html).text();		
			content = content + sep_begin + html_text + sep_end;	

		}		
		return content;		
	}
	 private String getPurport(Element root) {
			Element div_node = FindNthChild(root, "div", 2);;		
			List<Element> p_t_nodes = div_node.select("p");
			List<Element> purport = new ArrayList<Element>();
			if (p_t_nodes.size() >= 1) {
			  //Element trans = p_t_nodes.get(2).nextElementSibling();
			  Element trans = p_t_nodes.get(0);	
			  purport.add(trans);
			  
			  Element nextElement = trans;
			  while ((nextElement = trans.nextElementSibling()) != null) {
				 //if (nextElement.hasClass("l")) break;
				 trans = nextElement;
			     purport.add(trans);
			  }
			  return(parseContent(purport, "<p>", "</p>"));
			}        
			return "";
	}
	private void processHTML(String file, int chapter, int verse, String chapter_s, String verse_s) {
		File input = new File(file);
		Document doc;
		
		if (verse >= start_group_verse && verse <= end_group_verse) {
			return;
		}
		
		
		start_group_verse = 0;
		end_group_verse = 0;
		
		try {
			doc = Jsoup.parse(input, "UTF-8", "");

			Elements tables = doc.select("table");
			System.out.println(tables.size());

	
			Elements table_children = doc.select("body > table");


			Element tbody = FindNthChild(table_children.get(1), "tbody", 1);
			List<Element> trs = GetMatchingChilds(tbody, "tr");


			print("\nRows: " + trs.size());
			List<Element> commentaries12 = GetMatchingChilds(trs.get(0), "td");
			print("\ncommentaries12: " + commentaries12.size());
			List<Element> commentaries34 = GetMatchingChilds(trs.get(1), "td");
			
            String purport = "";
        	
        	Chapter ch = cdao.findByNumberAndScripture(Integer.toString(chapter), sc);
        	
        	if (ch == null) {
        		print("ERROR: Count not locate Chapter object \n");
        		System.exit(0);
        	}
        	
        	
        	Verse v = null;
        	int count = 0;
        	String verse_search;
        	String sourceUrl ="";
        	
        	
        	v = vdao.findByNumberAndChapter(verse, ch);     	 
        	
	        //TODO: need to find the file for the corresponding verse range
        	     	
        	sourceUrl = SOURCE_URL_PREFIX + "/verse-" + chapter_s + "-" + Integer.toString(verse - verse_offset)  + ".html";     		
        	
			Element commentary1 = FindNthChild(commentaries12.get(0), "center", 1);
			if (commentary1 != null) {				
			  logger.debug("Commentary 1: " + commentary1.text());
			  purport = getPurport(commentaries12.get(0));
			  Commentary bySridharSwami = new Commentary(purport, sourceUrl, v, authorSridhara);
			  utilDao.save(bySridharSwami);

			}

			Element commentary2 = FindNthChild(commentaries12.get(1), "center", 1);
			if (commentary2 != null) {
			  print("Commentary 2: " + commentary2.text());
			  purport = getPurport(commentaries12.get(1));
			  Commentary byMadhavaAcharya = new Commentary(purport, sourceUrl, v, authorMadhava);
			  utilDao.save(byMadhavaAcharya);			  

			}
			
			Element commentary3 = FindNthChild(commentaries34.get(0), "center", 1);
			if (commentary3 != null) {				
			  print("Commentary 3: " + commentary3.text());
			  purport = getPurport(commentaries34.get(0));
			  Commentary byRamanujaAcharya = new Commentary(purport, sourceUrl, v, authorRamanuja);
			  utilDao.save(byRamanujaAcharya);			  
			  
			}

			Element commentary4 = FindNthChild(commentaries34.get(1), "center", 1);
			if (commentary4 != null) {
			  print("Commentary 4: " + commentary4.text());
			  purport = getPurport(commentaries34.get(1));
			  Commentary byKesavaKashmiri = new Commentary(purport, sourceUrl, v, authorKesava);
			  utilDao.save(byKesavaKashmiri);	
			  
			}

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
		authorSridhara = new Author("Sridhar Swami", "http://en.wikipedia.org/wiki/Shreedhar_Swami");
		authorMadhava = new Author("Madhava Acharya", "http://en.wikipedia.org/wiki/Madhvacharya");
		authorRamanuja = new Author("Ramanuja Acharya", "http://en.wikipedia.org/wiki/Ramanuja");
		authorKesava = new Author("Kesave Kashmiri Swami", "http://www.harekrsna.com/philosophy/gss/sadhu/sampradayas/kumara/kumara.htm");		
		try {
			int chapter;
			for (chapter = 1; chapter <= 18; chapter++) {
				verse_offset = 0;
				for (int verse = 1;; verse++) {
					NumberFormat myFormat = NumberFormat.getInstance();
					myFormat.setMinimumIntegerDigits(2);
					String chapter_s = myFormat.format(chapter);
					String verse_s = myFormat.format(verse);
					
					String verse_actual = myFormat.format(verse - verse_offset);
					String verse_file = rootDir + "/verse-" + chapter_s + "-"
							+ verse_actual + ".html";
					// System.out.println("file:" + verse_file);
					File file = new File(verse_file);

					// File file = new
					// File("F:/Dropbox/www.bhagavad-gita.org/Gita/test.html");
					if (!file.exists())
						break;
					System.out.println(file.getCanonicalPath());
					processHTML(verse_file, chapter, verse, chapter_s, verse_s);
					// processHTML("F:/Dropbox/www.bhagavad-gita.org/Gita/test.html");

					// return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
