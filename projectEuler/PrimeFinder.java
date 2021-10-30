package projectEuler;

import java.util.*;

class PrimeFinder {
	private class Prime {
		Prime prev;
		Prime next;
		int val;

		public Prime(int val) {
			prev = null;
			next = null;
			this.val = val;
		}
	}

	private class PrimePower {
		int prime;
		int power;

		public PrimePower(int prime, int power) {
			this.prime = prime;
			this.power = power;
		}

		public PrimePower(int prime) {
			this.prime = prime;
			this.power = 1;
		}
	}

	private Prime[] primes;
	private Prime head;
	private Prime tail;
	private List<Integer> primeList;
	private int limit;

	public PrimeFinder(int limit) {
		this(limit, true);
	}

	public PrimeFinder(int limit, boolean collectPrimes) {
		this.primes = new Prime[limit];
		this.limit = Math.max(limit, 2);

		head = new Prime(2);
		tail = head;

		sievePrimes();
		if (collectPrimes)
			collectPrimes();
	}

	private void sievePrimes() {
		for (int i = 3; i < limit; i++) {
			add(i);
		}

		Prime tracer = head;
		int calcLimit = (int) Math.sqrt(limit);
		while (tracer.val < calcLimit) {
			for (int j = 2 * tracer.val; j < limit; j += tracer.val) {
				remove(j);
			}

			tracer = tracer.next;
		}
	}

	private void add(int i) {
		Prime entry = new Prime(i);
		tail.next = entry;
		entry.prev = tail;
		tail = entry;
		primes[i] = entry;
	}

	private void remove(int i) {
		if (primes[i] == null)
			return;

		primes[i].prev.next = primes[i].next;
		if (primes[i].next == null)
			tail = tail.prev;
		else
			primes[i].next.prev = primes[i].prev;

		primes[i] = null;
	}

	private void collectPrimes() {
		primeList = new ArrayList<Integer>();
		Prime tracer = head;
		while (tracer != null) {
			primeList.add(tracer.val);
			tracer = tracer.next;
		}
	}

	public boolean isPrime(long n) {
		if (n < limit)
			return primes[(int) n] == null;
		if (n >= limit * (long) limit) {
			// can only definitively say a number is prime under this limit, but we'll try
			// our best otherwise
		}
		//
		for (int prime : primeList)
			if (n % prime == 0)
				return false;
		return true;
	}

	/**
	 * finds all prime factors of n. For example, primeFactorize(12) returns a List
	 * containing {2, 2, 3}. If n has any remaining factors beyond the limit, the
	 * prodcuct of the remaining factors are appended to the list. If the product
	 * does not fit in an int, then it is not included.
	 * 
	 * @param n the number to be factorized
	 * @return a sorted list of all prime factors of n
	 */

	public List<Integer> primeFactorize(long n) {
		List<Integer> factors = new ArrayList<>();

		// If n is prime, this check saves a lot of time
		if (n < limit && isPrime((int) n)) {
			factors.add((int) n);
			return factors;
		}

		for (int prime : primeList) {
			while (n % prime == 0) {
				factors.add(prime);
				n /= prime;
			}
			// we can return early if no more factors
			if (n == 1)
				break;
		}
		// if we don't remove all factors, there must be at loast one beyond the scope
		// of our search, so we add it if it fits in an int, otherwise, we ignore it.
		if (n != 1 && n <= Integer.MAX_VALUE)
			factors.add((int) n);
		return factors;
	}

	/**
	 * Finds all prime factors of n. For example, primeFactorizeInternal(12) returns
	 * [{prime: 2, power: 2}, {prime: 3,power 1}]
	 * 
	 * @param n the number to be factorized
	 * @return the prime factors of n
	 */
	private List<PrimePower> primeFactorizeInternal(int n) {
		List<PrimePower> factors = new ArrayList<>();

		if (isPrime(n)) {
			factors.add(new PrimePower(n));
			return factors;
		}

		for (int i = 0; n > 1; i++) {
			int power = 0;
			while (n % primeList.get(i) == 0) {
				power++;
				n /= primeList.get(i);
			}
			if (power > 0) {
				factors.add(new PrimePower(primeList.get(i), power));
			}
		}
		return factors;
	}

	/**
	 * finds all unique prime factors of n. For example, primeFactorize(12) returns
	 * a List containing {2, 3}
	 * 
	 * @param n the number to be factorized
	 * @return a sorted list of all unique prime factors of n
	 */
	public List<Integer> uniquePrimeFactorize(int n) {
		List<Integer> factors = new ArrayList<>();

		// this check isn't necessary, but it's constant runtime, and if n was in fact
		// prime, it saves a lot of time
		if (isPrime(n)) {
			factors.add(n);
			return factors;
		}
		for (int i = 0; n != 1; i++) {
			if (n % primeList.get(i) == 0) {
				factors.add(primeList.get(i));
				// this is so we don't add factors more than once, and it's written like this
				// because we already checked the condition
				do
					n /= primeList.get(i);
				while (n % primeList.get(i) == 0);
			}
		}
		return factors;
	}

