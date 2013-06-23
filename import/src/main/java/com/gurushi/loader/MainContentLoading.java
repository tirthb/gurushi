package com.gurushi.loader;


import com.gurushi.data.Scripture;

public class MainContentLoading {
	public static void main(String[] args) {
		String currentDir = "F:/Dropbox/www.bhagavad-gita.org/Gita"; // current directory
		//File currentDir = new File("/Users/nileshvaghela/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		
		//File currentDir = new File("F:/Dropbox/vedabase.net/bg"); // current directory
		//String currentDir = "/Users/nileshvaghela/Dropbox/vedabase.net/bg";
		
		Scripture gita = new Scripture("Bhagavad Gita");
		gita.setDescription("Lord Krishna clears the doubts of Arjuna in the middle of a battlefield.");
		
		//VedaBaseDotNetGita vbdn = new VedaBaseDotNetGita(currentDir, gita);
		//vbdn.loadData();
		BhagavadGitaDotOrg bgo = new BhagavadGitaDotOrg(currentDir, gita);
		bgo.loadData();
	}
}

