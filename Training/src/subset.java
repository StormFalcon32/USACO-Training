
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
import java.util.HashMap;
import java.util.StringTokenizer;

public class subset {

	static int numInts;
	static int totalSum;
	static HashMap<State, Long> states = new HashMap<State, Long>();

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
		} else {
			out.println((bruteForce(ints, numInts - 1, totalSum / 2)) / 2);
		}
		out.close();
		in.close();
	}

	static long bruteForce(int[] nums, int currNumInd, int currSum) {
		State curr = new State(currNumInd, currSum);
		if (states.containsKey(curr)) {
			return states.get(curr);
		}
		if (currSum == 0) {
			return 1;
		}
		if (currSum < 0) {
			return 0;
		}
		if (currSum > 0 && currNumInd < 0) {
			return 0;
		}
		// Sum of bruteForce including currNum and excluding currNum
		long result = bruteForce(nums, currNumInd - 1, currSum) + bruteForce(nums, currNumInd - 1, currSum - nums[currNumInd]);
		states.put(curr, result);
		return result;
	}

	static class State {
		int numInd;
		int sum;

		public State(int a, int b) {
			numInd = a;
			sum = b;
		}

		@Override
		public boolean equals(Object o) {
			State other = (State) o;
			if (this.numInd == other.numInd && this.sum == other.sum) {
				return true;
			}
			return false;
		}

		@Override
		public int hashCode() {
			int hash = 7;
			hash = 31 * hash + numInd;
			hash = 31 * sum;
			return hash;
		}
	}
}