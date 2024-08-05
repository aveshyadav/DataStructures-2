package com.javacode.dp;

import java.util.LinkedList;
import java.util.Queue;

public class DynamicProgramming_1_1_10 {

	public static void main(String[] args) {

//		int arr[][] = { { 0, 1, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0 }, { 0, 1, 1, 1, 1, 0 }, { 0, 0, 1, 1, 1, 0 },
//				{ 1, 1, 1, 1, 1, 1 } };
//		largestSquareSubMatrix(arr);

//		int arr[] = { 3, 3, 0, 2, 1, 2, 4, 2, 0, 0 };
//		printAllPathsWithMinJumps(arr, 10);

//		int arr[][] = { { 2, 6, 1, 1, 3 }, { 9, 1, 1, 0, 5 }, { 0, 7, 5, 2, 0 }, { 4, 3, 0, 4, 7 }, { 2, 0, 1, 4, 1 } };
//		printAllMinCostPath(arr);

//		int arr[][] = { { 3, 2, 3, 1 }, { 2, 4, 6, 0 }, { 5, 0, 1, 3 }, { 9, 1, 5, 1 } };
//		printGoldMinePath(arr);

//		int arr[] = { 4, 2, 7, 1, 3 };
//		printTargetSumSubsetPath(arr, 10);

//		int wt[] = { 15, 14, 10, 45, 30 };
//		int val[] = { 2, 5, 1, 3, 4 };
//		printZeroOneKnapsackPath(wt, val, 7);

		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
//		longestIncreasingSubsequence(arr);
		printLongestIncreasingSubsequence(arr);
//		maxSumInlongestIncreasingSubsequence(arr);

		twoKeysKeboard(20);
	}

	private static void twoKeysKeboard(int n) {

	}

	private static void maxSumInlongestIncreasingSubsequence(int[] arr) {

		int dp[] = new int[arr.length];
		dp[0] = arr[0];

		for (int i = 1; i < arr.length; i++) {

			int max = 0;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + arr[i];
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void printLongestIncreasingSubsequence(int[] arr) {

		int dp[] = new int[arr.length];
		dp[0] = 1;

		for (int i = 1; i < dp.length; i++) {

			int max = 0;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
		}

		int max = 0;
		for (int i = 0; i < dp.length; i++) {
			max = Math.max(max, dp[i]);
			System.out.print(dp[i] + " ");
		}
		System.out.println();
		System.out.println("Ans: " + max);

		Queue<Pair3> queue = new LinkedList<>();
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] == max) {
				queue.add(new Pair3(i, arr[i] + ""));
			}
		}

