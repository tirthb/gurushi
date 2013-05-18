package com.gurushi.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Chapter extends AbstractDocument {
	
	@DBRef
	private Scripture scripture; 
	
	//making it String as it need not be a number for all scriptures e.g. Roman literal
	@Indexed
	private String number;
	
	@Indexed
	private String title;
	
	//TODO: full text index in mongo db?
	private String description;
	
	private ObjectId nextChapter;
	
	private ObjectId previousChapter;
	
	//all chapters need not have verses, for example introduction
	private List<ObjectId> verses;
	
	public Chapter(String number, String title, Scripture scripture) {
		this.number = number;
		this.title = title;
		this.scripture = scripture;
	}
	
	public Chapter(String number, Scripture scripture) {
		this.number = number;
		this.scripture = scripture;
	}
	
	public Chapter() {}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String name) {
		this.title = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ObjectId getNextChapter() {
		return nextChapter;
	}

	public void setNextChapter(ObjectId nextChapter) {
		this.nextChapter = nextChapter;
	}
	
	public ObjectId getPreviousChapter() {
		return previousChapter;
	}

	public void setPreviousChapter(ObjectId previousChapter) {
		this.previousChapter = previousChapter;
	}

	public List<ObjectId> getVerses() {
		return verses;
	}
	
	public void addVerse(ObjectId verse) {
		if (verses == null) {
			verses = new ArrayList<ObjectId>();
		}
		verses.add(verse);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public Scripture getScripture() {
		return scripture;
	}
	
	public String toString() {
		return String.format("Chapter(%d) %s", getId(), title);
	}
	
}
