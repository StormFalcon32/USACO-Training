/*
ID: benchen1
LANG: JAVA
TASK: comehome
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class comehome {
	
	static String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int PATHS = Integer.parseInt(ln.nextToken());
		int[][] adjMat = new int[26][26];
		ArrayList<Integer> startingPoints = new ArrayList<Integer>();
		for (int i = 0; i < PATHS; i++) {
			ln = new StringTokenizer(in.readLine());
			String f = ln.nextToken();
			String l = ln.nextToken();
			int dist = Integer.parseInt(ln.nextToken());
			if (f.equals(f.toUpperCase()) && !f.equals("Z")) {
				startingPoints.add(f.toUpperCase().charAt(0) - 'A');
			}
			adjMat[f.toUpperCase().charAt(0) - 'A'][l.toUpperCase().charAt(0) - 'A'] = dist;
		}
		int[] distances = dijkstra(adjMat, 25);
		String bestLetter = "";
		int best = Integer.MAX_VALUE;
		for (int i = 0; i < startingPoints.size(); i++) {
			if (distances[startingPoints.get(i)] < best) {
				best = adjMat[25][startingPoints.get(i)];
				bestLetter = alphabet[startingPoints.get(i)];
			}
		}
		System.out.println(bestLetter + " " + best);
		out.close();
		in.close();
	}
	
	static int[] dijkstra(int[][] adj, int root) {
		int[] dist = new int[26];
		Arrays.fill(dist, 1 << 20);
		boolean[] inSet = new boolean[26];
		dist[root] = 0;
		
		for (int k = 0; k < 26 - 1; k++) {
			
			int u = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 26; i++) {
				if (!inSet[i] && dist[i] < min) {
					u = i;
					min = dist[i];
				}
			}
			inSet[u] = true;
			
			for (int v = 0; v < 26; v++) {
				int distThroughU = dist[u] + adj[u][v];
				if (!inSet[v] && adj[u][v] != 0 && distThroughU < dist[v]) {
					dist[v] = distThroughU;
				}
			}
		}
		return dist;
	}
}