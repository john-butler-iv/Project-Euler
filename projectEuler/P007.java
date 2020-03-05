package projectEuler;

import java.util.List;

class P007 extends ParameterizedProblem {

	@Override
	long getDefaultParameter() {
		return 10001;
	}

	public static void main(String[] args){
		new P007().solve(true);
	}

	@Override
	long solve(long parameter, boolean printResults) {
		/*
		List<Integer> primes = PrimeFinder.findNPrimes((int) parameter);
		//System.out.println(primes);
		*/

		PrimeFinder pf = new PrimeFinder(3 * (int) Math.log(parameter) * (int)parameter);
		List<Integer> primes = pf.getPrimes();

		if (printResults)
			System.out.println("the " + parameter + " prime is " + primes.get((int) parameter-1));
		
		return primes.get((int) parameter-1);
	}

	@Override
	protected long getTestParameter() {
		return 6;
	}

	@Override
	protected long getTestSolution() {
		return 13;
	}

	@Override
	String getTitle() {
		return "Problem 007: 10,001st Prime";
	}

}