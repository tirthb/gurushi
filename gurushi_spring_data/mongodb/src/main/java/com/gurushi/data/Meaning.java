package com.gurushi.data;


public class Meaning {
	
	private Integer sortOrder;
	private String word;
	private String meaning;
	
	public Meaning() {
	}
	
	public Meaning(String word, String meaning, int sortOrder) {
		this.word = word;
		this.meaning = meaning;
		this.sortOrder = sortOrder;
	}
	
	public String getWord() {
		return word;
	}
	
	public String getMeaning() {
		return meaning;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}

	@Override
	public String toString() {
		return String.format("%s:%s", word, meaning);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
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
		Meaning other = (Meaning) obj;
		if (word == null) {
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
}
