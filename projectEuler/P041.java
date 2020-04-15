package projectEuler;

public class P041 extends Problem {

	@Override
	long solve(boolean printResults) {
		// floor plus one ensures that we get all factors of every number, even if
		// 987,654,321 is a square.
		int limit = (int) Math.sqrt(987654321) + 1;
		PrimeFinder pf = new PrimeFinder(limit);
		String origStr = "987654321";
		String str = origStr;

		while (!pf.isPrime(Integer.valueOf(str))) {
			str = CollectionTools.prevPermute(str);

			// if we ran out of permutations on this length, we need to go one shorter
			if (str.equals("")) {
				origStr = origStr.substring(1);
				str = origStr;
			}
		}

		if (printResults)
			System.out.println(str + " is the largest prime n-digit pandigital number.");
		return Long.valueOf(str);
	}

	@Override
	String getTitle() {
		return "Problem 041: Pandigital prime";
	}
}