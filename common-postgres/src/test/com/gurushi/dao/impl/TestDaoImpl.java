package com.gurushi.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void cleanUpTables() {
		
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
