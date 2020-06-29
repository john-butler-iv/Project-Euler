package projectEuler;

class P034 extends Problem {

	@Override
	public boolean test() {
		int digitSum = 0;

		int[] digitFactorials = new int[] { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

		for (char c : String.valueOf(145).toCharArray())
			digitSum += digitFactorials[c - '0'];

		return digitSum == 145;
	}

	@Override
	public long solve(boolean printResults) {

		/* find factorials: */

		// we only need the to find a factorial for 0 - 9
		int[] factorials = new int[10];
		factorials[0] = 1;

		for (int i = 1; i < factorials.length; i++)
			factorials[i] = factorials[i - 1] * i;

		// the end result is stored in this number
		int sum = 0;

		/* Begin Computing */

		/*
		 * Adding digits to a number makes it grow much faster, O(10^n), than summing
		 * its digits' factorials, O(n). Clearly, for any length number, all 9's gives
		 * the maximum digit factorial, and the first case where the factorial of its
		 * digits is less than itself is 9,999,999, which gives 2,540,160, so the digits
		 * can only sum up to themselves at least until that point. With that said,
		 * 40,585 is actually the largest number with the property, but I don't have any
		 * way to lower the limit without having solved the problem already
		 */
		// this keeps track of each individual digit factorial sum, but I don't want to
		// reinitialize it everytime
		int digitSum;
		for (int i = 10; i < 2540160; i++) {
			digitSum = 0;

			// for each digit in the string, look up its factorial
			for (char c : String.valueOf(i).toCharArray())
				digitSum += factorials[c - '0'];

			if (digitSum == i)
				sum += i;
		}

		if (printResults)
			System.out.println(sum + " is the sum of all numbers whose digits factorialized sum to itself");
		return sum;
	}

	@Override
	public String getTitle() {
		return "Problem 034: Digit Factorials";
	}

}
