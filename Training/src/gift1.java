/*
ID: benchen1
LANG: JAVA
TASK: gift1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class gift1 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		Person[] names = new Person[n];
		for (int i = 0; i < n; i++) {
			names[i] = new Person(in.readLine(), 0);
		}
		for (int i = 0; i < n; i++) {
			String currName = in.readLine();
			int currIndex = 0;
			for (int j = 0; j < n; j++) {
				if (names[j].name.equals(currName)) {
					currIndex = j;
				}
			}
			ln = new StringTokenizer(in.readLine());
			int money = Integer.parseInt(ln.nextToken());
			int numRecipients = Integer.parseInt(ln.nextToken());
			String[] recipientNames = new String[numRecipients];
			int[] recipients = new int[numRecipients];
			for (int j = 0; j < numRecipients; j++) {
				recipientNames[j] = in.readLine();
			}
			if (money != 0) {
				for (int j = 0; j < numRecipients; j++) {
					for (int k = 0; k < n; k++) {
						if (names[k].name.equals(recipientNames[j])) {
							recipients[j] = k;
						}
					}
				}
				int leftOver = money % numRecipients;
				int each = (money - leftOver) / numRecipients;
				names[currIndex].netChange -= money - leftOver;
				for (int j = 0; j < numRecipients; j++) {
					names[recipients[j]].netChange += each;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			out.println(names[i].name + " " + names[i].netChange);
		}
		in.close();
		out.close();
	}

	static class Person {
		String name;
		int netChange;

		public Person(String a, int b) {
			name = a;
			netChange = b;
		}
	}
}
