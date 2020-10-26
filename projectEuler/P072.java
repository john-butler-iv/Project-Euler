package projectEuler;

import java.util.Stack;

public class P072 extends ParameterizedProblem<Integer> {
	
	public Integer getDefaultParameter(){
		return 1000000;
	}

	public long solve(Integer maxD, boolean printResults){
		int[] totientTable = PrimeFinder.totientTable(maxD+1);

		// the number of reduced proper fractions for any denominator d is the number of fractions which don't 
		// reduce. The number of fractions that reduce are those that share a factor with d, so what's left is
		// exactly the numbers coprime with d.
		long numFracs = 0;
		for(int d = 2; d <= maxD; d++)
			numFracs += totientTable[d];
		
		if(printResults)
			System.out.println("There are " + numFracs + 
					" reduced proper fractions whose denominator is <= " + maxD);
		return numFracs;
		
	}
	
	public Integer getTestParameter(){
		return 8;
	}

	public long getTestSolution(){
		return 21;
	}

	public String getTitle(){
		return "Problem 072: Counting Fractions";
	}
}
