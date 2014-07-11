package com.gurushi.dao;

import java.util.List;

import com.gurushi.domain.Chapter;
import com.gurushi.domain.Scripture;

public interface ChapterDao {

	public List<Chapter> findAllChaptersForAScripture(Scripture s);
	
	public Chapter findChapterByNumber(String number, Scripture s);
}
