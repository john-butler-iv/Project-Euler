package projectEuler;

public class P043 extends Problem {
	@Override
	public boolean test() {
		return satisfiesConditions("1406357289") != 0;
	}

	@Override
	public long solve(boolean printResults) {
		long sum = 0;

		for (String str = "0123456789"; !str.equals("9876543210"); str = CollectionTools.permute(str))
			sum += satisfiesConditions(str);

		if (printResults)
			System.out.println(sum + " is the sum of all pandigital numbers with the required property");
		return sum;
	}

	private static long satisfiesConditions(String str) {
		int subStr = Integer.parseInt(str.substring(1, 4));
		if (subStr % 2 != 0)
			return 0;
		subStr = Integer.parseInt(str.substring(2, 5));
		if (subStr % 3 != 0)
			return 0;
		subStr = Integer.parseInt(str.substring(3, 6));
		if (subStr % 5 != 0)
			return 0;
		subStr = Integer.parseInt(str.substring(4, 7));
		if (subStr % 7 != 0)
			return 0;
		subStr = Integer.parseInt(str.substring(5, 8));
		if (subStr % 11 != 0)
			return 0;
		subStr = Integer.parseInt(str.substring(6, 9));
		if (subStr % 13 != 0)
			return 0;
		subStr = Integer.parseInt(str.substring(7));
		if (subStr % 17 != 0)
			return 0;

		return Long.parseLong(str);
	}

	@Override
	public String getTitle() {
		return "Problem 043: Sub-string divisibility";
	}
}
