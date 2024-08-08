package com.javacode.dp;

import java.util.Arrays;

public class DynamicProgramming_3_22_28 {

	public static void main(String[] args) {

//		int arr[] = { 1, 5, 8, 9, 10, 17, 17, 20 };
//		rodCuttingTabulation(arr);
//
//		int memo[] = new int[arr.length + 1];
//		Arrays.fill(memo, -1);
//		int max = rodCuttingRecursive(arr, arr.length, memo);
//		System.out.println("Max: " + max);
//
//		for (int i = 1; i < memo.length; i++) {
//			System.out.print(memo[i] + " ");
//		}

//		String str = "abccbc";
//		minPalindromicPartitioningCut1(str);
//		minPalindromicPartitioningCut2(str);

//		int memo[] = new int[str.length()];
//		Arrays.fill(memo, -1);
//		int minCut = minPalindromicPartitioningCutRecursive(str, 0, str.length() - 1, memo);
//		System.out.println("Min Cut: " + minCut);

//		int arr[] = { 1, 2, 3, 4, 5, 6 };
//		int memo[][] = new int[arr.length + 1][arr.length + 1];
//		for (int i = 0; i < memo.length; i++) {
//			Arrays.fill(memo[i], -1);
//		}
//		int min = matrixChainMultiplicationRecursive(arr, 0, arr.length - 2, memo);
//		System.out.println("Min: " + min);
//		matrixChainMultiplicationTabulation(arr);

//		int arr[] = { 2, 3, 1, 5, 6, 4 };
//		int arr[] = { 3, 1, 5, 8 };
//		int narr[] = new int[arr.length + 2];
//		narr[0] = narr[arr.length + 1] = 1;
//		for (int i = 0; i < arr.length; i++) {
//			narr[i + 1] = arr[i];
//		}
//		int memo[][] = new int[arr.length + 1][arr.length + 1];
//		for (int i = 0; i < memo.length; i++) {
//			Arrays.fill(memo[i], -1);
//		}
//		int max = burstBallonRecursive(narr, 1, arr.length, memo);
//		System.out.println("Max: " + max);
//		burstBalloon(arr);

//		int arr[] = { 1, 3, 1, 4, 1, 5 };
//		minScoreOfPolygonTriangulationTabulation(arr);
//
//		int memo[][] = new int[arr.length + 1][arr.length + 1];
//		for (int i = 0; i < memo.length; i++) {
//			Arrays.fill(memo[i], -1);
//		}
//		int min = minScoreOfPolygonTriangulationRecursive(arr, 0, arr.length - 1, memo);
//		System.out.println("Min Score: " + min);

//		booleanParenthesization("TFTF", "&|^");

//		int arr1[] = { 1, 3, 4, 5, 6, 7, 8, 9, 11 };
//		int arr2[] = { 3, 6, 4, 8, 7, 3, 7, 4, 7 };

		int arr1[] = { 10, 20, 30, 40 };
		int arr2[] = { 3, 1, 2, 1 };

		optimalBinarySearchTree(arr1, arr2);
	}

