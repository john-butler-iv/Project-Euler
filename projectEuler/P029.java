package projectEuler;

import java.util.HashSet;

class P029 extends ParameterizedProblem<Integer> {

    @Override
	public Integer getDefaultParameter() {
        return 100;
    }

    @Override
    public long solve(Integer maxValue, boolean printResults) {
        HashSet<Double> set = new HashSet<Double>();
        for (int a = 2; a <= maxValue; a++)
            for (int b = 2; b <= maxValue; b++)
                set.add(Math.pow(a, b));

        if (printResults)
            System.out.println(set.size() + " is the answer.");
        return set.size();
    }

    @Override
    protected Integer getTestParameter() {
        return 5;
    }

    @Override
    protected long getTestSolution() {
        return 15;
    }

    @Override
	public String getTitle() {
        return "Problem 029: Distinct Powers";
    }

}
