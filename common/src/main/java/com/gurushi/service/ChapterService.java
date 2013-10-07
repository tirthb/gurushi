package com.gurushi.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.data.to.ChapterMini;

public interface ChapterService {

	public Chapter save(Chapter c);
	
	public List<Chapter> getAllChaptersForAScripture(Scripture s);
	
	public List<ChapterMini> getAllChapterMinisForAScripture(Scripture s);
	
	public Chapter previousChapter(Chapter c);
	
	public Chapter nextChapter(Chapter c);
	
	public Chapter findByTitleAndScripture(String title, Scripture s);
	
	public Chapter findByNumberAndScripture(String number, Scripture s);
	
	public Chapter findOne(ObjectId chapterId);
}
