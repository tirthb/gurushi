package com.gurushi.service;

import java.util.List;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.data.Verse;

public interface ChapterService {

	public Chapter save(Chapter c);
	
	public List<Chapter> getAllChaptersForAScripture(Scripture s);
	
	public Chapter previousChapter(Chapter c);
	
	public Chapter nextChapter(Chapter c);
	
	public Verse firstVerse(Chapter c);
	
	public Chapter findByTitleAndScripture(String title, Scripture s);
	
	public Chapter findByNumberAndScripture(String number, Scripture s);
}
