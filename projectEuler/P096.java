package projectEuler;

import java.io.File;
import java.util.Scanner;

public class P096 extends ParameterizedProblem<File> {

	private static class Tile {
		boolean[] hints;
		int number;

		public Tile(int number) {
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

		public Tile(Tile tile) {
			this.number = tile.number;
			this.hints = new boolean[tile.hints.length];
			for (int i = 0; i < this.hints.length; i++)
				this.hints[i] = tile.hints[i];
		}

		public String toString() {
			return String.valueOf(number);
		}

		public boolean equals(Tile tile) {
			for (int i = 0; i < hints.length; i++)
				if (this.hints[i] != tile.hints[i])
					return false;
			if (this.number != tile.number)
				return false;
			return true;
		}

	}

	public File getDefaultParameter() {
		return new File("p096.txt");
	}

	public long solve(File boards, boolean printResults) {
		try {
			Scanner scanner = new Scanner(boards);

			Tile[][] board;
			int sum = 0;

			while (scanner.hasNext()) {
				board = getBoard(scanner);

				board = solve(board);

				sum += getNum(board);
			}

			if (printResults)
				System.out.println(sum + " is the sum of the first three digits of all solved boards.");
			return sum;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static Tile[][] solve(Tile[][] board) {
		while (!isSolved(board)) {

			Tile[][] beginning;
			do {
				beginning = copy(board);
				board = setHints(board);
				board = setNumber(board);

				if (isSolved(board))
					return board;
				if (isInvalid(board))
					return null;

			} while (!equal(board, beginning));

			// we "guess and check" all the hints
			Tile[][] attempt = copy(board);

			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[0].length; c++)
					if (attempt[r][c].number == 0)

						for (int i = 1; i < attempt[r][c].hints.length; i++)
							if (attempt[r][c].hints[i]) {

								attempt[r][c].number = i;
								attempt = solve(attempt);

								if (attempt == null) {
									board[r][c].hints[i] = false;
									attempt = copy(board);
								} else if (isSolved(attempt))
									return attempt;
							}
			}
		}
		return null;
	}

	private static Tile[][] setHints(Tile[][] board) {
		// set column hints
		for (int c = 0; c < board[0].length; c++) {
			boolean[] numbers = new boolean[10];
			// find numbers in col
			for (int r = 0; r < board.length; r++)
				if (board[r][c].number != 0)
					numbers[board[r][c].number] = true;

			// set hints
			for (int r = 0; r < board.length; r++)
				for (int i = 0; i < numbers.length; i++)
					if (numbers[i])
						board[r][c].hints[i] = false;
		}

		// set row hints
		for (int r = 0; r < board.length; r++) {
			boolean[] numbers = new boolean[10];
			// find numbers in col
			for (int c = 0; c < board[0].length; c++)
				if (board[r][c].number != 0)
					numbers[board[r][c].number] = true;

			// set hints
			for (int c = 0; c < board.length; c++)
				for (int i = 0; i < numbers.length; i++)
					if (numbers[i])
						board[r][c].hints[i] = false;
		}
		// set sub-board hints
		for (int R = 0; R < 3; R++) {
			for (int C = 0; C < 3; C++) {
				boolean[] numbers = new boolean[10];
				// find numbers in sub grid
				for (int r = 0; r < 3; r++)
					for (int c = 0; c < 3; c++)
						if (board[R * 3 + r][C * 3 + c].number != 0)
							numbers[board[R * 3 + r][C * 3 + c].number] = true;

				// set hints
				for (int r = 0; r < 3; r++)
					for (int c = 0; c < 3; c++)
						for (int i = 0; i < numbers.length; i++)
							if (numbers[i])
								board[R * 3 + r][C * 3 + c].hints[i] = false;

			}
		}

		// TODO maybe check for lines of hints

		return board;
	}

