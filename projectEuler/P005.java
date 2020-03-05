package projectEuler;

class P005 extends ParameterizedProblem<Integer> {

	@Override
	Integer getDefaultParameter() {
		return 20;
	}

	@Override
	long solve(Integer parameter, boolean printResults) {

		PrimeFinder pf = new PrimeFinder(parameter + 1);

		int[] totalFactorsNeeded = new int[parameter + 1];
		
		for (int i = 2; i <= parameter; i++) {

			// find the multiplicity of each factor
			int[] currFactorsNeeded = new int[totalFactorsNeeded.length];
			for (int factor : pf.primeFactorize(i))
				currFactorsNeeded[factor]++;

			// check if it surpasses the need of the factors we are already considering
			for (int j = 2; j < currFactorsNeeded.length; j++)
				if (currFactorsNeeded[j] > totalFactorsNeeded[j])
					totalFactorsNeeded[j] = currFactorsNeeded[j];

		}

		// multiplies all of the prime factors together
		int product = 1;
		for (int i = 2; i < totalFactorsNeeded.length; i++)
			if (totalFactorsNeeded[i] != 0) 
				product *= Math.pow(i, totalFactorsNeeded[i]);

				
		if (printResults)
			System.out.println(
					product + " is the smallest number that is divisible by every number less than " + parameter);

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
	String getTitle() {
		return "Problem 005: Smallest Multiple";
	}

}