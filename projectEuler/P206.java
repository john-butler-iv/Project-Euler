package projectEuler;

public class P206 extends Problem{
	@Override
	public long solve(boolean printResults){

		// for motivation of the bounds, check writeup
		//
		// until I make it, here's a rough sketch.
		// the square must be 1*2*3*4*5*6*7*8*9*0, 
		// so the square must be between 1020304050607080900 and 1929394959697989990
		// so the square root must be between sqrt(1020304050607080900) = 101010101010.1
		// and sqrt(1929394959697989990)=138902623.1
		// additionally, the last digit must be a 0, so the square and root must be a multilple of 10.
		
		for(int root = 1010101020; root <= 1389026623; root += 10){
			long square = (long)root*(long)root;
			String str = Long.toString(square);

			// check square is of the form
			boolean satisfies = true;
			// we don't have to check the last digit, because we delt with that in the loop
			for(int i = 1; i <= 9; i++){
				if(str.charAt(2*(i-1)) != i + '0'){
					satisfies = false;
					break;
				}
			}

			if(satisfies){
				if(printResults)
					System.out.println(root + "^2 = " + str + ", which is of the form 1_2_3_4_5_6_7_8_9_0");
				return root;
			}

		}

		if(printResults)
			System.out.println("Could not find any squares of the form 1_2_3_4_5_6_7_8_9_0");
		return -1;
	}

	@Override
	public String getTitle(){
		return "Problem 206: Conceraled Square";
	}
}
