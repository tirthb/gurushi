package com.gurushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gurushi.data.Scripture;
import com.gurushi.data.repository.ScriptureRepository;

@Service
public class ScriptureServiceImpl implements ScriptureService {
	
	@Autowired
	private ScriptureRepository rep;

	@Override
	public Scripture findByName(String name) {
		
		return rep.findByName(name);
	}

}
