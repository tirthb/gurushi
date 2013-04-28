package com.gurushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.data.Chapter;
import com.gurushi.data.Scripture;
import com.gurushi.data.repository.ScriptureRepository;

@Service
public class ScriptureServiceImpl implements ScriptureService {
	
	@Autowired
	private ScriptureRepository rep;

	@Override
	@Transactional
	public Scripture save(Scripture s) {
		return rep.save(s);
	}
	
	@Override
	public Scripture findByName(String name) {
		return lazyLoad(rep.findByName(name));
	}

	@Override
	public Chapter firstChapter(Scripture s) {
		return rep.firstChapter(s);
	}
	
	private Scripture lazyLoad(Scripture s) {
		
		if (s != null && s.getId() != null) {
			s.setFirstChapter(firstChapter(s));
		}
		return s;
	}

}
