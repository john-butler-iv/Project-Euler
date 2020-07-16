package projectEuler;

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Iterator;

public class P096 extends ParameterizedProblem<File> {

	private class TileIterator implements Iterator<Tile[]> {
		// could use an enum but this is less work
		private final static int SUBGRID = 0;
		private final static int ROW = 1;
		private final static int COLUMN = 2;
		private final static int END = 3;

		private final Tile[][] board;
		private int currGroup;
		// represents progress through group (there's 9 rows/columns/subgrids)
		private int groupId;

		public TileIterator(final Tile[][] board) {
			this.board = board;
			currGroup = SUBGRID;
			groupId = 0;
		}

		@Override
		public boolean hasNext() {
			return currGroup != END;
		}

		public void reset() {
			currGroup = SUBGRID;
			groupId = 0;
		}

		@Override
		public Tile[] next() {
			final Tile[] returnVar = new Tile[9];

			int i = 0;
			switch (currGroup) {
				case SUBGRID:
					final int r = (groupId / 3) * 3;
					final int c = (groupId / 3) * 3;

					// System.out.println("SUBGRID: groupId: " + groupId + ", r: " + r + " c: " +
					// c);
					for (int r1 = 0; r1 < 3; r1++)
						for (int c1 = 0; c1 < 3; c1++)
							returnVar[i++] = board[r + r1][c + c1];
					break;
				case ROW:
					// System.out.println("ROW: groupId: " + groupId);
					for (; i < 9; i++)
						returnVar[i] = board[groupId][i];
					break;
				case COLUMN:
					// System.out.println("COLUMN: groupId: " + groupId);
					for (; i < 9; i++)
						returnVar[i] = board[i][groupId];
					break;
			}

			if (groupId >= 8) {
				groupId = 0;
				currGroup++;
			} else
				groupId++;

			return returnVar;
		}
	}

	private class Tile {
		boolean[] hints;
		int number;

		public Tile(final int number) {
			if (number != 0) {
				this.number = number;
				hints = new boolean[10];
			} else {
				hints = new boolean[10];
				for (int i = 0; i < hints.length; i++)
					hints[i] = true;
				this.number = 0;
			}
		}

		public Tile(final Tile tile) {
			this.number = tile.number;
			this.hints = new boolean[tile.hints.length];
			for (int i = 0; i < this.hints.length; i++)
				this.hints[i] = tile.hints[i];
		}

		public boolean equals(final Tile tile) {
			if (this.number != tile.number)
				return false;
			return Arrays.mismatch(this.hints, tile.hints) == -1;
		}

	}

	@Override
	public File getDefaultParameter() {
		return new File("p096test.txt");
	}

	@Override
	public long solve(final File file, final boolean printResults) {
		try {
			final Scanner scanner = new Scanner(file);

			Tile[][] board;
			int sum = 0;

			final int cnt = 1;

			while (scanner.hasNext()) {
				board = new Tile[9][9];

				// we don't care about the grid number
				scanner.nextLine();

				for (int i = 0; i < 9; i++) {
					final String row = scanner.nextLine();
					for (int j = 0; j < 9; j++)
						board[i][j] = new Tile(row.charAt(j) - '0');
				}

				board = solveBoard(board);

				sum += getNum(board);
				// int num = getNum(board);
				// sum += num;
			}
			if (printResults)
				System.out.println(sum + " is the sum of the first three digits of all of the SuDoku puzzles");
			return sum;
		} catch (final Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private void printBoard(final Tile[][] board) {
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				System.out.print(board[r][c].number);
				if (c % 3 == 2)
					System.out.print("  ");
			}
			System.out.println();
			if (r % 3 == 2)
				System.out.println();
		}
	}

	private enum Status {
		UNSOLVED, SOLVED, INVALID;
	}

	private Status status(final Tile[][] board) {
		final TileIterator it = new TileIterator(board);

		boolean solved = true;

		boolean[] encountered;
		while (it.hasNext()) {
			encountered = new boolean[10];
			final Tile[] group = it.next();

			for (final Tile tile : group) {
				if (tile.number == 0)
					solved = false;
				else {
					if (encountered[tile.number])
						return Status.INVALID;

					encountered[tile.number] = true;
				}
			}
		}

		return solved ? Status.SOLVED : Status.UNSOLVED;
	}

	private Tile[][] copy(final Tile[][] board) {
		final Tile[][] newBoard = new Tile[board.length][board[0].length];
		for (int i = 0; i < newBoard.length; i++)
			newBoard[i] = Arrays.copyOf(board[i], board[i].length);
		return newBoard;
	}

