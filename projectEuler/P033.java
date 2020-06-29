package projectEuler;

class P033 extends Problem {
	PrimeFinder pf;

	private static class DigitReducableFraction {
		// represents where in the fraction the reducable digit will be placed.
		private enum Position {
			ff, // first in numerator, first in denomonator
			fs, // first in numerator, second in denomonator
			sf, // second in numerator, first in denomonator
			ss // second in numerator, second in denomonator
		}

		private char[] reals;
		private char reducable;
		private Position currentPos;

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

		/**
		 * Ensures the current fraction is a valid fraction, and continues to iterate
		 * over fractions until one is found
		 * 
		 * @return returns the first valid fraction from when the method is called
		 */
		private String[] validate() {
			String[] unreduced = unreduced();

			// the problem specifies fractions must be less than one (a/b < 1 => a < b)
			if (unreduced[0].compareTo(unreduced[1]) >= 0)
				return next();

			return unreduced();
		}

		/**
		 * finds the next possible digit reducable fraction, starting with 11/11. After
		 * the final possible fraction, it returns null. Note: 0's cannot be reduced, so
		 * we don't consider them.
		 * 
		 * @return first call returns 11/11, each subsiquent call gives another digit
		 *         reducable fraction, or null if there is are no more.
		 */
		public String[] next() {
			// check if initialized
			if (reals == null) {
				currentPos = Position.ff;
				reals = new char[] { '1', '1' };
				reducable = '1';
				return validate();
			}

			// first attempt to move where we cancel the digit
			Position next = nextPos(currentPos);
			if (next != null) {
				currentPos = next;
				return validate();
			}
			currentPos = Position.ff;

			// if we've tried every position, change the digit we're canceling
			if (reducable < '9') {
				reducable++;
				return validate();
			}
			reducable = '1';

			// if we've tried every digit, change the digit in the denomonator which doesn't
			// reduce.
			if (reals[1] < '9') {
				reals[1]++;
				return validate();
			}
			reals[1] = '1';

			// if we've tried every number in the denomonator, change the digit in the
			// numerator which doesn't reduce
			if (reals[0] < '9') {
				reals[0]++;
				return validate();
			}

			// if everything has been exhausted, we've reached the end of the line
			return null;
		}

		/**
		 * converts the internal state into a String[] in the form [numerator,
		 * denomonator].
		 * 
		 * @return returns a String[] representation of the fraction currently stored by
		 *         this instance.
		 */
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

		/**
		 * converts the internal state into a int[] in the form [reduced numerator,
		 * reduced denomonator].
		 * 
		 * @return returns a int[] representation of the reduced form of the fraction
		 *         currently stored by this instance.
		 */
		public int[] reduced() {
			return new int[] { reals[0] - '0', reals[1] - '0' };
		}
	}

	@Override
	public long solve(boolean printResults) {
		int cnt = 0;
		// need to keep track of both to reduce later
		int prodNum = 1;
		int prodDen = 1;

		pf = new PrimeFinder(100);
		DigitReducableFraction drFrac = new DigitReducableFraction();
		while (cnt < 4) {
			// iterating through fractions like this invloves going through fewer cases than
			// iterating over all fractions or properly reducable ones
			drFrac.next();

			// the digit-canceled fractions
			String[] fullFrac = drFrac.unreduced();
			int[] fakeReduced = drFrac.reduced();

			// the real math-reduced fractions

			// I can only reduce integer fractions with PrimeFinder
			int[] intFrac = new int[] { Integer.parseInt(fullFrac[0]), Integer.parseInt(fullFrac[1]) };

			int[] realReduced = pf.reduce(intFrac);

			// figure out if the reduced fraction works out
			// I can't just compare the digits because the digit cancelation may only
			// partially reduce. i.e. 49/98 -> 4/8
			if (realReduced[0] / (double) realReduced[1] == fakeReduced[0] / (double) fakeReduced[1]) {
				prodNum *= realReduced[0];
				prodDen *= realReduced[1];
				cnt++;
			}
		}

		int[] frac = pf.reduce(prodNum, prodDen);
		if (printResults)
			System.out.println(frac[1]
					+ " is the reduced denomonator of the product of all two-digit fractions which can be reduced properly or by digits.");
		return frac[1];
	}

	@Override
	public String getTitle() {
		return "Problem 033: Digit Cancelling Fractions";
	}
}
