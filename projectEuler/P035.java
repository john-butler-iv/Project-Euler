package projectEuler;

class P035 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 1000000;
	}

	public static int[] getDigitRotations(int num) {
		String numStr = String.valueOf(num);
		int[] rotations = new int[numStr.length()];
		rotations[0] = num;
		for (int i = 1; i < rotations.length; i++) {
			numStr = numStr.charAt(numStr.length() - 1) + numStr.substring(0, numStr.length() - 1);
			rotations[i] = Integer.valueOf(numStr);
		}
		return rotations;
	}

	@Override
	public long solve(Integer maxPrime, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(maxPrime);

		int numCircularPrimes = 0;
		for (int i = 0; i < maxPrime; i++) {
			boolean allPrime = true;
			int[] digitRotations = getDigitRotations(i);
			for (int j : digitRotations)
				if (!pf.isPrime(j))
					allPrime = false;

			if (allPrime)
				numCircularPrimes++;

		}
		if (printResults)
			System.out.println("There are " + numCircularPrimes + " circular primes below " + maxPrime);

		return numCircularPrimes;
	}

	@Override
	protected Integer getTestParameter() {
		return 100;
	}

	@Override
	protected long getTestSolution() {
		return 13;
	}

	@Override
	public String getTitle() {
		return "Problem 035: Circular Primes";
	}

}
