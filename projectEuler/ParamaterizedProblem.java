package projectEuler;

abstract class ParameterizedProblem extends Problem {
	
	abstract long getDefaultParameter();

	abstract long solve(long parameter, boolean printResults);

	protected abstract long getTestParameter();

	protected abstract long getTestSolution();

	public boolean test(){
		return solve(getTestParameter(), false) == getTestSolution();
	}
	
	public long solve(boolean printResults) {
		return solve(getDefaultParameter(), printResults);
	}
}