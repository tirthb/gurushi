package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="verse_number")
public class VerseNumber extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name="number")
	private Integer number;
    
	@ManyToOne
	@JoinColumn(name="verse_id")
	private Verse verse;
	
	public VerseNumber() {}
	
	public VerseNumber(Integer number, Verse v) {
		this.number = number;
		this.verse = v;
	}
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}

	public Verse getVerse() {
		return verse;
	}
	public void setVerse(Verse verse) {
		this.verse = verse;
	}

	@Override
    public String toString() {
	    return "VerseNumber [number=" + number + ", verse=" + verse + "]";
    }
	
}