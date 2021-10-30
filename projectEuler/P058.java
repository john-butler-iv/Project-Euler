package projectEuler;

public class P058 extends ParameterizedProblem<Double> {
	@Override
	public Double getDefaultParameter() {
		return 0.10;
	}

	@Override
	public long solve(Double goalRatio, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(700000000, false);
		int primes = 0;
		int total = 1;

		for (int sideLen = 3; true; sideLen += 2) {
			// see writeup for proof of algorithm

			// bottom left
			int diagonal = sideLen * sideLen - sideLen + 1;
			primes += pf.isPrime(diagonal) ? 1 : 0;
			// top left
			diagonal += -sideLen + 1;
			primes += pf.isPrime(diagonal) ? 1 : 0;
			// top right
			diagonal += -sideLen + 1;
			primes += pf.isPrime(diagonal) ? 1 : 0;
			// no bottom right
			total += 4;

			if (primes / (double) total < goalRatio) {
				if (printResults)
					System.out.println(sideLen + " is the first side length whose ratio of diagonal primes is bellow "
							+ (100 * goalRatio) + "%");
				return sideLen;
			}
		}
	}

	@Override
	public Double getTestParameter() {
		return 0.62;
	}

	@Override
	public long getTestSolution() {
		return 7;
	}

	@Override
	public String getTitle() {
		return "Problem 058: Spiral Primes";
	}
}
