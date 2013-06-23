package com.gurushi.loader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LoaderApp {
	public static void main(String[] args) throws Exception {

		ApplicationContext appContext = new ClassPathXmlApplicationContext("META-INF/spring/application-context.xml");
		GitaLoader gitaLoader = (GitaLoader) appContext.getBean("gitaLoader");
		gitaLoader.createAndLoadScripture();
		
		((ClassPathXmlApplicationContext) appContext).close(); 
	}
}