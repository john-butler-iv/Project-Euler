package projectEuler;

import java.util.List;

class P037 extends Problem {
	PrimeFinder pf;

	@Override
	long solve(boolean printResults) {
		int truncatablePrimesFound = 0;
		int sumOfPrimes = 0;

		pf = new PrimeFinder(1000000);
		List<Integer> primes = pf.getPrimes();

		// primes under 10 (the first four) don't count
		for (int i = 4; truncatablePrimesFound < 11; i++) {
			int prime = primes.get(i);
			boolean truncatable = true;
			String primeStr = String.valueOf(prime);

			for (int k = 0; k < primeStr.length(); k++) {
				int truncLeft = Integer.valueOf(primeStr.substring(0, k + 1));
				int truncRight = Integer.valueOf(primeStr.substring(k));

				if (!(pf.isPrime(truncLeft) && pf.isPrime(truncRight))) {
					truncatable = false;
					break;
				}
			}

			if (truncatable) {
				sumOfPrimes += prime;
				truncatablePrimesFound++;
			}
		}
		if (printResults)
			System.out.println(sumOfPrimes + " is the sum of all truncatable primes");
		return sumOfPrimes;
	}

	@Override
	String getTitle() {
		return "Problem 037: Truncatable Primes";
	}

}