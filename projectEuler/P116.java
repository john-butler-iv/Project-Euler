package projectEuler;

public class P116 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 50;
	}

	@Override
	public long solve(Integer numTiles, boolean printResults) {

		long[] redCache = new long[numTiles + 1];
		long[] greenCache = new long[numTiles + 1];
		long[] blueCache = new long[numTiles + 1];

		long ways = solveCore(2, numTiles, redCache) - 1;
		ways += solveCore(3, numTiles, greenCache) - 1;
		ways += solveCore(4, numTiles, blueCache) - 1;

		if (printResults) {
			System.out.println("There are " + ways
					+ " different wasy that the grey tiles can be colored if you only use one type of tile.");
		}

		return ways;
	}

	private long solveCore(int tileSize, int fieldWidth, long[] cache) {
		if (fieldWidth < tileSize)
			return 1;
		if (cache[fieldWidth] != 0)
			return cache[fieldWidth];

		// initial way represents not filling with anything
		long ways = 1;

		for (int offset = 0; offset <= fieldWidth - tileSize; offset++) {
			ways += solveCore(tileSize, fieldWidth - offset - tileSize, cache);
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
		return 12;
	}

	@Override
	public String getTitle() {
		return "Problem 116: Red, Green, or Blue Tiles";
	}

}
