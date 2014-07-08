package com.gurushi.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="verse")
public class Verse extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
	/**
	 * e.g. number_range can be "10-15","20-21" etc.
	 */
	@Column(name="number_range")
	private String numberRange;
	
	@Column(name="sort_order")
	private Integer sortOrder;
	
	@ManyToOne
	@JoinColumn(name="chapter_id")
	private Chapter chapter;
	
	
	/**
	 * verseNumbers saves the individual numbers in number range, e.g. number range "10 - 15" 
	 * will have numbers 10 through 15 in verseNumbers.
	 * Please numbers can be Roman numeric for some scriptures, so they are string.
	 * The purpose of this field is to fetch easily.
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true, mappedBy = "verse")
	private List<VerseNumber> verseNumbers;
	
	/**
	 * one line of a verse, we need a separator to display e.g. <br/> 
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true, mappedBy = "verse")
	private List<VerseLine> verseLines;
	
	public Verse() {}
	
	public Verse(String numberRange, Chapter c) {
		setNumberRange(numberRange);
		this.chapter = c;
	}
	
	public String getNumberRange() {
		return numberRange;
	}
	public void setNumberRange(String numberRange) {
		numberRange = numberRange.trim();
		this.numberRange = numberRange;
		
		if(!numberRange.contains("-")) {
			addVerseNumber(new VerseNumber(Integer.parseInt(numberRange), this));
		} else {
			String[] numbers = numberRange.split("( +)?\\-( +)?");
			if (numbers.length != 2) {
				throw new IllegalStateException("number range can be of the format x or x-y");
			}
			int start = Integer.parseInt(numbers[0]);
			int end = Integer.parseInt(numbers[1]);
			
			for (int i = start; i <= end; i++) {
				addVerseNumber(new VerseNumber(i, this));
            }
		}
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public Chapter getChapter() {
		return chapter;
	}
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}
	
	public List<VerseNumber> getVerseNumbers() {
		return verseNumbers;
	}
	void setVerseNumbers(List<VerseNumber> verseNumbers) {
		this.verseNumbers = verseNumbers;
	}
	public void addVerseNumber(VerseNumber verseNumber) {
		if (verseNumbers == null) {
			verseNumbers = new ArrayList<VerseNumber>();
		}
		verseNumbers.add(verseNumber);
	}
	
	public void setVerseLines(List<VerseLine> verseLines) {
		this.verseLines = verseLines;
	}
	public List<VerseLine> getVerseLines() {
		return verseLines;
	}
	public void addVerseLine(VerseLine verseLine) {
		if (verseLines == null) {
			verseLines = new ArrayList<VerseLine>();
		}
		verseLines.add(verseLine);
	}
		
	@Override
    public String toString() {
	    return "Verse [number=" + numberRange + ", chapter=" + chapter + "]";
    }
	
}
