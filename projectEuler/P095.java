package projectEuler;

import java.util.ArrayList;
import java.util.HashSet;

public class P095 extends Problem {
	@Override
	public long solve(boolean printResults){
		ArrayList<Integer> longestChain = new ArrayList<>();
		HashSet<Integer> seen = new HashSet<>();
		// it might be faster to just do 1000, but idk, I need to time it
		PrimeFinder pf = new PrimeFinder(1000000);

		int[] mapsTo = new int[1000001];
		mapsTo[1] = 1;

		for(int i = 2; i < mapsTo.length; i++)
			mapsTo[i] = pf.sigma(i);


		ArrayList<Integer> currentChain = new ArrayList<>();
		for(int i = 2; i < mapsTo.length; i++){
			currentChain.clear();

			int next = i;
			do{
				currentChain.add(next);
				seen.add(next);
				next = mapsTo[next] - next;

				// values are capped at 1 million
				if(next > 1000000){
					currentChain.clear();
					next = 0;
				}
			} while(!currentChain.contains(next) && !seen.contains(next));

			// we check if the chain is the longest we've seen
			int index = currentChain.indexOf(next);
			if(index != -1 && currentChain.size() - index > longestChain.size())
				longestChain = new ArrayList<>(currentChain.subList(index, currentChain.size()));
		}
		// find the smallest of the longest chain
		int smallest = longestChain.get(0);
		for(int i : longestChain) 
			if(smallest > i)
				smallest = i;

		if(printResults)
			System.out.println(smallest + " is the smallest number in the longest amicable chain "
					+"where all numbers do not excede 1,000,000.");
		return smallest;
	}

	@Override
	public String getTitle(){
		return "Problem 095: Amicable Chains";
	}
}
