/*
ID: benchen1
LANG: JAVA
TASK: concom
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class concom {
	
	static int n;
	static int[][] shares = new int[101][101]; // shares[a][b] is how much shares a has in b
	static boolean[][] control = new boolean[101][101]; // control[a][b] is true if a owns b

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("concom.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		int biggest = 0;
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken());
			int b = Integer.parseInt(ln.nextToken());
			int percent = Integer.parseInt(ln.nextToken());
			shares[a][b] = percent;
			if (a != b && percent > 50) {
				control[a][b] = true;
			}
			biggest = Math.max(biggest, Math.max(a, b));
		}
		biggest++;
		boolean change = true;
		while (change) {
			change = false;
			for (int i = 1; i < biggest; i++) {
				for (int j = 1; j < biggest; j++) {
					if (control[i][j]) {
						continue;
					}
					if (shares[i][j] > 50) {
						change = true;
						control[i][j] = true;
						continue;
					}
					int sum = shares[i][j];
					for (int k = 1; k < biggest; k++) {
						
						if (control[i][k]) {
							sum += shares[k][j];
						}
						if (sum > 50) {
							change = true;
							control[i][j] = true;
							shares[i][j] += sum;
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < biggest; i++) {
			for (int j = 0; j < biggest; j++) {
				if (i != j && control[i][j]) {
					out.println(i + " " + j);
				}
			}
		}
		out.close();
		in.close();
	}
}