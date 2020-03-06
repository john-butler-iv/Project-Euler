package projectEuler;

class P012 extends ParameterizedProblem<Integer> {

	@Override
	Integer getDefaultParameter() {
		return 500;
	}

	@Override
	long solve(Integer parameter, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(parameter * parameter * 4);
		int triNum = 1;

		for (int i = 2;; ++i) {
			triNum = EulerTools.triangle(i);

			if (pf.divisors(triNum) > parameter)
				break;
		}

		if (printResults)
			System.out.println(triNum + " is the first triangle number to have over " + parameter + " divisors.");
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
	String getTitle() {
		return "Problem 012: Highly Divisible Triangular Number";
	}

}