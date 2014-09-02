package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="chapter")
public class Chapter extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
	@Column(name="number")
	private String number;
	
	@Column(name="title")
	private String title;

	@Column(name="description")
	private String description;
	
	@Column(name="sort_order")
	private Integer sortOrder;
	
	@ManyToOne
	@JoinColumn(name="scripture_id")
	private Scripture scripture;
	
	public Chapter() {}
	
	public Chapter(Integer sortOrder, String number, String title, Scripture s) {
		
		if (number == null && sortOrder == null) {
			throw new IllegalArgumentException("Chapter number or sort order should be not null.");
		}
		
		setNumber(sortOrder, number);
		this.title = title;
		this.scripture = s;
	}
	
	public Chapter(Integer sortOrder, String number, Scripture s) {
		this(sortOrder, number, null, s);
	}
	
	public Chapter(String number, String title, Scripture s) {
		this(null, number, title, s);
	}
	
	public Chapter(String number, Scripture s) {
		this(null, number, null, s);
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(Integer sortOrder, String number) {
		this.number = number;
		
		if (sortOrder == null) {
			this.sortOrder = Integer.parseInt(number);
		} else {
			this.sortOrder = sortOrder;
		}
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
	public String toString() {
		return "Chapter [id=" + id + ", title=" + title + "]";
	}
}
