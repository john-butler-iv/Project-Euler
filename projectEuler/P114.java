package projectEuler;

public class P114 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 50;
	}

	@Override
	public long solve(Integer parameter, boolean printResults) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'solve'");
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