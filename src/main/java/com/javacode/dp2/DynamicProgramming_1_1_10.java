package com.javacode.dp2;

import java.util.Arrays;

public class DynamicProgramming_1_1_10 {

	public static void main(String[] args) {

//		int n = 10;
//		int dp[] = new int[n + 1];
//		System.out.println(fibonacciRecursive(n, dp));
//		fibonacciTabulation(n);

//		int n = 5;
//		int dp[] = new int[n + 1];
//		Arrays.fill(dp, -1);
//		System.out.println(climbingStairsRec(n, dp));
//		climbingStairsTab(n);

//		int arr[] = { 10, 20, 30, 10 };
//		int arr[] = { 7, 4, 4, 2, 6, 6, 3, 4 };
//		int dp[] = new int[arr.length];
//		Arrays.fill(dp, -1);
//		System.out.println(frogJumpsRec(arr, arr.length - 1, dp));
//		frogJumpstab(arr);

//		int k = 4;
//		int arr[] = { 40, 10, 20, 70, 80, 10, 20, 70, 80, 60 };
//		int dp[] = new int[arr.length];
//		Arrays.fill(dp, -1);
//		System.out.println(frogJumps2Rec(arr, arr.length - 1, k, dp));
//		frogJumps2Tab(arr, k);

//		int arr[] = { 1, 2, 3, 1, 3, 5, 8, 1, 9 };
//		int dp[] = new int[arr.length];
//		Arrays.fill(dp, -1);
//		System.out.println(houseRobberRec(arr, arr.length - 1, dp));
//		houseRobberTab(arr);

//		int arr[][] = { { 10, 40, 70 }, { 20, 50, 80 }, { 30, 60, 90 } };
//		int dp[][] = new int[arr.length][4];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(ninjaTrainingRec(arr, arr.length - 1, 3, dp));
//		ninjaTrainingTab(arr);

//		int m = 3;
//		int n = 7;
//		int dp[][] = new int[m][n];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(uniquePathsRec(m - 1, n - 1, dp));
//		uniquePathsTab(m, n);

//		int arr[][] = { { 0, 0, 0 }, { 0, -1, 0 }, { 0, 0, 0 } };
//		int dp[][] = new int[arr.length][arr[0].length];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(uniquePaths2Rec(arr, arr.length - 1, arr[0].length - 1, dp));
//		uniquePaths2Tab(arr);

//		int arr[][] = { { 1, 2, 3 }, { 4, 5, 4 }, { 7, 5, 9 } };
//		int dp[][] = new int[arr.length][arr[0].length];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(minSumPathRec(arr, arr.length - 1, arr[0].length - 1, dp));
//		minSumPathTab(arr);

	}

	private static void minSumPathTab(int arr[][]) {

		int prev[] = new int[arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			int curr[] = new int[arr[0].length];
			for (int j = 0; j < arr[0].length; j++) {

				if (i == 0 && j == 0) {
					curr[j] = arr[i][j];
				} else {

					int p1 = Integer.MAX_VALUE;
					if (i - 1 >= 0) {
						p1 = arr[i][j] + prev[j];
					}
					int p2 = Integer.MAX_VALUE;
					if (j - 1 >= 0) {
						p2 = arr[i][j] + curr[j - 1];
					}

					curr[j] = Math.min(p1, p2);
				}
			}
			prev = curr;
		}
		System.out.println(prev[arr[0].length - 1]);
	}

	private static int minSumPathRec(int arr[][], int i, int j, int dp[][]) {

		if (i < 0 || j < 0) {
			return (int) 1e9;
		}

		if (i == 0 && j == 0) {
			return arr[i][j];
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int p1 = arr[i][j] + minSumPathRec(arr, i - 1, j, dp);
		int p2 = arr[i][j] + minSumPathRec(arr, i, j - 1, dp);

		return dp[i][j] = Math.min(p1, p2);
	}

	private static void uniquePaths2Tab(int arr[][]) {

		int dp[][] = new int[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				if (i == 0 && j == 0) {
					dp[i][j] = 1;
				} else if (arr[i][j] != -1) {

					int p1 = 0;
					if (j - 1 >= 0) {
						p1 = dp[i][j - 1];
					}
					int p2 = 0;
					if (i - 1 >= 0) {
						p2 = dp[i - 1][j];
					}

					dp[i][j] = p1 + p2;
				}
			}
		}

		System.out.println(dp[arr.length - 1][arr[0].length - 1]);
	}