	private boolean equal(final Tile[][] b1, final Tile[][] b2) {
		for (int r = 0; r < b1.length; r++)
			for (int c = 0; c < b1[0].length; c++)
				if (!b1[r][c].equals(b2[r][c]))
					return false;
		return true;
	}

	private Tile[] getSubGrid(final Tile[][] board, final int r, final int c) {
		final int subR = r / 3;
		final int subC = c / 3;

		final Tile[] subGrid = new Tile[8];
		int i = 0;

		for (int r1 = subR; r1 < subR + 3; r1++) {
			for (int c1 = subC; c1 < subC + 3; c1++) {
				if (r1 == r && c1 == c)
					continue;
				subGrid[i++] = board[r1][c1];
			}
		}

		return subGrid;
	}

	private Tile[][] solveBoard(final Tile[][] board) {
		if (board == null)
			return null;
		printBoard(board);
		final Status status = status(board);
		if (status == Status.SOLVED)
			return board;
		if (status == Status.INVALID)
			return null;

		final TileIterator it = new TileIterator(board);

		Tile[][] orig;
		do {
			orig = copy(board);

			/* * * * * * * * * * * */
			/* eliminate hints */
			/* * * * * * * * * * * */
			it.reset();
			while (it.hasNext()) {
				final Tile[] group = it.next();

				// records what numbers are commited i.e. what needs to be removed
				final boolean[] importantHints = new boolean[10];
				// records how many tiles have any specific number as a
				final int[] numHinters = new int[10];
				// contains tiles whose number == 0, organized by what hints they have
				final Tile[][] hinters = new Tile[10][9];
				for (final Tile tile : group) {
					if (tile.number == 0) {
						// store in hinters based on hints
						for (int i = 1; i < tile.hints.length; i++) {
							if (!tile.hints[i])
								continue;
							hinters[i][numHinters[i]++] = tile;
						}
					} else {
						// record that we have to strip hints for this number
						importantHints[tile.number] = true;
					}
				}

				// strip required hints
				for (int hint = 1; hint <= 9; hint++) {
					if (!importantHints[hint])
						continue;
					for (int i = 0; i < numHinters[hint]; i++)
						hinters[hint][i].hints[hint] = false;
				}
			}

			/* * * * * * * * * * * * * */
			/* do basic number solving */
			/* * * * * * * * * * * * * */
			// only one hint per tile
			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[0].length; c++) {
					// if we already know what the number is, we don't have to find it.
					if (board[r][c].number != 0)
						continue;
					boolean foundHint = false;
					int hint = 0;

					// count the hints
					for (int i = 1; i <= 9; i++) {
						if (board[r][c].hints[i]) {
							if (foundHint) {
								hint = 0;
								break;
							} else {
								foundHint = true;
								hint = i;
							}
						}
					}

					if (foundHint)
						board[r][c].number = hint;
				}
			}

			// only one hint in a group
			it.reset();
			Tile[] relevantTiles;
			int[] encountered;
			while (it.hasNext()) {
				encountered = new int[10];
				relevantTiles = new Tile[10];
				final Tile[] group = it.next();

				for (final Tile tile : group) {
					// if the number is already commited we don't care about it.
					if (tile.number != 0)
						continue;
					// count all of the hints
					for (int i = 1; i <= 9; i++) {
						if (!tile.hints[i])
							continue;
						encountered[i]++;
						relevantTiles[i] = tile;
					}
				}

				// actually assign number if applicable
				for (int i = 1; i <= 9; i++)
					if (encountered[i] == 1)
						relevantTiles[i].number = i;
			}
		} while (!equal(orig, board));

		// guess and check O(n!)
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board.length; c++) {
				if (board[r][c].number != 0)
					continue;
				for (int i = 1; i <= 9; i++) {
					if (!board[r][c].hints[i])
						continue;
					Tile[][] attempt = copy(board);
					attempt[r][c].number = i;
					attempt = solveBoard(attempt);
					if (attempt != null)
						return attempt;
					board[r][c].hints[i] = false;
				}
			}
		}
		return board;
	}

	private int getNum(final Tile[][] board) {
		return board[0][0].number * 100 + board[0][1].number * 10 + board[0][2].number;
	}

	@Override
	public File getTestParameter() {
		return new File("p096test.txt");
	}

	@Override
	public long getTestSolution() {
		return 483;
	}

	@Override
	public String getTitle() {
		return "Problem 096: Su Doku";
	}
}
