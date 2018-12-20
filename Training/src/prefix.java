/*
ID: benchen1
LANG: JAVA
TASK: prefix
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class prefix {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		StringTokenizer ln;
		ArrayList<String> primitives = new ArrayList<String>();
		while (true) {
			String str = in.readLine();
			if (str.equals(".")) {
				break;
			}
			ln = new StringTokenizer(str);
			while (ln.hasMoreTokens()) {
				primitives.add(ln.nextToken());
			}
		}
		String s = in.readLine();
		System.out.println(s);
		out.close();
		in.close();
	}
}