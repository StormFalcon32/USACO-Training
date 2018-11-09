/*
ID: benchen1
LANG: JAVA
TASK: numtri
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class numtri {
	
	static int ROWS;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		ROWS = Integer.parseInt(ln.nextToken());
		for (int i = 0; i < ROWS; i++) {
			
		}
		out.close();
		in.close();
	}
}