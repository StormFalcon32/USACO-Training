/*
ID: benchen1
LANG: JAVA
TASK: cowtour
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class cowtour {
	
	static int N;
	static int INF = 1 << 20;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("cowtour.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		N = Integer.parseInt(ln.nextToken());
		Pasture[] pastures = new Pasture[N];
		for (int i = 0; i < N; i++) {
			ln = new StringTokenizer(in.readLine());
			int x = Integer.parseInt(ln.nextToken());
			int y = Integer.parseInt(ln.nextToken());
			pastures[i] = new Pasture(x, y);
		}
		double[][] distance = new double[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				distance[i][j] = INF;
			}
			distance[i][i] = 0;
		}
		for (int i = 0; i < N; i++) {
			String s = in.readLine();
			for (int j = 0; j < N; j++) {
				int a = Integer.parseInt(s.substring(j, j + 1));
				if (a == 1) {
					distance[i][j] = dist(pastures[i], pastures[j]);
				}
			}
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (distance[i][k] + distance[k][j] < distance[i][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		// worstPossible[i] is the longest path you can take starting at i
		double[] worstPossible = new double[N];
		double best = Double.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				worstPossible[i] = (distance[i][j] != INF) ? Math.max(worstPossible[i], distance[i][j]) : worstPossible[i];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (distance[i][j] == INF) {
					best = Math.min(best, worstPossible[i] + worstPossible[j] + dist(pastures[i], pastures[j]));
				}
			}
		}
		for (int i = 0; i < N; i++) {
			best = Math.max(best, worstPossible[i]);
		}
		out.println(String.format("%.6f", best));
		out.close();
		in.close();
	}
	
	static double dist(Pasture a, Pasture b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}
	
	static class Pasture {
		int x;
		int y;
		
		public Pasture(int a, int b) {
			x = a;
			y = b;
		}
	}
}