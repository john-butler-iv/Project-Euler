package projectEuler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;

public class P066 extends ParameterizedProblem<Integer> {
	private static class ReturnClass {
		ArrayList<Integer> continuedFraction;
		MathContext precision;

		public ReturnClass(ArrayList<Integer> cf, MathContext pre){
			continuedFraction = cf;
			precision = pre;
		}
	}

	@Override
	public Integer getDefaultParameter(){
		return 1000;
	}

	@Override
	public long solve(Integer maxD, boolean printResults){
		BigInteger largestX = BigInteger.ZERO;
		int largestD = 0;

		MathContext precision = new MathContext(10);

		for(int D = 2; D <= maxD; D++){
			ReturnClass returnVar = findCF(D, precision);
			// this is how I'm skipping squares
			if(returnVar == null)
				continue;
			precision = returnVar.precision;

			ArrayList<Integer> continuedFraction = returnVar.continuedFraction;

			// required for correct soln. I don't quite understand it
			if((continuedFraction.size() - 1) % 2 == 1){
				// ad an extra period
				int originalSize = continuedFraction.size();
				for(int j = 1; j < originalSize; j++)
					continuedFraction.add(continuedFraction.get(j));
			}

			//get rid of last item
			continuedFraction.remove(continuedFraction.size() - 1);

			// in form x / y
			BigInteger[] frac = computeCF(continuedFraction);
			if(frac[0].compareTo(largestX) > 0) {
				largestX = frac[0];
				largestD = D;
			}
		}

		if(printResults)
			System.out.println("the largest minimal value of x is obtained when D = " + largestD + " for D <= "
					+ maxD);
		return largestD;
	}

	private static BigInteger[] computeCF(ArrayList<Integer> cf) {
		if(cf == null)
			return null;

		// jump start algorithm
		BigInteger num = BigInteger.ONE;
		BigInteger den = BigInteger.valueOf(cf.get(cf.size() - 1));

		for(int i = cf.size() - 2; i >= 0; i--){
			BigInteger temp = den.multiply(BigInteger.valueOf(cf.get(i))).add(num);
			num = den;
			den = temp;
		}

		return new BigInteger[]{den, num};
	}

	private static ReturnClass findCF(int D, MathContext precision){
		ArrayList<Integer> continuedFraction = new ArrayList<>();
		int a0 = (int) Math.sqrt(D);
		continuedFraction.add(a0);


		// these are very minor improvements because these are so short anyways,
		// but these are one's I found that do follow a pattern
		if(EulerTools.isSquare(D)) {
			return null;
		} else if(EulerTools.isSquare(D - 1)) {
			continuedFraction.add(a0 * 2);
			return new ReturnClass(continuedFraction, precision);
		} else if(EulerTools.isSquare(D - 2)) {
			continuedFraction.add(a0);
			continuedFraction.add(2 * a0);
			return new ReturnClass(continuedFraction, precision);
		} else if(EulerTools.isSquare(D + 1)) {
			continuedFraction.add(1);
			continuedFraction.add(2 * a0);
			return new ReturnClass(continuedFraction, precision);
		}
		
		// these are very minor improvements because these are so short anyways,
		// but these are one's I found that do follow a pattern
		if(EulerTools.isSquare(D)) {
			return null;
		} else if(EulerTools.isSquare(D - 1)) {
			continuedFraction.add(a0 * 2);
			return new ReturnClass(continuedFraction, precision);
		} else if(EulerTools.isSquare(D - 2)) {
			continuedFraction.add(a0);
			continuedFraction.add(2 * a0);
			return new ReturnClass(continuedFraction, precision);
		} else if(EulerTools.isSquare(D + 1)) {
			continuedFraction.add(1);
			continuedFraction.add(2 * a0);
			return new ReturnClass(continuedFraction, precision);
		}


		//reinitialize variables
		while(true) {

			BigDecimal num = BigDecimal.ONE;
			BigDecimal den = BigDecimal.valueOf(D).sqrt(precision).subtract(BigDecimal.valueOf(a0));

			// catching errors of final number is not 2*a_0, which isn't something that's possible, and div by 0
			// both of these are indicators that the precision is off
			try {
				//the last number in the period is always 2 * a0
				while(num.divideToIntegralValue(den).intValue() < a0 * 2) {

					int an = num.divideToIntegralValue(den).intValue();
					continuedFraction.add(an);

					BigDecimal temp = num.subtract(BigDecimal.valueOf(an).multiply(den));
					num = den;
					den = temp;
				}
				
				// if lost precision
				if(num.divideToIntegralValue(den).intValue() != 2 * a0)
					throw new Exception();

				continuedFraction.add(2 * a0);

				return new ReturnClass(continuedFraction, precision);


			} catch(Exception e) {
				//increase precision until it's precise enough
				precision = new MathContext(precision.getPrecision() * 2);
				//retry
				continuedFraction.clear();
				continuedFraction.add(a0);
			}
		}
	}

	@Override
	public Integer getTestParameter(){
		return 7;
	}

	@Override
	public long getTestSolution(){
		return 5;
	}

	@Override
	public String getTitle(){
		return "Problem 066: Diophantine Equation";
	}
}
