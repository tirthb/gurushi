package com.gurushi.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gurushi.data.Scripture;
import com.gurushi.service.ScriptureService;
import com.gurushi.service.TemplateService;

@Component
public class GitaLoader {
	
	@Autowired
	TemplateService ts;
	
	@Autowired
	ScriptureService service;
	
	//TODO:pass constructor arg
	@Autowired
	VedaBaseDotNetGita vbSource;
	
	private Scripture gita;

	public void createAndLoadScripture() {
		
		ts.cleanUpDb();

		gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		gita = service.save(gita);
		
		//File currentDir = new File("F:/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//File currentDir = new File("/Users/nileshvaghela/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//BhagavadGitaDotOrg bgo = new BhagavadGitaDotOrg(currentDir);
		//File currentDir = new File("F:/Dropbox/vedabase.net/bg"); // current directory
		
		//rootDir defined in application-context.xml
		vbSource.setScripture(gita);
		
		vbSource.loadData();
	}

}
