package com.javacode.dp2;

import java.util.Arrays;

public class DynamicProgramming_2_11_18 {

	public static void main(String[] args) {

//		int arr[][] = { { 2 }, { 3, 4 }, { 6, 5, 7 }, { 4, 1, 8, 3 } };
//		int dp[][] = new int[arr.length][arr[arr.length - 1].length];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(minimumPathSumRec(arr, 0, 0, dp));
//		minimumPathSumTab(arr);

//		int arr[][] = { { 10, 10, 2, -13, 20, 4 }, { 1, -9, -81, 30, 2, 5 }, { 0, 10, 4, -79, 2, -10 },
//				{ 1, -5, 2, 20, -11, 4 } };
//		int dp[][] = new int[arr.length][arr[0].length];
//		int maxi = Integer.MIN_VALUE;
//		for (int i = 0; i < arr[0].length - 1; i++) {
//			for (int k = 0; k < dp.length; k++) {
//				Arrays.fill(dp[k], -1);
//			}
//			int max = getMaxPathSumRec(arr, arr.length - 1, i, dp);
//			maxi = Math.max(maxi, max);
//		}
//		System.out.println(maxi);
//		getMaxPathSumTab(arr);

//		int arr[][] = { { 2, 3, 1, 2 }, { 3, 4, 2, 2 }, { 5, 6, 3, 5 } };
//		int dp[][][] = new int[arr.length][arr[0].length][arr[0].length];
//		for (int i = 0; i < dp.length; i++) {
//			for (int j = 0; j < dp[i].length; j++) {
//				Arrays.fill(dp[i][j], -1);
//			}
//		}
//		System.out.println(maximumChocolates(arr, 0, 0, arr[0].length - 1, dp));
//		maximumChocolatesTab(arr);

//		int k = 10;
//		int arr[] = { 4, 3, 2, 1 };
//		Boolean dp[][] = new Boolean[arr.length][k + 1];
//		System.out.println(subsetSumToKRec(arr, arr.length - 1, k, dp));
//		subsetSumToKTab(arr, k);

//		int arr[] = { 3, 1, 1, 2, 2, 1 };
//		int arr[] = { 5, 6, 5, 11, 6 };
//		canPartitionEqualSubSetSum(arr);

//		int arr[] = { 3, 1, 5, 2, 8 };
//		minSubsetSumDifferenceTab(arr);

//		int tar = 5	;
//		int arr[] = { 1, 4, 4, 5 };
//		int arr[] = { 0, 0, 1 };
//		int dp[][] = new int[arr.length][tar + 1];
//		for (int i = 0; i < arr.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(countSubsetsWithSumKRec(arr, arr.length - 1, tar, dp));
//		countSubsetsWithSumKTab(arr, tar);

		int arr[] = { 3, 2, 2, 5, 1 };
		countPartitionsWithGivenDifference(arr, 1);
	}

	private static void countPartitionsWithGivenDifference(int arr[], int d) {

		int sum = 0;
		for (int i : arr) {
			sum += i;
		}

		if ((sum - d) < 0 || (sum - d) % 2 != 0) {
			System.out.println(0);
		} else {
			int tar = (sum - d) / 2;
			countSubsetsWithSumKTab(arr, tar);
		}
	}

