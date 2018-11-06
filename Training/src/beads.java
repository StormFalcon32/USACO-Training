/*
ID: benchen1
LANG: JAVA
TASK: beads
*/


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class beads {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		String temp = in.readLine();
		String[][] necklaces = new String[n][2];
//		necklaces[i][0] is if white was painted blue, necklaces[i][1] if white was painted red
		for (int i = 0; i < n; i++) {
			String sub = temp.substring(i, i + 1);
			if (sub.equals("w")) {
				necklaces[i][0] = "b";
				necklaces[i][1] = "r";
			} else {
				necklaces[i][0] = sub;
				necklaces[i][1] = sub;
			}
		}
		int best = 0;
		for (int i = 0; i < n; i++) {
			int[] consecutives = new int[2];
			
			for (int c = 0; c < 2; c++) {
				int j = i;
				boolean sameColor = true;
				String prev = necklaces[i][c];
				do {
					String curr = necklaces[j % n][c];
					if (!curr.equals(prev)) {
						sameColor = false;
					} else {
						consecutives[c]++;
					}
					j++;
					prev = curr;
				} while (sameColor && (j + 1) % n != i);
			}
			
			int consecutive = Math.max(consecutives[0], consecutives[1]);
			consecutives = new int[2];
			
			for (int c = 0; c < 2; c++) {
				int j = i - 1;
				String prev = necklaces[((i - 1) % n + n) % n][c];
				boolean sameColor = true;
				do {
					String curr = necklaces[(j % n + n) % n][c];
					if (!curr.equals(prev)) {
						sameColor = false;
					} else {
						consecutives[c]++;
					}
					j--;
					prev = curr;
				} while (sameColor && ((j - 1) % n + n) % n != i);
			}
			
			consecutive += Math.max(consecutives[0], consecutives[1]);
			best = Math.max(best, consecutive);
		}
		best = Math.min(n, best);
		out.println(best);
		in.close();
		out.close();		
	}

}
