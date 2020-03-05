package projectEuler;

class ProblemTimer1To25 extends ProblemTimer {
	public ProblemTimer1To25() {
		problems = new Problem[25];
		problems[0] = new P001();
		problems[1] = new P002();
		problems[2] = new P003();
		problems[3] = new P004();
		problems[4] = new P005();
		problems[5] = new P006();
		problems[6] = new P007();
		problems[7] = new P008();
		problems[8] = new P009();
		problems[9] = new P010();
		problems[10] = new P011();
		problems[11] = new P012();
		// problems[12] = new P013();
		// problems[13] = new P014();
		// problems[14] = new P015();
		// problems[15] = new P016();
		// problems[16] = new P017();
		// problems[17] = new P018();
		// problems[18] = new P019();
		// problems[19] = new P020();
		// problems[20] = new P021();
		// problems[21] = new P022();
		// problems[22] = new P023();
		// problems[23] = new P024();
		// problems[24] = new P025();
	}

	@Override
	long[] timeAll() {
		return timeInRange();
	}

	@Override
	String[] reportAll() {
		return reportInRange();
	}

	public static void main(String[] args) {
		EulerTools.printlnArr(new ProblemTimer1To25().reportAll(), "\n");
		//new P012().solve(true);
	}

}