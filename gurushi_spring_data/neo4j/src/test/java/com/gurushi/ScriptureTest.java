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

	@Autowired
	ChapterService chservice;
	
	@Autowired
	ScriptureService service;
	
	private Scripture gita;

	@Test
	public void createScripture() {

		gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		Chapter chPreface = new Chapter(null, "Preface", gita);
		gita.setFirstChapter(chPreface);
		
		chPreface.setDescription("Originally I wrote Bhagavad-gītā As It Is in the form in which it is presented now.");
		
		chPreface = chservice.save(chPreface);
	}
	
	@Test
	public void getScriptureByName() {
		
		createScripture();
		
		Scripture s = service.findByName(gita.getName());
		
		Assert.assertEquals(gita.getId(), s.getId());
		Assert.assertEquals(gita.getFirstChapter(), s.getFirstChapter());
	}
}
