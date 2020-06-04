package projectEuler;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

class P013 extends Problem {

	private BigInteger[] nums;

	public P013() {
		nums = readData("p013.txt");
	}

	private static BigInteger[] readData(String filename) {
		BigInteger[] nums = new BigInteger[100];
		try {
			Scanner input = new Scanner(new File(filename));

			for (int i = 0; input.hasNext(); i++)
				nums[i] = new BigInteger(input.next());

			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("cannot find file " + filename);
		}

		return nums;
	}

	@Override
	public long solve(boolean printResults) {
		BigInteger sum = BigInteger.ZERO;

		for (int i = 0; i < nums.length; ++i)
			sum = sum.add(nums[i]);

		String sumStr = sum.toString().substring(0, 10);

		if (printResults)
			System.out.println(sumStr + " are first ten digits.");

		return Long.valueOf(sumStr);
	}

	@Override
	public String getTitle() {
		return "Problem 013: Large Sum";
	}

}
