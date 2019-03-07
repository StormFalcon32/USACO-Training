
/*
ID: benchen1
LANG: JAVA
TASK: fact4
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class fact4 {
	
	static final BigInteger ten = new BigInteger("10");
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fact4.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tk.nextToken());
		BigInteger[] debug = new BigInteger[N + 1];
		debug[0] = new BigInteger("1");
		for (int i = 1; i < N + 1; i++) {
			debug[i] = debug[i - 1].multiply(new BigInteger((new Integer(i)).toString()));
		}
		while (debug[N].mod(ten).equals(new BigInteger("0"))) {
			debug[N] = debug[N].divide(ten);
		}
		debug[N] = debug[N].mod(ten);
		out.println(debug[N]);
		out.close();
		in.close();
	}
}