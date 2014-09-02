package com.gurushi.dao;

import com.gurushi.domain.Scripture;

public interface ScriptureDao {

	public Scripture findByName(String name);
	
}
