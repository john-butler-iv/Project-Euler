package projectEuler;

class P010 extends ParameterizedProblem<Integer> {

	@Override
	Integer getDefaultParameter() {
		return 2000000;
	}

	@Override
	long solve(Integer parameter, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(parameter);

		int sum = 0;

		for (int prime : pf.getPrimes())
			sum += prime;

		if (printResults)
			System.out.println(sum + " is the sum of all primes below " + parameter + ".");

		return sum;
	}

	@Override
	protected Integer getTestParameter() {
		return 10;
	}

	@Override
	protected long getTestSolution() {
		return 17;
	}

	@Override
	String getTitle() {
		return "Problem 010: Summation of Primes";
	}

}