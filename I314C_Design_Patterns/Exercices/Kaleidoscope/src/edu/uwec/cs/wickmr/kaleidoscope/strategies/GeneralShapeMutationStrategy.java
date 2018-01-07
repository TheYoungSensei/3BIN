package edu.uwec.cs.wickmr.kaleidoscope.strategies;

import java.awt.Point;

import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;

public class GeneralShapeMutationStrategy extends ShapeMutationStrategy {

	public GeneralShapeMutationStrategy(int w) {
		super(w);
	}

	public void mutate(Shape s) {
		s.translateTo(new Point(r.nextInt(0, width), r.nextInt(0, width)));
	}
}
