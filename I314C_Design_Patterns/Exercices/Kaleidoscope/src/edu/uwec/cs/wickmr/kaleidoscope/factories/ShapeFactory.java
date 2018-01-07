package edu.uwec.cs.wickmr.kaleidoscope.factories;

import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;

public abstract class ShapeFactory {
	protected final int MIN_SHAPE_SIZE;
	protected final int MAX_SHAPE_SIZE;
	protected final int WIDTH;

	public ShapeFactory(int w) {
		WIDTH = w;
		MIN_SHAPE_SIZE = ((int) (0.10 * WIDTH));
		MAX_SHAPE_SIZE = ((int) (0.20 * WIDTH));
	}

	public abstract Shape createShape();
}
