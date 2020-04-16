package projectEuler;

public class P047 extends ParameterizedProblem<Integer> {

	@Override
	Integer getDefaultParameter() {
		return 4;
	}

	@Override
	long solve(Integer parameter, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(134043);

		// consecutive number of previous numbers to have the correct number of factors.
		int prevValid = 0;

		// we declare i out here so we can reference it after the loop.
		int i = 2;
		for (; prevValid != parameter; i++) {
			// count the number of prime factors
			int factors = pf.uniquePrimeFactorize(i).size();

			// adjust prevValid accordingly
			if (factors == parameter)
				prevValid++;
			else
				prevValid = 0;
		}

		if (printResults)
			System.out.println(i - parameter + " is the first of " + parameter
					+ " consecutive integers to have exactly " + parameter + " distinct prime factors");
		return i - parameter;
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
	String getTitle() {
		return "Problem 047: Distinct prime factors";
	}
}