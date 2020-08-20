package projectEuler;

public class P090 extends Problem {
	private boolean satisfies(String square, String die1, String die2){
		
		// does die1 have the 10's place and die2 have the 1's place?
		if(die1.charAt(square.charAt(0) - '0') == '1' 
				&& die2.charAt(square.charAt(1) - '0') == '1')
			return true;
		// does die2 have the 10's place and die1 have the 1's place?
		if(die2.charAt(square.charAt(0) - '0') == '1' 
				&& die1.charAt(square.charAt(1) - '0') == '1')
			return true;
		
		// we can replace each 6 with 9, so check those too
		if(square.charAt(0) == '6'){
			if(die1.charAt(9) == '1' && die2.charAt(square.charAt(1) - '0') == '1')
				return true;
			if(die2.charAt(9) == '1' && die1.charAt(square.charAt(1) - '0') == '1')
				return true;
		} else if (square.charAt(1) == '6'){
			if(die1.charAt(square.charAt(0) - '0') == '1' && die2.charAt(9) == '1')
				return true;
			if(die2.charAt(square.charAt(0) - '0') == '1' && die1.charAt(9) == '1')
				return true;
		}

		// there's no other way to create the string
		return false;
	}

	public long solve(boolean printResults){
		// I replace all '9's with '6's
		String[] squares = { "01", "04", "06", "16", "25", "36", "46", "64", "81" };

		int validDice = 0;

		// I brute force the problem, since there's only 44100 possible dice combiniations.
		// The format I'm using is like a table: die1.charAt(i) == '1' iff the die has that number on it
		String die1 = "0000111111";
		do{
			String die2 = "0000111111";
			do{
				
				boolean works = true;
				// check each square to see if it's valid
				for(String square : squares){
					if(!satisfies(square, die1, die2)){
						works = false;
						break;
					}
				}
				
				if(works)
					validDice++;

				// iterating through permutations ensures we keep the same amount of numbers on the die
				die2 = CollectionTools.permute(die2);
			} while(die2.length() > 0);
			die1 = CollectionTools.permute(die1);
		} while(die1.length() > 0);

		// in my counting, the order of the dice matters, so I remove the reordering of the dice
		validDice /= 2;

		if (printResults)
			System.out.println("There are " + validDice + " unique sets of 2 dice that can show all squares below 100.");
		return validDice;
	}


	public String getTitle(){
		return "Problem 090: Cube Digit Pairs";
	}
}
