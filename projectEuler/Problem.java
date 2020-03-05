package projectEuler;

import java.util.function.Consumer;

abstract class Problem implements Consumer<Object> {
	@Override
	public void accept(Object input) {
		solve(false);
	}

	abstract long solve(boolean printResults);

	abstract String getTitle();

	public int getNumber() {
		return Integer.valueOf(getTitle().substring(9, 12));
	}

	public String toString() {
		return getTitle();
	}
}