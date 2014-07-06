package com.gurushi.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.gurushi.dao.UtilDao;
import com.gurushi.domain.AbstractEntity;

@Repository
public class UtilDaoImpl implements UtilDao {
    
    @PersistenceContext
    private EntityManager em;

	@Override
    public <E extends AbstractEntity> E save(E e) {
	    return em.merge(e);
    }

	@Override
    public <E extends AbstractEntity> E find(Class<E> genericType, Integer id) {
	    return em.find(genericType, id);
    }

    

}
