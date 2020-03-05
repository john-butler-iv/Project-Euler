package projectEuler;

import java.util.Iterator;

class P002 extends ParameterizedProblem {

	@Override
	public long getDefaultParameter() {
		return 4000000;
	}

	@Override
	public long getTestParameter() {
		return 100;
	}
	
	@Override
	public long getTestSolution(){
		return 44;
	}
	
	@Override
	public long solve(long parameter, boolean printResults) {
		Iterator<Long> it = EulerTools.fibonacciIterator();
		it.next();//0
		it.next();//1
		it.next();//1
		it.next();//2
		long sum = 0;
		long curr = 2;
		do {
			sum += curr;

			//every third 
			it.next();
			it.next();
			curr = it.next();
		} while (curr <= parameter);

		if (printResults)
			System.out.println("The sum of the even Fibonacci numbers below "+ parameter + " is " + sum);

		return sum;
	}

	@Override
	public String getTitle() {
		return "Problem 002: Even Fibonacci Numbers";
	}

}