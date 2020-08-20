package projectEuler;

public class P090_alt extends Problem {

	public long solve(boolean printResults) {
		// I replace all '9's with '6's
		String[] squares = { "01", "04", "06", "16", "25", "36", "46", "64", "81" };

		int[] factorials = EulerTools.factorialTable(9);
		int cnt = 0;
		for (int directions = 0; directions < 256; directions++) {
			boolean[] die1 = new boolean[10];
			int used1 = 0;
			boolean[] die2 = new boolean[10];
			int used2 = 0;

			int mask = 1;
			for (int i = 0; i < squares.length; i++) {
				int dir = directions & mask;

				int index2 = squares[i].charAt(0) - '0';
				int index1 = squares[i].charAt(1) - '0';
				if (dir == 0) {
					int temp = index1;
					index1 = index2;
					index2 = temp;
				}

				if (!die1[index1]) {
					used1++;
					die1[index1] = true;
				}
				if (!die2[index2]) {
					used2++;
					die2[index2] = true;
				}

				// I could check if a die has more than 6 faces here, but I think it loses more
				// time than it gains

				mask = mask << 1;
			}

			if (used1 <= 6 && used2 <= 6) {
				int cnt1 = EulerTools.choose(10 - used1, 6 - used1, factorials);
				if (die1[6])
					// pretend you used 9 instead of 6, and count that
					// we already counted 6 and 9 together, so don't count that again
					cnt1 += EulerTools.choose(9 - used1, 6 - used1, factorials);

				int cnt2 = EulerTools.choose(10 - used2, 6 - used2, factorials);
				if (die2[6])
					cnt2 += EulerTools.choose(9 - used2, 6 - used2, factorials);

				cnt += cnt1 * cnt2;
			}
		}

		if (printResults)
			System.out.println("There are " + cnt + " unique sets of 2 dice that can show all squares below 100.");
		return cnt;
	}

	public String getTitle() {
		return "Problem 090: Cube Digit Pairs";
	}
}
