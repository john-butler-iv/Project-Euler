package projectEuler;

import java.util.ArrayList;

class P026 extends ParameterizedProblem<Integer> {

    @Override
	public Integer getDefaultParameter() {
        return 1000;
    }

    public @Override
    long solve(Integer maxD, boolean printResults) {

        int largestLength = -1;
        int largestD = -1;

        PrimeFinder pf = new PrimeFinder(maxD);

        ArrayList<Integer> remainders = new ArrayList<>();
        // I don't have a proof on why we only need to check primes, but when I looked
        // at every new largestD, it was always prime, so there's probably something
        // there.
        for (int d : pf.getPrimes()) {
            remainders.clear();
            int n = 10;
            remainders.add(1);

            boolean flag = true;
            while (flag) {

                // find remainder
                n = n % d;
                if (n == 0)
                    break;

                for (int i = 0; i < remainders.size(); i++) {
                    // if the remainder already was it, it means there must be a cycle,
                    if (remainders.get(i) == n) {
                        int length = remainders.size() - i;
                        // if the cycle length is new best, record it
                        if (largestLength < length) {
                            largestLength = length;
                            largestD = d;
                        }
                        // exit back to for loop
                        flag = false;
                        break;
                    }
                }

                remainders.add(n);
                n *= 10;
            }

        }

        if (printResults)
            System.out.println(largestD + " was the denominator with the longest cycle (" + largestLength + ")");
        return largestD;

    }

    @Override
    protected Integer getTestParameter() {
        return 11;
    }

    @Override
    protected long getTestSolution() {
        return 7;
    }

    @Override
    public String getTitle() {
        return "Problem 026: Reciprocal Cycles";
    }

}
