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
import org.springframework.beans.factory.annotation.Autowired;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.service.ChapterService;
import com.gurushi.service.ScriptureService;

/**
 * Integration tests for {@link ChapterService}.
 * 
 */
public class ChapterTest extends AbstractIntegrationTest {

	@Autowired
	ChapterService service;
	
	@Autowired
	ScriptureService scService;
	
	private Scripture gita;
	
	private Chapter chPreface, chIntro;

	@Test
	public void createChapters() {

		gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		chPreface = new Chapter(null, "Preface", gita);
		gita.setFirstChapter(chPreface);
		
		chPreface.setDescription("Originally I wrote Bhagavad-gītā As It Is in the form in which it is presented now.");
		
		chPreface = service.save(chPreface);
		
		chIntro = new Chapter(null, "Introduction", gita);
		chIntro.setDescription("Bhagavad-gītā is also known as Gītopaniṣad. It is the essence of Vedic knowledge and one of the most important Upaniṣads in Vedic literature.");
		chIntro = service.save(chIntro);
		
		chPreface.setNextChapter(chIntro);
		chPreface = service.save(chPreface);
		
		Chapter c1 = new Chapter("1", "Observing the Armies on the Battlefield of Kurukṣetra", gita);
		c1 = service.save(c1);
		
		chIntro.setNextChapter(c1);
		chIntro = service.save(chIntro);
	}
	
	@Test
	public void getAllChaptersForScripture() {
		createChapters();
		
		Scripture s = scService.findByName(gita.getName());
		List<Chapter> chapters = service.getAllChaptersForAScripture(s);
		
		Assert.assertEquals(3, chapters.size());
		
		for (Chapter chapter : chapters) {
			Assert.assertNotNull(chapter.getId());
		}
	}
	
	@Test
	public void getPreviousChapter() {
		
		createChapters();
		
		Chapter current = service.findByTitleAndScripture(chIntro.getTitle(), chIntro.getScripture());
		
		Chapter previous = service.previousChapter(current);
		
		Assert.assertEquals(chPreface, previous);
		
		current = previous;
		
		previous = service.previousChapter(current);
		
		Assert.assertNull(previous);
	}
}