	private static int uniquePaths2Rec(int arr[][], int i, int j, int dp[][]) {

		if (i < 0 || j < 0 || arr[i][j] == -1) {
			return 0;
		}

		if (i == 0 && j == 0) {
			return 1;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int p1 = uniquePaths2Rec(arr, i - 1, j, dp);
		int p2 = uniquePaths2Rec(arr, i, j - 1, dp);

		return dp[i][j] = p1 + p2;
	}

	private static void uniquePathsTab(int m, int n) {

		int dp[][] = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {

				if (i == 0 && j == 0) {
					dp[0][0] = 1;
					continue;
				}

				int p1 = 0;
				if (j - 1 >= 0) {
					p1 = dp[i][j - 1];
				}
				int p2 = 0;
				if (i - 1 >= 0) {
					p2 = dp[i - 1][j];
				}

				dp[i][j] = p1 + p2;
			}
		}

		System.out.println(dp[m - 1][n - 1]);
	}

	private static int uniquePathsRec(int i, int j, int dp[][]) {

		if (i < 0 || j < 0) {
			return 0;
		}

		if (i == 0 && j == 0) {
			return 1;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int p1 = uniquePathsRec(i, j - 1, dp);
		int p2 = uniquePathsRec(i - 1, j, dp);

		return dp[i][j] = p1 + p2;
	}

	private static void ninjaTrainingTab(int arr[][]) {

		int prev[] = new int[4];

		prev[0] = Math.max(arr[0][1], arr[0][2]);
		prev[1] = Math.max(arr[0][0], arr[0][2]);
		prev[2] = Math.max(arr[0][0], arr[0][1]);
		prev[3] = Math.max(Math.max(arr[0][0], arr[0][1]), arr[0][2]);

		for (int i = 1; i < arr.length; i++) {

			int curr[] = new int[4];
			for (int j = 0; j < 4; j++) {

				int maxi = 0;
				for (int k = 0; k < 3; k++) {
					if (k != j) {
						int max = arr[i][k] + prev[k];
						maxi = Math.max(maxi, max);
					}
				}

				curr[j] = maxi;
			}
			prev = curr;
		}

		System.out.println(prev[3]);
	}

	private static int ninjaTrainingRec(int arr[][], int i, int j, int dp[][]) {

		if (i == 0) {
			if (j == 0) {
				return Math.max(arr[0][1], arr[0][2]);
			} else if (j == 1) {
				return Math.max(arr[0][0], arr[0][2]);
			} else if (j == 2) {
				return Math.max(arr[0][0], arr[0][1]);
			} else {
				return Math.max(Math.max(arr[0][0], arr[0][1]), arr[0][2]);
			}
		}

		int maxi = 0;
		for (int k = 0; k < 3; k++) {
			if (k != j) {
				int max = arr[i][k] + ninjaTrainingRec(arr, i - 1, k, dp);
				maxi = Math.max(maxi, max);
			}
		}

		return dp[i][j] = maxi;
	}

	private static void houseRobberTab(int arr[]) {

		int n1 = arr[0];
		int n2 = 0;

		for (int idx = 1; idx < arr.length; idx++) {

			int v1 = arr[idx] + n2;
			int v2 = n2;

			int curr = Math.max(v1, v2);
			n2 = n1;
			n1 = curr;
		}

		System.out.println(n1);
	}

	private static int houseRobberRec(int arr[], int idx, int dp[]) {

		if (idx < 0) {
			return 0;
		}

		if (idx == 0) {
			return arr[0];
		}

		if (dp[idx] != -1) {
			return dp[idx];
		}

		int v1 = arr[idx] + houseRobberRec(arr, idx - 2, dp);
		int v2 = houseRobberRec(arr, idx - 1, dp);

		return dp[idx] = Math.max(v1, v2);
	}

	private static void frogJumps2Tab(int[] arr, int k) {

		int dp[] = new int[arr.length];
		dp[0] = 0;

		for (int idx = 1; idx < dp.length; idx++) {

			int mini = Integer.MAX_VALUE;
			for (int i = 1; i <= k; i++) {
				if (idx - i >= 0) {
					mini = Math.min(mini, Math.abs(arr[idx] - arr[idx - i]) + dp[idx - i]);
				}
			}
			dp[idx] = mini;
		}

		System.out.println(dp[dp.length - 1]);
	}

	private static int frogJumps2Rec(int arr[], int idx, int k, int dp[]) {

		if (idx == 0) {
			return 0;
		}

		if (dp[idx] != -1) {
			return dp[idx];
		}

		int mini = Integer.MAX_VALUE;
		for (int i = 1; i <= k; i++) {
			if (idx - i >= 0) {
				mini = Math.min(mini, Math.abs(arr[idx] - arr[idx - i]) + frogJumps2Rec(arr, idx - i, k, dp));
			}
		}
		return dp[idx] = mini;
	}

	private static void frogJumpstab(int arr[]) {

		int n1 = 0;
		int n2 = 0;

		for (int i = 1; i < arr.length; i++) {

			int v1 = Integer.MAX_VALUE;
			if (i - 1 >= 0) {
				v1 = Math.abs(arr[i] - arr[i - 1]) + n1;
			}

			int v2 = Integer.MAX_VALUE;
			if (i - 2 >= 0) {
				v2 = Math.abs(arr[i] - arr[i - 2]) + n2;
			}
			int curr = Math.min(v1, v2);
			n2 = n1;
			n1 = curr;
		}

		System.out.println(n1);
	}

	private static int frogJumpsRec(int arr[], int idx, int dp[]) {

		if (idx == 0) {
			return 0;
		}

		if (dp[idx] != -1) {
			return dp[idx];
		}

		int v1 = Integer.MAX_VALUE;
		if (idx - 1 >= 0) {
			v1 = Math.abs(arr[idx] - arr[idx - 1]) + frogJumpsRec(arr, idx - 1, dp);
		}

		int v2 = Integer.MAX_VALUE;
		if (idx - 2 >= 0) {
			v2 = Math.abs(arr[idx] - arr[idx - 2]) + frogJumpsRec(arr, idx - 2, dp);
		}

		return dp[idx] = Math.min(v1, v2);
	}

	private static void climbingStairsTab(int n) {

		int n1 = 1;
		int n2 = 0;

		for (int i = 1; i <= n; i++) {

			int curr = n1 + n2;
			n2 = n1;
			n1 = curr;
		}

		System.out.println("Ans: " + n1);
	}

	private static int climbingStairsRec(int n, int dp[]) {

		if (n < 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}

		if (dp[n] != -1) {
			return dp[n];
		}

		int n1 = climbingStairsRec(n - 1, dp);
		int n2 = climbingStairsRec(n - 2, dp);

		return dp[n] = n1 + n2;
	}

	private static void fibonacciTabulation(int n) {

		int n1 = 0;
		int n2 = 0;

		for (int i = 0; i <= n; i++) {

			if (i == 1) {
				n2 = 1;
			} else {
				int cur = n1 + n2;
				n2 = n1;
				n1 = cur;
			}
		}

		System.out.println(n1);
	}

	private static int fibonacciRecursive(int n, int dp[]) {

		if (n <= 1) {
			return n;
		}

		if (dp[n] > 0) {
			return dp[n];
		}

		return dp[n] = fibonacciRecursive(n - 1, dp) + fibonacciRecursive(n - 2, dp);
	}

}
