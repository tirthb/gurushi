package com.gurushi.bo;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Video extends AbstractEntity {
	
	private String title;
	private String url;
	
	public Video(String title, String url) {
		this.title = title;
		this.url = url;
	}
	
	public Video() {}
	
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
	
	@Override
	public String toString() {
		return String.format("vdo:%s", title);
	}

}
