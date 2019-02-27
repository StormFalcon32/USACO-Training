/*
ID: benchen1
LANG: JAVA
TASK: contact
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class contact {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("contact.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int A = Integer.parseInt(tk.nextToken());
		int B = Integer.parseInt(tk.nextToken());
		int N = Integer.parseInt(tk.nextToken());
		String ln = in.readLine();
		StringBuilder data = new StringBuilder();
		while (ln != null) {
			data.append(ln);
			ln = in.readLine();
		}
		Map<String, Integer> frequencies = new HashMap<String, Integer>();
		for (int length = A; length <= B && length <= data.length(); length++) {
			StringBuilder curr = new StringBuilder(data.substring(0, length));
			String toString;
			for (int i = 0; i + length < data.length(); i++) {
				toString = curr.toString();
				if (frequencies.containsKey(toString)) {
					int count = frequencies.get(toString);
					frequencies.put(toString, count + 1);
				} else {
					frequencies.put(toString, 1);
				}
				curr.deleteCharAt(0);
				curr.append(data.charAt(i + length));
			}
			toString = curr.toString();
			if (frequencies.containsKey(toString)) {
				int count = frequencies.get(toString);
				frequencies.put(toString, count + 1);
			} else {
				frequencies.put(toString, 1);
			}
		}
		PriorityQueue<Freq> sortedOutput = new PriorityQueue<Freq>();
		for (String s : frequencies.keySet()) {
			sortedOutput.add(new Freq(s, frequencies.get(s)));
		}
		Freq prev = new Freq("", 0);
		int freqNum = 0;
		int freqsPerLine = 0;
		while (freqNum < N) {
			Freq curr = sortedOutput.poll();
			if (curr != null) {
				if (curr.count == prev.count) {
					if (freqsPerLine == 6) {
						freqsPerLine = 0;
						out.print("\n" + curr.seq);
					} else {
						out.print(" " + curr.seq);
					}
					freqsPerLine++;
				} else {
					if (freqNum != 0) {
						out.println();
					}
					out.print(curr.count + "\n" + curr.seq);
					freqsPerLine = 1;
					if (freqNum == N - 1) {
						prev.count = curr.count;
						curr = sortedOutput.poll();
						while (curr.count == prev.count) {
							if (freqsPerLine == 6) {
								freqsPerLine = 0;
								out.print("\n" + curr.seq);
							} else {
								out.print(" " + curr.seq);
							}
							freqsPerLine++;
							prev.count = curr.count;
							curr = sortedOutput.poll();
						}
					}
					freqNum++;
				}
				prev.count = curr.count;
			} else {
				break;
			}
		}
		out.println();
		out.close();
		in.close();
	}
	
	static class Freq implements Comparable<Freq> {
		String seq;
		int count;
		
		public Freq(String s, int c) {
			seq = s;
			count = c;
		}

		@Override
		public int compareTo(Freq o) {
			if (this.count == o.count) {
				if (this.seq.length() == o.seq.length()) {
					return Integer.compare(Integer.parseInt(this.seq, 2), Integer.parseInt(o.seq, 2));
				}
				return Integer.compare(this.seq.length(), o.seq.length());
			}
			return Integer.compare(o.count, this.count);
		}
	}
}