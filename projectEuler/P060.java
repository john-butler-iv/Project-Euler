package projectEuler;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class P060 extends ParameterizedProblem<Integer> {

	private static class PrimePairDesc {
		int prime;
		HashSet<Integer> pairIndicesSet;
		ArrayList<Integer> pairIndices;

		public PrimePairDesc(int prime) {
			this.prime = prime;
			this.pairIndicesSet = new HashSet<>();
			this.pairIndices = new ArrayList<>();
		}

		public void addConnection(int index) {
			pairIndicesSet.add(index);
			this.pairIndices.add(index);
		}

		public int getTotalPairs() {
			return pairIndices.size();
		}

		public boolean pairsWith(int index) {
			return pairIndicesSet.contains(index);
		}
	}

	PrimeFinder pf;

	@Override
	public Integer getDefaultParameter() {
		return 5;
	}

	public static void main(String[] args) {
		ProblemTimer51To100.main(args);
	}

	@Override
	public long solve(Integer setSize, boolean printResults) {
		pf = new PrimeFinder(2000000000);
		List<Integer> primes = pf.getPrimes();
		ArrayList<PrimePairDesc> primePairs = new ArrayList<>();

		for (int i = 0; i < primes.size(); i++) {
			primePairs.add(new PrimePairDesc(primes.get(i)));
			for (int j = 0; j < i; j++) {
				if (validPair(primes.get(i), primes.get(j))) {
					primePairs.get(i).addConnection(j);
					primePairs.get(j).addConnection(i);
				}
			}

			if (primePairs.get(i).getTotalPairs() >= setSize) {
				ArrayList<Integer> otherIndices = primePairs.get(i).pairIndices;

				int[] indices = new int[5];
				indices[0] = i;

				for (int a = 0; a < otherIndices.size() - setSize + 2; a++) {
					indices[1] = a;
					for (int b = a + 1; b < otherIndices.size() - setSize + 3; b++) {
						indices[2] = b;
						for (int c = b + 1; c < otherIndices.size() - setSize + 4; c++) {
							indices[3] = c;
							for (int d = c + 1; d < otherIndices.size() - setSize + 5; d++) {
								indices[4] = d;

								if (isValidSet(primePairs, indices)) {
									int sum = 0;
									for (int j = 0; j < indices.length; j++)
										sum += primes.get(indices[j]);

									if (printResults)
										System.out.println(sum + " is the lowest sum of the set of " + setSize
												+ " primes which all concatenate into more primes");
									return sum;
								}
							}
						}
					}
				}
			}
		}
		if (printResults)
			System.out.println("could not find a valid set");
		return 0;

	}

	private boolean isValidSet(ArrayList<PrimePairDesc> primePairs, int[] indices) {
		for (int i = 0; i < indices.length - 1; i++) {
			for (int j = 0; j < indices.length; j++) {
				if (i == j)
					continue;
				if (!primePairs.get(indices[i]).pairsWith(indices[j]))
					return false;
			}
		}
		return true;
	}

	private boolean validPair(int p, int q) {
		int pair1 = Integer.valueOf(String.valueOf(p) + String.valueOf(q));
		int pair2 = Integer.valueOf(String.valueOf(q) + String.valueOf(p));

		return pf.isPrime(pair1) && pf.isPrime(pair2);
	}

	@Override
	public Integer getTestParameter() {
		return 4;
	}

	@Override
	public long getTestSolution() {
		return 792;
	}

	@Override
	public String getTitle() {
		return "Problem 060: Prime Pair Sets";
	}
}
