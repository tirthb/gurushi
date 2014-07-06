package com.gurushi.util;

import java.util.Properties;

import org.apache.log4j.Logger;

public enum GurushiProperty {
	
	ANY
	;
	
	private static final String PATH = "/gurushi.properties";

	private static final Logger logger = Logger.getLogger(GurushiProperty.class);

	private Properties properties;

	private String value;

	private void init() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(GurushiProperty.class.getResourceAsStream(PATH));
			}
			catch (Exception e) {
				logger.error("Unable to load " + PATH + " file from classpath.", e);
			}
		}
		value = (String) properties.get(this.toString());
	}

	public String getValue() {
		if (value == null) {
			init();
		}
		return value;
	}
}
