package projectEuler;

public class P114 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 50;
	}

	@Override
	public long solve(Integer fieldWidth, boolean printResults) {
		long ways = solveCore(fieldWidth);

		if (printResults) {
			System.out.println("There are " + ways + " ways to fill a row measuring " + fieldWidth + " units.");
		}
		return ways;
	}

	private long solveCore(int fieldWidth) {
		if (fieldWidth <= 0) {
			return 0;
		} else if (fieldWidth <= 2) {
			return 1;
		}

		long ways = 0;
		for (int blockSize = 3; blockSize <= fieldWidth; blockSize++) {
			ways += solveCore(fieldWidth - blockSize);
		}

		return ways;
	}

	@Override
	protected Integer getTestParameter() {
		return 7;
	}

	@Override
	protected long getTestSolution() {
		return 17;
	}

	@Override
	public String getTitle() {
		return "Problem 114: Counting Block Combinations I";
	}

}