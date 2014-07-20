package com.gurushi.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.dao.ChapterDao;
import com.gurushi.dao.ScriptureDao;
import com.gurushi.dao.VerseDao;
import com.gurushi.domain.Chapter;
import com.gurushi.domain.Scripture;
import com.gurushi.domain.Verse;

public class VerseDaoTest extends AbstractTest {
    
    @Autowired
    private VerseDao dao;
    
    @Autowired
    private ScriptureDao sdao;
    
    @Autowired
    private ChapterDao cdao;
    
    @Test
    @Transactional
    public void findByNumberAndChapter() {
    	
    	Scripture s = sdao.findByName("Bhagavad Gita");
		Chapter c = cdao.findByNumberAndScripture("1", s);
		
    	Verse verse = dao.findByNumberAndChapter(5, c);
    	
    	assertNotNull(verse);
    }
}
