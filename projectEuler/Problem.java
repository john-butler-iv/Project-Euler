package projectEuler;

import java.util.function.Consumer;

abstract class Problem implements Consumer<Object> {
	@Override
	public void accept(Object input) {
		solve(false);
	}

	public boolean test() {
		return true;
	}

	public abstract long solve(boolean printResults);

	public abstract String getTitle();

	public int getNumber() {
		return Integer.valueOf(getTitle().substring(9, 12));
	}

	public String toString() {
		return getTitle();
	}
}
