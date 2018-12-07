
/*
ID: benchen1
LANG: JAVA
TASK: runround
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class runround {

	static int m;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		m = Integer.parseInt(ln.nextToken());
		ArrayList<Integer> num = calcDigits(m, 10);
		increment(num);
		while (true) {
			HashSet<Integer> prevs = new HashSet<Integer>();
			int currDigit = num.get(0);
			int origDigit = currDigit;
			int currIndex = 0;
			int times = 0;
			while (true) {
				currIndex = (currIndex + currDigit) % num.size();
				currDigit = num.get(currIndex);
				if (currDigit == origDigit) {
					if (times == num.size() - 1) {
						for (int i = 0; i < num.size(); i++) {
							out.print(num.get(i));
						}
						out.println();
						out.close();
						in.close();
						return;
					} else {
						break;
					}
				} else if (prevs.contains(currDigit)) {
					break;
				}
				prevs.add(currDigit);
				times++;
			}
			increment(num);
		}
	}
	
	static ArrayList<Integer> increment(ArrayList<Integer> num) {
		for (int i = num.size() - 1; i >= 0; i--) {
			num.set(i, num.get(i) + 1);
			if (num.get(i) == 10) {
				num.set(i, 0);
				if (i == 0) {
					num.add(0, 0);
					i++;
				}
			} else {
				break;
			}
		}
		return num;
	}

	static ArrayList<Integer> calcDigits(int num, int base) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		while (num > 0) {
			digits.add(0, num % base);
			num /= base;
		}
		return digits;
	}
}