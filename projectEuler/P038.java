package projectEuler;

class P038 extends Problem {

	public static void main(String[] args) {
		new P038().solve(true);
	}

	@Override
	long solve(boolean printResults) {
		// want to search backward, since the largest number will be closer to the end
		// than the beginning
		for (int i = 9999; i > 999; i--) {
			// technically I could check the higher multiples of the number, but the largest
			// pandigital multiple will be too large to concatenate with larger multiples.

			// form the concatenated string
			String str = String.valueOf(i) + String.valueOf(i * 2);

			if (EulerTools.isPandigital(str)) {
				if (printResults)
					System.out
							.println(str + " is the largest pandigital concatenation of an integer and its multiples.");
				return Long.valueOf(str);
			}
		}

		// In practice, a result is found, but it's still good practice to put this
		// here. Also the Java complier isn't smart enough to notice that this won't be
		// reached, so I a default return value so that it will compile.
		if (printResults)
			System.out.println("Could not find the largest pandigital concatenation of an integer and its multiples.");
		return -1;
	}

	@Override
	String getTitle() {
		return "Pandigital Multiples";
	}

}