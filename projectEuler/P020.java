package projectEuler;

import java.math.BigInteger;

class P020 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 100;
	}

	@Override
	public long solve(Integer n, boolean printResults) {
		BigInteger fact = EulerTools.factorial(n);
		String str = fact.toString();

		int sum = 0;
		for (char c : str.toCharArray())
			sum += c - '0';

		if (printResults)
			System.out.println(sum + " is the sum of the digits of " + n + "!.");

		return sum;
	}

	@Override
	protected Integer getTestParameter() {
		return 10;
	}

	@Override
	protected long getTestSolution() {
		return 27;
	}

	@Override
	public String getTitle() {
		return "Problem 020: Factorial Digit Sum";
	}

}
