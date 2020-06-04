package projectEuler;

abstract class ParameterizedProblem <T> extends Problem {
	
	public abstract T getDefaultParameter();

	public abstract long solve(T parameter, boolean printResults);

	protected abstract T getTestParameter();

	protected abstract long getTestSolution();

	public boolean test(){
		return solve(getTestParameter(), false) == getTestSolution();
	}
	
	public long solve(boolean printResults) {
		return solve(getDefaultParameter(), printResults);
	}
}
