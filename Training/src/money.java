
/*
ID: benchen1
LANG: JAVA
TASK: money
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

public class money {

	static int numCoins;
	static HashMap<State, Long> states = new HashMap<State, Long>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("money.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numCoins = Integer.parseInt(ln.nextToken());
		int moneyToMake = Integer.parseInt(ln.nextToken());
		ArrayList<Integer> coins = new ArrayList<Integer>();
		int currCoins = 0;
		while (currCoins < numCoins) {
			ln = new StringTokenizer(in.readLine());
			while (ln.hasMoreTokens()) {
				int next = Integer.parseInt(ln.nextToken());
				if (!coins.contains(next)) {
					coins.add(next);
				}
				currCoins++;
			}
		}
		out.println(make(coins, coins.size() - 1, moneyToMake));
		out.close();
		in.close();
	}

	static long make(ArrayList<Integer> coins, int currInd, int currSum) {
		// Base cases
		State curr = new State(currInd, currSum);
		if (states.containsKey(curr)) {
			return states.get(curr);
		}
		if (currInd < 0 && currSum > 0) {
			return 0;
		}
		if (currSum < 0) {
			return 0;
		}
		if (currSum == 0) {
			return 1;
		}
		// Return make with current coin and without
		long result = make(coins, currInd - 1, currSum) + make(coins, currInd, currSum - coins.get(currInd));
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