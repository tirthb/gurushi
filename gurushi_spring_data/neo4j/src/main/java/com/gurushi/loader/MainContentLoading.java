package com.gurushi.loader;


import com.gurushi.data.Scripture;

public class MainContentLoading {
	public static void main(String[] args) {
		//File currentDir = new File("F:/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//File currentDir = new File("/Users/nileshvaghela/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//BhagavadGitaDotOrg bgo = new BhagavadGitaDotOrg(currentDir);
		//File currentDir = new File("F:/Dropbox/vedabase.net/bg"); // current directory
		String currentDir = "/Users/nileshvaghela/Dropbox/vedabase.net/bg";
		
		Scripture gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		VedaBaseDotNet vbdn = new VedaBaseDotNet(currentDir, gita);
		vbdn.loadData();
	}
}

