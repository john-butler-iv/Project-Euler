package projectEuler;

import java.math.BigInteger;

class P016 extends ParameterizedProblem<Integer> {

	@Override
	Integer getDefaultParameter() {
		return 1000;
	}

	@Override
	long solve(Integer parameter, boolean printResults) {
		BigInteger largeNumber = EulerTools.TWO.pow(parameter);
		String str = largeNumber.toString();

		int sum = 0;

		for (int i = 0; i < str.length(); i++)
			sum += str.charAt(i) - '0';

		if (printResults)
			System.out.println(sum + " is the sum of the digits of 2^" + parameter);

		return sum;
	}

	@Override
	protected Integer getTestParameter() {
		return 15;
	}

	@Override
	protected long getTestSolution() {
		return 26;
	}

	@Override
	String getTitle() {
		return "Problem 016: Power Digit Sum";
	}

}