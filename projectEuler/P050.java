package projectEuler;

import java.util.List;

public class P050 extends ParameterizedProblem<Integer> {

	@Override
	Integer getDefaultParameter() {
		return 1000000;
	}

	@Override
	long solve(Integer maxPrime, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(maxPrime);
		List<Integer> primes = pf.getPrimes();

		// summing the smallest consecutive primes, 545 primes brings you over 1000000
		for (int numPrimes = 545; numPrimes >= 1; numPrimes--) {
			for (int firstIndex = 0; firstIndex + numPrimes < primes.size(); firstIndex++) {

				// we can pretty easily sum any number of consecutive primes, so we do that and
				// see what we get.
				int sum = 0;
				for (int i = 0; i < numPrimes; i++)
					sum += primes.get(firstIndex + i);

				// if we end up out of bounds, the numbers only get larger, so we just break out
				// of the loop
				if (sum >= maxPrime)
					break;

				// if the sum is prime, we found our number
				if (pf.isPrime(sum)) {
					if (printResults)
						System.out.println(
								sum + " can be written as the sum of the most consecutive primes below " + maxPrime);
					return sum;
				}
			}
		}

		if (printResults)
			System.out.println("no answer was found.");
		return -1;
	}

	@Override
	protected Integer getTestParameter() {
		return 100;
	}

	@Override
	protected long getTestSolution() {
		return 41;
	}

	@Override
	String getTitle() {
		return "Problem 050: Consecutive prime sum";
	}

}