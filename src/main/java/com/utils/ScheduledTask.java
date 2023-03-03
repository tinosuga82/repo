package com.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

	@Autowired
	CacheManager cacheManager;
	
	@Scheduled(fixedRate = 1800000)
	public void evictAllcachesAtIntervals() {
		cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
}
