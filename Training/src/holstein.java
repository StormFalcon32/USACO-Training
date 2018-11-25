/*
ID: benchen1
LANG: JAVA
TASK: holstein
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class holstein {
	
	static int numVitamins;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numVitamins = Integer.parseInt(ln.nextToken());
		ln = new StringTokenizer(in.readLine());
		int[] requiredVitamins = new int[numVitamins];
		for (int i = 0; i < numVitamins; i++) {
			requiredVitamins[i] = Integer.parseInt(ln.nextToken());
		}
		int numFeeds = Integer.parseInt(in.readLine());
		Feed[] feeds = new Feed[numFeeds];
		for (int i = 0; i < numFeeds; i++) {
			ln = new StringTokenizer(in.readLine());
			int[] temp = new int[numVitamins];
			for (int j = 0; j < numVitamins; j++) {
				temp[j] = Integer.parseInt(ln.nextToken());
			}
			feeds[i] = new Feed(temp);
		}
		int max = 0;
		for (int i = 0; i < numFeeds; i++) {
			max += 1 << i;
		}
		int best = Integer.MAX_VALUE;
		boolean[] used = new boolean[numFeeds];
		for (int i = 1; i <= max; i++) {
//			Using binary digits to represent which feeds are used, we can iterate through all possible combinations
//			For every combination, check if it's more efficient than the previously most efficient solution
			int numUsed = 0;
			int[] total = new int[numVitamins];
			boolean[] chosen = new boolean[numFeeds];
			boolean done = true;
			for (int j = 0; j < numFeeds; j++) {
//				Turn int back into boolean array
				done = true;
				if ((i & (1 << j)) == 1 << j) {
					numUsed++;
					chosen[j] = true;
					Feed curr = feeds[j];
					for (int k = 0; k < numVitamins; k++) {
						total[k] += curr.numVitamins[k];
					}
					for (int k = 0; k < numVitamins; k++) {
						if (total[k] < requiredVitamins[k]) {
							done = false;
							break;
						}
					}
					if (done) {
						used = (numUsed < best) ? chosen : used;
						best = Math.min(best, numUsed);
						break;
					}
				}	
			}
		}
		out.print(best);
		for (int i = 0; i < numFeeds; i++) {
			if (used[i]) {
				out.print(" " + (i + 1));
			}
		}
		out.println();
		out.close();
		in.close();
	}
	
	static class Feed {
		int[] numVitamins;
		
		public Feed(int[] a) {
			numVitamins = a;
		}
	}
}