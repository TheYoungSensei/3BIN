package edu.uwec.cs.wickmr.kaleidoscope.util;

public class Randomizer {
	public Randomizer() {
	}

	public int nextInt(int l, int h) {
		int result = (int) (Math.round(l + Math.random() * (h - l)));
		return (result);
	}
}
