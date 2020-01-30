package singleton;

import java.time.LocalDateTime;

public class Client {

	public static void main(String[] args) {
		Singleton uniqueInstance = Singleton.INSTANCE;
		String IP = "127.0.0.1";
		if(uniqueInstance.getValue(IP) != null) {
			uniqueInstance.addToCache(IP, LocalDateTime.now().toString());
		}
	}
}
