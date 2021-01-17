package projectEuler;

import java.math.BigDecimal;
import java.math.MathContext;

public class P080 extends ParameterizedProblem<Integer> {
	public Integer getDefaultParameter() {
		return 100;
	}

	public long solve(Integer maxSquare, boolean printResults) {
		int sqIndex = 3;
		int rationalSq = 4;

		// 100 digiits, plus a couple extra for rounding purposes
		final MathContext mc = new MathContext(102);
		int total = 0;
		for (int square = 2; square <= maxSquare; square++) {
			// we don't want rational squares
			// see writeup for proof that this iterates through squares
			if (square == rationalSq) {
				sqIndex += 2;
				rationalSq += sqIndex;
				continue;
			}

			// calculate the digits
			BigDecimal root = BigDecimal.valueOf(square);
			root = root.sqrt(mc);

			// find the digital sum
			String rootStr = root.toString();

			for (int digit = 0; digit < 101; digit++)
				if (rootStr.charAt(digit) == '.')
					continue;
				else
					total += rootStr.charAt(digit) - '0';

		}
		if (printResults)
			System.out.println(total
					+ " is the sum of the digital sums of the first one hundred decimal digits of all irrational square roots up to "
					+ maxSquare);
		return total;
	}

	public Integer getTestParameter() {
		return 2;
	}

	public long getTestSolution() {
		return 475;
	}

	public String getTitle() {
		return "Problem 080: Square Root Digital Expansion";
	}
}
