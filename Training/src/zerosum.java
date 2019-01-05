
/*
ID: benchen1
LANG: JAVA
TASK: zerosum
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class zerosum {

	static int n;
	static ArrayList<String> sols = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		recur(1, 0, 1, new StringBuilder("1"), true);
		Collections.sort(sols);
		for (int i = 0; i < sols.size(); i++) {
			out.println(sols.get(i));
		}
		out.close();
		in.close();
	}

	public static void recur(int at, int sum, int left, StringBuilder soFar, boolean add) {
		if (at == n) {
			if (sum + left == 0)
				sols.add(soFar.substring(0));
			return;
		}
		recur(at + 1, sum + left, (at + 1), soFar.append("+" + (at + 1)), true);
		soFar.delete(soFar.length() - 2, soFar.length());
		recur(at + 1, sum + left, -(at + 1), soFar.append("-" + (at + 1)), false);
		soFar.delete(soFar.length() - 2, soFar.length());
		recur(at + 1, sum, left * 10 + (add ? (at + 1) : -(at + 1)), soFar.append(" " + (at + 1)), add);
		soFar.delete(soFar.length() - 2, soFar.length());
	}
}