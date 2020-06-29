package projectEuler;

import java.math.*;
import java.util.Iterator;

class EulerTools {
	public static final BigDecimal TWO = new BigDecimal("2.0");

	public static BigInteger factorial(BigInteger n){
		BigInteger returnVar = BigInteger.ONE;
		for(BigInteger bi = BigInteger.TWO; bi.compareTo(n) <= 0; bi = bi.add(BigInteger.ONE))
			returnVar = returnVar.multiply(bi);
		
		return returnVar;
	}

	/**
	 * Creates an Iterator that iterates over the Fibonacci numbers (0, 1, 1, ... ),
	 * but behavior is only defined while the numbers can fit in an int. hasNext()
	 * will return false after the final valid Fibonacci number has been generated.
	 * 
	 * @return returns an Iterator which iterates over all Fibonacci numbers which
	 *         can fit in an int.
	 */
	public static Iterator<Integer> fibonacciIterator() {
		return new Iterator<Integer>() {
			int currNum = 0;
			int prevNum = 1;

			@Override
			public boolean hasNext() {
				return currNum > 0;
			}

			@Override
			public Integer next() {
				int temp = currNum;
				currNum += prevNum;
				prevNum = temp;

				return prevNum;
			}
		};
	}

	/**
	 * calculates the n'th Fibonacci number (0, 1, 1, ...) in constant time
	 * i.e. fibonacci(0) returns 0 and fibonacci(1) returns 1
	 *
	 * @param n the index of the Fibonacci number to be calulated
	 *
	 * @return returns the nth Fibonacci number
	 */
	public static int fibonacci(int n){
		// see write up for explaination of formula (Problem 2)
		double rt5 = Math.sqrt(5);

		double r1 = Math.pow(1 + rt5, n);
		double r2 = Math.pow(1 - rt5, n);
		double f = (r1 - r2) / (Math.pow(2, n) * rt5);

		// round in case of floating point error
		return (int) (f + 0.5);
	}

	/**
	 * Creates an Iterator that iterates over the Fibonacci numbers (0, 1, 1, ... )
	 * 
	 * @return returns an Iterator which iterates over the Fibonacci numbers with no
	 *         limit.
	 */
	public static Iterator<BigInteger> bigFibonacciIterator() {
		return new Iterator<BigInteger>() {
			BigInteger currNum = BigInteger.ZERO;
			BigInteger prevNum = BigInteger.ONE;

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public BigInteger next() {
				BigInteger temp = currNum;
				currNum = currNum.add(prevNum);
				prevNum = temp;

				return prevNum;
			}
		};
	}

	/**
	 * calculates the n'th Fibonacci number (0, 1, 1, ...) in constant time
	 * i.e. bigFibonacci(0) returns 0 and bigFibonacci(1) returns 1
	 *
	 * @param n the index of the Fibonacci number to be calulated
	 *
	 * @return returns the nth Fibonacci number
	 */
	public static BigInteger bigFibonacci(int n){
		// I may want to change the MathContext
		BigDecimal rt5 = new BigDecimal("5.0").sqrt(MathContext.DECIMAL128);

		BigDecimal r1 = rt5.add(BigDecimal.ONE).pow(n);
		BigDecimal r2 = rt5.subtract(BigDecimal.ONE).pow(n);
		BigDecimal f = r1.subtract(r2).divide(rt5.multiply(TWO.pow(n)));

		f.round(new MathContext(128, RoundingMode.HALF_EVEN));
		return f.toBigInteger();
	}

	/**
	 * Determines whether the string passed is pandigital, that is only contains the
	 * digits 1-9 each exactly once.
	 * 
	 * @param str a string which may or may not be pandigital
	 * @return returns true if str is pandigital, false otherwise.
	 */
	public static boolean isPandigital(String str) {
		// these are some common violated restrictions
		if (str.contains("0") || str.length() != 9)
			return false;

		// check that every digit is in the string
		for (char ch = '1'; ch <= '9'; ch++)
			if (!str.contains(String.valueOf(ch)))
				return false;

		return true;
	}

	/**
	 * Determines whether str is a palindrome i.e. it is the same forwards and
	 * backwards
	 * 
	 * @param str a String which may or may not be a palindrome
	 * @return returns true if str is a palindrome, false otherwise
	 */
	public static boolean isPalindrome(String str) {
		// if str.length() is odd, there's no need to check the middle character, it is
		// of course going to be equal to itself
		for (int i = 0; i < str.length() / 2; i++)
			if (str.charAt(i) != str.charAt(str.length() - i - 1))
				return false;

		return true;
	}

