package projectEuler;

import java.util.LinkedList;
import java.util.Queue;

public class P072 extends ParameterizedProblem<Integer>{
	private class Node {
		int num, den;
		Node next;
		Node prev;
		public Node(int num, int den, Node prev, Node next){
			this.num = num;
			this.den = den;
			this.prev = prev;
			this.next = next;
		}
	}

	public Integer getDefaultParameter(){
		return 1000000;
	}

	public long solve(Integer maxD, boolean printResults){
		Node head = new Node(0, 1, null, null);
		Node oneHalf = new Node(1, 2, head, null);
		Node tail = new Node(1, 1, oneHalf, null);
		head.next = oneHalf;
		oneHalf.next = tail;

		// we don't count 0 or 1 in the fraction set.
		int size = 1;

		Node currNode;
		int origSize;

		Queue<Node> updates = new LinkedList<>();
		updates.add(oneHalf);
		
		// this is still too slow
		while(!updates.isEmpty()){
			currNode = updates.poll();

			int leftDen = currNode.den + currNode.prev.den;
			if(leftDen <= maxD){
				int leftNum = currNode.num + currNode.prev.num;
				Node newNode = new Node(leftNum, leftDen, currNode.prev, currNode);
				currNode.prev.next = newNode;
				currNode.prev = newNode;
				size++;
				updates.add(newNode);
			}

			int rightDen = currNode.den + currNode.next.den;
			if(rightDen <= maxD){
				int rightNum = currNode.num + currNode.next.num;
				Node newNode = new Node(rightNum, rightDen, currNode, currNode.next);
				currNode.next.prev = newNode;
				currNode.next = newNode;
				size++;
				updates.add(newNode);
			}
		}

		if(printResults)
			System.out.println("There are " + size + " elements in the set of reduced proper fractions for d <= "
					+ maxD);
		
		return size;
	}

	public Integer getTestParameter(){
		return 8;
	}

	public long getTestSolution(){
		return 21;
	}

	public String getTitle(){
		return "Problem 072: Counting Fractions";
	}
}
