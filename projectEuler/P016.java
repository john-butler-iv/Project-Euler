package projectEuler;

import java.math.BigInteger;

class P016 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 1000;
	}

	@Override
	public long solve(Integer exponent, boolean printResults) {
		BigInteger largeNumber = BigInteger.TWO.pow(exponent);
		String str = largeNumber.toString();

		int sum = 0;

		for (int i = 0; i < str.length(); i++)
			sum += str.charAt(i) - '0';

		if (printResults)
			System.out.println(sum + " is the sum of the digits of 2^" + exponent);

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
	public String getTitle() {
		return "Problem 016: Power Digit Sum";
	}

}
