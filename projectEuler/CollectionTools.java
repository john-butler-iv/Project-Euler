package projectEuler;

import java.util.List;

public class CollectionTools {
	private static String DEFAULT_PRINT_SEPARATOR = ", ";

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
	 * swaps the chars at indices i and j in str, leaving all other chars unaltered.
	 * 
	 * @param String the String in which we're swapping two elements
	 * @param i      the index of one element to be swapped
	 * @param j      the index of one element to be swapped
	 */
	public static String swap(String str, int i, int j) {
		// by using substrings, like I am, i cannot be larger than j
		if (i > j) {
			int temp = i;
			i = j;
			j = temp;
		} else if (i == j)
			return str;

		// rearrange the string such that i and j get swapped
		return str.substring(0, i) + str.charAt(j) + str.substring(i + 1, j) + str.charAt(i) + str.substring(j + 1);
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
	 * computes the next lexicographic permutation of str. i.e. permute("0123")
	 * gives "0132"
	 * 
	 * @param str the string which will be permuted
	 * @return returns the next permutation of str.
	 */
	public static String permute(String str) {
		if (str == null)
			return null;

		// assigns the pivot to before the last "run" in the string
		int pivot = str.length() - 1;
		while (--pivot >= 0 && str.charAt(pivot) >= str.charAt(pivot + 1))
			;

		// if we're already at the last permutation, there is no next one.
		if (pivot == -1)
			return "";

		// finds the last number greater than the pivot
		int lexRoot = str.length();
		while (--lexRoot > pivot && str.charAt(pivot) >= str.charAt(lexRoot))
			;

		str = swap(str, pivot, lexRoot);

		// reverse everything after the pivot
		String rev = "";
		for (int i = str.length() - 1; i > pivot; i--)
			rev += str.charAt(i);

		return str.substring(0, pivot + 1) + rev;
	}

	/**
	 * computes the previous lexicographic permutation of str. i.e. permute("3210")
	 * gives "3201"
	 * 
	 * @param str the string which will be permuted
	 * @return returns the previous permutation of str.
	 */
	public static String prevPermute(String str) {
		if (str == null)
			return null;

		// assigns the pivot to before the last "run" in the string
		int pivot = str.length() - 1;
		while (--pivot >= 0 && str.charAt(pivot) <= str.charAt(pivot + 1))
			;

		// if we're already at the first permutation, there is no previous permutation
		if (pivot == -1)
			return "";

		// finds the last number less than the pivot
		int lexRoot = str.length();
		while (--lexRoot > pivot && str.charAt(pivot) <= str.charAt(lexRoot))
			;

		str = swap(str, pivot, lexRoot);

		// reverse the string up to the pivot
		String rev = "";
		for (int i = str.length() - 1; i > pivot; i--)
			rev += str.charAt(i);

		return str.substring(0, pivot + 1) + rev;
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
		// don't bother checking elements if they one has extra elements
		if (list1.size() != list2.size())
			return false;

		for (int i = 0; i < list1.size(); i++)
			if (!list1.get(i).equals(list2.get(i)))
				return false;

		return true;
	}
}