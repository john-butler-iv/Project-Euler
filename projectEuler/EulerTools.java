package projectEuler;

import java.math.*;
import java.util.Iterator;

class EulerTools {
	public static final BigDecimal TWO = new BigDecimal("2.0");
	public static final double DEFAULT_EPSILON = 0.000001;

	/**
	 * Finds n! = 1 * 2 * ... * n. If that doesn't fit in an int, result is undefined.
	 * Meant for one off uses; if you want to find many factorials,
	 * use bigFactorialIterator() or factorialIterator().
	 *
	 * @param n the factorial index
	 * @return n! = 1 * 2 * ... * n if that fits an int, otherwise result is undefined.
	 */
	public static int factorial(int n){
		int fact = 1;
		for(int i = 2; i <= n; i++)
			fact *= i;

		return fact;
	}

	/**
	 * Computes all factorials up to and including maxFact where
	 * factorialTable(n)[m] = m! if m <= n.
	 *
	 * @param maxFact the upper limit of factorials to compute
	 * @return an array of ints where factorialTable(n)[m] = m!
	 */
	public static int[] factorialTable(int maxFact){
		int[] table = new int[maxFact + 1];
		table[0] = 1;
		for(int i = 1; i < table.length; i++)
			table[i] = i * table[i - 1];
		return table;
	}

	/**
	 * Finds n! = 1 * 2 * ... * n. Meant for one off uses. If you want to find many factorials,
	 * use bigFactorialIterator() or factorialIterator().
	 *
	 * @param n the factorial index
	 * @return n! = 1 * 2 * ... * n as a BigInteger
	 */
	public static BigInteger bigFactorial(BigInteger n) {
		BigInteger returnVar = BigInteger.ONE;
		for (BigInteger bi = BigInteger.TWO; bi.compareTo(n) <= 0; bi = bi.add(BigInteger.ONE))
			returnVar = returnVar.multiply(bi);

		return returnVar;
	}

	/**
	 * Creates an Iterator that finds 0!, 1!, etc. as long as it fits an int.
	 *
	 * @return an Iterator that finds 0!, then 1!, and so on until n! cannot fit in an int.
	 */
	public static Iterator<Integer> factorialIterator(){
		return new Iterator<Integer>() {
			int fact = 1;
			int index = 0;

			@Override
			public boolean hasNext() {
				return fact > 0;
			}

			@Override
			public Integer next() {
				int returnVar = fact;

				fact *= ++index;

				return returnVar;
			}
		};
	}
	/**
	 * Creates an Iterator that finds 0!, 1!, etc. with no limit.
	 *
	 * @return an Iterator that returns 0!, then 1!, and so on with no limit.
	 */
	public static Iterator<BigInteger> bigFactorialIterator(){
		return new Iterator<BigInteger>(){
			BigInteger fact = BigInteger.ONE;
			BigInteger index = BigInteger.ZERO;

			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public BigInteger next() {
				BigInteger returnVar = fact;

				index = index.add(BigInteger.ONE);
				fact = fact.multiply(index);

				return returnVar;
			}
		};
	}

