/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gurushi;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gurushi.data.Audio;
import com.gurushi.data.Author;
import com.gurushi.data.Chapter;
import com.gurushi.data.Commentary;
import com.gurushi.data.Scripture;
import com.gurushi.data.Translation;
import com.gurushi.data.Verse;
import com.gurushi.service.ChapterService;
import com.gurushi.service.ScriptureService;
import com.gurushi.service.TemplateService;
import com.gurushi.service.VerseService;

/**
 * Integration tests for {@link ChapterService}.
 * 
 */
public class VerseTest extends AbstractIntegrationTest {
	
	private final Logger logger = LoggerFactory.getLogger(getClass()); 

	@Autowired
	VerseService service;
	
	@Autowired
	ScriptureService scService;
	
	@Autowired
	TemplateService ts;
	
	Verse v1, v2;
	
	@Test
	public void createVerses() {
		
		super.createChapters();
		
		v1 = new Verse("1", c1);
		v1.setText("dhṛtarāṣṭra uvāca"
				+ LS + "dharma-kṣetre kuru-kṣetre"
				+ LS + "samavetā yuyutsavaḥ"
				+ LS + "māmakāḥ pāṇḍavāś caiva"
				+ LS + "kim akurvata sañjaya");
	
		Author a = new Author("Srila Prabhupada", "http://en.wikipedia.org/wiki/A._C._Bhaktivedanta_Swami_Prabhupada");
		
		Translation t = new Translation(a);
		v1.setTranslation(t);
		t.setText("Dhṛtarāṣṭra said: O Sañjaya, after my sons and the sons of Pāṇḍu assembled in the place of pilgrimage at Kurukṣetra, desiring to fight, what did they do?");
		t.setSourceUrl("http://vedabase.net/bg/1/1/en");
		
		Commentary com = new Commentary("Bhagavad-gītā is the widely read theistic science summarized in the Gītā-māhātmya (Glorification of the Gītā). There it says that one should read Bhagavad-gītā very scrutinizingly with the help of a person who is a devotee of Śrī Kṛṣṇa and try to understand it without personally motivated interpretations. The example of clear understanding is there in the Bhagavad-gītā itself, in the way the teaching is understood by Arjuna, who heard the Gītā directly from the Lord. If someone is fortunate enough to understand Bhagavad-gītā in that line of disciplic succession, without motivated interpretation, then he surpasses all studies of Vedic wisdom, and all scriptures of the world. One will find in the Bhagavad-gītā all that is contained in other scriptures, but the reader will also find things which are not to be found elsewhere. That is the specific standard of the Gītā. It is the perfect theistic science because it is directly spoken by the Supreme Personality of Godhead, Lord Śrī Kṛṣṇa." 
				, "http://vedabase.net/bg/1/1/en"
				, a);
		ts.save(com);
		
		//the commentary is being added to the verse through the constructor
		v1.addCommentary(com);
		
		Author a2 = new Author("Sri Vishwanath Chakravarti Thakur", "http://en.wikipedia.org/wiki/Visvanatha_Chakravarti");
		
		com = new Commentary("How did Arjuna become bewildered and fall into ignorance? The speaker of the Mahabharata, Vaisampayana, started explaining the topic to Janmejaya in the Bhisma Parva, with the following words."
				, "http://www.bhagavad-gita.us/category/bhagavad-gita-chapter-1/"
				, a2);
		ts.save(com);
		
		v1.addCommentary(com);
		
		Author a3 = new Author("Sridhara Swami", null, "Rudra Vaisnava");
		
		com = new Commentary("How did Arjuna become bewildered and fall into ignorance? The speaker of the Mahabharata, Vaisampayana, started explaining the topic to Janmejaya in the Bhisma Parva, with the following words."
				, "http://www.bhagavad-gita.org/Gita/verse-01-01.html"
				, a3);
		ts.save(com);
		
		v1.addCommentary(com);
		
		v1.addMeaning("dhṛtarāṣṭraḥ uvāca", "King Dhṛtarāṣṭra said");
		v1.addMeaning("dharma-kṣetre", "in the place of pilgrimage");
		
		v1.addAudio(new Audio("SP BG 01-01 London 1973-07-07 The Material World Means--etc", 
				"http://audio.iskcondesiretree.info/01_-_His_Divine_Grace_A_C_Bhaktivedanta_Swami_Srila_Prabhupada/Lectures/English/Bhagavad_Gita/Chapter-01/SP_BG_01-01_London_1973-07-07_The_Material_World_Means--etc.mp3"));
		
		//could not find a good video
		
		//saving verse
		v1 = service.save(v1);
		
		//saving versse ids in the commentary
		for (Commentary comm : v1.getCommentaries()) {
			comm.setVerse(v1.getId());
			ts.save(comm);
		}
		
		//Adding another verse
		
		v2 = new Verse("2", c1);
		v2.setPreviousVerse(v1.getId());
		
		v2.setText("sañjaya uvāca"
				+ LS + "dṛṣṭvā tu pāṇḍavānīkam"
				+ LS + "vyūḍhaḿ duryodhanas tadā"
				+ LS + "ācāryam upasańgamya"
				+ LS + "rājā vacanam abravīt");
	
		Translation t2 = new Translation(a);
		v2.setTranslation(t2);
		t2.setText("Sañjaya said: O King, after looking over the army arranged in military formation by the sons of Pāṇḍu, King Duryodhana went to his teacher and spoke the following words.");
		t2.setSourceUrl("http://vedabase.net/bg/1/2/en");
		
		//Srila Prabhupada
		com = new Commentary("Dhṛtarāṣṭra was blind from birth. Unfortunately, he was also bereft of spiritual vision. He knew very well that his sons were equally blind in the matter of religion, and he was sure that they could never reach an understanding with the Pāṇḍavas, who were all pious since birth." 
				, "http://vedabase.net/bg/1/2/en"
				, a);
		ts.save(com);
		
		v2.addCommentary(com);
		
		//Sri Vishwanath Chakravarti Thakur
		com = new Commentary("“O teacher, see this great army of the sons of Pandu, arranged for battle by your intelligent disciple, the son of Drupada.”"
				, "http://www.bhagavad-gita.us/category/bhagavad-gita-chapter-1/"
				, a2);
		ts.save(com);
		
		v2.addCommentary(com);
		
		com = new Commentary("Having seen the armies of the Pandavas arrayed in military formation ready for battle, King Duryodhana approaches his preceptor Drona and spoke the following words."
				, "http://www.bhagavad-gita.org/Gita/verse-01-02.html"
				, a3);
		
		ts.save(com);
		
		//Sridhara Swami
		v2.addCommentary(com);
		
		v2.addMeaning("sañjayaḥ uvāca", "Sañjaya said");
		v2.addMeaning("dṛṣṭvā", "after seeing");
		
		v2.addAudio(new Audio("SP BG 01-02 London 1973-07-09 Death Is God", 
				"http://audio.iskcondesiretree.info/01_-_His_Divine_Grace_A_C_Bhaktivedanta_Swami_Srila_Prabhupada/Lectures/English/Bhagavad_Gita/Chapter-01/SP_BG_01-02_London_1973-07-09_Death_Is_God.mp3"));
		
		//could not find a good video
		
		v2 = service.save(v2);
		
		//Adding verses to chapter
		c1.addVerse(v1.getId());
		c1.addVerse(v2.getId());
		c1 = cs.save(c1);
		
		//saving versse ids in the commentary
		for (Commentary comm : v2.getCommentaries()) {
			comm.setVerse(v2.getId());
			ts.save(comm);
		}
		
		v1.setNextVerse(v2.getId());
		v1 = service.save(v1);
		
	}
	