	/**
	 * Finds the nth triangular number, where triangle(1) = 1
	 * 
	 * @param n the index of the triangle number
	 * @return returns the nth triangular number
	 */
	public static int triangle(int n) {
		return n * (n + 1) / 2;
	}

	/**
	 * Finds the nth triangular number, where triangle(1) = 1
	 * 
	 * @param n the index of the triangle number
	 * @return returns the nth triangular number
	 */
	public static long triangleL(int n) {
		return n * (n + 1L) / 2;
	}

	/**
	 * finds the nth pentagonal number, where pentagon(1) = 1
	 * 
	 * @param n the index of the pentagonal number
	 * @return returns the nth pentagonal number
	 */
	public static long pentagonL(int n) {
		return n * (3L * n - 1) / 2;
	}

	/**
	 * finds the nth hexagonal number, where hexagon(1) = 1
	 * 
	 * @param n the index of the hexagonal number
	 * @return returns the nth hexagonal number
	 */
	public static int hexagon(int n) {
		return n * (2 * n - 1);
	}

	/**
	 * finds the nth hexagonal number, where hexagon(1) = 1
	 * 
	 * @param n the index of the hexagonal number
	 * @return returns the nth hexagonal number
	 */
	public static long hexagonL(int n) {
		return n * (2L * n - 1);
	}
	// LaTeX write up code that I drafted since I don't have an actual writeup.
	// tri=\frac{n(n+1)}{2}\\
	// \therefore 2 T=n(n+1)\\
	// n^2 < n(n+1) < (n+1)^2\\
	// \therefore n < \sqrt{n(n+1)} < n+1\\
	// \therefore \sqrt{n(n+1)} \nin \mathbb{Z}\\
	// \therefore n=\lfloor\sqrt{n(n+1)}\rfloor\\
	// \therefore 2T = \lfloor\sqrt{n(n+1)}\rfloor\cdot \lfloor\sqrt{n(n+1)} +
	// 1\rfloor

	/**
	 * Determines whether tri is or is not a triangular number
	 * 
	 * @param t a number which may or may not be a triangular number
	 * @return returns true if t is a triangular number, false otherwise
	 */
	public static boolean isTriangular(int t) {
		int n = (int) Math.sqrt(t * 2);
		return n * (n + 1) == t * 2;
	}

	/**
	 * determines if s is a perfect square
	 * 
	 * @param s a number which may or may not be a perfect square
	 * @return returns true if s is a perfect square, false otherwise
	 */
	public static boolean isSquare(int s) {
		return isIntegral(Math.sqrt(s));
	}

	// LaTeX write up code that I drafted since I don't have an actual writeup.
	// P \text{ is pentagonal.}\\
	// \therefore \exists n \in \mathbb{N} : P = \frac{n(3n - 1)}{2}\\
	// \therefore 2P = n(3n-1)\\
	// &= 3n^2 - n\\
	// 0 &= 3n^2 - n - 1P\\
	// n = \frac{1 \pm \sqrt{1 - 4(3)(-2P)}}{6}\\
	// &= \frac{1 \pm \sqrt {1 - 24P}{6}\\
	// n \in \mathbb{N} \implies n \ge 0 \implies n \ne \frac{1 - \sqrt{1 + 24P}}{6}
	// < 0\\
	// \therefore n = \frac{1 + \sqrt{1 + 24P}}{6} \text{ only.}\\
	// \threfore \text{If } P \text{ is pentagonal, } \frac{1 + \sqrt{1 + 24P}}{6}
	// \in \mathbb{N}\\
	// Converse follows similarly, but it would still be good to write it
	// explicitly.

	/**
	 * Determines if p is a pentagonal number
	 * 
	 * @param p a number which may or may not be pentagonal
	 * @return returns true if p is pentagonal, false otherwise
	 */
	public static boolean isPentagonal(long p) {
		return isIntegral((1 + Math.sqrt(1 + 24 * p)) / 6);
	}

	/**
	 * computes the next Collatz number based on the starting input: n → n/2 (n is
	 * even) n → 3n + 1 (n is odd)
	 * 
	 * @param n the "seed" value for the Collatz sequence
	 * @return returns the next Collatz number after n
	 */
	public static long nextCollatz(long n) {
		return n % 2 == 0 ? n / 2 : 3 * n + 1;
	}

	/**
	 * determines if d can be expressed as an int without any data loss
	 * 
	 * @param d a double which is potentially an integer
	 * @return returns true if d can be expressed as an int without any data loss,
	 *         false otherwise
	 */
	public static boolean isIntegral(double d) {
		return d == (int) d;
	}

}
