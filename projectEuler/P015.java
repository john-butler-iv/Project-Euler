package projectEuler;

class P015 extends ParameterizedProblem<Integer> {

	@Override
	Integer getDefaultParameter() {
		return 20;
	}

	@Override
	long solve(Integer latticeSize, boolean printResults) {
		long[][] lattice = new long[latticeSize + 1][latticeSize + 1];
		for (int i = 0; i < lattice.length; i++) {
			lattice[0][i] = 1;
			lattice[i][0] = 1;
		}
		for (int r = 1; r < lattice.length; r++)
			for (int c = 1; c < lattice.length; c++)
				lattice[r][c] = lattice[r - 1][c] + lattice[r][c - 1];

		long result = lattice[lattice.length - 1][lattice[0].length - 1];

		if (printResults)
			System.out.println(
					"There are " + result + " ways to traverse a " + latticeSize + "x" + latticeSize + " lattice ");
		return result;
	}

	@Override
	protected Integer getTestParameter() {
		return 2;
	}

	@Override
	protected long getTestSolution() {
		return 6;
	}

	@Override
	String getTitle() {
		return "Problem 015: Lattice Paths";
	}

}