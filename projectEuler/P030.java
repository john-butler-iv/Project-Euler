package projectEuler;

class P030 extends ParameterizedProblem<Integer>{
    @Override
    long solve(Integer parameter, boolean printResults) {
		int sum = 0;
		for(int i = 2; i <= 999999; i++){
			if(works(i, parameter))
				sum+= i;
        }
        if(printResults)
            System.out.println(sum + " is the sum of all numbers whose digits to the 5th power equal themselves.");
        return sum;
    }
	private static boolean works(int num, int parameter){
		String str = String.valueOf(num);
		int sum = 0;
		for(char ch : str.toCharArray()){
			int n = ch -'0';
			sum += Math.pow(n, parameter);
		}
		return num == sum;
	}

    @Override
    String getTitle() {
        return "Problem 030: Digit Fifth Powers";
    }

    @Override
    Integer getDefaultParameter() {
        return 5;
    }

    @Override
    protected Integer getTestParameter() {
        return 4;
    }

    @Override
    protected long getTestSolution() {
        return 19316;
    }

}