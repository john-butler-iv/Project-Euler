package projectEuler;

class P001 extends ParameterizedProblem {
	@Override
	public String getTitle() {
		return "Problem 001: Multiples of 3 and 5";
	}

	@Override
	public long getDefaultParameter() {
		return 1000;
	}

	@Override
	public long getTestParameter() {
		return 10;
	}
	public long getTestSolution(){
		return 23;
	}

	@Override
	long solve(long parameter, boolean printResults) {
		long sum = 0;
		
		for (long i = 1; i < parameter; i++)
			if (i % 3 == 0 || i % 5 == 0)
				sum += i;
		
		if (printResults)
			System.out.println(sum + " is the sum of all numbers below "+parameter+" that are divisible by 3 or 5");

		return sum;
	}
}