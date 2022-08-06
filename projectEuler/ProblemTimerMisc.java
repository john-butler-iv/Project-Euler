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
			case 108:
				return problems[2];
			case 110:
				return problems[3];
			case 112:
				return problems[4];
			case 113:
				return problems[5];
			case 144:
				return problems[6];
			case 202:
				return problems[7];
			case 205:
				return problems[8];
			case 206:
				return problems[9];
			case 246:
				return problems[10];
			case 301:
				return problems[11];
			case 357:
				return problems[12];
			case 752:
				return problems[13];
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

		problems = new Problem[14];
		problems[0] = new P101();
		problems[1] = new P102();
		problems[2] = new P108();
		problems[3] = new P110();
		problems[4] = new P112();
		problems[5] = new P113();
		problems[6] = new P144();
		problems[7] = new P202();
		problems[8] = new P205();
		problems[9] = new P206();
		problems[10] = new P246();
		problems[11] = new P301();
		problems[12] = new P357();
		problems[13] = new P752();
	}
}
