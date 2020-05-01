package projectEuler;

import java.util.Iterator;

class P002 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 4000000;
	}

	@Override
	public Integer getTestParameter() {
		return 100;
	}

	@Override
	public long getTestSolution() {
		return 44;
	}

	@Override
	public long solve(Integer maxValue, boolean printResults) {
		Iterator<Integer> it = EulerTools.fibonacciIterator();

		// we only we know 2 is the first even Fibonacci number, so we just don't check
		// until then
		it.next(); // 0
		it.next(); // 1
		it.next(); // 1
		it.next(); // 2
		int sum = 0;

		// technically I can make this into a for loop, but it would be kinda messy, so
		// I don't do it
		int curr = 2;
		do {
			sum += curr;

			// only every third Fibonacci number is even, see writeup for proof.
			// This means that we don't have to directly check if its even
			it.next();
			it.next();
			curr = it.next();
		} while (curr < maxValue);

		if (printResults)
			System.out.println("The sum of the even Fibonacci numbers below " + maxValue + " is " + sum);

		return sum;
	}

	@Override
	public String getTitle() {
		return "Problem 002: Even Fibonacci Numbers";
	}

}