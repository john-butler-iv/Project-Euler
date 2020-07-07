package projectEuler;

import java.math.BigInteger;

public class P063 extends Problem {

	@Override
	public long solve(boolean printResults) {
		int total = 0;

		int startingNum = firstLongBase(1, 1);
		int endingNum = firstLongBase(2, 1);

		for(int b = 2; startingNum != -1; b++){
			total += endingNum - startingNum;
			startingNum = firstLongBase(b, b);
			endingNum = firstLongBase(b+1, b);
		}


		if(printResults)
			System.out.println("There are " + total + " n-digit numbers that are also an nth power.");

		return total;
	}

	private static int firstLongBase(int n, int power){
		int a = 1;
		int lowerBound = 1;
		int upperBound = 1;
		while(true){
			if(BigInteger.valueOf(a).pow(power).toString().length() >= n) {
				upperBound = a;
				break;
			}
			lowerBound = a;
			a*=2;
		}
		for(int i = lowerBound; i < upperBound; i++){
			int len = BigInteger.valueOf(i).pow(power).toString().length();
			if(len == n)
				return i-1;
			if(len > n)
				return -1;
		}
		return upperBound-1;
	}

	@Override
	public String getTitle(){
		return "Problem 063: Powerful Digit Counts";
	}
}
