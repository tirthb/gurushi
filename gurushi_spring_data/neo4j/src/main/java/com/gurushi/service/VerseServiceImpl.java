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

	@Override
	@Transactional
	public Verse save(Verse v) {
		return rep.save(v);
	}

	@Override
	public List<Verse> getAllVersesForAChapter(Chapter c) {
		
		List<Verse> verses = new ArrayList<Verse>();
		
		Verse nextVerse = rep.findOne(c.getFirstVerse().getId());
		while (nextVerse != null) {
			verses.add(nextVerse);
			nextVerse = nextVerse.getNextVerse();
		}
		
		return verses;
	}

	@Override
	public Verse previousVerse(Verse v) {
		return rep.previousVerse(v);
	}

	@Override
	public Verse findByNumberAndChapter(String number, Chapter ch) {
		return rep.findByNumberAndChapter(number, ch);
	}

}