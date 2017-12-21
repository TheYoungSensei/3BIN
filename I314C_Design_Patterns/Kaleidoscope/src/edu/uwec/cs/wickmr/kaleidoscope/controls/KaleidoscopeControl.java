package edu.uwec.cs.wickmr.kaleidoscope.controls;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import edu.uwec.cs.wickmr.kaleidoscope.scopes.Kaleidoscope;

@SuppressWarnings("serial")
public class KaleidoscopeControl extends Panel implements Runnable {
	private List<Kaleidoscope> theScopes;

	private boolean inAutoMode;

	private Button turnButton, runButton, stopButton;

	private Thread animator;

	public KaleidoscopeControl(Kaleidoscope kal) {
		theScopes = new ArrayList<Kaleidoscope>();
		theScopes.add(kal);

		setLayout(new FlowLayout());

		turnButton = new Button("Turn");
		runButton = new Button("Run");
		stopButton = new Button("Stop");

		add(turnButton);
		add(runButton);
		add(stopButton);

		turnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < theScopes.size(); i++) {
					theScopes.get(i).turn();
				}
			}
		});

		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stop();
			}
		});

		runButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start();
			}
		});

		inAutoMode = false;
	}

	public void register(Kaleidoscope k) {
		theScopes.add(k);
	}

	public void run() {
		while (inAutoMode) {
			for (int i = 0; i < theScopes.size(); i++) {
				theScopes.get(i).turn();
			}
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public void start() {
		animator = new Thread(this);
		inAutoMode = true;
		animator.start();
	}

	public void stop() {
		if (animator != null) {
			inAutoMode = false;
		}
	}
}
