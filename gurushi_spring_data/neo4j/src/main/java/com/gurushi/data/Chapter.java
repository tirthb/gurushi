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
	private String name;
	
	//some chapters will be description only, for example introduction
	@Indexed(indexType = IndexType.FULLTEXT,indexName = "chapter_search")
	private String description;
	
	@RelatedTo
	@Fetch
	private Chapter nextChapter;
	
	//all chapters need not have verses, for example introduction
	@RelatedTo
	private Verse firstVerse;
	
	public Chapter(String number, String name, Scripture scripture) {
		this.number = number;
		this.name = name;
		this.scripture = scripture;
	}
	
	public Chapter() {}
	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result
				+ ((scripture == null) ? 0 : scripture.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
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
		return String.format("Chapter %s - %s of %s", name, number, scripture);
	}
	
}
