package projectEuler;

import java.util.Arrays;

public class P205 extends Problem {

	public double[] calcOdds(int numDice, int faces){
		// I'm using a lookuptable for each number
		// the maximum roll is getting the max number on all dice
		int[] numRolls = new int[numDice * faces + 1];

		int totalRolls = (int) Math.pow(faces, numDice);
		for(int rolls = 0; rolls < totalRolls; rolls++){
			String roll = Integer.toString(rolls, faces);
	
			// leading 0's (1 rolls) aren't counted
			int total = numDice - roll.length();
			// add up the value on the dice
			for(char die : roll.toCharArray())
				total += die - '0' + 1;

			// update table
			numRolls[total] ++;
		}

		// convert number of rolls to probability of each outcome (= ways to roll # / totalRolls)
		double[] odds = new double[numRolls.length];
		for(int i = numDice; i < numRolls.length; i++)
			odds[i] = ((double) numRolls[i]) / (double) totalRolls;

		return odds;
	}

	@Override
	public long solve(boolean printResults){
		double[] peteOdds = calcOdds(9, 4);
		double[] colinOdds = calcOdds(6, 6);
		

		// the minimum number able to be rolled is all ones on all dice, 
		// so the starting number should be the number of dice
		double finalOdds = 0.0;
		for(int p = 9; p < peteOdds.length; p++)
			// the index represents the roll, and we want pete to win, so we break when c >= p
			for(int c = 6; c < p; c++)
				finalOdds += peteOdds[p] * colinOdds[c];


		if(printResults)
			System.out.println("The probability that Pyramidal Pete beats Cubic Colin is " + finalOdds);

		return (long) (finalOdds * 10000000);
	}

	@Override
	public String getTitle() {
		return "Problem 205: Dice Game";
	}
}
