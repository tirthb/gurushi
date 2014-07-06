package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="author")
public class Author extends AbstractEntityAssigned {

    private static final long serialVersionUID = 1L;
    
	@Column(name="name")
	private String name;
	
	@Column(name="sampradaya")
	private String sampradaya;
	
	@Column(name="wikipedia_url")
	private String wikipediaUrl;
	
	@Column(name="sort_order")
	private Integer sortOrder;

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
