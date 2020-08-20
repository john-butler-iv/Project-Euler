package projectEuler;

import java.util.ArrayList;

public class P078 extends ParameterizedProblem<Integer>{

	public Integer getDefaultParameter(){
		return 1000000;
	}

	public long solve(Integer divisor, boolean printResults){
		ArrayList<Integer> partitions = new ArrayList<>();
		partitions.add(0);
		partitions.add(1);
		
		for(int i = 2; partitions.get(i - 1) % divisor != 0; i++){
			
		}

		if(printResults)
			System.out.println();
		return 0;
	}

	public Integer getTestParameter(){
		return 7;
	}

	public long getTestSolution(){
		return 5;
	}

	public String getTitle(){
		return "Problem 078: Coin Partitions";
	}
}
