package com.javacode.dp2;

import java.util.Arrays;

public class DynamicProgramming_4_48_56 {

	public static void main(String[] args) {

//		int n = 8;
//		int arr[] = { 3, 5, 8, 9, 10, 17, 17, 20 };
//		int dp[][] = new int[arr.length][n + 1];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(rodCuttingRec(arr, arr.length - 1, n, dp));
//		rodCuttingTab(arr, n);

//		int arr[] = { 1, 2, 3, 4, 5, 6 };
//		int dp[][] = new int[arr.length][arr.length];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(mcmRec(arr, 0, arr.length - 2, dp));
//		mcmTab(arr);

//		int arr[] = { 3, 1, 5, 8 };
//		int arr[] = { 2, 3, 1, 5, 6, 4 };
//		int narr[] = new int[arr.length + 2];
//		narr[0] = narr[narr.length - 1] = 1;
//		for (int i = 0; i < arr.length; i++) {
//			narr[i + 1] = arr[i];
//		}
//		int dp[][] = new int[narr.length][narr.length];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(burstBallonRec(narr, 1, narr.length - 2, dp));
//		burstBallonTab(narr);

//		int n = 7;
//		int cuts[] = { 1, 3, 4, 5 };
//		Arrays.sort(cuts);
//		int narr[] = new int[cuts.length + 2];
//		narr[0] = 0;
//		narr[narr.length - 1] = n;
//		for (int i = 0; i < cuts.length; i++) {
//			narr[i + 1] = cuts[i];
//		}
//		int dp[][] = new int[narr.length][narr.length];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(minCostToCutStickRec(narr, 1, narr.length - 2, dp));
//		minCostToCutStickTab(narr);

//		String str = "abccbc";
//		int dp[] = new int[str.length()];
//		Arrays.fill(dp, -1);
//		System.out.println(minPalindromePartitionCutRec(str, 0, dp));
//		minPalindromePartitionCutTab(str);

//		int k = 3;
//		int arr[] = { 1, 15, 7, 9, 2, 5, 10 };
//		int dp[] = new int[arr.length + 1];
//		Arrays.fill(dp, -1);
//		System.out.println(partitionArrayForMaxSumRec(arr, 0, k, dp));
//		partitionArrayForMaxSumTab(arr, k);

		booleanParenthesization("TFTF", "&|^");
	}

	private static void booleanParenthesization(String s1, String s2) {

		int dp1[][] = new int[s1.length()][s1.length()];
		int dp2[][] = new int[s1.length()][s1.length()];

		for (int g = 0; g < dp1.length; g++) {
			for (int i = 0, j = g; j < dp1[0].length; i++, j++) {

				if (g == 0) {
					if (s1.charAt(i) == 'T') {
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

						if (s2.charAt(k) == '&') {

							dp1[i][j] += ltc * rtc;
							dp2[i][j] += (ltc * rfc) + (lfc * rtc) + (lfc * rfc);

						} else if (s2.charAt(k) == '|') {

							dp1[i][j] += (ltc * rfc) + (lfc * rtc) + (ltc * rtc);
							dp2[i][j] += lfc * rfc;

						} else {

							dp1[i][j] += (ltc * rfc) + (lfc * rtc);
							dp2[i][j] += (ltc * rtc) + (lfc * rfc);
						}
					}
				}
			}
		}

		display(dp1);
		System.out.println("--------------------");
		display(dp2);
	}

	private static void display(int arr[][]) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static void partitionArrayForMaxSumTab(int arr[], int k) {

		int dp[] = new int[arr.length + 1];

		for (int i = arr.length - 1; i >= 0; i--) {

			int maxSum = 0;
			int maxi = 0;
			for (int j = i; j < i + k && j < arr.length; j++) {

				maxi = Math.max(maxi, arr[j]);
				int len = j - i + 1;
				int val = len * maxi;
				int v2 = dp[j + 1];

				int mv = val + v2;
				maxSum = Math.max(maxSum, mv);
			}

			dp[i] = maxSum;

		}

		System.out.println(dp[0]);
	}

	private static int partitionArrayForMaxSumRec(int arr[], int i, int k, int dp[]) {

		if (i == arr.length) {
			return 0;
		}

		if (dp[i] != -1) {
			return dp[i];
		}

		int maxSum = 0;
		int maxi = 0;
		for (int j = i; j < i + k && j < arr.length; j++) {

			maxi = Math.max(maxi, arr[j]);
			int len = j - i + 1;
			int val = len * maxi;
			int v2 = partitionArrayForMaxSumRec(arr, j + 1, k, dp);

			int mv = val + v2;
			maxSum = Math.max(maxSum, mv);
		}

		return dp[i] = maxSum;
	}

	private static void minPalindromePartitionCutTab(String str) {

		int dp[] = new int[str.length()];
		for (int i = str.length() - 1; i >= 0; i--) {

			if (i == str.length() || isPalindrome(str.substring(i))) {
				continue;
			}

			int min = Integer.MAX_VALUE;
			for (int k = i; k < str.length() - 1; k++) {

				if (isPalindrome(str.substring(i, k + 1))) {
					int cost = 1 + dp[k + 1];
					min = Math.min(min, cost);
				}
			}

			dp[i] = min;
		}

		System.out.println(dp[0]);
	}

