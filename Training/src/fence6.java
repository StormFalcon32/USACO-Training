/*
ID: benchen1
LANG: JAVA
TASK: fence6
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class fence6 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("fence6.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence6.out")));
		StringTokenizer tk = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(tk.nextToken());
		int[][] adjMat = new int[N * 2][N * 2];
		Edge[] edges = new Edge[N];
		for (int i = 0; i < N; i++) {
			tk = new StringTokenizer(in.readLine());
			int segNum = Integer.parseInt(tk.nextToken()) - 1;
			int length = Integer.parseInt(tk.nextToken());
			int N1 = Integer.parseInt(tk.nextToken());
			int N2 = Integer.parseInt(tk.nextToken());
			edges[segNum] = new Edge(segNum, length);
			tk = new StringTokenizer(in.readLine());
			for (int j = 0; j < N1; j++) {
				int toAdd = Integer.parseInt(tk.nextToken()) - 1;
				edges[segNum].leftAdj.add(toAdd);
			}
			tk = new StringTokenizer(in.readLine());
			for (int j = 0; j < N2; j++) {
				int toAdd = Integer.parseInt(tk.nextToken()) - 1;
				edges[segNum].rightAdj.add(toAdd);
			}
		}
		edges[0].left = 0;
		edges[0].right = 1;
		int vertexCounter = 2;
		for (int i = 0; i < N; i++) {
			for (int j : edges[i].leftAdj) {
				if (edges[j].leftAdj.contains(i)) {
					edges[j].left = edges[i].left;
				}
				if (edges[j].rightAdj.contains(j)) {
					edges[j].right = edges[i].left;
				}
				if (edges[j].left == -1) {
					edges[j].left = vertexCounter++;
				}
				if (edges[j].right == -1) {
					edges[j].right = vertexCounter++;
				}
			}
			for (int j : edges[i].rightAdj) {
				if (edges[j].leftAdj.contains(i)) {
					edges[j].left = edges[i].right;
				}
				if (edges[j].rightAdj.contains(i)) {
					edges[j].right = edges[i].right;
				}
				if (edges[j].left == -1) {
					edges[j].left = vertexCounter++;
				}
				if (edges[j].right == -1) {
					edges[j].right = vertexCounter++;
				}
			}
		}
		for (int i = 0; i < N * 2; i++) {
			Arrays.fill(adjMat[i], 1 << 20);
		}
		HashSet<Integer> vertices = new HashSet<Integer>();
		for (int i = 0; i < N; i++) {
			int left = edges[i].left;
			int right = edges[i].right;
			int weight = edges[i].weight;
			adjMat[left][right] = weight;
			adjMat[right][left] = weight;
			vertices.add(left);
			vertices.add(right);
		}
		out.println(floyd(vertexCounter, adjMat, N));
		out.close();
		in.close();
	}

	static int floyd(int index, int[][] adjMat, int N) {
		int[][] dists = new int[N * 2][N * 2];
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N * 2; i++) {
			for (int j = 0; j < N * 2; j++) {
				dists[i][j] = adjMat[i][j];
			}
		}
		for (int k = 0; k < index; k++) {
			for (int i = 0; i < k; i++) {
				for (int j = i + 1; j < k; j++) {
					result = Math.min(result, dists[i][j] + adjMat[i][k] + adjMat[k][j]);
				}
			}
			for (int i = 0; i < index; i++) {
				for (int j = 0; j < index; j++) {
					dists[i][j] = Math.min(dists[i][j], dists[i][k] + dists[k][j]);
				}
			}
		}
		return result;
	}

	static class Edge {
		int num;
		int weight;
		LinkedList<Integer> leftAdj = new LinkedList<Integer>();
		LinkedList<Integer> rightAdj = new LinkedList<Integer>();
		int left = -1;
		int right = -1;

		public Edge(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
	}
}