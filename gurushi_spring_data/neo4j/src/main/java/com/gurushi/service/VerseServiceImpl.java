package com.gurushi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.data.Chapter;
import com.gurushi.data.Verse;
import com.gurushi.data.repository.VerseRepository;

@Service
public class VerseServiceImpl implements VerseService {
	
	@Autowired
	private VerseRepository rep;
	
	@Autowired
	private ChapterService chService;

	@Override
	@Transactional
	public Verse save(Verse v) {
		return rep.save(v);
	}

	@Override
	public List<Verse> getAllVersesForAChapter(Chapter c) {
		
		List<Verse> verses = new ArrayList<Verse>();
		
		Verse nextVerse = lazyLoad(chService.firstVerse(c));
		while (nextVerse != null) {
			verses.add(nextVerse);
			nextVerse = lazyLoad(nextVerse).getNextVerse();
		}
		
		return verses;
	}

	@Override
	public Verse previousVerse(Verse v) {
		return rep.previousVerse(v);
	}
	
	@Override
	public Verse nextVerse(Verse v) {
		return rep.nextVerse(v);
	}

	@Override
	public Verse findByNumberAndChapter(String number, Chapter ch) {
		return lazyLoad(rep.findByNumberAndChapter(number, ch));
	}
	
	private Verse lazyLoad(Verse v) {
		
		if (v != null && v.getId() != null) {
			v.setNextVerse(nextVerse(v));
		}
		return v;
	}

}