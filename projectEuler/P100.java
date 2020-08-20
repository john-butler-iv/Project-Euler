package projectEuler;

public class P100 extends ParameterizedProblem<Long>{
	
	public Long getDefaultParameter(){
		return 1000000000000L;
	}

	public long solve(Long minDisks, boolean printResults){
		long blue = 15;
		long red = 6;
		// I haven't made writup, but I start with \frac{B(B-1)}{(B+R)(B+R-1)}= 0.5 and create Diophantine
		// equation, then I found the recursive formula at https://www.alpertron.com.ar/QUAD.HTM
		do {
			long nextBlue = 5*blue + 2*red - 2;
			long nextRed = 2*blue + red - 1;
			blue = nextBlue;
			red = nextRed;
		} while(blue + red < minDisks);
		if(printResults)
			System.out.println(blue + " blue disks and " + red + " red disks gives a "
					+ "50% chance to pull 2 blues in a row");
		return blue;
	}

	public Long getTestParameter(){
		return 119L;
	}

	public long getTestSolution(){
		return 85L;
	}

	public String getTitle(){
		return "Problem 100: Arranged Probability";
	}
}
