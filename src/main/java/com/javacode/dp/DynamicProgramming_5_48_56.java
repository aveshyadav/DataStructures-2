package com.javacode.dp;

public class DynamicProgramming_5_48_56 {

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

		maxDiffZerosOnes("11000010001");
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
