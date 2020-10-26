package projectEuler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CollectionTools {

	public static <T> boolean inBounds(List<T> list, int i){
		if(list == null)
			return false;
		return i >= 0 && i < list.size();
	}
	public static <T> boolean inBounds(T[][] mat, int r, int c){
		if(mat == null)
			return false;
		if(mat.length == 0)
			return false;
		return r >= 0 && r < mat.length && c >= 0 && c < mat[0].length;
	}
	public static boolean inBounds(int[][] mat, int r, int c){
		if(mat == null)
			return false;
		if(mat.length == 0)
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
		if(i == j)
			return str;
		// by using substrings, like I am, i cannot be larger than j
		if(i > j){
			int temp = i;
			i = j;
			j = temp;
		}

		// rearrange the string such that i and j get swapped
		String returnVar = str.substring(0, i) + str.charAt(j) + str.substring(i + 1, j) + str.charAt(i);
		if(j < str.length() - 1)
			returnVar +=  str.substring(j + 1);

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
	 * @param str		the string which will be reversed.
	 *
	 * @return str, but reversed
	 */
	public static String reverse(String str){
		return reverse(str, 0);
	}

	/**
	 * reverses the elements of str, starting at startingIndex. i.e.
	 * reverse("0123", 1) gives "0321"
	 *
	 * @param str		the string which will be reversed.
	 * @param startingIndex	the first index to be reversed.
	 *
	 * @return str, but reversed after startingIndex
	 */
	public static String reverse(String str, int startingIndex){
		String returnStr = str;
		for(int i = startingIndex; i < (startingIndex + str.length()) / 2; i++)
			returnStr = swap(returnStr, i, str.length() - 1 - i);

		return returnStr;
	}

	/**
	 * computes the next lexicographic permutation of str. i.e. permute("0123")
	 * gives "0132"
	 * 
	 * @param str the string which will be permuted
	 * @return the next permutation of str.
	 */
	public static String permute(String str) {
		if (str == null || str.equals(""))
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
	 * determines if str1 and str2 are permutations of each other, i.e.
	 * arePermutations("123345", "543123") returns true. This works the same as 
	 * arePermutations(String, String, Map), but assumes no alphabet restrictions. 
	 * For this reason, it is reccomended to use that function if there are significant 
	 * restrictions to the strings' character space. i.e. if the strings are all numbers.
	 * 
	 * @param str1 one string which may or may be a permuted version of str2
	 * @param str2 one string which may or may be a permuted version of str1
	 * @return true if str1 and str2 are permutations of each other
	 */
	public static boolean arePermutations(String str1, String str2) {
		if(str1.length() != str2.length())
			return false;

		// count the characters in str1 
		int[] set1 = new int[Character.MAX_VALUE];
		for(char ch : str1.toCharArray())
			set1[(int)ch]++;

		// count the characters in str2
		int[] set2 = new int[Character.MAX_VALUE];
		for(char ch : str2.toCharArray())
			set2[(int)ch]++;

		// chech if they have the same amount of each characters
		return Arrays.equals(set1, set2);
	}

	public static boolean areNumPermutations(String str1, String str2){
		if(str1.length() != str2.length())
			return false;
		
		int[] set1 = new int[10];
		for(int i = 0; i < str1.length(); i++)
			set1[str1.charAt(i) - '0']++;
		
		int[] set2 = new int[10];
		for(int i = 0; i < str2.length(); i++)
			set2[str2.charAt(i) - '0']++;

		return Arrays.equals(set1, set2);
	}

	/**
	 * Determines if str1 and str2 are permuations of each other in linear time, i.e.
	 * arePermutations("123345", "543123", <indexing>) returns true.
	 *
	 * @param str1     a string which may or may bot be a permuted verion of str2
	 * @param str2     a string which may or may bot be a permuted verion of str1
	 * @param indexing a mapping which maps each character that str1/str2 may have 
	 *                 to an index ranging from 0 to indexing.size() - 1
	 *
	 * @return true if str1 and str2 are permutations of each other, otherwise false
	 */
	public static boolean arePermutations(String str1, String str2, Map<Character, Integer> indexing){
		if(str1.length() != str2.length())
			return false;

		// initialize the character counter for str1
		int[] set1 = new int[indexing.size()];
		for(int i = 0; i < set1.length; i++)
			set1[i] = 0;

		// count each character in str1
		for(char ch : str1.toCharArray())
			set1[indexing.get(ch)]++;
	
		// initialize the character counter for str2
		int[] set2 = new int[indexing.size()];
		for(int i = 0; i < set2.length; i++)
			set2[i] = 0;

		// count each character in str2	
		for(char ch : str2.toCharArray())
			set2[indexing.get(ch)]++;

		// if they have the same amount of each character, then they are permutations.
		return Arrays.equals(set1, set2);
	}
}
