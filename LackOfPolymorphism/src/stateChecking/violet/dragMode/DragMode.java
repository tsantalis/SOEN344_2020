package stateChecking.violet.dragMode;


import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class DragMode {
	public abstract int getDragMode();

	public abstract void mouseDragged(MouseEvent event, GraphPanel graphPanel);

	public abstract void paintComponent(Graphics2D g2, GraphPanel graphPanel);

	public abstract void mouseReleased(MouseEvent event, GraphPanel graphPanel);
}