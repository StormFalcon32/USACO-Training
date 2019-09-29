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
		boolean[][][] dp = new boolean[N][N][N + 1];
		// dp[r][c][l] is whether there is a square with top left corner at (r, c) and l length sides
		boolean[][][] dpCross = new boolean[N][N][N + 1];
		// dpCross[r][c][l] is whether there is a cross centered at (r, c) with length l
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < N; j++) {
				field[i][j] = Integer.parseInt(line.substring(j, j + 1)) == 1;
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				dp[r][c][1] = field[r][c];
				dpCross[r][c][1] = field[r][c];
			}
		}
		for (int l = 2; l <= N; l++) {
			int count = 0;
			for (int r = 0; r + l <= N; r++) {
				for (int c = 0; c + l <= N; c++) {
					if (l % 2 == 0) {
						dp[r][c][l] = dp[r][c][l / 2] && dp[r + (l / 2)][c][l / 2] && dp[r][c + (l / 2)][l / 2] && dp[r + (l / 2)][c + (l / 2)][l / 2];
					} else {
						boolean cross = field[r + (l / 2)][c] && field[r + (l / 2)][c + l - 1] && field[r][c + (l / 2)] && field[r + l - 1][c + (l / 2)] && dpCross[r + (l / 2)][c + (l / 2)][l - 2];
						dpCross[r + (l / 2)][c + (l / 2)][l] = cross;
						dp[r][c][l] = dp[r][c][l / 2] && dp[r + (l / 2) + 1][c][l / 2] && dp[r][c + (l / 2) + 1][l / 2] && dp[r + (l / 2) + 1][c + (l / 2) + 1][l / 2] && cross;
					}
					if (dp[r][c][l]) {
						count++;
					}
				}
			}
			if (count == 0) {
				break;
			}
			out.println(l + " " + count);
		}
		out.close();
		in.close();
	}
}