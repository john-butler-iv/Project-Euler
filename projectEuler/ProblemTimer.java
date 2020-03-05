package projectEuler;

abstract class ProblemTimer extends Timer {
	protected Problem[] problems;

	private Problem getProblem(int problemID) {
		if (problemID < 0 || problemID >= problems.length)
			return null;

		return problems[problemID];
	}

	private int[] getAllIDs() {
		int size = problems.length;
		int[] ids = new int[size];

		for (int i = 0; i < size; i++)
			ids[i] = i;

		return ids;
	}

	long time(int problemID) {
		if (getProblem(problemID) == null)
			return -1;
		return Timer.time(getProblem(problemID), null, DEFUALT_TRIALS, false);
	}

	public boolean test(int problemID) {
		Problem problem = getProblem(problemID);
		if (problem == null)
			return false;
		return problem instanceof ParameterizedProblem ? ((ParameterizedProblem) problem).test() : true;
	}

	String report(int problemID) {
		Problem problem = getProblem(problemID);
		
		if (problem == null)
			return "There is no problem with ID " + problemID;

		String title = problem.getTitle();

		if (!test(problemID))
			return title + " failed its test";

		long time = time(problemID);

		return title + " finished in " + time + "ms.";
	}

	long[] time(int[] problemIDs) {
		long[] times = new long[problemIDs.length];
		for (int i = 0; i < problemIDs.length; i++) {
			try {
				times[i] = time(problemIDs[i]);
			} catch (IllegalArgumentException e) {
				times[i] = -1;
			}
		}
		return times;
	}

	boolean[] test(int[] problemIDs) {
		boolean[] tests = new boolean[problemIDs.length];
		for (int i = 0; i < problemIDs.length; i++) {
			try {
				tests[i] = test(problemIDs[i]);
			} catch (IllegalArgumentException e) {
				tests[i] = false;
			}
		}
		return tests;
	}

	String[] report(int[] problemIDs) {
		String[] reports = new String[problemIDs.length];
		for (int i = 0; i < problemIDs.length; i++) {
			try {
				reports[i] = report(problemIDs[i]);
			} catch (IllegalArgumentException e) {
				reports[i] = problemIDs[i] + " is an invalid ID, valid ID's range from 0 - " + (problems.length - 1);
			}
		}
		return reports;
	}

	long[] timeInRange() {
		return time(getAllIDs());
	}

	boolean[] testInRange() {
		return test(getAllIDs());
	}

	String[] reportInRange() {
		return report(getAllIDs());
	}

	abstract long[] timeAll();

	abstract String[] reportAll();
}