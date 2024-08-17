package com.javacode.dp2;

import java.util.Arrays;

public class DynamicProgramming_3_19_30 {

	public static void main(String[] args) {

//		int cap = 5;
//		int wt[] = { 1, 2, 4, 5 };
//		int val[] = { 5, 4, 8, 6 };
//		int dp[][] = new int[wt.length][cap + 1];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(zeroOneKnapsackRec(val, wt, wt.length - 1, cap, dp));
//		zeroOneKnapsackTab(val, wt, cap);

//		int tar = 4;
//		int arr[] = { 12, 1, 3 };
//		int dp[][] = new int[arr.length][tar + 1];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(minimumElementsRec(arr, arr.length - 1, tar, dp));
//		minimumElementsTab(arr, tar);

//		int tar = 4;
//		int arr[] = { 1, 2, 3 };
//		int dp[][] = new int[arr.length][tar + 1];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(countWaysToMakeChangeRec(arr, arr.length - 1, tar, dp));
//		countWaysToMakeChangeTab(arr, tar);

//		int cap = 15;
//		int wt[] = { 5, 10, 20 };
//		int val[] = { 7, 2, 4 };
//		int dp[][] = new int[wt.length][cap + 1];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(unboundedKnapsackRec(wt, val, wt.length - 1, cap, dp));
//		unboundedKnapsackTab(wt, val, cap);

		int n = 8;
		int arr[] = { 3, 5, 8, 9, 10, 17, 17, 20 };
		int dp[][] = new int[arr.length][n + 1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(rodCuttingRec(arr, arr.length - 1, n, dp));
		rodCuttingTab(arr, n);

//		longestCommonSubsequence("abcd", "aebd");
//		printLongestCommonSubsequence("abcd", "abed");

//		longestCommonSubstring("abcjklp", "acjkp");
//		longestPalindromicSubsequence("abkccbc");

//		minInsertiontToMakeStringPalindrome("abcaa");
//		minOperationToConvertS1ToS2("abcd", "anc");
	}

	private static void minOperationToConvertS1ToS2(String s1, String s2) {

		int dp[][] = longestCommonSubsequence(s1, s2);
		int lcs = dp[0][0];

		int ans = (s1.length() - lcs) + (s2.length() - lcs);
		System.out.println(ans);
	}

	private static void minInsertiontToMakeStringPalindrome(String str) {

		int dp[][] = longestPalindromicSubsequence(str);
		int ans = str.length() - dp[0][dp[0].length - 1];

		System.out.println(ans);
	}

	private static int[][] longestPalindromicSubsequence(String str) {

		int dp[][] = new int[str.length()][str.length()];

		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 0) {
					dp[i][j] = 1;
				} else if (g == 1) {
					dp[i][j] = str.charAt(i) == str.charAt(j) ? 2 : 1;
				} else {

					if (str.charAt(i) == str.charAt(j)) {
						dp[i][j] = dp[i + 1][j - 1] + 2;
					} else {
						dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
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

		return dp;
	}

	private static void longestCommonSubstring(String s1, String s2) {

		int dp[][] = new int[s1.length()][s2.length()];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (s1.charAt(i) == s2.charAt(j)) {
					if (i == 0 && j == 0) {
						dp[i][j] = 1;
					} else {
						dp[i][j] = dp[i - 1][j - 1] + 1;
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

	private static void printLongestCommonSubsequence(String s1, String s2) {

		int dp[][] = longestCommonSubsequence(s1, s2);

		int i = 0;
		int j = 0;

		StringBuilder sb = new StringBuilder();

		while (i < s1.length() && j < s2.length()) {

			if (s1.charAt(i) == s2.charAt(j)) {
				sb.append(s1.charAt(i));
				i++;
				j++;
			} else {
				if (dp[i][j] == dp[i + 1][j]) {
					i++;
				} else if (dp[i][j] == dp[i][j + 1]) {
					j++;
				}
			}
		}

		System.out.println(sb.toString());
	}

	private static int[][] longestCommonSubsequence(String s1, String s2) {

		int dp[][] = new int[s1.length() + 1][s2.length() + 1];

		for (int i = dp.length - 2; i >= 0; i--) {
			for (int j = dp[0].length - 2; j >= 0; j--) {

				if (s1.charAt(i) == s2.charAt(j)) {
					dp[i][j] = dp[i + 1][j + 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j + 1], dp[i + 1][j]);
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}

		return dp;
	}

	private static void rodCuttingTab(int arr[], int n) {

		int prev[] = new int[n + 1];
		for (int i = 0; i < arr.length; i++) {

			int curr[] = new int[n + 1];
			for (int j = 0; j <= n; j++) {

				if (i == 0) {
					curr[j] = arr[0] * j;

				} else {
					int m1 = prev[j];
					int m2 = 0;
					int rodLength = i + 1;
					if (j >= rodLength) {
						m2 = arr[i] + curr[j - rodLength];
					}

					curr[j] = Math.max(m1, m2);
				}
			}
			prev = curr;
		}

		System.out.println(prev[n]);

		int dp[] = new int[n];
		dp[0] = arr[0];

		for (int i = 1; i < arr.length; i++) {

			int li = 0;
			int ri = i - 1;

			int max = 0;
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
	}

	private static int rodCuttingRec(int arr[], int i, int j, int dp[][]) {

		if (i == 0) {
			return arr[0] * j;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int m1 = rodCuttingRec(arr, i - 1, j, dp);
		int m2 = 0;
		int rodLength = i + 1;
		if (j >= rodLength) {
			m2 = arr[i] + rodCuttingRec(arr, i, j - rodLength, dp);
		}

		return dp[i][j] = Math.max(m1, m2);
	}

	private static void unboundedKnapsackTab(int wt[], int val[], int tar) {

		int dp[][] = new int[wt.length][tar + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0) {
					if (j >= wt[0]) {
						dp[i][j] = (j / wt[0]) * val[0];
					}
				} else {
					int p1 = dp[i - 1][j];
					int p2 = 0;
					if (j - wt[i] >= 0) {
						p2 = val[i] + dp[i][j - wt[i]];
					}

					dp[i][j] = Math.max(p1, p2);
				}
			}
		}
		System.out.println(dp[wt.length - 1][tar]);
	}

	private static int unboundedKnapsackRec(int wt[], int val[], int i, int j, int dp[][]) {

		if (i == 0) {
			if (j >= wt[0]) {
				return (j / wt[0]) * val[0];
			}
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int p1 = unboundedKnapsackRec(wt, val, i - 1, j, dp);
		int p2 = 0;
		if (j - wt[i] >= 0) {
			p2 = val[i] + unboundedKnapsackRec(wt, val, i, j - wt[i], dp);
		}

		return dp[i][j] = Math.max(p1, p2);
	}

	private static void countWaysToMakeChangeTab(int arr[], int tar) {

		int dp[][] = new int[arr.length][tar + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j <= tar; j++) {

				if (i == 0) {
					if (j % arr[0] == 0) {
						dp[i][j] = 1;
					}
				} else {

					int w1 = dp[i - 1][j];
					int w2 = 0;
					if (j - arr[i] >= 0) {
						w2 = dp[i][j - arr[i]];
					}

					dp[i][j] = w1 + w2;
				}
			}
		}

		System.out.println(dp[arr.length - 1][tar]);

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j <= tar; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int countWaysToMakeChangeRec(int[] arr, int i, int j, int dp[][]) {

		if (i == 0) {
			if (j % arr[0] == 0) {
				return 1;
			}
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int w1 = countWaysToMakeChangeRec(arr, i - 1, j, dp);
		int w2 = 0;
		if (j - arr[i] >= 0) {
			w2 = countWaysToMakeChangeRec(arr, i, j - arr[i], dp);
		}

		return dp[i][j] = w1 + w2;
	}

	private static void minimumElementsTab(int arr[], int tar) {

		int dp[][] = new int[arr.length][tar + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j <= tar; j++) {

				if (i == 0) {
					if (j % arr[0] == 0) {
						dp[i][j] = j / arr[0];
					} else {
						dp[i][j] = Integer.MAX_VALUE;
					}

				} else {
					int m1 = dp[i - 1][j];

					int m2 = Integer.MAX_VALUE;
					if (j - arr[i] >= 0) {
						m2 = 1 + dp[i][j - arr[i]];
					}

					dp[i][j] = Math.min(m1, m2);
				}
			}
		}

		System.out.println(dp[arr.length - 1][tar]);
	}

	private static int minimumElementsRec(int arr[], int i, int j, int dp[][]) {

		if (i == 0) {
			if (j % arr[0] == 0) {
				return j / arr[0];
			}
			return Integer.MAX_VALUE;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int m1 = minimumElementsRec(arr, i - 1, j, dp);

		int m2 = Integer.MAX_VALUE;
		if (j - arr[i] >= 0) {
			m2 = 1 + minimumElementsRec(arr, i, j - arr[i], dp);
		}

		return dp[i][j] = Math.min(m1, m2);
	}

	private static void zeroOneKnapsackTab(int val[], int wt[], int cap) {

		int dp[][] = new int[wt.length][cap + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0) {
					if (wt[i] <= j) {
						dp[i][j] = val[i];
					}
				} else {

					int s1 = dp[i - 1][j];
					int s2 = 0;
					if (j - wt[i] >= 0) {
						s2 = val[i] + dp[i - 1][j - wt[i]];
					}

					dp[i][j] = Math.max(s1, s2);
				}
			}
		}

		System.out.println(dp[wt.length - 1][cap]);

	}

	private static int zeroOneKnapsackRec(int val[], int wt[], int i, int j, int dp[][]) {

		if (i == 0) {
			if (wt[i] <= j) {
				return val[i];
			}
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int s1 = zeroOneKnapsackRec(val, wt, i - 1, j, dp);
		int s2 = 0;
		if (j - wt[i] >= 0) {
			s2 = val[i] + zeroOneKnapsackRec(val, wt, i - 1, j - wt[i], dp);
		}

		return dp[i][j] = Math.max(s1, s2);
	}

}
