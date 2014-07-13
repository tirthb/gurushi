package com.gurushi.data.to;

import java.util.ArrayList;
import java.util.List;

import com.gurushi.data.Chapter;

public class ChapterWithVerses extends Chapter {
	
	private List<VerseMini> verseMinis = new ArrayList<VerseMini>();
	
	public ChapterWithVerses(Chapter ch) {
		
		this.id = ch.getId();
	    this.setDescription(ch.getDescription());
	    this.setNextChapter(ch.getNextChapter());
	    this.setNumber(ch.getNumber());
	    this.setPreviousChapter(ch.getPreviousChapter());
	    this.setTitle(ch.getTitle());
    }
	
	public void addVerseMini(VerseMini v) {
		verseMinis.add(v);
	}
	
	public List<VerseMini> getVerseMinis() {
	    return verseMinis;
    }

}
