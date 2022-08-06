package projectEuler;

import java.util.*;

public class P112 extends ParameterizedProblem<Integer> {
	private static class IncreasingIterator implements Iterator<Integer> {
		// array of increasing numbers previously computed, indexed by leading digit.
		// Index 0 is null
		ArrayList<List<Integer>> prevIncs;
		ArrayList<List<Integer>> nextIncs;
		Iterator<Integer> currInc;
		int prevIncLead;
		int digit;
		int place;
		int shiftedDigit;

		public IncreasingIterator() {
			prevIncs = new ArrayList<>(10);
			prevIncs.add(null);
			for (int d1 = 1; d1 < 10; d1++) {
				prevIncs.add(new ArrayList<>());
				for (int d2 = d1; d2 < 10; d2++) {
					prevIncs.get(d1).add(d1 * 10 + d2);
				}
			}
			currInc = prevIncs.get(1).iterator();

			nextIncs = new ArrayList<>(10);
			nextIncs.add(null);
			for (int d = 1; d < 10; d++) {
				nextIncs.add(new ArrayList<>());
			}

			digit = 1;
			place = 100;
			shiftedDigit = 100;
			prevIncLead = 1;
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Integer next() {
			if (!currInc.hasNext()) {
				if (digit == 9) { // need more digits to continue, full transfer
					digit = 1;
					place *= 10;
					shiftedDigit = place;
					prevIncLead = 1;

					prevIncs = nextIncs;
					nextIncs = new ArrayList<>(10);
					nextIncs.add(null);
					for (int d = 1; d < 10; d++) {
						nextIncs.add(new ArrayList<>());
					}
				} else if (prevIncLead == 9) { // switch leading digit
					digit++;
					shiftedDigit = digit * place;
					prevIncLead = digit;
				} else {
					prevIncLead++;
				}
				currInc = prevIncs.get(prevIncLead).iterator();
			}
			int retVar = shiftedDigit + currInc.next();

			nextIncs.get(digit).add(retVar);

			return retVar;
		}
	}

	private static class DecreasingIterator implements Iterator<Integer> {

		ArrayList<Integer> number;

		public DecreasingIterator() {
			number = new ArrayList<Integer>();
			number.add(9);
			number.add(9);
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		private void rebase() {
			for (int i = 0; i < number.size(); i++) {
				number.set(i, 0);
			}
			number.add(1);
		}

		private void check() {
			int place = 0;
			while (place < number.size() - 1) {
				if (number.get(place) <= number.get(place + 1))
					return;
				number.set(place, 0);
				place++;
				number.set(place, number.get(place) + 1);
			}
			if (number.get(place) >= 10)
				rebase();
		}

		private int parse() {
			int retVar = 0;
			int shiftedUnit = 1;
			for (int place = 0; place < number.size(); place++) {
				retVar += shiftedUnit * number.get(place);
				shiftedUnit *= 10;
			}
			return retVar;
		}

		@Override
		public Integer next() {
			int retVar;
			number.set(0, number.get(0) + 1);
			check();
			if (allEq())
				retVar = next();
			else
				retVar = parse();

			return retVar;
		}

		private boolean allEq() {
			for (int i = 0; i < number.size() - 1; i++) {
				if (number.get(i) != number.get(i + 1))
					return false;
			}
			return true;
		}

	}

	private class NonBouncyIterator implements Iterator<Integer> {
		private Iterator<Integer> incIt;
		private Iterator<Integer> decIt;
		private int currInc;
		private int currDec;

		public NonBouncyIterator() {
			incIt = new IncreasingIterator();
			decIt = new DecreasingIterator();
			currInc = incIt.next();
			currDec = decIt.next();
		}

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public Integer next() {
			int retVar;
			if (currInc < currDec) {
				retVar = currInc;
				currInc = incIt.next();
			} else {
				retVar = currDec;
				currDec = decIt.next();
			}
			return retVar;
		}
	}

	@Override
	public Integer getDefaultParameter() {
		return 99;
	}

	@Override
	public long solve(Integer percent, boolean printResults) {
		Iterator<Integer> iterator = new NonBouncyIterator();

		int currNonBouncy;

		// totalNonBouncy aways has a coefficient of 100, so I'm just treating this
		// variable as 100*totalNonBouncy
		int totalNonBouncy = 99;

		// total nb / nb = ratio
		// total nb / curr nb = upperRatio
		// total nb - 1 / prev nb = prevRatio
		// want 100* prevRatio <= percent < 100*prevRatio
		// we know 100 * prevRatio <= percent by getting to this point,
		// so we just need to check:
		// percent < 100 * prevRatio
		// percent < 100 * total nb / curr nb
		// curr nb * percent < 100 * total nb

		do {
			currNonBouncy = iterator.next();
			totalNonBouncy++;
			// System.out.println(totalNonBouncy + " / " + currNonBouncy + " = "
			// + (100 * (currNonBouncy - totalNonBouncy) / currNonBouncy) + "("
			// + (100 * totalNonBouncy / (100 - percent))
			// + ")");
		} while (currNonBouncy * percent >= 100 * (currNonBouncy - totalNonBouncy));
		// we find the first non-bouncy number over, so we have one more non-bouncy
		// number than is in the appropriate range
		totalNonBouncy--;

		// we know retVar * percent = 100 * (retVar - totalNonBouncy)
		// retVar * percent - 100 * retVar = -100 * totalNonBouncy
		// retVar * (percent - 100) = -100 * totalNonBouncy
		// retVar = 100 * totalNonBouncy / (100 - percent)
		long retVar = 100 * totalNonBouncy / (100 - percent);

		if (printResults)
			System.out.println(percent + "% of numbers below " + retVar + " are bouncy.");

		return retVar;
	}

	@Override
	protected Integer getTestParameter() {
		return 90;
	}

	@Override
	protected long getTestSolution() {
		return 21780;
	}

	@Override
	public String getTitle() {
		return "Problem 112: Bouncy numbers";
	}
}
