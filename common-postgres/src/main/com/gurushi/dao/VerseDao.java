package com.gurushi.dao;

import com.gurushi.domain.Chapter;
import com.gurushi.domain.Verse;

public interface VerseDao {

	public Verse findByNumberAndChapter(String number, Chapter c);
	
}
