package observer.socket;

import java.io.IOException;

public class ChatConsoleClient {

	public static void main(String[] args) {
		String server = "127.0.0.1";
		int port = 2222;
		ChatAccess access = new ChatAccess();

		ChatConsole console = new ChatConsole(access);
		Thread thread = new Thread(console);
		thread.start();

		try {
			access.initSocket(server,port);
		} catch (IOException ex) {
			System.out.println("Cannot connect to " + server + ":" + port);
			ex.printStackTrace();
			System.exit(0);
		}
	}

}
