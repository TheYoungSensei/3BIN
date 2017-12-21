package edu.uwec.cs.wickmr.kaleidoscope.strategies;

import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;

public class SpinShapeMutationStrategy extends ShapeMutationStrategy {

	public SpinShapeMutationStrategy(int w) {
		super(w);
	}

	public void mutate(Shape s) {
		s.rotateAbout(origin, 10);
	}
}
