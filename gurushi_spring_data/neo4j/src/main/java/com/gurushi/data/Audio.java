package com.gurushi.data;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Audio extends AbstractEntity {
	
	private Integer sortOrder;
	private String title;
	private String url;
	
	public Audio(String title, String url, int sortOrder) {
		this.title = title;
		this.url = url;
		this.sortOrder = sortOrder;
	}
	
	public Audio() {}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	
	@Override
	public String toString() {
		return String.format("audio:%s", title);
	}

}
