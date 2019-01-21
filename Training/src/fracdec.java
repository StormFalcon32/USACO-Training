/*
ID: benchen1
LANG: JAVA
TASK: fracdec
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class fracdec {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fracdec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(ln.nextToken());
		int D = Integer.parseInt(ln.nextToken());
		StringBuilder ret = new StringBuilder("");
		int remainder = 0;
		int quotient = 0;
		int decimalInd = 1;
		if (N >= D) {
			do {
			quotient = N / D;
			ret.append(quotient);
			remainder = N - quotient * D;
			N = remainder * 10;
			decimalInd = ret.length();
			} while (N / 10 > D);
		} else {
			N *= 10;
			ret.append("0");
		}
		ret.append(".");
		if (N == 0) {
			ret.append("0");
		}
		// Previous.get(i) is the quotient when N = i
		HashMap<Integer, Integer> previous = new HashMap<Integer, Integer>();
		while (true) {
			if (N == 0) {
				break;
			}
			if (previous.containsKey(N)) {
				String sub = ret.substring(decimalInd);
				int ind = sub.indexOf(Integer.toString(previous.get(N))) + decimalInd;
				ret.insert(ind, "(");
				ret.append(")");
				break;
			}
			quotient = N / D;
			ret.append(quotient);
			remainder = N - quotient * D;
			previous.put(N, quotient);
			N = remainder * 10;
		}
		int i = 0;
		while (i < ret.length()) {
			out.print(ret.substring(i, i + 1));
			i++;
			if (i % 76 == 0) {
				out.println();
			}
		}
		out.println();
		out.close();
		in.close();
	}
}