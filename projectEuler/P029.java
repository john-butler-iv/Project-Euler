package projectEuler;

import java.util.HashSet;

class P029 extends ParameterizedProblem<Integer> {

    @Override
    Integer getDefaultParameter() {
        return 100;
    }

    @Override
    long solve(Integer parameter, boolean printResults) {
        HashSet<Double> set = new HashSet<Double>();
        for (int a = 2; a <= 100; a++)
            for (int b = 2; b <= 100; b++)
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
    String getTitle() {
        return "Problem 029: Distinct Powers";
    }

}