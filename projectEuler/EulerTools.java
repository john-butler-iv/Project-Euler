package projectEuler;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;

class EulerTools {
	private static String DEFAULT_PRINT_SEPARATOR = ", ";
	public static final BigInteger TWO = new BigInteger("2");

	/**
	 * Creates an Iterator that iterates over the Fibonacci numbers (0, 1, 1, ... ),
	 * but behavior is only defined while the numbers can fit in an int. hasNext()
	 * will return false after the final valid Fibonacci number has been generated.
	 * 
	 * @return returns an Iterator which iterates over the Fibonacci numbers which
	 *         can fit in an int.
	 */
	public static Iterator<Long> fibonacciIterator() {
		return new Iterator<Long>() {
			long currNum = 0;
			long prevNum = 1;

			@Override
			public boolean hasNext() {
				return currNum > 0;
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
	 * finds i! = i * (i-1) * ... * 2 * 1
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
	 * finds l! = l * (l-1) * ... * 2 * 1
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
	 * finds bi! = bi * (bi-1) * ... * 2 * 1
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
	 * Determines whether t is in list using the binary search algorithm with time
	 * complexity O(log_2(list.size())). The list must be sorted in ascending order
	 * 
	 * @param list an ascending list which may or may not contain t
	 * @param t    a value which may be in list.
	 * @return returns true if list contains t, false otherwise
	 */
	public static <T extends Comparable<T>> boolean binaryContains(List<T> list, T t) {
		return binaryContains(list, t, 0, list.size() - 1);
	}

	private static <T extends Comparable<T>> boolean binaryContains(List<T> list, T t, int start, int end) {
		if (list.size() == 0)
			return false;
		if (start >= end)
			return list.get(start).equals(t);

		// if we found it here, we can stop
		int midPoint = (start + end) / 2;

		if (list.get(midPoint).equals(t))
			return true;
		// only need to check points greater than midpoint
		if (list.get(midPoint).compareTo(t) < 0)
			return binaryContains(list, t, midPoint + 1, end);
		// to get here, t must be less than the value at midpoint
		return binaryContains(list, t, start, midPoint - 1);
	}

	/**
	 * swaps the values at index i and j in arr, leaving all other elements
	 * unaltered.
	 * 
	 * @param arr the array in which we're swapping two elements
	 * @param i   the index of one element to be swapped
	 * @param j   the index of one element to be swapped
	 */
	public static <T> void swap(T[] arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/**
	 * swaps the values at index i and j in list, leaving all other elements
	 * unaltered.
	 * 
	 * @param list the list in which we're swapping two elements
	 * @param i    the index of one element to be swapped
	 * @param j    the index of one element to be swapped
	 */
	public static <T> void swap(List<T> list, int i, int j) {
		T temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}

	/**
	 * reverses the elements of arr. i.e. reverse({0,1,2,3}) gives {3,2,1,0}.
	 * 
	 * @param arr the array which will be reversed
	 */
	public static <T> void reverse(T[] arr) {
		reverse(arr, 0);
	}

	/**
	 * reverses the elements of arr, starting at startingIndex. i.e.
	 * reverse({0,1,2,3}, 1) gives {0,3,2,1}.
	 * 
	 * @param arr           the array which will be reversed.
	 * @param startingIndex the first index to be reversed.
	 */
	public static <T> void reverse(T[] arr, int startingIndex) {
		for (int i = startingIndex; i < (startingIndex + arr.length) / 2; i++)
			swap(arr, i, arr.length - (i - startingIndex) - 1);
	}

	/**
	 * reverses the elements of list. i.e. reverse({0,1,2,3}) gives {3,2,1,0}.
	 * 
	 * @param list the list which will be reversed
	 */
	public static <T> List<T> reverse(List<T> list) {
		return reverse(list, 0);
	}

	/**
	 * reverses the elements of list, starting at startingIndex. i.e.
	 * reverse({0,1,2,3}, 1) gives {0,3,2,1}.
	 * 
	 * @param list          the list which will be reversed.
	 * @param startingIndex the first index to be reversed.
	 */
	public static <T> List<T> reverse(List<T> list, int startingIndex) {
		for (int i = 0; i < (startingIndex + list.size()) / 2; i++)
			swap(list, i, list.size() - i - 1);
		return list;
	}

	/**
	 * computes the next lexicographic permutation of arr. i.e. permute({0,1,2,3})
	 * gives {0,1,3,2}
	 * 
	 * @param arr the array which will be permuted
	 */
	public static <T extends Comparable<T>> void permute(T[] arr) {
		if (arr == null)
			return;

		int pivot = -1;
		for (int i = arr.length - 1; i >= 1; i--) {
			if (arr[i - 1].compareTo(arr[i]) < 0) {
				pivot = i - 1;
				break;
			}
		}
		if (pivot == -1)
			return;
		for (int i = arr.length - 1; i > pivot; i--) {
			if (arr[i].compareTo(arr[pivot]) > 0) {
				swap(arr, i, pivot);
				reverse(arr, pivot + 1);

				return;
			}
		}
		swap(arr, arr.length - 1, arr.length - 2);
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

		for (int i = 0; i < list1.size(); i++)
			if (!list1.get(i).equals(list2.get(i)))
				return false;

		return true;
	}
}