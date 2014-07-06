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
	private String text;
    
    @Column(name="source_url")
	private String sourceUrl;
	
	@Column(name="sort_order")
	private Integer sortOrder;
	
	@ManyToOne
	@JoinColumn(name="verse_id")
	private Verse verse;
	
	@ManyToOne
	@JoinColumn(name="author_id")
	private Author author;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
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