package com.gurushi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="verse_line")
public class VerseLine extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    
    @Column(name="line")
	private String line;
    
    @Column(name="sort_order")
    private Integer sortOrder;
    
	@ManyToOne
	@JoinColumn(name="verse_id")
	private Verse verse;
	
	public VerseLine() {}
	
	public VerseLine(Verse v, String line, Integer sortOrder) {
		this.verse = v;
		this.line = line;
		this.sortOrder = sortOrder;
	}
	
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	
	public Verse getVerse() {
		return verse;
	}
	public void setVerse(Verse verse) {
		this.verse = verse;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	@Override
    public String toString() {
	    return "VerseLine [line=" + line + ", verse=" + verse + "]";
    }
	
}