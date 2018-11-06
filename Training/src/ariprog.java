
/*
ID: benchen1
LANG: JAVA
TASK: ariprog
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeSet;

public class ariprog {

	static int n;
	static int m;
	static TreeSet<AriSeq> outputs = new TreeSet<AriSeq>();

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// FileReader("D:\\bench\\eclipse\\Workspace\\Training\\ariprog\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		n = Integer.parseInt(in.readLine());
		m = Integer.parseInt(in.readLine());
		boolean[] bisquares = new boolean[m * m * 2 + 10];
		int max = 0;
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= m; j++) {
//				bisquares[a] = true where a is a bisquare
				if (bisquares[i * i + j * j]) {
					continue;
				}
				bisquares[i * i + j * j] = true;
				max = Math.max(max, i * i + j * j);
			}
		}
		for (int start = 0; start < bisquares.length; start++) {
//			For every bisquare
			if (!bisquares[start]) {
				continue;
			}
//			Try every possible interval
			for (int interval = 1; interval <= (max - start) / (n - 1); interval++) {
				int check = start;
				int numIntervals = 0;
//				Loop through and check every potential value of the progression given the starting position and interval
				boolean found = false;
				while (numIntervals < n) {
					if (check >= bisquares.length || !bisquares[check]) {
						found = false;
						break;
					} else {
						found = true;
					}
					check += interval;
					numIntervals++;
				}
				if (found)
					outputs.add(new AriSeq(start, interval));
				}
		}
		if (outputs.isEmpty()) {
			out.println("NONE");
		}
		while (!outputs.isEmpty()) {
			AriSeq curr = outputs.pollFirst();
			out.println(curr.start + " " + curr.diff);
		}
		out.close();
		out.close();
		in.close();
	}

	static class AriSeq implements Comparable<AriSeq> {
		int start;
		int diff;

		public AriSeq(int a, int b) {
			start = a;
			diff = b;
		}

		@Override
		public int compareTo(AriSeq other) {
			if (this.diff == other.diff) {
				return Integer.compare(this.start, other.start);
			}
			return Integer.compare(this.diff, other.diff);
		}
	}
}