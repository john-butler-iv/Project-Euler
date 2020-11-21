package projectEuler;

import java.util.List;

class P037 extends Problem {
	PrimeFinder pf;

	@Override
	public boolean test() {
		PrimeFinder pf = new PrimeFinder(3798);
		String primeStr = String.valueOf(3797);

		for (int k = 0; k < primeStr.length(); k++) {
			int truncLeft = Integer.parseInt(primeStr.substring(0, k + 1));
			int truncRight = Integer.parseInt(primeStr.substring(k));

			if (!(pf.isPrime(truncLeft) && pf.isPrime(truncRight)))
				return false;
		}
		return true;
	}

	@Override
	public long solve(boolean printResults) {
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
				int truncLeft = Integer.parseInt(primeStr.substring(0, k + 1));
				int truncRight = Integer.parseInt(primeStr.substring(k));

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
	public String getTitle() {
		return "Problem 037: Truncatable Primes";
	}

}
