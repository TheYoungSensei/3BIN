package edu.uwec.cs.wickmr.kaleidoscope.views;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import edu.uwec.cs.wickmr.kaleidoscope.scopes.Kaleidoscope;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.ShapeIterator;

public abstract class KaleidoscopeView extends Canvas {
	protected ShapeIterator shapes;
	protected Kaleidoscope kaleidoscope;

	public KaleidoscopeView(Kaleidoscope k, int size) {
		kaleidoscope = k;
		shapes = k.getShapes();
		setSize(size, size);
		k.register(this);
	}

	protected void drawLines(Graphics g) {
		Dimension d = getSize();
		setBackground(Color.black);
		g.setColor(Color.cyan);
		g.drawLine(0, d.width / 2, d.width, d.width / 2);
		g.drawLine(d.width / 2, 0, d.width / 2, d.width);
		g.drawLine(0, 0, d.width, d.width);
		g.drawLine(0, d.width, d.width, 0);
	}

	protected abstract void drawShapes(Graphics g);

	public void paint(Graphics g) {
		drawLines(g);
		drawShapes(g);
	}

	public void update() {
		shapes = kaleidoscope.getShapes();
		repaint();
	}
}
