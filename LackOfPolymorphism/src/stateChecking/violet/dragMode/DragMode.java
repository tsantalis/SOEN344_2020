package stateChecking.violet.dragMode;


import java.awt.event.MouseEvent;

public abstract class DragMode {
	public abstract int getDragMode();

	public abstract void mouseDragged(MouseEvent event, GraphPanel graphPanel);
}