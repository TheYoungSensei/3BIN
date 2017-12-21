package edu.uwec.cs.wickmr.kaleidoscope.factories;

import java.awt.Color;
import java.awt.Point;

import edu.uwec.cs.wickmr.kaleidoscope.shapes.Circle;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;

public class CircleFactory extends ShapeFactory {

	public CircleFactory(int w) {
		super(w);
	}

	public Shape createShape() {
		Shape result;
		int size1 = MIN_SHAPE_SIZE
				+ (int) (Math.random() * (MAX_SHAPE_SIZE - MIN_SHAPE_SIZE));

		int temp1 = (int) (Math.random() * WIDTH / 2);
		int temp2 = (int) (Math.random() * WIDTH / 2);
		Point point = new Point(temp1, temp2);
		Color color = new Color((int) (Math.random() * 255), (int) (Math
				.random() * 255), (int) (Math.random() * 255));
		result = new Circle(point, size1, color);
		return (result);
	}
}
