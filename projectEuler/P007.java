package projectEuler;

class P007 extends ParameterizedProblem<Integer> {

	@Override
	Integer getDefaultParameter() {
		return 10001;
	}

	public static void main(String[] args) {
		new P007().solve(true);
	}

	@Override
	long solve(Integer parameter, boolean printResults) {

		PrimeFinder pf = new PrimeFinder(3 * (int) Math.log(parameter) * parameter);

		int prime = pf.getPrimes().get(parameter - 1);
		if (printResults)
			System.out.println("the " + parameter + " prime is " + prime);

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
	String getTitle() {
		return "Problem 007: 10,001st Prime";
	}

}