package com.gurushi.loader;

import com.gurushi.domain.Scripture;

public abstract class ScriptureSource {
	
	protected String rootDir;
	protected Scripture sc;
	
	public ScriptureSource(String rootDirectory, Scripture sc) {
		this.rootDir = rootDirectory;
		this.sc = sc;
	}
	
	//TODO: remove blank constructor
	public ScriptureSource() {}
	
	public abstract void loadData() ;
	
	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}
	
	public void setScripture(Scripture s) {
		this.sc = s;
	}
}
