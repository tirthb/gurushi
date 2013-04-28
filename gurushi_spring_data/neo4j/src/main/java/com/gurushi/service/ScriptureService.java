package com.gurushi.service;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;

public interface ScriptureService {

	public Scripture findByName(String name);
	
	public Chapter firstChapter(Scripture s);
	
	public Scripture save(Scripture s);
	
}