	private static Tile[][] setNumber(Tile[][] board) {
		boolean doneDid = false;

		// if only one possibility, set it to be that
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (board[r][c].number == 0) {
					int cnt = 0;
					int index = -1;
					for (int i = 1; i < board[r][c].hints.length; i++) {
						if (board[r][c].hints[i]) {
							cnt++;
							index = i;
						}
					}
					if (cnt == 1)
						if (!doneDid) {
							board[r][c].number = index;
							doneDid = true;
						}
				}
			}
		}

		// if only one in zone, do that
		// for columns

		for (int c = 0; c < board[0].length; c++) {
			int[] numbersCnt = new int[10];
			int[] previousIndex = new int[10];

			for (int r = 0; r < board.length; r++)
				for (int i = 0; i < board[r][c].hints.length; i++)
					if (board[r][c].number == 0)
						if (board[r][c].hints[i]) {
							numbersCnt[i]++;
							previousIndex[i] = r;
						}

			for (int i = 1; i < numbersCnt.length; i++)
				if (numbersCnt[i] == 1 && !doneDid) {
					board[previousIndex[i]][c].number = i;
					doneDid = true;
				}
		}

		// for rows
		for (int r = 0; r < board.length; r++) {
			int[] numbersCnt = new int[10];
			int[] previousIndex = new int[10];

			for (int c = 0; c < board[0].length; c++)
				for (int i = 0; i < board[r][c].hints.length; i++)
					if (board[r][c].number == 0)
						if (board[r][c].hints[i]) {
							numbersCnt[i]++;
							previousIndex[i] = c;
						}
			for (int i = 0; i < numbersCnt.length; i++)
				if (numbersCnt[i] == 1)
					if (!doneDid) {
						board[r][previousIndex[i]].number = i;
						doneDid = true;
					}
		}

