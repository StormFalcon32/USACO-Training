
/*
ID: benchen1
LANG: JAVA
TASK: kimbits
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class kimbits {
	
	int N;
	int L;
	long I;
	long[][] size;
	
	public kimbits(int N, int L, long I, long[][] size) {
		this.N = N;
		this.L = L;
		this.I = I;
		this.size = size;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("kimbits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tk.nextToken());
		int L = Integer.parseInt(tk.nextToken());
		long I = Long.parseLong(tk.nextToken());
		long[][] size = new long[N + 1][L + 1];
		// num ints that are N bits long and have L set bits
		size[0][0] = 1;
		size[1][0] = 1;
		size[1][1] = 1;
		for (int i = 2; i < N + 1; i++) {
			for (int j = 0; j < L + 1; j++) {
				if (j == 0) {
					size[i][j] = size[i - 1][j];
					continue;
				}
				size[i][j] = size[i - 1][j] + size[i - 1][j - 1];
				// size[i][j] = num ints that are N - 1 bits long that start with either 0 or 1
			}
		}
		kimbits solve = new kimbits(N, L, I, size);
		out.println(solve.binSearch(N, L, I, new boolean[N]));
		out.close();
		in.close();
	}
	
	public String binSearch(int x, int y, long i, boolean[] ans) {
		if (x == 0) {
			StringBuilder sb = new StringBuilder("");
			for (int a = N - 1; a >= 0; a--) {
				if (ans[a]) {
					sb.append("1");
				} else {
					sb.append("0");
				}
			}
			return sb.toString();
		}
		int num = 0;
		for (int a = 0; a <= y; a++) {
			num += size[x - 1][a];
		}
		if (i <= num) {
			return binSearch(x - 1, y, i, ans);
		} else {
			ans[x - 1] = true;
			return binSearch(x - 1, y - 1, i - num, ans);
		}
	}
	
	public String format(String str) {
		while (str.length() < N) {
			str = "0" + str;
		}
		return str;
	}
}