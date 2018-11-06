/*
ID: benchen1
LANG: JAVA
TASK: milk
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class milk {
	
	static int finalMilk;
	static int numFarmers;

	public static void main(String[] args) throws IOException {
//		BufferedReader in = new BufferedReader(new FileReader("D:\\bench\\eclipse\\USACO\\Training\\milk\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("milk.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		finalMilk = Integer.parseInt(ln.nextToken());
		numFarmers = Integer.parseInt(ln.nextToken());
		Farmer[] farmers = new Farmer[numFarmers];
		for (int i = 0; i < numFarmers; i++) {
			ln = new StringTokenizer(in.readLine());
			farmers[i] = new Farmer(Integer.parseInt(ln.nextToken()), Integer.parseInt(ln.nextToken()));
		}
		Arrays.sort(farmers);
		int price = 0;
		int milk = 0;
		for (int i = 0; i < numFarmers && milk < finalMilk; i++) {
			Farmer curr = farmers[i];
			if (milk + curr.units >= finalMilk) {
				price += (finalMilk - milk) * curr.price;
				milk = finalMilk;
			} else {
				price += curr.units * curr.price;
				milk += curr.units;
			}
		}
		out.println(price);
		out.close();
		in.close();
	}
	
	static class Farmer implements Comparable<Farmer> {
		int units;
		int price;
		
		public Farmer(int a, int b) {
			price = a;
			units = b;
		}

		public int compareTo(Farmer other) {
			if (this.price == other.price) {
				return Integer.compare(other.units, this.units);
			}
			return Integer.compare(this.price, other.price);
		}
	}
}