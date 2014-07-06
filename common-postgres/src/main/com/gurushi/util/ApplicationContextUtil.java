package com.gurushi.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext ctx = null;

	private ApplicationContextUtil(){

	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		if(ctx == null){
			ctx = context;	
		}
	}

	public static ApplicationContext getContext() {
		return ctx;
	}
}

