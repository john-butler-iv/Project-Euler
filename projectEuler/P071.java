package projectEuler;

public class P071 extends ParameterizedProblem<Integer> {
	private final double GOAL = 3.0 / 7.0;

	@Override
	public Integer getDefaultParameter(){
		return 1000000;
	}

	@Override
	public long solve(Integer maxD, boolean printResults){
		int[] bestFrac = new int[]{0, 1};
		double bestDouble = 0.0;

		int n = 0;
		double currentDouble = 0.0;
		for(int d = 2; d <= maxD; d++){
			
			currentDouble = n / (double) d;

			while(currentDouble < GOAL) {
				if(currentDouble > bestDouble) {
					bestFrac = new int[] {n, d};
					bestDouble = currentDouble;
				}
				n++;
				currentDouble = n / (double) d;
			}
		}
		if(printResults)
			System.out.println(bestFrac[0] + " / " + bestFrac[1] + " is the greatest faction smaller than "
					+ "3 / 7, whose denonimator is <= " + maxD);
		return bestFrac[0];
	}

	@Override
	public Integer getTestParameter(){
		return 8;
	}

	@Override
	public long getTestSolution(){
		return 2;
	}

	@Override
	public String getTitle() {
		return "Problem 071: Ordered Fractions";
	}
}
