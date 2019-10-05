/*
ID: benchen1
LANG: JAVA
TASK: heritage
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

public class heritage {
	
	static int ind = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("heritage.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("heritage.out")));
		char[] inOrder = in.readLine().toCharArray();
		char[] preOrder = in.readLine().toCharArray();
		Stack<Node> s1 = new Stack<Node>();
		Stack<Character> s2 = new Stack<Character>();
		s1.add(calc(inOrder, preOrder, 0, inOrder.length - 1));
		while (!s1.isEmpty()) {
			Node curr = s1.pop();
			s2.push(curr.val);
			if (curr.left != null) {
				s1.push(curr.left);
			}
			if (curr.right != null) {
				s1.push(curr.right);
			}
		}
		while (!s2.isEmpty()) {
			out.print(s2.pop());
		}
		out.println();
		out.close();
		in.close();
	}
	
	static Node calc(char inOrder[], char preOrder[], int i, int j) {
		if (i > j) {
			return null;
		}
		Node toAdd = new Node(preOrder[ind]);
		ind++;
		if (i == j) {
			return toAdd;
		}
		int inInd = 0;
		for (inInd = i; inInd <= j; inInd++) {
			if (inOrder[inInd] == toAdd.val) {
				break;
			}
		}
		toAdd.left = calc(inOrder, preOrder, i, inInd - 1);
		toAdd.right = calc(inOrder, preOrder, inInd + 1, j);
		return toAdd;
	}
	
	static class Node {
		Node left;
		Node right;
		char val;
		
		public Node(char val) {
			this.val = val;
		}
	}
}