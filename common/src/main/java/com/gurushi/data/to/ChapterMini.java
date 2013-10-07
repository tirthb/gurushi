package com.gurushi.data.to;

import com.gurushi.data.Chapter;

public class ChapterMini {
	
	private String id;
	private String number;
	private String title;
	
	public ChapterMini(String id, String number, String title) {
		this.id = id;
	    this.number = number;
	    this.title = title;
    }
	
	public ChapterMini(Chapter ch) {
		this.id = ch.getId() == null ? null : ch.getId().toString();
	    this.number = ch.getNumber();
	    this.title = ch.getTitle();
    }

	public String getNumber() {
		return number;
	}

	public String getTitle() {
		return title;
	}
	
	public String getId() {
		return id;
	}
	
}
