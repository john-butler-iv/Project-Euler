package projectEuler;

public class ProblemTimerMisc extends ProblemTimer {

	public static void printUsage() {
		System.out.println("Usage: java projectEuler.ProblemTimerMisc <solve/report> <pid [pid] [...] / all / range>");
	}

	@Override
	protected Problem getProblem(int pid) {
		switch (pid) {
			case 101:
				return problems[0];
			case 102:
				return problems[1];
			case 144:
				return problems[2];
			case 205:
				return problems[3];
			case 206:
				return problems[4];
			case 301:
				return problems[5];
			default:
				break;
		}
		return previousTimer.getProblem(pid);
	}

	public static void main(String[] args) {
		ProblemTimer.main(args, new ProblemTimerMisc());
	}

	public ProblemTimerMisc() {
		previousTimer = new ProblemTimer51To100();

		problems = new Problem[6];
		problems[0] = new P101();
		problems[1] = new P102();
		problems[2] = new P144();
		problems[3] = new P205();
		problems[4] = new P206();
		problems[5] = new P301();
	}

}
