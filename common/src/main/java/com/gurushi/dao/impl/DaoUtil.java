package com.gurushi.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

public class DaoUtil {
    
    public static Object getSingleResultOrNull(Query query) {
        List<?> results = query.getResultList();
        if (results.isEmpty()) return null;
        else if (results.size() == 1) return results.get(0);
        throw new NonUniqueResultException();
    }
    
    public static Object getTopResult(Query query) {
        List<?> results = query.getResultList();
        if (results.isEmpty()) return null;
        return results.get(0);
    }

    public static List<?> getListOfResult(Query query) {
    	List<?> results = query.getResultList();
    	if (results.isEmpty()) return null;
    	return results;
    }
    
    public static Set<?> getSetOfResult(Query query) {
        List<?> results = query.getResultList();
        if (results.isEmpty()) return null;
        Set<Object> returnSet = new HashSet<Object>();
        returnSet.addAll(results);
        return returnSet;
    }
    
    public static Long getSource(Query query){
    	Object array[] = query.getResultList().toArray();
    	if(array != null && array.length > 0){
    		return Long.parseLong(array[0].toString());
    	}else{
    		return null;
    	}
    }
}
