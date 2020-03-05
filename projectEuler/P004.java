package projectEuler;

class P004 extends Problem {

	@Override
	long solve(boolean printResults) {
		int big = 0;
		// iterate through all 3 digit numbers
		for (int i = 999; i > 99; i--) {
			for (int j = i; j > 99; j--) {
				int product = j * i;
				if (product > big && EulerTools.isPalindrome(String.valueOf(product)))
					big = product;
			}
		}
		if (printResults)
			System.out.println(big + " is the largest palindrome made from the product of two 3-digit numbers.");
		return big;
	}

	public static void main(String[] args) {
		new P004().solve(true);
	}

	@Override
	String getTitle() {
		return "Problem 004: Largest Palindrome Product";
	}

}