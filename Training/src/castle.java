
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
	static int numRooms = 0;
	static int biggestRoom = 0;
	static int biggestRemoveWallRoom = 0;
	static PriorityQueue<Room> rooms;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		COLS = Integer.parseInt(ln.nextToken());
		ROWS = Integer.parseInt(ln.nextToken());
		numVerts = ROWS * COLS;
		@SuppressWarnings("unchecked")
		LinkedList<Vertex>[][] adjList = new LinkedList[ROWS][COLS];
		rooms = new PriorityQueue<Room>();
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
				int[] dirR = {0, -1, 0, 1};
				int[] dirC = {-1, 0, 1, 0};
				// 0, 1, 2, 3 is west, north, east, south
				for (int i = 0; i < 4; i++) {
					if ((x & (1 << i)) == 0 << i) {
						int newR = r + dirR[i];
						int newC = c + dirC[i];
						if (inRange(newR, newC)) {
							adjList[r][c].add(new Vertex(newR, newC));
						}
					}
				}
			}
		}
		int numRooms = 0;
		for (int r = 0; r < ROWS; r++) {
			for (int c = 0; c < COLS; c++) {
				if(!visited[r][c]) {
					numRooms++;
					floodFill(adjList, new Vertex(r, c));
				}
			}
		}
		System.out.println(numRooms + "\n" + biggestRoom + "\n" + biggestRemoveWallRoom);
		out.close();
		in.close();
	}
	
	static void floodFill(LinkedList<Vertex>[][] adjList, Vertex root) {
		int roomSize = 0;
		LinkedList<Vertex> q = new LinkedList<Vertex>();
		q.add(root);
		Room temp = new Room();
		while (!q.isEmpty()) {
			roomSize++;
			Vertex curr = q.pollFirst();
			temp.modules.add(curr);
			visited[curr.r][curr.c] = true; 
			LinkedList<Vertex> adj = adjList[curr.r][curr.c];
			for (int i = 0; i < adj.size(); i++) {
				Vertex target = adj.get(i);
				if (!visited[target.r][target.c]) {
					visited[target.r][target.c]= true; 
					q.add(target);
				}
			}
		}
		temp.size = roomSize;
		rooms.add(temp);
		biggestRoom = Math.max(biggestRoom, roomSize);
	}
	
	static boolean inRange(int r, int c) {
		if (r < ROWS && r >= 0 && c < COLS && c >= 0) {
			return true;
		}
		return false;
	}
	
	static class Room implements Comparable<Room> {
		int size = 0;
		LinkedList<Vertex> modules = new LinkedList<Vertex>();
		
		public Room() {
		}
		
		@Override
		public int compareTo(Room other) {
			return Integer.compare(other.size, this.size);
		}
	}
	
	static class Vertex {
		int r;
		int c;
		
		public Vertex(int a, int b) {
			r = a;
			c = b;
		}
	}
}