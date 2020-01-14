package solid.ocp.violation;

public class Circle {

	private int centerX;
	private int centerY;
	private int radius;
	
	public Circle(int centerX, int centerY, int radius) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public int getRadius() {
		return radius;
	}

}