	/**
	 * Creates an Iterator that iterates over the Fibonacci numbers (0, 1, 1, ... ),
	 * but behavior is only defined while the numbers can fit in an int. hasNext()
	 * will return false after the final valid Fibonacci number has been generated.
	 *
	 * @return an Iterator which iterates over all Fibonacci numbers which
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
	 * calculates the n'th Fibonacci number (0, 1, 1, ...) in constant time i.e.
	 * fibonacci(0) returns 0 and fibonacci(1) returns 1
	 *
	 * @param n the index of the Fibonacci number to be calulated
	 *
	 * @return the nth Fibonacci number
	 */
	public static int fibonacci(int n) {
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
	 * @return an Iterator which iterates over the Fibonacci numbers with no
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
	 * calculates the n'th Fibonacci number (0, 1, 1, ...) in constant time i.e.
	 * bigFibonacci(0) returns 0 and bigFibonacci(1) returns 1
	 *
	 * @param n the index of the Fibonacci number to be calulated
	 *
	 * @return the nth Fibonacci number
	 */
	public static BigInteger bigFibonacci(int n) {
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
	 * @return true if str is pandigital, false otherwise.
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
	 * @return true if str is a palindrome, false otherwise
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
	 * Computes all factorials up to and including maxFact where
	 * factorialTable(n)[m] = m! if m <= n.
	 *
	 * @param maxFact the upper limit of factorials to compute
	 * @return an array of BigIntegers where factorialTable(n)[m] = m!
	 */
	public static BigInteger[] bigFactorialTable(int maxFact) {
		BigInteger[] factorials = new BigInteger[maxFact + 1];
		factorials[0] = BigInteger.ONE;
		for (int i = 0; i <= maxFact; i++)
			factorials[i] = factorials[i - 1].multiply(BigInteger.valueOf(i));

		return factorials;
	}

	/**
	 * Computes n! = n * (n-1) * ... * 2 * 1. Designed for single use, if you wish
	 * have many factorial values, use the factorialTable function.
	 *
	 * @param n the number whose factorial we will compute
	 * @return n! as a BigInteger
	 */
	public static BigInteger bigFactorial(int n) {
		BigInteger bi = BigInteger.ONE;

		for (int i = 2; i <= n; i++)
			bi = bi.multiply(BigInteger.valueOf(i));

		return bi;
	}

	/**
	 * Finds the nth triangular number, where triangle(1) = 1
	 *
	 * @param n the index of the triangle number
	 * @return the nth triangular number
	 */
	public static int triangle(int n) {
		return n * (n + 1) / 2;
	}

	/**
	 * Finds the nth triangular number, where triangleL(1) = 1
	 *
	 * @param n the index of the triangle number
	 * @return the nth triangular number
	 */
	public static long triangleL(int n) {
		return n * (n + 1L) / 2;
	}

	/**
	 * finds the nth pentagonal number, where pentagon(1) = 1
	 *
	 * @param n the index of the pentagonal number
	 * @return the nth pentagonal number
	 */
	public static int pentagon(int n){
		return n * (3 * n - 1) / 2;
	}
	/**
	 * finds the nth pentagonal number, where pentagonL(1) = 1
	 *
	 * @param n the index of the pentagonal number
	 * @return the nth pentagonal number
	 */
	public static long pentagonL(int n) {
		return n * (3L * n - 1) / 2;
	}

	/**
	 * finds the nth hexagonal number, where hexagon(1) = 1
	 *
	 * @param n the index of the hexagonal number
	 * @return the nth hexagonal number
	 */
	public static int hexagon(int n) {
		return n * (2 * n - 1);
	}

	/**
	 * finds the nth hexagonal number, where hexagonL(1) = 1
	 *
	 * @param n the index of the hexagonal number
	 * @return the nth hexagonal number
	 */
	public static long hexagonL(int n) {
		return n * (2L * n - 1);
	}

	/**
	 *	finds the nth heptagonal number, where heptagon(1) = 1
	 *
	 *	@param n the index of the heptagonal number
	 *	@return the nth heptagonal number
	 */
	public static int heptagon(int n){
		return n * (5*n  - 3) / 2;
	}

	/**
	 * finds teh nth octagonal number, where octagon(1) = 1
	 *
	 * @param n the index of the heptagonal number
	 * @return the nth octagonal number
	 */
	public static int octagon(int n){
		return n * (3 * n - 2);
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
	 * @return true if t is a triangular number, false otherwise
	 */
	public static boolean isTriangular(int t) {
		int n = (int) Math.sqrt(t * 2);
		return n * (n + 1) == t * 2;
	}

	/**
	 * determines if s is a perfect square
	 *
	 * @param s a number which may or may not be a perfect square
	 * @return true if s is a perfect square, false otherwise
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
	 * @return true if p is pentagonal, false otherwise
	 */
	public static boolean isPentagonal(long p) {
		return isIntegral((1 + Math.sqrt(1 + 24 * p)) / 6);
	}

	/**
	 * computes the next Collatz number based on the starting input: n → n/2 (n is
	 * even) n → 3n + 1 (n is odd)
	 *
	 * @param n the "seed" value for the Collatz sequence
	 * @return the next Collatz number after n
	 */
	public static long nextCollatz(long n) {
		return n % 2 == 0 ? n / 2 : 3 * n + 1;
	}

	/**
	 * determines if d can be expressed as an int without any data loss
	 *
	 * @param d a double which is potentially an integer
	 * @return true if d can be expressed as an int without any data loss,
	 *         false otherwise
	 */
	public static boolean isIntegral(double d) {
		return d == (int) d;
	}

	/**
	 *	Finds the Euclidean distance between the two points i.e. sqrt( (x2 - x1)^2 + (y2 - y1)^2 )
	 *
	 *	@param x1 the x-coordinate of the first point
	 *	@param y1 the y-coordinate of the first point
	 *	@param x2 the x-coordinate of the second point
	 *	@param y2 the y-coordinate of the second point
	 *
	 *	@return the Euclidean distance betweeen the points given
	 */
	public static double distForm(double x1, double y1, double x2, double y2){
		double deltaX = x2 - x1;
		double deltaY = y2 - y1;
		return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	}

	/**
	 *	Finds the square of the Euclidean distance between the two points i.e. (x2 - x1)^2 + (y2 - y1)^2.
	 *	If the exact distance is not needed, for example finding shortest distance, it may be enough to
	 *	use this function and not have to compute sqrt.
	 *
	 *	@param x1 the x-coordinate of the first point
	 *	@param y1 the y-coordinate of the first point
	 *	@param x2 the x-coordinate of the second point
	 *	@param y2 the y-coordinate of the second point
	 *
	 *	@return the square of the Euclidean distance betweeen the points given
	 */
	public static double squareDistForm(double x1, double y1, double x2, double y2){
		double deltaX = x2 - x1;
		double deltaY = y2 - y1;
		return deltaX*deltaX + deltaY*deltaY;
	}

	/**
	 * Finds the angle in radians between the rays pa and pc using the law of cosines
	 *
	 * @param ax x-coordinate of a point on one of the rays from point p.
	 * @param ax y-coordinate of a point on one of the rays from point p.
	 * @param px x value of the vertex
	 * @param py y value of the vertex
	 * @param cx x-coordinate of a point on the other ray from point p.
	 * @param cy y-coordinate of a point on the other ray from point p.
	 *
	 * @return the angle in radians between the rays pa and pc. p is of course the vertex
	 */
	public static double findAngle(double ax, double ay, double px, double py, double cx, double cy) {
		double pc = distForm(px, py, cx, cy); // side pc
		double pa = distForm(px, py, ax, ay); // side pa
		double ac = distForm(ax, ay, cx, cy); // side ac
		double numerator = pc * pc + pa * pa - (ac * ac);
		double denomonator = 2 * pa * pc;

		return Math.acos(numerator / denomonator);
	}

	public static boolean epsilonEquals(double d1, double d2, double epsilon){
		return Math.abs(d1 - d2) <= epsilon;
	}
	public static boolean epsilonEquals(double d1, double d2){
		return epsilonEquals(d1, d2, DEFAULT_EPSILON);
	}

	public static int choose(int n, int k, int[] factorials){
		return factorials[n] / (factorials[k]*factorials[n-k]);

	}
	}
