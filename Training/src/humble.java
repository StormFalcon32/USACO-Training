
/*
ID: benchen1
LANG: JAVA
TASK: humble
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class humble {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int K = Integer.parseInt(ln.nextToken());
		int N = Integer.parseInt(ln.nextToken());
		ln = new StringTokenizer(in.readLine());
		int[] nums = new int[K];
		for (int i = 0; i < K; i++) {
			nums[i] = Integer.parseInt(ln.nextToken());
		}
		long[] humble = new long[N + 1];
		int[] prev = new int[K + 1];
		humble[0] = 1;
		for (int i = 0; i < N; i++) {
			long next = Long.MAX_VALUE;
			ArrayList<Integer> possible = new ArrayList<Integer>();
			for (int j = 0; j < K; j++) {
				long check = nums[j] * humble[prev[j]];
				if (check > humble[i] && check <= next) {
					if (check < next) {
						next = check;
						possible.clear();
						possible.add(j);
					} else if (check == next) {
						possible.add(j);
					}
				}
			}
			humble[i + 1] = next;
			for (int j : possible) {
				prev[j]++;
			}
		}
		out.println(humble[N]);
		out.close();
		in.close();
	}
}