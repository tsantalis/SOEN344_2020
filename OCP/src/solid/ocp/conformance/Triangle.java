package solid.ocp.conformance;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Triangle implements Drawable {
	private Point point1;
	private Point point2;
	private Point point3;

	public Triangle(Point point1, Point point2, Point point3) {
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
	}

	public Point getPoint1() {
		return point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public Point getPoint3() {
		return point3;
	}
	
	public void draw(Graphics g) {
		Polygon polygon = new Polygon();
		polygon.addPoint(this.getPoint1().x, this.getPoint1().y);
		polygon.addPoint(this.getPoint2().x, this.getPoint2().y);
		polygon.addPoint(this.getPoint3().x, this.getPoint3().y);
		g.drawPolygon(polygon);
	}
}
