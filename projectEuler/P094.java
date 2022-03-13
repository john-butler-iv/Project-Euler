package projectEuler;

import java.math.BigDecimal;
import java.math.MathContext;

public class P094 extends Problem {
	private static final boolean DEBUG = true;
	private static final BigDecimal EPSILON = BigDecimal.ONE.scaleByPowerOfTen(-20);

	private boolean approxZero(BigDecimal bd) {
		return bd.subtract(BigDecimal.valueOf(bd.intValue())).abs().compareTo(EPSILON) <= 0;
	}

	private long findPlusTris(int sideLimit) {
		long sum = 0;
		for (long l = 2; l <= sideLimit; l++) {
			BigDecimal rad = new BigDecimal(3 * l * l - 2 * l - 1).sqrt(MathContext.DECIMAL64);
			// double rad = Math.sqrt(3 * l + 1) * Math.sqrt(l - 1);
			if (approxZero(rad)) {// EulerTools.approxIntegral(rad)) {
				long area = (rad.intValue()) * (l + 1);
				if (area % 4 == 0) {
					// sum += area / 4;
					sum += 3 * l + 1;
					if (DEBUG)
						System.out.println(l + "+ " + rad);
					// l *= 10;
				}
			}
		}
		return sum;

	}

	private long findMinusTris(int sideLimit) {
		long sum = 0;
		for (long l = 2; l <= sideLimit; l++) {
			BigDecimal rad = new BigDecimal(3 * l * l + 2 * l - 1).sqrt(MathContext.DECIMAL64);
			// double rad = Math.sqrt(3 * l - 1) * Math.sqrt(l + 1);
			if (approxZero(rad)) {// EulerTools.approxIntegral(rad))
									// {
				long area = ((int) rad.intValue()) * (l - 1);
				if (area % 4 == 0) {
					sum += 3 * l - 1;
					// sum += area / 4;
					if (DEBUG)
						System.out.println(l + "- " + rad);
				}
			}
		}
		return sum;
	}

	@Override
	public long solve(boolean printResults) {
		final int LIMIT = 1000000000;

		long sum = 0;
		sum += findPlusTris((LIMIT - 1) / 3);
		sum += findMinusTris((LIMIT + 1) / 3);

		if (printResults)
			System.out.println(sum + " is the sum of the perimeters of all almost equilateral traingles "
					+ "with integral side lengths and areas whose perimeters do not exceed 1000000000");
		return 0;
	}

	@Override
	public String getTitle() {
		return "Problem 094: Almost Equilateral Triangles";
	}

}
