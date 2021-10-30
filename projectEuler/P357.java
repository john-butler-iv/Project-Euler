package projectEuler;

import java.util.List;

public class P357 extends Problem {

	@Override
	public long solve(boolean printResults) {
		final int LIMIT = (int) (Math.pow(10, 8)) + 1;
		PrimeFinder pf = new PrimeFinder(LIMIT);

		long sum = 2;
		for (int prime : pf.getPrimes()) {
			// this is the only time in which a prime is one below another prime, which
			// messes with my algorithm
			if (prime == 3)
				continue;

			System.out.println(prime + " / " + LIMIT);
			// 1 divides every number, so d + n / d when d=1 looks like 1 + n / 1 = n + 1,
			// which must be prime, so every candidate must be one less than a prime.
			int n = prime - 1;

			List<Integer> factors = pf.factorize(n);
			// perfect square. sqrt n + n / sqrt n = 2sqrt n; not prime.
			if (factors.size() % 2 == 1)
				continue;

			// remove 1 and n
			factors.remove(0);
			factors.remove(factors.size() - 1);

			boolean pgi = true;
			for (int i = 0; i < factors.size() / 2; i++) {
				if (!pf.isPrime(factors.get(i) + factors.get(factors.size() - 1 - i))) {
					pgi = false;
					break;
				}
			}
			if (pgi)
				sum += n;
		}

		if (printResults) {
			System.out.println(sum + " is the sum of all prime generating integers below 10^8");
		}
		return sum;
	}

	@Override
	public String getTitle() {
		return "Problem 357: Prime Generating Integers";
	}

}
