package com.gurushi.data;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Commentary extends AbstractDocument {
	
	//back reference
	private ObjectId verse;
	
	private String text;
	private String sourceUrl;
	
	private Author author;
	
	public Commentary(String text, String sourceUrl, Author author) {
		this.text = text;
		this.sourceUrl = sourceUrl;
		this.author = author;
	}
	
	public Commentary() {}

	public String getText() {
		return text;
	}

	public Author getAuthor() {
		return author;
	}
	
	public String getSourceUrl() {
		return sourceUrl;
	}
	
	public ObjectId getVerse() {
		return verse;
	}

	public void setVerse(ObjectId verse) {
		this.verse = verse;
	}

	@Override
	public String toString() {
		return String.format("Commentary by %s of verse %s", author, verse);
	}
	
}