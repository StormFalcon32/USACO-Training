/*
ID: benchen1
LANG: JAVA
TASK: friday
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class friday {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		int[] nonLeap = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int[] leap = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int dayOfWeek = 0;
		int[] weekdays = new int[7];
		for (int i = 0; i < n; i++) {
			int year = 1900 + i;
			if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
				// Leap
				for (int j = 0; j < 12; j++) {
					int thirteenth = (dayOfWeek + 12) % 7;
					weekdays[thirteenth]++;
					dayOfWeek = (dayOfWeek + leap[j]) % 7;
				}
			} else {
				// Non leap
				for (int j = 0; j < 12; j++) {
					int thirteenth = (dayOfWeek + 12) % 7;
					weekdays[thirteenth]++;
					dayOfWeek = (dayOfWeek + nonLeap[j]) % 7;
				}
			}
		}
		for (int i = 0; i < 6; i++) {
			out.print(weekdays[(i + 5) % 7] + " ");
		}
		out.print(weekdays[4] + "\n");
		in.close();
		out.close();
	}

}
