package projectEuler;

class P005 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 20;
	}

	@Override
	public long solve(Integer maxDivisor, boolean printResults) {

		PrimeFinder pf = new PrimeFinder(maxDivisor + 1);

		int[] totalFactorsNeeded = new int[maxDivisor + 1];

		for (int i = 2; i <= maxDivisor; i++) 
			for (PrimeFinder.PrimePower pp : pf.primePowerFactorize(i)) 
				totalFactorsNeeded[pp.prime] = Math.max(totalFactorsNeeded[pp.prime], pp.power);

		// multiplies all of the prime factors together
		int product = 1;
		for (int i = 2; i < totalFactorsNeeded.length; i++) {
			if (totalFactorsNeeded[i] != 0)
				product *= Math.pow(i, totalFactorsNeeded[i]);
		}

		if (printResults)
			System.out.println(
					product + " is the smallest number that is divisible by every number less than " + maxDivisor);

		return product;
	}

	@Override
	protected Integer getTestParameter() {
		return 10;
	}

	@Override
	protected long getTestSolution() {
		return 2520;
	}

	@Override
	public String getTitle() {
		return "Problem 005: Smallest Multiple";
	}

}
