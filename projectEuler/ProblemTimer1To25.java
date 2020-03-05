package projectEuler;

class ProblemTimer1To25 extends ProblemTimer {
	public ProblemTimer1To25() {
		problems = new Problem[7];//25];
		problems[0] = new P001();
		problems[1] = new P002();
		problems[2] = new P003();
		problems[3] = new P004();
		problems[4] = new P005();
		problems[5] = new P006();
		problems[6] = new P007();
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
	}

}