package com.gurushi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.data.repository.ChapterRepository;

@Service
public class ChapterServiceImpl implements ChapterService {
	
	@Autowired
	private ChapterRepository rep;

	@Override
	@Transactional
	public Chapter save(Chapter c) {
		return rep.save(c);
	}

	@Override
	public List<Chapter> getAllChaptersForAScripture(Scripture s) {
		
		List<Chapter> chapters = new ArrayList<Chapter>();
		
		Chapter nextChapter = rep.findOne(s.getFirstChapter().getId());
		while (nextChapter != null) {
			chapters.add(nextChapter);
			nextChapter = nextChapter.getNextChapter();
		}
		
		return chapters;
	}

	@Override
	public Chapter previousChapter(Chapter c) {
		return rep.previousChapter(c);
	}

	@Override
	public Chapter findByTitleAndScripture(String title, Scripture s) {
		return rep.findByTitleAndScripture(title, s);
	}

}