package com.gurushi.bo;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.util.Assert;

@NodeEntity
public class Scripture extends AbstractEntity {
	
	@Indexed(unique = true)
	private String name;
	
	private String description;
	
	@RelatedTo
	private Chapter firstChapter;
	
	public Scripture(String name) {
		
		Assert.hasText(name);
		
		this. name = name;
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
	
	public Chapter getFirstChapter() {
		return firstChapter;
	}

	public void setFirstChapter(Chapter firstChapter) {
		this.firstChapter = firstChapter;
	}

	@Override
	public String toString() {
		return String.format("Scripture - %s", name);
	}
	
}