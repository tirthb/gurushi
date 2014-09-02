package com.gurushi.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gurushi.dao.UtilDao;
import com.gurushi.domain.AbstractEntity;

@Repository
public class UtilDaoImpl implements UtilDao {
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
    public <E extends AbstractEntity> E save(E e) {
	    return em.merge(e);
    }

	@Override
    public <E extends AbstractEntity> E find(Class<E> genericType, Integer id) {
	    return em.find(genericType, id);
    }

	@Override
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
