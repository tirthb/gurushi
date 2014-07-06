package com.gurushi.dao;

import com.gurushi.domain.AbstractEntity;

public interface UtilDao {
    
    public <E extends AbstractEntity> E save(E e);
    
    public <E extends AbstractEntity> E find(Class<E> genericType, Integer id);

}
