package com.gurushi.service;

import com.gurushi.data.Scripture;

public interface ScriptureService {

	public Scripture findByName(String name);
	
	public Scripture save(Scripture s);
	
}