package projectEuler;

import java.math.MathContext;
import java.math.BigDecimal;

public class P064 extends ParameterizedProblem<Integer> {
	@Override
	public Integer getDefaultParameter(){
		return 10000;
	}

	@Override
	public long solve(Integer maxN, boolean printResults){
		// what I put here doesn't matter too much because it get dynamically updated based on precision needed.
		// With that said, I did pick the number so that it doesn't we don't end up with wildly more precision
		// than we need in the end
		MathContext sqrtPrecision = new MathContext(27);

		int oddPeriods = 0;

		BigDecimal num;
		BigDecimal den;
		int periodLen;

		int lastSquare = 1;

		for(int i = 2; i <= maxN; i++){
			//these are very minor improvements because these are so short anyways
			if(EulerTools.isSquare(i)){
				lastSquare = 1;
				continue;
			} else if(lastSquare == 1){
				oddPeriods++;
				lastSquare++;
				continue;
			} else if(lastSquare == 2) {
				lastSquare++;
				continue;
			} else if(EulerTools.isSquare(i+1))
				continue;



			//reinitialize variables
			int a0 = (int) Math.sqrt(i);

			num = BigDecimal.ONE;
			den = new BigDecimal(i).sqrt(sqrtPrecision).subtract(new BigDecimal(a0));

			periodLen = 1;


			try {
				//the last number in the period is always 2 * a0
				while(num.divideToIntegralValue(den).intValue() < a0 * 2) {

					int an = num.divideToIntegralValue(den).intValue();


					BigDecimal newNum = den;

					// $ newDen = num - a_n \cdot oldDen $
					BigDecimal newDen = num.subtract(BigDecimal.valueOf(an).multiply(den));

					num = newNum;
					den = newDen;

					periodLen++;
				}

				// if loss from precision, make sure there's enough
				if(num.divideToIntegralValue(den).intValue() != 2 * a0)
					throw new Exception();


				if(periodLen % 2 == 1)
					oddPeriods++;

			} catch(Exception e){
				//increase precision until it's precise enough
				sqrtPrecision = new MathContext(sqrtPrecision.getPrecision() * 2);
				//retry
				i--;
			}
		}
		System.out.println(sqrtPrecision.getPrecision());

		if(printResults)
			System.out.println(oddPeriods + " continued fractions for N <= " +maxN + " have an odd period");
		return oddPeriods;
	}

	@Override
	public Integer getTestParameter(){
		return 13;
	}

	@Override
	public long getTestSolution(){
		return 4;
	}

	@Override
	public String getTitle(){
		return "Problem 064: Odd Period Square Roots";
	}
}
