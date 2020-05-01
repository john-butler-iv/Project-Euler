package projectEuler;

class P031 extends Problem {

    @Override
    public boolean test() {
        return 1 * 1 + 1 * 50 + 2 * 20 + 1 * 5 + 1 * 2 + 3 * 1 == 200;
    }

    @Override
    long solve(boolean printResults) {
        int totalCombinations = 0;
        // there is almost certainly a combinatorics way to figure this out, but I'm too
        // lazy to figure it out and I've never taken a combinatorics class
        for (int p1 = 0; p1 <= 200; p1++)
            for (int p2 = 0; p2 <= 200; p2 += 2)
                for (int p5 = 0; p5 <= 200; p5 += 5)
                    for (int p10 = 0; p10 <= 200; p10 += 10)
                        for (int p20 = 0; p20 <= 200; p20 += 20)
                            for (int p50 = 0; p50 <= 200; p50 += 50)
                                for (int p100 = 0; p100 <= 200; p100 += 100)
                                    for (int p200 = 0; p200 <= 200; p200 += 200)
                                        if (p1 + p2 + p5 + p10 + p20 + p50 + p100 + p200 == 200)
                                            totalCombinations++;

        if (printResults)
            System.out.println("total number of ways to make 2 pounds: " + totalCombinations);
        return totalCombinations;
    }

    @Override
    String getTitle() {
        return "Problem 031: Coin Sums";
    }

}