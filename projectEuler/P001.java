package projectEuler;

class P001 extends ParameterizedProblem<Long> {
	@Override
	public String getTitle() {
		return "Problem 001: Multiples of 3 and 5";
	}

	@Override
	public Long getDefaultParameter() {
		return 1000L;
	}

	@Override
	public Long getTestParameter() {
		return 10L;
	}

	public long getTestSolution() {
		return 23;
	}

	@Override
	long solve(Long maxN, boolean printResults) {
		long sum = 0;

		for (long i = 1; i < maxN; i++)
			if (i % 3 == 0 || i % 5 == 0)
				sum += i;

		if (printResults)
			System.out.println(sum + " is the sum of all numbers below " + maxN + " that are divisible by 3 or 5");

		return sum;
	}
}