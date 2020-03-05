package projectEuler;

import java.util.ArrayList;

class P003 extends ParameterizedProblem {

	@Override
	long getDefaultParameter() {
		return 600851475143L;
	}

	@Override
	long solve(long parameter, boolean printResults) {
		// TODO I can refactor this, but it works and it's quick and I'm too lazy to
		// implement prime stuff for longs
		ArrayList<Long> factors = new ArrayList<Long>();

		while (parameter > 1) {
			for (long i = 3; i <= parameter; i += 2) {
				if (parameter % i == 0) {
					factors.add(i);
					parameter /= i;
				}
			}
		}
		long big = factors.get(factors.size() - 1);
		if(printResults)
			System.out.println(big + " is the biggest prime factor of 6,008,514,751,43l");
		return big;
	}

	@Override
	protected long getTestParameter() {
		return 13195L;
	}

	@Override
	protected long getTestSolution() {
		return 29;
	}

	@Override
	String getTitle() {
		return "Problem 003: Largesst Prime Factor";
	}

}