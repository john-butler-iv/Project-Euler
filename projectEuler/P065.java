package projectEuler; 

import java.math.BigInteger;

public class P065 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter(){
		return 100;
	}

	@Override
	public long solve(Integer convergents, boolean printResults){

		// initialize the numerator and denominator with the last convergent of e we care about
		BigInteger num = BigInteger.ONE;
		BigInteger den = BigInteger.ONE;

		if((convergents - 1) % 3 == 2)
			den = BigInteger.valueOf(2 * (convergents + 1) / 3);

		// find/compute the rest
		for(int i = convergents - 2; i > 0; i--){

			// find the next number in e's continued fraction
			BigInteger next = BigInteger.ONE;

			if(i % 3 == 2)
				next = BigInteger.valueOf(2*(i + 2) / 3);


			BigInteger temp = den.multiply(next).add(num);
			num = den;
			den = temp;
			
		}

		// we end with adding a constant two at the end
		num = num.add(den.multiply(BigInteger.TWO));
		
		// find the digital sum
		int sum = 0;
		for(char ch : num.toString().toCharArray())
			sum += ch - '0';


		if(printResults)
			System.out.println(sum  + " is the digitial sum of the numerator of the " + convergents +
					" convergent of the continued fraction of e.");
		return sum;
	}

	@Override
	public Integer getTestParameter(){
		return 10;
	}

	@Override
	public long getTestSolution(){
		return 17;
	}
	@Override
	public String getTitle(){
		return "Problem 065: Convergents of e";
	}
}
