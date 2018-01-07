package edu.uwec.cs.wickmr.kaleidoscope.strategies;

import java.util.ArrayList;
import java.util.List;

import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;

public class CompositeShapeMutationStrategy extends ShapeMutationStrategy {
	List<ShapeMutationStrategy> strategies;

	public CompositeShapeMutationStrategy(int w) {
		super(w);
		strategies = new ArrayList<ShapeMutationStrategy>();
	}

	public void add(ShapeMutationStrategy s) {
		strategies.add(s);
	}

	public void mutate(Shape s) {
		for (int i = 0; i < strategies.size(); i++) {
			strategies.get(i).mutate(s);
		}
	}
}
