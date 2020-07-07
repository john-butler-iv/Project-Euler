package projectEuler;

import java.util.ArrayList;

public class P074 extends Problem {
	final static int[] factorial = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

	@Override
	public long solve(boolean printResults){
		int[] memo = new int[3000000];

		// we keep track of the other numbers in the chain so we can record their chain lengths
		ArrayList<Integer> otherIndices = new ArrayList<>();
		int total = 0;

		for(int i = 3; i < 1000000; i++){
			otherIndices.clear();
			int num = i;
			int length = 0;

			// while the next number isn't alreay in the chain (no repeats)
			while(!otherIndices.contains(num)){
				otherIndices.add(num);
				length++;
				num = nextNumber(num);
				// don't bother computing if I already calculated in the memo
				if(num < memo.length && memo[num] != 0){
					length += memo[num];
					break;
				}
			}
			if(length == 60)
				total ++;
			// record the other links' lengths in the memo so we don't have to recompute them.
			while(!otherIndices.isEmpty()){
				int index = otherIndices.remove(0);	
				memo[index] = length;
				length --;		
			}
		}
		if(printResults)
			System.out.println(total + " numbers less than 1,000,000 give a chain which contains excatly "
					+ "60 distinct terms");
		return total;
	}

	private int nextNumber(int num){
		String str = String.valueOf(num);
		int newNum = 0;
		for(char ch : str.toCharArray())
			newNum += factorial[ch - '0'];
		return newNum;
	}
	
	@Override
	public String getTitle(){
		return "Problem P074: Digit Factorial Chains";
	}
}