	private static void optimalBinarySearchTree(int[] arr1, int[] arr2) {

		int dp[][] = new int[arr1.length][arr1.length];

		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 0) {
					dp[i][j] = arr2[i];
				} else if (g == 1) {
					int f1 = arr2[i];
					int f2 = arr2[j];
					dp[i][j] = Math.min(2 * f1 + f2, 2 * f2 + f1);
				} else {

					int min = Integer.MAX_VALUE;
					int sum = 0;
					for (int k = i; k <= j; k++) {
						if (k == i) {
							min = Math.min(min, dp[k + 1][j]);
						} else if (k == j) {
							min = Math.min(min, dp[i][k - 1]);
						} else {
							int tc = dp[i][k - 1] + dp[k + 1][j];
							min = Math.min(min, tc);
						}
						sum += arr2[k];
					}
					dp[i][j] = min + sum;
				}
			}
		}

		display(dp);
	}

	private static void booleanParenthesization(String str1, String str2) {

		char ch1[] = str1.toCharArray();
		char ch2[] = str2.toCharArray();

		int dp1[][] = new int[ch1.length][ch1.length];
		int dp2[][] = new int[ch1.length][ch1.length];

		for (int g = 0; g < dp1.length; g++) {
			for (int i = 0, j = g; j < dp1.length; i++, j++) {

				if (g == 0) {

					if (ch1[i] == 'T') {
						dp1[i][j] = 1;
						dp2[i][j] = 0;
					} else {
						dp1[i][j] = 0;
						dp2[i][j] = 1;
					}

				} else {
					for (int k = i; k < j; k++) {

						int ltc = dp1[i][k];
						int rtc = dp1[k + 1][j];
						int lfc = dp2[i][k];
						int rfc = dp2[k + 1][j];

						if (ch2[k] == '&') {

							dp1[i][j] += (ltc * rtc);
							dp2[i][j] += (ltc * rfc) + (lfc * rtc) + (lfc * rfc);

						} else if (ch2[k] == '|') {

							dp1[i][j] += (ltc * rfc) + (lfc * rtc) + (ltc * rtc);
							dp2[i][j] += (lfc * rfc);

						} else {

							dp1[i][j] += (lfc * rtc) + (ltc * rfc);
							dp2[i][j] += (ltc * rtc) + (lfc * rfc);

						}
					}
				}
			}
		}

		display(dp1);
		System.out.println("------------------");
		display(dp2);
	}

	private static int minScoreOfPolygonTriangulationRecursive(int arr[], int i, int j, int memo[][]) {

		if (j - i < 2) {
			return 0;
		}

		if (memo[i][j] != -1) {
			return memo[i][j];
		}
		int min = Integer.MAX_VALUE;
		for (int k = i + 1; k < j; k++) {

			int lc = minScoreOfPolygonTriangulationRecursive(arr, i, k, memo);
			int rc = minScoreOfPolygonTriangulationRecursive(arr, k, j, memo);
			int mc = arr[i] * arr[k] * arr[j];

			int tc = lc + rc + mc;
			min = Math.min(min, tc);
		}

		memo[i][j] = min;
		return min;
	}

	private static void minScoreOfPolygonTriangulationTabulation(int[] arr) {

		int dp[][] = new int[arr.length][arr.length];

		for (int g = 2; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 2) {
					dp[i][j] = arr[i] * arr[i + 1] * arr[j];
				} else {

					int min = Integer.MAX_VALUE;
					for (int k = i + 1; k < j; k++) {

						int lc = dp[i][k];
						int rc = dp[k][j];
						int mc = arr[i] * arr[k] * arr[j];

						int tc = mc + lc + rc;
						min = Math.min(min, tc);
					}

					dp[i][j] = min;
				}
			}
		}

		display(dp);
	}

	private static void burstBalloon(int[] arr) {

		int dp[][] = new int[arr.length][arr.length];

		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				int max = Integer.MIN_VALUE;
				for (int k = i; k <= j; k++) {

					int left = (k == i) ? 0 : dp[i][k - 1];
					int right = (k == j) ? 0 : dp[k + 1][j];

					int val = (i == 0 ? 1 : arr[i - 1]) * arr[k] * (j == arr.length - 1 ? 1 : arr[j + 1]);
					int tc = left + right + val;

					max = Math.max(max, tc);
				}

				dp[i][j] = max;
			}
		}

		display(dp);
	}

	private static int burstBallonRecursive(int arr[], int start, int end, int memo[][]) {

		if (start > end) {
			return 0;
		}

		if (memo[start][end] != -1) {
			return memo[start][end];
		}

		int max = Integer.MIN_VALUE;
		for (int k = start; k <= end; k++) {

			int lc = burstBallonRecursive(arr, start, k - 1, memo);
			int rc = burstBallonRecursive(arr, k + 1, end, memo);
			int mc = arr[start - 1] * arr[k] * arr[end + 1];

			int tc = lc + rc + mc;
			max = Math.max(max, tc);
		}

		memo[start][end] = max;

		return max;
	}

	private static void matrixChainMultiplicationTabulation(int[] arr) {

		int dp[][] = new int[arr.length - 1][arr.length - 1];

		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 0) {
					dp[i][j] = 0;
				} else if (g == 1) {
					dp[i][j] = arr[i] * arr[j] * arr[j + 1];
				} else {

					int min = Integer.MAX_VALUE;
					for (int k = i; k < j; k++) {
						int cost = dp[i][k] + dp[k + 1][j];
						int mc = arr[i] * arr[k + 1] * arr[j + 1];

						min = Math.min(min, cost + mc);
					}
					dp[i][j] = min;
				}
			}
		}

		display(dp);
	}

	private static int matrixChainMultiplicationRecursive(int[] arr, int i, int j, int memo[][]) {

		if (i == j) {
			return 0;
		}

		if (memo[i][j] != -1) {
			return memo[i][j];
		}

		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {

			int lc = matrixChainMultiplicationRecursive(arr, i, k, memo);
			int rc = matrixChainMultiplicationRecursive(arr, k + 1, j, memo);
			int mc = arr[i] * arr[k + 1] * arr[j + 1];

			min = Math.min(min, lc + rc + mc);
		}
		memo[i][j] = min;

		return min;
	}

	private static int minPalindromicPartitioningCutRecursive(String str, int start, int end, int memo[]) {

		if (start >= end || isPalindrome(str, start, end)) {
			return 0;
		}

		if (memo[start] != -1) {
			return memo[start];
		}

		int min = Integer.MAX_VALUE;
		for (int i = start; i < end; i++) {

			String sub = str.substring(start, i + 1);
			if (isPalindrome(sub, 0, sub.length() - 1)) {
				int rcut = minPalindromicPartitioningCutRecursive(str, i + 1, end, memo);
				int tcut = rcut + 1;
				min = Math.min(min, tcut);
			}
		}

		memo[start] = min;
		return min;
	}

	private static boolean isPalindrome(String str, int i, int j) {

		while (i < j) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	private static void minPalindromicPartitioningCut2(String str) {

		boolean dp1[][] = new boolean[str.length()][str.length()];
		for (int g = 0; g < dp1.length; g++) {
			for (int i = 0, j = g; j < dp1.length; i++, j++) {
				if (g == 0) {
					dp1[i][j] = true;
				} else if (g == 1) {
					dp1[i][j] = str.charAt(i) == str.charAt(j);
				} else {
					if (str.charAt(i) == str.charAt(j)) {
						dp1[i][j] = dp1[i + 1][j - 1];
					}
				}
			}
		}

		int dp[] = new int[str.length()];
		dp[0] = 0;

		for (int j = 1; j < dp.length; j++) {
			int min = Integer.MAX_VALUE;
			for (int i = j; i >= 1; i--) {
				if (dp1[i][j] == true) {
					min = Math.min(min, dp[i - 1]);
				}
			}
			dp[j] = min + 1;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void minPalindromicPartitioningCut1(String str) {

		boolean dp1[][] = new boolean[str.length()][str.length()];

		for (int g = 0; g < dp1.length; g++) {
			for (int i = 0, j = g; j < dp1.length; i++, j++) {

				if (g == 0) {
					dp1[i][j] = true;
				} else if (g == 1) {
					dp1[i][j] = str.charAt(i) == str.charAt(j);
				} else {
					if (str.charAt(i) == str.charAt(j)) {
						dp1[i][j] = dp1[i + 1][j - 1];
					}
				}
			}
		}

		int dp[][] = new int[str.length()][str.length()];

		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 0) {
					dp[i][j] = 0;
				} else if (g == 1) {
					dp[i][j] = str.charAt(i) == str.charAt(j) ? 0 : 1;
				} else {
					if (dp1[i][j] == true) {
						dp[i][j] = 0;
					} else {
						int min = Integer.MAX_VALUE;
						for (int k = i; k < j; k++) {
							int cut = dp[i][k] + dp[k + 1][j] + 1;
							min = Math.min(min, cut);
						}
						dp[i][j] = min;
					}
				}
			}
		}

		display(dp);
	}

	private static int rodCuttingRecursive(int arr[], int n, int memo[]) {

		if (n <= 0) {
			return 0;
		}

		if (memo[n] != -1) {
			return memo[n];
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int currVal = arr[i] + rodCuttingRecursive(arr, n - i - 1, memo);
			max = Math.max(max, currVal);
		}

		memo[n] = max;
		return max;
	}

	private static void rodCuttingTabulation(int[] arr1) {

		int arr[] = new int[arr1.length + 1];
		arr[0] = 0;
		for (int i = 0; i < arr1.length; i++) {
			arr[i + 1] = arr1[i];
		}
		int dp[] = new int[arr1.length + 1];
		dp[0] = 0;
		dp[1] = arr[1];

		for (int i = 2; i < dp.length; i++) {

			int li = 1;
			int ri = i - 1;
			int max = arr[i];
			while (li <= ri) {
				max = Math.max(max, dp[li] + dp[ri]);
				li++;
				ri--;
			}
			dp[i] = max;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();
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
