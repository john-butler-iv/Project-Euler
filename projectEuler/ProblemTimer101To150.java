package projectEuler;

public class ProblemTimer101To150 extends ProblemTimer {

	public static void printUsage() {
		System.err.println("Usage: java projectEuler.ProblemTimer101To150 <solve/report> <pid pid ...>");
	}

	public static void main(String[] args) {
		ProblemTimer.main(args, new ProblemTimer101To150());
	}

	public ProblemTimer101To150() {
		previousTimer = new ProblemTimer51To100();
		minPid = 101;
		maxPid = 150;

		problems = new Problem[50];
		problems[0] = new P101();
		problems[1] = new P102();
		//problems[2] = new P103();
		//problems[3] = new P104();
		//problems[4] = new P105();
		//problems[5] = new P106();
		//problems[6] = new P107();
		problems[7] = new P108();
		//problems[8] = new P109();
		problems[9] = new P110();
		//problems[10] = new P111();
		problems[11] = new P112();
		problems[12] = new P113();
		problems[13] = new P114();
		//problems[14] = new P115();
		//problems[15] = new P116();
		//problems[16] = new P117();
		//problems[17] = new P118();
		//problems[18] = new P119();
		//problems[19] = new P120();
		//problems[20] = new P121();
		//problems[21] = new P122();
		//problems[22] = new P123();
		//problems[23] = new P124();
		//problems[24] = new P125();
		//problems[25] = new P126();
		problems[26] = new P127();
		//problems[27] = new P128();
		//problems[28] = new P129();
		//problems[29] = new P130();
		//problems[30] = new P131();
		//problems[31] = new P132();
		//problems[32] = new P133();
		//problems[33] = new P134();
		//problems[34] = new P135();
		//problems[35] = new P136();
		//problems[36] = new P137();
		//problems[37] = new P138();
		//problems[38] = new P139();
		//problems[39] = new P140();
		//problems[40] = new P141();
		//problems[41] = new P142();
		//problems[42] = new P143();
		problems[43] = new P144();
		//problems[44] = new P145();
		//problems[45] = new P146();
		//problems[46] = new P147();
		//problems[47] = new P148();
		//problems[48] = new P149();
		//problems[49] = new P150();
	}
}