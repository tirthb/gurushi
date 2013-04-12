package com.gurushi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gurushi.data.Chapter;
import com.gurushi.data.repository.ChapterRepository;

@Service
public class ChapterServiceImpl implements ChapterService {
	
	@Autowired
	private ChapterRepository rep;

	@Override
	public Chapter save(Chapter c) {
		return rep.save(c);
	}

}
