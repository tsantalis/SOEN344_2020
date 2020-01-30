package singleton;

import java.util.Map;

public class Singleton {

	public static Singleton getInstance() {
		return null;
	}
	
	private Map<String, String> cache;
	
	public String addToCache(String key, String value) {
		return cache.put(key, value);
	}
	
	public String getValue(String key) {
		return cache.get(key);
	}
}
