package projectEuler;

import java.nio.ReadOnlyBufferException;
import java.util.Iterator;

class P033 extends Problem {
	PrimeFinder pf;

	private static class FakeFraction {
		enum Position {
			ff, fs, sf, ss
		}

		char[] reals;
		char reducable;
		Position currentPos;

		public FakeFraction() {
		}

		private static Position nextPos(Position pos) {
			switch (pos) {
				case ff:
					return Position.fs;
				case fs:
					return Position.sf;
				case sf:
					return Position.ss;
				case ss:
					return null;
			}
			return Position.ff;
		}

		private String[] doAgain() {
			String[] unreduced = unreduced();
			if (unreduced[0].compareTo(unreduced[1]) >= 0)
				return next();

			return unreduced();
		}

		public String[] next() {
			if (reals == null) {
				currentPos = Position.ff;
				reals = new char[] { '1', '1' };
				reducable = '1';
				return doAgain();
			}
			Position next = nextPos(currentPos);
			if (next != null) {
				currentPos = next;
				return doAgain();
			}
			currentPos = Position.ff;

			if (reducable < '9') {
				reducable++;
				return doAgain();
			}
			reducable = '1';
			if (reals[1] < '9') {
				reals[1]++;
				return doAgain();
			}
			reals[1] = '1';
			if (reals[0] < '9') {
				reals[0]++;
				return doAgain();
			}

			return null;
		}

		public String[] unreduced() {
			String num;
			String den;
			switch (currentPos) {
				case ff:
					num = new String(new char[] { reducable, reals[0] });
					den = new String(new char[] { reducable, reals[1] });
					break;
				case fs:
					num = new String(new char[] { reducable, reals[0] });
					den = new String(new char[] { reals[1], reducable });
					break;
				case sf:
					num = new String(new char[] { reals[0], reducable });
					den = new String(new char[] { reducable, reals[1] });
					break;
				case ss:
					num = new String(new char[] { reals[0], reducable });
					den = new String(new char[] { reals[1], reducable });
					break;
				default:
					return null;
			}
			return new String[] { num, den };
		}

		public int[] reduced() {
			return new int[] { reals[0] - '0', reals[1] - '0' };
		}
	}

	@Override
	long solve(boolean printResults) {
		int cnt = 0;
		int prodNum = 1;
		int prodDen = 1;

		pf = new PrimeFinder(100);
		FakeFraction fakeFraction = new FakeFraction();
		while (cnt < 4) {
			// iterating through fractions like this invloves going through fewer cases
			fakeFraction.next();

			String[] fullFrac = fakeFraction.unreduced();
			// I can only reduce integer fractions
			int[] intFrac = new int[] { Integer.parseInt(fullFrac[0]), Integer.parseInt(fullFrac[1]) };
			int[] realReduced = pf.reduce(intFrac);

			int[] fakeReduced = fakeFraction.reduced();

			// figure out if the reduced fraction works out
			if (realReduced[0] / (double) realReduced[1] == fakeReduced[0] / (double) fakeReduced[1]) {
				// need to keep track of both to reduce
				prodNum *= realReduced[0];
				prodDen *= realReduced[1];
				cnt++;
			}
		}

		int[] frac = pf.reduce(prodNum, prodDen);
		if (printResults)
			System.out.println(frac[1]);
		return frac[1];
	}

	@Override
	String getTitle() {
		return "Problem 033: Digit Cancelling Fractions";
	}
}