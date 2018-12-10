
/*
ID: benchen1
LANG: JAVA
TASK: subset
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class subset {

	static int numInts;
	static int totalSum;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numInts = Integer.parseInt(ln.nextToken());
		int[] ints = new int[numInts];
		for (int i = 0; i < numInts; i++) {
			ints[i] = i + 1;
			totalSum += i + 1;
		}
		if (totalSum % 2 == 1) {
			out.println(0);
			out.close();
			in.close();
			return;
		}
		System.out.println((bruteForce(ints, numInts - 1, totalSum / 2)) / 2);
		out.close();
		in.close();
	}

	static int bruteForce(int[] nums, int currNumInd, int currSum) {
		if (currSum == 0) {
			return 1;
		}
		if (currSum < 0) {
			return 0;
		}
		if (currSum > 0 && currNumInd < 0) {
			return 0;
		}
//		Sum of bruteForce including currNum and excluding currNum
		return bruteForce(nums, currNumInd - 1, currSum) + bruteForce(nums, currNumInd - 1, currSum - nums[currNumInd]);
	}
}