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
import java.util.StringTokenizer;

public class castle {
	
	static int COLS;
	static int ROWS;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		COLS = Integer.parseInt(ln.nextToken());
		ROWS = Integer.parseInt(ln.nextToken());
		for (int r = 0; r < ROWS; r++) {
			ln = new StringTokenizer(in.readLine());
			for (int c = 0; c < COLS; c++) {
				
			}
		}
		out.close();
		in.close();
	}
	
	static void walls(int x) {
		
	}
}