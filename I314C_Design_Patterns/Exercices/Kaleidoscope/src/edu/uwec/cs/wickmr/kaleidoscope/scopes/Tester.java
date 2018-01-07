package edu.uwec.cs.wickmr.kaleidoscope.scopes;

import java.applet.Applet;
import java.awt.Color;
import java.awt.FlowLayout;

import edu.uwec.cs.wickmr.kaleidoscope.controls.KaleidoscopeControl;
import edu.uwec.cs.wickmr.kaleidoscope.factories.GeneralShapeFactory;
import edu.uwec.cs.wickmr.kaleidoscope.strategies.CompositeShapeMutationStrategy;
import edu.uwec.cs.wickmr.kaleidoscope.strategies.GeneralShapeMutationStrategy;
import edu.uwec.cs.wickmr.kaleidoscope.views.FlipKaleidoscopeView;
import edu.uwec.cs.wickmr.kaleidoscope.views.KaleidoscopeView;

@SuppressWarnings("serial")
public class Tester extends Applet {
	// Construct the applet
	private Kaleidoscope kal;

	private KaleidoscopeView kalView1;

	private KaleidoscopeControl kalControl;

	public Tester() {
		setLayout(new FlowLayout());
		CompositeShapeMutationStrategy s = new CompositeShapeMutationStrategy(
				200);

		s.add(new GeneralShapeMutationStrategy(200));
		// s.add( new ImplodeShapeMutationStrategy(200) );

		kal = new Kaleidoscope(new GeneralShapeFactory(200), s);

		kalView1 = new FlipKaleidoscopeView(kal, 200);
		// kalView2 = new RotateKaleidoscopeView(kal);
		kalControl = new KaleidoscopeControl(kal);

		add(kalControl);
		add(kalView1);
		// add(kalView2, new XYConstraints(215, 10, 200, 200));

		setBackground(Color.lightGray);

	}
}
