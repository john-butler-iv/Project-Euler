package projectEuler;

abstract class ProblemTimer extends Timer {
	protected final static int MIN_PROBLEM = -1;
	protected final static int MAX_PROBLEM = -1;
	protected ProblemTimer previousTimer;
	protected Problem[] problems;

	protected static ProblemTimer instance;

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("usage: java ProblemTimer <solve/report> [pid pid ...]");
			return;
		} else {
			String method = args[0];
			if (!method.equals("report") && !method.equals("solve")) {
				System.out.println("usage: java ProblemTimer <solve/report> [pid pid ...]");
				return;
			}
			int[] pids = new int[args.length - 1];
			for (int i = 0; i < args.length; i++) {
				try {
					pids[i] = Integer.valueOf(args[i + 1]);

					if (pids[i] > MAX_PROBLEM || pids[i] <= 0)
						System.out.println(pids[i] + " is not a valid problem number");

				} catch (NumberFormatException e) {
					pids[i] = -1;
					System.out.println(args[i + 1] + " is an invalid problem number (NumberFormatException)");
				}
			}

			int inRange = 0;
			int prev = 0;
			for (int pid : pids)
				if (pid >= MIN_PROBLEM && pid <= MAX_PROBLEM)
					inRange++;
				else if (pid != -1)
					prev++;

			int[] thisPids = new int[inRange];
			int[] prevPids = new int[prev];
			for (int pid : pids)
				if (pid >= MIN_PROBLEM && pid <= MAX_PROBLEM)
					thisPids[--inRange] = pid;
				else if (pid != -1)
					prevPids[--prev] = pid;

			if (method.equals("solve")) {
				if (instance.previousTimer != null)
					instance.previousTimer.solve(prevPids);
				else if (prevPids.length != 0)
					for (int pid : prevPids)
						System.out.println(pid + " is an invalid problem number");

				instance.solve(thisPids);

			} else if (method.equals("report")) {
				if (instance.previousTimer != null)
					instance.previousTimer.report(prevPids);
				else if (prevPids.length != 0)
					for (int pid : prevPids)
						System.out.println(pid + " is an invalid problem number");

				instance.report(thisPids);
			}
		}
	}

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
