
/*
ID: benchen1
LANG: JAVA
TASK: pprime
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pprime {

	static int a;
	static int b;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		a = Integer.parseInt(ln.nextToken());
		b = Integer.parseInt(ln.nextToken());
		// Generate all primes less than b
		// Generate all palindromes
		int numDigitsA = calcNumDigits(a);
		int numDigitsB = calcNumDigits(b);
		for (int numDigs = numDigitsA; numDigs <= numDigitsB; numDigs++) {
			int[] chosen = new int[numDigs];
			if (numDigs % 2 == 0) {
				makeNextDigit(numDigs / 2, 0, chosen, a, b, true, out);
			} else {
				makeNextDigit((numDigs + 1) / 2, 0, chosen, a, b, false, out);
			}
		}
		out.close();
		in.close();
	}
	
	static void makeNextDigit(int length, int currLength, int[] chosen, int min, int max, boolean even, PrintWriter out) {
		if (currLength == length) {
			int num = 0;
			if (even) {
				for (int i = 0; i < length; i++) {
					num += chosen[i] * Math.pow(10, length - i - 1 + length);
				}
				for (int i = 0; i < length; i++) {
					num += chosen[i] * Math.pow(10, i);
				}
			} else {
				for (int i = 0; i < length; i++) {
					num += chosen[i] * Math.pow(10, length - i - 2 + length);
				}
				for (int i = 0; i < length - 1; i++) {
					num += chosen[i] * Math.pow(10, i);
				}
			}
			if (num >= min && num <= max && isPrime(num)) {
				out.println(num);
			}
			return;
		}
		for (int i = (currLength == 0) ? 1 : 0; i < 10; i++) {
			chosen[currLength] = i;
			makeNextDigit(length, currLength + 1, chosen, min, max, even, out);
		}
	}
	
	static boolean isPrime(int num) {
		if (num % 2 == 0) {
			return false;
		}
		int upperLim = (int) Math.sqrt(num);
		for (int i = 3; i <= upperLim; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	static int calcNumDigits(int num) {
		int ret = 0;
		while (num > 0) {
			ret++;
			num /= 10;
		}
		return ret;
	}
}