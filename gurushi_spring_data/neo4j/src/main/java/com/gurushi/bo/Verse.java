package com.gurushi.bo;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.fieldaccess.DynamicProperties;
import org.springframework.data.neo4j.fieldaccess.PrefixedDynamicProperties;
import org.springframework.data.neo4j.support.index.IndexType;

@NodeEntity
public class Verse extends AbstractEntity {
	
	@RelatedTo
	private Chapter chapter;
	
	@Indexed
	private String number;
	
	@Indexed(indexType = IndexType.FULLTEXT,indexName = "verse_search")
	private String text;
	
	@Fetch
	@RelatedTo
	private Verse nextVerse;
	
	@Fetch
	@RelatedTo
	private Verse previousVerse;
	
	//restricting to one translation per verse for less confusion
	@Fetch
	@RelatedTo
	private Translation translation;
	
	private DynamicProperties meanings = new PrefixedDynamicProperties("meaning");
	
	@RelatedTo(type="COMMENTARY")
	private Set<Commentary> commentaries = new HashSet<Commentary>();
	
	@RelatedTo(type="VIDEO")
	private Set<Video> videos = new HashSet<Video>();
	
	@RelatedTo(type="AUDIO")
	private Set<Audio> audios = new HashSet<Audio>();
	
	public Verse(String number, Chapter chapter) {
		this.number = number;
		this.chapter = chapter;
	}
	
	public Verse() {}

	public Chapter getChapter() {
		return chapter;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Translation getTranslation() {
		return translation;
	}

	public void setTranslation(Translation translation) {
		this.translation = translation;
	}

	public DynamicProperties getMeanings() {
		return meanings;
	}

	public void setMeaning(String word, String meaning) {
		this.meanings.setProperty(word, meaning);
	}
	
	public Set<Commentary> getCommentaries() {
		return Collections.unmodifiableSet(commentaries);
	}
	
	public Commentary getCommentaryOfAuthor(Author a) {
		
		if(a == null) {
			throw new IllegalArgumentException("author cannot be null.");
		}
		
		for (Commentary commentary : commentaries) {
			if (commentary.getAuthor().equals(a)) {
				return commentary;
			}
		}
		
		return null;
	}

	public void addCommentary(Commentary commentary) {
		commentaries.add(commentary);
	}
	
	public Set<Video> getVideos() {
		return Collections.unmodifiableSet(videos);
	}
	
	public void addVideo(Video video) {
		videos.add(video);
	}
	
	public Set<Audio> getAudios() {
		return Collections.unmodifiableSet(audios);
	}
	
	public void addAudio(Audio audio) {
		audios.add(audio);
	}
	
	public Verse getNextVerse() {
		return nextVerse;
	}

	public void setNextVerse(Verse nextVerse) {
		this.nextVerse = nextVerse;
	}

	public Verse getPreviousVerse() {
		return previousVerse;
	}

	public void setPreviousVerse(Verse previousVerse) {
		this.previousVerse = previousVerse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chapter == null) ? 0 : chapter.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Verse other = (Verse) obj;
		if (chapter == null) {
				return false;
		} else if (!chapter.equals(other.chapter))
			return false;
		if (number == null) {
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("Verse %s of %s", number, chapter);
	}
	
}