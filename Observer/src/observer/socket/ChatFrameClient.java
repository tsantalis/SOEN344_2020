package observer.socket;

import javax.swing.*;


// Class to manage Client chat Box.
public class ChatFrameClient {

	public static void main(String[] args) {
		String server = "127.0.0.1";
		int port = 2222;
		ChatAccess access = new ChatAccess(server, port);

		JFrame frame = new ChatFrame(access);
		frame.setTitle("MyChatApp - connected to " + server + ":" + port);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}