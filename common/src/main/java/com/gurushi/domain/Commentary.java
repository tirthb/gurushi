package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="commentary")
public class Commentary extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name="text")
	private String purport;
    
    @Column(name="source_url")
	private String sourceUrl;
	
	@ManyToOne
	@JoinColumn(name="verse_id")
	private Verse verse;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	private Author author;
	
	public Commentary() {}
	
	public Commentary(String purport, String sourceUrl, Verse v, Author a) {
		this.purport = purport;
		this.sourceUrl = sourceUrl;
		this.verse = v;
		this.author = a;
	}
	
	public String getPurport() {
		return purport;
	}
	public void setPurport(String purport) {
		this.purport = purport;
	}
	
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public Verse getVerse() {
		return verse;
	}
	public void setVerse(Verse verse) {
		this.verse = verse;
	}

	@Override
    public String toString() {
	    return "Commentary [author=" + author + ", verse=" + verse + "]";
    }
	
}