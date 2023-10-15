package projectEuler;

public class ProblemTimerMisc extends ProblemTimer {

	public static void printUsage() {
		System.out.println("Usage: java projectEuler.ProblemTimerMisc <solve/report> <pid [pid] [...] / all / range>");
	}

	@Override
	protected Problem getProblem(int pid) {
		Problem retProblem = null;

		// check if we have the requested problem
		for (Problem problem : problems) {
			if (problem.getNumber() == pid) {
				retProblem = problem;
				break;
			}
		}

		if (retProblem == null) {
			// otherwise, must be in another timer.
			retProblem = previousTimer.getProblem(pid);
		}

		return retProblem;
	}

	public static void main(String[] args) {
		ProblemTimer.main(args, new ProblemTimerMisc());
	}

	public ProblemTimerMisc() {
		previousTimer = new ProblemTimer101To150();

		problems = new Problem[9];
		// 151-200
		problems[0] = new P152();
		// 201-250
		problems[1] = new P202();
		problems[2] = new P205();
		problems[3] = new P206();
		problems[4] = new P246();
		problems[5] = new P247();
		// 301-350
		problems[6] = new P301();
		// 351-352
		problems[7] = new P357();
		// ...
		// 751-800
		problems[8] = new P752();
	}
}
