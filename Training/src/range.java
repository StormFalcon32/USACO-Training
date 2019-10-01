/*
ID: benchen1
LANG: JAVA
TASK: range
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class range {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("range.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
		int N = Integer.parseInt(in.readLine());
		boolean[][] field = new boolean[N][N];
		int[][] dp = new int[N][N];
		// dp[r][c] is number of squares with top left corner at (r, c)
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < N; j++) {
				field[i][j] = Integer.parseInt(line.substring(j, j + 1)) == 1;
			}
		}
		for (int i = 0; i < N; i++) {
			dp[N - 1][i] = field[N - 1][i] ? 1 : 0;
			dp[i][N - 1] = field[i][N - 1] ? 1 : 0;
		}
		for (int r = N - 2; r >= 0; r--) {
			for (int c = N - 2; c >= 0; c--) {
				if (field[r][c]) {
					int borders = 0;
					for (int i = 1; i <= dp[r + 1][c + 1]; i++) {
						if (field[r][c + i] && field[r + i][c]) {
							borders++;
						} else {
							break;
						}
					}
					dp[r][c] = Math.min(borders, dp[r + 1][c + 1]) + 1;
				}
			}
		}
		int[] count = new int[N + 1];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				count[dp[r][c]]++;
			}
		}
		int[] sums = new int[N + 1];
		sums[N] = count[N];
		for (int i = N - 1; i >= 0; i--) {
			sums[i] = sums[i + 1] + count[i];
		}
		for (int i = 2; i < N + 1; i++) {
			int x = sums[i];
			if (x == 0) {
				break;
			}
			out.println(i + " " + sums[i]);
		}
		out.close();
		in.close();
	}
}