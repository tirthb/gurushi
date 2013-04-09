package com.gurushi.bo;

import java.util.ArrayList;
import java.util.List;

public class Scripture {
	
	private String name;
	private String description;
	
	public Scripture(String name) {
		this. name = name;
	}
	
	private List<Chapter> chapters;
	
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
	
	public List<Chapter> getChapters() {
		return chapters;
	}
	
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	
	public void addChapter(Chapter c) {
		if (chapters == null) {
			chapters = new ArrayList<Chapter>();
		}
		chapters.add(c);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
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
	
	@Override
	public String toString() {
		return "Scripture [name=" + name + "]";
	}
	
}
