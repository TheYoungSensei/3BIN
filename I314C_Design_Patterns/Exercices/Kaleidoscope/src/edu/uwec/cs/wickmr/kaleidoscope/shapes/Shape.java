package edu.uwec.cs.wickmr.kaleidoscope.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Shape {
	private Point theCenter;
	private Color theColor;

	public Shape(Shape orig) {
		theCenter = new Point(orig.theCenter);
		theColor = orig.theColor;
	}

	public Shape(Point p, Color c) {
		theCenter = new Point(p);
		theColor = c;
	}

	public abstract Shape copy();

	protected double degreesToRadians(int degrees) {
		return (Math.PI / 180.0 * degrees);
	}

	protected static int distance(Point p1, Point p2) {
		return ((int) Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y)
				* (p2.y - p1.y)));
	}

	public void draw(Graphics g) {
		g.setColor(theColor);
		drawBorder(g);
	}

	public abstract void drawBorder(Graphics g);

	protected abstract void flipBorderHorizontalAbout(Point p);

	protected abstract void flipBorderVerticalAbout(Point p);

	public void flipDiagonalAbout(Point p) {
		flipVerticalAbout(p);
		flipHorizontalAbout(p);
	}

	public void flipHorizontalAbout(Point p) {
		Point ctr = new Point(theCenter);
		ctr.x = 2 * p.x - ctr.x;
		theCenter = ctr;
		flipBorderHorizontalAbout(p);
	}

	public void flipVerticalAbout(Point p) {
		Point ctr = new Point(theCenter);
		ctr.y = 2 * p.y - ctr.y;
		theCenter = ctr;
		flipBorderVerticalAbout(p);
	}

	public Point getCenter() {
		return new Point(theCenter);
	}

	public Color getColor() {
		return theColor;
	}

	public void rotateAbout(Point p, int degrees) {
		theCenter = rotateAbout(p, theCenter, degreesToRadians(degrees));
		rotateBorderAbout(p, degrees);
	}

	protected Point rotateAbout(Point p, Point data, double radians) {
		double cos = Math.cos(radians), sin = Math.sin(radians);
		int x, y;
		x = data.x;
		y = data.y;
		return (new Point((int) (x * cos - y * sin + p.x * (1 - cos) + p.y
				* sin), (int) (x * sin + y * cos + p.y * (1 - cos) - p.x * sin)));
	}

	protected abstract void rotateBorderAbout(Point p, int degrees);

	public void setColor(Color c) {
		theColor = c;
	}

	public void translate(int dx, int dy) {
		Point ctr = new Point(theCenter);
		ctr.x += dx;
		ctr.y += dy;
		theCenter = ctr;
		translateBorder(dx, dy);
	}

	protected static Point translate(Point data, int dx, int dy) {
		return (new Point(data.x + dx, data.y + dy));
	}

	protected abstract void translateBorder(int dx, int dy);

	public void translateTo(Point p) {
		translate(p.x - theCenter.x, p.y - theCenter.y);
	}
}
