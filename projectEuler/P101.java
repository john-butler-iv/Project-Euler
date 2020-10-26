package projectEuler;

public class P101 extends Problem {
	public long solve(boolean printResults){
		long[] u = new long[11];
		for(int i = 1; i <= u.length; i++)
			u[i - 1] = (long)(1 - i + Math.pow(i, 2) - Math.pow(i, 3) + Math.pow(i, 4) - Math.pow(i, 5) + Math.pow(i, 6)
				- Math.pow(i, 7) + Math.pow(i, 8) - Math.pow(i, 9) + Math.pow(i, 10));

		long[][] superMat = new long[11][11];
		for(int i = 0; i < superMat.length; i++)
			for(int j = 0; j < superMat[0].length; j++)
				superMat[i][j] = (long)Math.pow(i,j);

		for(int degree = 0; degree < 10; degree++){
			// invert submat sized degree+1, 
			// find coefficients (inverted * u subVect sized degree+1)
			// caclulate degree+1 of new poly
			// calculate of u[degree]
		}
		// TODO 
		return 0;
	}

	public String getTitle(){
		return "Problem 101: Optimum Polynomial";
	}

}
