
/*
ID: benchen1
LANG: JAVA
TASK: zerosum
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class zerosum {

	static int n = 7;
	static ArrayList<String> sols = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		calc(1, new StringBuilder("1"));
		Collections.sort(sols);
		for (int i = 0; i < sols.size(); i++) {
			out.println(sols.get(i));
		}
		out.close();
		in.close();
	}

	static boolean check(String s) {
		int sum = 0;
		boolean positive = true;
	    s = s.replaceAll("\\s","");
	    for (int i = 0; i < s.length(); i++) {
	    	String curr = s.substring(i, i + 1);
	    	switch (curr) {
	    	case "+":
	    		positive = true;
	    		break;
	    	case "-":
	    		positive = false;
	    		break;
	    	default:
	    		int num = 0;
	    		while (i < s.length()) {
	    			curr = s.substring(i, i + 1);
	    			if (curr.equals("+") || curr.equals("-")) {
	    				break;
	    			}
	    			num = num * 10 + Integer.parseInt(curr);
	    			i++;
	    		}
	    		if (positive) {
	    			sum += num;
	    		} else {
	    			sum -= num;
	    		}
	    		i--;
	    		break;
	    	}
	    }
		if (sum == 0) {
			return true;
		}
		return false;
	}
	
	static StringBuilder undo(StringBuilder s) {
		return s.delete(s.length() - 2, s.length());
	}
	
	static void calc(int l, StringBuilder s) {
		if (l == n) {
			if (check(s.toString())) {
				sols.add(s.toString());
			}
			return;
		}
		// Try + then undo
		calc(l + 1, s.append("+" + (l + 1)));
		undo(s);
		// Try - then undo
		calc(l + 1, s.append("-" + (l + 1)));
		undo(s);
		// Try blank then undo
		calc(l + 1, s.append(" " + (l + 1)));
		undo(s);
	}
}