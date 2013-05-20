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

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.service.ChapterService;

/**
 * Integration tests for {@link ChapterService}.
 * 
 */
public class ChapterTest extends AbstractIntegrationTest {

	@Test
	public void createChapters() {
		super.createChapters();
	}
	
	@Test
	public void getAllChaptersForScripture() {
		createChapters();
		
		Scripture s = ss.findByName("Bhagavad Gita");
		List<Chapter> chapters = cs.getAllChaptersForAScripture(s);
		
		Assert.assertEquals(3, chapters.size());
		
		for (Chapter chapter : chapters) {
			Assert.assertNotNull(chapter.getId());
		}
	}
	
	@Test
	public void getPreviousChapter() {
		
		createChapters();
		
		Scripture s = ss.findByName("Bhagavad Gita");
		
		Chapter current = cs.findByNumberAndScripture("2", s);
		Chapter previous = cs.previousChapter(current);
		
		Chapter chOne = cs.findByNumberAndScripture("1", s);
		
		Assert.assertEquals(chOne, previous);
		
		current = previous;
		
		previous = cs.previousChapter(current);
		
		Assert.assertNull(previous);
	}
}
