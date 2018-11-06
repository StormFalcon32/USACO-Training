/*
ID: benchen1
LANG: JAVA
TASK: transform
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class transform {

	public static void main(String[] args) throws IOException {
		// BufferedReader in = new BufferedReader(new
		// FileReader("D:\\bench\\eclipse\\USACO\\Training\\PLACEHOLD\\1.in"));
		BufferedReader in = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		StringTokenizer ln = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(ln.nextToken());
		boolean[][] originalPiece = new boolean[n][n];
		boolean[][] shiftedPiece = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String str = in.readLine();
			originalPiece[i] = convertToBool(str, n);
		}
		for (int i = 0; i < n; i++) {
			String str = in.readLine();
			shiftedPiece[i] = convertToBool(str, n);
		}
//		Initialize variables
		boolean equals = false;
		boolean[][] reflect = reflectHorizontal(originalPiece, n);
		boolean[][][] rotatedPieces = new boolean[4][n][n];
//		Case 1 - 3, rotated
		if (!equals) {
			rotatedPieces[0] = originalPiece;
			for (int rotates = 1; rotates < 4; rotates++) {
				rotatedPieces[rotates] = rotateClockwise90(rotatedPieces[rotates - 1], n);
				equals = matrixEquals(rotatedPieces[rotates], shiftedPiece, n);
				if (equals) {
					out.println(rotates);
					break;
				}
			}
		}
//		Case 4, reflected
		if (!equals) {
			equals = matrixEquals(reflect, shiftedPiece, n);
			if (equals) {
				out.println(4);
			}
		}
//		Case 5
		if (!equals) {
			boolean[][][] rotatedReflected = new boolean[4][n][n];
			rotatedReflected[0] = reflect;
			for (int rotates = 1; rotates < 4; rotates++) {
				rotatedReflected[rotates] = rotateClockwise90(rotatedReflected[rotates - 1], n);
				equals = matrixEquals(rotatedReflected[rotates], shiftedPiece, n);
				if (equals) {
					out.println(5);
					break;
				}
			}
		}
//		Case 6, no change
		if (!equals) {
			equals = matrixEquals(shiftedPiece, originalPiece, n);
			if (equals) {
				out.println(6);
			}
		}
//		Case 7, non valid change
		if (!equals) {
			out.println(7);
		}
		out.close();
		in.close();
	}

	static boolean[] convertToBool(String str, int n) {
		char[] chArr = str.toCharArray();
		boolean[] ret = new boolean[n];
		for (int i = 0; i < n; i++) {
			char a = chArr[i];
			if (a == '@') {
				ret[i] = true;
			} else {
				ret[i] = false;
			}
		}
		return ret;
	}

	static boolean[][] rotateClockwise90(boolean[][] matrix, int n) {
//		Matrix guaranteed to be square
		boolean[][] ret = new boolean[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				ret[c][n - r - 1] = matrix[r][c];
			}
		}
		return ret;
	}
	
	static boolean[][] reflectHorizontal(boolean[][] matrix, int n) {
//		Matrix guaranteed to be square
//		Can't set ret = matrix (pass by value)
		boolean[][] ret = new boolean[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				ret[r][c] = matrix[r][c];
			}
		}
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n / 2; c++) {
				ret[r][c] = matrix[r][n - c - 1];
				ret[r][n - c - 1] = matrix[r][c];
			}
		}
		return ret;
	}
	
	static boolean matrixEquals(boolean[][] a, boolean[][] b, int n) {
//		A and B guaranteed to have same dimensions
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (a[r][c] != b[r][c]) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void printMatrix(boolean[][] matrix, int n) {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (matrix[r][c]) {
					System.out.print("@");
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
}
