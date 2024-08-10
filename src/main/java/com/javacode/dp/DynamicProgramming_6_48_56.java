package com.javacode.dp;

import java.util.Arrays;

public class DynamicProgramming_6_48_56 {

	public static void main(String[] args) {

//		eggDroppingProblem(7, 3);

//		int arr[] = { 20, 30, 2, 10 };
//		optimalStrategyForGame(arr);

//		int arr[][] = { { 1, 1, 1, 0, -1 }, { 0, 0, 1, 0, 0 }, { 0, 0, 1, 0, 1 }, { 1, 0, 1, 0, 0 },
//				{ -1, 0, 1, 1, 1 } };

//		cherryPickupDown(arr, 0, 0, 0);
//		System.out.println("Max cherries: " + maxCherries);

//		int dp[][][] = new int[arr.length][arr.length][arr.length];
//		int res = cherryPickup2(arr, 0, 0, 0, dp);
//		System.out.println("Max cherries: " + res);

//		knightsProbability(3, 2, 0, 0);

//		int m = 20;
//		int board[] = { 6, 8, 12, 14, 16 };
//		int rev[] = { 5, 8, 5, 3, 1 };
//		int n = 3;
//		highwayBillBoardProblem1(m, board, rev, n);
//		highwayBillBoardProblem2(m, board, rev, n);

//		String s1 = "aaaabbbccd";
//		String s2 = "abcd";
//
//		int dp[][] = new int[s1.length()][s2.length()];
//		for (int i = 0; i < dp.length; i++) {
//			for (int j = 0; j < dp[0].length; j++) {
//				dp[i][j] = -1;
//			}
//		}
//
//		int res = distinctTransformation(s1, s2, 0, 0, dp);
//		System.out.println("Result: " + res);
//		distinctTransformationTab(s1, s2);

		letterCombination(3);

//		maxDiffZerosOnes("11000010001");
	}

	private static void maxDiffZerosOnes(String str) {

		int curr = 0;
		int ans = 0;

		for (int i = 0; i < str.length(); i++) {

			int val = str.charAt(i) == '1' ? -1 : 1;
			if (curr > 0) {
				curr += val;
			} else {
				curr = val;
			}
			ans = Math.max(ans, curr);
		}

		System.out.println("Max Diff: " + ans);
	}

	private static void letterCombination(int n) {

		int curr[][] = new int[4][3];
		int next[][] = new int[4][3];

		for (int i = 0; i < curr.length; i++) {
			Arrays.fill(curr[i], 1);
		}

		for (int k = 2; k <= n; k++) {

			for (int i = 0; i < curr.length; i++) {
				for (int j = 0; j < curr[0].length; j++) {

					if (i == 0 && j == 0) {
						next[i][j] = curr[i][j] + curr[i][j + 1] + curr[i + 1][j];
					} else if (i == 0 && j == 1) {
						next[i][j] = curr[i][j] + curr[i][j - 1] + curr[i + 1][j] + curr[i][j + 1];
					} else if (i == 0 && j == 2) {
						next[i][j] = curr[i][j] + curr[i][j - 1] + curr[i + 1][j];
					} else if (i == 1 && j == 0) {
						next[i][j] = curr[i][j] + curr[i - 1][j] + curr[i][j + 1] + curr[i + 1][j];
					} else if (i == 1 && j == 1) {
						next[i][j] = curr[i][j] + curr[i - 1][j] + curr[i][j + 1] + curr[i + 1][j] + curr[i][j - 1];
					} else if (i == 1 && j == 2) {
						next[i][j] = curr[i][j] + curr[i - 1][j] + curr[i + 1][j] + curr[i][j - 1];
					} else if (i == 2 && j == 0) {
						next[i][j] = curr[i][j] + curr[i - 1][j] + curr[i][j + 1];
					} else if (i == 2 && j == 1) {
						next[i][j] = curr[i][j] + curr[i - 1][j] + curr[i][j + 1] + curr[i + 1][j] + curr[i][j - 1];
					} else if (i == 2 && j == 2) {
						next[i][j] = curr[i][j] + curr[i - 1][j] + curr[i][j - 1];
					} else if (i == 3 && j == 1) {
						next[i][j] = curr[i][j] + curr[i - 1][j];
					}
				}
			}

			curr = next;
			next = new int[4][3];
		}

		int sum = 0;
		for (int i = 0; i < curr.length; i++) {
			for (int j = 0; j < curr[0].length; j++) {
				System.out.print(curr[i][j] + " ");
				sum += curr[i][j];
			}
			System.out.println();
		}
		System.out.println("Sum: " + sum);
	}

