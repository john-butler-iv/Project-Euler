package projectEuler;

import java.util.LinkedList;
import java.util.Stack;

public class P073 extends ParameterizedProblem<Integer> {
	
	public class Fraction{
		int num;
		int den;
		public Fraction(int num, int den){
			this.num = num;
			this.den = den;
		}
	}
	public class FractionPair {
		Fraction lessFrac;
		Fraction moreFrac;
		public FractionPair(Fraction lessFrac, Fraction moreFrac){
			this.lessFrac = lessFrac;
			this.moreFrac = moreFrac;
		}
	}

	public Integer getDefaultParameter(){
		return 12000;
	}

	public long solve(Integer maxD, boolean printResults){
		
		Stack<FractionPair> stack = new Stack<>();
		// Fraction is a separate class from FractionPair because it's marginally faster to copy one pointer than
		// two integers, a numerator and denomiator, assuming we're using each fraction more than once.
		// It may end up being equal, but this is easier to read imo.
		stack.add(new FractionPair(new Fraction(1,3), new Fraction(1,2)));

		int totalFracs = 0;
		
		while(!stack.isEmpty()){
			FractionPair currFracs = stack.pop();
			// num1 + num2 / den1 + den2 generates all desired fractions, see writeup for proof of correctness
			// there's no need to calculate the numerator if we know that the 
			int newDen = currFracs.lessFrac.den + currFracs.moreFrac.den;
			if(newDen <= maxD){
				int newNum = currFracs.lessFrac.num + currFracs.moreFrac.num;
				Fraction newFrac = new Fraction(newNum, newDen);
				stack.add(new FractionPair(currFracs.lessFrac, newFrac));
				stack.add(new FractionPair(newFrac, currFracs.moreFrac));
				totalFracs++;
			}
		}
		if(printResults)
			System.out.println("There are " + totalFracs + " fractions between 1/3 and 1/2 whose denominator is <= "
					+ maxD);
		return totalFracs;
	}
	
	public Integer getTestParameter(){
		return 8;
	}

	public long getTestSolution(){
		return 3;
	}

	public String getTitle(){
		return "Problem 073: Counting Fractions in a Range";
	}
}
