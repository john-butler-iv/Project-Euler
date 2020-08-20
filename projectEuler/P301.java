package projectEuler;

public class P301 extends ParameterizedProblem<Integer>{
	@Override
	public Integer getDefaultParameter(){
		return 1073741824;
	}
	
	@Override
	public long solve(Integer maxN, boolean printResults){
		int cnt = 0;

		for(int num = maxN; num > 0; --num)
			//I honestly don't really understand the formula, I just found it on Wikipedia
			if((num ^ (2*num) ^ (3*num)) == 0)
				++cnt;

		if(printResults)
			System.out.println("There are " + cnt + " n <= " + maxN + " such that X(n, 2n, 3n) = 0.");
		return cnt;
	}

	@Override
	public Integer getTestParameter(){
		return 1;
	}

	@Override
	public long getTestSolution(){
		return 1;
	}

	@Override
	public String getTitle(){
		return "Problem 301: Nim";
	}
}
