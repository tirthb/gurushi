package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="author")
public class Author extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
	@Column(name="name")
	private String name;
	
	@Column(name="sampradaya")
	private String sampradaya;
	
	@Column(name="wikipedia_url")
	private String wikipediaUrl;
	
	@Column(name="sort_order")
	private Integer sortOrder;
	
	public Author() {}
	
	public Author(String name, String wikiUrl, String sampradaya) {
		this.name = name;
		this.wikipediaUrl = wikiUrl;
		this.sampradaya = sampradaya;
	}
	
	public Author(String name, String wikiUrl) {
		this.name = name;
		this.wikipediaUrl = wikiUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSampradaya() {
		return sampradaya;
	}

	public void setSampradaya(String sampradaya) {
		this.sampradaya = sampradaya;
	}

	public String getWikipediaUrl() {
		return wikipediaUrl;
	}

	public void setWikipediaUrl(String wikipediaUrl) {
		this.wikipediaUrl = wikipediaUrl;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + "]";
	}
}
