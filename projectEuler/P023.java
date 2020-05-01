package projectEuler;

import java.util.ArrayList;
import java.util.List;

class P023 extends ParameterizedProblem<Integer> {
    PrimeFinder pf;

    @Override
    Integer getDefaultParameter() {
        return 28123;
    }

    @Override
    long solve(Integer maxNum, boolean printResults) {
        PrimeFinder pf = new PrimeFinder(maxNum);
        List<Integer> abundantNums = new ArrayList<Integer>();
        // find all relavent abundant numbers
        abundantNums.add(12);
        for (int i = 13; i <= maxNum; i++)
            if (pf.sigma(i) - i > i)
                abundantNums.add(i);

        // start with the sums of all numbers
        int sum = EulerTools.triangle(maxNum);

        boolean[] expressable = new boolean[maxNum];
        for (int i = 0; i < abundantNums.size(); i++) {
            for (int j = i; j < abundantNums.size(); j++) {

                int aij = abundantNums.get(i) + abundantNums.get(j);
                if (aij > maxNum)
                    break;

                // subtract from sum if it is actually expressable
                if (!expressable[aij]) {
                    expressable[aij] = true;
                    sum -= aij;
                }
            }
        }

        if (printResults)
            System.out.println(
                    sum + " is the sum of all numbers which cannot be expressed as the sum of two abundant numbers.");
        return sum;
    }

    @Override
    protected Integer getTestParameter() {
        return 12;
    }

    @Override
    protected long getTestSolution() {
        return 24;
    }

    @Override
    String getTitle() {
        return "Problem 023: Non-Abundant Sums";
    }

}