package com.gurushi.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.gurushi.dao.ScriptureDao;
import com.gurushi.dao.UtilDao;
import com.gurushi.domain.Scripture;

@Component
public class GitaLoader {
	
	@Autowired
	UtilDao utilDao;
	
	@Autowired
	ScriptureDao sdao;
	
	//TODO:pass constructor arg
	@Autowired
	VedaBaseDotNetGita vbSource;
	
	@Autowired
	BhagavadGitaDotOrg bgdo;
	
	private Scripture gita;
	
	@Transactional
	public void createAndLoadScripture() {
		
		utilDao.cleanUpTables();

		gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		gita = utilDao.save(gita);
		
		//File currentDir = new File("F:/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//File currentDir = new File("/Users/nileshvaghela/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//BhagavadGitaDotOrg bgo = new BhagavadGitaDotOrg(currentDir);
		//File currentDir = new File("F:/Dropbox/vedabase.net/bg"); // current directory
		
		//rootDir defined in application-context.xml
		vbSource.setScripture(gita);		
		vbSource.loadData();
		
		gita = sdao.findByName("Bhagavad Gita");

		bgdo.setScripture(gita);		
		bgdo.loadData();
		
		
	}
	
	

}