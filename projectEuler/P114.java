package projectEuler;

public class P114 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 50;
	}

	@Override
	public long solve(Integer fieldWidth, boolean printResults) {
		long[] cache = new long[fieldWidth + 1];
		long ways = solveCore(fieldWidth, cache);

		if (printResults) {
			System.out.println("There are " + ways + " ways to fill a row measuring " + fieldWidth + " units.");
		}
		return ways;
	}

	private long solveCore(int fieldWidth, long[] cache) {
		// we need a check for if fieldWidth <= 0, so we may as well, just go up to 2 and exclude the need for initializing the cache
		if (fieldWidth <= 2)
			return 1;
		if (cache[fieldWidth] != 0)
			return cache[fieldWidth];

		// represents leaving space empty
		long ways = 1;

		for (int blockSize = 3; blockSize <= fieldWidth; blockSize++) {
			for (int offset = 0; offset <= fieldWidth - blockSize; offset++) {
				ways += solveCore(fieldWidth - blockSize - offset - 1, cache);
			}
		}

		cache[fieldWidth] = ways;
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