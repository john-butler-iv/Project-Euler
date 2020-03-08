package projectEuler;

import java.util.ArrayList;
import java.util.List;

class P023 extends Problem {
    PrimeFinder pf;

    @Override
    long solve(boolean printResults) {
        PrimeFinder pf = new PrimeFinder(28123);
        List<Integer> abundantNums = new ArrayList<Integer>();
        // find all relavent abundant numbers
        abundantNums.add(12);
        for (int i = 13; i <= 28123; i++)
            if (pf.sigma(i) - i > i)
                abundantNums.add(i);

        // start with the sums of all numbers
        int sum = EulerTools.triangle(28123);
        boolean[] expressable = new boolean[28124];

        for (int i = 0; i < abundantNums.size(); i++) {
            for (int j = i; j < abundantNums.size(); j++) {

                int aij = abundantNums.get(i) + abundantNums.get(j);
                if (aij > 28123)
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
    String getTitle() {
        return "Problem 023: Non-Abundant Sums";
    }

}