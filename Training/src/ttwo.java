
/*
ID: benchen1
LANG: JAVA
TASK: ttwo
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ttwo {

	static int n = 10;

	static State farmer;
	static State cow;
	static boolean[][] grid;

	static int[] dirR = { -1, 0, 1, 0 };
	static int[] dirC = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("ttwo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));

		// True means obstacle
		grid = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			String ln = in.readLine();
			for (int j = 0; j < n; j++) {
				String sub = ln.substring(j, j + 1);
				switch (sub) {
				case "*":
					grid[i][j] = true;
					break;
				case "F":
					farmer = new State(i, j, 0);
					break;
				case "C":
					cow = new State(i, j, 0);
					break;
				}
			}
		}
		int t;
		for (t = 0; t < 401; t++) {
			if (move() == 2) {
				break;
			}
		}
		out.println((t == 401) ? 0 : t + 1);
		out.close();
		in.close();
	}

	static boolean inBounds(int r, int c) {
		if (r < n && r >= 0 && c < n && c >= 0) {
			return true;
		}
		return false;
	}

	static int move() {

		int fDir = farmer.dir;

		int newR = farmer.r + dirR[fDir];
		int newC = farmer.c + dirC[fDir];

		if (inBounds(newR, newC) && !grid[newR][newC]) {
			// Keep going straight
			farmer.r = newR;
			farmer.c = newC;
		} else {
			// Turn
			farmer.dir = (farmer.dir + 1) % 4;
		}

		int cDir = cow.dir;
		
		newR = cow.r + dirR[cDir];
		newC = cow.c + dirC[cDir];

		if (inBounds(newR, newC) && !grid[newR][newC]) {
			// Keep going straight
			cow.r = newR;
			cow.c = newC;
		} else {
			// Turn
			cow.dir = (cow.dir + 1) % 4;
		}
		if (cow.r == farmer.r && cow.c == farmer.c) {
			return 2;
		}
		return 1;
	}

	static class State {
		int r;
		int c;
		int dir;

		public State(int a, int b, int d) {
			r = a;
			c = b;
			dir = d;
		}
	}
}