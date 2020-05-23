package projectEuler;

class P006 extends ParameterizedProblem<Long> {

	@Override
	public Long getDefaultParameter() {
		return 100L;
	}

	@Override
	public long solve(Long sumLimit, boolean printResults) {
		long sum1 = sumLimit * (sumLimit + 1) / 2;
		sum1 *= sum1;

		long sum2 = sumLimit * (sumLimit + 1) * (2 * sumLimit + 1) / 6;

		long diff = sum1 - sum2;

		if (printResults)
			System.out.println(
					diff + " is the difference between the sum of the squares and the square of the sum of the first "
							+ sumLimit + " natural numbers");
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
	public String getTitle() {
		return "Problem 006: Sum Square Difference";
	}

}
