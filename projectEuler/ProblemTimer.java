package projectEuler;

abstract class ProblemTimer extends Timer {
	protected ProblemTimer previousTimer;
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

	public long time(int problemID) {
		if (getProblem(problemID) == null)
			return -1;
		return Timer.time(getProblem(problemID), null, DEFUALT_TRIALS, false);
	}

	public boolean test(int problemID) {
		Problem problem = getProblem(problemID);
		if (problem == null)
			return false;
		return problem.test();
	}

	public void report(int problemID) {
		Problem problem = getProblem(problemID);

		if (problem == null) {
			System.out.println("There is no problem with ID " + problemID);
			return;
		}

		String title = problem.getTitle();

		if (!test(problemID)) {
			System.out.println(title + " failed its test");
			return;
		}

		long time = time(problemID);
		System.out.println(title + " finished in " + time + "ms.");
	}

	public void solve(int problemID) {
		Problem problem = getProblem(problemID);

		if (problem == null) {
			System.out.println("There is no problem with ID " + problemID);
			return;
		}

		String title = problem.getTitle();

		if (!test(problemID)) {
			System.out.println(title + " failed its test");
		}

		System.out.println(title + ": ");
		problem.solve(true);
	}

	public long[] time(int[] problemIDs) {
		long[] times = new long[problemIDs.length];
		for (int i = 0; i < problemIDs.length; i++)
			times[i] = time(problemIDs[i]);

		return times;
	}

	public boolean[] test(int[] problemIDs) {
		boolean[] tests = new boolean[problemIDs.length];

		for (int i = 0; i < problemIDs.length; i++)
			tests[i] = test(problemIDs[i]);

		return tests;
	}

	public void report(int[] problemIDs) {
		for (int pid : problemIDs)
			report(pid);
	}

	public void solve(int[] problemIDs) {
		for (int pid : problemIDs)
			solve(pid);
	}

	public long[] timeInRange() {
		return time(getAllIDs());
	}

	public boolean[] testInRange() {
		return test(getAllIDs());
	}

	public void reportInRange() {
		report(getAllIDs());
	}

	public void solveInRange() {
		solve(getAllIDs());
	}

	public long[] timeAll() {
		// get the times
		long[] prevTimes = previousTimer.timeAll();
		long[] currTimes = timeInRange();

		long[] allTimes = new long[prevTimes.length + currTimes.length];
		// join the arrays into one array.
		for (int i = 0; i < prevTimes.length; i++)
			allTimes[i] = prevTimes[i];
		for (int i = 0; i < allTimes.length; i++)
			allTimes[i + prevTimes.length] = currTimes[i];

		// return the merged array
		return allTimes;
	}

	public void reportAll() {
		if (previousTimer != null)
			previousTimer.reportAll();
		reportInRange();
	}

	public void solveAll() {
		if (previousTimer != null)
			previousTimer.solveAll();
		solveInRange();
	}
}
