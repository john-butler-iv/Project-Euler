package projectEuler;

import java.util.*;

public class P093 extends Problem {

	public boolean test() {

		return solveCombo(new Integer[] { 1, 2, 3, 4 }) == 28;
	}

	public static int solveCombo(Integer[] digits) {
		Iterator<Integer[]> it = CollectionTools.permuteIterator(digits);

		boolean[] encountered = new boolean[3026];
		int maxVal = 0;
		while (it.hasNext()) {
			Iterator<Integer> itCurr = solveComboOrdered(it.next()).iterator();
			while (itCurr.hasNext()) {
				int curr = itCurr.next();
				encountered[curr] = true;
				maxVal = Math.max(curr, maxVal);
			}
		}

		int maxStreak = 0;
		int currStreak = 0;

		for (int i = 1; i <= maxVal + 1; i++) {
			if (encountered[i]) {
				currStreak++;
			} else {
				maxStreak = Math.max(maxStreak, currStreak);
				currStreak = 0;
			}
		}

		return maxStreak;
	}

	private static double performOp(double a, int o, double b) {
		switch (o) {
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		case 3:
			return a / b;
		}
		return 0;
	}

	private static List<Integer> solveComboOrdered(Integer[] digits) {
		List<Integer> list = new LinkedList<Integer>();

		for (int o1 = 0; o1 <= 4; o1++) {
			double r1 = performOp(digits[0], o1, digits[1]);

			for (int o2 = 0; o2 <= 4; o2++) {
				double r2 = performOp(r1, o2, digits[2]);
				double r2p = performOp(digits[2], o2, digits[3]);

				for (int o3 = 0; o3 <= 4; o3++) {
					double r3 = performOp(r2, o3, digits[3]);
					double r3p = performOp(r1, o3, r2p);

					if (r3 > 0 && EulerTools.approxIntegral(r3)) {
						list.add((int) (r3 + 0.5));
					}
					if (r3p > 0 && EulerTools.approxIntegral(r3p)) {
						list.add((int) (r3p + 0.5));
					}
				}
			}
		}

		return list;
	}

	public long solve(boolean printResults) {
		int best = -1;
		int[] bestNums = new int[] { 0, 0, 0, 0 };
		for (int a = 1; a <= 6; a++) {
			for (int b = 2; b <= 7; b++) {
				for (int c = 3; c <= 8; c++) {
					for (int d = 4; d <= 9; d++) {
						int curr = solveCombo(new Integer[] { a, b, c, d });
						if (curr > best) {
							best = curr;
							bestNums = new int[] { a, b, c, d };
						}
					}
				}
			}
		}
		if (printResults)
			System.out.println("" + bestNums[0] + bestNums[1] + bestNums[2] + bestNums[3]
					+ " generates the most consecutive positive integers");
		return best;
	}

	public String getTitle() {
		return "Problem 093: Arithmetic Expressions";
	}
}
