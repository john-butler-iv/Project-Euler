package projectEuler;

import java.math.BigInteger;

public class P056 extends Problem{
	
	public static void main(String[] args){
		new P056().solve(true);
	}
	@Override
	public boolean test(){
		return true;
	}

	@Override
	public long solve(boolean printResults){
		int largestDigitalSum = -1;

		for(BigInteger a = BigInteger.ONE; a.intValue() < 100; a = a.add(BigInteger.ONE)) {
			for(int b = 1; b < 100; b++) {

				BigInteger power = a.pow(b);
				String str = power.toString();
				
				// count digits 
				int total = 0;
				for(char ch : str.toCharArray())
					total += ch - '0';

				// check if maximum digital sum
				if(total > largestDigitalSum)
					largestDigitalSum = total;
			}
		}

		if(printResults)
			System.out.println(largestDigitalSum + " is the maximum digital sum of the form a^b for a,b < 100");
		return largestDigitalSum;
	}

	@Override
	public String getTitle(){
		return "Problem 056: Powerful Digit Sum";
	}
}
