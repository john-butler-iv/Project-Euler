package projectEuler;

public class P075 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 1500000;
	}

	@Override
	public long solve(Integer maxPerimeter, boolean printResults) {
		int[] arr = new int[maxPerimeter];
		int validLengths = maxPerimeter;

		for (int a = 3; a < maxPerimeter / 3; a++) {
			for (int b = a + 1; b < maxPerimeter / 3; b++) {
				double c = Math.sqrt(a * a + b * b);
				if (EulerTools.isIntegral(c) && ++arr[a + b + (int) c] == 2)
					validLengths--;
			}
		}
		if (printResults) {
			System.out.println("There are " + validLengths + " lengths of wire less than or equal to " + maxPerimeter
					+ " that can form a singlular integer right triangle");
		}
		return validLengths;
	}

	@Override
	protected Integer getTestParameter() {
		return 50;
	}

	@Override
	protected long getTestSolution() {
		return 6;
	}

	@Override
	public String getTitle() {
		return "Problem 075: Singlular Integer Right Triangles";
	}

}
