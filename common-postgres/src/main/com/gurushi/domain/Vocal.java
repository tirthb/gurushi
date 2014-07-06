package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vocal")
public class Vocal extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name="title")
	private String title;
    
    @Column(name="url")
	private String url;
	
	@ManyToOne
	@JoinColumn(name="verse_id")
	private Verse verse;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Verse getVerse() {
		return verse;
	}
	public void setVerse(Verse verse) {
		this.verse = verse;
	}

	@Override
    public String toString() {
	    return "Vocal [title=" + title + ", verse=" + verse + "]";
    }
	
}