/*
ID: benchen1
LANG: JAVA
TASK: rockers
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class rockers {
	static int N;
	static int T;
	static int M;
	static int[] discs;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		N = Integer.parseInt(tk.nextToken());
		T = Integer.parseInt(tk.nextToken());
		M = Integer.parseInt(tk.nextToken());
		dp = new int[N][M * T + 1];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		discs = new int[N];
		tk = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			discs[i] = Integer.parseInt(tk.nextToken());
		}
		out.println(recurse(0, T * M, 0));
		out.close();
		in.close();
	}

	static int recurse(int ind, int time, int numDiscs) {
		if (ind >= N) {
			return numDiscs;
		}
		if (time <= 0) {
			return numDiscs;
		}
		if (dp[ind][time] != -1) {
			return dp[ind][time];
		}
		int timeLeft = 0;
		int timeOnDisc = time % T;
		if (discs[ind] > timeOnDisc) {
			timeLeft = time - timeOnDisc - discs[ind];
		} else {
			timeLeft = time - discs[ind];
		}
		int ans = 0;
		if (discs[ind] > time) {
			ans = recurse(ind + 1, time, numDiscs);
		} else {
			ans = Math.max(recurse(ind + 1, time, numDiscs), recurse(ind + 1, timeLeft, numDiscs + 1));
		}
		dp[ind][time] = ans;
		return ans;
	}
}