package stateChecking.violet.dragMode;


import com.horstmann.violet.framework.Edge;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * @see stateChecking.violet.dragMode.GraphPanel#DRAG_RUBBERBAND
 */
public class DragRubberband extends DragMode {
	public DragRubberband(GraphPanel graphPanel) {
		super(graphPanel);
	}

	public int getDragMode() {
		return GraphPanel.DRAG_RUBBERBAND;
	}

	public void mouseDragged(MouseEvent event) {
	}

	public void paintComponent(Graphics2D g2) {
		Color oldColor = g2.getColor();
		g2.setColor(GraphPanel.PURPLE);
		g2.draw(new Line2D.Double(graphPanel.getMouseDownPoint(), graphPanel.getLastMousePoint()));
		g2.setColor(oldColor);
	}

	public void mouseReleased(MouseEvent event) {
		Point2D mousePoint = new Point2D.Double(event.getX() / graphPanel.getZoom(),
				event.getY() / graphPanel.getZoom());
		Object tool = graphPanel.getToolBar().getSelectedTool();
		Edge prototype = (Edge) tool;
		Edge newEdge = (Edge) prototype.clone();
		if (mousePoint.distance(graphPanel.getMouseDownPoint()) > GraphPanel.CONNECT_THRESHOLD
				&& graphPanel.getGraph().connect(newEdge, graphPanel.getMouseDownPoint(), mousePoint)) {
			graphPanel.setModified(true);
			graphPanel.setSelectedItem(newEdge);
		}
	}
}