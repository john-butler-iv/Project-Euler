package projectEuler;

public class ProblemTimer51To100 extends ProblemTimer {

	public static void printUsage() {
		System.err.println("Usage: java projectEuler.ProblemTimer51To100 <solve/report> <pid pid ...>");
	}

	public static void main(String[] args) {
		ProblemTimer.main(args, new ProblemTimer51To100());
	}

	public ProblemTimer51To100() {
		previousTimer = new ProblemTimer1To50();
		minPid = 51;
		maxPid = 100;

		problems = new Problem[50];
		problems[0] = new P051();
		problems[1] = new P052();
		problems[2] = new P053();
		problems[3] = new P054();
		problems[4] = new P055();
		problems[5] = new P056();
		problems[6] = new P057();
		problems[7] = new P058();
		problems[8] = new P059();
		problems[9] = new P060();
		problems[10] = new P061();
		problems[11] = new P062();
		problems[12] = new P063();
		problems[13] = new P064();
		problems[14] = new P065();
		problems[15] = new P066();
		problems[16] = new P067();
		problems[17] = new P068();
		problems[18] = new P069();
		problems[19] = new P070();
		problems[20] = new P071();
		problems[21] = new P072();
		problems[22] = new P073();
		problems[23] = new P074();
		// problems[24] = new P075();
		problems[25] = new P076();
		// problems[26] = new P077();
		problems[27] = new P078();
		problems[28] = new P079();
		problems[29] = new P080();
		problems[30] = new P081();
		problems[31] = new P082();
		problems[32] = new P083();
		problems[33] = new P084();
		problems[34] = new P085();
		problems[35] = new P086();
		problems[36] = new P087();
		// problems[37] = new P088();
		problems[38] = new P089();
		problems[39] = new P090();
		problems[40] = new P091();
		problems[41] = new P092();
		problems[42] = new P093();
		problems[43] = new P094();
		problems[44] = new P095();
		problems[45] = new P096();
		problems[46] = new P097();
		// problems[47] = new P098();
		problems[48] = new P099();
		problems[49] = new P100();
	}

}
