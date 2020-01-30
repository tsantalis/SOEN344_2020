package singleton;

import java.util.Map;

public enum Singleton {
	INSTANCE;
	
	private Map<String, String> cache;
	
	public String addToCache(String key, String value) {
		return cache.put(key, value);
	}
	
	public String getValue(String key) {
		return cache.get(key);
	}
}
