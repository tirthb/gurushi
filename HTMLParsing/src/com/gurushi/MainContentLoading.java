package com.gurushi;


import java.io.File;
import com.gurushi.BhagavadGitaDotOrg;

public class MainContentLoading {
	public static void main(String[] args) {
		File currentDir = new File("F:/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		//File currentDir = new File("/Users/nileshvaghela/Dropbox/www.bhagavad-gita.org/Gita"); // current directory
		BhagavadGitaDotOrg bgo = new BhagavadGitaDotOrg(currentDir);
		bgo.loadData();
	}
}

