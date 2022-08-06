package projectEuler;

import java.util.*;
import java.io.File;

public class P098 extends Problem {

	private static final String FILENAME = "p098.txt";

	private List<String> allWords;
	private HashMap<String, List<String>> anagrams;

	public P098() {
		allWords = readData(FILENAME);
		anagrams = new HashMap<>();
	}

	@Override
	public boolean test() {
		return findBestSquare("ACER", "RACE", "CARE") == 9216;
	}

	@Override
	public long solve(boolean printResults) {
		linkAnagrams();
		// based on CARE/RACE example
		int largestSquare = 9216;
		int bestDigits = 4;

		for (String key : anagrams.keySet()) {
			List<String> words = anagrams.get(key);
			// If we've found a 5-digit square, no 4-digit square will be larger.
			if (words.get(0).length() < bestDigits)
				continue;

			int localBest = findBestSquare(key, words.get(0), words.get(1));
			if (localBest > largestSquare) {
				largestSquare = localBest;
				bestDigits = words.get(0).length();
			}
		}

		if (printResults)
			System.out.println(largestSquare + " is the largest square number formed by an anagram letter replacement");

		return 0;
	}

	private List<String> readData(String filename) {
		List<String> words = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File(filename));
			scanner.useDelimiter(",");

			while (scanner.hasNext()) {
				String word = scanner.next();
				// strip quotes
				word = word.substring(1, word.length() - 1);
				// toUpperCase is redundent, but just to be safe
				words.add(word.toUpperCase());
			}

			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return words;
	}

	private String getKey(String word) {
		int[] letters = new int[26];
		for (char ch : word.toCharArray()) {
			letters[(ch - 'A')]++;
		}
		String key = "";
		for (int i = 0; i < letters.length; i++) {
			while (letters[i] > 0) {
				key += (char) (i + 'A');
				letters[i]--;
			}
		}
		return key;
	}

	private void linkAnagrams() {
		// map words to their letters
		Set<String> nonAnagrams = new HashSet<>();
		for (String word : allWords) {
			String key = getKey(word);
			// keep track of singleton sets
			if (anagrams.get(key) == null) {
				// if letter combination not in use, first seen -- may be only one
				anagrams.put(key, new ArrayList<String>());
				nonAnagrams.add(key);
			} else {
				// if set already exists, then we've found an anagram.
				nonAnagrams.remove(key);
			}

			anagrams.get(key).add(word);
		}

		// cull non anagrams
		for (String key : nonAnagrams) {
			if (anagrams.get(key).size() == 1) {
				anagrams.remove(key);
			}
		}
	}

	private static int findBestSquare(String key, String word1, String word2) {
		int[] mappingIndexes = new int[26];
		for (int i = 0; i < mappingIndexes.length; i++)
			mappingIndexes[i] = -1;

		mappingIndexes[key.charAt(0) - 'A'] = 0;
		int numUniqueChars = 1;

		for (int i = 1; i < key.length(); i++)
			if (key.charAt(i) != key.charAt(i - 1))
				mappingIndexes[key.charAt(i) - 'A'] = numUniqueChars++;

		int bestSquare = 0;

		Iterator<Integer[]> it = CollectionTools.permuteIterator(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });// "0123456789");
		do {
			Integer[] mapping = it.next();
			// for (int mapping = 0; mapping < Math.pow(10, numUniqueChars); mapping++) {
			int mappedWord1 = takeMapping(mappingIndexes, mapping, word1);
			int mappedWord2 = takeMapping(mappingIndexes, mapping, word2);
			if (String.valueOf(mappedWord1).length() != String.valueOf(mappedWord2).length())
				continue;
			if (EulerTools.isSquare(mappedWord1) && EulerTools.isSquare(mappedWord2))
				bestSquare = Math.max(bestSquare, Math.max(mappedWord1, mappedWord2));
		} while (it.hasNext());

		return bestSquare;
	}

	private static int takeMapping(int[] mappingIndexes, Integer[] mapping, String word) {
		int mappedNum = 0;
		int place = 1;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(word.length() - 1 - i);
			int mappingIndex = mappingIndexes[ch - 'A'];
			int mappedDigit = 0;
			if (mappingIndex < mapping.length)
				mappedDigit = mapping[mappingIndex];
			mappedNum += mappedDigit * place;

			place *= 10;
		}
		return mappedNum;
	}

	@Override
	public String getTitle() {
		return "Problem 098: Anagramic Squares";
	}

}