	private static int minPalindromePartitionCutRec(String str, int i, int dp[]) {

		if (i == str.length() || isPalindrome(str.substring(i))) {
			return 0;
		}

		if (dp[i] != -1) {
			return dp[i];
		}

		int min = Integer.MAX_VALUE;
		for (int k = i; k < str.length() - 1; k++) {

			if (isPalindrome(str.substring(i, k + 1))) {
				int cost = 1 + minPalindromePartitionCutRec(str, k + 1, dp);
				min = Math.min(min, cost);
			}
		}

		return dp[i] = min;
	}

	private static boolean isPalindrome(String str) {

		int i = 0;
		int j = str.length() - 1;

		while (i < j) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}

		return true;
	}

	private static void minCostToCutStickTab(int cuts[]) {

		int dp[][] = new int[cuts.length][cuts.length];

		for (int i = cuts.length - 1; i >= 1; i--) {
			for (int j = 1; j <= cuts.length - 2; j++) {

				if (i > j) {
					continue;
				} else {

					int min = Integer.MAX_VALUE;
					for (int k = i; k <= j; k++) {

						int lc = dp[i][k - 1];
						int rc = dp[k + 1][j];
						int mc = cuts[j + 1] - cuts[i - 1];

						int tc = lc + rc + mc;
						min = Math.min(min, tc);
					}
					dp[i][j] = min;
				}
			}
		}

		System.out.println(dp[1][cuts.length - 2]);
	}

	private static int minCostToCutStickRec(int cuts[], int i, int j, int dp[][]) {

		if (i > j) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int min = Integer.MAX_VALUE;
		for (int k = i; k <= j; k++) {

			int lc = minCostToCutStickRec(cuts, i, k - 1, dp);
			int rc = minCostToCutStickRec(cuts, k + 1, j, dp);
			int mc = cuts[j + 1] - cuts[i - 1];

			int tc = lc + rc + mc;
			min = Math.min(min, tc);
		}
		return dp[i][j] = min;
	}

	private static void burstBallonTab(int arr[]) {
		int dp[][] = new int[arr.length][arr.length];

		for (int i = arr.length - 1; i >= 1; i--) {
			for (int j = 1; j <= arr.length - 2; j++) {

				if (i > j) {
					continue;
				} else {

					int max = 0;
					for (int k = i; k <= j; k++) {

						int lc = dp[i][k - 1];
						int rc = dp[k + 1][j];
						int mc = arr[i - 1] * arr[k] * arr[j + 1];

						int tc = lc + mc + rc;
						max = Math.max(max, tc);
					}

					dp[i][j] = max;
				}
			}
		}

		System.out.println(dp[1][arr.length - 2]);
	}

	private static int burstBallonRec(int arr[], int i, int j, int dp[][]) {

		if (i > j) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int max = 0;
		for (int k = i; k <= j; k++) {

			int lc = burstBallonRec(arr, i, k - 1, dp);
			int rc = burstBallonRec(arr, k + 1, j, dp);
			int mc = arr[i - 1] * arr[k] * arr[j + 1];

			int tc = lc + mc + rc;
			max = Math.max(max, tc);
		}

		return dp[i][j] = max;
	}

	private static void mcmTab(int arr[]) {

		int dp[][] = new int[arr.length][arr.length];

		for (int i = arr.length - 1; i >= 0; i--) {
			for (int j = 1; j < arr.length - 1; j++) {

				if (i == j) {
					dp[i][j] = 0;
				} else {

					int min = Integer.MAX_VALUE;
					for (int k = i; k < j; k++) {

						int lc = dp[i][k];
						int rc = dp[k + 1][j];
						int mc = arr[i] * arr[k + 1] * arr[j + 1];

						int tc = lc + mc + rc;

						min = Math.min(min, tc);
					}
					dp[i][j] = min;
				}
			}
		}

		System.out.println(dp[0][arr.length - 2]);
	}

	private static int mcmRec(int arr[], int i, int j, int dp[][]) {

		if (i == j) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; k++) {

			int lc = mcmRec(arr, i, k, dp);
			int rc = mcmRec(arr, k + 1, j, dp);
			int mc = arr[i] * arr[k + 1] * arr[j + 1];

			int tc = lc + mc + rc;

			min = Math.min(min, tc);
		}

		return dp[i][j] = min;
	}

	private static void rodCuttingTab(int arr[], int n) {

		int dp[][] = new int[arr.length][n + 1];

		for (int i = 0; i < arr.length; i++) {
			for (int j = n; j >= 0; j--) {

				if (i == 0) {
					dp[i][j] = arr[0] * j;
				}

				else {
					int v1 = dp[i - 1][j];
					int rodLength = i + 1;
					int v2 = 0;
					if (j - rodLength >= 0) {
						v2 = arr[i] + dp[i][j - rodLength];
					}
					dp[i][j] = Math.max(v1, v2);
				}
			}
		}

		System.out.println(dp[arr.length - 1][n]);
	}

	private static int rodCuttingRec(int arr[], int i, int j, int dp[][]) {

		if (i == 0) {
			return arr[0] * j;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int v1 = rodCuttingRec(arr, i - 1, j, dp);
		int rodLength = i + 1;
		int v2 = 0;
		if (j - rodLength >= 0) {
			v2 = arr[i] + rodCuttingRec(arr, i, j - rodLength, dp);
		}
		return dp[i][j] = Math.max(v1, v2);
	}

}
