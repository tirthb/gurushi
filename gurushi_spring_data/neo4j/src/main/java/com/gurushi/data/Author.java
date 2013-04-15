package com.gurushi.data;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Author extends AbstractEntity {
	
	@Indexed(unique = true)
	private String name;
	private String wikipediaUrl;
	private String sampradaya;
	
	public Author(String name) {
		this.name = name;
	}
	
	public Author(String name, String wikipediaUrl) {
		this.name = name;
		this.wikipediaUrl = wikipediaUrl;
	}
	
	public Author(String name, String wikipediaUrl, String sampradaya) {
		this.name = name;
		this.wikipediaUrl = wikipediaUrl;
		this.sampradaya = sampradaya;
	}
	
	public Author() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getWikipediaUrl() {
		return wikipediaUrl;
	}

	public void setWikipediaUrl(String wikipediaUrl) {
		this.wikipediaUrl = wikipediaUrl;
	}
	
	public String getSampradaya() {
		return sampradaya;
	}

	public void setSampradaya(String sampradaya) {
		this.sampradaya = sampradaya;
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
		Author other = (Author) obj;
		if (name == null) {
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Author is %s", name);
	}
	
}