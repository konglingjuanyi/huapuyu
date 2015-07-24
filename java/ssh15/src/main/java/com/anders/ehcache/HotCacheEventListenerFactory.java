package com.anders.ehcache;

import java.util.Properties;

import net.sf.ehcache.event.CacheEventListener;
import net.sf.ehcache.event.CacheEventListenerFactory;

public class HotCacheEventListenerFactory extends CacheEventListenerFactory {

	@Override
	public CacheEventListener createCacheEventListener(final Properties properties) {
		return HotCacheEventListener.INSTANCE;
	}
}