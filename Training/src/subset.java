/*
ID: benchen1
LANG: JAVA
TASK: subset
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class subset {
	
	static int numInts;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numInts = Integer.parseInt(ln.nextToken());
		
		out.close();
		in.close();
	}
}