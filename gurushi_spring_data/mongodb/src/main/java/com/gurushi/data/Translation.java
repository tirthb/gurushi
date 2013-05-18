package com.gurushi.data;


public class Translation {
	
	private String text;
	
	private String sourceUrl;
	
	private Author author;
	
	public Translation(Author author) {
		this.author = author;
	}
	
	public Translation() {}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Author getAuthor() {
		return author;
	}
	
	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String toString() {
		return String.format("Translation %s", 
								(text != null && text.length() > 10) ? text.substring(0,10) : text);
	}
	
}