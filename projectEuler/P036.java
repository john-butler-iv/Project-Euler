package projectEuler;

class P036 extends Problem {

	@Override
	public boolean test() {
		int num = 585;
		return EulerTools.isPalindrome(Integer.toString(num)) && EulerTools.isPalindrome(Integer.toBinaryString(num));
	}

	@Override
	public long solve(boolean printResults) {
		int sum = 0;

		for (int i = 1; i < 1000000; i++) {
			// determine base 10 palindrome
			String decStr = Integer.toString(i);
			if (EulerTools.isPalindrome(decStr)) {

				// determine base 2 palindrome
				String binStr = Integer.toBinaryString(i);
				if (EulerTools.isPalindrome(binStr))
					sum += i;
			}
		}

		if (printResults)
			System.out.println(
					sum + " is the sum of all numbers, less than 1,000,000, who are palindromes in bases 10 and 2.");
		return sum;
	}

	@Override
	public String getTitle() {
		return "Problem 036: Double Bases Palindromes";
	}

}
