package projectEuler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PrimeFinder {
	private boolean[] primeTable;
	private List<Integer> primes;
	private int limit;

	/**
	 * Finds all primes less than limit, non-inclusive
	 * 
	 * @param limit the upper-limit on which primes will be found, non-inclusive
	 */
	public PrimeFinder(int limit) {
		if (limit <= 0)
			limit = 1;

		primeTable = new boolean[limit];
		primes = new ArrayList<>();
		this.limit = limit;
		sievePrimes();
	}

	/**
	 * This method is the one that actually finds all primes under limit, and
	 * assigns the proper values to primeTable e and primes. I believe this is the
	 * fastest known algorithm to find primes, but I could be mistaken.
	 */
	private void sievePrimes() {
		// every number is a prime unless we find factors
		for (int i = 2; i < limit; i++)
			primeTable[i] = true;

		for (int i = 2; i < limit; i++) {
			// if the number is composite, it is not prime, and thus we don't want to append
			// it to our list, and any multiple of a composite number is a multiple of its
			// composite factors, so we can safely skip it.
			if (!primeTable[i])
				continue;

			// if we make it here, we can be certain that i is prime
			primes.add(i);

			// any multiple of a prime is a composite number by definition.
			for (int j = i + i; j < limit; j += i)
				primeTable[j] = false;
		}
	}

	/**
	 * Increases the limit to the new limit passed and appends all new primes found
	 * to what has already been found. If a call to this function does not increase
	 * the limit, nothing happens. There is overhead to call this function, so it is
	 * preferable to start the instance with as many primes as you will need.
	 * 
	 * @param newLimit the upper limit, under which, primes will be found and added
	 *                 to the ones already known
	 */
	public void increaseLimit(int newLimit) {
		// no new primes to find
		if (newLimit <= limit)
			return;

		// extend the old sieve into new territory

		int oldLimit = limit;
		limit = newLimit;
		boolean[] sieve = new boolean[limit];
		// copy the data we already know
		for (int i = 0; i < primeTable.length; i++)
			sieve[i] = primeTable[i];

		// extend the already computed sieve-work
		// i.e. erase mulitples of primes in new territory
		for (int i = oldLimit; i < newLimit; i++)
			sieve[i] = true;
		for (int i = 2; i < oldLimit; i++) {
			if (!sieve[i])
				continue;
			// oldLimit - (oldLimit % i) is the largest number under the old limit which is
			// a mulitple of i, so add i to get the first multiple of i above the old limit
			int firstNew = oldLimit - (oldLimit % i) + i;
			for (int j = firstNew; j < newLimit; j++)
				sieve[j] = false;
		}

		// now, sieve using the same algorithm as sievePrimes()
		for (int i = oldLimit; i < limit; i++) {
			if (!sieve[i])
				continue;
			primes.add(i);
			for (int j = i + i; j < limit; j += i)
				sieve[j] = false;
		}

		primeTable = sieve;
	}

	/**
	 * This method determines if p is prime in constant time; the computational work
	 * is done during initialization.
	 * 
	 * @param p the number which may or may not be prime
	 * @return returns true if p is prime, and false if p is composite or p is not
	 *         within the limit
	 */
	public boolean isPrime(int p) {
		if (p < 0 || p >= limit)
			return false;
		return primeTable[p];
	}

	/**
	 * finds all prime factors of n. For example, primeFactorize(12) returns a List
	 * containing {2, 2, 3}
	 * 
	 * @param n the number to be factorized
	 * @return returns a sorted list of all prime factors of n
	 */
	public List<Integer> primeFactorize(int n) {
		List<Integer> factors = new ArrayList<>();

		// this check isn't necessary, but it's constant runtime, and if n was in fact
		// prime, it saves a lot of time
		if (isPrime(n)) {
			factors.add(n);
			return factors;
		}
		for (int i = 0; n != 1; i++) {
			while (n % primes.get(i) == 0) {
				factors.add(i);
				n /= primes.get(i);
			}
		}
		return factors;
	}

	public List<Integer> factorize(int n) {
		/*
		 * TODO there is likely a faster algorithm with combining combinations of prime
		 * factors, becasue there are far fewer primes than total numbers, and
		 * generating combinations is fairly quick as I understand.
		 */
		List<Integer> lowerFactors = new ArrayList<>();
		lowerFactors.add(1);
		// this check isn't necessary, but it's constant runtime, and if n was in fact
		// prime, it saves a lot of time
		if (isPrime(n)) {
			lowerFactors.add(n);
			return lowerFactors;
		}

		List<Integer> upperFactors = new ArrayList<>();
		upperFactors.add(n);
		double sqrt = Math.sqrt(n);
		for (int i = 2; i < sqrt; i++) {
			// if i is a factor, that means that n is an integer multiple of i, <=> n = ik,
			// since k is an integer, k = n / i
			if (n % i == 0) {
				lowerFactors.add(i);
				upperFactors.add(n / i);
			}
		}

		if (EulerTools.isIntegral(sqrt))
			lowerFactors.add((int) sqrt);

		for (int i = upperFactors.size() - 1; i >= 0; i--)
			lowerFactors.add(upperFactors.get(i));

		return lowerFactors;
	}

	public int gcd(int a, int b) {
		// makes sure that a is the smaller number. I want this because, on average,
		// smaller numbers have fewer factors
		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		if (isPrime(a))
			return b % a == 0 ? a : 1;
		if (isPrime(b))
			return 1;

		List<Integer> aFactors = factorize(a);
		List<Integer> bFactors = factorize(b);

		int gcd = 1;

		// compare factors
		// if match, remove it from each and multiply gcd by that removed number

		return gcd;
	}

	public boolean areCoprime(int a, int b) {
		return gcd(a, b) == 1;
	}
}