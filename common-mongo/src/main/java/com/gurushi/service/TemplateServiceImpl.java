package com.gurushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.data.Chapter;
import com.gurushi.data.Commentary;
import com.gurushi.data.Scripture;
import com.gurushi.data.Verse;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	MongoOperations template;
	
	@Override
	@Transactional
	public void cleanUpDb() {
    	
    	template.dropCollection(Commentary.class);
    	template.dropCollection(Verse.class);
    	template.dropCollection(Chapter.class);
    	template.dropCollection(Scripture.class);
    }

	@Override
    public void save(Object o) {
		template.save(o);
    }

}
