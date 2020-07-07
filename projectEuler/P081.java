package projectEuler;

import java.io.File;
import java.util.Scanner;

public class P081 extends ParameterizedProblem<String>{
	private int SIZE;
	@Override
	public String getDefaultParameter(){
		return "p081.txt";
	}

	@Override
	public long solve(String filename, boolean printResults){
		int[][] mat = readData(filename);
		int[][] sum = new int[SIZE][SIZE];
			

		for(int r = SIZE - 1; r >= 0; r--){
			for(int c = SIZE - 1; c >= 0; c--){

				int downSum = Integer.MAX_VALUE;
				if(r != SIZE - 1)
					downSum = sum[r + 1][c];

				int rightSum = Integer.MAX_VALUE;
				if(c != SIZE - 1)
					rightSum = sum[r][c + 1];
				
				sum[r][c] = Math.min(downSum, rightSum) + mat[r][c];
			}
		}

		// this is a byproduct of how I assume out of bounds are Integer.MAX_VALUE
		sum[0][0] -= Integer.MAX_VALUE;

		if(printResults)
			System.out.println(sum[0][0] + " is the shortest path sum for the grid in the file " + filename);
		return sum[0][0];
	}

	private int[][] readData(String filename){
		int[][]mat = null;
		try{
			Scanner scanner = new Scanner(new File(filename));
			SIZE = scanner.nextInt();
			mat = new int[SIZE][SIZE];
			
			for(int r = 0; r < mat.length; r++)
				for(int c = 0; c < mat[0].length; c++)
					mat[r][c] = scanner.nextInt();
				
			scanner.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return mat;	
	}

	@Override
	public String getTestParameter(){
		return"p081test.txt";
	}

	@Override
	public long getTestSolution(){
		return 2427;
	}

	@Override
	public String getTitle(){
		return "Problem 081: Path Sum: Two Ways";
	}
}
