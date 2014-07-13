package com.gurushi.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.data.repository.ChapterRepository;
import com.gurushi.data.repository.VerseRepository;
import com.gurushi.data.to.ChapterMini;

@Service
public class ChapterServiceImpl implements ChapterService {
	
	@Autowired
	private ChapterRepository rep;
	
	@Autowired
	private VerseRepository vs;
	
	@Override
	@Transactional
	public Chapter save(Chapter c) {
		return rep.save(c);
	}

	@Override
	public List<Chapter> getAllChaptersForAScripture(Scripture s) {
		
		List<Chapter> chapters = new ArrayList<Chapter>();
		List<ObjectId> chapterIds = s.getChapters();
		
		if (chapterIds == null) {
			return chapters;
		}
		
		for (ObjectId chapterId : chapterIds) {
			Chapter ch = rep.findOne(chapterId);
			chapters.add(ch);
			
		}
		
		return chapters;
	}
	
	public List<ChapterMini> getAllChapterMinisForAScripture(Scripture s) {
		List<Chapter> allChapters = getAllChaptersForAScripture(s);
		
		List<ChapterMini> allChapterMinis = new ArrayList<ChapterMini>();
		for (Chapter ch : allChapters) {
	        allChapterMinis.add(new ChapterMini(ch.getId().toString(), ch.getNumber(), ch.getTitle()));
        }
		
		return allChapterMinis;
	}

	@Override
	public Chapter previousChapter(Chapter c) {
		
		ObjectId previousChapter = c.getPreviousChapter();
		
		if (previousChapter == null) {
			return null;
		}
		
		return rep.findOne(previousChapter);
	}
	
	@Override
	public Chapter nextChapter(Chapter c) {
		
		ObjectId nextChapter = c.getNextChapter();
		
		if (nextChapter == null) {
			return null;
		}
		
		return rep.findOne(nextChapter);
	}

	@Override
	public Chapter findByTitleAndScripture(String title, Scripture s) {
		return rep.findByTitleAndScripture(title, s);
	}

	@Override
	public Chapter findByNumberAndScripture(String number, Scripture s) {
		return rep.findByNumberAndScripture(number, s);
	}

	@Override
	public Chapter findOne(ObjectId chapterId) {
		return rep.findOne(chapterId);
	}
	
}