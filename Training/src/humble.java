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
		int[] humble = new int[N + 1];
		System.out.println(humble[N]);
		out.close();
		in.close();
	}
}