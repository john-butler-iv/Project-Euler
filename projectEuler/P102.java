package projectEuler;

import java.io.File;

public class P102 extends ParameterizedProblem<File> {
	
	@Override
	public File getDefaultParameter() {
		return new File("p102.txt");
	}

	@Override
	public long solve(File triangles, boolean printResults){
		return 0;
	}

	@Override
	public File getTestParameter(){
		return new File("p102test.txt");
	}

	@Override
	public long getTestSolution(){
		return 1;
	}

	@Override
	public String getTitle(){
		return "Problem 102: Triangle Containment";
	}
}
