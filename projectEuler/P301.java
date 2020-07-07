package projectEuler;

public class P301 extends ParameterizedProblem<Integer>{
	@Override
	public Integer getDefaultParameter(){
		return 1073741824;
	}
	
	@Override
	public long solve(Integer maxN, boolean printResults){
		return 0;
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
