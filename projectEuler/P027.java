package projectEuler;

class P027 extends Problem {
    PrimeFinder pf;

    @Override
    public boolean test() {
        pf = new PrimeFinder(5000);
        return countConsecutivePrimes(-79, 1601) == 80;
    }

    @Override
    public long solve(boolean printResults) {
        int limit = 1000;
        pf = new PrimeFinder(limit * limit);

        int largestA = 0;
        int largestB = 0;
        int mostPrimes = 0;

		for(int b : pf.getPrimes()){
			if(b > limit)
				break;
			for (int a = 0; a < limit; a++) {

                int consecPrimes = countConsecutivePrimes(a, b);
                if (mostPrimes < consecPrimes) {
                    largestA = a;
                    largestB = b;
                    mostPrimes = consecPrimes;
                }
                consecPrimes = countConsecutivePrimes(a, -b);
                if (mostPrimes < consecPrimes) {
                    largestA = a;
                    largestB = -1 * b;
                    mostPrimes = consecPrimes;
                }
                consecPrimes = countConsecutivePrimes(-a, b);
                if (mostPrimes < consecPrimes) {
                    largestA = -1 * a;
                    largestB = b;
                    mostPrimes = consecPrimes;
                }
                consecPrimes = countConsecutivePrimes(-a, -b);
                if (mostPrimes < consecPrimes) {
                    largestA = -1 * a;
                    largestB = -1 * b;
                    mostPrimes = consecPrimes;
                }
            }
        }

        if (printResults)
            System.out.println("a = " + largestA + " and b = " + largestB + " (" + (largestA * largestB)
                    + ") have the most primes (" + mostPrimes + ").");
        return largestA * largestB;
    }

    private int countConsecutivePrimes(int a, int b) {
        int n = 1;
        while (true) {
            int quadValue = quadratic(n, a, b);
            n++;
            if (quadValue < 0)
                return n;
            if (!pf.isPrime(quadValue))
                return n;
        }
    }

    private static int quadratic(int n, int a, int b) {
        return n * n + a * n + b;
    }

    @Override
	public String getTitle() {
        return "Problem 027: Quadratic Primes";
    }

}
