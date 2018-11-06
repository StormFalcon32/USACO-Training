/*
ID: benchen1
LANG: JAVA
TASK: combo
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class combo {
	
	static int n;
	static TreeSet<Integer> validCombs;
	static int[] farmerComb;
	static int[] masterComb;
	static int numValid = 0;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\Workspace\\Training\\combo\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(ln.nextToken());
		farmerComb = new int[3];
		masterComb = new int[3];
		
		ln = new StringTokenizer(in.readLine());
		for (int i = 0; i < 3; i++) {
			farmerComb[i] = Integer.parseInt(ln.nextToken());
		}
		
		ln = new StringTokenizer(in.readLine());
		for (int i = 0; i < 3; i++) {
			masterComb[i] = Integer.parseInt(ln.nextToken());
		}
		
		int[] digits = new int[n];
		for (int i = 0; i < n; i++) {
			digits[i] = i + 1;
		}
		
		validCombs = new TreeSet<Integer>();
		int[] chosen = new int[4];
		MakeAll(chosen, digits, 0, 3);
		out.println(numValid);
		out.close();
		in.close();
	}
	
	static boolean isGood(int[] goodComb, int[] checkComb, int n) {
		boolean good = false;
		for (int i = 0; i < 3; i++) {
			int goodNum = goodComb[i];
			int check = checkComb[i];
			
			if (goodNum + 2 >= n && (goodNum + 2) % n >= check) {
				good = true;
			} else if (goodNum - 2 <= 0 && n + goodNum - 2 <= check) {
				good = true;
			} else if (goodNum - 2 <= check && goodNum + 2 >= check) {
				good = true;
			} else {
				good = false;
				break;
			}
		}
		
		return good;
	}
	
	static void MakeAll(int[] chosen, int[] arr, int currLength, int length) {
//		Repeated digits allowed, order matters, all combinations
		if (currLength == length) {
			int[] checkComb = new int[length];
//			int num = 0;
			for (int i = 0; i < length; i++) {
//				num += arr[chosen[i]] * Math.pow(10, length - i - 1);
				checkComb[i] = arr[chosen[i]];
			}
			if (isGood(farmerComb, checkComb, n) || isGood(masterComb, checkComb, n)) {
//				System.out.println(num);
				numValid++;
			}
			return;
		}

		for (int i = 0; i < arr.length; i++) {
			chosen[currLength] = i;
			MakeAll(chosen, arr, currLength + 1, length);
		}
		return;
	}
}