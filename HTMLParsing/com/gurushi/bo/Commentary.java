package com.gurushi.bo;

public class Commentary {
	
	//back reference
	private Verse verse;
	
	private String text;
	private Author author;
	
	public Commentary(String text, Author author, Verse verse) {
		this.text = text;
		this.author = author;
		this.verse = verse;
		verse.addCommentary(this);
	}

	public String getText() {
		return text;
	}

	public Author getAuthor() {
		return author;
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
		Commentary other = (Commentary) obj;
		if (author == null) {
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
		return "Commentary [verse=" + verse + ", author=" + author + "]";
	}
	
}
