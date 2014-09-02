package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="audio")
public class Audio extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name="title")
	private String title;
    
    @Column(name="url")
	private String url;
	
	@Column(name="sort_order")
	private Integer sortOrder;
	
	@ManyToOne
	@JoinColumn(name="verse_id")
	private Verse verse;
	
	public Audio() {}
	
	public Audio(String title, String url, Integer sortOrder, Verse v) {
		this.title = title;
		this.url = url;
		this.sortOrder = sortOrder;
		this.verse = v;
	}
	
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
	    return "Audio [title=" + title + ", verse=" + verse + "]";
    }
	
}