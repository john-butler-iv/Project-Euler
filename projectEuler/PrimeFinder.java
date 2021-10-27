package projectEuler;

import java.util.ArrayList;
import java.util.List;

class PrimeFinder {
	private boolean[] primeTable;
	private List<Integer> primes;
	private LinkedHashSet<Integer> primeHash;
	private int limit;

	/**
	 * Finds all primes less than limit, non-inclusive. The most this algorithm can
	 * generate under 1ms on my system is about 1.6 million. [out of date]
	 * 
	 * @param limit the upper-limit on which primes will be found, non-inclusive
	 */
	public PrimeFinder(int limit) {
		if (limit <= 0)
			limit = 1;

		primeHash = new LinkedHashSet<Integer>();
		//primeTable = new boolean[limit];
		primes = new ArrayList<>();
		this.limit = limit;
		sieveLinkedPrimes();

		for(int i = 0; i < primes.size(); i++){
			System.out.println(primes.get(i));
		} 
	}

	/**
	 * [DEPRICATED]
	 * This method is the one that actually finds all primes under limit, and
	 * assigns the proper values to primeTable e and primes. I believe this is the
	 * fastest known algorithm to find primes, but I could be mistaken. 
	 * This implimentation of the algorithm has O(n sqrt n) time complexity (optimized from O(n^2)) and 
	 * O(n) memory complexity.
	 */
	private void sievePrimes() {
		// assume every number is prime unless we find factors
		for (int i = 2; i < limit; i++){
			primeTable[i] = true;
		}
		
		// suppose we have p,q > sqrt(limit). wlog, additionally assume p <= q
		// therefore pq > sqrt(limit) * sqrt(limit) = limit
		// therefore p < sqrt(limit) or (p <= ) q < sqrt(limit),
		// therefore i=p at some point, and we will have eliminated pq. 
		// Any searching beyond sqrt(limit) is unfruitful.

		// edge case: if pq = sqrt(limit), then we eliminated it when i=p<sqrt(limit)
		int calcLimit = (int) Math.sqrt(limit);
		for (int i = 2; i < calcLimit; i++) {
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

		// don't need to propigate value to factors, but still need to find the primes left behind
		for(int i = calcLimit + 1 - calcLimit % 2; i < limit; i+=2){
			if(primeTable[i]) primes.add(i);
		}
	}

	private void sieveLinkedPrimes(){
		for(int i = 2; i < limit; i++)
			primeHash.add(i);
		

		int calcLimit = (int) Math.sqrt(limit);
		LinkedHashEntry<Integer> entry = primeHash.head;
		while(entry.val < calcLimit) {
			primeHash.add(entry.val);

			for(int i = entry.val * 2; i < limit; i += entry.val)
				primeHash.remove(i);
			
			entry = entry.next;
		}

		primes = primeHash.keys();
	}

	/**
	 * Increases the limit to the new limit passed and appends all new primes found
	 * to what has already been found. If a call to this function does not increase
	 * the limit, nothing happens. There is overhead to call this function, so it is
	 * preferable to start the instance with as many primes as you will need.
	 * TODO refactor to only run up to sqrt and gather primes from old limit
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
	 * This method determines if p is prime in constant time if p < limit, or in
	 * O(sqrt(p)) time if p >= limit.
	 * 
	 * @param p the number which may or may not be prime
	 * @return returns true if p is prime, and false if p is composite or p is not
	 *         within the limit
	 */
	public boolean isPrime(int p) {
		// negative numbers cannot be prime, and 0 and 1 are not prime.
		if (p <= 1)
			return false;

		// if we have the value in the table, we can just look it up.
		if (p < limit)
			return primeTable[p];

		// If we reach this point, we are forced to check the factors of p, but we'll
		// only have to check up to sqrt(p), as if p has any factors, there will be one
		// less than sqrt(p)
		int sqrt = (int) Math.sqrt(p);

		for (int prime : primes) {
			// this is the best way I could think of for only checking as far as we need and
			// not going out of bounds if p is too large.
			if (sqrt < prime)
				break;
			// obviously, if a prime divides p, p is not prime.
			// if a composite divides p, then its prime factors will aswell.
			if (p % prime == 0)
				return false;
		}

		// if there are still potential factors we have not considered,
		if (sqrt >= limit)
			// we don't know if any number past limit is prime, so we'll just check every
			// number
			for (int n = limit; n < sqrt; n++)
				if (p % n == 0)
					return false;

		// if we have not found any factors, p must be prime.
		return true;
	}

	/**
	 * finds all prime factors of n. For example, primeFactorize(12) returns a List
	 * containing {2, 2, 3}. If n has any remaining factors beyond the limit, the
	 * prodcuct of the remaining factors are appended to the list. If the product
	 * does not fit in an int, then it is not included.
	 * 
	 * @param n the number to be factorized
	 * @return returns a sorted list of all prime factors of n
	 */

	public List<Integer> primeFactorize(long n) {
		List<Integer> factors = new ArrayList<>();

		// If n is prime, it saves a lot of time
		if (n < limit && isPrime((int) n)) {
			factors.add((int) n);
			return factors;
		}

		for (int prime : primes) {
			while (n % prime == 0) {
				factors.add(prime);
				n /= prime;
			}
			// we can return early if acceptable
			if (n == 1)
				break;
		}
		// if we don't remove all factors, there must be one beyond the scope of our
		// search, so we add it if it fits in an int, otherwise, we ignore it.
		if (n != 1 && n <= Integer.MAX_VALUE)
			factors.add((int) n);
		return factors;
	}

	/**
	 * finds all unique prime factors of n. For example, primeFactorize(12) returns
	 * a List containing {2, 3}
	 * 
	 * @param n the number to be factorized
	 * @return returns a sorted list of all unique prime factors of n
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
			if (n % primes.get(i) == 0) {
				factors.add(primes.get(i));
				// this is so we don't add factors more than once, and it's written like this
				// because we already checked the condition
				do
					n /= primes.get(i);
				while (n % primes.get(i) == 0);
			}
		}
		return factors;
	}

	/**
	 * Uses a somewhat optimized algorithm with time complexity O(sqrt(n)). Finds
	 * all factors of n and returns them in an ascending list
	 * 
	 * @param n the number to be factorized
	 * @return returns a list of the factors of n in ascending order
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

	/**
	 * This function finds the greatest common demonator between a and b
	 * 
	 * @param a one of the numbers compared
	 * @param b one of the numbers compared
	 * @return returns the greatest common denomonator between a and b
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
	 * @return returns true if a and b are coprime, false otherwise
	 */
	public boolean areCoprime(int a, int b) {
		// TODO refactor to "short circuit" when find a factor, like ^^^ for the long
		// version
		return gcd(a, b) == 1;
	}

	/**
	 * returns the value of Euler's totient function of n. That is it finds how many
	 * numbers are coprime with n, below n
	 * 
	 * @param n the input value
	 * @return returns φ(n) <=> how many numbers below n are coprime with n
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
	 * @return returns the number of divisors of n
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
	 * @return returns σ(n)
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
	 * @return returns the limit to which primes have been found
	 */
	public int limit() {
		return limit;
	}

	/**
	 * a getter for the primes found
	 * 
	 * @return returns all primes less than the limit
	 */
	List<Integer> getPrimes() {
		return primes;
	}

	/**
	 * returns a table where getPrimeTable()[i] is true iff i is prime
	 * 
	 * @return returns a table to look up if a number is prime
	 */
	boolean[] getPrimeTable() {
		return primeTable;
	}
}
