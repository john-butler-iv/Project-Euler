package projectEuler;

public class P052 extends ParameterizedProblem<Integer> {

	@Override
	public Integer getDefaultParameter() {
		return 6;
	}

	@Override
	public long solve(Integer maxMultiple, boolean printResults) {
		for (int i = 1; i < Integer.MAX_VALUE; i++) {
			String baseString = String.valueOf(i);

			boolean permutable = true;
			// by going backwards through the string, we can catch the multiple overflowing,
			// so overall, there will be fewer computations
			for (int j = maxMultiple; j >= 2; j--) {
				String multipleString = String.valueOf(i * j);

				// if the multiples gained an extra digt, increasing i will only continue that
				// happening for any larger i with the same length, so we just increase the
				// length of i.
				if (multipleString.length() > baseString.length()) {
					// i gets incremented, but it's easy to show that the answer cannot be a
					// multiple of 10.
					i = (int) Math.pow(10, baseString.length());
					permutable = false;
					break;
				}

				// if the multiple isn't
				if (!CollectionTools.arePermutations(baseString, multipleString)) {
					permutable = false;
					break;
				}
			}
			if (permutable) {
				if (printResults)
					System.out.println(
							i + " is the smallest number which contains the same digits as its multiples up to "
									+ maxMultiple + " times.");
				return i;
			}
		}
		if (printResults)
			System.out.println("no answer was found, try a smaller parameter?");
		return -1;
	}

	@Override
	protected Integer getTestParameter() {
		return 2;
	}

	@Override
	protected long getTestSolution() {
		return 125874;
	}

	@Override
	public String getTitle() {
		return "Problem 052: Permuted multiples";
	}

}
