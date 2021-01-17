package projectEuler;

public class P101 extends Problem {
	private static long lagrange(int x, int pts, long[] real_vals) {
		long total = 0;
		for (int i = 1; i <= pts; i++) {
			// to avoid float imprecision, I only divide at the end,
			// and if I do everything right, I should end with an integer anyways
			long num = real_vals[i];
			long den = 1;
			// two for loops is the easiest way I thought of to do prod_{j \ne i}
			for (int j = 1; j < i; j++) {
				num *= (x - j);
				den *= (i - j);
			}
			for (int j = i + 1; j <= pts; j++) {
				num *= (x - j);
				den *= (i - j);
			}
			total += num / den;
		}
		return total;
	}

	private static long u(int x) {
		long mult = 1;
		long total = 0;
		for (int i = 0; i <= 10; i++) {
			total += mult;
			mult *= -x;
		}
		return total;
	}

	public long solve(boolean printResults) {
		long us[] = new long[12];
		for (int i = 1; i < us.length; i++)
			us[i] = u(i);

		long total = 0;
		for (int i = 1; i <= 10; i++) {
			// I just happen to know that the FIT is always the first point that we don't
			// actively acheive
			long l = lagrange(i + 1, i, us);
			total += l;
		}

		if (printResults)
			System.out.println(total + " is the sum of the FITs of the BOPs of u_n");
		return total;
	}

	public String getTitle() {
		return "Problem 101: Optimum Polynomial";
	}

}
