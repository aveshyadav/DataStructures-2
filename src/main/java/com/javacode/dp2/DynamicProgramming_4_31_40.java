package com.javacode.dp2;

import java.util.Arrays;

public class DynamicProgramming_4_31_40 {

	public static void main(String[] args) {

//		shortestSupersequence("brute", "groot");

//		String s1 = "brootgroot";
//		String s2 = "brt";
//		int dp[][] = new int[s1.length() + 1][s2.length() + 1];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(distinctSubsequencesRec(s1, s2, s1.length(), s2.length(), dp));
//		distinctSubsequencesTab(s1, s2);

//		String s1 = "whgtdwhgtdg";
//		String s2 = "aswcfg";
//		int dp[][] = new int[s1.length() + 1][s2.length() + 1];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(editDistanceRec(s1, s2, s1.length(), s2.length(), dp));
//		editDistanceTab(s1, s2);

//		String s1 = "?ay";
//		String s2 = "ray";
//		Boolean dp[][] = new Boolean[s1.length() + 1][s2.length() + 1];
//		System.out.println(wildcardMatching(s1, s2, s1.length(), s2.length(), dp));
//		wildcardMatchingTab(s1, s2);

//		int arr[] = { 98, 101, 66, 72 };
//		maxProfitOneTrans(arr);

//		int arr[] = { 1, 2, 3, 4, 5, 6, 7 };
//		maxProfitMultiTrans(arr);

//		int arr[] = { 3, 3, 5, 0, 3, 1, 4 };
//		int dp[][][] = new int[arr.length][2][3];
//		for (int i = 0; i < dp.length; i++) {
//			for (int j = 0; j < dp[0].length; j++) {
//				Arrays.fill(dp[i][j], -1);
//			}
//		}
//		System.out.println(maxProfitTwoTransRec(arr, 0, 1, 2, dp));
//		maxProfitKTransTab(arr);

//		int arr[] = { 10, 15, 17, 20, 16, 18, 22, 20, 22, 20, 23, 25 };
//		int fee = 3;
//		maxProfitWithTransFee(arr, fee);

//		int arr[] = { 11, 6, 7, 19, 4, 1, 6, 18, 4 };
//		maxProfitMultiTransCoolDown(arr);
	}

	private static void maxProfitMultiTransCoolDown(int[] arr) {

		int bsp = -arr[0];
		int ssp = 0;
		int csp = 0;

		for (int i = 1; i < arr.length; i++) {

			int tssp = ssp;
			ssp = Math.max(ssp, arr[i] + bsp);
			bsp = Math.max(bsp, csp - arr[i]);
			csp = Math.max(csp, tssp);
		}

		System.out.println(ssp);
	}

	private static void maxProfitWithTransFee(int[] arr, int fee) {

		int bsp = -arr[0];
		int ssp = 0;

		for (int i = 1; i < arr.length; i++) {

			int tssp = ssp;
			ssp = Math.max(ssp, arr[i] + bsp - fee);
			bsp = Math.max(bsp, tssp - arr[i]);
		}

		System.out.println(ssp);
	}

	public static void maxProfitKTransTab(int[] arr) {

		int after[][] = new int[2][3];

		for (int i = arr.length - 1; i >= 0; i--) {

			int curr[][] = new int[2][3];
			for (int buy = 0; buy <= 1; buy++) {
				for (int cap = 1; cap <= 2; cap++) {

					if (buy == 1) {

						int v1 = -arr[i] + after[0][cap];
						int v2 = after[1][cap];

						curr[buy][cap] = Math.max(v1, v2);
					} else {

						int v1 = arr[i] + after[1][cap - 1];
						int v2 = after[0][cap];

						curr[buy][cap] = Math.max(v1, v2);
					}
				}
			}
			after = curr;
		}

		System.out.println(after[1][2]);
	}

	public static int maxProfitTwoTransRec(int[] arr, int i, int buy, int cap, int dp[][][]) {

		if (i == arr.length || cap == 0) {
			return 0;
		}

		if (dp[i][buy][cap] != -1) {
			return dp[i][buy][cap];
		}

		if (buy == 1) {

			int v1 = -arr[i] + maxProfitTwoTransRec(arr, i + 1, 0, cap, dp);
			int v2 = maxProfitTwoTransRec(arr, i + 1, 1, cap, dp);

			return dp[i][buy][cap] = Math.max(v1, v2);
		} else {

			int v1 = arr[i] + maxProfitTwoTransRec(arr, i + 1, 1, cap - 1, dp);
			int v2 = maxProfitTwoTransRec(arr, i + 1, 0, cap, dp);

			return dp[i][buy][cap] = Math.max(v1, v2);
		}
	}

