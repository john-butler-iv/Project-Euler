package projectEuler;

import java.util.Arrays;

public class P068 extends Problem {

	private String getSide(String slnSet, int index){
		// one "side" is solnSet[i], solnSet[i+1], solnSet[(i+3) % len]
		return "" + slnSet.charAt(index) + slnSet.charAt(index+1) + slnSet.charAt((index+3) % slnSet.length());
	}

	private String[] isValid(String slnSet){
		String[] formattedStr = new String[slnSet.length() / 2];

		int sum = -1;
		System.out.println();
		System.out.println(slnSet);
		for(int i = 0; i < slnSet.length(); i+= 2){
			// for each side, unpack the number and find the sum
			String currLine = getSide(slnSet, i);
			System.out.print(currLine + ";");
			int currSum = 0;
			for(char ch : currLine.toCharArray())
				currSum += ch;

			// check if we're still valid
			if(sum == -1)
				sum = currSum;
			else if(sum != currSum)
				return null;

			// update "Solution Set" string
			formattedStr[i/2] = currLine;
		}

		return formattedStr;
	}

	public long solve(boolean printResults){
		String slnSet = "123456";//789a";

		String[] bestSln = null;


		do {
			String[] formattedString = isValid(slnSet);
			if(formattedString != null) {
				if(bestSln != null)
				for(int i = 0; i < bestSln.length; i++)
					System.out.println(bestSln[i] + ";");

				Arrays.sort(formattedString);
				if(bestSln == null || Arrays.compare(formattedString, bestSln) > 0)
					bestSln = formattedString;
			}
			
			slnSet = CollectionTools.permute(slnSet);
		} while(!slnSet.equals(""));//slnSet != null);

		System.out.println("sln: ");
		for(int i = 0; i < bestSln.length; i++)
			System.out.print(bestSln[i] + ";");
		

		return 0;
	}

	public String getTitle(){
		return "Problem 068: Magic 5-gon ring";
	}
}
