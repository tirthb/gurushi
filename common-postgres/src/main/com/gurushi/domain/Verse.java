package com.gurushi.domain;

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
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "verse")
	private List<VerseNumber> verseNumbers;
	
	/**
	 * one line of a verse, we need a separator to display e.g. <br/> 
	 */
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy = "verse")
	private List<VerseLine> verseLines;
	
	public String getNumberRange() {
		return numberRange;
	}
	public void setNumberRange(String numberRange) {
		this.numberRange = numberRange;
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
	public void setVerseNumbers(List<VerseNumber> verseNumbers) {
		this.verseNumbers = verseNumbers;
	}
	
	public void setVerseLines(List<VerseLine> verseLines) {
		this.verseLines = verseLines;
	}
	public List<VerseLine> getVerseLines() {
		return verseLines;
	}
		
	@Override
    public String toString() {
	    return "Verse [number=" + numberRange + ", chapter=" + chapter + "]";
    }
	
}
