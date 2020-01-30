package singleton;

import java.util.Map;

public class Singleton {
	private volatile static Singleton instance;

	private Singleton() {
		
	}

	public static Singleton getInstance() {
		if(instance == null) {
			synchronized(Singleton.class) {
				if(instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	private Map<String, String> cache;
	
	public String addToCache(String key, String value) {
		return cache.put(key, value);
	}
	
	public String getValue(String key) {
		return cache.get(key);
	}
}
