
/*
ID: benchen1
LANG: JAVA
TASK: castle
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class castle {

	static int COLS;
	static int ROWS;
	static int numVerts;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		COLS = Integer.parseInt(ln.nextToken());
		ROWS = Integer.parseInt(ln.nextToken());
		numVerts = ROWS * COLS;
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[][] adjMat = new LinkedList[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				adjMat[r][c] = new LinkedList<Integer>();
				
			}
		}
		for (int r = 0; r < ROWS; r++) {
			ln = new StringTokenizer(in.readLine());
			for (int c = 0; c < COLS; c++) {
				int x = Integer.parseInt(ln.nextToken());
				int[] dirR = {0, -1, 0, 1};
				int[] dirC = {-1, 0, 1, 0};
				// 0, 1, 2, 3 is west, north, east, south
				for (int i = 0; i < 4; i++) {
					if ((x & (1 << i)) == 1 << i) {
						int newR = r + dirR[i];
						int newC = c + dirC[i];
						if (inRange(newR, newC)) {
							
						}
					}
				}
			}
		}
		out.close();
		in.close();
	}
	
	static boolean inRange(int r, int c) {
		if (r < ROWS && r >= 0 && c < COLS && c >= 0) {
			return true;
		}
		return false;
	}
}