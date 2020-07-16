package projectEuler;

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class P096 extends ParameterizedProblem<File> {

	private static class Tile {
		boolean[] hints;
		int number;

		public Tile(int number) {
			if(number != 0) {
				this.number = number;
				hints = new boolean[10];
			} else {
				hints = new boolean[10];
				for(int i = 0; i < hints.length; i++)
					hints[i] = true;
				this.number = 0;
			}
		}

		public Tile(Tile tile) {
			this.number = tile.number;
			this.hints = new boolean[tile.hints.length];
			for(int i = 0; i < this.hints.length; i++)
				this.hints[i] = tile.hints[i];
		}

		public boolean equals(Tile tile) {
			if(this.number != tile.number)
				return false;
			return Arrays.mismatch(this.hints, tile.hints) == -1;
		}

	}
	@Override
	public File getDefaultParameter(){
		return new File("p096test.txt");
	}

	@Override
	public long solve(File file, boolean printResults){
		try {
			Scanner scanner = new Scanner(file);

			Tile[][] board;
			int sum = 0;

			int cnt = 1;

			while(scanner.hasNext()) {
				board = new Tile[9][9];

				// we don't care about the grid number
				scanner.nextLine();

				for(int i = 0; i < 9; i++) {
					String row = scanner.nextLine();
					for(int j = 0; j < 9; j++)
						board[i][j] = new Tile(row.charAt(j) - '0');
				}

				board = solveBoard(board);

				for(int r = 0; r < board.length; r++)
					for(int c = 0; c < board[0].length; c++)
						System.out.println(board[r][c].number);

				int num = getNum(board);
				sum += num;
			}
			if(printResults)
				System.out.println(sum + " is the sum of the first three digits of all of the SuDoku puzzles");
			return sum;
		} catch(Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	private Tile[][] copy(Tile[][] board){
		Tile[][] newBoard = new Tile[board.length][board[0].length];
		for(int i = 0 ; i < newBoard.length; i++)
			newBoard[i] = Arrays.copyOf(board[i], board[i].length);
		return newBoard;
	}
	private boolean equal(Tile[][] b1, Tile[][] b2){
		for(int r = 0; r < b1.length; r++)
			for(int c = 0; c < b1[0].length; c++)
				if(!b1[r][c].equals(b2[r][c]))
					return false;
		return true;
	}

	private void printboard(Tile[][] board){
			for(int r = 0; r < board.length; r++){
				for(int c= 0; c < board[0].length; c++){
					System.out.print(board[r][c].number);
					if(c % 3 == 2)
						System.out.print(" ");
				}
				System.out.println();
				if(r% 3 == 2)
					System.out.println();
			}
			System.out.println();
			System.out.println();
	}

	private Tile[][] solveBoard(Tile[][] board){
		while(!isSolved(board)) {
			Tile[][] orig;
			do {
				orig = copy(board);
				board = setHints(board);
				board = setNumber(board);
			} while(!equal(orig, board));

			if(isSolved(board))
				return board;
			if(isInvalid(board))
				return null;

			//we "guess and check" all the hints
			Tile[][] attempt = copy(board);

			printBoard(board);

			for(int r = 0; r < board.length; r++) {
				for(int c = 0; c < board[0].length; c++) {
					if(attempt[r][c].number == 0) {

						// try each hint
						for(int i = 1; i < attempt[r][c].hints.length; i++) {
							if(attempt[r][c].hints[i]) {

								attempt[r][c].number = i;
								attempt = solveBoard(attempt);

								// if impossible, we know to rule out
								if(attempt == null) {
									board[r][c].hints[i] = false;
									attempt = copy(board);
									// if we find a solution, we're done
								} else if(isSolved(attempt)){
									return attempt;
								}
							}
						}
					}
				}
			}
		}

		// we have gone through all possible boardstates and found no solution
		return null;	
	}

	private static Tile[][] setHints(Tile[][] board) {
		//set column hints
		for(int c = 0; c < board[0].length; c++) {
			boolean[] numbers = new boolean[10];
			//find numbers in col
			for(int r = 0; r < board.length; r++)
				if(board[r][c].number != 0)
					numbers[board[r][c].number] = true;


			//set hints
			for(int r = 0; r < board.length; r++)
				for(int i = 0; i < numbers.length; i++)
					if(numbers[i])
						board[r][c].hints[i] = false;
		}

		//set row hints
		for(int r = 0; r < board.length; r++) {
			boolean[] numbers = new boolean[10];
			//find numbers in col
			for(int c = 0; c < board[0].length; c++)
				if(board[r][c].number != 0)
					numbers[board[r][c].number] = true;

			//set hints
			for(int c = 0; c < board.length; c++)
				for(int i = 0; i < numbers.length; i++)
					if(numbers[i])
						board[r][c].hints[i] = false;
		}
		//set sub-board hints
		for(int R = 0; R < 3; R++) {
			for(int C = 0; C < 3; C++) {
				boolean[] numbers = new boolean[10];
				//find numbers in sub grid
				for(int r = 0; r < 3; r++)
					for(int c = 0; c < 3; c++)
						if(board[R * 3 + r][C * 3 + c].number != 0)
							numbers[board[R * 3 + r][C * 3 + c].number] = true;

				//set hints
				for(int r = 0; r < 3; r++)
					for(int c = 0; c < 3; c++)
						for(int i = 0; i < numbers.length; i++)
							if(numbers[i])
								board[R * 3 + r][C * 3 + c].hints[i] = false;


			}
		}

		return board;
	}

	private static boolean isInvalid(Tile[][] board) {
		if(board == null)
			return true;

		//check for doubles in same area

		//check cols
		for(int c = 0; c < board[0].length; c++) {
			boolean[] numbers = new boolean[10];
			for(int r = 0; r < board.length; r++)
				if(board[r][c].number != 0) {
					if(numbers[board[r][c].number])
						return true;
					numbers[board[r][c].number] = true;
				}

		}

		//check rows
		for(int r = 0; r < board.length; r++) {
			boolean[] numbers = new boolean[10];
			for(int c = 0; c < board[0].length; c++)
				if(board[r][c].number != 0) {
					if(numbers[board[r][c].number])
						return true;
					numbers[board[r][c].number] = true;
				}

		}
		//check sub-boards
		for(int R = 0; R < 3; R++) {
			for(int C = 0; C < 3; C++) {
				boolean[] numbers = new boolean[10];
				for(int r = 0; r < 3; r++)
					for(int c = 0; c < 3; c++)
						if(board[R * 3 + r][C * 3 + c].number != 0) {
							if(numbers[board[R * 3 + r][C * 3 + c].number])
								return true;

							numbers[board[R * 3 + r][C * 3 + c].number] = true;
						}
			}
		}

		//check for tiles with no hints
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[0].length; c++) {
				int cnt = 0;
				for(int i = 1; i < board[r][c].hints.length; i++) {
					if(board[r][c].number != 0 || board[r][c].hints[i]) {
						cnt++;
						break;
					}
				}
				if(cnt == 0)
					return true;
			}
		}

		//check for areas with no possibility of getting a number

		//check cols
		for(int c = 0; c < board[0].length; c++) {
			boolean[] numbers = new boolean[10];
			for(int r = 0; r < board.length; r++)
				if(board[r][c].number != 0)
					numbers[board[r][c].number] = true;
				else {
					for(int i = 1; i < board[r][c].hints.length; i++) {
						if(board[r][c].hints[i])
							numbers[i] = true;
					}
				}
			for(int i = 1; i < numbers.length; i++) {
				if(!numbers[i])
					return false;
			}
		}

		//check rows
		for(int r = 0; r < board.length; r++) {
			boolean[] numbers = new boolean[10];
			for(int c = 0; c < board[0].length; c++)
				if(board[r][c].number != 0)
					numbers[board[r][c].number] = true;
				else {
					for(int i = 1; i < board[r][c].hints.length; i++) {
						if(board[r][c].hints[i])
							numbers[i] = true;
					}
				}
			for(int i = 1; i < numbers.length; i++) {
				if(!numbers[i])
					return false;

			}
		}
		//check sub-boards
		for(int R = 0; R < 3; R++) {
			for(int C = 0; C < 3; C++) {
				boolean[] numbers = new boolean[10];
				for(int r = 0; r < 3; r++)
					for(int c = 0; c < 3; c++) {
						if(board[R * 3 + r][C * 3 + c].number != 0)
							numbers[board[R * 3 + r][C * 3 + c].number] = true;
						else {
							for(int i = 1; i < board[R * 3 + r][C * 3 + c].hints.length; i++) {
								if(board[R * 3 + r][C * 3 + c].hints[i])
									numbers[i] = true;
							}
						}
						for(int i = 1; i < numbers.length; i++) {
							if(!numbers[i])
								return false;
						}
					}
			}
		}
		return false;
	}

	private static boolean isSolved(Tile[][] board) {
		if(board == null)
			return false;
		if(board.length != 9 || board[0].length != 9)
			return false;

		//clever idea if I'm not just randomly guessing, but that's not how I decided to go about it
		//return board[0][0].number != 0 && board[0][1].number != 0 && board[0][2].number != 0;

		for(int r = 0; r < board.length; r++)
			for(int c = 0; c < board[0].length; c++)
				if(board[r][c].number == 0)
					return false;

		return !isInvalid(board);
	}

	private static int getNum(Tile[][] board) {
		if(isSolved(board))
			return Integer.valueOf(board[0][0].number + "" + board[0][1].number + "" + board[0][2].number);
		return 0;
	}

	@Override
	public File getTestParameter(){
		return new File("p096test.txt");
	}

	@Override
	public long getTestSolution(){
		return 483;
	}

	@Override
	public String getTitle(){
		return "Problem 096: Su Doku";
	}
}
