package edu.uwec.cs.wickmr.kaleidoscope.views;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import edu.uwec.cs.wickmr.kaleidoscope.scopes.Kaleidoscope;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;

@SuppressWarnings("serial")
public class RotateKaleidoscopeView extends KaleidoscopeView {

	public RotateKaleidoscopeView(Kaleidoscope kal, int size) {
		super(kal, size);
	}

	protected void drawShapes(Graphics g) {
		Dimension d = getSize();
		Point origin = new Point(d.width / 2, d.width / 2);
		Shape[] quad = new Shape[8];
		// Draw the shapes
		while (shapes.hasNext()) {
			quad[0] = shapes.next().copy();
			quad[0].draw(g);
			for (int j = 1; j < quad.length; j++) {
				quad[j] = quad[0].copy();
				quad[j].rotateAbout(origin, -(360 / quad.length) * j);
				quad[j].draw(g);
			}
		}
	}
}
