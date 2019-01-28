/*
ID: benchen1
LANG: JAVA
TASK: agrinet
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class agrinet {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("agrinet.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(ln.nextToken());
		int[][] adjMat = new int[N][N];
		for (int i = 0; i < N; i++) {
			ln = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				adjMat[i][j] = Integer.parseInt(ln.nextToken());
			}
		}
		
		out.close();
		in.close();
	}
}