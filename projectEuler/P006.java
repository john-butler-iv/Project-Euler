package projectEuler;

class P006 extends ParameterizedProblem<Long> {

	@Override
	Long getDefaultParameter() {
		return 100L;
	}

	@Override
	long solve(Long parameter, boolean printResults) {
		long sum1 = parameter * (parameter + 1) / 2;
		sum1 *= sum1;

		long sum2 = parameter * (parameter + 1) * (2 * parameter + 1) / 6;

		long diff = sum1 - sum2;

		if (printResults)
			System.out.println(
					diff + " is the difference between the sum of the squares and the square of the sum of the first "
							+ parameter + " natural numbers");
		return diff;
	}

	@Override
	protected Long getTestParameter() {
		return 10L;
	}

	@Override
	protected long getTestSolution() {
		return 2640;
	}

	@Override
	String getTitle() {
		return "Problem 006: Sum Square Difference";
	}

}