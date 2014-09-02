package com.gurushi.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.gurushi.dao.LookupDao;
import com.gurushi.domain.LookupValue;
import com.gurushi.util.LookupTypeEnum;

@Repository
public class LookupDaoImpl implements LookupDao {
    
    private static Logger logger = LoggerFactory.getLogger(LookupDaoImpl.class);
    
    @PersistenceContext
    private EntityManager em;
    
    private Map<Long, LookupValue> cache = new HashMap<Long, LookupValue>();

    @Override
    public LookupValue getLookupValue(LookupTypeEnum type, Long id) {
        
        LookupValue lookupValue = cache.get(id);
        
        if (lookupValue == null) {
            logger.debug("Lookup id: " + id + " not in cache");
            Query query = em.createNamedQuery("findByTypeAndId");
            query.setParameter("typeId", Long.valueOf(type.getId()));
            query.setParameter("id", id);

            lookupValue = (LookupValue) DaoUtil.getSingleResultOrNull(query);
            if (lookupValue != null) {
                cache.put(lookupValue.getId(), lookupValue);
            }
        } else {
            logger.debug("Lookup id: " + id + " in cache");
        }
        
        return lookupValue;
    }

}
