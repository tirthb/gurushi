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

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gurushi.data.Chapter;
import com.gurushi.data.Commentary;
import com.gurushi.data.Scripture;
import com.gurushi.data.Verse;
import com.gurushi.service.ChapterService;
import com.gurushi.service.ScriptureService;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { ApplicationConfig.class })
@ContextConfiguration(locations = "classpath:META-INF/spring/application-context.xml")
public abstract class AbstractIntegrationTest {

	@Autowired
    protected MongoOperations template;
	
	@Autowired
    protected ChapterService cs;
	
	@Autowired
    protected ScriptureService ss;
	
	public static final String LS = System.getProperty("line.separator");
	
	Scripture gita;
	
	Chapter chPreface, chIntro, c1;

    @Before
    public void cleanUp() {
    	template.dropCollection(Commentary.class);
    	template.dropCollection(Verse.class);
    	template.dropCollection(Chapter.class);
    	template.dropCollection(Scripture.class);
    }
    
    public void createChapters() {
    	gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		chPreface = new Chapter(null, "Preface", gita);
		chPreface.setDescription("Originally I wrote Bhagavad-gītā As It Is in the form in which it is presented now.");
		
		chPreface = cs.save(chPreface);
		gita.addChapter(chPreface.getId());
		
		chIntro = new Chapter(null, "Introduction", gita);
		chIntro.setDescription("Bhagavad-gītā is also known as Gītopaniṣad. It is the essence of Vedic knowledge and one of the most important Upaniṣads in Vedic literature.");
		chIntro.setPreviousChapter(chPreface.getId());
		chIntro = cs.save(chIntro);
		
		gita.addChapter(chIntro.getId());
		
		chPreface.setNextChapter(chIntro.getId());
		chPreface = cs.save(chPreface);
		
		c1 = new Chapter("1", "Observing the Armies on the Battlefield of Kurukṣetra", gita);
		c1.setPreviousChapter(chIntro.getId());
		c1 = cs.save(c1);
		
		gita.addChapter(c1.getId());
		
		chIntro.setNextChapter(c1.getId());
		chIntro = cs.save(chIntro);
		
    }
}
