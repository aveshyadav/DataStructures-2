package com.javacode.dp;

public class DynamicProgramming_5_40_47 {

	public static void main(String[] args) {

//		String s1 = "ahellobye";
//		String s2 = "amezooe";
//
//		int min = editDistanceRecursive(s1, s2);
//		System.out.println("Min distance: " + min);
//
//		editDistanceTabulation(s1, s2);

//		String s1 = "pepcoder";
//		String s2 = "peerdcop";
//		System.out.println("Is Scramble: " + isScramble(s1, s2));

//		minASCIIDeleteSumDP("delete", "leet");
//		minCostToMakeStringsIdentical("abc#d", "a#ebd");

//		int arr[] = { 4, 3, -2, 6, -14, 7, -1, 4, 5, 7, -10, 2, 9, -10, -5, -9, 6, 1 };
//		int arr[] = { 4, 3, -2, 6, 7, -10, -10, 4, 5, 9, -3, 4, 7, -28, 2, 9, 3, 2, 11 };

//		maxSumSubArray(arr);
//		kConcatenationMaxSum(arr, 3);

		int arr[] = { 2, 3, 1, -7, 6, -5, -4, 4, 3, 3, 2, -9, -5, 6, 1, 2, 1, 4 };
		maxSumSubarraySizeK(arr, 4);
	}

	private static void maxSumSubarraySizeK(int[] arr, int k) {

		int dp[] = new int[arr.length];
		dp[0] = arr[0];
		int curr = arr[0];

		for (int i = 1; i < arr.length; i++) {

			if (curr > 0) {
				curr += arr[i];
			} else {
				curr = arr[i];
			}
			dp[i] = curr;
		}

		int sum = 0;
		for (int i = 0; i < k; i++) {
			sum += arr[i];
		}

		int ans = sum;
		for (int i = k; i < arr.length; i++) {

			sum += arr[i] - arr[i - k];
			int ms = sum;
			if (dp[i - k] > 0) {
				ms += dp[i - k];
			}
			ans = Math.max(ans, ms);
		}

		System.out.println("Ans: " + ans);
		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}

	}

	private static void kConcatenationMaxSum(int[] arr, int k) {

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		System.out.println("Sum: " + sum);

		if (k == 1) {
			maxSumSubArray(arr);
		} else {

			int narr[] = new int[arr.length * 2];
			for (int i = 0; i < arr.length; i++) {
				narr[i] = arr[i];
			}
			for (int i = 0; i < arr.length; i++) {
				narr[arr.length + i] = arr[i];
			}

			int ans = maxSumSubArray(narr);
			if (sum > 0) {
				ans = ans + (sum * (k - 2));
			}

			System.out.println("Ans: " + ans);
		}
	}

	private static int maxSumSubArray(int[] arr) {

		int ans = arr[0];
		int curr = arr[0];

		for (int i = 1; i < arr.length; i++) {

			if (curr > 0) {
				curr += arr[i];
			} else {
				curr = arr[i];
			}
			ans = Math.max(ans, curr);
		}

		System.out.println("Max Sum SubArray: " + ans);
		return ans;
	}

	private static void minCostToMakeStringsIdentical(String s1, String s2) {

		int dp[][] = new int[s1.length() + 1][s2.length() + 1];

		for (int i = dp.length - 1; i >= 0; i--) {
			for (int j = dp[0].length - 1; j >= 0; j--) {

				if (i == dp.length - 1 || j == dp[0].length - 1) {
					dp[i][j] = 0;
				} else {
					if (s1.charAt(i) == s2.charAt(j)) {
						dp[i][j] = dp[i + 1][j + 1] + 1;
					} else {
						dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
					}
				}
			}
		}

		int lcs = dp[0][0];
		int cost = s1.length() - lcs + s2.length() - lcs;

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("Min cost: " + cost);
	}

	private static void minASCIIDeleteSumDP(String s1, String s2) {

		int dp[][] = new int[s1.length() + 1][s2.length() + 1];

		for (int i = dp.length - 1; i >= 0; i--) {
			for (int j = dp[0].length - 1; j >= 0; j--) {

				if (i == dp.length - 1 && j == dp[0].length - 1) {
					dp[i][j] = 0;
				} else if (i == dp.length - 1) {
					dp[i][j] = s2.charAt(j) + dp[i][j + 1];
				} else if (j == dp[0].length - 1) {
					dp[i][j] = s1.charAt(i) + dp[i + 1][j];
				} else {
					if (s1.charAt(i) == s2.charAt(j)) {
						dp[i][j] = dp[i + 1][j + 1];
					} else {
						int a1 = s1.charAt(i) + dp[i + 1][j];
						int a2 = s2.charAt(j) + dp[i][j + 1];
						dp[i][j] = Math.min(a1, a2);
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

	private static boolean isScramble(String s1, String s2) {

		if (s1.equals(s2)) {
			return true;
		}

		for (int i = 1; i < s1.length(); i++) {

			if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))
					|| (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
							&& isScramble(s1.substring(i), s2.substring(0, s2.length() - i)))) {
				return true;
			}
		}

		return false;
	}

	private static void editDistanceTabulation(String s1, String s2) {

		int dp[][] = new int[s1.length() + 1][s2.length() + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0 && j == 0) {
					dp[i][j] = 0;
				} else if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else {
					if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
					}
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

	private static int editDistanceRecursive(String s1, String s2) {

		if (s1.length() == 0) {
			return s2.length();
		}

		if (s2.length() == 0) {
			return s1.length();
		}

		if (s1.equals(s2)) {
			return 0;
		}

		if (s1.charAt(0) == s2.charAt(0)) {
			return editDistanceRecursive(s1.substring(1), s2.substring(1));
		} else {

			int rm = editDistanceRecursive(s1.substring(1), s2);
			int rp = editDistanceRecursive(s1.substring(1), s2.substring(1));
			int is = editDistanceRecursive(s1, s2.substring(1));

			int tc = Math.min(Math.min(rm, rp), is) + 1;
			return tc;
		}
	}
}
