package edu.uwec.cs.wickmr.kaleidoscope.factories;

import java.awt.Color;
import java.awt.Point;

import edu.uwec.cs.wickmr.kaleidoscope.shapes.Circle;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.Rectangle;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.Shape;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.Square;
import edu.uwec.cs.wickmr.kaleidoscope.shapes.Triangle;
import edu.uwec.cs.wickmr.kaleidoscope.util.Randomizer;

public class GeneralShapeFactory extends ShapeFactory {
	private static Randomizer r = new Randomizer();

	public GeneralShapeFactory(int w) {
		super(w);
	}

	public Shape createShape() {
		Shape result;
		int size1 = r.nextInt(MIN_SHAPE_SIZE, MAX_SHAPE_SIZE);
		int size2 = r.nextInt(MIN_SHAPE_SIZE, MAX_SHAPE_SIZE);
		int temp1 = r.nextInt(0, WIDTH / 2);
		int temp2 = r.nextInt(0, WIDTH / 2);
		Point point = new Point(temp1, temp2);
		Color color = new Color(r.nextInt(100, 255), r.nextInt(100, 255), r
				.nextInt(100, 255));
		switch (r.nextInt(0, 3)) {
		case 0:
			result = new Triangle(point, size1, color);
			break;
		case 1:
			result = new Square(point, size1, color);
			break;
		case 2:
			result = new Rectangle(point, size1, size2, color);
			break;
		default:
			result = new Circle(point, size1, color);
			break;
		}
		return (result);
	}
}
