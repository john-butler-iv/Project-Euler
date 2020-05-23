package projectEuler;

public class P040 extends Problem {

	@Override
	public boolean test() {
		int digitCounter = 1;
		int currentDigit = 1;

		while (digitCounter <= 12) {
			String digitString = String.valueOf(currentDigit++);
			for (int i = 0; i < digitString.length(); ++i)
				if (digitCounter++ == 12)
					return digitString.charAt(i) == '1';
		}
		return false;
	}

	@Override
	public long solve(boolean printResults) {
		int product = 1;
		int digitCounter = 1;
		int currentDigit = 1;

		while (digitCounter <= 1000000) {
			String digitString = String.valueOf(currentDigit);
			for (int i = 0; i < digitString.length(); ++i) {
				switch (digitCounter) {
					case 1:
					case 10:
					case 100:
					case 1000:
					case 10000:
					case 100000:
					case 1000000:
						product *= digitString.charAt(i) - '0';
						break;
					default:
						break;
				}

				digitCounter++;
			}
			currentDigit++;
		}
		if (printResults)
			System.out.println(product
					+ " is the product of the 1st, 10th, 100th, 1000th, 10,000th, 100,000th, and 1,000,000th digits of the fractional part of Champernowne's constant");
		return product;
	}

	@Override
	public String getTitle() {
		return "Problem 040: Champernowne's constant";
	}

}
