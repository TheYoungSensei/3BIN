package edu.uwec.cs.wickmr.kaleidoscope.views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import edu.uwec.cs.wickmr.kaleidoscope.scopes.Kaleidoscope;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;

@SuppressWarnings("serial")
public class FlipKaleidoscopeView extends KaleidoscopeView {

	public FlipKaleidoscopeView(Kaleidoscope kal, int size) {
		super(kal, size);
	}

	protected void drawShapes(Graphics g) {
		Dimension d = getSize();
		Shape quad1, quad2, quad3, quad4;
		Point origin = new Point(d.width / 2, d.height / 2);
		// Draw the shapes
		while (shapes.hasNext()) {
			quad1 = shapes.next().copy();
			quad2 = quad1.copy();
			quad2.flipVerticalAbout(origin);
			quad3 = quad1.copy();
			quad3.flipDiagonalAbout(origin);
			quad4 = quad1.copy();
			quad4.flipHorizontalAbout(origin);
			quad1.draw(g);
			quad2.draw(g);
			quad3.draw(g);
			quad4.draw(g);
		}
	}
}
