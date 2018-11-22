/*
ID: benchen1
LANG: JAVA
TASK: frac1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class frac1 {
	
	static int maxDenom;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		maxDenom = Integer.parseInt(ln.nextToken());
		TreeSet<Fraction> fracs = new TreeSet<Fraction>();
		for (int i = 1; i <= maxDenom; i++) {
			for (int j = 0; j <= i; j++) {
				Fraction f = new Fraction(j, i);
				fracs.add(f);
			}
		}
		while (!fracs.isEmpty()) {
			Fraction f = fracs.pollFirst();
			out.println(f.num + "/" + f.denom);
		}
		out.close();
		in.close();
	}
	
	static class Fraction implements Comparable<Fraction> {
		int num;
		int denom;
		double val;
		
		public Fraction(int a, int b) {
			num = a;
			denom = b;
			val = (double) num / denom;
		}
		
		@Override
		public boolean equals(Object o) {
			Fraction other = (Fraction) o;
			if (this.val == other.val) {
				return true;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return (int) val * 10000000;
		}

		@Override
		public int compareTo(Fraction other) {
			return Double.compare(this.val, other.val);
		}
		
	}
}