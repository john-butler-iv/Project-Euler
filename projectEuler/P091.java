package projectEuler;

public class P091 extends ParameterizedProblem<Integer> {
	public Integer getDefaultParameter() {
		return 50;
	}

	public long solve(Integer maxXY, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(maxXY);

		int total = 0;

		// find triangles with a point where y > x > 0
		// we're iterating over the point that has the right angle
		for (int y1 = 1; y1 <= maxXY; y1++) {
			for (int x1 = 1; x1 < y1; x1++) {
				int gcd = pf.gcd(x1, y1);

				int dx = y1 / gcd;
				int dy = x1 / gcd;

				// hypoteneuse to the left
				total += Math.min((maxXY - x1) / dx, y1 / dy);
				// hypoteneuse to the right
				total += Math.min(x1 / dx, (maxXY - y1) / dy);
			}
		}

		// triangles where x > y > 0
		total *= 2;

		total += 7 * maxXY * maxXY / 2;

		if (printResults)
			System.out.println("There are " + total + " right triangles with points (0,0), (x1, y1), and (x2, y2) "
					+ "with 0 <= x1, x2, y1, y2 <= " + maxXY);
		return total;

	}

	public Integer getTestParameter() {
		return 2;
	}

	public long getTestSolution() {
		return 14;
	}

	public String getTitle() {
		return "Problem 091: Right Triangles with Integer Coordinates";
	}
}
