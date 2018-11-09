/*
ID: benchen1
LANG: JAVA
TASK: numtri
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class numtri {
	
	static int ROWS;
	static int highestSum = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		ROWS = Integer.parseInt(ln.nextToken());
		int[][] triangle = new int[ROWS][ROWS];
		int[][] sums = new int[ROWS][ROWS];
		for (int i = 0; i < ROWS; i++) {
			ln = new StringTokenizer(in.readLine());
			for (int j = 0; j < i + 1; j++) {
				triangle[i][j] = Integer.parseInt(ln.nextToken());
			}
		}
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < r + 1; c++) {
				int better = Math.max(getValAt(sums, r - 1, c), getValAt(sums, r - 1, c - 1));
				sums[r][c] = triangle[r][c] + better;
				if (r == ROWS - 1) {
					highestSum = Math.max(highestSum, sums[r][c]);
				}
			}
		}
		out.println(highestSum);
		out.close();
		in.close();
	}
	
	static int getValAt(int[][] sums, int r, int c) {
		if (c < 0 || c >= sums.length || r < 0 || r >= sums.length) {
//			If out of bounds return 0
			return 0;
		}
		return sums[r][c];
	}
}