
/*
ID: benchen1
LANG: JAVA
TASK: nocows
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class nocows {

	static long[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nocows.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int nodes = Integer.parseInt(ln.nextToken());
		int height = Integer.parseInt(ln.nextToken());
		dp = new long[nodes + 1][height + 1];
		for (int i = 0; i < nodes + 1; i++) {
			for (int j = 0; j < height + 1; j++) {
				dp[i][j] = -1;
			}
		}
		out.println(calc(nodes, height) % 9901);
		out.close();
		in.close();
	}

	static long calc(int nodes, int height) {
		if (nodes % 2 == 0)
			return 0;
		if (nodes < 2 * height - 1)
			return 0;
		if (height == 1) {
			if (nodes == 1) {
				return 1;
			}
			return 0;
		}
		if (dp[nodes][height] != -1) {
			return dp[nodes][height];
		}
		dp[nodes][height] = 0;
		for (int i = 1; i + 1 < nodes; i++) {
			// Nodes in subtree 1 + nodes in subtree 2 + root node = total nodes
			int nodesInSub1 = i;
			int nodesInSub2 = nodes - nodesInSub1 - 1;
			long sol = 0;
			for (int j = 1; j + 1 < height; j++) {
				sol += calc(nodesInSub1, j) * calc(nodesInSub2, height - 1);
				long a = calc(nodesInSub1, height - 1);
				long b = calc(nodesInSub2, j);
				sol +=  a * b;
			}
			sol += calc(nodesInSub1, height - 1) * calc(nodesInSub2, height - 1);
			dp[nodes][height] += sol % 9901;
		}
		return dp[nodes][height];
	}
}