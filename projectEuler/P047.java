package projectEuler;

public class P047 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 4;
	}

	@Override
	public long solve(Integer numConsec, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(134043);

		// consecutive number of previous numbers to have the correct number of factors.
		int prevValid = 0;

		// we declare i out here so we can reference it after the loop.
		int i = 2;
		for (; prevValid != numConsec; i++) {
			// count the number of prime factors
			int factors = pf.uniquePrimeFactorize(i).size();

			// adjust prevValid accordingly
			if (factors == numConsec)
				prevValid++;
			else
				prevValid = 0;
		}

		if (printResults)
			System.out.println(i - numConsec + " is the first of " + numConsec
					+ " consecutive integers to have exactly " + numConsec + " distinct prime factors");
		return i - numConsec;
	}

	@Override
	protected Integer getTestParameter() {
		return 3;
	}

	@Override
	protected long getTestSolution() {
		return 644;
	}

	@Override
	public String getTitle() {
		return "Problem 047: Distinct prime factors";
	}
}
