package com.gurushi.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.gurushi.dao.VerseDao;
import com.gurushi.domain.Chapter;
import com.gurushi.domain.Verse;

@Repository
public class VerseDaoImpl implements VerseDao {
    
    @PersistenceContext
    private EntityManager em;
    
	@Override
    public Verse findByNumberAndChapter(Integer number, Chapter c) {
		
		Query query = em.createNamedQuery("findByNumberAndChapter");
        query.setParameter("number", number);
        query.setParameter("chapter", c.getNumber());
		
	    return (Verse) DaoUtil.getSingleResultOrNull(query);
    }

}
