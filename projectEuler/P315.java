package projectEuler;

import java.util.List;

public class P315 extends Problem {
	
	public long solve(boolean printResults){
		PrimeFinder pf = new PrimeFinder(20000000);
		List<Integer> primes = pf.getPrimes();

		//TODO optimize search
		int index = 0;
		while(pf.get(index) < 10000000)
			index++;

		//	000
		// 1   2
		// 1   2
		// 1   2
		//  333
		// 4   5
		// 4   5
		// 4   5
		//  666
		final boolean[][] digits = new boolean[][]{
			{ true,  true,  true, false,  true,  true,  true}, // 0 
			{false, false,  true, false, false,  true, false}, // 1
			{ true, false,  true,  true,  true, false,  true}, // 2
			{ true, false,  true,  true, false,  true,  true}, // 3
			{false,  true,  true,  true, false,  true, false}, // 4
			{ true,  true, false,  true, false,  true,  true}, // 5
			{ true,  true, false,  true,  true,  true,  true}, // 6
			{ true, false,  true, false, false,  true, false}, // 7
			{ true,  true,  true,  true,  true,  true,  true}, // 8
			{ true,  true,  true,  true, false,  true,  true}, // 9
		};

		for(int i = index; i < primes.size(); i++){
			
		}
		
		return 0;
	}

	public String getTitle(){
		return "Problem 315: Digital Root Clocks";
	}
}
