package com.gurushi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.data.Verse;
import com.gurushi.data.repository.ChapterRepository;

@Service
public class ChapterServiceImpl implements ChapterService {
	
	@Autowired
	private ChapterRepository rep;
	
	@Autowired
	private ScriptureService scService;

	@Override
	@Transactional
	public Chapter save(Chapter c) {
		return rep.save(c);
	}

	@Override
	public List<Chapter> getAllChaptersForAScripture(Scripture s) {
		
		List<Chapter> chapters = new ArrayList<Chapter>();
		Chapter firstChapter = lazyLoad(scService.firstChapter(s));
		
		Chapter nextChapter = firstChapter;
		while (nextChapter != null) {
			chapters.add(nextChapter);
			nextChapter = lazyLoad(nextChapter).getNextChapter();
		}
		
		return chapters;
	}

	@Override
	public Chapter previousChapter(Chapter c) {
		return rep.previousChapter(c);
	}
	
	@Override
	public Chapter nextChapter(Chapter c) {
		return rep.nextChapter(c);
	}

	@Override
	public Chapter findByTitleAndScripture(String title, Scripture s) {
		return lazyLoad(rep.findByTitleAndScripture(title, s));
	}

	@Override
	public Chapter findByNumberAndScripture(String number, Scripture s) {
		return lazyLoad(rep.findByNumberAndScripture(number, s));
	}
	
	private Chapter lazyLoad(Chapter c) {
		
		if (c != null && c.getId() != null) {
			c.setNextChapter(nextChapter(c));
		}
		return c;
	}

	@Override
	public Verse firstVerse(Chapter c) {
		return rep.firstVerse(c);
	}
	
}