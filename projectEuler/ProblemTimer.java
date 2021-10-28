package projectEuler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

abstract class ProblemTimer extends Timer {
	// Ideally these would be static, but then I would need to define them,
	// and there's nothing to define here
	protected ProblemTimer previousTimer;
	protected Problem[] problems;
	protected int maxPid;
	protected int minPid;

	public static void printUsage() {
		System.err.println("Usage: java projectEuler.ProblemTimer <solve / time> <pid [pid] [...] / all / range>");
	}

	public static void main(String[] args) {
		main(args, new ProblemTimerMisc());
	}

	protected static void main(String[] args, ProblemTimer instance) {
		if (args.length < 2) {
			printUsage();
			return;
		}

		// determine the operation
		Function<Problem, Long> function;
		if (args[0].equalsIgnoreCase("solve"))
			function = ProblemTimer.solve;
		else if (args[0].equalsIgnoreCase("time")) {
			function = ProblemTimer.time;
		} else {
			printUsage();
			return;
		}

		// determine which problems to use
		Problem[] problems = null;
		if (args[1].equalsIgnoreCase("range")) {
			problems = instance.problems;
		} else if (!args[1].equalsIgnoreCase("all")) { // we leave problems = null for "all"
			problems = new Problem[args.length - 1];

			for (int i = 1; i < args.length; i++) {

				try {
					problems[i - 1] = instance.getProblem(Integer.parseInt(args[i]));
				} catch (NumberFormatException e) {
				}

				if (problems[i - 1] == null)
					System.err.println(args[i] + " is not a valid pid");

			}
		}

		// apply the function to the problems
		if (problems != null) // if "all" was specified, problems remains null
			instance.apply(function, problems);
		else
			instance.applyAll(function);
	}

	// this is only used in subclasses
	protected Problem getProblem(int pid) {
		if (pid > maxPid)
			return null;

		if (pid < minPid)
			return previousTimer.getProblem(pid);
		return problems[pid - minPid];
	}

	/**
	 * tests the problem passed to see if should be run (see Problem.test()). It
	 * also checks if problem is null. If it is, false is returned
	 * 
	 * @param problem the problem to be tested
	 * @return returns whether problem passes its test or false if problem is null
	 */
	public static boolean test(Problem problem) {
		return problem != null && problem.test();
	}

	/**
	 * The function time(Problem) in a variable
	 */
	public static Function<Problem, Long> time = problem -> time(problem);

	/**
	 * Times the problem using Timer.time(). If the problem does not pass
	 * ProblemTimer.test(Problem), -1 is returned. Results are printed.
	 * 
	 * @param problem the problem to be timed
	 * @return -1 if the problem does not pass its test, or the minimum time
	 *         experementally achieved by the problem using Timer.time(Consumer,
	 *         Object).
	 */
	public static long time(Problem problem) {
		if (!test(problem)) {
			System.out.println(problem.getTitle() + " did not solve its test correctly");
			return -1L;
		}

		long time = Timer.time(problem, null, Timer.DEFUALT_TRIALS, false);

		System.out.println(problem.getTitle() + " was solved in " + time + "ms");

		return time;
	};

	/**
	 * The function solve(Problem) in a variable
	 */
	public static Function<Problem, Long> solve = problem -> solve(problem);

	/**
	 * Solves the problem, returning the result or -1 if it does not pass
	 * ProblemTimer.test(Problem). Results are printed
	 * 
	 * @param problem the problem to solve
	 * @return the solution to the problem passed
	 */
	public static long solve(Problem problem) {
		if (!test(problem)) {
			System.out.println(problem.getTitle() + " did not solve its test correctly");
			return -1L;
		}
		System.out.print(problem.getTitle() + " : ");
		return problem.solve(true);
	};

	/**
	 * applies the specified function to all problems in relProbs
	 * 
	 * @param <T>      the return type of the passed function
	 * @param func     the function to be applied
	 * @param relProbs an array of the set of relevant problems
	 * @return returns the outputs of the problems to the functions in order
	 */
	public <T> List<T> apply(Function<Problem, T> func, Problem[] relProbs) {
		List<T> out = new ArrayList<T>(relProbs.length);

		for (int i = 0; i < relProbs.length; i++)
			out.add(func.apply(relProbs[i]));

		return out;
	}

	/**
	 * Applies the function to all problems handled by this individual ProblemTimer
	 * 
	 * @param <T>  the return type of the function
	 * @param func the function to be applied to all problems in this range
	 * @return returns a list of outputs, in order, for every problem in this range
	 */
	public <T> List<T> applyInRange(Function<Problem, T> func) {
		return apply(func, problems);
	}

	/**
	 * Applies the function passed to all problems in this timer and all previous
	 * timers. The return values are in order. An example usage would be
	 * pTimer.applyAll(ProblemTimer.time);
	 * 
	 * @param <T>  return type of the function passed
	 * @param func the function to be applied to all problems
	 * @return List containing the outputs of all of the problems
	 */
	public <T> List<T> applyAll(Function<Problem, T> func) {
		List<T> out = new ArrayList<T>();
		if (previousTimer != null)
			out = previousTimer.applyAll(func);

		out.addAll(applyInRange(func));
		return out;
	}
}
