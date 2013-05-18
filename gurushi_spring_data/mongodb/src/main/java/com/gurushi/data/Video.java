package com.gurushi.data;

public class Video {
	
	private Integer sortOrder;
	private String title;
	private String url;
	
	public Video(String title, String url, int sortOrder) {
		this.title = title;
		this.url = url;
		this.sortOrder = sortOrder;
	}
	
	public Video() {}
	
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
	
	@Override
	public String toString() {
		return String.format("vdo:%s", title);
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
		Video other = (Video) obj;
		if (url == null) {
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}
