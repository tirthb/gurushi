package com.gurushi.loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gurushi.dao.ChapterDao;
import com.gurushi.dao.ScriptureDao;
import com.gurushi.dao.UtilDao;
import com.gurushi.dao.VerseDao;
import com.gurushi.domain.Author;
import com.gurushi.domain.Chapter;
import com.gurushi.domain.Commentary;
import com.gurushi.domain.Meaning;
import com.gurushi.domain.Scripture;
import com.gurushi.domain.Translation;
import com.gurushi.domain.Verse;

@Component
public class VedaBaseDotNetGita extends ScriptureSource {
	
	public static final String SOURCE_URL_PREFIX = "http://vedabase.net/bg/"; 
	final Logger logger = LoggerFactory.getLogger(VedaBaseDotNetGita.class);
	
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
	
	//TODO:remove blank constructor
	public VedaBaseDotNetGita() {}
	
	public VedaBaseDotNetGita(String rootDirectory, Scripture sc) {
		
		super(rootDirectory, sc);
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
	
	private List<String> getVisibleText(List<Element> ps) {
		List<String> content = new ArrayList<String>();
		for (int i = 0; i < ps.size(); i++) {
			String html = ps.get(i).html();
			String html_text = Jsoup.parse(html).text();		
			content.add(html_text);	

		}		
		return content;		
	}
	
	private String[] getVerseLines(Element root) {   		
		
		List<Element> verse_nodes = root.select("p.c");
		List<String> lines = getVisibleText(verse_nodes);	
		return lines.toArray(new String [lines.size()]);
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
			
			verse.setVerseLines(getVerseLines(doc.select("body").get(0)));
			logger.debug("Verse: \n" + verse.getVerseLines().get(0));
			
			//saving verse
			verse = utilDao.save(verse);
			
			String sourceUrl = SOURCE_URL_PREFIX + ch.getNumber() + "/" + verse.getNumberRange() + "/en";
			
			Translation trans = new Translation(author, verse);
			trans.setText(getTranslation(doc.select("body").get(0)));
			trans.setSourceUrl(sourceUrl);
			
			//saving trans
			trans = utilDao.save(trans);
			
			logger.debug("Translation: \n" + trans.getText());

			String synonyms = getSynonyms(doc.select("body").get(0));
			String[] words = synonyms.split(";");
			
			logger.debug("Meanings:");
			for (int i = 0; i < words.length; i++) {
				String word[] = words[i].split(" — ");
				logger.debug(word[0] + " - " + word[1]);
				
				//saving meaning
				utilDao.save(new Meaning(word[0], word[1], i+1, verse));
			}
			
			Commentary purport = new Commentary(getPurport(doc.select("body").get(0)), sourceUrl, verse, author);
			
			//saving commentary
			purport = utilDao.save(purport);
			
			logger.debug("Purport:\n" + purport.getPurport());

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
		
		for (chapter = 1; chapter <= 18; chapter++) {

			ch = new Chapter(Integer.toString(chapter), sc);
			ch = utilDao.save(ch);
			
			chapters.add(ch);
		}
	}
	
	public void loadData() {
		
		try {
			
			author = new Author("His Grace Bhakti Vedanta Swami Srila Prabhupada", 
					"http://en.wikipedia.org/wiki/A._C._Bhaktivedanta_Swami_Prabhupada",
					"Gaudiya Vaisnava Sampradaya");
			author.setSortOrder(10);
			
			author = utilDao.save(author);
			
			createChapters();
			
			int chapterIndex = 1;
			Verse currentVerse = null;
			
			for (Chapter ch : chapters) {
				
				String chapterDirPath = rootDir + "/" + chapterIndex + "/";
				
				List<String> verseNumbers = getVerseNumbers(chapterDirPath);
				
				for (String verseNum : verseNumbers) {
					
					String verseFile = rootDir + "/" + chapterIndex + "/" + verseNum + "/en";
					
					logger.info("File Being Processed: " + verseFile);
					logger.info("Chapter = " + chapterIndex + " Verse = " + verseNum);
					
					File file = new File(verseFile);
					
					if (!file.exists()) {
						logger.warn("File not found: " + file.getCanonicalPath());
						continue;
					}
					
					currentVerse = new Verse(verseNum, ch);
					processVerse(ch, currentVerse, verseFile);
					
					//if first verse, set the title
					if (verseNum.equals(verseNumbers.get(0))) {
						
						Document doc = Jsoup.parse(file, "UTF-8", "");
						ch.setTitle(getChapterTitle(doc));
					}
					
					ch = utilDao.save(ch);
					
					//if (verseNum.equals(verseNumbers.get(2))) break;
				}
				
				chapterIndex++;
				
				//if (chapterIndex == 3) return;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	private List<String> getVerseNumbers(String chapterDirPath) {
		
		List<String> verseNumbers = new ArrayList<String>();
		
		File chapterDir = new File(chapterDirPath);
		
		List<File> verseDirList = Arrays.asList(chapterDir.listFiles());
		
		for (File verseDir : verseDirList) {
			if(verseDir.isDirectory()) {
				verseNumbers.add(verseDir.getName());
			}
		}
		
		orderVerseNumbers(verseNumbers);
		
		return verseNumbers;
	}
	
	private void orderVerseNumbers(List<String> verseNumbers) {

		//sorting the verse numbers, please note some have dashes
		Collections.sort(
				verseNumbers, 
				new Comparator<String>() {
					@Override
					public int compare(String o1, String o2) {
						String[] o1Arr = o1.split("-");
						o1 = o1Arr[0];

						String[] o2Arr = o2.split("-");
						o2 = o2Arr[0];

						return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
					}
				}
			);

		//we would like to remove "21" if it is followed by "21,21-22". Also "2" if preceded by "1-2"

		String [] verseNumbersCopy = verseNumbers.toArray(new String[verseNumbers.size()]);

		for (int i = 0; i < verseNumbersCopy.length; i++) {
			String verseNumber = verseNumbersCopy[i];

			if (verseNumber.contains("-")) {
				String[] verseNumArr = verseNumber.split("-");

				//remove "21" if it is followed by "21,21-22" or remove 25 and 26 if followed by "25,26,25-28"
				if (i > 0) {
					for (int j = i - 1; j >= 0; j--)
					{
					  //int j = i - 1;
					  if (verseNumbersCopy[j].contains("-")) {
						  break;
					  }

					  if ( Integer.valueOf(verseNumbersCopy[j]) >= Integer.valueOf(verseNumArr[0])  && Integer.valueOf(verseNumbersCopy[j]) <= Integer.valueOf(verseNumArr[1]) ) {
						  verseNumbers.remove(verseNumbersCopy[j]);
					  } else {
						  break;
					  }
					}
				}

				//remove "2" if preceded by "1-2" or remove 21 and 23 when "20-23, 21, 22"
				if (i < verseNumbersCopy.length - 1) {
					for (int j = i + 1; j <= verseNumbersCopy.length - 1; j++)
					{
					  //int j = i - 1;
					  if (verseNumbersCopy[j].contains("-")) {
						  break;
					  }
					  
					  if ( Integer.valueOf(verseNumbersCopy[j]) >= Integer.valueOf(verseNumArr[0])  && Integer.valueOf(verseNumbersCopy[j]) <= Integer.valueOf(verseNumArr[1]) ) {
						  verseNumbers.remove(verseNumbersCopy[j]);
					  } else {
						  break;
					  }
					}
				}
			}
			
		
		}
		
	}
}
