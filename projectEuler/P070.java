package projectEuler;

public class P070 extends Problem{
	public long solve(boolean printResults){
		int[] totientTable = PrimeFinder.totientTable(10000000);

		double minRatio = 8.0;
		int bestN = 1;

		for(int n = 2; n < 10000000; n++){
			int totient = totientTable[n];
			if(CollectionTools.areNumPermutations(String.valueOf(n), String.valueOf(totient))){
				double ratio = (double) n / (double) totient;
				if(ratio < minRatio) {
					minRatio = ratio;
					bestN = n;
				}
			}
		}

		if(printResults)
			System.out.println(bestN + " produces the minimum n/phi(n) where n is a permutation of phi(n),"
					+ " and n <= 10,000,000.");
		return bestN;
	}

	public String getTitle(){
		return "Problem 070: Totient Permutation";
	}
}
