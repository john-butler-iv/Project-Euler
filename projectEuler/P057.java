package projectEuler;

import java.math.BigInteger;

public class P057 extends ParameterizedProblem<Integer>{

	@Override
	public Integer getDefaultParameter(){
		return 1000;
	}

	@Override
	public long solve(Integer maxExpansions, boolean printResults){
		int bigNumCnt = 0;

		BigInteger num = BigInteger.ONE;
		BigInteger den = BigInteger.TWO;

		for(int i = 1; i < maxExpansions; i++){
			//see write up for algorithm
			
			// figure out the next expansion
			BigInteger newDen = num.add(EulerTools.TWO.multiply(den));
			num = den;
			den = newDen;

			// compare the number of digits 
			if(num.add(den).toString().length() > den.toString().length())
				bigNumCnt++;
		}

		if(printResults)
			System.out.println("In the first " + maxExpansions + " exapansions, " + bigNumCnt + " have a numerator with more digits than the denominator.");
		return bigNumCnt;
	}

	@Override
	public Integer getTestParameter(){
		return 8;
	}

	@Override
	public long getTestSolution(){
		return 1;
	}

	@Override
	public String getTitle(){
		return "Problem 057: Square Root Convergents";
	}
}
