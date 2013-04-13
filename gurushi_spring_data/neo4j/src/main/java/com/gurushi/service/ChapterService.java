package com.gurushi.service;

import java.util.List;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;

public interface ChapterService {

	public Chapter save(Chapter c);
	
	public List<Chapter> getAllChaptersForAScripture(Scripture s);
	
	public Chapter previousChapter(Chapter c);
	
	public Chapter findByTitleAndScripture(String title, Scripture s); 
}
