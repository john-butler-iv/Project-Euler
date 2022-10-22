package projectEuler;

abstract class ParameterizedProblem<T> extends Problem {

	public abstract T getDefaultParameter();

	public abstract long solve(T parameter, boolean printResults);

	protected abstract T getTestParameter();

	protected abstract long getTestSolution();

	public boolean test() {
		return test(false, false);
	}

	public boolean test(boolean printResults) {
		return test(printResults, false);
	}

	public boolean test(boolean printFailure, boolean printSuccess) {
		long expectedResult = getTestSolution();
		T testParam = getTestParameter();
		long experimentalResult = solve(testParam, false);
		boolean testPassed = experimentalResult == expectedResult;
		if (printSuccess && testPassed) {
			System.out.println(this.getTitle() + " passed its test!");
		} else if (printFailure && !testPassed) {
			System.out.println(this.getTitle() + " failed its test. It was given a value of " + testParam
					+ " and returned " + experimentalResult + " instead of the expected " + expectedResult);
		}
		return testPassed;
	}

	public long solve(boolean printResults) {
		return solve(getDefaultParameter(), printResults);
	}
}
