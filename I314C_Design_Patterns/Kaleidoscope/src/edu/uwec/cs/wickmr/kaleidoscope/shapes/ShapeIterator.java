package edu.uwec.cs.wickmr.kaleidoscope.shapes;

public class ShapeIterator {
	private ShapeVector shapes;
	private int nextIndex;

	public ShapeIterator() { // Used by KaleidoscopeView
		shapes = new ShapeVector();
		nextIndex = 0;
	}

	public ShapeIterator(ShapeVector v) {
		shapes = v;
		nextIndex = 0;
	}

	public boolean hasNext() {
		return (nextIndex < shapes.size());
	}

	public Shape next() {
		Shape result = shapes.elementAt(nextIndex);
		nextIndex++;
		return (result);
	}
}
