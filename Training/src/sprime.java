
/*
ID: benchen1
LANG: JAVA
TASK: sprime
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

public class sprime {

	static int numDigits;
	static ArrayList<Integer> startingPrimes;
	static int[] validDigits = {1, 3, 7, 9};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numDigits = Integer.parseInt(ln.nextToken());
		startingPrimes = new ArrayList<Integer>(Arrays.asList(2, 3, 5, 7));
		nextDigit(0, numDigits);
		for (int i = 0; i < startingPrimes.size(); i++) {
			out.println(startingPrimes.get(i));
		}
		out.close();
		in.close();
	}
	
	static void nextDigit(int currLength, int length) {
		if (currLength == length - 1) {
			return;
		}
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < startingPrimes.size(); i++) {
			for (int j = 0; j < 4; j++) {
				int newNum = (startingPrimes.get(i) * 10) + validDigits[j];
				if (isPrime(newNum)) {
					temp.add(newNum);
				}
			}
		}
		startingPrimes = temp;
		nextDigit(currLength + 1, length);
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
}