package observer.socket;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/** Chat client UI */
@SuppressWarnings("serial")
public class ChatFrame extends JFrame implements Observer {

	private JTextArea textArea;
	private JTextField inputTextField;
	private JButton sendButton;
	private ChatAccess chatAccess;

	public ChatFrame(ChatAccess chatAccess) {
		this.chatAccess = chatAccess;
		// ChatFrame "this" reference is self-registered to the Observable chatAccess object
		chatAccess.addObserver(this);
		buildGUI();
	}

	/** Builds the user interface */
	private void buildGUI() {
		textArea = new JTextArea(20, 50);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		add(new JScrollPane(textArea), BorderLayout.CENTER);

		Box box = Box.createHorizontalBox();
		add(box, BorderLayout.SOUTH);
		inputTextField = new JTextField();
		sendButton = new JButton("Send");
		box.add(inputTextField);
		box.add(sendButton);

		// Action for the inputTextField and the goButton
		ActionListener sendListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = inputTextField.getText();
				// whenever some input is entered to inputTextField, chatAccess.send() is called
				if (str != null && str.trim().length() > 0) {
					chatAccess.send(str);
				}
				inputTextField.selectAll();
				inputTextField.requestFocus();
				inputTextField.setText("");
			}
		};
		inputTextField.addActionListener(sendListener);
		sendButton.addActionListener(sendListener);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				chatAccess.close();
			}
		});
	}

	/** Updates the UI depending on the Object argument */
	public void update(Observable o, Object arg) {
		final Object finalArg = arg;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textArea.append(finalArg.toString());
				textArea.append("\n");
			}
		});
	}
}