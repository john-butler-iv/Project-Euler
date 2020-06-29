package projectEuler;

class P009 extends Problem {

	@Override
	public long solve(boolean printResults) {

		for (int a = 1; a < 997; a++) {
			for (int b = a + 1; b < 998; b++) {

				if (isValid(a, b)) {
					int c = (1000 - a - b);

					int product = (a * b * c);
					if (printResults)
						System.out.println(
								(a * b * c) + " is the product of the only Pythagorean triplet where a + b + c = 1000");

					return product;
				}
			}
		}
		return -1;
	}

	// The algebra justifying the function below
	// {
	// a² + b² = c²
	// a + b + c = 1000
	// }
	// c = 1000 - (a + b)
	// a² + b² = (1000-(a+b))²
	// a² + b² = 1,000,000 - 2000(a+b) + (a+b)²
	// a² + b² = 1,000,000 - 2000(a+b) + a² + 2ab + b²
	// 0 = 1,000,000 - 2000(a + b) + 2ab
	// 0 = 500,000 - 1000(a + b) + ab
	// 1000(a+b) + ab = 500,000
	private static boolean isValid(int a, int b) {
		return 1000 * (a + b) - a * b == 500000;
	}

	@Override
	public String getTitle() {
		return "Problem 009: Special Pythagoean Triplet";
	}

}
