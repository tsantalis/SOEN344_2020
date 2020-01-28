package decorator;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class DecoratedIcon implements Icon {

	private Icon originalIcon;
	private Icon decorationIcon;
	//the difference in the width of the original and decoration icon
	private int xDiff;
	//the difference in the height of the original and decoration icon
	private int yDiff;
	//the location of the decoration icon, one of the Location enum constants
	private Location location;

	// Java 1.5 enumeration
	public enum Location { UPPER_LEFT, UPPER_RIGHT, LOWER_LEFT, LOWER_RIGHT };

	public DecoratedIcon(Icon original, Icon decoration, Location loc) {
		this.location = loc;
		this.originalIcon = original;
		this.decorationIcon = decoration;
		// add some logic to check that the decoration icon has smaller dimensions than the original icon
		if(decorationIcon.getIconWidth() >= originalIcon.getIconWidth() ||
				decorationIcon.getIconHeight() >= originalIcon.getIconHeight()) {
			throw new IllegalArgumentException("the decoration icon is larger than the base icon");
		}
		
		// compute xDiff as the difference in the width of the original and decoration icon
		this.xDiff = originalIcon.getIconWidth() - decorationIcon.getIconWidth();
		// compute yDiff as the difference in the height of the original and decoration icon
		this.yDiff = originalIcon.getIconHeight() - decorationIcon.getIconHeight();
	}

	public int getIconHeight() {
		return originalIcon.getIconHeight();
	}

	public int getIconWidth() {
		return originalIcon.getIconWidth();
	}

	public void paintIcon(Component owner, Graphics g, int x, int y) {
		// paint original icon first
		originalIcon.paintIcon(owner, g, x, y);
		// compute the x, y coordinates of the decoration icon based on its location
		int decorationX = x;
		int decorationY = y;
		// augment x
		if(location == Location.UPPER_RIGHT || location == Location.LOWER_RIGHT) {
			// compute decorationX based on xDiff
			decorationX += xDiff;
		}
		// augment y
		if(location == Location.LOWER_LEFT || location == Location.LOWER_RIGHT) {
			// compute decorationY based on yDiff
			decorationY += yDiff;
		}
		
		// paint decoration icon last, based on computed decorationX, decorationY values
		decorationIcon.paintIcon(owner, g, decorationX, decorationY);
	}
}


