
/*
ID: benchen1
LANG: JAVA
TASK: msquare
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class msquare {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("msquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int[] input = new int[8];
		for (int i = 0; i < 8; i++) {
			input[i] = Integer.parseInt(tk.nextToken());
		}
		SquareState target = new SquareState(input);
		HashMap<SquareState, Boolean> visited = new HashMap<SquareState, Boolean>();
		LinkedList<SquareState> q = new LinkedList<SquareState>();
		int[] root = { 1, 2, 3, 4, 5, 6, 7, 8 };
		q.add(new SquareState(root));
		while (!q.isEmpty()) {
			SquareState curr = q.poll();
			if (curr.hashCode() == target.hashCode()) {
				if (curr.equals(target)) {
					out.println(curr.transforms.length());
					for (int i = 0; i < curr.transforms.length(); i++) {
						if (i != 0 && i % 60 == 0) {
							out.println();
						}
						out.print(curr.transforms.charAt(i));
					}
					out.println();
					break;
				}
			}
			visited.put(curr, true);
			int[][] toAdd = new int[3][8];
			StringBuilder[] transforms = new StringBuilder[3];
			StringBuilder copyA = new StringBuilder(curr.transforms.toString());
			StringBuilder copyB = new StringBuilder(curr.transforms.toString());
			StringBuilder copyC = new StringBuilder(curr.transforms.toString());
			toAdd[0] = curr.A();
			transforms[0] = copyA.append("A");
			toAdd[1] = curr.B();
			transforms[1] = copyB.append("B");
			toAdd[2] = curr.C();
			transforms[2] = copyC.append("C");
			for (int i = 0; i < 3; i++) {
				SquareState temp = new SquareState(toAdd[i]);
				temp.transforms = transforms[i];
				if (!visited.containsKey(temp)) {
					q.add(temp);
					visited.put(temp, true);
				}
			}
		}
		out.close();
		in.close();
	}
}

class SquareState {
	int[] square;
	StringBuilder transforms = new StringBuilder("");
	
	public SquareState(int[] a) {
		square = a;
	}
	
	@Override
	public boolean equals(Object other) {
		SquareState o = (SquareState) other;
		for (int i = 0; i < 8; i++) {
			if (o.square[i] != square[i]) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		for (int i = 0; i < 8; i++) {
			hash += square[i] * Math.pow(9, i);
		}
		return hash;
	}
	
	public int[] A() {
		int[] ret = new int[8];
		for (int i = 0; i < 8; i++) {
			ret[8 - i - 1] = square[i];
		}
		return ret;
	}
	
	public int[] B() {
		int[] ret = new int[8];
		for (int i = 0; i < 4; i++) {
			ret[(i + 1) % 4] = square[i];
		}
		for (int i = 4; i < 8; i++) {
			ret[((i - 1) % 4) + 4] = square[i];
		}
		return ret;
	}
	
	public int[] C() {
		int[] ret = new int[8];
		for (int i = 0; i < 8; i++) {
			ret[i] = square[i];
		}
		ret[1] = square[6];
		ret[2] = square[1];
		ret[5] = square[2];
		ret[6] = square[5];
		return ret;
	}
}