	private static void maxProfitMultiTrans(int arr[]) {

		int min = arr[0];
		int profit = 0;

		for (int i = 1; i < arr.length; i++) {

			if (arr[i] < arr[i - 1]) {
				profit += arr[i - 1] - min;
				min = arr[i];
			}
		}

		if (arr[arr.length - 1] > min) {
			profit += arr[arr.length - 1] - min;
		}

		System.out.println(profit);
	}

	private static void maxProfitOneTrans(int arr[]) {

		int min = arr[0];
		int maxProfit = 0;

		for (int i = 1; i < arr.length; i++) {

			maxProfit = Math.max(maxProfit, arr[i] - min);
			min = Math.min(min, arr[i]);
		}

		System.out.println(maxProfit);
	}

	public static void wildcardMatchingTab(String s1, String s2) {

		Boolean dp[][] = new Boolean[s1.length() + 1][s2.length() + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0 && j == 0) {
					dp[i][j] = true;
				} else if (i == 0 || j == 0) {
					dp[i][j] = false;
				}

				else {

					boolean flag = false;
					if (s1.charAt(i - 1) == s2.charAt(j - 1) || (s1.charAt(i - 1) == '?')) {
						flag = dp[i - 1][j - 1];
					} else {
						if (s1.charAt(i - 1) == '*') {
							flag = flag || dp[i][j - 1] || dp[i - 1][j];
						}
					}
					dp[i][j] = flag;
				}
			}
		}

		System.out.println(dp[s1.length()][s2.length()]);
	}

	public static boolean wildcardMatching(String s1, String s2, int i, int j, Boolean dp[][]) {

		if (i == 0 && j == 0) {
			return true;
		} else if (i == 0 || j == 0) {
			return false;
		}

		if (dp[i][j] != null) {
			return dp[i][j];
		}

		boolean flag = false;
		if (s1.charAt(i - 1) == s2.charAt(j - 1) || (s1.charAt(i - 1) == '?')) {
			flag = wildcardMatching(s1, s2, i - 1, j - 1, dp);
		} else {
			if (s1.charAt(i - 1) == '*') {
				flag = flag || wildcardMatching(s1, s2, i, j - 1, dp) || wildcardMatching(s1, s2, i - 1, j, dp);
			}
		}
		dp[i][j] = flag;
		return flag;
	}

	public static void editDistanceTab(String s1, String s2) {

		int dp[][] = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else {

					if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {

						int rem = 1 + dp[i - 1][j];
						int ins = 1 + dp[i][j - 1];
						int rep = 1 + dp[i - 1][j - 1];

						dp[i][j] = Math.min(rem, Math.min(ins, rep));
					}
				}
			}
		}

		System.out.println(dp[s1.length()][s2.length()]);
	}

	public static int editDistanceRec(String s1, String s2, int i, int j, int dp[][]) {

		if (i == 0) {
			return j;
		}

		if (j == 0) {
			return i;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
			return dp[i][j] = editDistanceRec(s1, s2, i - 1, j - 1, dp);
		} else {

			int rem = 1 + editDistanceRec(s1, s2, i - 1, j, dp);
			int ins = 1 + editDistanceRec(s1, s2, i, j - 1, dp);
			int rep = 1 + editDistanceRec(s1, s2, i - 1, j - 1, dp);

			return dp[i][j] = Math.min(rem, Math.min(ins, rep));
		}
	}

	public static void distinctSubsequencesTab(String s1, String s2) {

		int dp[][] = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (j == 0) {
					dp[i][j] = 1;
				} else if (i == 0) {
					dp[i][j] = 0;
				}

				else {
					if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
					} else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}
		}

		System.out.println(dp[s1.length()][s2.length()]);

	}

	public static int distinctSubsequencesRec(String s1, String s2, int i, int j, int dp[][]) {

		if (j == 0) {
			return 1;
		}
		if (i == 0) {
			return 0;
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
			return dp[i][j] = distinctSubsequencesRec(s1, s2, i - 1, j - 1, dp)
					+ distinctSubsequencesRec(s1, s2, i - 1, j, dp);
		} else {
			return dp[i][j] = distinctSubsequencesRec(s1, s2, i - 1, j, dp);
		}
	}

	public static void shortestSupersequence(String s1, String s2) {

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
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}

		int i = 0;
		int j = 0;

		StringBuilder sb = new StringBuilder();
		while (i < dp.length - 1 && j < dp[0].length - 1) {

			if (s1.charAt(i) == s2.charAt(j)) {
				sb.append(s1.charAt(i));
				i++;
				j++;
			} else {
				if (dp[i][j] == dp[i + 1][j]) {
					sb.append(s1.charAt(i));
					i++;
				} else if (dp[i][j] == dp[i][j + 1]) {
					sb.append(s2.charAt(j));
					j++;
				}
			}
		}
		if (i < dp.length - 1) {
			sb.append(s1.charAt(i));
		} else if (j < dp[0].length - 1) {
			sb.append(s1.charAt(j));
		}
		System.out.println(sb.toString());
	}
}
