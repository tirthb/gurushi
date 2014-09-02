package com.gurushi.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.dao.ScriptureDao;
import com.gurushi.domain.Scripture;

@Repository
public class ScriptureDaoImpl implements ScriptureDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Scripture findByName(String name) {
		
		Query query = em.createNamedQuery("findByName");
        query.setParameter("name", name);
		
	    return (Scripture) DaoUtil.getSingleResultOrNull(query);
    }

}
