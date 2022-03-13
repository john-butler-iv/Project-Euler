package projectEuler;

public class P110 extends P108 {
	@Override
	public Integer getDefaultParameter() {
		return 4000000;
	}

	@Override
	protected Integer getTestParameter() {
		return 100;
	}

	@Override
	protected long getTestSolution() {
		return 1260;
	}

	@Override
	public String getTitle() {
		return "Problem 110: Diophantine Reciprocals II";
	}
}
