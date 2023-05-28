package projectEuler;

public class P115 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 50;
	}

	@Override
	public long solve(Integer minBlockSize, boolean printResults) {
		long[] cache = new long[500];

		int rowSize = minBlockSize + 2;

		while (solveCore(minBlockSize, rowSize, cache) < 1000000) {
			rowSize++;
		}

		if (printResults) {
			System.out.println(rowSize
					+ " is the first row size for which the fill-count function exceeds one million for blocks of size "
					+ minBlockSize + " or larger");
		}

		return rowSize;
	}

	private long solveCore(int minBlockSize, int fieldWidth, long[] cache) {
		if (fieldWidth < minBlockSize)
			return 1;
		if (cache[fieldWidth] != 0)
			return cache[fieldWidth];

		// include leaving everything blank, not adding any blocks.
		int ways = 1;

		for (int offset = 0; offset <= fieldWidth - minBlockSize; offset++) {
			for (int blockSize = minBlockSize; blockSize <= fieldWidth - offset; blockSize++) {
				ways += solveCore(minBlockSize, fieldWidth - offset - blockSize - 1, cache);
			}
		}

		cache[fieldWidth] = ways;
		return ways;
	}

	@Override
	protected Integer getTestParameter() {
		return 3;
	}

	@Override
	protected long getTestSolution() {
		return 30;
	}

	@Override
	public String getTitle() {
		return "Problem 115: Counting Block Combinations II";
	}

}
