/*
ID: benchen1
LANG: JAVA
TASK: skidesign
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class skidesign {
	
	static int n;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\Workspace\\Training\\skidesign\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("skidesign.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		int[] hills = new int[n];
		for (int i = 0; i < n; i++) {
			hills[i] = Integer.parseInt(in.readLine());	
		}
		Arrays.sort(hills);
		int bestPrice = Integer.MAX_VALUE;
		int highestElevation = hills[n - 1];
		int lowestElevation = hills[0];
		for (int low = lowestElevation; low < highestElevation - 17; low++) {
			int currPrice = 0;
			int high = low + 17;
			for (int j = 0; j < n; j++) {
				if (hills[j] < low) {
					currPrice += Math.pow(low - hills[j], 2);
				} else {
					break;
				}
			}
			for (int j = n - 1; j >= 0; j--) {
				if (hills[j] > high) {
					currPrice += Math.pow(hills[j] - high, 2);
				} else {
					break;
				}
			}
			bestPrice = Math.min(bestPrice, currPrice);
		}
		if (highestElevation - lowestElevation <= 17) {
			out.println(0);
		} else {
			out.println(bestPrice);
		}
		out.close();
		in.close();
	}
}