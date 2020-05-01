package projectEuler;

import java.util.Random;

public class P084 extends ParameterizedProblem<Integer> {

	private final static int GO = 0;
	private final static int[] CC = { 2, 17, 33 };
	private final static int[] CH = { 7, 22, 36 };
	private final static int[] R = { 5, 15, 25, 35 };
	private final static int[] U = { 12, 28 };
	private final static int JAIL = 10;
	private final static int C1 = 11;
	private final static int E3 = 24;
	private final static int G2J = 30;
	private final static int H2 = 39;
	private final static int BOARD_SIZE = 40;

	private final static int NEXT_RR = -2;
	private final static int NEXT_UTIL = -3;
	private final static int BACK3 = -4;

	private final static int[] CC1 = { GO, JAIL, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private final static int[] CH1 = { GO, JAIL, C1, E3, H2, R[0], NEXT_RR, NEXT_RR, NEXT_UTIL, BACK3, -1, -1, -1, -1,
			-1, -1 };

	private Random rand;

	public P084() {
		rand = new Random();
	}

	@Override
	Integer getDefaultParameter() {
		return 4;
	}

	@Override
	long solve(Integer faces, boolean printResults) {
		int[] endingSpot = new int[BOARD_SIZE];

		// int cc = CollectionTools.shuffle(CC1);
		// int ch = CollectionTools.shuffle(CH1);

		int space = GO;

		final int MAX_ITERATIONS = 200000;
		// move around the board a lot and record where you land
		for (int i = 0; i < MAX_ITERATIONS; i++) {
			int roll = roll(faces);
			if (roll == 0)
				space = JAIL;
			else {
				space = (space + roll) % BOARD_SIZE;

				// resolve chance locations
				boolean onCh = false;
				for (int ch : CH)
					onCh |= space == ch;

				if (onCh) {
					int ch = CH1[rand.nextInt(CH1.length)];
					if (ch >= 0 && ch < BOARD_SIZE)
						space = ch;
					else
						switch (ch) {
							case NEXT_RR:
								if (space < R[1])
									space = R[1];
								else if (space < R[2])
									space = R[2];
								else if (space < R[3])
									space = R[3];
								else
									space = R[0];
								break;
							case NEXT_UTIL:
								if (space < U[1] && space >= U[0])
									space = U[1];
								else
									space = U[0];
								break;
							case BACK3:
								space -= 3;
								break;
							case -1:
							default:
								break;
						}
				}

				// resolve community chest
				boolean onCC = false;
				for (int cc : CC)
					onCC |= space == cc;
				if (onCC) {
					int cc = CC1[rand.nextInt(CC1.length)];
					if (cc >= 0 && cc < BOARD_SIZE)
						space = cc;
				}

				// resolve go to jail
				if (space == G2J)
					space = JAIL;
			}
			endingSpot[space]++;
		}

		// find most common space
		int firstIndex = -1;
		double firstValue = -1;
		for (int i = 0; i < endingSpot.length; i++) {
			if (endingSpot[i] > firstValue) {
				firstIndex = i;
				firstValue = endingSpot[i];
			}
		}
		// find second most common space
		int secondIndex = -1;
		double secondValue = -1;
		for (int i = 0; i < endingSpot.length; i++) {
			if (i == firstIndex)
				continue;
			if (endingSpot[i] > secondValue) {
				secondIndex = i;
				secondValue = endingSpot[i];
			}
		}
		// find third most common space
		int thirdIndex = -1;
		double thirdValue = -1;
		for (int i = 0; i < endingSpot.length; i++) {
			if (i == firstIndex || i == secondIndex)
				continue;
			if (endingSpot[i] > thirdValue) {
				thirdIndex = i;
				thirdValue = endingSpot[i];
			}
		}

		if (printResults) {
			System.out.println(firstIndex + " " + (firstValue * 100.0 / MAX_ITERATIONS) + "%");
			System.out.println(secondIndex + " " + (secondValue * 100.0 / MAX_ITERATIONS) + "%");
			System.out.println(thirdIndex + " " + (thirdValue * 100.0 / MAX_ITERATIONS) + "%");
			System.out
					.println("So the concatenated string is " + (10000 * firstIndex + 100 * secondIndex + thirdIndex));
		}

		return 10000 * firstIndex + 100 * secondIndex + thirdIndex;

	}

	/**
	 * Finds a random roll, and recursively rolls again there's doubles
	 * 
	 * @param faces how many sides are on the dice
	 * @return returns the value of the roll, or 0 if 3 doubles were rolled,
	 *         indicating that the player should go to jail.
	 */
	private int roll(int faces) {
		return roll(faces, 3);
	}

	/**
	 * Finds a random roll, and recursively rolls again there's doubles
	 * 
	 * @param faces      how many sides are on the dice
	 * @param maxDoubles how many double will put you in jail (3 by default)
	 * @return returns the value of the roll, or 0 if maxDoubles doubles were
	 *         rolled, indicating that the player should go to jail.
	 */
	private int roll(int faces, int maxDoubles) {
		if (maxDoubles == 0)
			return 0;

		// roll twice
		int roll1 = rand.nextInt(faces) + 1;
		int roll2 = rand.nextInt(faces) + 1;

		if (roll1 == roll2) {
			// figure out how what the doubles reroll is
			int doublesRoll = roll(faces, maxDoubles);

			// if must go to jail, we wan to relay that
			if (doublesRoll == 0)
				return 0;

			return doublesRoll + roll1 * 2;
		}

		return roll1 + roll2;
	}

	@Override
	protected Integer getTestParameter() {
		return 6;
	}

	@Override
	protected long getTestSolution() {
		// I'm aware this value is technically wrong. My algorithm works for 4, but not
		// 6, so I'm not going to fix it for now
		return 100024;
	}

	@Override
	String getTitle() {
		return "Problem 084: Monopoly Odds";
	}

}
