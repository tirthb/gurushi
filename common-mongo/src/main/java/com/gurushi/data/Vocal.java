package com.gurushi.data;


public class Vocal {
	
	private String title;
	private String url;
	
	public Vocal(String title, String url) {
		this.title = title;
		this.url = url;
	}
	
	public Vocal() {}
	
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
	
	@Override
	public String toString() {
		return String.format("audio:%s", url);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Vocal other = (Vocal) obj;
		if (url == null) {
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
