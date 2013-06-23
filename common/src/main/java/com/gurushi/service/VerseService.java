package com.gurushi.service;

import java.util.List;

import org.bson.types.ObjectId;

import com.gurushi.data.Chapter;
import com.gurushi.data.Verse;

public interface VerseService {

	public Verse save(Verse c);
	
	public List<Verse> getAllVersesForAChapter(Chapter c);
	
	public Verse previousVerse(Verse v);
	
	public Verse nextVerse(Verse v);
	
	public Verse findByNumberAndChapter(String number, Chapter c);
	
	public Verse findOne(ObjectId verseId);
}
