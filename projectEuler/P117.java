package projectEuler;

public class P117 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 50;
	}

	@Override
	public long solve(Integer fieldWidth, boolean printResults) {
		long[] cache = new long[51];
		long ways = solveCore(fieldWidth, cache);

		if (printResults) {
			System.out.println("There are " + ways + " ways to tile a row of " + fieldWidth + " tiles.");
		}
		return ways;
	}

	private long solveCore(int fieldWidth, long[] cache) {
		if (fieldWidth <= 1)
			return 1;
		if (cache[fieldWidth] != 0)
			return cache[fieldWidth];

		// initial way represents not laying any tiles
		long ways = 1;

		for (int blockSize = 2; blockSize <= 4; blockSize++) {
			for (int offset = 0; offset <= fieldWidth - blockSize; offset++) {
				ways += solveCore(fieldWidth - blockSize - offset, cache);
			}
		}

		cache[fieldWidth] = ways;
		return ways;
	}

	@Override
	protected Integer getTestParameter() {
		return 5;
	}

	@Override
	protected long getTestSolution() {
		return 15;
	}

	@Override
	public String getTitle() {
		return "Problem 117: Red, Green, and Blue Tiles";
	}

}
