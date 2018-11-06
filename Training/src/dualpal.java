/*
ID: benchen1
LANG: JAVA
TASK: dualpal
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class dualpal {
	
	static int start;
	static int numPals;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Training\\dualpal\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numPals = Integer.parseInt(ln.nextToken());
		start = Integer.parseInt(ln.nextToken());
		int[] pals = new int[numPals];
		int currNum = start + 1;
		int numFound = 0;
		while (numFound < numPals) {
			int numBases = 0;
			for (int i = 2; i <= 10; i++) {
				LinkedList<Integer> changed = changeBase(currNum, i);
				if (isPalindrome(changed)) {
					numBases++;
				}
				if (numBases >= 2) {
					pals[numFound] = currNum;
					numFound++;
					break;
				}
			}
			currNum++;
		}
		for (int i = 0; i < numPals; i++) {
			out.println(pals[i]);
		}
		out.close();
		in.close();
	}
	
	static LinkedList<Integer> changeBase(int num, int base) {
		LinkedList<Integer> newDigits = calcDigits(num, base);
		return newDigits;
	}
	
	static boolean isPalindrome(LinkedList<Integer> digits) {
		int numDigits = digits.size();
		for (int i = 0; i < numDigits / 2; i++) {
			if (digits.get(i) != digits.get(numDigits - i - 1)) {
				return false;
			}
		}
		return true;
	}
	
	static LinkedList<Integer> calcDigits(int num, int base) {
		LinkedList<Integer> digits = new LinkedList<Integer>();
		while (num > 0) {
			digits.add(num % base);
			num /= base;
		}
		return digits;
	}
}