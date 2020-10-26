package projectEuler;

import java.util.Scanner;
import java.io.File;
import java.math.BigInteger;

public class P099 extends Problem{
	public long solve(boolean printResults){
		try {
			Scanner scanner = new Scanner(new File("p099.txt"));

			int currLine = 0;
			int bestLine = 0;
			BigInteger bestNum = BigInteger.ZERO;
			BigInteger bestBase = BigInteger.ZERO;
			int bestPow = 0;
			
			while(scanner.hasNext()){
				String[] strs = scanner.nextLine().split(",");
				System.out.println("currLine: " + currLine + " " + strs[0] + "^" + strs[1]);

				BigInteger base = new BigInteger(strs[0]);
				int pow = Integer.valueOf(strs[1]);

				if(base.compareTo(bestBase) > 0
						|| pow > bestPow) {
					BigInteger value = base.pow(pow);
					if(value.compareTo(bestNum) > 0){
						bestNum = value;
						bestLine = currLine;
						bestBase = base;
						bestPow = pow;
					}
				}
				currLine++;
			}

			if(printResults)
				System.out.println(bestLine + " was the line with the greatest value");
			return bestLine;

		} catch(Exception e){
			if(printResults)
				System.out.println("Error occured");
			return 0;
		}
	}

	public String getTitle(){
		return "Problem 099: Largest Exponential";
	}
}
