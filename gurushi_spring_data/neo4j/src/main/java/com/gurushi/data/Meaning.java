package com.gurushi.data;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Meaning extends AbstractEntity {
	
	private Integer sortOrder;
	private String word;
	private String meaning;
	
	public Meaning() {
	}
	
	public Meaning(String word, String meaning, int sortOrder) {
		this.word = word;
		this.meaning = meaning;
		this.sortOrder = sortOrder;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}

	@Override
	public String toString() {
		return String.format("%s(%d):%s", word, getId(), meaning);
	}

}