	/**
	 * Uses a somewhat optimized algorithm with time complexity O(sqrt(n)). Finds
	 * all factors of n, including 1 and n, and returns them in an ascending list
	 * 
	 * @param n the number to be factorized
	 * @return a list of the factors of n in ascending order
	 */
	public List<Integer> factorize(int n) {
		/*
		 * TODO there is likely a faster algorithm with combining prime factors, becasue
		 * there are far fewer primes than total numbers, and generating combinations is
		 * fairly quick as I understand.
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
		int limit = (int) Math.ceil(sqrt);
		for (int i = 2; i < limit; i++) {
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

	/**
	 * This function finds the greatest common demonator between a and b
	 * 
	 * @param a one of the numbers compared
	 * @param b one of the numbers compared
	 * @return the greatest common denomonator between a and b
	 */
	public int gcd(int a, int b) {
		if (a == 1 || b == 1)
			return 1;
		if (a == 0)
			return b;
		if (b == 0)
			return a;

		List<Integer> aFactors = primeFactorize(a);
		List<Integer> bFactors = primeFactorize(b);

		int gcd = 1;

		for (int i = 0; i < aFactors.size(); i++) {
			for (int j = 0; j < bFactors.size(); j++) {

				if (aFactors.get(i).equals(bFactors.get(j))) {
					gcd *= aFactors.get(i);
					// removing the factor outright is slow, so I instead set it to something not in
					// a
					bFactors.set(j, -1);
					// by breaking, we do not worry "re-treading old ground" because we will only
					// increase in the size of factors
					break;
				}
			}
		}

		return gcd;
	}

	public boolean areCoprime(long a, long b) {
		if (a == 1 || b == 1)
			return true;
		if (a == 0)
			return b == 1;
		if (b == 0)
			return a == 1;

		List<Integer> aFactors = primeFactorize(a);
		List<Integer> bFactors = primeFactorize(b);

		for (int i = 0; i < aFactors.size(); i++) {
			for (int j = 0; j < bFactors.size(); j++) {
				if (aFactors.get(i).equals(bFactors.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Determines whether a and b are coprime
	 * 
	 * @param a one potentially coprime number
	 * @param b one potentially coprime number
	 * @return true if a and b are coprime, false otherwise
	 */
	public boolean areCoprime(int a, int b) {
		// TODO refactor to "short circuit" when find a factor in common, like ^^^ for
		// the long
		// version
		return gcd(a, b) == 1;
	}

	/**
	 * returns the value of Euler's totient function of n. That is it finds how many
	 * numbers are coprime with n, below n
	 * 
	 * @param n the input value
	 * @return φ(n) <=> how many numbers below n are coprime with n
	 */
	public int totient(int n) {
		// there can be overflow, so I convert to a long before converting back
		long totientValue = n;
		for (int prime : uniquePrimeFactorize(n)) {
			totientValue *= prime - 1;
			totientValue /= prime;
		}
		return (int) totientValue;
	}

	/**
	 * Computes all totients from 1 to n-1, inclusive using a sieve algorithm. This
	 * is faster than repeatedly using totient(int). As a result, if you need all
	 * totient values, use this method instead. static totientTable(n)[i] =
	 * totient(i) for all i.
	 *
	 * @param n the limit to what totients are calculated
	 */
	public static int[] totientTable(int n) {
		int[] totientTable = new int[n];
		for (int i = 1; i < totientTable.length; i++)
			totientTable[i] = i;

		for (int i = 2; i < totientTable.length; i++) {
			// check if i is prime
			if (totientTable[i] == i) {
				// totient(prime) = prime - 1
				totientTable[i]--;

				for (int j = 2 * i; j < totientTable.length; j += i) {
					// there can be overflow here, so I convert to a long to ensure that doesn't
					// happen.
					long updatedTotient = totientTable[j] * (long) (i - 1);
					updatedTotient /= i;

					totientTable[j] = (int) updatedTotient;
				}
			}
		}

		return totientTable;
	}

	/**
	 * counts the number of divisors of n, including 1 and n
	 * 
	 * @param n a positive integer with some number of divisors
	 * @return the number of divisors of n
	 */
	public int divisors(int n) {
		List<Integer> factors = primeFactorize(n);
		int divisors = 1;

		for (int i = 0; i < factors.size();) {
			int numFactor = 1;

			// count how much the current factor divides n
			for (int j = i + 1; j < factors.size() && factors.get(j) == factors.get(i); j++)
				numFactor++;

			divisors *= numFactor + 1;

			// skip past all of the current factors
			i += numFactor;
		}
		return divisors;
	}

	/**
	 * Finds σ(n), that is, this function finds the sum of all divisors of n,
	 * including n itself
	 * 
	 * @param n the input to σ(n)
	 * @return σ(n)
	 */
	public int sigma(int n) {
		List<Integer> factors = factorize(n);
		int sum = 0;

		for (int factor : factors)
			sum += factor;

		return sum;
	}

	public int[] reduce(int a, int b) {
		int gcd = gcd(a, b);
		return new int[] { a / gcd, b / gcd };
	}

	public int[] reduce(int[] frac) {
		return reduce(frac[0], frac[1]);
	}

	/**
	 * a getter for the current limit
	 * 
	 * @return the limit to which primes have been found
	 */
	public int limit() {
		return limit;
	}

	/**
	 * a getter for the primes found
	 * 
	 * @return all primes less than the limit
	 */
	List<Integer> getPrimes() {
		return primeList;
	}
}