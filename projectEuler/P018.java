package projectEuler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class P018 extends ParameterizedProblem<String> {

	@Override
	public String getDefaultParameter() {
		return "p018.txt";
	}

	@Override
	public long solve(String treeFilename, boolean printResults) {
		int[] tree = readData(treeFilename);
		int[] sums = new int[tree.length];

		// using an inductive algorithm
		for (int i = tree.length - 1; i >= 0; --i) {
			// if you're at the bottom, you already have the maximal path
			int leftChild = leftChild(i);
			int rightChild = rightChild(i);
			if (leftChild >= tree.length || rightChild >= tree.length)
				sums[i] = tree[i];
			else
				sums[i] = Math.max(sums[leftChild], sums[rightChild]) + tree[i];
		}

		if (printResults)
			System.out.println("The longest path sum is " + sums[0] + ".");
		return sums[0];
	}

	private static int leftChild(int i) {
		int child = i + layerNumber(i) + 1;
		return child;
	}

	private static int rightChild(int i) {
		int child = leftChild(i) + 1;
		return child;
	}

	private static int layerNumber(int n) {
		return (int) Math.ceil(Math.sqrt(2 * n + 2.25) - 1.5);
	}

	private int[] readData(String filename) {
		try {
			Scanner input = new Scanner(new File(filename));

			// the first line contains the size of the tree
			int size = EulerTools.triangle(input.nextInt());
			int[] tree = new int[size];

			for (int t = 0; input.hasNext(); t++)
				tree[t] = input.nextInt();

			input.close();
			return tree;
		} catch (FileNotFoundException e) {
			System.out.println("Could not find file \"" + filename + "\"");
		}
		return null;
	}

	@Override
	public String getTitle() {
		return "Problem 018: Maximum Path Sum I";
	}

	@Override
	protected String getTestParameter() {
		return "p018test.txt";
	}

	@Override
	protected long getTestSolution() {
		return 23;
	}

}
