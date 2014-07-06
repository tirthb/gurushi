package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="meaning")
public class Meaning extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name="word")
	private String word;
    
    @Column(name="meaning")
	private String meaning;
	
	@Column(name="sort_order")
	private Integer sortOrder;
	
	@ManyToOne
	@JoinColumn(name="verse_id")
	private Verse verse;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public Verse getVerse() {
		return verse;
	}
	public void setVerse(Verse verse) {
		this.verse = verse;
	}

	@Override
    public String toString() {
	    return "Meaning [word=" + word + ", verse=" + verse + "]";
    }
	
}