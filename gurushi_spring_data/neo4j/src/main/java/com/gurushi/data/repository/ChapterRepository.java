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
package com.gurushi.data.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.CypherDslRepository;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.data.Verse;

public interface ChapterRepository extends GraphRepository<Chapter>, CypherDslRepository<Chapter> {
	
	@Query("START chapter=node({0}) " +
	           " MATCH previousChapter-[:nextChapter]->chapter" +
	           " RETURN previousChapter")
	Chapter previousChapter(Chapter c);
	
	@Query("START chapter=node({0}) " +
	           " MATCH chapter-[:nextChapter]->nextChapter" +
	           " RETURN nextChapter")
	Chapter nextChapter(Chapter c);
	
	@Query("START chapter=node({0}) " +
	           " MATCH chapter-[:firstVerse]->firstVerse" +
	           " RETURN firstVerse")
	Verse firstVerse(Chapter c);
	
	Chapter findByTitleAndScripture(String title, Scripture s);
	
	Chapter findByNumberAndScripture(String number, Scripture s);
}
