/*
ID: benchen1
LANG: JAVA
TASK: game1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class game1 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("game1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tk.nextToken());
		int[] board = new int[N];
		int[][][] dp = new int[N][N][2];
		// dp[i][j][k] is the best score player k can get in the interval from i to j inclusive if both players are playing optimally
		String line = in.readLine();
		int ind = 0;
		while (line != null) {
			tk = new StringTokenizer(line);
			while (tk.hasMoreElements()) {
				board[ind] = Integer.parseInt(tk.nextToken());
				ind++;
			}
			line = in.readLine();
		}
		for (int i = 0; i < N; i++) {
			dp[i][i][0] = board[i];
			dp[i][i][1] = 0;
		}
		for (int l = 1; l < N; l++) {
			for (int i = 0; i + l < N; i++) {
				int p1Score1 = board[i] + dp[i + 1][i + l][1];
				int p1Score2 = board[i + l] + dp[i][i + l - 1][1];
				int p2Score1 = dp[i + 1][i + l][0];
				int p2Score2 = dp[i][i + l - 1][0];
				if (p1Score1 - p2Score1 > p1Score2 - p2Score2) {
					dp[i][i + l][0] = p1Score1;
					dp[i][i + l][1] = p2Score1;
				} else {
					dp[i][i + l][0] = p1Score2;
					dp[i][i + l][1] = p2Score2;
				}
			}
		}
		out.println(dp[0][N - 1][0] + " " + dp[0][N - 1][1]);
		out.close();
		in.close();
	}
}