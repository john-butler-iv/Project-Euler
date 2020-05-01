package projectEuler;

import java.math.BigInteger;

class P020 extends ParameterizedProblem<Integer> {

	@Override
	Integer getDefaultParameter() {
		return 100;
	}

	@Override
	long solve(Integer n, boolean printResults) {
		BigInteger fact = EulerTools.factorial(BigInteger.valueOf(n));
		String str = fact.toString();

		int sum = 0;
		for (char c : str.toCharArray())
			sum += c - '0';

		if (printResults)
			System.out.println(sum + " is the sum of " + n + "!.");

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
	String getTitle() {
		return "Problem 020: Factorial Digit Sum";
	}

}