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

import com.gurushi.data.Author;
import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.service.ChapterService;
import com.gurushi.service.ScriptureService;
import com.gurushi.service.VerseService;

public class BhagavadGitaDotOrg extends ScriptureSource {
	public static final String SOURCE_URL_PREFIX = "http://www.bhagavad-gita.org/Gita";
	final Logger logger = LoggerFactory.getLogger(BhagavadGitaDotOrg.class);

	Author author;
	List<Chapter> chapters = new ArrayList<Chapter>();

	@Autowired
	VerseService vs;

	@Autowired
	ChapterService cs;

	@Autowired
	ScriptureService ss;

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

	private void processHTML(String file) {
		File input = new File(file);
		Document doc;
		try {
			doc = Jsoup.parse(input, "UTF-8", "");

			Elements tables = doc.select("table");
			System.out.println(tables.size());

			// Elements table_children = doc.select("table table");
			Elements table_children = doc.select("body > table");

			// Elements table_children.get(1)
			System.out.println(table_children.size());
			System.out.println(table_children.get(1).html());
			// tables.removeAll(table_children);
			// System.out.println(tables.size());

			// System.out.println(tables.get(1).html());

			// Element html = FindNthChild(doc, "html", 1);
			// Element body = FindNthChild(html, "body", 1);
			// Element table = FindNthChild(body, "table", 2);
			Element tbody = FindNthChild(table_children.get(1), "tbody", 1);
			List<Element> trs = GetMatchingChilds(tbody, "tr");

			// List<Element> trs = GetMatchingChilds(, "tr");

			print("\nRows: " + trs.size());
			List<Element> commentaries12 = GetMatchingChilds(trs.get(0), "td");
			print("\ncommentaries12: " + commentaries12.size());
			List<Element> commentaries34_td = GetMatchingChilds(trs.get(1),
					"td");
			print("\ncommentaries34_td: " + commentaries34_td.size());

			Element commentary1 = FindNthChild(commentaries12.get(0), "center",
					1);
			print("Commentary 1: " + commentary1.text());

			Element commentary2 = FindNthChild(commentaries12.get(1), "center",
					1);
			print("Commentary 2: " + commentary2.text());

			Element commentary3_div = FindNthChild(commentaries34_td.get(0),
					"div", 1);
			Element commentary3 = FindNthChild(commentary3_div, "u", 1);
			print("Commentary 3: " + commentary3.text());

			Element commentary4 = FindNthChild(commentaries34_td.get(1),
					"center", 1);
			print("Commentary 4: " + commentary4.text());

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
					NumberFormat myFormat = NumberFormat.getInstance();
					myFormat.setMinimumIntegerDigits(2);
					String chapter_s = myFormat.format(chapter);
					String verse_s = myFormat.format(verse);
					String verse_file = rootDir + "/verse-" + chapter_s + "-"
							+ verse_s + ".html";
					// System.out.println("file:" + verse_file);
					File file = new File(verse_file);

					// File file = new
					// File("F:/Dropbox/www.bhagavad-gita.org/Gita/test.html");
					if (!file.exists())
						break;
					System.out.println(file.getCanonicalPath());
					processHTML(verse_file);
					// processHTML("F:/Dropbox/www.bhagavad-gita.org/Gita/test.html");

					// return;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
