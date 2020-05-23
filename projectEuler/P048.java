package projectEuler;

import java.math.BigInteger;

public class P048 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 1000;
	}

	@Override
	public long solve(Integer maxExponent, boolean printResults) {
		BigInteger sum = BigInteger.ZERO;

		// calculate the sum with no fancy techniques

		// \iff
		// for(int i = 1; i < parameter; i++)
		// sum += Math.pow(i, i);
		for (BigInteger i = BigInteger.ONE; i.intValue() < maxExponent; i = i.add(BigInteger.ONE))
			sum = sum.add(i.pow(i.intValue()));

		// format the answer accordingly
		String ans = sum.toString();
		if (ans.length() > 10)
			ans = ans.substring(ans.length() - 10);

		if (printResults)
			System.out.println(ans + " is the last ten digits of 1^1 + 2^2 + ... + " + maxExponent + "^" + maxExponent);
		return Long.valueOf(ans);
	}

	@Override
	protected Integer getTestParameter() {
		return 10;
	}

	@Override
	protected long getTestSolution() {
		return Long.valueOf("0405071317");
	}

	@Override
	public String getTitle() {
		return "Problem 048: Self powers";
	}

}
