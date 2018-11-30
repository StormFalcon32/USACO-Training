/*
ID: benchen1
LANG: JAVA
TASK: lamps
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class lamps {
	
	static int numLamps;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numLamps = Integer.parseInt(ln.nextToken());
		for (int i = 0; i < numLamps; i++) {
			
		}
		out.close();
		in.close();
	}
}