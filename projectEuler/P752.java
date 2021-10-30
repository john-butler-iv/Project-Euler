package projectEuler;

public class P752 extends ParameterizedProblem<Integer> {

	private final static double SQRT7 = Math.sqrt(7);
	private final static double L1 = 1 + SQRT7;
	private final static double L2 = 1 - SQRT7;

	@Override
	public Integer getDefaultParameter() {
		return 1000000;
	}

	@Override
	public long solve(Integer parameter, boolean printResults) {

		long G = 0;
		for (int x = 2; x <= parameter; x++) {
			// System.out.println("g(" + x + ") = " + g(x));
			G += g(x);
		}

		for (int i = 0; i < 100; i++) {
			// System.out.println(alphaBeta(i)[0] + " " + alphaBeta(i)[1]);
		}

		// if (printResults)
		System.out.println("G(" + parameter + ") = " + G);

		return G;
	}

	private int g(int x) {
		for (int i = 1; i < x * x; i++) {
			int[] alphaBeta = alphaBeta(i, x);
			if (alphaBeta[0] == 1 && alphaBeta[1] == 0)
				return i;
		}
		return 0;
	}

	private int[] alphaBeta(int n, int x) {
		double l1n = Math.pow(L1, n % x);
		double l2n = Math.pow(L2, n % x);
		int alpha = (int) (l1n + l2n) / 2;
		int beta = (int) ((l1n - l2n) / Math.sqrt(7)) / 2;
		return new int[] { alpha % x, beta % x };

	}

	@Override
	protected Integer getTestParameter() {
		return 100;
	}

	@Override
	protected long getTestSolution() {
		return 28891;
	}

	@Override
	public String getTitle() {
		return "Problem 752: Powers of 1 + sqrt 7";
	}

}