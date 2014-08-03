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
	
	public Chapter(String number, String title, Scripture s) {
		setNumber(number);
		this.title = title;
		this.scripture = s;
	}
	
	public Chapter(String number, Scripture s) {
		setNumber(number);
		this.scripture = s;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
		this.sortOrder = Integer.parseInt(number);
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
