package projectEuler;

import java.util.Calendar;
import java.util.GregorianCalendar;

class P019 extends Problem {

	public boolean test() {
		return true;
	}

	@Override
	long solve(boolean printResults) {
		Calendar date = new GregorianCalendar();
		date.set(1901, Calendar.JANUARY, 1);

		int numSuns = 0;

		while ((date.get(Calendar.YEAR) <= 2000)) {

			if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
				numSuns++;

			date.add(Calendar.MONTH, 1);
		}

		if (printResults)
			System.out.println("There are " + numSuns
					+ " Sundays on the first of the month between January 1, 1900, and December 31, 2000.");
		return numSuns;
	}

	@Override
	String getTitle() {
		return "Problem 019: Counting Sundays";
	}
}
