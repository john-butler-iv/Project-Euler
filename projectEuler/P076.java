package projectEuler;

import java.util.*;
import java.math.BigInteger;

public class P076 extends ParameterizedProblem<Integer> {
	/**
	 * Keeps track of both the index needed to go back and whether to add or subtract.
	 * It would be possible to refactor to add or subtract by any scalar by changing sign to an int, but that's
	 * unnecessary for our use case
	 */
	private class FibInfo {
		static final boolean ADD = true;
		static final boolean SUB = false;
		int offset;
		boolean sign;
		public FibInfo(int offset, boolean sign) {
			this.offset = offset;
			this.sign = sign;
		}
	}

	/**
	 * Iterates through the gaps between the different "Fibonacci indices". Used by findFibAdds()
	 */
	private Iterator<Integer> findFibGaps(){
		return new Iterator<Integer>(){
			// start with index 0
			boolean odd = false;
			int evenVal = 1;
			int oddVal = 1;
			
			public boolean hasNext(){
				return odd || evenVal > 0;
			}

			public Integer next(){
				int retVal;
				if(odd) {
					retVal = oddVal;
					oddVal++;
				} else {
					retVal = evenVal;
					evenVal += 2;
				}
				odd = !odd;
				
				return retVal;
			}
		};
	}

	/**
	 * Iterates through the different indices which you need to add/sub together to find the next partition number.
	 * For example for the real Fiboanacci sequence, it would be {1, 2}, since you only add the last two terms.
	 * I should probably refactor this into a separate file, but I don't want to deal with two files. Also it
	 * only adds ~50 lines to keep here.
	 */
	private Iterator<FibInfo> findFibAdds(){
		return new Iterator<FibInfo>(){
			int currSign = 0;		
			Iterator<Integer> gapIt = findFibGaps();
			int currOff = 0;

			public boolean hasNext(){
				// there is actaully a restriction from overflow, but to figure that out, I would keep track of the
				// next return value and I don't want to do that.
				// Also I'm using this value to index in an array, and if it overflows, I'll run out of memory
				return true; 
			}

			public FibInfo next(){
				currOff += gapIt.next();
				
				// we don't need to reset currSign. Even if there's an overflow, currSign & 2 will still 
				// do it
				boolean sign = (currSign++ & 2) == 2 ? FibInfo.SUB : FibInfo.ADD;
				
				return new FibInfo(currOff, sign);
			}
		};
	}
	
	@Override
	public Integer getDefaultParameter(){
		return 100;
	}
	
	public long solve(Integer targetN, boolean printResults){
		// random access to previous partition numbers is critical, so we use an array
		ArrayList<Integer> prevParts = new ArrayList<Integer>();
		// we only ever iterate through the elements, so I use a linked list for constant insert
		LinkedList<FibInfo> fibAdds = new LinkedList<FibInfo>();
		Iterator<FibInfo> addIt = findFibAdds();

		prevParts.add(1);
		fibAdds.addLast(addIt.next());

		// represents p(n)
		for(int n = 1; n <= targetN; n++){
			int p = 0;

			// we want to make sure we know all of the partition numbers we need to add, so we 
			// ensure we have more than we actually need
			if(fibAdds.getLast().offset <= prevParts.size())
				fibAdds.addLast(addIt.next());

			Iterator<FibInfo> it = fibAdds.iterator();
			FibInfo currFib;
			// add up all relavent previous partition numbers
			while((currFib = it.next()).offset <= prevParts.size()){
				int partVal = prevParts.get(prevParts.size() - currFib.offset);
				if(currFib.sign == FibInfo.SUB)
					partVal *= -1;

				// this sequence grows really quickly, but still fits in an int through n = 100
				p += partVal; 
			}
			
			prevParts.add(p);
		}
		
		long result = prevParts.get(prevParts.size() - 1) - 1;

		if(printResults)
			System.out.println("There are " + result + " unique ways to sum to " + targetN); 
		return result;
	}

	@Override
	public Integer getTestParameter(){
		return 5;
	}

	@Override
	public long getTestSolution(){
		return 6;
	}

	public String getTitle(){
		return "Problem 076: Counting Summations";
	}
}
