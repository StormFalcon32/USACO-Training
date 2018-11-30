/*
ID: benchen1
LANG: JAVA
TASK: runround
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class runround {
	
	static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		m = Integer.parseInt(ln.nextToken());
		for (int i = 0; i < m; i++) {
			
		}
		out.close();
		in.close();
	}
}