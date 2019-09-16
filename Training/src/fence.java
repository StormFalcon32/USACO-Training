
/*
ID: benchen1
LANG: JAVA
TASK: fence
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class fence {
	int[] circuit;
	int circuitInd = 0;
	LinkedList<Integer>[] adjList;
	
	public fence(LinkedList<Integer>[] adjList, int N) {
		this.adjList = adjList;
		circuit = new int[N + 1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fence.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tk.nextToken());
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] adjList = new LinkedList[500];
		for (int i = 0; i < 500; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(tk.nextToken()) - 1;
			int b = Integer.parseInt(tk.nextToken()) - 1;
			adjList[a].add(b);
			adjList[b].add(a);
		}
		for (int i = 0; i < 500; i++) {
			Collections.sort(adjList[i]);
		}
		int root = 0;
		for (int i = 0; i < 500; i++) {
			if (adjList[i].size() % 2 == 1) {
				root = i;
				break;
			}
		}
		fence fence = new fence(adjList, N);
		fence.findCircuit(root);
		for (int i = N; i >= 0; i--) {
			out.println((fence.circuit[i] + 1));
		}
		out.close();
		in.close();
	}
	
	public void findCircuit(int curr) {
		LinkedList<Integer> adj = adjList[curr];
		if (adj.isEmpty()) {
			circuit[circuitInd] = curr;
			circuitInd++;
		} else {
			while (!adj.isEmpty()) {
				int toAdd = adj.poll();
				adjList[toAdd].remove(new Integer(curr));
				findCircuit(toAdd);
			}
			circuit[circuitInd] = curr;
			circuitInd++;
		}
	}
}