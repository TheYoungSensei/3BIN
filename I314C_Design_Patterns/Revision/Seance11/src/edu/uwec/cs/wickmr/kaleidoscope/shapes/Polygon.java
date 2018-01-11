package edu.uwec.cs.wickmr.kaleidoscope.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Polygon extends Shape {
	private int nbrOfSides;
	private Point[] theVertices;

	public Polygon(Polygon orig) {
		super(orig);
		nbrOfSides = orig.nbrOfSides;
		theVertices = new Point[orig.nbrOfSides];
		for (int i = 0; i < nbrOfSides; i++) {
			theVertices[i] = new Point(orig.theVertices[i]);
		}
	}

	public Polygon(Point center, int sides, Color aColor) {
		super(center, aColor);
		nbrOfSides = sides;
		theVertices = new Point[nbrOfSides];
	}

	public Polygon(Point center, Color aColor, Point[] vertices) {
		super(center, aColor);
		setVertices(vertices);
	}
	
	protected void setVertices(Point[] vertices) {
		nbrOfSides = vertices.length;
		theVertices = vertices;
	}

	public void drawBorder(Graphics g) {
		int[] xPoints = new int[nbrOfSides];
		int[] yPoints = new int[nbrOfSides];
		for (int i = 0; i < nbrOfSides; i++) {
			xPoints[i] = theVertices[i].x;
			yPoints[i] = theVertices[i].y;
		}
		g.fillPolygon(xPoints, yPoints, nbrOfSides);
	}

	protected void flipBorderHorizontalAbout(Point p) {
		for (int i = 0; i < nbrOfSides; i++) {
			Point v = new Point(theVertices[i]);
			v.x = 2 * p.x - v.x;
			theVertices[i] = v;
		}
	}

	protected void flipBorderVerticalAbout(Point p) {
		for (int i = 0; i < nbrOfSides; i++) {
			Point v = new Point(theVertices[i]);
			v.y = 2 * p.y - v.y;
			theVertices[i] = v;
		}
	}

	protected void rotateBorderAbout(Point p, int degrees) {
		double radians = degreesToRadians(degrees);

		for (int i = 0; i < nbrOfSides; i++) {
			Point v = new Point(theVertices[i]);
			v = rotateAbout(p, v, radians);
			theVertices[i] = v;
		}
	}

	public String toString() {
		String result = new String();
		for (int i = 0; i < nbrOfSides; i++) {
			result = result + " " + theVertices[i].toString();
		}
		return (result);
	}

	protected void translateBorder(int dx, int dy) {
		for (int i = 0; i < nbrOfSides; i++) {
			theVertices[i] = translate(theVertices[i], dx, dy);
		}
	}
}
