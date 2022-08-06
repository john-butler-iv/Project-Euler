package projectEuler;

public class P113 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 100;
	}

	@Override
	public long solve(Integer totalDigits, boolean printResults) {
		long[/* digits to fill */][/* effective base (offset by one) */] memo = new long[totalDigits + 1][11];

		for (int baseNum = 0; baseNum < memo[0].length; baseNum++)
			memo[0][baseNum] = 1;
		for (int digit = 0; digit < memo.length; digit++)
			memo[digit][0] = 1;

		for (int digit = 1; digit < memo.length; digit++)
			for (int base = 1; base < memo[0].length; base++)
				memo[digit][base] = memo[digit - 1][base] + memo[digit][base - 1];

		long increasing = memo[totalDigits][9];
		// you get an extra digit (0) when compared to increasing, which acts like
		// increasing the base, but you can't use it on its own, so 0, 00, 000, ...
		// don't add to the total, whereas 9, 99, 999, ... do.
		long decreasing = memo[totalDigits][10] - totalDigits - 1;
		long repDigits = 9 * (totalDigits);

		long total = increasing + decreasing - repDigits - 1; // our method counts "0"

		if (printResults) {
			System.out.println("There are " + total + " non-bouncy numbers below 10^" + totalDigits);
		}

		return total;
	}

	@Override
	protected Integer getTestParameter() {
		return 6;
	}

	@Override
	protected long getTestSolution() {
		return 12951;
	}

	@Override
	public String getTitle() {
		return "Problem 113: Non-Bouncy Numbers";
	}

}
