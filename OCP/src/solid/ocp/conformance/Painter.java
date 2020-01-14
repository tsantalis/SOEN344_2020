package solid.ocp.conformance;

import java.awt.Graphics;
import java.util.List;


public class Painter {
	private List<Drawable> drawables;
	
	public void paintComponent(Graphics g) {
		for(Drawable drawable : drawables) {
			drawable.draw(g);
		}
	}
}
