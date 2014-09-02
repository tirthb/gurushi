package com.gurushi.dao;

import com.gurushi.domain.LookupValue;
import com.gurushi.util.LookupTypeEnum;

public interface LookupDao {
    
    public LookupValue getLookupValue(LookupTypeEnum type, Long id);

}