	private static void countSubsetsWithSumKTab(int arr[], int tar) {

		int dp[][] = new int[arr.length][tar + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0) {
					if (arr[0] == j || j == 0) {
						dp[i][j] = 1;
					}
				} else {

					dp[i][j] = dp[i - 1][j];
					if (j - arr[i] >= 0) {
						dp[i][j] += dp[i - 1][j - arr[i]];
					}
				}
			}
		}

		System.out.println(dp[arr.length - 1][tar]);
	}

	private static int countSubsetsWithSumKRec(int arr[], int i, int tar, int dp[][]) {

		if (tar < 0) {
			return 0;
		}
		if (i == 0) {
			if (tar == 0 && arr[0] == 0) {
				return 2;
			}
			if (arr[0] == tar || tar == 0) {
				return 1;
			}
			return 0;
		}

		if (dp[i][tar] != -1) {
			return dp[i][tar];
		}

		int v1 = countSubsetsWithSumKRec(arr, i - 1, tar, dp);
		int v2 = countSubsetsWithSumKRec(arr, i - 1, tar - arr[i], dp);

		return dp[i][tar] = v1 + v2;
	}

	private static void minSubsetSumDifferenceTab(int arr[]) {

		int sum = 0;
		for (int i : arr) {
			sum += i;
		}

		boolean dp[][] = subsetSumToKTab(arr, sum);

		int min = Integer.MAX_VALUE;
		for (int i = 0; i <= sum; i++) {
			boolean f1 = dp[arr.length - 1][i];
			if (f1 == true) {

				int v1 = i;
				int v2 = sum - i;
				min = Math.min(min, Math.abs(v1 - v2));
			}

		}
		System.out.println(min);
	}

	public static void canPartitionEqualSubSetSum(int[] arr) {

		int sum = 0;
		for (int i : arr) {
			sum += i;
		}

		if (sum % 2 != 0) {
			System.out.println(false);
		} else {

			boolean dp[][] = subsetSumToKTab(arr, sum / 2);
			System.out.println(dp[arr.length - 1][sum / 2]);
		}
	}

	private static boolean[][] subsetSumToKTab(int arr[], int j) {

		boolean dp[][] = new boolean[arr.length][j + 1];
		for (int i = 0; i <= j; i++) {
			if (arr[0] == i) {
				dp[0][i] = true;
			}
		}

		for (int i = 1; i < arr.length; i++) {
			for (int k = 0; k <= j; k++) {

				boolean f1 = dp[i - 1][k];
				boolean f2 = false;
				if (k - arr[i] >= 0) {
					f2 = dp[i - 1][k - arr[i]];
				}

				boolean ans = f1 || f2;
				dp[i][k] = ans;
			}
		}

		System.out.println(dp[arr.length - 1][j]);
		return dp;
	}

	private static boolean subsetSumToKRec(int arr[], int i, int k, Boolean dp[][]) {

		if (i == 0) {
			return arr[i] == k;
		}

		if (dp[i][k] != null) {
			return dp[i][k];
		}

		boolean f1 = subsetSumToKRec(arr, i - 1, k, dp);
		boolean f2 = subsetSumToKRec(arr, i - 1, k - arr[i], dp);

		boolean ans = f1 || f2;
		dp[i][k] = ans;

		return ans;
	}

	private static void maximumChocolatesTab(int arr[][]) {

		int dp[][][] = new int[arr.length][arr[0].length][arr[0].length];

		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j1 = arr[0].length - 1; j1 >= 0; j1--) {
				for (int j2 = 0; j2 < arr[0].length; j2++) {

					if (i == arr.length - 1) {
						if (j1 == j2) {
							dp[i][j1][j2] = arr[i][j1];
						} else {
							dp[i][j1][j2] = arr[i][j1] + arr[i][j2];
						}
					} else {

						int maxi = 0;
						for (int k = -1; k <= 1; k++) {
							for (int l = -1; l <= 1; l++) {

								if (j1 + k >= 0 && j2 + l >= 0 && j1 + k < arr[0].length && j2 + l < arr[0].length) {
									maxi = Math.max(maxi, dp[i + 1][j1 + k][j2 + l]);
								}
							}
						}

						int choco = arr[i][j1];
						if (j1 != j2) {
							choco += arr[i][j2];
						}

						dp[i][j1][j2] = maxi + choco;
					}

				}
			}
		}

		System.out.println(dp[0][0][arr[0].length - 1]);
	}

	private static int maximumChocolates(int arr[][], int i, int j1, int j2, int dp[][][]) {

		if (j1 < 0 || j2 < 0 || j1 == arr[0].length || j2 == arr[0].length) {
			return (int) -1e9;
		}

		if (i == arr.length - 1) {
			if (j1 == j2) {
				return arr[i][j1];
			} else {
				return arr[i][j1] + arr[i][j2];
			}
		}

		if (dp[i][j1][j2] != -1) {
			return dp[i][j1][j2];
		}

		int maxi = 0;
		for (int k = -1; k <= 1; k++) {
			for (int l = -1; l <= 1; l++) {
				maxi = Math.max(maxi, maximumChocolates(arr, i + 1, j1 + k, j2 + l, dp));
			}
		}

		int choco = arr[i][j1];
		if (j1 != j2) {
			choco += arr[i][j2];
		}

		return dp[i][j1][j2] = maxi + choco;
	}

	private static void getMaxPathSumTab(int arr[][]) {

		int dp[][] = new int[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				if (i == 0) {
					dp[i][j] = arr[i][j];
				} else {

					int v1 = arr[i][j] + dp[i - 1][j];
					int v2 = Integer.MIN_VALUE;
					if (j - 1 >= 0) {
						v2 = arr[i][j] + dp[i - 1][j - 1];
					}

					int v3 = Integer.MIN_VALUE;
					if (j + 1 < arr[0].length) {
						v3 = arr[i][j] + dp[i - 1][j + 1];
					}

					dp[i][j] = Math.max(v1, Math.max(v2, v3));
				}
			}
		}
		int maxi = 0;
		for (int i = 0; i < dp[0].length; i++) {
			maxi = Math.max(maxi, dp[arr.length - 1][i]);
		}
		System.out.println(maxi);
	}

	private static int getMaxPathSumRec(int arr[][], int i, int j, int dp[][]) {

		if (j < 0 || j == arr[0].length) {
			return (int) -1e9;
		}

		if (i == 0) {
			return arr[i][j];
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int v1 = arr[i][j] + getMaxPathSumRec(arr, i - 1, j, dp);
		int v2 = arr[i][j] + getMaxPathSumRec(arr, i - 1, j - 1, dp);
		int v3 = arr[i][j] + getMaxPathSumRec(arr, i - 1, j + 1, dp);

		return dp[i][j] = Math.max(v1, Math.max(v2, v3));
	}

	private static void minimumPathSumTab(int arr[][]) {

		int dp[][] = new int[arr.length][arr[arr.length - 1].length];

		for (int i = dp.length - 1; i >= 0; i--) {
			for (int j = i; j >= 0; j--) {

				if (i == arr.length - 1) {
					dp[i][j] = arr[i][j];
				} else {

					int p1 = arr[i][j] + dp[i + 1][j];
					int p2 = arr[i][j] + dp[i + 1][j + 1];

					dp[i][j] = Math.min(p1, p2);
				}
			}
		}
		System.out.println(dp[0][0]);
	}

	private static int minimumPathSumRec(int arr[][], int i, int j, int dp[][]) {

		if (i == arr.length - 1) {
			return arr[i][j];
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int p1 = arr[i][j] + minimumPathSumRec(arr, i + 1, j, dp);
		int p2 = arr[i][j] + minimumPathSumRec(arr, i + 1, j + 1, dp);

		return dp[i][j] = Math.min(p1, p2);
	}
}
