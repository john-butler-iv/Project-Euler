package projectEuler;

import java.util.List;

public class P077 extends ParameterizedProblem<Integer> {

	@Override
	protected Integer getTestParameter() {
		return 4;
	}

	@Override
	public Integer getDefaultParameter() {
		return 5000;
	}

	@Override
	public long solve(Integer permutations, boolean printResults) {
		final int LIMIT = 100;
		PrimeFinder pf = new PrimeFinder(LIMIT);
		List<Integer> primes = pf.getPrimes();

		// combinations[n][primeIndex] contains the number of ways to write n as the sum
		// of primes under primes.get(primeIndex). We're pretending that we're only
		// summing number in decreasing order
		int[/* index of max prime index */][/* number to find combinations for */] numPartitions = new int[LIMIT][pf
				.getPrimes().size()];

		// prime + 0 = (a single) prime is a valid sumamtion, so if we make it to 0,
		// that is we try to subtract a prime from a prime, we want to increment the
		// count
		for (int primeIndex = 0; primeIndex < numPartitions[0].length; primeIndex++)
			numPartitions[0][primeIndex] = 1;
		// all values above the board should be treated as 0, but instead of checking
		// for that in the algorithm, I'm just hard coding the first row
		for (int n = 2; n < numPartitions.length; n++)
			numPartitions[n][0] = (n + 1) % 2;

		int answer = 0;

		for (int n = 2; n < LIMIT; n++) {
			int maxPrimeIndex = 1;
			int prime = 3;
			for (; prime <= n; prime = primes.get(++maxPrimeIndex)) {
				numPartitions[n][maxPrimeIndex] = numPartitions[n - prime][maxPrimeIndex]
						+ numPartitions[n][maxPrimeIndex - 1];

				if (numPartitions[n][maxPrimeIndex] > permutations) {
					answer = n;
					break;
				}
			}
			if (answer != 0) {
				break;
			}

			// there is no way to write a number as a sum of an even greater number, so
			// allowing primes larger than the number itself won't increase the total. We
			// can just copy the exisitng value. These copied vaues are important for future
			// calculations
			for (; maxPrimeIndex < primes.size(); maxPrimeIndex++) {
				numPartitions[n][maxPrimeIndex] = numPartitions[n][maxPrimeIndex - 1];
			}
		}

		if (printResults) {
			System.out.println(answer + " is the first value which can be written as the sum of primes in over "
					+ permutations + " ways.");
		}
		return answer;
	}

	@Override
	protected long getTestSolution() {
		return 10;
	}

	@Override
	public String getTitle() {
		return "Problem 077: Prime Summations";
	}

}