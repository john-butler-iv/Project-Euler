package projectEuler;

public class P094 extends Problem {
	private static final boolean DEBUG = false;

	private double h(double l, double m) {
		return Math.sqrt(0.75 * l * l - 0.5 * m * l - 0.25 * m);
	}

	private double A(double l, double m) {
		return 0.5 * (l + m) * h(l, m);
	}

	private long findTriangles(double maxOffset, int LIMIT) {
		long total = 0;
		for (long p = 4; p <= LIMIT; p++) {
			if (DEBUG)
				System.out.print(p + ": ");
			long maxA = (long) Math.floor(A(p / 3.0, 0));
			long minAPos = (long) Math.ceil(A((p - maxOffset) / 3.0, maxOffset));
			long minANeg = (long) Math.ceil(A((p + maxOffset) / 3.0, -maxOffset));

			if (DEBUG)
				System.out.println(A((p + maxOffset) / 3.0, -maxOffset) + " " + A(p / 3.0, 0) + " "
						+ A((p - maxOffset) / 3.0, maxOffset));

			if (DEBUG)
				System.out.println("\t" + minANeg + " " + maxA + " " + minAPos);
			// we don't have to worry about double counting m = 0 because there are no
			// integral area and perimeter triangles with m = 0, as stated in the problem
			long dist = (maxA - minAPos + 1) + (maxA - minANeg + 1);

			if (DEBUG)
				System.out.println("\t" + dist);
			total += dist * p;
		}
		return total;
	}

	@Override
	public long solve(boolean printResults) {
		// final int LIMIT = 20;
		final int LIMIT = 1000000000;

		long total = findTriangles(1, LIMIT);

		if (printResults)
			System.out.println(total + " is the sum of the perimeters of all almost equilateral traingles "
					+ "with integral side lengths and areas whose perimeters do not exceed " + LIMIT);
		return 0;
	}

	@Override
	public String getTitle() {
		return "Problem 094: Almost Equilateral Triangles";
	}

}
