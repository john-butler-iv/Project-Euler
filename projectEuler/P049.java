package projectEuler;

import java.util.List;

public class P049 extends Problem {

	@Override
	public boolean test() {
		PrimeFinder pf = new PrimeFinder(10000);
		if (!pf.isPrime(1487))
			return false;
		if (!pf.isPrime(4817))
			return false;
		if (!pf.isPrime(8147))
			return false;

		return arePermutations(1487, 4817, 8147);
	}

	@Override
	public long solve(boolean printResults) {
		PrimeFinder pf = new PrimeFinder(10000);
		List<Integer> primes = pf.getPrimes();

		// we want to start checking when the primes are four digits long
		int index = 0;
		while (primes.get(index++) < 1000)
			;

		// it's faster to iterate over primes than to iterate over all numbers and check
		// if they're prime
		for (; primes.get(index) < 10000; index++) {
			int num1 = primes.get(index);
			for (int addend = 1; num1 + 2 * addend < 10000; addend++) {
				// this is the given example
				if (num1 == 1487 && addend == 3330)
					break;

				int num2 = num1 + addend;
				int num3 = num2 + addend;

				// checking if a number is prime is O(1) with our method, so we make sure to do
				// that first
				if (pf.isPrime(num2) && pf.isPrime(num3) && arePermutations(num1, num2, num3)) {
					// output
					String concat = num1 + "" + num2 + num3;
					if (printResults)
						System.out.println(num1 + ", " + num2 + ", and " + num3 + " (" + concat
								+ ") have equal distances and all three are prime.");
					return Long.valueOf(concat);
				}
			}
		}

		// the true output is in the loop, so that means that something went wrong in
		// the algorithm.
		if (printResults)
			System.out.println("Could not find an answer, try increasing the prime limit");
		return -1;
	}

	public static boolean arePermutations(int a, int b, int c) {
		String aStr = String.valueOf(a);
		String bStr = String.valueOf(b);
		String cStr = String.valueOf(c);
		return CollectionTools.arePermutations(aStr, bStr) && CollectionTools.arePermutations(bStr, cStr);
	}

	@Override
	public String getTitle() {
		return "Problem 049: Prime permutations";
	}
}
