package com.gurushi.loader;


import java.io.File;

public class MainContentLoading {
	public static void main(String[] args) {
		//File currentDir = new File("F:/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//File currentDir = new File("/Users/nileshvaghela/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//BhagavadGitaDotOrg bgo = new BhagavadGitaDotOrg(currentDir);
		//File currentDir = new File("F:/Dropbox/vedabase.net/bg"); // current directory
		File currentDir = new File("/Users/nileshvaghela/Dropbox/vedabase.net/bg");
		VedaBaseDotNet vbdn = new VedaBaseDotNet(currentDir);
		vbdn.loadData();
	}
}

