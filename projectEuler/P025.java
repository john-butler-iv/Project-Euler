package projectEuler;

import java.math.BigInteger;
import java.util.Iterator;

class P025 extends ParameterizedProblem<Integer> {

    @Override
    public Integer getDefaultParameter() {
        return 1000;
    }

    @Override
    public long solve(Integer numDigits, boolean printResults) {
        Iterator<BigInteger> it = EulerTools.bigFibonacciIterator();

        // my iterator starts on F(0) = 0
        int index = 0;

        while (it.next().toString().length() < numDigits)
            index++;

        if (printResults)
            System.out.println(
                    index + " is the index of the first Fibonacci number to have over " + numDigits + " digits.");
        return index;
    }

    @Override
    protected Integer getTestParameter() {
        return 3;
    }

    @Override
    protected long getTestSolution() {
        return 12;
    }

    @Override
	public String getTitle() {
        return "Problem 025: 1000-Digit Fibonacci Number";
    }

}
