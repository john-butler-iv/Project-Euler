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

		public String[] next() {
			if (reals == null) {
				currentPos = Position.ff;
				reals = new char[] { '1', '1' };
				reducable = '1';
				return unreduced();
			}
			Position next = nextPos(currentPos);
			if (next != null) {
				currentPos = next;
				return unreduced();
			}
			currentPos = Position.ff;

			if (reducable < '9') {
				reducable++;
				return unreduced();
			}
			reducable = '0';
			if (reals[1] < '9') {
				reals[1]++;
				return unreduced();
			}
			reals[1] = '0';
			if(reals[0] < '9'){
				reals[0]++;
				return unreduced();
			}

			return null;
		}

		private String[] unreduced() {
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
	}

	@Override
	long solve(boolean printResults) {
		int cnt = 0;

		pf = new PrimeFinder(100);
		int prodNum = 1;
		int prodDen = 1;

		// it may be easier to iterate through only the "fake-reducable" numbers
		for (int num = 10; num < 100; num++) {
			for (int den = num + 1; den < 100; den++) {

				// I start with this one first because I'm under the assumption that going
				// through it is less computationally intensive to "fake reduce" than to
				// actually reduce
				int[] fake = fakeReduce(num, den);

				// if weren't able to reduce incorrecty, then we don't care
				// the fraction cannot reduce to 0, without starting out as 0, so we can skip
				// if the denominator is 0, that obviously is not a number and thus cannot be
				// reduced to
				if (fake[0] >= 10 || fake[0] == 0 || fake[1] == 0)
					continue;

				int[] real = pf.reduce(num, den);

				if (real[0] / (double) real[1] == fake[0] / (double) fake[1]) {
					prodNum *= real[0];
					prodDen *= real[1];
					// we know there are exactly four such cases
					if (++cnt == 4)
						break;
				}
			}
		}

		int[] frac = pf.reduce(prodNum, prodDen);
		if (printResults)
			System.out.println(frac[1]);
		return frac[1];
	}

	private static Iterator<FakeFraction> iterator() {
		return new Iterator<FakeFraction>() {

			FakeFraction frac = new FakeFraction();

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public FakeFraction next() {
				// TODO Auto-generated method stub
				return null;
			}

		};
	}

	private static int[] fakeReduce(int n, int d) {
		char[] num = String.valueOf(n).toCharArray();
		char[] den = String.valueOf(d).toCharArray();

		if (num[0] == '0' || num[1] == '0' || den[0] == '0' || den[1] == '1')
			return new int[] { n, d };

		if (num[0] == den[0]) {
			return new int[] { num[1] - '0', den[1] - '0' };
		} else if (num[0] == den[1]) {
			return new int[] { num[1] - '0', den[0] - '0' };
		} else if (num[1] == den[0]) {
			return new int[] { num[0] - '0', den[1] - '0' };
		} else if (num[1] == den[1]) {
			return new int[] { num[0] - '0', den[0] - '0' };
		}
		return new int[] { n, d };
	}

	@Override
	String getTitle() {
		return "Problem 033: Digit Cancelling Fractions";
	}
}