/*
ID: benchen1
LANG: JAVA
TASK: milk3
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class milk3 {
	
	static int n;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\bench\\git\\USACO-Training\\Training\\milk3\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		for (int i = 0; i < n; i++) {
			
		}
		out.close();
		in.close();
	}
}