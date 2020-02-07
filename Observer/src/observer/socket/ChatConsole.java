package observer.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

public class ChatConsole implements Observer, Runnable {
	private ChatAccess chatAccess;
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public ChatConsole(ChatAccess chatAccess) {
		this.chatAccess = chatAccess;
		chatAccess.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System.out.println(arg.toString());
			}
		});
	}

	@Override
	public void run() {
		try {
			while (true) {
				String input = br.readLine();
				chatAccess.send(input);
				if ("/quit".equals(input)) {
					System.exit(0);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
