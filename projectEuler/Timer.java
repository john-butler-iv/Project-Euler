package projectEuler;

import java.util.List;
import java.util.function.Consumer;

class Timer {
	private static final int DEFUALT_TRIALS = 500;

	public static void main(String[] args) {
		PrimeFinder pf = new PrimeFinder(720721);
		time(e -> {
			pf.factorize(720720);
		}, null);
		time(e -> {
			pf.fastFactorize(720720);
		}, null);
	}

	public static <T> void time(Consumer<T> cons, T input) {
		time(cons, input, DEFUALT_TRIALS, true);
	}

	public static <T> void time(Consumer<T> cons, T input, int reps, boolean printProgress) {
		long shortestTime = Long.MAX_VALUE;
		int progress = 0;
		for (int i = 0; i < reps; i++) {
			long startTime = System.currentTimeMillis();

			cons.accept(input);

			long endTime = System.currentTimeMillis();
			long execTime = endTime - startTime;

			if (execTime < shortestTime)
				shortestTime = execTime;

			if (printProgress) {
				int percent = (i * 100) / reps;
				if (percent > progress)
					System.out
							.println(percent + "% finished, " + shortestTime + "ms has been the shortest time so far.");
			}

			if (execTime == 0)
				break;
		}
		System.out.println("done: " + shortestTime + " was the shortest execution time over " + reps + " trials.");
	}

}