package com.gurushi.dao;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.dao.ScriptureDao;
import com.gurushi.domain.Scripture;

public class ScriptureDaoTest extends AbstractTest {
    
    @Autowired
    private ScriptureDao dao;
    
    @Test
    @Transactional
    public void testFindByName() {
    	String name = "Bhagavad Gita";
    	Scripture s = dao.findByName(name);
    	Assert.assertEquals(name, s.getName());
    }
    
}
