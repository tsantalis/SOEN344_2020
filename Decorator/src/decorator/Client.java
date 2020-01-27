package decorator;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class Client {

	public static void main(String[] args) {
		Icon baseIcon = new ImageIcon(Client.class.getResource("java_image.gif"));

		Icon decoration = new ImageIcon(Client.class.getResource("decoration.gif"));

		Icon lowerLeftDecorIcon = new DecoratedIcon(baseIcon, decoration, DecoratedIcon.Location.LOWER_LEFT);
		// decorate the already decorated icon.
		Icon lowerRightDecorIcon = new DecoratedIcon(lowerLeftDecorIcon, decoration, DecoratedIcon.Location.LOWER_RIGHT);
		Icon upperLeftDecorIcon = new DecoratedIcon(lowerRightDecorIcon, decoration, DecoratedIcon.Location.UPPER_LEFT);
		Icon upperRightDecorIcon = new DecoratedIcon(upperLeftDecorIcon, decoration, DecoratedIcon.Location.UPPER_RIGHT);
		
		Icon[] icons = new Icon[] {baseIcon, lowerLeftDecorIcon, lowerRightDecorIcon, upperLeftDecorIcon, upperRightDecorIcon};
		
		JFrame frame = new JFrame();
		frame.setLayout(new GridLayout(1, icons.length));
		
		for(Icon icon : icons) {
			JLabel label = createLabelWithIcon(icon);
			frame.getContentPane().add(label);
		}

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private static JLabel createLabelWithIcon(Icon baseIcon) {
		Border border = BorderFactory.createLineBorder(Color.BLUE, 5);
		JLabel label = new JLabel();
		label.setIcon(baseIcon);
		label.setBorder(border);
		return label;
	}
}
