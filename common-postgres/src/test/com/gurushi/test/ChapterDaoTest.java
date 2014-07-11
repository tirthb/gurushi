package com.gurushi.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.dao.ChapterDao;
import com.gurushi.dao.ScriptureDao;
import com.gurushi.domain.Chapter;
import com.gurushi.domain.Scripture;

public class ChapterDaoTest extends AbstractTest {
    
    @Autowired
    private ChapterDao dao;
    
    @Autowired
    private ScriptureDao sdao;
    
    @Test
    @Transactional
	public void getAllChaptersForScripture() {
		
		Scripture s = sdao.findByName("Bhagavad Gita");
		List<Chapter> chapters = dao.findAllChaptersForAScripture(s);
		
		assertEquals(3, chapters.size());
		
		for (Chapter chapter : chapters) {
			assertNotNull(chapter.getId());
		}
	}
    
}
