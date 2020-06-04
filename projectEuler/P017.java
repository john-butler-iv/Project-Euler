package projectEuler;

class P017 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 1000;
	}

	@Override
	public long solve(Integer maxN, boolean printResults) {
		int total = 0;

		for (int i = 1; i <= maxN; ++i)
			total += numbersToLetters(i);

		if (printResults)
			System.out.println(total + " is the sum of the number of letters of 1-" + maxN);

		return total;
	}

	private static int numbersToLetters(int n) {
		if (n == 1000)
			return 11;// one thousand
		if (n < 10) {
			switch (n) {
				case 1:// one
				case 2:// two
				case 6:// six
					return 3;
				case 4:// four
				case 5:// five
				case 9:// nine
					return 4;
				case 3:// three
				case 7:// seven
				case 8:// eight
					return 5;
				case 0:// if n is 0 it's because there's a double digit number, and you don't say
						// "twenty-zero" for example
					return 0;
			}
		}
		if (n < 100) {
			if (n < 20) {
				switch (n) {
					case 10: // ten
						return 3;
					case 11:// eleven
					case 12:// twelve
						return 6;
					case 15:// fifteen
						return 7;
					case 13:// thirteen
					case 18:// eighteen
						return 8;
					default:
						return numbersToLetters(n % 10) + 4;// teen
				}
			}
			if (n < 30)
				return numbersToLetters(n % 10) + 6;// twenty
			if (n < 40)
				return numbersToLetters(n % 10) + 6;// thirty
			if (n < 50)
				return numbersToLetters(n % 10) + 5;// forty
			if (n < 60)
				return numbersToLetters(n % 10) + 5;// fifty
			if (n < 70)
				return numbersToLetters(n % 10) + 5;// sixty
			if (n < 80)
				return numbersToLetters(n % 10) + 7;// seventy
			if (n < 90)
				return numbersToLetters(n % 10) + 6;// eighty
			return numbersToLetters(n % 10) + 6;// ninety
		}
		if (n % 100 != 0)
			return (numbersToLetters(n / 100) + numbersToLetters(n % 100) + 10);// _ hundred _ _
		return numbersToLetters(n / 100) + 7;// _ hundred
	}

	@Override
	protected Integer getTestParameter() {
		return 5;
	}

	@Override
	protected long getTestSolution() {
		return 19;
	}

	@Override
	public String getTitle() {
		return "Problem 017: Number Letter Counts";
	}

}
