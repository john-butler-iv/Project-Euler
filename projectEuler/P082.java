package projectEuler;

import java.util.Scanner;
import java.io.File;
import java.util.PriorityQueue;
import java.util.ArrayList;


public class P082 extends ParameterizedProblem<String> {
	private static class Dijkstra {
		public int r;
		public int c;
		public int weight;
		public int totalWeight;
		public Dijkstra cameFrom;

		public Dijkstra(int r, int c, int weight){
			this.r = r;
			this.c = c;
			this.weight = weight;
			totalWeight = weight;
			cameFrom = null;
		}

		public void update(Dijkstra cameFrom){
			totalWeight = cameFrom.totalWeight + weight;
			this.cameFrom = cameFrom;
		}

		public boolean equals(Dijkstra obj){
			return r == obj.r && c == obj.c;
		}

		public int compareTo(Dijkstra obj){
			return totalWeight - obj.totalWeight;
		}
	}

	@Override
	public String getDefaultParameter(){
		return "p081.txt";
	}

	@Override
	public long solve(String filename, boolean printResults){
		int [][] graph = readData(filename);

		int smallestPath = Integer.MAX_VALUE;
		Dijkstra smallestNode = null;
		for(int i = 0; i < graph[0].length; ++i) {
            PriorityQueue<Dijkstra> pq = new PriorityQueue<Dijkstra>(Dijkstra::compareTo);

            Dijkstra curr = new Dijkstra(i, 0, graph[i][0]);
            pq.add(curr);

            boolean[][] visited = new boolean[graph.length][graph[0].length];

			ArrayList<Dijkstra> dirs = new ArrayList<>();
            while (curr.c != graph[0].length-1) {
                curr = pq.poll();
                visited[curr.r][curr.c] = true;
				
				// find valid directions
				dirs.clear();
				if(CollectionTools.inBounds(graph, curr.r, curr.c + 1))
					dirs.add(new Dijkstra(curr.r, curr.c + 1, graph[curr.r][curr.c + 1]));
				if(CollectionTools.inBounds(graph, curr.r - 1, curr.c))
					dirs.add(new Dijkstra(curr.r - 1, curr.c, graph[curr.r - 1][curr.c]));
				if(CollectionTools.inBounds(graph, curr.r + 1, curr.c))
					dirs.add(new Dijkstra(curr.r + 1, curr.c, graph[curr.r + 1][curr.c]));
			
				// process those directions
				for(Dijkstra dir : dirs){
					dir.update(curr);
					if(!visited[dir.r][dir.c] && (curr.cameFrom == null || !curr.cameFrom.equals(dir)))
						pq.add(dir);
				}
            }

            if(smallestNode == null || smallestPath > curr.totalWeight){
                smallestNode = curr;
                smallestPath = curr.totalWeight;
            }
        }
        System.out.println(smallestPath + " was the smallest path sum for the grid in file " + filename);
		return 0;
	}

	public int[][] readData(String filename){
		int[][]mat = null;
		try{
			Scanner scanner = new Scanner(new File(filename));
			int size = scanner.nextInt();
			mat = new int[size][size];
			
			for(int r = 0; r < mat.length; r++)
				for(int c = 0; c < mat[0].length; c++)
					mat[r][c] = scanner.nextInt();
				
			scanner.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return mat;	
	}

	@Override
	public String getTestParameter(){
		return "p081test.txt";
	}

	@Override
	public long getTestSolution(){
		return 994;
	}

	@Override
	public String getTitle(){
		return "Problem 082: Path Sum: Three Ways";
	}

}
