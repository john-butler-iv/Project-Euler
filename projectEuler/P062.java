package projectEuler;

import java.util.ArrayList;
import java.util.Map;
import java.util.Hashtable;

public class P062 extends ParameterizedProblem<Integer> {
	private static class Node {
		String cubeString;
		ArrayList<Long> permCubesRoots;

		public Node(long root){
			cubeString = String.valueOf(root*root*root);
			permCubesRoots = new ArrayList<Long>();
			permCubesRoots.add(root);
		}

		public Node addCube(long root){
			permCubesRoots.add(root);
			return this;
		}
		public int thisPerms(){
			return permCubesRoots.size();
		}
	}

	@Override
	public Integer getDefaultParameter() {
		return 5;
	}

	@Override
	public long solve(Integer numPerms, boolean printResults) {
		ArrayList<Node> cubes = new ArrayList<>();
		
		// create an indexing for CollectionTools.arePermutations(String, String, Map)
		Map<Character, Integer> indexing = new Hashtable<>();
		for(char i = '0'; i <= '9'; i++)
			indexing.put(i , i - '0');


		// if root < 0, then overflow occured. I could use BigIntegers, but primitive types are easier.
		for(long root = 1; root > 0; root++) {
			long cube = root*root*root;
			String cubeStr = String.valueOf(cube);

			boolean foundPerm = false;
			for(int j = 0; j < cubes.size(); j++) {

				// trim obsolete entries
				if(cubes.get(j).cubeString.length() < cubeStr.length()){
					cubes.remove(j--);
					continue;
				}

				// if we find a permutation
				if(CollectionTools.arePermutations(cubes.get(j).cubeString, cubeStr, indexing)) {
					foundPerm = true;
					
					cubes.set(j, cubes.get(j).addCube(root));

					// check if we found enough
					if(cubes.get(j).thisPerms() == numPerms){
						long cube1 = cubes.get(j).permCubesRoots.get(0);
						cube1 = cube1*cube1*cube1;

						if(printResults)
							System.out.println(cube1 + " is the smallest cube with " + numPerms 
									+ " permutations that are cubes.");
						return cube1;
					}

					break;
				}
			}

			// if not already part of a set, make a new one
			if(!foundPerm)
				cubes.add(new Node(root));
		}

		if(printResults)
			System.out.println("There are no cubes that have " + numPerms +
				   	" permuations that are cubes and can fit in a long (<=" + Long.MAX_VALUE + ").");
		return 0;
	}

	@Override
	public Integer getTestParameter() {
		return 3;
	}

	@Override
	public long getTestSolution() {
		return 	41063625;
	}

	@Override
	public String getTitle(){
		return "Problem 062: Cubic Permutations";
	}
}
