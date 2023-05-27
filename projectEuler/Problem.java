package projectEuler;

import java.util.function.Consumer;

abstract class Problem implements Consumer<Object> {
	@Override
	public void accept(Object input) {
		solve(false);
	}

	public boolean test() {
		return test(false, false);
	}

	public boolean test(boolean printResults) {
		return test(printResults, false);
	}

	public boolean test(boolean printFailure, boolean printSuccess) {
		if (printSuccess) {
			System.out.println(this.getTitle() + " does not have a test.");
		}
		return true;
	}

	public abstract long solve(boolean printResults);

	public abstract String getTitle();

	public int getNumber() {
		return Integer.valueOf(getTitle().substring(8, 11));
	}

	public String toString() {
		return getTitle();
	}
}
