package projectEuler;

import java.util.ArrayList;

class P003 extends ParameterizedProblem<Long> {

	@Override
	public Long getDefaultParameter() {
		return 600851475143L;
	}

	@Override
	public long solve(Long num, boolean printResults) {
		// TODO I can refactor this, but it works and it's quick and I'm too lazy to
		// implement prime stuff for longs
		ArrayList<Long> factors = new ArrayList<Long>();

		while (num > 1) {
			for (long i = 3; i <= num; i += 2) {
				if (num % i == 0) {
					factors.add(i);
					num /= i;
				}
			}
		}

		long big = factors.get(factors.size() - 1);
		if (printResults)
			System.out.println(big + " is the biggest prime factor of 6,008,514,751,43l");
		return big;
	}

	@Override
	protected Long getTestParameter() {
		return 13195L;
	}

	@Override
	protected long getTestSolution() {
		return 29;
	}

	@Override
	public String getTitle() {
		return "Problem 003: Largesst Prime Factor";
	}

}
