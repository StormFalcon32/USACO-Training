
/*
ID: benchen1
LANG: JAVA
TASK: butter
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class butter {
	
	static final int INF = Integer.MAX_VALUE;
	
	int N;
	int P;
	int C;
	
	public butter(int N, int P, int C) {
		this.N = N;
		this.P = P;
		this.C = C;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("butter.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tk.nextToken());
		int P = Integer.parseInt(tk.nextToken());
		int C = Integer.parseInt(tk.nextToken());
		int[] pastures = new int[N];
		@SuppressWarnings("unchecked")
		LinkedList<ButterEdge>[] adjList = new LinkedList[P];
		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(in.readLine());
			pastures[i] = Integer.parseInt(tk.nextToken()) - 1;
		}
		for (int i = 0; i < P; i++) {
			adjList[i] = new LinkedList<ButterEdge>();
		}
		for (int i = 0; i < C; i++) {
			tk = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(tk.nextToken()) - 1;
			int b = Integer.parseInt(tk.nextToken()) - 1;
			int dist = Integer.parseInt(tk.nextToken());
			adjList[a].add(new ButterEdge(b, dist));
			adjList[b].add(new ButterEdge(a, dist));
		}
		butter solve = new butter(N, P, C);
		int best = INF;
		for (int i = 0; i < P; i++) {
			long[] dists = solve.dijkstra(adjList, i);
			int sum = 0;
			for (int j = 0; j < N; j++) {
				sum += dists[pastures[j]];
			}
			best = Math.min(best, sum);
		}
		out.println(best);
		out.close();
		in.close();
	}
	
	public long[] dijkstra(LinkedList<ButterEdge>[] adjList, int root) {
		Queue<ButterNode> heap = new PriorityQueue<ButterNode>();
		long[] dist = new long[P];
		Arrays.fill(dist, INF);
		boolean[] inSet = new boolean[P];
		heap.add(new ButterNode(root, 0));
		dist[root] = 0;
		
		while (!heap.isEmpty()) {
			int u = heap.poll().num;
			inSet[u] = true;
			LinkedList<ButterEdge> adj = adjList[u];
			ListIterator<ButterEdge> iterate = adj.listIterator();
			while (iterate.hasNext()) {
				ButterEdge currButterEdge = iterate.next();
				int v = currButterEdge.other;
				long distThroughU = dist[u] + currButterEdge.weight;
				if (!inSet[v]) {
					if (distThroughU < dist[v]) {
						dist[v] = distThroughU;
						ButterNode toAdd = new ButterNode(v, distThroughU);
						heap.add(toAdd);
					}
				}
			}
		}
		return dist;
	}
}

class ButterNode implements Comparable<ButterNode> {
	int num;
	long dist;
	
	public ButterNode(int num, long dist) {
		this.num = num;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(ButterNode other) {
		// TODO Auto-generated method stub
		if (this.dist == other.dist) {
			return Integer.compare(this.num, other.num);
		}
		return Long.compare(this.dist, other.dist);
	}
}

class ButterEdge {
	int other;
	int weight;
	
	public ButterEdge(int o, int w) {
		other = o;
		weight = w;
	}
}