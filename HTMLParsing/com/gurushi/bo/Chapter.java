package com.gurushi.bo;

import java.util.ArrayList;
import java.util.List;

public class Chapter {

	//back reference
	private Scripture scripture; 
	
	//making it String as it need not be a number for all scriptures e.g. roman literal 
	private String number;
	
	private String name;
	
	//some chapters will be description only, for example introduction
	private String description;
	
	//all chapters need not have verses, for example introduction
	private List<Verse> verses;
	
	public Chapter(String number, String name, Scripture scripture) {
		this.number = number;
		this.name = name;
		this.scripture = scripture;
		this.scripture.addChapter(this);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Verse> getVerses() {
		return verses;
	}
	
	public void setVerses(List<Verse> verses) {
		this.verses = verses;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public void addVerse(Verse verse) {
		if (verses == null) {
			verses = new ArrayList<Verse>();
		}
		verses.add(verse);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result
				+ ((scripture == null) ? 0 : scripture.hashCode());
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
		Chapter other = (Chapter) obj;
		if (number == null) {
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (scripture == null) {
				return false;
		} else if (!scripture.equals(other.scripture))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Chapter [number=" + number + ", name=" + name + "]";
	}
	
}
