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

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.service.ChapterService;
import com.gurushi.service.ScriptureService;

/**
 * Integration tests for {@link ChapterService}.
 * 
 */
public class ScriptureTest extends AbstractIntegrationTest {
	
	final Logger logger = LoggerFactory.getLogger(ScriptureTest.class);

	@Autowired
	ScriptureService service;
	
	@Autowired
	ChapterService chService;
	
	private Scripture gita;

	@Test
	public void createScripture() {

		gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		Chapter chPreface = new Chapter(null, "Preface", gita);
		chPreface.setDescription("Originally I wrote Bhagavad-gītā As It Is in the form in which it is presented now.");
		
		chPreface = chService.save(chPreface);
		
		gita.addChapter(chPreface.getId());
	}
	
	@Test
	public void getScriptureByName() {
		
		//createScripture();
		
		Scripture s = service.findByName("Bhagavad Gita");
		
		Assert.assertNotNull(s.getId());
		
		Chapter c = chService.findByNumberAndScripture("1", s);
		Assert.assertEquals(c.getId(), s.getChapters().get(0));
	}
	
	@Test
	public void cleanUpDB() {
		//do nothing
	}
}
