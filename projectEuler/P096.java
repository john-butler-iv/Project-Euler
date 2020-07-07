package projectEuler;

public class P096 extends ParameterizedProblem<String> {
	@Override
	public String getDefaultParameter(){
		return "p097.txt";
	}

	@Override
	public long solve(String filename, boolean printResults){
		//TODO
		return 0;
	}

	@Override
	public String getTestParameter(){
		return "p097test.txt";
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
