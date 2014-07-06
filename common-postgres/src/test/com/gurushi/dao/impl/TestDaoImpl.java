package com.gurushi.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TestDaoImpl {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation=Propagation.MANDATORY)
    public void cleanupTables() {
		
		//delete all tables
		jdbcTemplate.update("delete from verse_line");
		jdbcTemplate.update("delete from verse_number");
		jdbcTemplate.update("delete from translation");
		jdbcTemplate.update("delete from commentary");
		jdbcTemplate.update("delete from meaning");
		jdbcTemplate.update("delete from video");
		jdbcTemplate.update("delete from audio");
		jdbcTemplate.update("delete from vocal");
		jdbcTemplate.update("delete from verse");
		jdbcTemplate.update("delete from chapter");
		jdbcTemplate.update("delete from author");
		jdbcTemplate.update("delete from scripture");
	}

}
