package projectEuler;

public class P053 extends Problem {
	@Override
	public boolean test() {
		int[] line = new int[25];
		line[0] = 1;
		for (int i = 1; i <= 23; i++)
			updateLine(line);

		return line[10] > 1000000;
	}

	@Override
	long solve(boolean printResults) {
		int total = 0;

		int[] line = new int[102]; // line of pascal's triangle
		line[0] = 1;

		for (int lineNum = 0; lineNum <= 100; lineNum++) {
			for (int i = 0; line[i] != 0; i++)
				total += line[i] > 1000000 ? 1 : 0;

			updateLine(line);
		}
		if (printResults)
			System.out.println(total);
		return total;
	}

	private static void updateLine(int[] line) {
		if (line[line.length - 1] != 0) {
			System.out.println("int[] too short in updateLine");
			return;
		}

		for (int i = line.length - 1; i >= 1; i--) {
			int total = line[i] + line[i - 1];
			line[i] = total > 1000000 ? 1000001 : total;
		}
	}

	@Override
	String getTitle() {
		return "Problem 053: Combinatoric selections";
	}

}