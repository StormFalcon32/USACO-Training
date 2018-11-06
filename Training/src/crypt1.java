
/*
ID: benchen1
LANG: JAVA
TASK: crypt1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class crypt1 {

	static int n;
	static TreeSet<Integer> combos1;
	static ArrayList<int[]> combos2;

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// FileReader("D:\\bench\\eclipse\\USACO\\Training\\crypt1\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		HashSet<Integer> useableDigits = new HashSet<Integer>();
		int[] digits = new int[n];
		ln = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			digits[i] = Integer.parseInt(ln.nextToken());
			useableDigits.add(digits[i]);
		}
		int numDigitsInLine = 3;
		int[] chosen = new int[numDigitsInLine + 1]; 
		combos1 = new TreeSet<Integer>();
		MakeAll(chosen, digits, 0, numDigitsInLine, 1);
		numDigitsInLine = 2;
		chosen = new int[numDigitsInLine + 1];
		combos2 = new ArrayList<int[]>();
		MakeAll(chosen, digits, 0, numDigitsInLine, 2);
//		Loop through every possible combination of products
		int numSolutions = 0;
		while (!combos1.isEmpty()) {
			int top = combos1.pollFirst();
			for (int i = 0; i < combos2.size(); i++) {
				int partProd1 = top * combos2.get(i)[0];
				int partProd2 = top * combos2.get(i)[1];
				if ((int) (partProd1 / 100) > 0 && (int) (partProd1 / 100) < 10 && (int) (partProd2 / 100) > 0 && (int) (partProd2 / 100) < 10) {
					LinkedList<Integer> part1Digits = calcDigits(partProd1, 10);
					LinkedList<Integer> part2Digits = calcDigits(partProd2, 10);
					boolean ok = true;
					for (int j = 0; j < part1Digits.size(); j++) {
						if (!useableDigits.contains(part1Digits.get(j))) {
							ok = false;
							break;
						}
					}
					if (ok) {
						for (int j = 0; j < part2Digits.size(); j++) {
							if (!useableDigits.contains(part2Digits.get(j))) {
								ok = false;
								break;
							}
						}
					}
					int bottom = 10 * combos2.get(i)[0] + combos2.get(i)[1];
					int prod = top * bottom;
					if (ok) {
						LinkedList<Integer> prodDigits = calcDigits(prod, 10);
						for (int j = 0; j < prodDigits.size(); j++) {
							if (!useableDigits.contains(prodDigits.get(j))) {
								ok = false;
								break;
							}
						}
					}
					if (ok) {
						numSolutions++;
					}
				}
			}
		}
		out.println(numSolutions);
		out.close();
		in.close();
	}
	
	static LinkedList<Integer> calcDigits(int num, int base) {
		LinkedList<Integer> digits = new LinkedList<Integer>();
		while (num > 0) {
			digits.add(num % base);
			num /= base;
		}
		return digits;
	}

	static void MakeAll(int[] chosen, int[] arr, int currLength, int length,  int combo) {
//		Repeated digits allowed, order matters, all combinations
		if (currLength == length) {
			if (combo == 1) {
				int num = 0;
				for (int i = 0; i < length; i++) {
					num += arr[chosen[i]] * Math.pow(10, length - i - 1);
				}
				combos1.add(num);
			} else if (combo == 2) {
				combos2.add(new int[length]);
				int[] temp = combos2.get(combos2.size() - 1);
				for (int i = 0; i < length; i++) {
					temp[i] = arr[chosen[i]];
				}
			}
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			chosen[currLength] = i;
			MakeAll(chosen, arr, currLength + 1, length, combo);
		}
		return;
	}
}