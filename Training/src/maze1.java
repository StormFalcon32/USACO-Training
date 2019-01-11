import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
ID: benchen1
LANG: JAVA
TASK: maze1
*/

public class maze1 {

	static short R;
	static short C;
	static short V;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		C = Short.parseShort(ln.nextToken());
		R = Short.parseShort(ln.nextToken());
		V = (short) (R * C);
		LinkedList<Short>[] adj = new LinkedList[V];
		short[] exits = new short[2];
		int ext = 0;
		
		for (int i = 0; i < V; i++) {
			adj[i] = new LinkedList<Short>();
		}

		for (int i = 0; i < 2 * R + 1; i++) {
			String str = in.readLine();
			if (i == 0) {
				for (int j = 0; j < 2 * C + 1; j++) {
					int newR = -1;
					int newC = j;
					String curr = str.substring(j, j + 1);
					if (curr.equals(" ")) {
						newR = 1;
						exits[ext] = findVNum(newR, newC);
						ext = 1;
					}
				}
				continue;
			}
			if (i == 2 * R) {
				for (int j = 0; j < 2 * C + 1; j++) {
					int newR = -1;
					int newC = j;
					String curr = str.substring(j, j + 1);
					if (curr.equals(" ")) {
						newR = 2 * R - 1;
						exits[ext] = findVNum(newR, newC);
						ext = 1;
					}
				}
				continue;
			}
			for (int j = 0; j < 2 * C + 1; j++) {
				String curr = str.substring(j, j + 1);
				if (j == 0) {
					int newR = i;
					int newC = -1;
					if (curr.equals(" ")) {
						newC = 1;
						exits[ext] = findVNum(newR, newC);
						ext = 1;
					}
					continue;
				}
				if (j == 2 * C) {
					int newR = i;
					int newC = -1;
					if (curr.equals(" ")) {
						newC = 2 * C - 1;
						exits[ext] = findVNum(newR, newC);
						ext = 1;
					}
					continue;
				}

				if (i % 2 == 0) {
					// Horizontal fence
					if (curr.equals(" ")) {
						short a = findVNum(i - 1, j);
						short b = findVNum(i + 1, j);
						adj[a].add(b);
						adj[b].add(a);
					}

				} else if (j % 2 == 0) {
					// Vertical fence
					if (curr.equals(" ")) {
						short a = findVNum(i, j - 1);
						short b = findVNum(i, j + 1);
						adj[a].add(b);
						adj[b].add(a);
					}
				}
			}
		}

		// We now have an adjacency matrix representation of the graph
		// Do Dijkstra's from each exit
		short[] first = dijkstra(adj, exits[0]);
		short[] second = dijkstra(adj, exits[1]);

		int furthest = 0;
		for (int i = 0; i < V; i++) {
			furthest = Math.max(furthest, Math.min(first[i], second[i]));
		}
		out.println(furthest + 1);
		out.close();
		in.close();
	}

	static short[] dijkstra(LinkedList<Short>[] adj, int root) {
		short[] dist = new short[V];
		for (int i = 0; i < V; i++) {
			dist[i] = 4000;
		}
		boolean[] inSet = new boolean[V];
		dist[root] = 0;

		for (int k = 0; k < V - 1; k++) {

			int u = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < V; i++) {
				if (!inSet[i] && dist[i] < min) {
					u = i;
					min = dist[i];
				}
			}
			inSet[u] = true;

			for (short v = 0; v < V; v++) {
				short distThroughU = (short) (dist[u] + 1);
				if (!inSet[v] && adj[u].contains(v) && distThroughU < dist[v]) {
					dist[v] = distThroughU;
				}
			}
		}
		return dist;
	}

	static short findVNum(int r, int c) {
		int newR = (r - 1) / 2;
		int newC = (c - 1) / 2;
		return (short) (newR * C + newC);
	}
}