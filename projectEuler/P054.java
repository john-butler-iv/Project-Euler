package projectEuler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P054 extends Problem {

	private final static int P1 = 0;
	private final static int P2 = 1;
	private final static int VALUE = 0;
	private final static int SUIT = 1;

	private final static char T = 10 + '0';
	private final static char J = 11 + '0';
	private final static char Q = 12 + '0';
	private final static char K = 13 + '0';
	private final static char A = 14 + '0';

	private static class ReturnObj {
		static ReturnObj FALSE_OBJ = new ReturnObj(false, null, null);
		boolean result;
		String[] leftoverCards;
		String usedCard;

		ReturnObj(boolean result, String[] leftoverCards, String usedCard) {
			this.result = result;
			this.leftoverCards = leftoverCards;
			this.usedCard = usedCard;
		}
	}

	@Override
	public long solve(boolean printResults) {

		// data is stored as an array list with each element being one round.
		// Each round is an array with each element representing each player.
		// Each player has a sorted array of cards like "<value><suit>".
		ArrayList<String[/* player */][/* card */]> hands = readData("p054.txt");

		int player1Wins = 0;
		for (int i = 0; i < hands.size(); i++)
			player1Wins += whoWins(hands.get(i)) == 1 ? 1 : 0;

		if (printResults)
			System.out.println("player 1 won " + player1Wins + " times.");
		return player1Wins;
	}

	private static int straightFlushWin(String[] hand1, String[] hand2) {
		boolean sf1 = isStraight(hand1) && isFlush(hand2);
		boolean sf2 = isStraight(hand2) && isFlush(hand2);
		if (sf1) {
			// if there's a tie, whoever's straight is higher
			if (sf2)
				return hand1[P1].charAt(VALUE) > hand2[P1].charAt(VALUE) ? 1 : 2;
			return 1;
		}

		if (sf1)
			return 2;

		return 0;
	}

	private static int fourKindWin(String[] hand1, String[] hand2) {
		ReturnObj four1 = isNKind(hand1, 4);
		ReturnObj four2 = isNKind(hand2, 4);

		if (four1.result) {
			if (four2.result) {
				if (four1.usedCard.charAt(VALUE) == four2.usedCard.charAt(VALUE))
					return highCardsWinner(four1.leftoverCards, four2.leftoverCards);
				return four1.usedCard.charAt(VALUE) > four2.usedCard.charAt(VALUE) ? 1 : 2;
			}
			return 1;
		}

		if (four2.result)
			return 2;

		return 0;
	}

	private static int fullHouseWin(String[] hand1, String[] hand2) {
		ReturnObj full1 = isFullHouse(hand1);
		ReturnObj full2 = isFullHouse(hand2);

		if (full1.result) {
			if (full2.result) {
				if (full1.usedCard.charAt(VALUE) == full2.usedCard.charAt(VALUE))
					return full1.leftoverCards[0].charAt(VALUE) > full2.leftoverCards[0].charAt(VALUE) ? 1 : 2;
				return full1.usedCard.charAt(VALUE) > full2.usedCard.charAt(VALUE) ? 1 : 2;
			}
			return 1;
		}

		if (full2.result)
			return 2;

		return 0;
	}

	private static int flushWin(String[] hand1, String[] hand2) {
		if (isFlush(hand1)) {
			if (isFlush(hand2))
				return highCardsWinner(hand1, hand2);
			return 1;
		}

		if (isFlush(hand2))
			return 2;

		return 0;
	}

	private static int straightWin(String[] hand1, String[] hand2) {
		if (isStraight(hand1)) {
			if (isStraight(hand2))
				return hand1[0].charAt(0) > hand2[0].charAt(0) ? 1 : 2;
			return 1;
		}

		if (isStraight(hand2))
			return 2;

		return 0;
	}

	private static int threeWin(String[] hand1, String[] hand2) {
		ReturnObj three1 = isNKind(hand1, 3);
		ReturnObj three2 = isNKind(hand2, 3);

		if (three1.result) {
			if (three2.result) {
				if (three1.usedCard.charAt(VALUE) == three2.usedCard.charAt(VALUE))
					return highCardsWinner(three1.leftoverCards, three2.leftoverCards);
				return three1.usedCard.charAt(VALUE) > three2.usedCard.charAt(VALUE) ? 1 : 2;
			}
			return 1;
		}

		if (three2.result)
			return 2;

		return 0;
	}

	private static int pairWin(String[] hand1, String[] hand2) {
		ReturnObj pair1 = isNKind(hand1, 2);
		ReturnObj pair2 = isNKind(hand2, 2);
		if (pair1.result) {
			if (pair2.result) {
				if (pair1.usedCard.charAt(VALUE) == pair2.usedCard.charAt(VALUE))
					return highCardsWinner(pair1.leftoverCards, pair2.leftoverCards);
				return (pair1.usedCard.charAt(VALUE) > pair2.usedCard.charAt(VALUE)) ? 1 : 2;
			}
			return 1;
		}

		if (pair2.result)
			return 2;

		return 0;
	}

	private static int whoWins(String[][] hands) {
		// Straight Flush.
		// Royal flush is a special case, so we don't have to explicitly check for it
		int sfWinner = straightFlushWin(hands[P1], hands[P2]);
		if (sfWinner != 0)
			return sfWinner;

		// Four of a Kind
		int fkWinner = fourKindWin(hands[P1], hands[P2]);
		if (fkWinner != 0)
			return sfWinner;

		// Full House
		int fhWinner = fullHouseWin(hands[P1], hands[P2]);
		if (fhWinner != 0)
			return fhWinner;

		// Flush
		int fWinner = flushWin(hands[P1], hands[P2]);
		if (fWinner != 0)
			return fWinner;

		// Straight
		int sWinner = straightWin(hands[P1], hands[P2]);
		if (sWinner != 0)
			return sWinner;

		// Three of a Kind
		int tWinner = threeWin(hands[P1], hands[P2]);
		if (tWinner != 0)
			return tWinner;

		// Two Pairs
		if (isTwoPairs(hands[P1]).result)
			return 1;
		if (isTwoPairs(hands[P2]).result)
			return 2;

		// One Pair
		int pWinner = pairWin(hands[P1], hands[P2]);
		if (pWinner != 0)
			return pWinner;

		// High Card
		return highCardsWinner(hands[P1], hands[P2]);
	}

	private static ReturnObj isFullHouse(String[] hand) {
		// full house is a three of a kind ...
		ReturnObj three = isNKind(hand, 3);
		if (!three.result)
			return ReturnObj.FALSE_OBJ;

		// and a pair
		ReturnObj pair = isNKind(three.leftoverCards, 2);
		if (pair.result)
			return new ReturnObj(true, new String[] { pair.usedCard }, three.usedCard);

		return ReturnObj.FALSE_OBJ;
	}

	private static boolean isFlush(String[] hand) {
		// ensure no suits are the same
		for (int i = 0; i < 4; i++)
			if (hand[i].charAt(SUIT) != hand[i + 1].charAt(SUIT))
				return false;

		return true;
	}

	private static boolean isStraight(String[] hand) {
		// ensure no cards are the same
		for (int i = 0; i < 4; i++)
			if (hand[i].charAt(VALUE) == hand[i + 1].charAt(VALUE))
				return false;

		// make sure they only go up by one each
		return hand[0].charAt(VALUE) + 4 == hand[4].charAt(VALUE);
	}

	// figures out if there is "n" of a kind i.e. 3 of a kind
	private static ReturnObj isNKind(String[] hand, int n) {
		for (int i = 0; i < hand.length - n + 1; i++) {
			// if _ in a row,
			if (hand[i].charAt(VALUE) == hand[i + n - 1].charAt(VALUE)) {
				String[] leftoverCards = new String[hand.length - n];

				// keep track of cards before triplet
				for (int j = 0; j < i; j++)
					leftoverCards[j] = hand[j];

				// keep track of cards after _ of a kind
				for (int j = i + n; j < hand.length; j++)
					leftoverCards[j - n] = hand[j];

				// create return object
				return new ReturnObj(true, leftoverCards, hand[i]);
			}
		}
		// no _ of a kind found.
		return ReturnObj.FALSE_OBJ;
	}

	private static ReturnObj isTwoPairs(String[] hand) {
		// one pair
		ReturnObj obj = isNKind(hand, 2);

		if (!obj.result)
			// we know obj is a false return obj
			return obj;

		return isNKind(obj.leftoverCards, 2);
	}

	private static int highCardsWinner(String[] hand1, String[] hand2) {
		for (int i = hand1.length - 1; i >= 0; i--) {
			if (hand1[i].charAt(VALUE) > hand2[i].charAt(VALUE))
				return 1;
			else if (hand1[i].charAt(VALUE) < hand2[i].charAt(VALUE))
				return 2;
		}
		return 0;
	}

	private static ArrayList<String[][]> readData(String filename) {
		try {
			Scanner scanner = new Scanner(new File(filename));
			ArrayList<String[][]> rounds = new ArrayList<>();

			// put the each hand in the data structure
			while (scanner.hasNext()) {
				String[][] round = new String[2][5];
				for (int player = 0; player < 2; player++) {
					for (int card = 0; card < 5; card++) {
						String rawCard = scanner.next();
						char value = rawCard.charAt(VALUE);
						char suit = rawCard.charAt(VALUE);

						// using 'T', 'J', ... makes the coding more complicated, so I change it to
						// ('9' + 1), ('9' + 2), ...
						switch (value) {
							case 'T':
								value = T;
								break;
							case 'J':
								value = J;
								break;
							case 'Q':
								value = Q;
								break;
							case 'K':
								value = K;
								break;
							case 'A':
								value = A;
								break;
						}

						round[player][card] = String.valueOf(new char[] { value, suit });
					}
				}
				rounds.add(round);
			}

			// sort each hand to make it easer to compute which hand wins
			for (int round = 0; round < rounds.size(); round++) {
				for (int player = 0; player < 2; player++) {
					String[] hand = rounds.get(round)[player];

					// bubble sort is fine since hands are small
					// sorts by value then suit
					Arrays.sort(hand);
				}
			}
			return rounds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getTitle() {
		return "Problem 054: Poker hands";
	}
}
