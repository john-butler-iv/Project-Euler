package projectEuler;

import java.util.*;

public class CollectionTools {
	public static class Tuple<E1, E2> {
		public E1 val1;
		public E2 val2;

		public Tuple(E1 val1, E2 val2) {
			this.val1 = val1;
			this.val2 = val2;
		}
	}

	public static <T> boolean inBounds(List<T> list, int i) {
		if (list == null)
			return false;
		return i >= 0 && i < list.size();
	}

	public static <T> boolean inBounds(T[][] mat, int r, int c) {
		if (mat == null)
			return false;
		if (mat.length == 0)
			return false;
		return r >= 0 && r < mat.length && c >= 0 && c < mat[0].length;
	}

	public static boolean inBounds(int[][] mat, int r, int c) {
		if (mat == null)
			return false;
		if (mat.length == 0)
			return false;
		return r >= 0 && r < mat.length && c >= 0 && c < mat[0].length;
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
	 * @param str the String in which we're swapping two elements
	 * @param i   the index of one element to be swapped
	 * @param j   the index of one element to be swapped
	 */
	public static String swap(String str, int i, int j) {
		if (i == j)
			return str;
		// by using substrings, like I am, i cannot be larger than j
		if (i > j) {
			int temp = i;
			i = j;
			j = temp;
		}

		// rearrange the string such that i and j get swapped
		String returnVar = str.substring(0, i) + str.charAt(j) + str.substring(i + 1, j) + str.charAt(i);
		if (j < str.length() - 1)
			returnVar += str.substring(j + 1);

		return returnVar;
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
	 * reverses the elements of str. i.e. reverse("0123", 1) gives "0321"
	 *
	 * @param str the string which will be reversed.
	 *
	 * @return str, but reversed
	 */
	public static String reverse(String str) {
		return reverse(str, 0);
	}

	/**
	 * reverses the elements of str, starting at startingIndex. i.e. reverse("0123",
	 * 1) gives "0321"
	 *
	 * @param str           the string which will be reversed.
	 * @param startingIndex the first index to be reversed.
	 *
	 * @return str, but reversed after startingIndex
	 */
	public static String reverse(String str, int startingIndex) {
		String returnStr = str;
		for (int i = startingIndex; i < (startingIndex + str.length()) / 2; i++)
			returnStr = swap(returnStr, i, str.length() - 1 - i);

		return returnStr;
	}

	/**
	 * Creates an iterator, which iterates over all permutations of the array
	 * PLEASE NOTE it is assumed arr is sorted
	 * 
	 * @param arr the sorted array which permutations will be generated from
	 * 
	 * @return an iterator that iterates over all permuations of arr
	 */
	public static <E extends Comparable<E>> Iterator<E[]> permuteIterator(E[] arr) {
		return new Iterator<E[]>() {
			E[] curr = arr;

			@Override
			public boolean hasNext() {
				return curr != null;
			}

			@Override
			public E[] next() {
				E[] retVar = curr;
				curr = permute(curr);
				return retVar;
			}
		};
	}

	/**
	 * Creates an iterator, which iterates over all permutations of the array in
	 * reverse order.
	 * PLEASE NOTE it is assumed arr is sorted
	 * 
	 * @param arr the sorted array which permutations will be generated from
	 * 
	 * @return an iterator that iterates over all permuations of arr
	 */
	public static <E extends Comparable<E>> Iterator<E[]> reversePermuteIterator(E[] arr) {
		return new Iterator<E[]>() {
			E[] curr = arr;

			@Override
			public boolean hasNext() {
				return curr != null;
			}

			@Override
			public E[] next() {
				E[] retVar = curr;
				curr = prevPermute(curr);
				return retVar;
			}
		};
	}

	/**
	 * Creates an iterator, which iterates over all permutations of the ArrayList.
	 * PLEASE NOTE it is assumed list is sorted
	 * 
	 * @param list the sorted ArrayList which permutations will be generated from
	 * 
	 * @return an iterator that iterates over all permuations of list
	 */
	public static <E extends Comparable<E>> Iterator<ArrayList<E>> permuteIterator(ArrayList<E> list) {
		return new Iterator<ArrayList<E>>() {
			ArrayList<E> curr = list;

			@Override
			public boolean hasNext() {
				return !list.isEmpty();
			}

			@Override
			public ArrayList<E> next() {
				ArrayList<E> retVar = curr;
				curr = permute(curr);
				return retVar;
			}
		};
	}

	/**
	 * Creates an iterator, which iterates over all permutations of the string.
	 * PLEASE NOTE it is assumed str is sorted
	 * 
	 * @param str the sorted string which permutations will be generated from
	 * 
	 * @return an iterator that iterates over all permuations of str
	 */
	public static Iterator<String> permuteIterator(String str) {
		return new Iterator<String>() {
			String currStr = str;

			@Override
			public boolean hasNext() {
				return currStr.length() != 0;
			}

			@Override
			public String next() {
				String retVar = currStr;
				currStr = permute(currStr);
				return retVar;
			}
		};
	}

	/**
	 * computes the next lexicographic permutation of str. i.e. permute("0123")
	 * gives "0132". This method does not "wrap around", and if str has reached the
	 * last lexicographic permuation, "" will be returned.
	 * 
	 * @param str the string which will be permuted
	 * 
	 * @return the next permutation of str, or "" if there is no next permutation.
	 */
	public static String permute(String str) {
		if (str == null || str.equals(""))
			return "";

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
		return reverse(str, pivot + 1);
	}

	/**
	 * computes the next lexicographic permutation of arr. i.e. permute([0, 1, 2,
	 * 3])
	 * gives [0, 1, 3, 2]. This method does not "wrap around", and if arr has
	 * reached the
	 * last lexicographic permuation, nullwill be returned.
	 * 
	 * @param arr the arr which will be permuted
	 * 
	 * @return the next permutation of arr, or null if there is no next permutation.
	 */
	public static <E extends Comparable<E>> E[] permute(E[] arr) {
		if (arr == null || arr.length == 0)
			return null;

		// assigns the pivot to before the last "run" in the string
		int pivot = arr.length - 1;
		while (--pivot >= 0 && arr[pivot].compareTo(arr[pivot + 1]) >= 0)
			;

		// if we're already at the last permutation, there is no next one.
		if (pivot == -1)
			return null;

		// finds the last number greater than the pivot
		int lexRoot = arr.length;
		while (--lexRoot > pivot && arr[pivot].compareTo(arr[lexRoot]) >= 0)
			;

		E[] retVar = Arrays.copyOf(arr, arr.length);

		swap(retVar, pivot, lexRoot);

		reverse(retVar, pivot + 1);

		return retVar;
	}

	/**
	 * computes the next lexicographic permutation of list. i.e. permute([0, 1, 2,
	 * 3]) gives [0, 1, 3, 2]. This method does not "wrap around", and if arr has
	 * reached the last lexicographic permuation, [] will be returned.
	 * 
	 * @param list the ArrayList which will be permuted
	 * @return the next permutation of list, or [] if there is no next permutation.
	 */
	public static <E extends Comparable<E>> ArrayList<E> permute(ArrayList<E> list) {
		if (list == null || list.isEmpty())
			return new ArrayList<>();

		// assigns the pivot to before the last "run" in the string
		int pivot = list.size() - 1;
		while (--pivot >= 0 && list.get(pivot).compareTo(list.get(pivot + 1)) >= 0)
			;

		// if we're already at the last permutation, there is no next one.
		if (pivot == -1)
			return new ArrayList<>();

		// finds the last number greater than the pivot
		int lexRoot = list.size();
		while (--lexRoot > pivot && list.get(pivot).compareTo(list.get(lexRoot)) >= 0)
			;

		ArrayList<E> retVar = new ArrayList<>(list);

		swap(retVar, pivot, lexRoot);

		reverse(retVar, pivot + 1);

		return retVar;
	}

	/**
	 * computes the previous lexicographic permutation of str. i.e. permute("3210")
	 * gives "3201"
	 * 
	 * @param str the string which will be permuted
	 * @return the previous permutation of str.
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
	 * computes the previous permutation of arr. ie permute([3, 2, 1, 0])
	 * gives [3, 2, 0, 1]
	 * 
	 * @param <E> a comparable type. The comparision will be used to determine
	 *            what is lexicographically next/previous
	 * @param arr the array to permute
	 * @return the previous permutation of arr
	 */
	public static <E extends Comparable<E>> E[] prevPermute(E[] arr) {
		if (arr == null || arr.length == 0)
			return null;

		// assigns the pivot to before the last "run" in the string
		int pivot = arr.length - 1;
		while (--pivot >= 0 && arr[pivot].compareTo(arr[pivot + 1]) <= 0)
			;

		// if we're already at the last permutation, there is no next one.
		if (pivot == -1)
			return null;

		// finds the last number greater than the pivot
		int lexRoot = arr.length;
		while (--lexRoot > pivot && arr[pivot].compareTo(arr[lexRoot]) <= 0)
			;

		E[] retVar = Arrays.copyOf(arr, arr.length);

		swap(retVar, pivot, lexRoot);

		reverse(retVar, pivot + 1);

		return retVar;
	}

	/**
	 * determines if str1 and str2 are permutations of each other, i.e.
	 * arePermutations("123345", "543123") returns true. This works the same as
	 * arePermutations(String, String, Map), but assumes no alphabet restrictions.
	 * For this reason, it is recommended to use that function if there are
	 * significant restrictions to the strings' character space. i.e. if the strings
	 * are all numbers.
	 * 
	 * @param str1 one string which may or may be a permuted version of str2
	 * @param str2 one string which may or may be a permuted version of str1
	 * @return true if str1 and str2 are permutations of each other
	 */
	public static boolean arePermutations(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;

		// count the characters in str1
		int[] set1 = new int[Character.MAX_VALUE];
		for (char ch : str1.toCharArray())
			set1[(int) ch]++;

		// count the characters in str2
		int[] set2 = new int[Character.MAX_VALUE];
		for (char ch : str2.toCharArray())
			set2[(int) ch]++;

		// chech if they have the same amount of each characters
		return Arrays.equals(set1, set2);
	}

	/**
	 * If str1 and str2 are numeric strings (only contain digits '0'-'9'), then
	 * this determines if str1 and str2 are permutations of each other, i.e.
	 * areNumPermuatations("12345","54321") returns true.
	 * 
	 * @param str1 one string which may or may not be a permuted version of str2
	 * @param str2 one string whihc may or may not be a permuted version of str1
	 * @return true if str1 and str2 are permutations of each other.
	 */
	public static boolean areNumPermutations(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;

		int[] set1 = new int[10];
		for (int i = 0; i < str1.length(); i++)
			set1[str1.charAt(i) - '0']++;

		int[] set2 = new int[10];
		for (int i = 0; i < str2.length(); i++)
			set2[str2.charAt(i) - '0']++;

		return Arrays.equals(set1, set2);
	}

	/**
	 * Determines if str1 and str2 are permuations of each other in linear time,
	 * i.e. arePermutations("123345", "543123", indexing) returns true.
	 *
	 * @param str1     a string which may or may bot be a permuted verion of str2
	 * @param str2     a string which may or may bot be a permuted verion of str1
	 * @param indexing a mapping which maps each character that str1/str2 may have
	 *                 to an index ranging from 0 to indexing.size() - 1
	 *
	 * @return true if str1 and str2 are permutations of each other, otherwise false
	 */
	public static boolean arePermutations(String str1, String str2, Map<Character, Integer> indexing) {
		if (str1.length() != str2.length())
			return false;

		// initialize the character counter for str1
		int[] set1 = new int[indexing.size()];
		for (int i = 0; i < set1.length; i++)
			set1[i] = 0;

		// count each character in str1
		for (char ch : str1.toCharArray())
			set1[indexing.get(ch)]++;

		// initialize the character counter for str2
		int[] set2 = new int[indexing.size()];
		for (int i = 0; i < set2.length; i++)
			set2[i] = 0;

		// count each character in str2
		for (char ch : str2.toCharArray())
			set2[indexing.get(ch)]++;

		// if they have the same amount of each character, then they are permutations.
		return Arrays.equals(set1, set2);
	}

	/**
	 * Given two sorted lists, determines if they don't share any elements (are
	 * disjoint)
	 * 
	 * @param <E>   Comparable type used to determine if listA or listB share
	 *              elements
	 * @param listA a list of comparable elements which may or may not be disjoint
	 *              with listB
	 * @param listB a list of comparable elements which may or may not be disjoint
	 *              with listA
	 * @return true if listA and listB do not share any elements.
	 */
	public static <E extends Comparable<E>> boolean areDisjointSorted(List<E> listA, List<E> listB) {
		if (listA.isEmpty() || listB.isEmpty())
			return true;

		Iterator<E> itA = listA.iterator();
		E currA = itA.next();
		Iterator<E> itB = listB.iterator();
		E currB = itB.next();

		while (true) {
			int cmpVal = currA.compareTo(currB);
			if (cmpVal == 0) // A = B
				return false;
			else if (cmpVal > 0) { // A > B
				if (itB.hasNext())
					currB = itB.next();
				else
					break;
			} else { // cmpVal < 0 // A < B
				if (itA.hasNext())
					currA = itA.next();
				else
					break;
			}
		}

		return true;
	}

	public static <E> void printArray(E[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length - 1; i++) {
			System.out.print(arr[i] + ", ");
		}
		if (arr.length != 0)
			System.out.print(arr[arr.length - 1]);
		System.out.print("]");
	}

	public static <E> void printlnArray(E[] arr) {
		printArray(arr);
		System.out.println();
	}

	public static <E> void printList(List<E> list) {
		System.out.print("[");
		for (int i = 0; i < list.size() - 1; i++) {
			System.out.print(list.get(i) + ", ");
		}
		if (list.size() != 0)
			System.out.print(list.get(list.size() - 1));
		System.out.print("]");
	}

	public static <E> void printlnList(List<E> list) {
		printList(list);
		System.out.println();
	}
}
