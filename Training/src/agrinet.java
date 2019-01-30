
/*
ID: benchen1
LANG: JAVA
TASK: agrinet
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class agrinet {

	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		N = Integer.parseInt(ln.nextToken());
		int[][] adjMat = new int[N][N];
		for (int i = 0; i < N; i++) {
			ln = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				if (ln.hasMoreTokens()) {
					adjMat[i][j] = Integer.parseInt(ln.nextToken());
				} else {
					ln = new StringTokenizer(in.readLine());
					adjMat[i][j] = Integer.parseInt(ln.nextToken());
				}
			}
		}
		int total = 0;
		int[] parent = primAdjMat(adjMat);
		for (int i = 1; i < N; i++) {
			total += adjMat[i][parent[i]];
		}
		out.println(total);
		out.close();
		in.close();
	}

	static int[] primAdjMat(int adj[][]) {
		// Array to store constructed MST
		int parent[] = new int[N];

		int key[] = new int[N];
		Arrays.fill(key, Integer.MAX_VALUE);

		boolean mstSet[] = new boolean[N];

		key[0] = 0;
		parent[0] = -1;

		for (int count = 0; count < N - 1; count++) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;

			for (int v = 0; v < N; v++) {
				if (mstSet[v] == false && key[v] < min) {
					min = key[v];
					minIndex = v;
				}
			}
			int u = minIndex;
			mstSet[u] = true;
			for (int v = 0; v < N; v++) {
				if (adj[u][v] != 0 && mstSet[v] == false && adj[u][v] < key[v]) {
					parent[v] = u;
					key[v] = adj[u][v];
				}
			}
		}
		return parent;
	}
}