package projectEuler;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class P079 extends Problem {
	
	@Override
	public long solve(boolean printResults){
		// we're making a couple of assumptions for this problem:
		//  1. there are only numbers in password (verified by looking at log)
		//  2. each number only appears once

		ArrayList<String> logs = readData("p079.txt");
		boolean[] validVertices = new boolean[10];

		// create a directed matrix where each number points to the preceding ones, 
		// also find out which letters aren't in the password
		boolean[][] graph = new boolean[10][10];
		for(String log : logs){
			int a = log.charAt(0) - '0';
			int b = log.charAt(1) - '0';
			int c = log.charAt(2) - '0';

			graph[a][b] = true;
			graph[b][c] = true;

			validVertices[a] = true;
			validVertices[b] = true;
			validVertices[c] = true;
		}


		// find a topological ordering for the 
		Stack<Integer> stack = new Stack<Integer>();
		boolean []visited = new boolean[10];

		for(int i = 0; i < 10; i++)
			if(validVertices[i] &&visited[i] == false)
				topoSort(i, visited, graph, stack);


		// read out password
		String password = "";
		while(!stack.empty())
				password += String.valueOf(stack.pop());

		if(printResults)
			System.out.println("The password is " + password);
		return Long.valueOf(password);
	}
	
	private static void topoSort(int v, boolean[] visited, boolean[][] graph, Stack<Integer> stack){
		visited[v] = true;

		for(int i = 0; i < graph[v].length; i++){
			if(!graph[v][i])
				continue;
			if(!visited[i])
				topoSort(i, visited, graph, stack);
		}

		stack.push(v);
	}

	private static ArrayList<String> readData(String filename){
		ArrayList<String> data = null;
		try{
			Scanner scanner = new Scanner(new File(filename));
			data = new ArrayList<>();

			while(scanner.hasNext())
				data.add(scanner.next());

			scanner.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	@Override
	public String getTitle(){
		return "Problem 079: Passcode Derivation";
	}
}
