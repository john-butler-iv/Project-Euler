package projectEuler;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

class EulerTools {
	private static String DEFAULT_PRINT_SEPARATOR = ", ";
	public static final BigInteger TWO = new BigInteger("2");

	public static Iterator<Long> fibonacciIterator() {
		return new Iterator<Long>() {
			long currNum = 0;
			long prevNum = 1;

			@Override
			public boolean hasNext() {
				return currNum + prevNum > 0;
			}

			@Override
			public Long next() {
				long temp = currNum;
				currNum += prevNum;
				prevNum = temp;

				return prevNum;
			}
		};
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
	 * finds i!, that is i * (i-1) * ... * 2 * 1
	 * 
	 * @param i the input to the factorial function
	 * @return returns i!
	 */
	public static int factorial(int i) {
		for (int j = i - 1; j > 1; j--)
			i *= j;
		return i;
	}

	/**
	 * finds l!, that is l * (l-1) * ... * 2 * 1
	 * 
	 * @param l the input to the factorial function
	 * @return returns l!
	 */
	public static long factorial(long l) {
		for (long j = l - 1; j > 1; j--)
			l *= j;
		return l;
	}

	
	/**
	 * finds bi!, that is bi * (bi-1) * ... * 2 * 1
	 * 
	 * @param bi the input to the factorial function
	 * @return returns bi!
	 */
	public static BigInteger factorial(BigInteger bi) {
		BigInteger cnt = new BigInteger(bi.toString());
		cnt = cnt.subtract(BigInteger.ONE);
		while (cnt.compareTo(BigInteger.ONE) > 0) {
			bi = bi.multiply(cnt);
			cnt = cnt.subtract(BigInteger.ONE);
		}
		return bi;
	}

	/**
	 * Finds the i'th triangular number, where triangle(1) = 1
	 * 
	 * @param i       the index of the triangle number
	 * @param returns the ith triangular number
	 */
	public static int triangle(int i) {
		return i * (i + 1) / 2;
	}

	/**
	 * determines if n is a perfect square
	 * 
	 * @param n a number which may or may not be a perfect square
	 * @return returns true if n is a perfect square, false otherwise
	 */
	public static boolean isSquare(int n) {
		return isIntegral(Math.sqrt(n));
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

	/**
	 * Prints every element separated by a comma and a space
	 * 
	 * @param list the list whose elements will be printed
	 */
	public static <T> void printList(List<T> list) {
		printList(list, DEFAULT_PRINT_SEPARATOR);
	}

	/**
	 * Prints every element separated by a comma and a space
	 * 
	 * @param arr the list whose elements will be printed
	 */
	public static <T> void printArr(T[] arr) {
		printArr(arr, DEFAULT_PRINT_SEPARATOR);
	}

	/**
	 * Prints every element separated a comma and a space, and prints a new line
	 * after the entire list
	 * 
	 * @param list the list whose elements will be printed
	 */
	public static <T> void printlnList(List<T> list) {
		printList(list, DEFAULT_PRINT_SEPARATOR);
		System.out.println();
	}

	/**
	 * Prints every element separated a comma and a space, and prints a new line
	 * after the entire array
	 * 
	 * @param arr the array whose elements will be printed
	 */
	public static <T> void printlnArr(T[] arr) {
		printArr(arr, DEFAULT_PRINT_SEPARATOR);
		System.out.println();
	}

	/**
	 * Prints every element separated by the separator string
	 * 
	 * @param list      the list whose elements will be printed
	 * @param separator the String that will show up between each element in the
	 *                  list
	 */
	public static <T> void printList(List<T> list, String separator) {
		for (int i = 0; i < list.size() - 1; i++)
			System.out.print(list.get(i) + separator);
		System.out.print(list.get(list.size() - 1));
	}

	/**
	 * Prints every element separated by the separator string
	 * 
	 * @param arr       the array whose elements will be printed
	 * @param separator the String that will show up between each element in the
	 *                  list
	 */
	public static <T> void printArr(T[] arr, String separator) {
		for (int i = 0; i < arr.length - 1; i++)
			System.out.print(arr[i] + separator);
		System.out.print(arr[arr.length - 1]);
	}

	/**
	 * Prints every element separated by the separator string, and prints a new line
	 * after the entire list
	 * 
	 * @param list      the list whose elements will be printed
	 * @param separator the String that will show up between each element in the
	 *                  list
	 */
	public static <T> void printlnList(List<T> list, String separator) {
		printList(list, separator);
		System.out.println();
	}

	/**
	 * Prints every element separated by the separator string, and prints a new line
	 * after the entire array
	 * 
	 * @param arr       the array whose elements will be printed
	 * @param separator the String that will show up between each element in the
	 *                  array
	 */
	public static <T> void printlnArr(T[] arr, String separator) {
		printArr(arr, separator);
		System.out.println();
	}

	/**
	 * determines whether the two lists are equal
	 * 
	 * @param list1 one of the lists compared
	 * @param list2 one of the lists compared
	 * @return returns true if both lists have the same elements in the same order,
	 *         false otherwise
	 */
	public static <T> boolean equals(List<T> list1, List<T> list2) {
		if (list1.size() != list2.size())
			return false;
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equals(list2.get(i)))
				return false;
		}
		return true;
	}
}