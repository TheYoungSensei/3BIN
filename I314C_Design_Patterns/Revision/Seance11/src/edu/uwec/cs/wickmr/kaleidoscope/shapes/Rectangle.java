package edu.uwec.cs.wickmr.kaleidoscope.shapes;

import java.awt.Color;
import java.awt.Point;

public class Rectangle extends Polygon {
	private int height, width;
	protected final static int SIDES = 4;

	public Rectangle(Rectangle orig) {
		super(orig);
		height = orig.height;
		width = orig.width;
	}

	public Rectangle(Point center, int h, int w, Color aColor) {
		super(center, aColor, new Point[] {
				new Point(center.x - w / 2, center.y - h / 2),
				new Point(center.x - w / 2, center.y + h / 2),
				new Point(center.x + w / 2, center.y + h / 2),
				new Point(center.x + w / 2, center.y - h / 2)
		});
		height = h;
		width = w;

	}

	public Shape copy() {
		return (new Rectangle(this));
	}
}
