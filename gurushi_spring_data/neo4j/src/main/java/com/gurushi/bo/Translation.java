package com.gurushi.bo;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Translation extends AbstractEntity {
	
	private String text;
	
	private String sourceUrl;
	
	@RelatedTo
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
		return String.format("Translation %d", getId());
	}
	
}