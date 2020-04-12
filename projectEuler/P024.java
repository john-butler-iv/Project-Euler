package projectEuler;

class P024 extends Problem {

    @Override
    long solve(boolean printResults) {
        String permutation = "0123456789";
        int permNum = 1;

        while (permNum++ < 1000000)
            permutation = EulerTools.permute(permutation);

        if (printResults)
            System.out.println(permutation + " is the 1,000,000th lexical permutation of all the numbers 0-9");

        return Long.valueOf(permutation);
    }

    @Override
    String getTitle() {
        return "Problem 024: Lexicographic Permutations";
    }

}