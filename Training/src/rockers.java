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

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("rockers.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rockers.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tk.nextToken());
		int T = Integer.parseInt(tk.nextToken());
		int M = Integer.parseInt(tk.nextToken());
		int[] discs = new int[N];
		tk = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			discs[i] = Integer.parseInt(tk.nextToken());
		}
		out.close();
		in.close();
	}
}