package edu.uwec.cs.wickmr.kaleidoscope.shapes;

import java.awt.Color;
import java.awt.Point;

public class Square extends Rectangle {

	public Square(Square orig) {
		super(orig);
	}

	public Square(Point center, int h, Color aColor) {
		super(center, h, h, aColor);
	}

	public Shape copy() {
		return (new Square(this));
	}
}
