package projectEuler;

import java.math.BigInteger;

public class P055 extends Problem {

	@Override
	public boolean test() {
		return !isLychrel(349);
	}

	@Override
	public long solve(boolean printResults) {
		int lychrelCnt = 0;
		for (int i = 1; i < 10001; i++)
			if (isLychrel(i))
				lychrelCnt++;

		if (printResults)
			System.out.println("there are " + lychrelCnt + " lychrel numbers bellow 10,000.");
		return lychrelCnt;
	}

	private static boolean isLychrel(int num) {
		BigInteger bigNum = BigInteger.valueOf(num);
		return isLychrel(bigNum);
	}

	private static boolean isLychrel(BigInteger test) {
		for (int i = 0; i < 50; i++) {
			test = test.add(reverseNum(test));
			if (EulerTools.isPalindrome(test.toString()))
				return false;
		}
		return true;
	}

	private static BigInteger reverseNum(BigInteger i) {
		String str = i.toString();
		if (str.length() == 1)
			return i;

		str = CollectionTools.reverse(str);
		return new BigInteger(str);
	}

	@Override
	public String getTitle() {
		return "Problem 055: Lychrel Numbers";
	}

}
