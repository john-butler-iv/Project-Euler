package projectEuler;

public class P044 extends Problem {

	@Override
	long solve(boolean printResults) {
		int k = 1;
		long K = EulerTools.pentagonL(k);

		int j = 1;
		long J = EulerTools.pentagonL(j);

		boolean flag = true;
		for (k = 1; flag; K = EulerTools.pentagonL(++k)) {
			for (j = k - 1; j > 1; J = EulerTools.pentagonL(--j)) {
				if (EulerTools.isPentagonal(K - J)) {
					if (EulerTools.isPentagonal(K + J)) {
						k--;
						flag = false;
						break;
					}
				}
			}
		}

		long D = K - J;
		if (printResults)
			System.out.println(D
					+ " is the minimal difference of two pentagonal numbers whose sum and difference are both pentagonal.");
		return D;
	}

	@Override
	String getTitle() {
		return "Problem 044: Pentagon numbers";
	}

}