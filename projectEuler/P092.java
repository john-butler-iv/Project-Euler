package projectEuler;

import java.util.ArrayList;

public class P092 extends Problem {
	@Override
	public long solve(boolean printResults){
		// records the ending number
		short[] memo = new short[10000000];
		memo[1] = 1;
		memo[89] = 89;

		// counts how many end at 89
		int cnt = 0;

		int currentNum = 0;
		ArrayList<Integer> chain = new ArrayList<>();
		for(int startingNum = 2; startingNum < memo.length; startingNum++){
			currentNum = startingNum;
			chain.clear();

			while(true){
				// calculate the next in the chain
				String str = String.valueOf(currentNum);
				currentNum = 0;
				for(char ch : str.toCharArray())
					currentNum += (ch -'0') * (ch - '0');
			
				// if we find a hit, stop there	
				if(memo[currentNum] == 89){
					for(int digit : chain)
						memo[digit] = 89;
					// 89's are only counted at their own starting num 
					cnt++;
					break;
				} else if(memo[currentNum] == 1){
					for(int digit : chain)
						memo[digit] = 1;
					break;
				} else
					chain.add(currentNum);
			}
		}

		if(printResults)
			System.out.println(cnt + " numbers below 10,000,000 arive at 89.");
		return cnt;
	}

	@Override
	public String getTitle(){
		return "Problem 092: Square Digit Chains";
	}
}
