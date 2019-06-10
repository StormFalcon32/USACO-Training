
/*
ID: benchen1
LANG: JAVA
TASK: ratios
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ratios {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("ratios.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		double[][] ratios = new double[4][3];
		for (int i = 0; i < 4; i++) {
			StringTokenizer tk = new StringTokenizer(in.readLine());
			for (int j = 0; j < 3; j++) {
				ratios[i][j] = Integer.parseInt(tk.nextToken());
			}
		}
		int sum = Integer.MAX_VALUE;
		int totalRatio = 0;
		int ansA = 0;
		int ansB = 0;
		int ansC = 0;
		for (int i = 0; i <= 100; i++) {
			for (int j = 0; j <= 100; j++) {
				for (int k = 0; k <= 100; k++) {
					if (i == 0 && j == 0 && k == 0) {
						continue;
					}
					double[] newRatio = new double[3];
					int[] multiples = { i, j, k };
					for (int x = 1; x < 4; x++) {
						for (int y = 0; y < 3; y++) {
							newRatio[y] += multiples[x - 1] * ratios[x][y];
						}
					}
					double prevMult = newRatio[0] / ratios[0][0];
					boolean correctRatio = true;
					for (int x = 0; x < 3; x++) {
						if (ratios[0][x] == 0 && newRatio[x] == 0) {
							continue;
						}
						if (newRatio[x] / ratios[0][x] != prevMult || (newRatio[x] / ratios[0][x]) % 1 != 0) {
							correctRatio = false;
						}
						prevMult = newRatio[x] / ratios[0][x];
					}
					if (correctRatio) {
						if (i + j + k <= sum) {
							ansA = i;
							ansB = j;
							ansC = k;
							sum = i + j + k;
							totalRatio = (int) prevMult;
						}
					}
				}
			}
		}
		if (sum == Integer.MAX_VALUE) {
			out.println("NONE");
		} else {
			out.println(ansA + " " + ansB + " " + ansC + " " + totalRatio);
		}
		out.close();
		in.close();
	}
}