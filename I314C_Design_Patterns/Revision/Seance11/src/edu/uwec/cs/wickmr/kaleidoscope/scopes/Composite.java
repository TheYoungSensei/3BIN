package edu.uwec.cs.wickmr.kaleidoscope.scopes;

import java.applet.Applet;
import java.awt.Color;
import java.awt.FlowLayout;

import edu.uwec.cs.wickmr.kaleidoscope.controls.KaleidoscopeControl;
import edu.uwec.cs.wickmr.kaleidoscope.factories.GeneralShapeFactory;
import edu.uwec.cs.wickmr.kaleidoscope.strategies.CompositeShapeMutationStrategy;
import edu.uwec.cs.wickmr.kaleidoscope.strategies.ExplodeShapeMutationStrategy;
import edu.uwec.cs.wickmr.kaleidoscope.strategies.SpinShapeMutationStrategy;
import edu.uwec.cs.wickmr.kaleidoscope.views.FlipKaleidoscopeView;
import edu.uwec.cs.wickmr.kaleidoscope.views.KaleidoscopeView;

@SuppressWarnings("serial")
public class Composite extends Applet {
	// Construct the applet
	private Kaleidoscope kal;

	private KaleidoscopeView kalView1;

	private KaleidoscopeControl kalControl;

	public Composite() {
		setLayout(new FlowLayout());
		CompositeShapeMutationStrategy s = new CompositeShapeMutationStrategy(
				200);

		s.add(new SpinShapeMutationStrategy(200));
		s.add(new ExplodeShapeMutationStrategy(200));

		kal = new Kaleidoscope(new GeneralShapeFactory(200), s);

		kalView1 = new FlipKaleidoscopeView(kal, 200);
		kalControl = new KaleidoscopeControl(kal);

		add(kalView1);
		add(kalControl);

		setBackground(Color.lightGray);
	}
}
