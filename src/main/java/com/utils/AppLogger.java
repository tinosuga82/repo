package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppLogger {
	
	public static void info(String message, Class<?> target) {
		final Logger logger = LoggerFactory.getLogger(target);
		logger.info(message + " "+ target);

	}
	
	public static void error(String message, Class<?> target) {
		final Logger logger = LoggerFactory.getLogger(target);
		logger.error(message + " "+ target);

	}

}
