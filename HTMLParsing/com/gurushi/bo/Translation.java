package com.gurushi.bo;

public class Translation {
	
	//back reference
	private Verse verse;
	
	private String text;
	private Author author;
	
	public Translation(Verse verse, Author author) {
		this.verse = verse;
		verse.setTranslation(this);
		this.author = author;
	}

	public Verse getVerse() {
		return verse;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((verse == null) ? 0 : verse.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Translation other = (Translation) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (verse == null) {
				return false;
		} else if (!verse.equals(other.verse))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Translation [verse=" + verse + "]";
	}
	
}
