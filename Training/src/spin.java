
/*
ID: benchen1
LANG: JAVA
TASK: spin
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class spin {
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("spin.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		boolean[][] wheels = new boolean[5][360];
		int[] speeds = new int[5];
		for (int i = 0; i < 5; i++) {
			StringTokenizer tk = new StringTokenizer(in.readLine());
			int s = Integer.parseInt(tk.nextToken());
			speeds[i] = s;
			int numW = Integer.parseInt(tk.nextToken());
			for (int j = 0; j < numW; j++) {
				int start = Integer.parseInt(tk.nextToken());
				int width = Integer.parseInt(tk.nextToken());
				for (int k = start; k <= start + width; k++) {
					wheels[i][k % 360] = true;
				}
			}
		}
		String ans = "none";
		for (int time = 0; time < 360; time++) {
			boolean pass = true;
			for (int i = 0; i < 360; i++) {
				pass = true;
				for (int j = 0; j < 5; j++) {
					if (!wheels[j][i]) {
						pass = false;
					}
				}
				if (pass) {
					break;
				}
			}
			if (pass) {
				ans = Integer.toString(time);
				break;
			}
			for (int i = 0; i < 5; i++) {
				boolean[] temp = new boolean[360];
				for (int j = 0; j < 360; j++) {
					temp[(j + speeds[i]) % 360] = wheels[i][j];
				}
				wheels[i] = temp;
			}
		}
		out.println(ans);
		out.close();
		in.close();
	}
}