package projectEuler;

class P030 extends ParameterizedProblem<Integer> {

    @Override
    long solve(Integer parameter, boolean printResults) {
        // the overall sum; the answer we're looking for
        int sum = 0;
        // the sum for any individual number
        int powerSum;

        for (int i = 2; i <= 999999; i++) {
            powerSum = 0;

            // sum the powers of the digits of the number
            String str = String.valueOf(i);
            for (char ch : str.toCharArray())
                powerSum += Math.pow(ch - '0', parameter);

            // check if valid
            if (i == powerSum)
                sum += i;
        }
        if (printResults)
            System.out.println(
                    sum + " is the sum of all numbers whose digits to the " + parameter + " power equal themselves.");
        return sum;
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