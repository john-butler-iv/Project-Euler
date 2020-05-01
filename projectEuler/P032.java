package projectEuler;

import java.util.HashSet;
import java.util.Set;

class P032 extends Problem {

	@Override
	boolean test() {
		int a = 39;
		int b = 186;
		int c = a * b;
		return EulerTools.isPandigital(String.valueOf(a) + String.valueOf(b) + String.valueOf(c));
	}

	@Override
	long solve(boolean printResults) {
		Set<Integer> set = new HashSet<>();

		long sum = 0;
		for (int a = 1; a < 10000; a++) {
			for (int b = a + 1; b < 10000; b++) {
				int c = a * b;
				if (!set.contains(c)) {
					String str = String.valueOf(a) + String.valueOf(b) + String.valueOf(c);
					if (EulerTools.isPandigital(str)) {
						set.add(c);
						sum += c;
					}
				}
			}
		}

		if (printResults)
			System.out.println(sum + " is the sum of all pandigital multiplication equations.");
		return sum;
	}

	@Override
	String getTitle() {
		return "Problem 032: Pandigital Products";
	}

}