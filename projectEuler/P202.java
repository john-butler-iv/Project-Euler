package projectEuler;

public class P202 extends ParameterizedProblem<Long> {
    @Override
    public Long getDefaultParameter() {
        return 12017639147L;
    }

    public static void main(String[] args) {
        Problem prob = new P202();
        System.out.println(prob.test());
        prob.solve(true);
    }

    @Override
    public long solve(Long bounces, boolean printResults) {
        PrimeFinder pf = new PrimeFinder(Integer.MAX_VALUE / 8);
        final long LIMIT = (bounces - 7) / 6;
        long total = 0;
        for (long m = 0; m <= LIMIT; m++) {
            long n = bounces - 6 * m - 7;
            if ((n & 3) != 0)
                continue;
            n /= 4;
            if (pf.areCoprime(n, m))
                total++;
        }

        if (printResults) {
            System.out.println("There are " + total + " ways that a laserbeam can bounce " + bounces
                    + " times and exit through the same vertex");
        }

        return total;
    }

    @Override
    public Long getTestParameter() {
        return 1000001L;
    }

    @Override
    public long getTestSolution() {
        return 80840;
    }

    @Override
    public String getTitle() {
        return "Problem 202: Laserbeam";
    }
}