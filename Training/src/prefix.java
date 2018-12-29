
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
import java.util.Arrays;
import java.util.StringTokenizer;

public class prefix {

	static String s;
	static ArrayList<String> primitives;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		StringBuilder seq = new StringBuilder("");
		primitives = new ArrayList<String>();
		while (true) {
			String str = in.readLine();
			if (str.equals(".")) {
				break;
			}
			StringTokenizer ln = new StringTokenizer(str);
			while (ln.hasMoreTokens()) {
				primitives.add(ln.nextToken());
				
			}
		}
		String str = in.readLine();
		while (str != null) {
			seq.append(str);
			str = in.readLine();
		}
		s = seq.toString();
		int[] dp = new int[s.length() + 1];
		Arrays.fill(dp, -1);
		dp[0] = 0;
		int biggest = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < primitives.size(); j++) {
				String prim = primitives.get(j);
				int l = prim.length();
				if (l <= i + 1) {
					String check = s.substring(i - l + 1, i + 1);
					if (check.equals(prim) && dp[i - l + 1] != -1) {
						dp[i + 1] = Math.max(dp[i + 1], l + dp[i - l + 1]);
					}
				}
			}
			biggest = Math.max(biggest, dp[i + 1]);
		}
		out.println(biggest);
		out.close();
		in.close();
	}
}