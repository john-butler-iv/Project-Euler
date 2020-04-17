package projectEuler;

import java.util.function.Consumer;

class Timer {
	protected static final int DEFUALT_TRIALS = 500;

	public static void main(String[] args) {
		// PrimeFinder pf = new PrimeFinder(Integer.MAX_VALUE / 8);
		// compareTimes(p -> new P053().solve(false), 0, p -> new P053().solve2(false),
		// 0);
	}

	/**
	 * figures out how long it takes cons.accept(input) to run, printing out the
	 * best time while it is still running.
	 * 
	 * @param cons  the consumer to be timed
	 * @param input the input to run the consumer function on
	 * @return returns the lowest run time, in ms, of all 500 trials
	 */
	public static <T> long time(Consumer<T> cons, T input) {
		return time(cons, input, DEFUALT_TRIALS, true);
	}

	/**
	 * figures out how long it takes cons.accept(input) to run
	 * 
	 * @param cons          the consumer to be timed
	 * @param input         the input to run the consumer function on
	 * @param reps          the number of trials to test the consumer
	 * @param printProgress whether or not the lowest time will be printed after
	 *                      each percent
	 * @return returns the lowest run time, in ms, of all trials
	 */
	public static <T> long time(Consumer<T> cons, T input, int reps, boolean printProgress) {
		long shortestTime = Long.MAX_VALUE;
		int progress = 0;

		for (int i = 0; i < reps; i++) {
			long startTime = System.currentTimeMillis();

			cons.accept(input);

			long endTime = System.currentTimeMillis();
			long execTime = endTime - startTime;

			if (execTime < shortestTime) {
				shortestTime = execTime;
				if (shortestTime == 0)
					break;
			}

			if (printProgress) {
				int percent = (i * 100) / reps;
				if (percent > progress) {
					System.out
							.println(percent + "% finished, " + shortestTime + "ms has been the shortest time so far.");
					progress = percent;
				}
			}
		}
		if (printProgress)
			System.out
					.println("Done: " + shortestTime + "ms was the shortest execution time over " + reps + " trials.");
		return shortestTime;
	}

	/**
	 * Figures out whether cons1.accept(input1) or cons.accept(input2) is faster
	 * 
	 * @param cons1  one of the consumers tested
	 * @param input1 the input for cons1
	 * @param cons2  one of the consumers tested
	 * @param input2 the input for cons2
	 */
	public static <T, U> void compareTimes(Consumer<T> cons1, T input1, Consumer<U> cons2, U input2) {
		compareTimes(cons1, input1, cons2, input2, DEFUALT_TRIALS, false);
	}

	/**
	 * Figures out whether cons1.accept(input1) or cons.accept(input2) is faster
	 * 
	 * @param cons1         one of the consumers tested
	 * @param input1        the input for cons1
	 * @param cons2         one of the consumers tested
	 * @param input2        the input for cons2
	 * @param reps          the number of trials to test each consumer over
	 * @param printProgress whether or not the best time will be printed after each
	 *                      percent
	 */
	public static <T, U> void compareTimes(Consumer<T> cons1, T input1, Consumer<U> cons2, U input2, int reps,
			boolean printProgress) {
		System.out.println("starting...");
		long time1 = time(cons1, input1, reps, printProgress);
		long time2 = time(cons2, input2, reps, printProgress);
		if (time1 < time2)
			System.out.println("the first consumer was completed in " + time1 + "ms, " + (time2 - time1)
					+ "ms faster than the other.");
		else if (time1 > time2)
			System.out.println("the second consumer was completed in " + time2 + "ms, " + (time1 - time2)
					+ "ms faster than the other.");
		else
			System.out.println("Bother consumers were completed in " + time1 + "ms.");
	}

}