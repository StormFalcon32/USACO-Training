
/*
ID: benchen1
LANG: JAVA
TASK: sprime
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class sprime {

	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		for (int i = 0; i < n; i++) {

		}
		out.close();
		in.close();
	}
}