package projectEuler;

class P004 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 3;
	}

	
	@Override
	public long solve(Integer digits, boolean printResults) {
		int big = 0;
		final int MIN = (int) Math.pow(10, digits-1);
		final int MAX = MIN * 10 - 1;

		// iterate through all n-digit numbers
		for (int i = MAX; i > MIN; i--) {
			for (int j = MAX; j >= i; j--) {
				int product = j * i;
				if (product > big && EulerTools.isPalindrome(String.valueOf(product)))
					big = product;
			}
		}
		if (printResults)
			System.out.println(big + " is the largest palindrome made from the product of two " + digits + "-digit numbers.");
		return big;
	}

	@Override
	protected Integer getTestParameter() {
		return 2;
	}

	@Override
	protected long getTestSolution() {
		return 9009;
	}

	@Override
	public String getTitle() {
		return "Problem 004: Largest Palindrome Product";
	}

}
