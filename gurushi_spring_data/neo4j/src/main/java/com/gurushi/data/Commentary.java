package com.gurushi.data;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Commentary extends AbstractEntity {
	
	private String text;
	private String sourceUrl;
	
	@RelatedTo
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

	@Override
	public String toString() {
		return String.format("Commentary %d of %s", getId(), author);
	}
	
}