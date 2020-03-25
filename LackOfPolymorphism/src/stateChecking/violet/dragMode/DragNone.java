package stateChecking.violet.dragMode;


import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 * @see stateChecking.violet.dragMode.GraphPanel#DRAG_NONE
 */
public class DragNone extends DragMode {
	public DragNone(GraphPanel graphPanel) {
		super(graphPanel);
	}

	public int getDragMode() {
		return GraphPanel.DRAG_NONE;
	}

	public void mouseDragged(MouseEvent event) {
	}

	public void paintComponent(Graphics2D g2) {
	}

	public void mouseReleased(MouseEvent event) {
	}
}