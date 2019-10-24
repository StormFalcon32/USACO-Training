/*
ID: benchen1
LANG: JAVA
TASK: nuggets
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class nuggets {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nuggets.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
		int N = Integer.parseInt(in.readLine());
		int[] input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(in.readLine());
		}
		int gcd = input[0];
		for (int i = 1; i < N; ++i) {
			gcd = gcd(gcd, input[i]);
			if (gcd == 1) {
				break;
			}
		}
		if (gcd != 1 || input.length == 1) {
			out.println(0);
			in.close();
			out.close();
			return;
		}

		Arrays.sort(input);
		int max = input[N - 1] * input[N - 2] * gcd(input[N - 1], input[N - 2]);
		boolean[] dp = new boolean[max + 1];
		dp[0] = true;
		for (int i = 0; i < N; i++) {
			for (int j = input[i]; j <= max; j++) {
				if (dp[j - input[i]]) {
					dp[j] = true;
				}
			}
		}

		for (int i = max; i > 0; i--) {
			if (!dp[i]) {
				out.println(i);
				break;
			}
		}
		out.close();
		in.close();
	}

	static int gcd(int a, int b) {
		if (a == 0) {
			return b;
		}
		if (b == 0) {
			return a;
		}
		if (a == b) {
			return a;
		}
		if (a > b) {
			return gcd(a - b, b);
		}
		return gcd(a, b - a);
	}
}