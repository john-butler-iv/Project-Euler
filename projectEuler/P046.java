package projectEuler;

public class P046 extends Problem {

	@Override
	public long solve(boolean printResults) {
		PrimeFinder pf = new PrimeFinder(1000, false);

		for (int c = 3; c < pf.limit(); c += 2) {
			if (pf.isPrime(c))
				continue;

			boolean expressable = false;

			// there are fewer squares less than n than primes less than n, so it takes
			// fewer iterations to loop over squares than primes
			// As per p7, there are 10,001 primes below 104743, but sqrt(104743) = 323
			// squares below 104743
			for (int sqrt = 1; c - 2 * sqrt * sqrt >= 2; sqrt++) {
				// c = prime + 2 * square
				// c - 2 * square = prime
				if (pf.isPrime(c - 2 * sqrt * sqrt)) {
					expressable = true;
					break;
				}
			}

			if (!expressable) {
				if (printResults)
					System.out.println(c + " cannot be written as the sum of a prime and twice a square.");
				return c;
			}
		}

		if (printResults)
			System.out.println("could not find a counter example. Maybe the prime limit is too small?");
		return -1;
	}

	@Override
	public String getTitle() {
		return "Problem 046: Goldbach's other conjecture";
	}

}
