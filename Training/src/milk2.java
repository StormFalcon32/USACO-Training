/*
ID: benchen1
LANG: JAVA
TASK: milk2
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class milk2 {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		State[] states = new State[n * 2];
		for (int i = 0; i < n; i++) {
			ln = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(ln.nextToken());
			int b = Integer.parseInt(ln.nextToken());
			states[i * 2] = new State(a, i);
			states[i * 2 + 1] = new State(b, i);
		}
		Arrays.sort(states);
		int milkTime = 0;
		int currMilkTime = 0;
		int prevTime = 0;
		int biggestGap = 0;
		int currGap = -states[0].time;
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for (int i = 0; i < n * 2; i++) {
			State curr = states[i];
			if (ts.isEmpty()) {
				currGap += curr.time - prevTime;
			} else {
				currMilkTime += curr.time - prevTime;
			}
			if (ts.contains(curr.cow)) {
				ts.remove(curr.cow);
				if (curr.time != prevTime && ts.isEmpty() && (i + 1 >= n * 2 || curr.time != states[i + 1].time)) {
					milkTime = Math.max(milkTime, currMilkTime);
					currMilkTime = 0;
				}
			} else {
				if (ts.isEmpty()) {
					biggestGap = Math.max(biggestGap, currGap);
					currGap = 0;
				}
				ts.add(curr.cow);
			}
			prevTime = curr.time;
		}
		out.println(milkTime + " " + biggestGap);
		in.close();
		out.close();		
	}
	
	static class State implements Comparable<State> {
		int time;
		int cow;
		
		public State(int a, int b) {
			time = a;
			cow = b;
		}
		@Override
		public int compareTo(State other) {
			// TODO Auto-generated method stub
			return Integer.compare(this.time, other.time);
		}
		
	}
}