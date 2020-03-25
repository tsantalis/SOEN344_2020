package stateChecking.violet.dragMode;


import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import com.horstmann.violet.framework.Node;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;

/**
 * @see stateChecking.violet.dragMode.GraphPanel#DRAG_MOVE
 */
public class DragMove extends DragMode {
	public DragMove(GraphPanel graphPanel) {
		super(graphPanel);
	}

	public int getDragMode() {
		return GraphPanel.DRAG_MOVE;
	}

	public void mouseDragged(MouseEvent event) {
		if (graphPanel.getLastSelected() instanceof Node) {
			Point2D mousePoint = new Point2D.Double(event.getX() / graphPanel.getZoom(),
					event.getY() / graphPanel.getZoom());
			Node lastNode = (Node) graphPanel.getLastSelected();
			Rectangle2D bounds = lastNode.getBounds();
			double dx = mousePoint.getX() - graphPanel.getLastMousePoint().getX();
			double dy = mousePoint.getY() - graphPanel.getLastMousePoint().getY();
			Iterator iter = graphPanel.getSelectedItems().iterator();
			while (iter.hasNext()) {
				Object selected = iter.next();
				if (selected instanceof Node) {
					Node n = (Node) selected;
					bounds.add(n.getBounds());
				}
			}
			dx = Math.max(dx, -bounds.getX());
			dy = Math.max(dy, -bounds.getY());
			iter = graphPanel.getSelectedItems().iterator();
			while (iter.hasNext()) {
				Object selected = iter.next();
				if (selected instanceof Node) {
					Node n = (Node) selected;
					if (!graphPanel.getSelectedItems().contains(n.getParent())) {
						n.translate(dx, dy);
					}
				}
			}
			graphPanel.setLastMousePoint(mousePoint);
		}
	}

	public void paintComponent(Graphics2D g2) {
	}

	public void mouseReleased(MouseEvent event) {
		graphPanel.getGraph().layout();
		graphPanel.setModified(true);
	}
}