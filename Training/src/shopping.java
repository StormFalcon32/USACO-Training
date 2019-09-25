
/*
ID: benchen1
LANG: JAVA
TASK: shopping
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class shopping {
	
	static int[][][][][] dp = new int[6][6][6][6][6];
	static int S;
	static int B;
	static int[][] offers;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("shopping.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		S = Integer.parseInt(tk.nextToken());
		HashMap<Integer, Integer> norm = new HashMap<Integer, Integer>();
		offers = new int[105][6];
		int mapping = 1;
		for (int i = 0; i < S; i++) {
			tk = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(tk.nextToken());
			for (int j = 0; j < num; j++) {
				int c = Integer.parseInt(tk.nextToken());
				int k = Integer.parseInt(tk.nextToken());
				if (!norm.containsKey(c)) {
					norm.put(c, mapping);
					mapping++;
				}
				c = norm.get(c);
				offers[i][c] = k;
			}
			int cost = Integer.parseInt(tk.nextToken());
			offers[i][0] = cost;
		}
		tk = new StringTokenizer(in.readLine());
		B = Integer.parseInt(tk.nextToken());
		int[] purchases = new int[6];
		for (int i = 0; i < B; i++) {
			tk = new StringTokenizer(in.readLine());
			int c = Integer.parseInt(tk.nextToken());
			int k = Integer.parseInt(tk.nextToken());
			int p = Integer.parseInt(tk.nextToken());
			if (!norm.containsKey(c)) {
				norm.put(c, mapping);
				mapping++;
			}
			c = norm.get(c);
			offers[S + i][c] = 1;
			offers[S + i][0] = p;
			purchases[c] = k;
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 6; k++) {
					for (int l = 0; l < 6; l++) {
						for (int m = 0; m < 6; m++) {
							dp[i][j][k][l][m] = -1;
						}
					}
				}
			}
		}
		dp[0][0][0][0][0] = 0;
		out.println(calc(purchases[1], purchases[2], purchases[3], purchases[4], purchases[5]));
		out.close();
		in.close();
	}
	
	static int calc(int i, int j, int k, int l, int m) {
		if (dp[i][j][k][l][m] != -1) {
			return dp[i][j][k][l][m];
		}
		int min = Integer.MAX_VALUE;
		int[] before = { i, j, k, l, m };
		int[] after = new int[5];
		for (int x = 0; x < S + B; x++) {
			boolean valid = true;
			for (int y = 0; y < 5; y++) {
				after[y] = before[y] - offers[x][y + 1];
				if (after[y] < 0) {
					valid = false;
					break;
				}
			}
			if (valid) {
				dp[after[0]][after[1]][after[2]][after[3]][after[4]] = calc(after[0], after[1], after[2], after[3], after[4]);
				min = Math.min(min, dp[after[0]][after[1]][after[2]][after[3]][after[4]] + offers[x][0]);
			}
		}
		return min;
	}
}