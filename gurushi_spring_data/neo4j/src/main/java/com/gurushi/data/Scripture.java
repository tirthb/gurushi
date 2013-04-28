package com.gurushi.data;

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
	//@Fetch
	private Chapter firstChapter;
	
	public Scripture(String name) {
		
		Assert.hasText(name);
		
		this.name = name;
	}
	
	public Scripture() {}
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Scripture other = (Scripture) obj;
		if (name == null) {
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}