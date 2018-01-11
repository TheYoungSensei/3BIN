package edu.uwec.cs.wickmr.kaleidoscope.views;

import edu.uwec.cs.wickmr.kaleidoscope.shapes.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import edu.uwec.cs.wickmr.kaleidoscope.scopes.*;

@SuppressWarnings("serial")
public class NegativeKaleidoscopeView extends KaleidoscopeView {

	public NegativeKaleidoscopeView(Kaleidoscope kal, int size) {
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

			Color newColor = new Color((quad1.getColor().getRed() / 128) * 128,
					(quad1.getColor().getGreen() / 128) * 128, (quad1
							.getColor().getBlue() / 128) * 128);

			quad1.setColor(newColor);
			quad2.setColor(newColor);
			quad3.setColor(newColor);
			quad4.setColor(newColor);

			quad1.draw(g);
			quad2.draw(g);
			quad3.draw(g);
			quad4.draw(g);
		}
	}
}
