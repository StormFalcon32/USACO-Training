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
		int[][] adjMat = new int[N][N];
		for (int i = 0; i < N; i++) {
			String s = in.readLine();
			for (int j = 0; j < N; j++) {
				adjMat[i][j] = Integer.parseInt(s.substring(j, j + 1));
			}
		}
		
		out.close();
		in.close();
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