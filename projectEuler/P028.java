package projectEuler;

class P028 extends ParameterizedProblem<Integer> {

    @Override
    Integer getDefaultParameter() {
        return 1001;
    }

    @Override
    long solve(Integer parameter, boolean printResults) {
        //see my writeup for the derivation of this formula
        int diaLen = parameter / 2;
        int total = diaLen * (16 * diaLen * diaLen + 30 * diaLen + 26) / 3 + 1;

        if (printResults)
            System.out.println(total + " is the sum of all diagonals diagonals.");
        return total;
    }

    @Override
    protected Integer getTestParameter() {
        return 5;
    }

    @Override
    protected long getTestSolution() {
        return 101;
    }

    @Override
    String getTitle() {
        return "Problem 028: Number Spiral Diagonals";
    }

}