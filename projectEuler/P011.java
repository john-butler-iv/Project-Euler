package projectEuler;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class P011 extends Problem {
	private int[][] grid;

	public P011() {
		grid = readData("p011.txt");
	}

	public boolean test() {
		return sumDir(6, 8, 1, 1) == 1788696;
	}

	private static int[][] readData(String fileName) {
		int[][] grid = new int[20][20];
		try {
			Scanner input = new Scanner(new File(fileName));

			for (int r = 0; r < 20; r++)
				for (int c = 0; c < 20; c++)
					grid[r][c] = input.nextInt();

			input.close();
		} catch (FileNotFoundException e) {
			System.out.println("cannot find file " + fileName);
			return null;
		}

		return grid;
	}

	@Override
	long solve(boolean printResults) {
		if (grid == null)
			return -1;

		int biggestProduct = 0;

		for (int r = 0; r < grid.length; ++r) {
			for (int c = 0; c < grid[0].length; ++c) {

				int pne = sumDir(r, c, -1, 1); // /
				int pe = sumDir(r, c, 0, 1); // -
				int pse = sumDir(r, c, 1, 1); // \
				int ps = sumDir(r, c, 1, 0); // |

				int p = Math.max(pne, Math.max(pe, Math.max(pse, ps)));
				if (p > biggestProduct)
					biggestProduct = p;
			}
		}

		if (printResults)
			System.out.println(biggestProduct + " is greatest product of 4 adjacent numbers in the same direction.");
		return biggestProduct;
	}

	private int sumDir(int r, int c, int dr, int dc) {
		if (r + 3 * dr < 0 || r + 3 * dr >= grid.length)
			return -1;
		if (c + 3 * dc < 0 || c + 3 * dc >= grid[0].length)
			return -1;

		int product = 1;
		for (int i = 0; i < 4; i++)
			product *= grid[r + i * dr][c + i * dc];

		return product;
	}

	@Override
	String getTitle() {
		return "Problem 011: Largest Product in a Grid";
	}

}