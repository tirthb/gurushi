package com.gurushi.data;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Meaning extends AbstractEntity {
	
	private String word;
	private String meaning;
	
	public Meaning() {
	}
	
	public Meaning(String word, String meaning) {
		this.word = word;
		this.meaning = meaning;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	@Override
	public String toString() {
		return String.format("%s:%s", word, meaning);
	}

}
