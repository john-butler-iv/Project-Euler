package projectEuler;

class P010 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 2000000;
	}

	@Override
	public long solve(Integer primeLimit, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(primeLimit);

		int sum = 0;

		for (int prime : pf.getPrimes())
			sum += prime;

		if (printResults)
			System.out.println(sum + " is the sum of all primes below " + primeLimit + ".");

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
	public String getTitle() {
		return "Problem 010: Summation of Primes";
	}

}
