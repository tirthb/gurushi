package com.gurushi.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.gurushi.dao.ChapterDao;
import com.gurushi.domain.Chapter;
import com.gurushi.domain.Scripture;

@Repository
public class ChapterDaoImpl implements ChapterDao {
    
    @PersistenceContext
    private EntityManager em;

	@SuppressWarnings("unchecked")
    @Override
    public List<Chapter> findAllChaptersForAScripture(Scripture s) {
		
		Query query = em.createNamedQuery("findAllChaptersForAScripture");
        query.setParameter("sid", s.getId());
		
	    return query.getResultList();
    }

}
