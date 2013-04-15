package com.gurushi.service;

import java.util.List;

import com.gurushi.data.Chapter;
import com.gurushi.data.Verse;

public interface VerseService {

	public Verse save(Verse c);
	
	public List<Verse> getAllVersesForAChapter(Chapter c);
	
	public Verse previousVerse(Verse c);
	
	public Verse findByNumberAndChapter(String number, Chapter c); 
}
