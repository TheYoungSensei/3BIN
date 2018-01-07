package edu.uwec.cs.wickmr.kaleidoscope.shapes;

import java.awt.Color;
import java.awt.Point;

public class Triangle extends Polygon {
	protected final static int SIDES = 3;
	private int length;

	public Triangle(Triangle orig) {
		super(orig);
		length = orig.length;
	}

	public Triangle(Point center, int l, Color aColor) {
		super(center, SIDES, aColor);
		length = l;

		double c = length / (2.0 * Math.cos(degreesToRadians(30)));
		double a = Math.tan(degreesToRadians(30)) * 0.5 * length;

		Point[] theVertices = new Point[SIDES];
		theVertices[0] = new Point(center.x, (int) (center.y - c));
		theVertices[1] = new Point((int) (center.x - .5 * length),
				(int) (center.y + a));
		theVertices[2] = new Point((int) (center.x + 0.5 * length),
				(int) (center.y + a));
		setVertices(theVertices);
	}

	public Shape copy() {
		return (new Triangle(this));
	}
}
