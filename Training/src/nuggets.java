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

public class nuggets {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("nuggets.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nuggets.out")));
		int N = Integer.parseInt(in.readLine());
		int[] input = new int[N];
		boolean[] redundant = new boolean[N];
		int numRedundant = 0;
		int max = 0;
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(in.readLine());
			max = Math.max(max, input[i]);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					continue;
				}
				if (Math.max(input[i], input[j]) % Math.min(input[i], input[j]) == 0) {
					redundant[input[i] > input[j] ? i : j] = true;
					numRedundant++;
				}
			}
		}
		numRedundant /= 2;
		N -= numRedundant;
		int[] boxes = new int[N];
		int ind = 0;
		for (int i = 0; i < N + numRedundant; i++) {
			if (!redundant[i]) {
				boxes[ind] = input[i];
				ind++;
			}
		}
		boolean[] dp = new boolean[max + 1];
		for (int i = 0; i < N; i++) {
			int multiple = max / boxes[i];
			for (int j = 0; j <= multiple; j++) {
				dp[boxes[i] * j] = true;
			}
		}
		int lastImpossible = 0;
		for (int i = 0; i < max + 1; i++) {
			if (!dp[i]) {
				lastImpossible = i;
			}
		}
		for (int i = max + 1; i < 2000000001; i++) {
			if (i - lastImpossible > max) {
				break;
			}
			for (int j = 0; j < max; j++) {
				dp[j] = dp[j + 1];
			}
			boolean possible = false;
			for (int j = 0; j < N; j++) {
				if (dp[max - boxes[j]]) {
					possible = true;
					break;
				}
			}
			dp[max] = possible;
			lastImpossible = !possible ? i : lastImpossible;
		}
		out.println(lastImpossible);
		out.close();
		in.close();
	}
}