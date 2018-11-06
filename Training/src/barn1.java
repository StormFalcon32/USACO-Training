/*
ID: benchen1
LANG: JAVA
TASK: barn1
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

public class barn1 {
	
	static int numBoards;
	static int numStalls;
	static int numCows;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Training\\barn1\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numBoards = Integer.parseInt(ln.nextToken());
		numStalls = Integer.parseInt(ln.nextToken());
		numCows = Integer.parseInt(ln.nextToken());
		boolean[] stalls = new boolean[numStalls];
		int start = 0;
		int end = 0;
		for (int i = 0; i < numCows; i++) {
			stalls[Integer.parseInt(in.readLine()) - 1] = true;
		}
		for (int i = 0; i < numStalls; i++) {
			if (stalls[i]) {
				start = i;
				break;
			}
		}
		for (int i = numStalls - 1; i >= 0; i--) {
			if (stalls[i]) {
				end = i;
				break;
			}
		}
		ArrayList<Gap> gaps = new ArrayList<Gap>();
		int gapStart = 0;
		boolean isGap = false;
		for (int i = start; i <= end; i++) {
			if (!stalls[i]) {
				if (!isGap) {
					gapStart = i;
					isGap = true;
				}
			} else {
				if (isGap) {
					isGap = false;
					gaps.add(new Gap(gapStart, i));
				}
			}
		}
		Collections.sort(gaps);
		int currentLength = end - start + 1;
		int currentNumBoards = 1;
		while (currentNumBoards < numBoards && !gaps.isEmpty()) {
			currentNumBoards++;
			int biggestGap = gaps.remove(0).length;
			currentLength -= biggestGap;
		}
		out.println(currentLength);
		out.close();
		in.close();
	}
	
	static class Gap implements Comparable<Gap> {
		int start;
		int end;
		int length;
		
		public Gap(int a, int b) {
			start = a;
			end = b;
			length = b - a;
		}
		
		public int compareTo(Gap other) {
			return Integer.compare(other.length, this.length);
		}
	}
}