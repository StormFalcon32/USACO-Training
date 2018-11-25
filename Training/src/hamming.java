/*
ID: benchen1
LANG: JAVA
TASK: hamming
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class hamming {
	
	static int numWords;
	static int numBits;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numWords = Integer.parseInt(ln.nextToken());
		numBits = Integer.parseInt(ln.nextToken());
		int distance = Integer.parseInt(ln.nextToken());
		int max = 0;
		for (int i = 0; i < numBits; i++) {
			max += 1 << i;
		}
		int numFound = 0;
		ArrayList<Integer> prevCodeWords = new ArrayList<Integer>();
		for (int i = 0; i <= max && numFound < numWords; i++) {
//			Check that the num is far enough
			boolean ok = true;
			for (int j = 0; j < prevCodeWords.size(); j++) {
				if (hammingDistance(i, prevCodeWords.get(j)) < distance) {
					ok = false;
				}
			}
			if (ok) {
				prevCodeWords.add(i);
				numFound++;
			}
		}
		int i = 0;
		for (i = 0; i < prevCodeWords.size() - 1; i++) {
			out.print(prevCodeWords.get(i));
			if ((i + 1) % 10 == 0) {
				out.println();
			} else {
				out.print(" ");
			}
		}
		out.println(prevCodeWords.get(i));
		out.close();
		in.close();
	}
	
	static int hammingDistance(int a, int b) {
		int dist = 0;
		for (int i = 0; i < numBits; i++) {
			if ((a & (1 << i)) != (b & (1 << i))) {
				dist++;
			}
		}
		return dist;
	}
}