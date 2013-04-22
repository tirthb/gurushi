package com.gurushi.loader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) throws Exception {

		@SuppressWarnings("resource")
		ApplicationContext appContext = new ClassPathXmlApplicationContext("META-INF/spring/application-context.xml");
		
		GitaLoader gitaLoader = (GitaLoader) appContext.getBean("gitaLoader");
		gitaLoader.createAndLoadScripture();
	}
}