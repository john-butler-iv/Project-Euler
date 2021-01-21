package projectEuler;

class P007 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 10001;
	}

	@Override
	public long solve(Integer primeIndex, boolean printResults) {

		PrimeFinder pf = new PrimeFinder(3 * (int) Math.log(primeIndex) * primeIndex);

		int prime = pf.getPrimes().get(primeIndex - 1);
		if (printResults)
			System.out.println("the " + primeIndex + " prime is " + prime);

		return prime;
	}

	@Override
	protected Integer getTestParameter() {
		return 6;
	}

	@Override
	protected long getTestSolution() {
		return 13;
	}

	@Override
	public String getTitle() {
		return "Problem 007: 10,001st Prime";
	}

}
