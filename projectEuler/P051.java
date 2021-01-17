package projectEuler;

public class P051 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 8;
	}

	@Override
	public long solve(Integer numPrimes, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(200000);

		// counts how many primes the base will generate
		int currPrimesFound = 0;
		// holds the value of the answer, so we don't have to search for the first prime
		// in the series.
		int ans = 0;
		// 56**3 is the first base pattern to generate 7 primes, and generating 7 primes
		// is necessary to generating 8 primes.
		for (Integer baseNum = Integer.valueOf("56aa3", 11); currPrimesFound < numPrimes; baseNum++) {
			// we're treating the extra digit symbol as the one which should be replaced.
			String basePattern = Integer.toString(baseNum, 11);

			// want to make sure there is a number to replace before doing anything
			if (!basePattern.contains("a"))
				continue;

			// reset counters
			currPrimesFound = 0;
			ans = 0;

			// try to insert each digit in case its prime
			for (int digit = 0; digit <= 9; digit++) {

				// A is the digit we're replacing, so replace the A's with the current digit
				int num = Integer.valueOf(basePattern.replace('a', (char) (digit + '0')));

				// check if the converted digit works
				if (pf.isPrime(num)) {
					if (ans == 0)
						ans = num;
					currPrimesFound++;
				}
			}

			// this is when we check to see if we generate enough primes to
		}
		if (printResults)
			System.out.println(ans + " is the smallest prime which generates " + (numPrimes - 1)
					+ " other primes by replaces digits");
		return ans;
	}

	@Override
	protected Integer getTestParameter() {
		return 7;
	}

	@Override
	protected long getTestSolution() {
		return 56003;
	}

	@Override
	public String getTitle() {
		return "Problem 051: Prime digit replacements";
	}

}