		while (queue.size() > 0) {

			Pair3 mp = queue.remove();
			if (dp[mp.idx] == 1) {
				System.out.println(mp.path);
			}

			for (int i = 0; i < mp.idx; i++) {
				if (dp[mp.idx] - 1 == dp[i]) {
					queue.add(new Pair3(i, arr[i] + "-" + mp.path));
				}
			}
		}

	}

	private static class Pair3 {

		int idx;
		String path;

		public Pair3(int idx, String path) {
			this.idx = idx;
			this.path = path;
		}
	}

	private static void longestIncreasingSubsequence(int[] arr) {

		int dp[] = new int[arr.length];
		dp[0] = 1;

		for (int i = 1; i < dp.length; i++) {

			int max = 0;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void printZeroOneKnapsackPath(int[] wt, int[] val, int target) {

		int dp[][] = new int[val.length + 1][target + 1];

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {

				int max = dp[i - 1][j];
				if (j - val[i - 1] >= 0) {
					max = Math.max(max, dp[i - 1][j - val[i - 1]] + wt[i - 1]);
				}
				dp[i][j] = max;
			}
		}

		display(dp);

		Queue<Pair2> queue = new LinkedList<>();
		queue.add(new Pair2(dp.length - 1, dp[0].length - 1, dp[dp.length - 1][dp[0].length - 1] + "->"));

		while (queue.size() > 0) {

			Pair2 mp = queue.remove();
			if (mp.row == 0 || mp.col == 0) {
				System.out.println(mp.path);
			} else {

				int find = dp[mp.row][mp.col] - wt[mp.row - 1];
				if (mp.row - 1 >= 0 && dp[mp.row - 1][mp.col] == find) {
					queue.add(new Pair2(mp.row - 1, mp.col, mp.path));
				}
				if (mp.row - 1 >= 0 && mp.col - val[mp.row - 1] >= 0
						&& dp[mp.row - 1][mp.col - val[mp.row - 1]] == find) {
					queue.add(new Pair2(mp.row - 1, mp.col - val[mp.row - 1], mp.path + "," + wt[mp.row - 1]));
				}

			}
		}

	}

	private static void printTargetSumSubsetPath(int[] arr, int target) {

		boolean dp[][] = new boolean[arr.length + 1][target + 1];
		dp[0][0] = true;

		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (j == 0) {
					dp[i][j] = true;
				} else {
					dp[i][j] = dp[i - 1][j];
					if (j - arr[i - 1] >= 0) {
						dp[i][j] = dp[i][j] || dp[i - 1][j - arr[i - 1]];
					}
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}

		Queue<Pair2> queue = new LinkedList<>();
		queue.add(new Pair2(dp.length - 1, dp[0].length - 1, target + "->"));

		while (queue.size() > 0) {

			Pair2 mp = queue.remove();
			if (mp.row == 0 && mp.col == 0) {
				System.out.println(mp.path);
			} else {

				if (mp.row - 1 >= 0 && dp[mp.row - 1][mp.col] == true) {
					queue.add(new Pair2(mp.row - 1, mp.col, mp.path));
				}
				if (mp.row - 1 >= 0 && mp.col - arr[mp.row - 1] >= 0
						&& dp[mp.row - 1][mp.col - arr[mp.row - 1]] == true) {
					queue.add(new Pair2(mp.row - 1, mp.col - arr[mp.row - 1], mp.path + "," + arr[mp.row - 1]));
				}
			}
		}
	}

	private static void printGoldMinePath(int[][] arr) {

		display(arr);
		int dp[][] = new int[arr.length][arr[0].length];

		for (int j = dp[0].length - 1; j >= 0; j--) {
			for (int i = 0; i < dp.length; i++) {

				if (j == dp[0].length - 1) {
					dp[i][j] = arr[i][j];
				} else {
					int max = dp[i][j + 1];
					if (i > 0) {
						max = Math.max(max, dp[i - 1][j + 1]);
					}
					if (i < dp.length - 1) {
						max = Math.max(max, dp[i + 1][j + 1]);
					}
					dp[i][j] = max + arr[i][j];
				}
			}
		}

		display(dp);

		Queue<Pair2> queue = new LinkedList<>();
		for (int i = 0; i < dp.length; i++) {
			queue.add(new Pair2(i, 0, i + "->"));
		}

		while (queue.size() > 0) {

			Pair2 mp = queue.remove();
			if (mp.col == dp[0].length - 1) {
				System.out.println(mp.path);
			} else {

				for (int i = 0; i < dp.length; i++) {

					int find = dp[mp.row][mp.col] - arr[mp.row][mp.col];
					if (find == dp[i][mp.col + 1]) {

						String dir = "";
						if (mp.row == i) {
							dir = "R";
						} else if (mp.row - 1 == i) {
							dir = "RT";
						} else if (mp.row + 1 == i) {
							dir = "RD";
						}

						queue.add(new Pair2(i, mp.col + 1, mp.path + dir + ","));
					}
				}
			}
		}
	}

	private static void printAllMinCostPath(int[][] arr) {

		int dp[][] = new int[arr.length][arr[0].length];

		for (int i = dp.length - 1; i >= 0; i--) {
			for (int j = dp[0].length - 1; j >= 0; j--) {

				if (i == dp.length - 1 && j == dp[0].length - 1) {
					dp[i][j] = arr[i][j];
				} else if (i == dp.length - 1) {
					dp[i][j] = dp[i][j + 1] + arr[i][j];
				} else if (j == dp[0].length - 1) {
					dp[i][j] = dp[i + 1][j] + arr[i][j];
				} else {
					dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + arr[i][j];
				}
			}
		}

		display(dp);

		Queue<Pair2> queue = new LinkedList<>();
		queue.add(new Pair2(0, 0, ""));

		while (queue.size() > 0) {

			Pair2 mp = queue.remove();
			if (mp.row == dp.length - 1 && mp.col == dp[0].length - 1) {
				System.out.println(mp.path);
			}

			int right = Integer.MAX_VALUE;
			if (mp.col + 1 < dp[0].length) {
				right = dp[mp.row][mp.col + 1];
			}
			int down = Integer.MAX_VALUE;

			if (mp.row + 1 < dp.length) {
				down = dp[mp.row + 1][mp.col];
			}

			int min = Math.min(right, down);
			if (right == min) {
				queue.add(new Pair2(mp.row, mp.col + 1, mp.path + "R,"));
			}
			if (down == min) {
				queue.add(new Pair2(mp.row + 1, mp.col, mp.path + "D,"));
			}
		}
	}

	private static class Pair2 {

		int row;
		int col;
		String path;

		public Pair2(int row, int col, String path) {
			this.row = row;
			this.col = col;
			this.path = path;
		}

	}

	private static void printAllPathsWithMinJumps(int[] arr, int n) {

		Integer dp[] = new Integer[n];
		dp[n - 1] = 0;

		for (int i = arr.length - 2; i >= 0; i--) {

			int min = Integer.MAX_VALUE;
			for (int j = 1; j <= arr[i]; j++) {
				if (i + j < n && dp[i + j] != Integer.MAX_VALUE) {
					min = Math.min(min, dp[i + j]) + 1;
				}
			}
			dp[i] = min;
		}

		for (int i = 0; i < dp.length; i++) {
			if (dp[i] == Integer.MAX_VALUE) {
				dp[i] = 0;
			}
			System.out.print(dp[i] + " ");
		}

		Queue<Pair> queue = new LinkedList<>();
		queue.add(new Pair(0, arr[0], dp[0], "0"));

		while (queue.size() > 0) {

			Pair mp = queue.remove();
			if (mp.idx == arr.length - 1) {
				System.out.println(mp.path);
			}

			for (int i = mp.idx + 1; i <= mp.idx + mp.size; i++) {
				if (i < dp.length && dp[i] == mp.jumps - 1) {
					queue.add(new Pair(i, arr[i], dp[i], mp.path + "->" + i));
				}
			}
		}
	}

	private static class Pair {

		int idx;
		int size;
		int jumps;
		String path;

		public Pair(int idx, int size, int jumps, String path) {
			this.idx = idx;
			this.size = size;
			this.jumps = jumps;
			this.path = path;
		}
	}

	private static void largestSquareSubMatrix(int[][] arr) {

		int dp[][] = new int[arr.length][arr[0].length];

		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = arr[0].length - 1; j >= 0; j--) {

				if (i == arr.length - 1 || j == arr[0].length - 1) {
					dp[i][j] = arr[i][j];
				} else {
					if (arr[i][j] == 1) {
						int c1 = dp[i][j + 1];
						int c2 = dp[i + 1][j + 1];
						int c3 = dp[i + 1][j];

						int min = Math.min(Math.min(c1, c2), c3);
						dp[i][j] = min + 1;
					}
				}
			}
		}

		display(dp);
	}

	private static void display(int arr[][]) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
