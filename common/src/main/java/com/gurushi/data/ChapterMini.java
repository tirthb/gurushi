package com.gurushi.data;

public class ChapterMini {
	
	private String id;
	private String number;
	private String title;
	
	public ChapterMini(String id, String number, String title) {
		this.id = id;
	    this.number = number;
	    this.title = title;
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
