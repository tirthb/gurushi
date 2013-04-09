package com.gurushi;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

public class FileBrowser {

	public void displayDirectoryContents(File dir) {
		try {
			File[] files = dir.listFiles();
			
			for (File file : files) {
				if (file.isDirectory()) {
					displayDirectoryContents(file);
				} else {
				   String filename = file.getName();				   //System.out.println("file:" + filename);
				   int index = FilenameUtils.indexOfExtension(filename);
				    if (index != -1) {		
				        if (filename.substring(index).matches(".html")) {
				        	System.out.println("file:" + file.getCanonicalPath());
				        }
				    }
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
