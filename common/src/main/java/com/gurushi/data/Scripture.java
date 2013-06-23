package com.gurushi.data;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.Assert;

@Document
public class Scripture extends AbstractDocument {
	
	@Indexed(unique = true)
	private String name;
	
	private String description;
	
	private List<ObjectId> chapters;
	
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
	
	public List<ObjectId> getChapters() {
		return chapters;
	}
	
	public void addChapter(ObjectId chapter) {
		if (chapters == null) {
			chapters = new ArrayList<ObjectId>();
		}
		chapters.add(chapter);
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