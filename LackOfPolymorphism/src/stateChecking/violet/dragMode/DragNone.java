package stateChecking.violet.dragMode;


import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * @see stateChecking.violet.dragMode.GraphPanel#DRAG_NONE
 */
public class DragNone extends DragMode {
	public int getDragMode() {
		return GraphPanel.DRAG_NONE;
	}

	public void mouseDragged(MouseEvent event, GraphPanel graphPanel) {
	}

	public void paintComponent(Graphics2D g2, GraphPanel graphPanel) {
	}

	public void mouseReleased(MouseEvent event, GraphPanel graphPanel) {
	}
}