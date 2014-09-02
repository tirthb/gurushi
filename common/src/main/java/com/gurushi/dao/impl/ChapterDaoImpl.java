package com.gurushi.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    private Map<String/*number*/, Chapter> cache = new HashMap<String, Chapter>();

	@SuppressWarnings("unchecked")
    @Override
    public List<Chapter> findAllChaptersForAScripture(Scripture s) {
		
		Query query = em.createNamedQuery("findAllChaptersForAScripture");
        query.setParameter("sid", s.getId());
        
        List<Chapter> list = query.getResultList();
        
        cache.clear();
        for (Chapter c : list) {
	        cache.put(c.getNumber(), c);
        }
		
	    return list;
    }
	
	public Chapter findByNumberAndScripture(String number, Scripture s) {
		if (cache.isEmpty()) findAllChaptersForAScripture(s);
		return cache.get(number);
	}

}
