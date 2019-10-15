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
		discs = new int[N];
		tk = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			discs[i] = Integer.parseInt(tk.nextToken());
		}
		int ans = 0;
		for (int x = 0; x < 1 << N; x++) {
			int sum = 0;
			int time = T * M;
			for (int i = 0; i < N; i++) {
				if ((x & (1 << i)) == 1 << i) {
					int tOnDisc = time % T;
					if (discs[i] > tOnDisc) {
						time -= discs[i] + tOnDisc;
					} else {
						time -= discs[i];
					}
					sum++;
				}
			}
			if (time < 0) {
				continue;
			}
			ans = Math.max(ans, sum);
		}
		out.println(ans);
		out.close();
		in.close();
	}
}