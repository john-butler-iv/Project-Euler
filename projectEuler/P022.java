package projectEuler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class P022 extends Problem {
	/*
	 * This is just a standard binary tree, I haven't tried to implement a more
	 * complicated one to better ensure a good balance. In the nameset, we get a
	 * maximum depth of 30 with 5163 items, but optimally, it would be 13, so it
	 * would improve things, but this is already good enough, I'd say; it still has
	 * a time complexity of about O(n log n). PS, putting everything in an array or
	 * list and then using mergesort or quicksort may have been a better idea to
	 * ensure O(n log n), but this already runs in 0ms, so I'm not going to refactor
	 */
	private class NameTree {
		private static final int SCORE = 0;
		private static final int POSITION = 1;
		NameTree left;
		NameTree right;
		String name;

		public NameTree(String name) {
			this.name = name;
		}

		public void insert(String name) {
			if (this.name.compareTo(name) > 0) {
				if (left == null)
					left = new NameTree(name);
				else
					left.insert(name);
			} else {
				if (right == null)
					right = new NameTree(name);
				else
					right.insert(name);
			}
		}

		public int totalScores() {
			return totalScores(new int[] { 0, 0 })[SCORE];
		}

		private int[] totalScores(int[] scorePos) {
			// by using an in order traversal, I guaruntee the name are positioned right
			// left
			if (left != null)
				scorePos = left.totalScores(scorePos);

			// this
			scorePos[POSITION]++;
			scorePos[SCORE] += this.score(scorePos[POSITION]);

			// right
			if (right != null)
				scorePos = right.totalScores(scorePos);

			return scorePos;
		}

		public int score(int position) {
			int score = 0;

			char[] charArray = name.toCharArray();
			for (int i = 0; i < charArray.length; ++i)
				score += charArray[i] - 'A' + 1;

			score *= position;

			return score;
		}
	}

	@Override
	public boolean test() {
		NameTree tree = new NameTree("COLIN");
		return tree.score(938) == 49714;
	}

	@Override
	public long solve(boolean printResults) {
		NameTree tree = readData("p022.txt");
		if (tree == null)
			return -1;

		int totalScore = tree.totalScores();

		if (printResults)
			System.out.println("the total of all name scores in the file is " + totalScore);

		return totalScore;
	}

	private NameTree readData(String filename) {
		NameTree tree = null;
		try {
			Scanner scanner = new Scanner(new File(filename));

			tree = new NameTree(scanner.next());

			while (scanner.hasNext())
				tree.insert(scanner.next().toUpperCase());

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return tree;
	}

	@Override
	public String getTitle() {
		return "Problem 022: Names Scores";
	}

}