	@Test
	public void getAllVersesForAChapter() {
		
		createVerses();
	
		Scripture s = scService.findByName("Bhagavad Gita");
		
		Chapter c = cs.findByNumberAndScripture("1", s);
		
		List<Verse> verses = service.getAllVersesForAChapter(c);
		
		Assert.assertEquals(2, verses.size());
		
		for (Verse verse : verses) {
			Assert.assertNotNull(verse.getId());
		}
	}
	
	@Test
	public void getPreviousVerse() {
		
		createVerses();
		
		Scripture s = scService.findByName("Bhagavad Gita");
		
		Chapter c = cs.findByNumberAndScripture("1", s);
		Verse current = service.findByNumberAndChapter("2", c);
		Verse previous = service.previousVerse(current);
		
		Verse v1 = service.findByNumberAndChapter("1", c);
		Assert.assertEquals(v1, previous);
		
		current = previous;
		
		previous = service.previousVerse(current);
		
		Assert.assertNull(previous);
	}
	
	@Test
	public void getCommentaryOfAuthor() {
		
		createVerses();
		
		Author a = new Author("Sridhara Swami", null, "Rudra Vaisnava");
		
		Assert.assertEquals("How did Arjuna become bewildered and fall into ignorance? The speaker of the Mahabharata, Vaisampayana, started explaining the topic to Janmejaya in the Bhisma Parva, with the following words.", 
				v1.getCommentaryOfAuthor(a).getText());
	}
	
	@Test
	public void getVerseByNumberAndScripture() {
		
		createVerses();
		
		gita = scService.findByName("Bhagavad Gita");
		
		Author author = new Author("Srila Prabhupada", 
				"http://en.wikipedia.org/wiki/A._C._Bhaktivedanta_Swami_Prabhupada");
		
		Chapter c = cs.findByNumberAndScripture("1", gita);
		
		Verse v = service.findByNumberAndChapter("2", c);
		
		//v.getNextVerse().setChapter(c);
		
		logger.info(v.toString());
		logger.info("Next verse:" + v.getNextVerse());
		logger.info(v.getText());
		logger.info(v.getMeanings().toString());
		logger.info("Translation:" + v.getTranslation());
		logger.info("Translation source:" + v.getTranslation().getSourceUrl());
		
		Commentary com = v.getCommentaryOfAuthor(author);
		
		logger.info("Commentary: " + com.getText());
		logger.info("Commentary source: " + com.getSourceUrl());
		
	}
}
