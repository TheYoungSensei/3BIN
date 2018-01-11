package edu.uwec.cs.wickmr.kaleidoscope.shapes;

import java.util.ArrayList;
import java.util.List;

public class ShapeVector {
	private List<Shape> elements;

	public ShapeVector() {
		elements = new ArrayList<Shape>();
	}

	public void addElement(Shape s) {
		elements.add(s);
	}

	public Shape elementAt(int i) {
		return elements.get(i);
	}

	public ShapeIterator iterator() {
		return new ShapeIterator(this);
	}

	public int size() {
		return elements.size();
	}
}
