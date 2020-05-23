package projectEuler;

class P012 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 500;
	}

	@Override
	public long solve(Integer numDivisors, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(numDivisors * numDivisors * 4);
		int triNum = 1;

		for (int i = 2;; ++i) {
			triNum = EulerTools.triangle(i);

			if (pf.divisors(triNum) > numDivisors)
				break;
		}

		if (printResults)
			System.out.println(triNum + " is the first triangle number to have over " + numDivisors + " divisors.");
		return triNum;
	}

	@Override
	protected Integer getTestParameter() {
		return 5;
	}

	@Override
	protected long getTestSolution() {
		return 28;
	}

	@Override
	public String getTitle() {
		return "Problem 012: Highly Divisible Triangular Number";
	}

}
