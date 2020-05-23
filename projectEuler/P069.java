package projectEuler;

import java.util.List;
import java.lang.Math;

public class P069 extends ParameterizedProblem<Integer> {

    @Override
    public Integer getDefaultParameter() {
        return 1000000;
    }

    public static void main(String[] args) {
        new P069().solve(1000000, true);
    }

    @Override
    public long solve(Integer maxN, boolean printResults) {
        // see write up for proof that any lowest maximal n/φ(n) must be product of
        // primes.
        // As for only log_2(max) primes, I have no idea. The n grows exponentially,
        // sure, but why 2?
        // +1 is for making sure that we actually find all we need (don't find primes
        // equal to limit)
        List<Integer> primes = new PrimeFinder((int) (Math.log(maxN) / Math.log(2)) + 1).getPrimes();

        int currN = 1;
        for (int prime : primes) {

            int currPrime = prime;
            int nextN = currN * currPrime;

            if (nextN > maxN)
                break;
            currN = nextN;
        }
        if (printResults)
            System.out.println("" + currN + " / φ ( " + currN + " ) is maximal ∀ n ≤ " + maxN);
        return currN;
    }

    @Override
    protected Integer getTestParameter() {
        return 10;
    }

    @Override
    protected long getTestSolution() {
        return 6;
    }

    @Override
    public String getTitle() {
        return "Problem 069: Totient Maximum";
    }

}
