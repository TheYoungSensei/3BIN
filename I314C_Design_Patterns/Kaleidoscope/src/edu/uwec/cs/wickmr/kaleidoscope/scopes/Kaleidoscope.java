package edu.uwec.cs.wickmr.kaleidoscope.scopes;

import java.util.ArrayList;
import java.util.List;

import edu.uwec.cs.wickmr.kaleidoscope.factories.ShapeFactory;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.ShapeIterator;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.ShapeVector;
import edu.uwec.cs.wickmr.kaleidoscope.strategies.ShapeMutationStrategy;
import edu.uwec.cs.wickmr.kaleidoscope.views.KaleidoscopeView;

public class Kaleidoscope {
	private ShapeVector theShapes;
	private List<KaleidoscopeView> theViews;
	private ShapeFactory theFactory;
	private ShapeMutationStrategy theStrategy;

	public Kaleidoscope(ShapeFactory factory, ShapeMutationStrategy strategy) {
		theShapes = new ShapeVector();
		theViews = new ArrayList<KaleidoscopeView>();
		theFactory = factory;
		theStrategy = strategy;
	}

	public ShapeIterator getShapes() {
		return (theShapes.iterator());
	}

	public void register(KaleidoscopeView v) {
		theViews.add(v);
	}

	public void turn() {

		// Mutate the existing shapes.
		for (int i = 0; i < theShapes.size(); i++) {
			theStrategy.mutate(theShapes.elementAt(i));
		}

		// Add new shape from factory.
		theShapes.addElement(theFactory.createShape());

		// Update the views
		for (int i = 0; i < theViews.size(); i++) {
			theViews.get(i).update();
		}

	}
}
