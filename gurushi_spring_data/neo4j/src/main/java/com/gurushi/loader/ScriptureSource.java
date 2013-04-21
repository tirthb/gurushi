package com.gurushi.loader;

import java.io.File;

import com.gurushi.data.Scripture;

public abstract class ScriptureSource {
	
	protected File rootDir;
	protected Scripture sc;
	
	public ScriptureSource(String rootDirectory, Scripture sc) {
		this.rootDir = new File(rootDirectory);
		this.sc = sc;
	}
	
	//TODO: remove blank constructor
	public ScriptureSource() {}
	
	public abstract void loadData() ;
	
	public void setRootDir(String rootDir) {
		this.rootDir = new File(rootDir);
	}
	
	public void setScripture(Scripture s) {
		this.sc = s;
	}
}
