package com.gurushi.data;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Chapter extends AbstractEntity {
	
	@RelatedTo
	private Scripture scripture; 
	
	//making it String as it need not be a number for all scriptures e.g. Roman literal
	@Indexed
	private String number;
	
	@Indexed
	private String title;
	
	//some chapters will be description only, for example introduction
	@Indexed(indexType = IndexType.FULLTEXT,indexName = "chapter_search")
	private String description;
	
	@RelatedTo
	@Fetch
	private Chapter nextChapter;
	
	//all chapters need not have verses, for example introduction
	@RelatedTo
	@Fetch
	private Verse firstVerse;
	
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
	
	public Chapter getNextChapter() {
		return nextChapter;
	}

	public void setNextChapter(Chapter nextChapter) {
		this.nextChapter = nextChapter;
	}

	public Verse getFirstVerse() {
		return firstVerse;
	}

	public void setFirstVerse(Verse firstVerse) {
		this.firstVerse = firstVerse;
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
