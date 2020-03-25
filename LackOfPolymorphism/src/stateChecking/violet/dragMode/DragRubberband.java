package stateChecking.violet.dragMode;


import java.awt.event.MouseEvent;

/**
 * @see stateChecking.violet.dragMode.GraphPanel#DRAG_RUBBERBAND
 */
public class DragRubberband extends DragMode {
	public int getDragMode() {
		return GraphPanel.DRAG_RUBBERBAND;
	}

	public void mouseDragged(MouseEvent event, GraphPanel graphPanel) {
	}
}