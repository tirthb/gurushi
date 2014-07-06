package com.gurushi.util;

import java.util.HashMap;
import java.util.Map;

public enum LookupTypeEnum {
    
    SOURCE(1),
   
    ;
    
    private static Map<Integer, LookupTypeEnum> map = new HashMap<Integer, LookupTypeEnum>();
    private int id;
    
    LookupTypeEnum(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return id;
    }
    
    static {
    	for (LookupTypeEnum instance : LookupTypeEnum.values()) {
    		map.put(instance.id, instance);
    	}
    }
    
    public static LookupTypeEnum valueOf(Integer arg0) {
    	return map.get(arg0);
    }
}
