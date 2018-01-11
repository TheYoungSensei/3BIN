package edu.uwec.cs.wickmr.kaleidoscope.strategies;

import java.awt.Point;

import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;

public class GrowShapeMutationStrategy extends ShapeMutationStrategy {

	public GrowShapeMutationStrategy(int w) {
		super(w);
	}

	public void mutate(Shape s) {
		Point ctr;
		int d1, d2;
		ctr = s.getCenter();
		d1 = r.nextInt(0, Math.abs((origin.x - ctr.x) / 4));
		d2 = r.nextInt(0, Math.abs((origin.y - ctr.y) / 4));
		// Scale the shape
		s.translateTo(new Point((ctr.x - d1) % origin.x, (ctr.y - d2)
				% origin.y));
	}
}
