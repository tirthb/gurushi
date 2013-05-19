package com.gurushi.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Verse extends AbstractDocument {
	
	@DBRef
	private Chapter chapter;
	
	@Indexed
	private String number;
	
	//TODO: full text index in mongo db?
	private String text;
	
	private ObjectId nextVerse;
	
	private ObjectId previousVerse;
	
	private Translation translation;
	
	private List<Meaning> meanings = new ArrayList<Meaning>();
	
	@DBRef
	private List<Commentary> commentaries = new ArrayList<Commentary>();
	
	private List<Video> videos = new ArrayList<Video>();
	
	private List<Audio> audios = new ArrayList<Audio>();
	
	public Verse(String number, Chapter chapter) {
		this.number = number;
		this.chapter = chapter;
	}
	
	public Verse() {}

	public Chapter getChapter() {
		return chapter;
	}
	
	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
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

	public List<Meaning> getMeanings() {
		
		if (meanings == null) {
			return null;
		}
		
		List<Meaning> listMeanings = new ArrayList<Meaning>(meanings); 
		Collections.sort(
			listMeanings, 
			new Comparator<Meaning>() {
				public int compare(Meaning o1, Meaning o2) {
					if (o1.getSortOrder() == null) {
						if (o2.getSortOrder() == null) {
							return 0;
						} else {
							return 1;
						}
					}
					return o1.getSortOrder().compareTo(o2.getSortOrder());
				}
			}
		);
		
		return Collections.unmodifiableList(listMeanings);
	}

	public void addMeaning(String word, String meaning, int sortOrder) {
		this.meanings.add(new Meaning(word, meaning, sortOrder));
	}
	
	public List<Commentary> getCommentaries() {
		return Collections.unmodifiableList(commentaries);
	}
	
	public Commentary getCommentaryOfAuthor(Author a) {
		
		if(a == null) {
			throw new IllegalArgumentException("author cannot be null.");
		}
		
		for (Commentary commentary : commentaries) {
			if (a.equals(commentary.getAuthor())) {
				return commentary;
			}
		}
		
		return null;
	}

	public void addCommentary(Commentary commentary) {
		commentaries.add(commentary);
	}
	
	public List<Video> getVideos() {
		if (videos == null) {
			return null;
		}
		
		List<Video> listVideo = new ArrayList<Video>(videos); 
		Collections.sort(
				listVideo, 
			new Comparator<Video>() {
				public int compare(Video o1, Video o2) {
					if (o1.getSortOrder() == null) {
						if (o2.getSortOrder() == null) {
							return 0;
						} else {
							return 1;
						}
					}
					return o1.getSortOrder().compareTo(o2.getSortOrder());
				}
			}
		);
		
		return Collections.unmodifiableList(listVideo);
	}
	
	public void addVideo(Video video) {
		videos.add(video);
	}
	
	public List<Audio> getAudios() {
		if (audios == null) {
			return null;
		}
		
		List<Audio> listAudio = new ArrayList<Audio>(audios); 
		Collections.sort(
			listAudio, 
			new Comparator<Audio>() {
				public int compare(Audio o1, Audio o2) {
					if (o1.getSortOrder() == null) {
						if (o2.getSortOrder() == null) {
							return 0;
						} else {
							return 1;
						}
					}
					return o1.getSortOrder().compareTo(o2.getSortOrder());
				}
			}
		);
		
		return Collections.unmodifiableList(listAudio);
	}
	
	public void addAudio(Audio audio) {
		audios.add(audio);
	}
	
	public ObjectId getPreviousVerse() {
		return previousVerse;
	}

	public void setPreviousVerse(ObjectId previousVerse) {
		this.nextVerse = previousVerse;
	}
	
	public ObjectId getNextVerse() {
		return nextVerse;
	}

	public void setNextVerse(ObjectId nextVerse) {
		this.nextVerse = nextVerse;
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