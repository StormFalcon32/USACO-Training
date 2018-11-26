
/*
ID: benchen1
LANG: JAVA
TASK: preface
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class preface {
	
	static String[] romanCharacters = { "M", "CM", "D", "C", "XC", "L", "X", "IX", "V", "I" };

	static int maxNum;
	static int maxUsed = Integer.MAX_VALUE;
	static int[] numOccurences = new int[10];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		maxNum = Integer.parseInt(ln.nextToken());
		for (int i = 1; i <= maxNum; i++) {
			toRoman(i);
		}
//		Turn IX into I and X
		numOccurences[9] += numOccurences[7];
		numOccurences[6] += numOccurences[7];
//		Turn XC into X and C
		numOccurences[6] += numOccurences[4];
		numOccurences[3] += numOccurences[4];
//		Turn CM into C and M
		numOccurences[3] += numOccurences[1];
		numOccurences[0] += numOccurences[1];
		for (int i = 9; i >= maxUsed; i--) {
			if (i == 7) {
				continue;
			} else if (i == 4) {
				continue;
			} else if (i == 1) {
				continue;
			}
			out.println(romanCharacters[i] + " " + numOccurences[i]);
		}
		out.close();
		in.close();
	}

	private static void toRoman(int num) {
		int[] romanValues = { 1000, 900, 500, 100, 90, 50, 10, 9, 5, 1 };
		for (int i = 0; i < romanValues.length; i++) {
			int numberInPlace = num / romanValues[i];
			if (numberInPlace == 0) {
				continue;
			}
			if (numberInPlace == 4 && i > 0) {
				numOccurences[i]++;
				numOccurences[i - 1]++;
				maxUsed = Math.min(maxUsed, i - 1);
			} else {
				numOccurences[i] += numberInPlace;
				maxUsed = Math.min(maxUsed, i);
			}
			num = num % romanValues[i];
		}
	}
}