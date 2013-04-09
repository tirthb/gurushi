package com.gurushi.bo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Verse {
	
	//back reference
	private Chapter chapter;
	
	private int number;
	
	private String text;
	
	//restricting to one translation per verse for less confusion
	private Translation translation;
	
	private Map<String/*word*/, String/*meaning*/> meanings;
	
	private List<Commentary> commentaries;
	
	public Verse(int number, Chapter chapter) {
		this.number = number;
		this.chapter = chapter;
		chapter.addVerse(this);
	}

	public Chapter getChapter() {
		return chapter;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Translation getTranslation() {
		return translation;
	}

	public void setTranslation(Translation translation) {
		this.translation = translation;
	}

	public Map<String, String> getMeanings() {
		return meanings;
	}

	public void setMeanings(Map<String, String> meanings) {
		this.meanings = meanings;
	}
	
	public void putMeaning(String word, String meaning) {
		if (meanings == null) {
			meanings = new LinkedHashMap<String, String>();
		}
		meanings.put(word, meaning);
	}
	
	public List<Commentary> getCommentaries() {
		return commentaries;
	}

	public void setCommentaries(List<Commentary> commentaries) {
		this.commentaries = commentaries;
	}
	
	public void addCommentary(Commentary commentary) {
		if (commentaries == null) {
			commentaries = new ArrayList<Commentary>();
		}
		
		commentaries.add(commentary);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chapter == null) ? 0 : chapter.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Verse other = (Verse) obj;
		if (chapter == null) {
				return false;
		} else if (!chapter.equals(other.chapter))
			return false;
		if (number != other.number)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Verse [chapter=" + chapter + ", number=" + number + "]";
	}
	
}
