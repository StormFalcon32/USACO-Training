/*
ID: benchen1
LANG: JAVA
TASK: namenum
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class namenum {
	
	static int n;

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// FileReader("D:\\bench\\eclipse\\USACO\\Training\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		String str = in.readLine();
		int[] serialNum = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			serialNum[i] = Integer.parseInt(str.substring(i, i + 1));
		}
		n = serialNum.length;
		in.close();
		in = new BufferedReader(new FileReader("dict.txt"));
		String line = in.readLine();
		ArrayList<String> dict = new ArrayList<String>();
		while (line != null) {
			dict.add(line);
			line = in.readLine();
		}
		String[][] numToLetters = { { "A", "B", "C" }, { "D", "E", "F" }, { "G", "H", "I" }, { "J", "K", "L" },
				{ "M", "N", "O" }, { "P", "R", "S" }, { "T", "U", "V" }, { "W", "X", "Y" } };
		@SuppressWarnings("unchecked")
		ArrayList<String>[] possibleLetters = new ArrayList[n];
		// Loop through each serial number
		for (int num = 0; num < n; num++) {
			// Loop through each possible letter for each digit
			int currDigit = serialNum[num];
			possibleLetters[num] = new ArrayList<String>();
			for (int let = 0; let < 3; let++) {
				possibleLetters[num].add(numToLetters[currDigit - 2][let]);
			}
		}
		ArrayList<String> viableName = new ArrayList<String>();
		for (int i = 0; i < dict.size(); i++) {
			String currWord = dict.get(i);
			boolean found = true;
			if (currWord.length() == n) {
				for (int j = 0; j < currWord.length(); j++) {
					String currLetter = currWord.substring(j, j + 1);
					if (!possibleLetters[j].contains(currLetter)) {
						found = false;
					}
				}
			} else {
				found = false;
			}
			if (found) {
				viableName.add(currWord);
			}
		}
		Collections.sort(viableName);
		for (int i = 0; i < viableName.size(); i++) {
			out.println(viableName.get(i));
		}
		if (viableName.isEmpty()) {
			out.println("NONE");
		}
		out.close();
		in.close();
	}
}
