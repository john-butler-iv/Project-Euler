package projectEuler;

import java.util.*;

public class P087 extends ParameterizedProblem<Integer> {

    @Override
    public Integer getDefaultParameter() {
        return 50000000;
    }

    @Override
    public long solve(Integer limit, boolean printResults) {
        HashSet<Integer> possibleCombinations = new HashSet<>();
        PrimeFinder pf = new PrimeFinder(limit);
        List<Integer> primes = pf.getPrimes();

        int i = 0;
        for (int p2 = (int) Math.pow(primes.get(i++), 2); p2 < limit; p2 = (int) Math.pow(primes.get(i++), 2)) {
            int j = 0;
            for (int p3 = (int) Math.pow(primes.get(j++), 3); p2
                    + p3 < limit; p3 = (int) Math.pow(primes.get(j++), 3)) {
                int k = 0;
                for (int p4 = (int) Math.pow(primes.get(k++), 4); p2 + p3
                        + p4 < limit; p4 = (int) Math.pow(primes.get(k++), 4)) {
                    possibleCombinations.add(p2 + p3 + p4);
                }
            }
        }

        if (printResults)
            System.out.println("There are " + possibleCombinations.size() + " numbers below " + limit
                    + " that can be expressed as the sum of primes to the 2nd, 3rd, and 4th power");

        return possibleCombinations.size();
    }

    @Override
    protected Integer getTestParameter() {
        return 50;
    }

    @Override
    protected long getTestSolution() {
        return 4;
    }

    @Override
    public String getTitle() {
        return "Problem 087: Prime Power Triples";
    }

}