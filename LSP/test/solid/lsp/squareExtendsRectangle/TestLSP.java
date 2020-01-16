package solid.lsp.squareExtendsRectangle;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLSP {

	@Test
	public void area() {
		// try replacing new Rectangle() with new Square() in the line below
		Rectangle r = new Rectangle();
		r.setWidth(5);
		r.setHeight(10);
		long area = r.getArea();
		assertEquals(50, area);
	}

}
