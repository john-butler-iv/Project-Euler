package projectEuler;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class P089 extends Problem {
	@Override
	public long solve(boolean printResults){
		ArrayList<String> originalNumerals = readData();

		int totalSaved = 0;
		for(String numeral : originalNumerals)
			totalSaved += numeral.length() - numeralLength(evaluateNumeral(numeral));
	
		if(printResults)
			System.out.println("Using minimal numerals save " + totalSaved + " characters over the entire list");
		return totalSaved;
	}
	
	public static int evaluateNumeral(String numeral){
		int num = 0;
		
		for(int i = 0; i < numeral.length(); i++){
			int curr = lookupNumeral(numeral.charAt(i));

			int next = 0;
			if(i != numeral.length() - 1)
				next = lookupNumeral(numeral.charAt(i+1));

			// if we have to subtract
			if(next != 0 && curr < next){
				num += next - curr;
				i++;
			} else
				num += curr;

		}

		return num;
	}

	private static int numeralLength(int num){
		int numLength = num / 1000;
		num %= 1000;
		
		for(int digitMask = 1; digitMask <= num; digitMask *= 10){
			int digit = (num / digitMask) % 10;
			numLength += digitLength(digit);
		}
		return numLength;
	}

	public static int lookupNumeral(char ch){
		switch(ch){
			case 'I':
				return 1;
			case 'V':
				return 5;
			case 'X':
				return 10;
			case 'L':
				return 50;
			case 'C':
				return 100;
			case 'D':
				return 500;
			case 'M':
				return 1000;
			default:
				return 0;
		}
	}

	private static int digitLength(int digit){
		switch(digit){
			case 8: // VIII
				return 4;
			case 7: // VII
			case 3: // III
				return 3;
			case 9: // IX
			case 6: // VI
			case 4: // IV
			case 2: // II
				return 2;
			case 5: // V
			case 1: // I
				return 1;
		}
		return 0;
	}

	private static ArrayList<String> readData(){
		try {
			Scanner scanner = new Scanner(new File("p089.txt"));

			ArrayList<String> numbers = new ArrayList<String>();
			while(scanner.hasNext())
				numbers.add(scanner.next());

			return numbers;

		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}	

	@Override
	public String getTitle(){
		return "Problem 089: Roman Numerals";
	}
}
