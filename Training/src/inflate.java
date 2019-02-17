/*
ID: benchen1
LANG: JAVA
TASK: inflate
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class inflate {
	
	static int[] points;
	static int[] times;
	
	static long[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("inflate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int M = Integer.parseInt(ln.nextToken());
		int N = Integer.parseInt(ln.nextToken());
		points = new int[N];
		times = new int[N];
		dp = new long[M + 1];
		for (int i = 0; i < N; i++) {
			ln = new StringTokenizer(in.readLine());
			points[i] = Integer.parseInt(ln.nextToken());
			times[i] = Integer.parseInt(ln.nextToken());
		}
		// Dp[x] is the best score using exactly x minutes
		for (int i = 0; i < N; i++) {
			for (int j = times[i]; j < M + 1; j++) {
				dp[j] = Math.max(dp[j], points[i] + dp[j - times[i]]);
			}
		}
		out.println(dp[M]);
		out.close();
		in.close();
	}
}