/*
ID: benchen1
LANG: JAVA
TASK: fence9
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class fence9 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fence9.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence9.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		int P = Integer.parseInt(tk.nextToken());
		int count = 1 + gcd(N, M);
		count += P + 1;
		count += 1 + gcd(Math.abs(N - P), M);
		count -= 3;
		double total = M * P / 2.0;
		out.println((int) (total + 1 - count / 2.0));
		out.close();
		in.close();
	}
	
	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
	
	static double slope(int x1, int y1, int x2, int y2) {
		return ((double) y1 - y2) / ((double) x1 - x2);
	}
}