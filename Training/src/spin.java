/*
ID: benchen1
LANG: JAVA
TASK: spin
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class spin {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int S = Integer.parseInt(tk.nextToken());
		for (int i = 0; i < S; i++) {
			
		}
		out.close();
		in.close();
	}
}