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
	
	@Test
	public void getScriptureByName() {
		
		createChapters();
		
		Scripture s = service.findByName("Bhagavad Gita");
		
		Assert.assertNotNull(s.getId());
		
		Chapter c = chService.findByNumberAndScripture("1", s);
		Assert.assertEquals(c.getId(), s.getChapters().get(2));
	}
	
}
