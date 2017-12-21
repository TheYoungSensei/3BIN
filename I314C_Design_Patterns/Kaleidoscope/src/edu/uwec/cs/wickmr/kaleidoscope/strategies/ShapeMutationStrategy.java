package edu.uwec.cs.wickmr.kaleidoscope.strategies;

import java.awt.Point;

import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;
import edu.uwec.cs.wickmr.kaleidoscope.util.Randomizer;

public abstract class ShapeMutationStrategy {
	protected static Randomizer r = new Randomizer();

	protected int width;
	protected Point origin;

	public ShapeMutationStrategy(int w) {
		width = w;
		origin = new Point(width / 2, width / 2);
	}

	public abstract void mutate(Shape s);
}
