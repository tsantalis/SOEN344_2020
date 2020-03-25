package stateChecking.violet.dragMode;


import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public abstract class DragMode {
	protected GraphPanel graphPanel;
	public DragMode(GraphPanel graphPanel) {
		this.graphPanel = graphPanel;
	}

	public abstract int getDragMode();

	public abstract void mouseDragged(MouseEvent event);

	public abstract void paintComponent(Graphics2D g2);

	public abstract void mouseReleased(MouseEvent event);
}