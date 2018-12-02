
/*
ID: benchen1
LANG: JAVA
TASK: lamps
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class lamps {

	static int numLamps;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		numLamps = Integer.parseInt(ln.nextToken());
		int numPresses = Integer.parseInt(in.readLine());
		String on = in.readLine();
		String off = in.readLine();
		ArrayList<Integer> lampsOn = new ArrayList<Integer>();
		ArrayList<Integer> lampsOff = new ArrayList<Integer>();
		ln = new StringTokenizer(on);
		while (true) {
			int temp = Integer.parseInt(ln.nextToken());
			if (temp == -1) {
				break;
			}
			lampsOn.add(temp - 1);
		}
		ln = new StringTokenizer(off);
		while (true) {
			int temp = Integer.parseInt(ln.nextToken());
			if (temp == -1) {
				break;
			}
			lampsOff.add(temp - 1);
		}
		// First recognize that pressing a button more than once is useless, decreasing
		// the possibilities a lot
		TreeSet<BigInteger> basicStates = new TreeSet<BigInteger>();
		int comb = 0;
		for (int i = 0; i < 4; i++) {
			comb += 1 << i;
		}
		StringBuilder st = new StringBuilder("");
		for (int i = 0; i < numLamps; i++) {
			st.append("1");
		}
		BigInteger max = new BigInteger(st.toString(), 2);
		// 0 means all excluded and max means all included
		for (int x = 0; x <= comb; x++) {
			// Num represents state of lamps, 1 in digit i means the ith lamp is on
			BigInteger num = max;
			// Turn binary number x into a combination of button presses
			int presses = 0;
			for (int i = 0; i < 4; i++) {
				if ((x & (1 << i)) == 1 << i) {
					presses++;
					switch (i) {
					case 0:
						// Button 1
						for (int j = numLamps - 1; j >= 0; j--) {
							num = num.flipBit(j);
						}
						break;
					case 1:
						// Button 2
						for (int j = numLamps - 1; j >= 0; j--) {
							if (j % 2 == 0) {
								num = num.flipBit(j);
							}
						}
						break;
					case 2:
						// Button 3
						for (int j = numLamps - 1; j >= 0; j--) {
							if (j % 2 == 1) {
								num = num.flipBit(j);
							}
						}
						break;
					case 3:
						// Button 4
						int lampNum = 0;
						for (int j = numLamps - 1; j >= 0; j--) {
							if (lampNum % 3 == 0) {
								num = num.flipBit(j);
							}
							lampNum++;
						}
						break;
					}
				}
			}
			if (presses <= numPresses && (numPresses - presses) % 2 == 0) {
				basicStates.add(num);
			}
		}
		boolean found = false;
		while(!basicStates.isEmpty()) {
			BigInteger num = basicStates.pollFirst();
			boolean ok = true;
			for (int j = 0; j < lampsOn.size(); j++) {
				int shift = numLamps - lampsOn.get(j) - 1;
				if (!num.testBit(shift)) {
					ok = false;
					break;
				}
			}
			for (int j = 0; j < lampsOff.size() && ok; j++) {
				int shift = numLamps - lampsOff.get(j) - 1;
				if (num.testBit(shift)) {
					ok = false;
					break;
				}
			}
			if (ok) {
				found = true;
				String bin = String.format("%" + numLamps + "s", num.toString(2)).replace(" ", "0");
				out.println(bin);
			}
		}
		if (!found) {
			out.println("IMPOSSIBLE");
		}
		out.close();
		in.close();
	}
}