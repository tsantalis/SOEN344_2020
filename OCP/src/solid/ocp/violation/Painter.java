package solid.ocp.violation;

import java.awt.Graphics;
import java.awt.Polygon;
import java.util.List;

public class Painter {
	private List<Rectangle> rectangles;
	private List<Circle> circles;
	private List<Triangle> triangles;
	
	public void paintComponent(Graphics g) {
		for(Rectangle rect : rectangles) {
			g.drawRect(rect.getTopLeftX(),
					rect.getTopLeftY(),
					rect.getWidth(),
					rect.getHeight());
		}
		for(Circle c : circles) {
			g.drawArc(c.getCenterX()-c.getRadius(),
					c.getCenterY()-c.getRadius(),
					c.getRadius()*2,
					c.getRadius()*2,
					0,
					360);
		}
		for(Triangle triangle : triangles) {
			Polygon polygon = new Polygon();
			polygon.addPoint(triangle.getPoint1().x, triangle.getPoint1().y);
			polygon.addPoint(triangle.getPoint2().x, triangle.getPoint2().y);
			polygon.addPoint(triangle.getPoint3().x, triangle.getPoint3().y);
			g.drawPolygon(polygon);
		}
	}
}
