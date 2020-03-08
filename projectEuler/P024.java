package projectEuler;

class P024 extends Problem {

    @Override
    long solve(boolean printResults) {
        Integer[] permutation = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int permNum = 1;
        while (permNum < 1000000) {
            permutation = EulerTools.permute(permutation);
            permNum++;
        }
        String arrString = "";
        for(Integer i : permutation)
            arrString += i;
            if(printResults){
                EulerTools.printArr(permutation, "");
                System.out.println(" is the 1,000,000th lexical permutation of all the numbers 0-9");
            }
        return Long.valueOf(arrString);
    }

    @Override
    String getTitle() {
        return "Problem 024: Lexicographic Permutations";
    }

}