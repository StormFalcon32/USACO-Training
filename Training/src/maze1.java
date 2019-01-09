
/*
ID: benchen1
LANG: JAVA
TASK: maze1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class maze1 {

	static int R;
	static int C;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("maze1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		C = Integer.parseInt(ln.nextToken());
		R = Integer.parseInt(ln.nextToken());
		String[][] input = new String[2 * R + 1][2 * C + 1];
		int[][] dp = new int[R * C][R * C];
		int[] exits = new int[2];
		
		int ext = 0;
		
		for (int i = 0; i < R * C; i++) {
			for (int j = 0; j < R * C; j++) {
				dp[i][j] = 1 << 20;
			}
		}

		for (int i = 0; i < 2 * R + 1; i++) {
			String str = in.readLine();
			for (int j = 0; j < 2 * C + 1; j++) {
				input[i][j] = str.substring(j, j + 1);
			}
		}
		
		for (int i = 0; i < 2 * R + 1; i++) {
			int newR = i;
			int newC = -1;
			if (input[i][0].equals(" ")) {
				newC = 1;
				exits[ext] = findVNum(newR, newC);
				ext = 1;
			} else if (input[i][2 * C].equals(" ")) {
				newC = 2 * C - 1;
				exits[ext] = findVNum(newR, newC);
				ext = 1;
			}
		}
		
		for (int j = 0; j < 2 * C + 1; j++) {
			int newR = -1;
			int newC = j;
			if (input[0][j].equals(" ")) {
				newR = 1;
				exits[ext] = findVNum(newR, newC);
				ext = 1;
			} else if (input[2 * R][j].equals(" ")) {
				newR = 2 * R - 1;
				exits[ext] = findVNum(newR, newC);
				ext = 1;
			}
		}
		
		
		for (int i = 1; i < 2 * R; i++) {
			if (i == 0 || i == 2 * R) {
				continue;
			}
			for (int j = 1; j < 2 * C; j++) {

				String curr = input[i][j];
				if (i == 6) {
					System.out.println();
				}
				
				if (j == 0 || j == 2 * C) {
					continue;
				}
				
				if (i % 2 == 0) {
					// Horizontal fence
					if (curr.equals(" ")) {
						int a = findVNum(i - 1, j);
						int b = findVNum(i + 1, j);
						dp[a][b] = 1;
						dp[b][a] = 1;
					}

				} else if (j % 2 == 0) {
					// Vertical fence
					if (curr.equals(" ")) {
						int a = findVNum(i, j - 1);
						int b = findVNum(i, j + 1);
						dp[a][b] = 1;
						dp[b][a] = 1;
					}
				}
			}
		}
		
		out.close();
		in.close();
	}
	
	static int findVNum(int r, int c) {
		int newR = (r - 1) / 2;
		int newC = (c - 1) / 2;
		return newR * C + newC;
	}
}