		// for sub-boards
		for (int R = 0; R < 3; R++) {
			for (int C = 0; C < 3; C++) {
				int[] numbersCnt = new int[10];
				int[] previousR = new int[10];
				int[] previousC = new int[10];

				for (int r = 0; r < 3; r++)
					for (int c = 0; c < 3; c++)
						if (board[R * 3 + r][C * 3 + c].number == 0)
							for (int i = 0; i < board[R * 3 + r][C * 3 + c].hints.length; i++)
								if (board[R * 3 + r][C * 3 + c].hints[i]) {
									numbersCnt[i]++;
									previousR[i] = R * 3 + r;
									previousC[i] = C * 3 + c;
								}
				for (int i = 0; i < numbersCnt.length; i++)
					if (numbersCnt[i] == 1)
						if (!doneDid) {
							board[previousR[i]][previousC[i]].number = i;
							doneDid = true;
						}

			}
		}
		return board;
	}

	private static boolean equal(Tile[][] board1, Tile[][] board2) {
		if (board1.length != board2.length || board1[0].length != board2[0].length)
			return false;

		for (int r = 0; r < board1.length; r++)
			for (int c = 0; c < board1[0].length; c++)
				if (!board1[r][c].equals(board2[r][c]))
					return false;

		return true;
	}

	private static Tile[][] copy(Tile[][] board) {
		Tile[][] newBoard = new Tile[board.length][board[0].length];
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board.length; c++)
				newBoard[r][c] = new Tile(board[r][c]);
		return newBoard;
	}

	private static Tile[][] getBoard(Scanner scanner) {
		Tile[][] board = new Tile[9][9];
		for (int i = 0; i < 9; i++) {
			String row = scanner.nextLine();
			for (int j = 0; j < 9; j++)
				board[i][j] = new Tile(row.charAt(j) - '0');
		}

		return board;
	}

	private static boolean isInvalid(Tile[][] board) {
		if (board == null)
			return true;

		// check for doubles in same area

		// check cols
		for (int c = 0; c < board[0].length; c++) {
			boolean[] numbers = new boolean[10];
			for (int r = 0; r < board.length; r++)
				if (board[r][c].number != 0) {
					if (numbers[board[r][c].number])
						return true;
					numbers[board[r][c].number] = true;
				}

		}

		// check rows
		for (int r = 0; r < board.length; r++) {
			boolean[] numbers = new boolean[10];
			for (int c = 0; c < board[0].length; c++)
				if (board[r][c].number != 0) {
					if (numbers[board[r][c].number])
						return true;
					numbers[board[r][c].number] = true;
				}

		}
		// check sub-boards
		for (int R = 0; R < 3; R++) {
			for (int C = 0; C < 3; C++) {
				boolean[] numbers = new boolean[10];
				for (int r = 0; r < 3; r++)
					for (int c = 0; c < 3; c++)
						if (board[R * 3 + r][C * 3 + c].number != 0) {
							if (numbers[board[R * 3 + r][C * 3 + c].number])
								return true;

							numbers[board[R * 3 + r][C * 3 + c].number] = true;
						}
			}
		}

		// check for tiles with no hints
		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				int cnt = 0;
				for (int i = 1; i < board[r][c].hints.length; i++) {
					if (board[r][c].number != 0 || board[r][c].hints[i]) {
						cnt++;
						break;
					}
				}
				if (cnt == 0)
					return true;
			}
		}

		// check for areas with no possibility of getting a number

		// check cols
		for (int c = 0; c < board[0].length; c++) {
			boolean[] numbers = new boolean[10];
			for (int r = 0; r < board.length; r++)
				if (board[r][c].number != 0)
					numbers[board[r][c].number] = true;
				else {
					for (int i = 1; i < board[r][c].hints.length; i++) {
						if (board[r][c].hints[i])
							numbers[i] = true;
					}
				}
			for (int i = 1; i < numbers.length; i++) {
				if (!numbers[i])
					return false;
			}
		}

		// check rows
		for (int r = 0; r < board.length; r++) {
			boolean[] numbers = new boolean[10];
			for (int c = 0; c < board[0].length; c++)
				if (board[r][c].number != 0)
					numbers[board[r][c].number] = true;
				else {
					for (int i = 1; i < board[r][c].hints.length; i++) {
						if (board[r][c].hints[i])
							numbers[i] = true;
					}
				}
			for (int i = 1; i < numbers.length; i++) {
				if (!numbers[i])
					return false;

			}
		}
		// check sub-boards
		for (int R = 0; R < 3; R++) {
			for (int C = 0; C < 3; C++) {
				boolean[] numbers = new boolean[10];
				for (int r = 0; r < 3; r++)
					for (int c = 0; c < 3; c++) {
						if (board[R * 3 + r][C * 3 + c].number != 0)
							numbers[board[R * 3 + r][C * 3 + c].number] = true;
						else {
							for (int i = 1; i < board[R * 3 + r][C * 3 + c].hints.length; i++) {
								if (board[R * 3 + r][C * 3 + c].hints[i])
									numbers[i] = true;
							}
						}
						for (int i = 1; i < numbers.length; i++) {
							if (!numbers[i])
								return false;
						}
					}
			}
		}
		return false;
	}

	private static boolean isSolved(Tile[][] board) {
		if (board == null)
			return false;
		if (board.length != 9 || board[0].length != 9)
			return false;

		// clever idea if I'm not just randomly guessing, but that's not how I decided
		// to go about it
		// return board[0][0].number != 0 && board[0][1].number != 0 &&
		// board[0][2].number != 0;

		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (board[r][c].number == 0)
					return false;
			}
		}

		return !isInvalid(board);
	}

	private static int getNum(Tile[][] board) {
		if (isSolved(board))
			return Integer.valueOf(board[0][0].number + "" + board[0][1].number + "" + board[0][2].number);
		return 0;
	}

	@Override
	public File getTestParameter() {
		return new File("p096test.txt");
	}

	@Override
	public long getTestSolution() {
		return 485;
	}

	@Override
	public String getTitle() {
		return "Problem 096: Su Doku";
	}
}
