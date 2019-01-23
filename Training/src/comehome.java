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
	
	static String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("comehome.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int PATHS = Integer.parseInt(ln.nextToken());
		int[][] adjMat = new int[52][52];
		for (int i = 0; i < 52; i++) {
			for (int j = 0; j < 52; j++) {
				adjMat[i][j] = 1 << 20;
			}
		}
		ArrayList<Integer> startingPoints = new ArrayList<Integer>();
		for (int i = 0; i < PATHS; i++) {
			ln = new StringTokenizer(in.readLine());
			String f = ln.nextToken();
			String l = ln.nextToken();
			int fNum = 0;
			int lNum = 0;
			int dist = Integer.parseInt(ln.nextToken());
			if (f.equals(f.toUpperCase()) && !f.equals("Z")) {
				startingPoints.add(f.toUpperCase().charAt(0) - 'A');
				fNum = f.toUpperCase().charAt(0) - 'A';
			} else {
				fNum = (f.toUpperCase().charAt(0) - 'A') + 26;
			}
			if (l.equals(l.toUpperCase()) && !l.equals("Z")) {
				startingPoints.add(l.toUpperCase().charAt(0) - 'A');
				lNum = l.toUpperCase().charAt(0) - 'A';
			} else {
				lNum = (l.toUpperCase().charAt(0) - 'A') + 26;
			}
			if (f.equals("Z")) {
				fNum = 25;
			}
			if (l.equals("Z")) {
				lNum = 25;
			}
			adjMat[fNum][lNum] = dist;
			adjMat[lNum][fNum] = dist;
		}
		int[] distances = dijkstra(adjMat, 25);
		String bestLetter = "";
		int best = Integer.MAX_VALUE;
		for (int i = 0; i < startingPoints.size(); i++) {
			if (distances[startingPoints.get(i)] < best) {
				best = distances[startingPoints.get(i)];
				bestLetter = alphabet[startingPoints.get(i)];
			}
		}
		out.println(bestLetter + " " + best);
		out.close();
		in.close();
	}
	
	static int[] dijkstra(int[][] adj, int root) {
		int[] dist = new int[52];
		Arrays.fill(dist, 1 << 20);
		boolean[] inSet = new boolean[52];
		dist[root] = 0;
		
		for (int k = 0; k < 50; k++) {
			
			int u = -1;
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < 52; i++) {
				if (!inSet[i] && dist[i] < min) {
					u = i;
					min = dist[i];
				}
			}
			inSet[u] = true;
			
			for (int v = 0; v < 52; v++) {
				int distThroughU = dist[u] + adj[u][v];
				if (!inSet[v] && adj[u][v] != 0 && distThroughU < dist[v]) {
					dist[v] = distThroughU;
				}
			}
		}
		return dist;
	}
}