	private static void distinctTransformationTab(String s, String t) {

		int dp[][] = new int[t.length() + 1][s.length() + 1];

		for (int i = dp.length - 1; i >= 0; i--) {
			for (int j = dp[0].length - 1; j >= 0; j--) {

				if (i == dp.length - 1 && j == dp[0].length - 1) {
					dp[i][j] = 1;
				} else if (i == dp.length - 1) {
					dp[i][j] = 1;
				} else if (j == dp[0].length - 1) {
					dp[i][j] = 0;
				} else {

					if (s.charAt(j) == t.charAt(i)) {
						dp[i][j] = dp[i + 1][j + 1] + dp[i][j + 1];
					} else {
						dp[i][j] = dp[i][j + 1];
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
	}

	private static int distinctTransformation(String s, String t, int si, int ti, int[][] dp) {

		if (si == s.length()) {
			if (ti < t.length()) {
				return 0;
			} else {
				return 1;
			}
		} else if (ti == t.length()) {
			return 1;
		}

		if (dp[si][ti] != -1) {
			return dp[si][ti];
		}

		char c1 = s.charAt(si);
		char c2 = t.charAt(ti);

		int tw = 0;
		if (c1 == c2) {
			tw += distinctTransformation(s, t, si + 1, ti + 1, dp);
			tw += distinctTransformation(s, t, si + 1, ti, dp);
		} else {
			tw += distinctTransformation(s, t, si + 1, ti, dp);
		}

		dp[si][ti] = tw;
		return tw;
	}

	private static void highwayBillBoardProblem2(int m, int[] board, int[] rev, int n) {

		int dp[] = new int[m];
		dp[0] = 0;

		int idx = 0;
		for (int i = 1; i < dp.length; i++) {

			if (idx < board.length && board[idx] == i) {

				int v1 = dp[i - 1];
				int v2 = dp[i - n] + rev[idx];
				dp[i] = Math.max(v1, v2);
				idx++;
			} else {
				dp[i] = dp[i - 1];
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void highwayBillBoardProblem1(int m, int[] board, int[] rev, int n) {

		int dp[] = new int[board.length];
		dp[0] = rev[0];

		for (int i = 1; i < dp.length; i++) {

			int max = 0;
			for (int j = 0; j < i; j++) {
				if (board[i] - board[j] >= n)
					max = Math.max(max, dp[j]);
			}
			dp[i] = max + rev[i];
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void knightsProbability(int n, int k, int row, int col) {

		double curr[][] = new double[n][n];
		double next[][] = new double[n][n];

		curr[row][col] = 1;
		for (int d = 1; d <= k; d++) {

			for (int i = 0; i < curr.length; i++) {
				for (int j = 0; j < curr[0].length; j++) {

					int ni = 0;
					int nj = 0;

					ni = i - 2;
					nj = j + 1;
					if (isValid(n, ni, nj)) {
						next[ni][nj] += curr[i][j] / 8.0;
					}

					ni = i - 1;
					nj = j + 2;
					if (isValid(n, ni, nj)) {
						next[ni][nj] += curr[i][j] / 8.0;
					}

					ni = i + 1;
					nj = j + 2;
					if (isValid(n, ni, nj)) {
						next[ni][nj] += curr[i][j] / 8.0;
					}

					ni = i + 2;
					nj = j + 1;
					if (isValid(n, ni, nj)) {
						next[ni][nj] += curr[i][j] / 8.0;
					}

					ni = i + 2;
					nj = j - 1;
					if (isValid(n, ni, nj)) {
						next[ni][nj] += curr[i][j] / 8.0;
					}

					ni = i + 1;
					nj = j - 2;
					if (isValid(n, ni, nj)) {
						next[ni][nj] += curr[i][j] / 8.0;
					}

					ni = i - 1;
					nj = j - 2;
					if (isValid(n, ni, nj)) {
						next[ni][nj] += curr[i][j] / 8.0;
					}

					ni = i - 2;
					nj = j - 1;
					if (isValid(n, ni, nj)) {
						next[ni][nj] += curr[i][j] / 8.0;
					}

				}
			}

			curr = next;
			next = new double[n][n];
		}

		double ans = 0;
		for (int i = 0; i < curr.length; i++) {
			for (int j = 0; j < curr.length; j++) {
				ans += curr[i][j];
			}
		}

		System.out.println("Ans: " + ans);
	}

	private static boolean isValid(int n, int i, int j) {

		if (i < 0 || j < 0 || i >= n || j >= n) {
			return false;
		}
		return true;
	}

	private static int cherryPickup2(int[][] arr, int r1, int c1, int r2, int[][][] dp) {

		int c2 = r1 + c1 - r2;
		if (r1 == arr.length || c1 == arr[0].length || r2 == arr.length || c2 == arr[0].length || arr[r1][c1] == -1
				|| arr[r2][c2] == -1) {
			return Integer.MIN_VALUE;
		}

		if (r1 == arr.length - 1 && c1 == arr[0].length - 1) {
			return arr[r1][c1];
		}

		if (dp[r1][c1][r2] != 0) {
			return dp[r1][c1][r2];
		}

		int cherries = 0;
		if (r1 == r2 && c1 == c2) {
			cherries = arr[r1][c1];
		} else {
			cherries = arr[r1][c1] + arr[r2][c2];
		}

		int v1 = cherryPickup2(arr, r1 + 1, c1, r2 + 1, dp);
		int v2 = cherryPickup2(arr, r1, c1 + 1, r2, dp);
		int v3 = cherryPickup2(arr, r1 + 1, c1, r2, dp);
		int v4 = cherryPickup2(arr, r1, c1 + 1, r2 + 1, dp);

		cherries += Math.max(Math.max(v1, v2), Math.max(v3, v4));
		dp[r1][c1][r2] = cherries;
		return cherries;
	}

	private static int maxCherries = 0;

	private static void cherryPickupDown(int[][] arr, int i, int j, int count) {

		if (i == arr.length || j == arr[0].length || arr[i][j] == -1) {
			return;
		}

		if (i == arr.length - 1 && j == arr[0].length - 1) {
			helper(arr, i, j, count);
			return;
		}

		int cherries = arr[i][j];
		arr[i][j] = 0;

		cherryPickupDown(arr, i, j + 1, count + cherries);
		cherryPickupDown(arr, i + 1, j, count + cherries);

		arr[i][j] = cherries;
	}

	private static void helper(int arr[][], int i, int j, int count) {

		if (i < 0 || j < 0 || arr[i][j] == -1) {
			return;
		}

		if (i == 0 && j == 0) {
			maxCherries = Math.max(count, maxCherries);
		}

		int cherries = arr[i][j];
		arr[i][j] = 0;

		helper(arr, i, j - 1, count + cherries);
		helper(arr, i - 1, j, count + cherries);

		arr[i][j] = cherries;
	}

	private static void optimalStrategyForGame(int[] arr) {

		int dp[][] = new int[arr.length][arr.length];

		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 0) {
					dp[i][j] = arr[i];
				} else if (g == 1) {
					dp[i][j] = Math.max(arr[i], arr[j]);
				} else {
					int v1 = arr[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]);
					int v2 = arr[j] + Math.min(dp[i][j - 2], dp[i + 1][j - 1]);

					dp[i][j] = Math.max(v1, v2);
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static void eggDroppingProblem(int f, int e) {

		int dp[][] = new int[e + 1][f + 1];

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {

				if (i == 1) {
					dp[i][j] = j;
				} else if (j == 1) {
					dp[i][j] = 1;
				} else {

					int left = 0;
					int right = j - 1;

					int min = Integer.MAX_VALUE;
					while (right >= 0) {
						min = Math.min(Math.max(dp[i - 1][left], dp[i][right]), min);
						left++;
						right--;
					}
					dp[i][j] = min + 1;
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

}
