package projectEuler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import javax.lang.model.util.ElementScanner6;

public class P068 extends Problem {

	private static int buildLine(int a, int b, int c) {
		return (((a << 4) + b) << 4) + c;
	}

	private static long buildAttempt(long line1, long line2, long line3, long line4, long line5) {
		return (((((((line1 << 12) + line2) << 12) + line3) << 12) + line4) << 12) + line5;
	}

	private static String extractString(long attempt) {
		String retString = "";
		while (attempt != 0) {
			retString = (attempt & 15) + retString;
			attempt = attempt >> 4;
		}

		return retString;
	}

	public boolean test(boolean printResults) {
		Iterator<Integer[]> it = CollectionTools.reversePermuteIterator(new Integer[] { 6, 5, 4, 3 });

		long greatestAttempt = 0;

		while (it.hasNext()) {
			Integer[] representation = it.next();

			int a = representation[0];
			int f = representation[1];
			int d = representation[2];
			int b = representation[3];

			boolean[] usedNumbers = new boolean[] { false, false, false };
			int c = d + b - f;
			if (c < 1 || c > 2)
				continue;
			usedNumbers[c] = true;

			int e = a + b - f;
			if (e < 1 || e > 2 || usedNumbers[e])
				continue;

			int line1 = buildLine(a, b, c);
			int line2 = buildLine(f, c, e);
			int line3 = buildLine(d, e, b);

			long attempt = buildAttempt(0, 0, line1, line2, line3);

			greatestAttempt = Math.max(greatestAttempt, attempt);
		}

		return extractString(greatestAttempt).equals("432621513");
	}

	public long solve(boolean printResults) {
		// TODO, I don't have a proof that the spindles should be in order. I can
		// say that five greatest values (6-10) must all be on the outside,
		// because otherwise one of the lower values (1-5) would be in the outer
		// ring, making that number the new lowest value, since 1-5 is all less than 6.
		// Also, this isn't that rediculous to do by hand. First, you have to just know that 6-10 has to go on the outside, and 1-5 on the inside.
		// then you want to maximize the 6-line. so you try the following:

		/*
		 *     a
		 *       \
		 *          b      d
		 * 	     /    \   /
		 * 	   i        c
		 * 	 /  \      /
		 * j     g -- e -- f
		 * 	      \
		 *          h
		 * 
		 * 
		 * Here are all of the calculations you need to do (easier to visualize if you draw it out):
		 * a=6, b=5, c=4
		 * => total=15
		 *    try d=10
		 *    => e=1, g=2/3, f=12/11 =><=
		 *    try d=9
		 *    => e=2, g=3 (if g=1, f=12 =><=), f=10, h=8 (if h=7, i=5=b =><=), i=1, i+g+h=12 =><=
		 *    try d=8
		 *    => e=3, i/g must be 1/2, h=12 =><=
		 *    try d=7
		 *    => e=4=c =><=
		 * a=6,b=5,c=3
		 * => total=14
		 *    try 10 (maximizes string)
		 *    => e=4
		 *       try f=9 (also maximizes string)
		 *       => g=1, i=2, h=8, j=7
		 * 
		 */

		int a = 6, d = 10, f = 9, h = 8, j = 7;

		boolean[] usedNumbers = new boolean[6];

		long bestResult = 0;
		for (int b = 1; b <= 5; b++) {
			for (int num = 1; num < 6; num++)
				usedNumbers[num] = false;
			usedNumbers[b] = true;

			int e = a + b - d;
			if (e < 1 || e > 5 || usedNumbers[e])
				continue;

			int i = f + e - h;
			if (i < 1 || i > 5 || usedNumbers[i])
				continue;

			int c = i + j - a;
			if (c < 1 || c > 5 || usedNumbers[c])
				continue;

			int g = j + b - h;
			if (g < 1 || g > 5 || usedNumbers[g])
				continue;

			long result = Long.valueOf("" + a + b + c
					+ d + c + e
					+ f + e + g
					+ h + g + i
					+ j + i + b);
			bestResult = Math.max(bestResult, result);
		}

		if (printResults) {
			System.out.println("The largest 16-digit string for a \"magic\" 5-gon ring is " + bestResult);
		}

		return bestResult;
	}

	public long solveSlow(boolean printResults) {
		Iterator<Integer[]> it = CollectionTools.reversePermuteIterator(new Integer[] { 10, 9, 8, 7, 6, 5 });

		long greatestAttempt = 0;

		while (it.hasNext()) {
			Integer[] representation = it.next();
			int a = representation[0];
			int d = representation[1];
			int f = representation[2];
			int h = representation[3];
			int j = representation[4];
			int b = representation[5];

			int e = a + b - d;
			if (e < 1 || e > 4)
				continue;
			boolean[] usedNumbers = new boolean[] { false, false, false, false, false };
			usedNumbers[e] = true;

			int i = f + e - h;
			if (i < 1 || i > 4 || usedNumbers[i])
				continue;
			usedNumbers[i] = true;

			int g = j + b - h;
			if (g < 1 || g > 4 || usedNumbers[g])
				continue;
			usedNumbers[g] = true;

			int c = f + g - d;
			if (c < 1 || c > 4 || usedNumbers[c])
				continue;

			ArrayList<Integer> arr = new ArrayList<>();
			arr.add(a);
			arr.add(d);
			arr.add(f);
			arr.add(h);
			arr.add(j);

			int min = Collections.min(arr);

			int line1 = buildLine(a, b, c);
			int line2 = buildLine(d, c, e);
			int line3 = buildLine(f, e, g);
			int line4 = buildLine(h, g, i);
			int line5 = buildLine(j, i, b);

			long attempt;
			if (min == a)
				attempt = buildAttempt(line1, line2, line3, line4, line5);
			else if (min == d)
				attempt = buildAttempt(line2, line3, line4, line5, line1);
			else if (min == f)
				attempt = buildAttempt(line3, line4, line5, line1, line2);
			else if (min == h)
				attempt = buildAttempt(line4, line5, line1, line2, line3);
			else
				attempt = buildAttempt(line5, line1, line2, line3, line4);

			greatestAttempt = Math.max(greatestAttempt, attempt);
		}

		String bestString = extractString(greatestAttempt);

		if (printResults) {
			System.out.println("The longest 16-digit string for a \"magic\" 5-gon ring is " + bestString);
		}
		return Long.valueOf(bestString);
	}

	public String getTitle() {
		return "Problem 068: Magic 5-gon ring";
	}
}
