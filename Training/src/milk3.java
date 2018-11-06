/*
ID: benchen1
LANG: JAVA
TASK: milk3
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class milk3 {
	
	static int capA;
	static int capB;
	static int capC;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		capA = Integer.parseInt(ln.nextToken());
		capB = Integer.parseInt(ln.nextToken());
		capC = Integer.parseInt(ln.nextToken());
//		Array capacities[a][b][c] is the state where the 3 buckets have a, b, and c units of milk respectively
		boolean[][][] capacities = new boolean[capA + 1][capB + 1][capC + 1];
		capacities[0][0][capC] = true;
		TreeSet<Integer> outputs = new TreeSet<Integer>();
		for (int a = 0; a < 7; a++) {
			for (int i = 0; i <= capA; i++) {
				for (int j = 0; j <= capB; j++) {
					for (int k = 0; k <= capC; k++) {
						if (!capacities[i][j][k]) {
							continue;
						}
	//					Check if bucket 1 is empty
						if (i == 0) {
							outputs.add(k);
						}
	//					Try all possible pours
	
	//					Try pouring bucket 3 into 2
						if (capB - j <= k) {
							capacities[i][capB][k - capB + j] = true;
						} else {
							capacities[i][j + k][0] = true;
						}
	//					Try pouring bucket 3 into 1
						if (capA - i <= k) {
							capacities[capA][j][k - capA + i] = true;
						} else {
							capacities[i + k][j][0] = true;
						}
	//					Try pouring bucket 2 into 3
						if (capC - k <= j) {
							capacities[i][j - capC + k][capC] = true;
						} else {
							capacities[i][0][k + j] = true;
						}
	//					Try pouring bucket 2 into 1
						if (capA - i <= j) {
							capacities[capA][j - capA + i][k] = true;
						} else {
							capacities[i + j][0][k] = true;
						}
	//					Try pouring bucket 1 into 2
						if (capB - j <= i) {
							capacities[i - capB + j][capB][k] = true;
						} else {
							capacities[0][j + i][k] = true;
						}
	//					Try pouring bucket 1 into 3
						if (capC - k <= i) {
							capacities[i - capC + k][j][capC] = true;
						} else {
							capacities[0][j][k + i] = true;
						}
					}
				}
			}
		}
		while (outputs.size() > 1) {
			out.print(outputs.pollFirst() + " ");
		}
		out.println(outputs.pollFirst());
		out.close();
		in.close();
	}
}