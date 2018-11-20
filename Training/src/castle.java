
/*
ID: benchen1
LANG: JAVA
TASK: castle
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class castle {

	static int COLS;
	static int ROWS;
	static int numVerts;
	static boolean[][] visited;
	static int[][] roomNums;
	static int[][] roomSizes;
	static int numRooms = 0;
	static int biggestRoom = 0;
	static int[] dirR = { 0, -1, 0, 1 };
	static int[] dirC = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		COLS = Integer.parseInt(ln.nextToken());
		ROWS = Integer.parseInt(ln.nextToken());
		numVerts = ROWS * COLS;
		@SuppressWarnings("unchecked")
		LinkedList<Vertex>[][] adjList = new LinkedList[ROWS][COLS];
		LinkedList<Wall> walls = new LinkedList<Wall>();
		roomNums = new int[ROWS][COLS];
		roomSizes = new int[ROWS][COLS];
		visited = new boolean[ROWS][COLS];
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				adjList[r][c] = new LinkedList<Vertex>();
			}
		}
		for (int r = 0; r < ROWS; r++) {
			ln = new StringTokenizer(in.readLine());
			for (int c = 0; c < COLS; c++) {
				int x = Integer.parseInt(ln.nextToken());
				// 0, 1, 2, 3 is west, north, east, south
				for (int i = 0; i < 4; i++) {
					if ((x & (1 << i)) == 0 << i) {
						int newR = r + dirR[i];
						int newC = c + dirC[i];
						if (inRange(newR, newC)) {
							adjList[r][c].add(new Vertex(newR, newC));
						}
					} else {
						int newR = r + dirR[i];
						int newC = c + dirC[i];
						if (inRange(newR, newC)) {
							walls.add(new Wall(r, c, i));
						}
					}
				}
			}
		}
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if (!visited[r][c]) {
					floodFill(adjList, new Vertex(r, c));
					numRooms++;
				}
			}
		}
		PriorityQueue<Wall> bestWalls = new PriorityQueue<Wall>();
		while (!walls.isEmpty()) {
			// Calculate potential of every wall
			Wall curr = walls.poll();
			int dir = curr.dir;
			int r = curr.r;
			int c = curr.c;
			if (dir == 1 || dir == 2) {
				// North or East
				int newR = r + dirR[dir];
				int newC = c + dirC[dir];
				if (inRange(newR, newC) && roomNums[r][c] != roomNums[newR][newC]) {
					curr.potSize = roomSizes[r][c] + roomSizes[newR][newC];
					bestWalls.add(curr);
				}
			}
		}
		Wall remove = bestWalls.poll();
		char[] dirs = { 'W', 'N', 'E', 'S' };
		out.println(numRooms + "\n" + biggestRoom + "\n" + remove.potSize + "\n" + (remove.r + 1) + " " + (remove.c + 1)
				+ " " + dirs[remove.dir]);
		out.close();
		in.close();
	}

	static void floodFill(LinkedList<Vertex>[][] adjList, Vertex root) {
		int roomSize = 0;
		LinkedList<Vertex> q = new LinkedList<Vertex>();
		LinkedList<Vertex> room = new LinkedList<Vertex>();
		q.add(root);
		while (!q.isEmpty()) {
			roomSize++;
			Vertex curr = q.pollFirst();
			room.add(curr);
			roomNums[curr.r][curr.c] = numRooms + 1;
			visited[curr.r][curr.c] = true;
			LinkedList<Vertex> adj = adjList[curr.r][curr.c];
			for (int i = 0; i < adj.size(); i++) {
				Vertex target = adj.get(i);
				if (!visited[target.r][target.c]) {
					visited[target.r][target.c] = true;
					q.add(target);
				}
			}
		}
		while (!room.isEmpty()) {
			Vertex curr = room.pollFirst();
			roomSizes[curr.r][curr.c] = roomSize;
		}
		biggestRoom = Math.max(biggestRoom, roomSize);
	}

	static boolean inRange(int r, int c) {
		if (r < ROWS && r >= 0 && c < COLS && c >= 0) {
			return true;
		}
		return false;
	}

	static class Vertex {
		int r;
		int c;

		public Vertex(int a, int b) {
			r = a;
			c = b;
		}
	}

	static class Wall implements Comparable<Wall> {
		int r;
		int c;
		int potSize;
		int dir;

		public Wall(int a, int b, int d) {
			r = a;
			c = b;
			dir = d;
		}

		@Override
		public int compareTo(Wall other) {
			if (this.potSize == other.potSize) {
				if (this.c == other.c) {
					return Integer.compare(other.r, this.r);
				}
				return Integer.compare(this.c, other.c);
			}
			return Integer.compare(other.potSize, this.potSize);
		}
	}
}