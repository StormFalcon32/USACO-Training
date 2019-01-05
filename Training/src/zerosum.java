
/*
ID: benchen1
LANG: JAVA
TASK: zerosum
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class zerosum {

	static int n;
	static HashMap<State, Long> states = new HashMap<State, Long>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		n = Integer.parseInt(ln.nextToken());
		int[] ints = new int[n];
		for (int i = 1; i <= n; i++) {
			ints[i - 1] = i;
		}
		System.out.println(calc(ints));
		out.close();
		in.close();
	}

	static long calc(int[] nums) {
		// All possible pairings
		long ret = 0;

		int numDigs = nums.length - 1;
		int max = 0;
		for (int i = 0; i < numDigs; i++) {
			max += 1 << i;
		}
		// 0 means all excluded and max means all included
		for (int x = 0; x <= max; x++) {
			ArrayList<Integer> toTry = new ArrayList<Integer>();
			int sum = 0;
			for (int i = 0; i < numDigs; i++) {
				if ((x & (1 << i)) == 1 << i) {
					// Combine here
					toTry.add(nums[i] + nums[i + 1]);
					sum += nums[i] + nums[i + 1];
				} else {
					toTry.add(nums[i]);
					sum += nums[i];
				}
			}
			ret += bruteForce(toTry, toTry.size() - 1, sum / 2);
		}
		return ret;
	}

	static long bruteForce(ArrayList<Integer> nums, int currNumInd, int currSum) {
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
		long result = bruteForce(nums, currNumInd - 1, currSum)
				+ bruteForce(nums, currNumInd - 1, currSum - nums.get(currNumInd));
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