/*
ID: benchen1
LANG: JAVA
TASK: camelot
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class camelot {
	static int R;
	static int C;
	static int N;
	static int[] dR = { -2, -1, 1, 2, -2, -1, 1, 2 };
	static int[] dC = { -1, -2, -2, -1, 1, 2, 2, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("camelot.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		R = Integer.parseInt(tk.nextToken());
		C = Integer.parseInt(tk.nextToken());
		Pos[] positions = new Pos[R * C + 1];
		N = 0;
		String line = in.readLine();
		while (line != null) {
			tk = new StringTokenizer(line);
			while (tk.hasMoreTokens()) {
				int c = tk.nextToken().toCharArray()[0] - 'A';
				int r = Integer.parseInt(tk.nextToken()) - 1;
				positions[N] = new Pos(r, c);
				N++;
			}
			line = in.readLine();
		}
		int[][][][] dists = new int[R][C][R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				for (int k = 0; k < R; k++) {
					for (int l = 0; l < C; l++) {
						dists[i][j][k][l] = 1 << 20;
					}
				}
				dists[i][j][i][j] = 0;
			}
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				LinkedList<Pos> q = new LinkedList<Pos>();
				boolean[][] visited = new boolean[R][C];
				q.add(new Pos(r, c));
				while (!q.isEmpty()) {
					Pos curr = q.poll();
					visited[curr.r][curr.c] = true;
					for (int j = 0; j < 8; j++) {
						int newR = curr.r + dR[j];
						int newC = curr.c + dC[j];
						if (inBounds(newR, newC) && !visited[newR][newC]) {
							q.add(new Pos(newR, newC));
							visited[newR][newC] = true;
							dists[r][c][newR][newC] = dists[r][c][curr.r][curr.c] + 1;
						}
					}
				}
			}
		}
		int best = Integer.MAX_VALUE;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				int baseMoves = 0;
				Pos king = positions[0];
				for (int i = 1; i < N; i++) {
					Pos curr = positions[i];
					baseMoves += dists[r][c][curr.r][curr.c];
				}
				for (int rk = Math.max(0, king.r - 3); rk < Math.min(R, king.r + 3); rk++) {
					for (int ck = Math.max(0, king.c - 3); ck < Math.min(C, king.c + 3); ck++) {
						int moves = 0;
						for (int i = 1; i < N; i++) {
							moves = baseMoves;
							Pos pickupKnight = positions[i];
							moves -= dists[r][c][pickupKnight.r][pickupKnight.c];
							moves += dists[rk][ck][pickupKnight.r][pickupKnight.c];
							moves += dists[r][c][rk][ck];
							moves += Math.max(Math.abs(king.r - rk), Math.abs(king.c - ck));
							best = Math.min(best, moves);
						}
						best = Math.min(best, moves);
					}
				}
			}
		}
		out.println(best);
		out.close();
		in.close();
	}
	
	static boolean inBounds(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
	
	static class Pos {
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public String toString() {
			return r + " " + c;
		}
	}
}