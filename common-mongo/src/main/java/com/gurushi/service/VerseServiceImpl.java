package com.gurushi.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
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
		List<ObjectId> verseIds = c.getVerses();
		
		if (verseIds == null) {
			return verses;
		}
		
		for (ObjectId verseId : verseIds) {
			verses.add(rep.findOne(verseId));
		}
		
		return verses;
	}

	@Override
	public Verse previousVerse(Verse v) {
		
		ObjectId previousVerse = v.getPreviousVerse();
		
		if (previousVerse == null) {
			return null;
		}
		
		return rep.findOne(previousVerse);
	}
	
	@Override
	public Verse nextVerse(Verse v) {
		
		ObjectId nextVerse = v.getNextVerse();
		
		if (nextVerse == null) {
			return null;
		}
		
		return rep.findOne(nextVerse);
	}

	@Override
	public Verse findByNumberAndChapter(String number, Chapter ch) {
		return rep.findByNumberAndChapter(number, ch);
	}

	@Override
	public Verse findOne(ObjectId verseId) {
		return rep.findOne(verseId);
	}

}