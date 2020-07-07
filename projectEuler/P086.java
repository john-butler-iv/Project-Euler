package projectEuler;

public class P086 extends ParameterizedProblem<Integer> {
	@Override
	public Integer getDefaultParameter() {
		return 1000000;
	}

	@Override
	public long solve(Integer pathGoal, boolean printResults){
		//TODO
		return 0;
	}

	@Override
	public Integer getTestParameter(){
		return 2000;
	}

	@Override
	public long getTestSolution(){
		return 100;
	}

	@Override
	public String getTitle(){
		return "Problem 086: Cuboid Route";
	}
}
