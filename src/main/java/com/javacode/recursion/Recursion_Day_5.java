package com.javacode.recursion;

public class Recursion_Day_5 {

	public static void main(String[] args) {

//		queensCombination1(0, 2, "", 0, 0);

//		int n = 2;
//		String board[][] = new String[n][n];
//		queensPermutation1(board, n, 1);

//		boolean arr[] = new boolean[2];
//		queensPermutation2(0, arr, 2, 0, 0, "");

//		boolean arr[][] = new boolean[2][2];
//		queensCombination2(arr, 0, 2, 0, -1);

//		boolean board[][] = new boolean[2][2];
//		queensCombination2As1d(board, 0, 2, -1);

//		boolean board[][] = new boolean[4][4];
//		nQueensProblem(board, 0, 4, -1);

//		int board[][] = new int[4][4];
//		nQueensProblem2(board, 0, 4);

//		int board[][] = new int[2][2];
//		nKnightsCombination(board, 0, 2, -1);

//		int arr[] = { 2, 3, 5, 6, 7 };
//		coinChangeCombination(arr, 0, 12, "");

		int arr[] = { 2, 3, 5, 6, 7 };
		coinChangeCombination2(arr, 0, 12, "");
	}

	private static void coinChangeCombination2(int[] arr, int idx, int target, String ans) {

		if (idx == arr.length) {
			if (target == 0) {
				System.out.println(ans);
			}
			return;
		} else if (target == 0) {
			System.out.println(ans);
			return;
		}

		if (target > 0) {
			coinChangeCombination2(arr, idx, target - arr[idx], ans + arr[idx] + "-");
		} else {
			coinChangeCombination2(arr, idx + 1, target - arr[idx], ans + arr[idx] + "-");
		}
		coinChangeCombination2(arr, idx + 1, target, ans);
	}

	private static void coinChangeCombination(int[] arr, int idx, int target, String ans) {

		if (idx == arr.length) {
			if (target == 0) {
				System.out.println(ans);
			}
			return;
		}

		coinChangeCombination(arr, idx + 1, target, ans);
		coinChangeCombination(arr, idx + 1, target - arr[idx], ans + arr[idx] + "-");

	}

	private static void nKnightsCombination(int[][] board, int cq, int tq, int li) {

		if (cq == tq) {

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					System.out.print(board[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			return;
		}

		for (int i = li + 1; i < board.length * board.length; i++) {

			int row = i / board.length;
			int col = i % board.length;

			if (board[row][col] == 0) {
				board[row][col] = 1;
				nKnightsCombination(board, cq + 1, tq, i);
				board[row][col] = 0;
			}
		}
	}

	private static void nQueensProblem2(int[][] board, int cq, int tq) {

		if (cq == tq) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] > 0) {
						System.out.print("q" + board[i][j]);
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < board.length * board.length; i++) {

			int row = i / board.length;
			int col = i % board.length;

			if (board[row][col] == 0 && isSafeToPlace2(board, row, col)) {

				board[row][col] = cq + 1;
				nQueensProblem2(board, cq + 1, tq);
				board[row][col] = 0;
			}
		}
	}

	public static boolean isSafeToPlace2(int board[][], int row, int col) {

		for (int i = 0; i < board.length; i++) {
			if (board[row][i] > 0) {
				return false;
			}
		}
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] > 0) {
				return false;
			}
		}

		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] > 0) {
				return false;
			}
		}
		for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[i][j] > 0) {
				return false;
			}
		}

		for (int i = row + 1, j = col - 1; i < board.length && j >= 0; i++, j--) {
			if (board[i][j] > 0) {
				return false;
			}
		}
		for (int i = row + 1, j = col + 1; i < board.length && j < board.length; i++, j++) {
			if (board[i][j] > 0) {
				return false;
			}
		}

		return true;
	}

	private static void nQueensProblem(boolean board[][], int cq, int tq, int li) {

		if (cq == tq) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] == true) {
						System.out.print("q");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			System.out.println();
			return;
		}

		for (int i = li + 1; i < board.length * board.length; i++) {

			int row = i / board.length;
			int col = i % board.length;

			if (isSafeToPlace(board, row, col)) {
				board[row][col] = true;
				nQueensProblem(board, cq + 1, tq, i);
				board[row][col] = false;
			}
		}
	}

	private static boolean isSafeToPlace(boolean[][] board, int row, int col) {

		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == true) {
				return false;
			}
		}

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == true) {
				return false;
			}
		}

		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == true) {
				return false;
			}
		}

		for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[i][j] == true) {
				return false;
			}
		}

		return true;
	}

	public static void queensCombination2As1d(boolean arr[][], int cq, int tq, int li) {

		if (cq == tq) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					if (arr[i][j] == true) {
						System.out.print("q");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			System.out.println();
			return;
		}

		for (int i = li + 1; i < arr.length * arr.length; i++) {

			int row = i / arr.length;
			int col = i % arr.length;

			if (arr[row][col] == false) {
				arr[row][col] = true;
				queensCombination2As1d(arr, cq + 1, tq, i);
				arr[row][col] = false;
			}
		}
	}

	public static void queensCombination2(boolean arr[][], int cq, int tq, int row, int col) {

		if (cq == tq) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr.length; j++) {
					if (arr[i][j] == true) {
						System.out.print("q");
					} else {
						System.out.print("-");
					}
				}
				System.out.println();
			}
			System.out.println();
			return;
		}

		for (int j = col + 1; j < arr.length; j++) {
			if (arr[row][j] == false) {
				arr[row][j] = true;
				queensCombination2(arr, cq + 1, tq, row, j);
				arr[row][j] = false;
			}
		}

		for (int i = row + 1; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i][j] == false) {
					arr[i][j] = true;
					queensCombination2(arr, cq + 1, tq, i, j);
					arr[i][j] = false;
				}
			}
		}
	}

	public static void queensPermutation2(int qsf, boolean arr[], int n, int row, int col, String ans) {

		if (row == n) {
			if (qsf == n)
				System.out.println(ans);
			return;
		}

		int nr = row;
		int nc = col;
		String sep = "";

		if (col == n - 1) {
			nr = row + 1;
			nc = 0;
			sep = "\n";
		} else {
			nc = col + 1;
			sep = "\t";
		}

		queensPermutation2(qsf, arr, n, nr, nc, ans + "-" + sep);
		for (int i = 0; i < n; i++) {
			if (arr[i] == false) {
				arr[i] = true;
				queensPermutation2(qsf + 1, arr, n, nr, nc, ans + "q" + (i + 1) + sep);
				arr[i] = false;
			}
		}
	}

	public static void queensPermutation1(String board[][], int n, int ci) {

		if (ci > n) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] == null) {
						System.out.print("-\t");
					} else {
						System.out.print(board[i][j] + "\t");
					}
				}
				System.out.println();
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == null) {
					board[i][j] = "q" + ci;
					queensPermutation1(board, n, ci + 1);
					board[i][j] = null;
				}
			}
		}
	}

	public static void queensCombination1(int ssf, int n, String ans, int row, int col) {

		if (row == n) {
			if (ssf == n) {
				System.out.println(ans);
			}
			return;
		}

		String yes = ans;
		String no = ans;

		int nr = row;
		int nc = col;

		if (col == n - 1) {
			nr = row + 1;
			nc = 0;

			yes = yes + "q" + "\n";
			no = no + "-" + "\n";
		} else {
			nc = col + 1;

			yes = yes + "q";
			no = no + "-";
		}

		queensCombination1(ssf, n, no, nr, nc);
		queensCombination1(ssf + 1, n, yes, nr, nc);
	}

}
