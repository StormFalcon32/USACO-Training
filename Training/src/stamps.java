/*
ID: benchen1
LANG: JAVA
TASK: stamps
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class stamps {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("stamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int K = Integer.parseInt(ln.nextToken());
		int N = Integer.parseInt(ln.nextToken());
		int[] stamps = new int[N];
		int max = 0;
		int i = 0;
		while (i < N) {
			ln = new StringTokenizer(in.readLine());
			while (ln.hasMoreTokens()) {
				stamps[i] = Integer.parseInt(ln.nextToken());
				max = Math.max(max, stamps[i]);
				i++;
			}
		}
		max *= K;
		Arrays.sort(stamps);
		int[] dp = new int[max + 1];
		// Dp[i] is the least amount of stamps needed to make i cents
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		i = 0;
		for (i = 0; i < max + 1; i++) {
			for (int j = 0; j < N; j++) {
				int curr = stamps[j];
				if (i >= curr) {
					dp[i] = Math.min(dp[i], dp[i - curr] + 1);
				}
			}
			if (dp[i] > K) {
				break;
			}
		}
		out.println(i - 1);
		out.close();
		in.close();
	}
}