
/*
ID: benchen1
LANG: JAVA
TASK: wormhole
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class wormhole {

	static int n;
	static Wormhole[] wormholes;
	static int[] pairs;
	static int[] next;

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// FileReader("D:\\bench\\eclipse\\Workspace\\Training\\wormhole\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("wormhole.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		wormholes = new Wormhole[n + 1];
		pairs = new int[n + 1];
		next = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			ln = new StringTokenizer(in.readLine());
			wormholes[i] = new Wormhole(Integer.parseInt(ln.nextToken()), Integer.parseInt(ln.nextToken()));
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (wormholes[j].x > wormholes[i].x && wormholes[i].y == wormholes[j].y) {
					if (next[i] == 0 || wormholes[j].x - wormholes[i].x < wormholes[next[i]].x - wormholes[i].x) {
						next[i] = j;
					}
				}
			}
		}
		out.println(solve());
		out.close();
		in.close();
	}

	static int solve() {
		int i = 0;
		int total = 0;
		for (i = 1; i <= n; i++) {
			if (pairs[i] == 0) {
				break;
			}
		}
		if (i > n) {
			if (isLoop()) {
				return 1;
			} else {
				return 0;
			}
		}
		for (int j = i + 1; j <= n; j++) {
			if (pairs[j] == 0) {
				pairs[i] = j;
				pairs[j] = i;
				total += solve();
				pairs[i] = 0;
				pairs[j] = 0;
			}
		}
		return total;
	}

	static boolean isLoop() {
		for (int start = 1; start <= n; start++) {
			int pos = start;
			for (int count = 0; count < n; count++) {
				pos = next[pairs[pos]];
			}
			if (pos != 0) {
				return true;
			}
		}
		return false;
	}
	
	static class Wormhole {
		int x;
		int y;
		
		public Wormhole(int a, int b) {
			x = a;
			y = b;
		}
	}
}