
/*
ID: benchen1
LANG: JAVA
TASK: sort3
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class sort3 {

	static int numVals;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numVals = Integer.parseInt(ln.nextToken());
		int[] vals = new int[numVals];
		for (int i = 0; i < numVals; i++) {
			vals[i] = Integer.parseInt(in.readLine());
		}
		out.println(minSwaps(vals));
		out.close();
		in.close();
	}

	public static int minSwaps(int[] vals) {
		int n = vals.length;
		int num1s = 0;
		int num2s = 0;
		for (int i = 0; i < n; i++) {
			if (vals[i] == 1) {
				num1s++;
			}
			if (vals[i] == 2) {
				num2s++;
			}
		}
		num2s += num1s;
		int num1in2 = 0;
		int num2in1 = 0;
		int num1in3 = 0;
		int num3in1 = 0;
		int num2in3 = 0;
		int num3in2 = 0;
		for (int i = 0; i < n; i++) {
			if (i < num1s) {
				if (vals[i] == 2) {
					num2in1++;
				} else if (vals[i] == 3) {
					num3in1++;
				}
			} else if (i < num2s) {
				if (vals[i] == 1) {
					num1in2++;
				} else if (vals[i] == 3) {
					num3in2++;
				}
			} else {
				if (vals[i] == 1) {
					num1in3++;
				} else if (vals[i] == 2) {
					num2in3++;
				}
			}
		}
		int swaps = Math.min(num1in2, num2in1) + Math.min(num1in3, num3in1) + Math.min(num2in3, num3in2)
				+ 2 * (Math.max(num1in2, num2in1) - Math.min(num1in2, num2in1));

		return swaps;
	}
}