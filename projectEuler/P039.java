package projectEuler;

public class P039 extends Problem {

	@Override
	long solve(boolean printResults) {
		int[] trisByP = new int[1001];

		// iterate through all possible three digit values with
		// a < b < c and a + b + c <= 1000. It's not too hard to show that no two sides
		// can be the same length.
		for (int c = 1; c <= 998; c++)
			for (int b = 1; b < c && b + c <= 999; b++)
				for (int a = 1; a < b && a + b + c <= 1000; a++)
					// keep track of how many pythagorian triples correspond to each perimeter
					if (a * a + b * b == c * c)
						trisByP[a + b + c]++;

		// find the perimeter with the best number of corresponding triangles
		int bestP = 0;
		int bestTris = 0;
		for (int p = 0; p < trisByP.length; p++) {
			if (trisByP[p] > bestTris) {
				bestTris = trisByP[p];
				bestP = p;
			}
		}

		if (printResults)
			System.out.println(bestP + " is the perimeter with the most integral right triangles");
		return bestP;
	}

	@Override
	String getTitle() {
		return "Problem 039: Integer right triangles";
	}

}