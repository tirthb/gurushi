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
	ScriptureService scService;
	
	@Test
	public void createChapters() {
		super.createChapters();
	}
	
	@Test
	public void getAllChaptersForScripture() {
		//createChapters();
		
		Scripture s = scService.findByName("Bhagavad Gita");
		List<Chapter> chapters = chService.getAllChaptersForAScripture(s);
		
		Assert.assertEquals(3, chapters.size());
		
		for (Chapter chapter : chapters) {
			Assert.assertNotNull(chapter.getId());
		}
	}
	
	@Test
	public void getPreviousChapter() {
		
		//createChapters();
		
		Chapter current = chService.findByNumberAndScripture(chIntro.getTitle(), chIntro.getScripture());
		
		Chapter previous = chService.previousChapter(current);
		
		Assert.assertEquals(chPreface, previous);
		
		current = previous;
		
		previous = chService.previousChapter(current);
		
		Assert.assertNull(previous);
	}
}
