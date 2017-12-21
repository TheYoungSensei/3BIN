package edu.uwec.cs.wickmr.kaleidoscope.shapes;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {
	private int radius;

	/* A C++-like copy constructor. */
	public Circle(Circle orig) {
		super(orig);
		radius = orig.radius;
	}

	public Circle(Point p, int r, Color c) {
		super(p, c);
		radius = r;
	}

	public Shape copy() {
		return (new Circle(this));
	}

	public void drawBorder(Graphics g) {
		Point upperLeft = translate(getCenter(), -radius, -radius);
		g.fillOval(upperLeft.x, upperLeft.y, radius * 2, radius * 2);
	}

	protected void flipBorderHorizontalAbout(Point p) {
	}

	protected void flipBorderVerticalAbout(Point p) {
	}

	/*
	 * Empty bodies since the border is implicitly defined using the radius
	 */
	protected void rotateBorderAbout(Point p, int degrees) {
	}

	protected void translateBorder(int dx, int dy) {
	}
}
