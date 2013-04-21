package com.gurushi.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gurushi.data.Scripture;
import com.gurushi.service.ScriptureService;

@Component
public class GitaLoader {
	
	@Autowired
	ScriptureService service;
	
	//TODO:pass constructor arg
	@Autowired
	VedaBaseDotNet vbSource;
	
	private Scripture gita;

	public void createAndLoadScripture() {

		gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		gita = service.save(gita);
		
		//File currentDir = new File("F:/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//File currentDir = new File("/Users/nileshvaghela/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//BhagavadGitaDotOrg bgo = new BhagavadGitaDotOrg(currentDir);
		//File currentDir = new File("F:/Dropbox/vedabase.net/bg"); // current directory
		
		//TODO: make rootDir as property
		vbSource.setRootDir("/Users/tirthomishtu/gurushi/vedabase.net-bg");
		vbSource.setScripture(gita);
		
		vbSource.loadData();
	}

}
