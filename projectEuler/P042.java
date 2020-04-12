package projectEuler;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class P042 extends Problem {

	@Override
	long solve(boolean printResults) {
		int cnt = 0;

		try {
			Scanner scanner = new Scanner(new File("p042.txt"));

			while (scanner.hasNext()) {
				// figure out the word's number
				int num = wordToNumber(scanner.next().toUpperCase());
				// increment counter if it's triangular
				if (EulerTools.isTriangular(num))
					cnt++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (printResults)
			System.out.println(cnt + " is the number of triangular words in the text file.");
		return cnt;
	}

	private static int wordToNumber(String word) {
		int returnVal = 0;

		for (char ch : word.toCharArray())
			returnVal += ch - 'A' + 1;

		return returnVal;
	}

	@Override
	String getTitle() {
		return "Problem 042: Coded triangle numbers";
	}

}