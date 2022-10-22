package projectEuler;

import java.util.*;

public class P127 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 120000;
	}

	@Override
	public long solve(Integer maxC, boolean printResults) {
		long sumC = 0;
		PrimeFinder pf = new PrimeFinder(maxC);
		ArrayList<List<Integer>> factorCache = pf.getUniquePrimeFactorCache(maxC);

		List<Integer> aFactors, bFactors, cFactors;

		// runtime: O(n^2 pi(n)) = ~O(n^2 log(n)), where pi(n) # of primes < n
		// in general for this search, we have to find unique prime factors twice (once
		// to check coprime, and once for rad(abc)), so we just grab them once.
		for (int a = 1; a < maxC; a++) {
			aFactors = factorCache.get(a);
			for (int b = a + 1; a + b < maxC; b++) {
				bFactors = factorCache.get(b);
				if (!CollectionTools.areDisjointSorted(aFactors, bFactors))
					continue;

				int c = a + b;
				cFactors = factorCache.get(c);
				if (!CollectionTools.areDisjointSorted(aFactors, cFactors)
						|| !CollectionTools.areDisjointSorted(bFactors, cFactors))
					continue;

				long rad = 1;
				Iterator<Integer> itA = factorCache.get(a).iterator();
				while (itA.hasNext())
					rad *= itA.next();

				Iterator<Integer> itB = factorCache.get(b).iterator();
				while (itB.hasNext())
					rad *= itB.next();

				Iterator<Integer> itC = factorCache.get(c).iterator();
				while (itC.hasNext())
					rad *= itC.next();

				if (rad < c) {
					sumC += c;
				}
			}
		}

		if (printResults) {
			System.out.println("The sum of all \"c\"s less than " + maxC + " is " + sumC);
		}

		return sumC;
	}

	@Override
	protected Integer getTestParameter() {
		return 1000;
	}

	@Override
	protected long getTestSolution() {
		return 12523;
	}

	@Override
	public String getTitle() {
		return "Problem 127: ABC-Hits";
	}

}
