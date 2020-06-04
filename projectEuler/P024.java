package projectEuler;

class P024 extends Problem {

    @Override
    public boolean test() {
        String permutation = "012";
        permutation = CollectionTools.permute(permutation);
        if (!permutation.equals("021"))
            return false;
        permutation = CollectionTools.permute(permutation);
        if (!permutation.equals("102"))
            return false;
        permutation = CollectionTools.permute(permutation);
        if (!permutation.equals("120"))
            return false;
        permutation = CollectionTools.permute(permutation);
        if (!permutation.equals("201"))
            return false;
        permutation = CollectionTools.permute(permutation);
        return permutation.equals("210");
    }

    @Override
    public long solve(boolean printResults) {
        String permutation = "0123456789";
        int permNum = 1;

        while (permNum++ < 1000000)
            permutation = CollectionTools.permute(permutation);

        if (printResults)
            System.out.println(permutation + " is the 1,000,000th lexical permutation of 0123456789");

        return Long.valueOf(permutation);
    }

    @Override
    public String getTitle() {
        return "Problem 024: Lexicographic Permutations";
    }

}
