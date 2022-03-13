package projectEuler;

import java.util.*;

public class P108 extends ParameterizedProblem<Integer> {
	private static class PrimePower {
		int prime;
		int power;

		PrimePower(int prime) {
			this.prime = prime;
			this.power = 2;
		}

		PrimePower(int prime, int power) {
			this.prime = prime;
			this.power = power;
		}
	}

	@Override
	public Integer getDefaultParameter() {
		return 1000;
	}

	public long solve(Integer solutions, boolean printResults) {
		PrimeFinder pf = new PrimeFinder(solutions * 50);
		List<Integer> factors;
		PrimePower lastFactor;

		int best = 0;
		int uniqPts = 0;
		long n = solutions;
		int divisors;

		while (uniqPts <= solutions) {
			n++;
			uniqPts = 0;
			divisors = 1;
			lastFactor = new PrimePower(1, 0);

			factors = pf.primeFactorize(n);
			for (int factor : factors) {
				if (factor != lastFactor.prime) {
					divisors *= lastFactor.power + 1;
					lastFactor = new PrimePower(factor);
				} else {
					lastFactor.power += 2;
				}
			}

			divisors *= lastFactor.power + 1;

			uniqPts = (int) Math.ceil(divisors / 2.0);

			if (n < 0 || uniqPts <= 0 || n > solutions * 50) {
				System.out.println("uh oh " + n + " " + uniqPts + " (" + (solutions * 50) + ")");
			}
			if (uniqPts >= best) {
				best = uniqPts;
				System.out.println("Best so far: " + n + " " + best);
			}
		}

		if (printResults)
			System.out.println(
					n + " is the least value of n whose numbe of distinct solutions first exceeds " + solutions);

		return n;
	}

	@Override
	protected Integer getTestParameter() {
		return 2;
	}

	@Override
	protected long getTestSolution() {
		return 4;
	}

	@Override
	public String getTitle() {
		return "Problem 108: Diophantine Reciprocals I";
	}

}
