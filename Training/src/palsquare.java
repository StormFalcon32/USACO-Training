/*
ID: benchen1
LANG: JAVA
TASK: palsquare
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class palsquare {

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Training\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int base = Integer.parseInt(ln.nextToken());
		for (int i = 1; i <= 300; i++) {
			int square = i * i;
			ArrayList<Integer> changed = changeBase(square, base);
			ArrayList<Integer> changedOrig = changeBase(i, base);
			if (isPalindrome(changed)) {
				out.println(digitsToNum(changedOrig) + " " + digitsToNum(changed));
			}
		}
		out.close();
		in.close();
	}
	
	static ArrayList<Integer> changeBase(int num, int base) {
		ArrayList<Integer> newDigits = calcDigits(num, base);
		return newDigits;
	}
	
	static boolean isPalindrome(ArrayList<Integer> digits) {
		int numDigits = digits.size();
		for (int i = 0; i < numDigits / 2; i++) {
			if (digits.get(i) != digits.get(numDigits - i - 1)) {
				return false;
			}
		}
		return true;
	}
	
	static ArrayList<Integer> calcDigits(int num, int base) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		while (num > 0) {
			digits.add(num % base);
			num /= base;
		}
		return digits;
	}
	
	static String digitsToNum(ArrayList<Integer> digits) {
		StringBuilder ret = new StringBuilder("");
		char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		for (int i = digits.size() - 1; i >= 0; i--) {
			int curr = digits.get(i);
			if (curr > 9) {
				ret.append(alphabet[curr - 10]);
			} else {
				ret.append(curr);
			}
		}
		return ret.toString();
	}
}