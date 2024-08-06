package com.javacode.dp;

import java.util.Arrays;

public class DynamicProgramming_2_11_21 {

	public static void main(String[] args) {

//		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
//		longestBitonicSubsequence(arr);

//		int arr[][] = { { 10, 20 }, { 2, 7 }, { 8, 15 }, { 17, 3 }, { 21, 40 }, { 50, 4 }, { 41, 57 }, { 60, 80 },
//				{ 80, 90 }, { 1, 30 } };
//		maxNonOverlappingBridges(arr);

//		int arr[][] = { { 17, 5 }, { 26, 18 }, { 25, 34 }, { 48, 84 }, { 63, 72 }, { 42, 86 }, { 9, 55 }, { 4, 70 },
//				{ 21, 45 }, { 68, 76 }, { 58, 51 } };
//		countMaxRussianDollEnvelopes(arr);

//		perfectSqaures(11);
//		printCatalanNumbers(8);
//		countNumberOfBST(8);
		countValleysAndMountain(8);

	}

	private static void countValleysAndMountain(int n) {

		int dp[] = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i < dp.length; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - j - 1];
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void countNumberOfBST(int n) {

		int dp[] = new int[n + 1];
		dp[0] = dp[1] = 1;

		for (int i = 2; i < dp.length; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - j - 1];
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void printCatalanNumbers(int n) {

		int dp[] = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i < dp.length; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - j - 1];
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void perfectSqaures(int n) {

		int dp[] = new int[n + 1];
		dp[0] = 0;
		dp[1] = 1;

		for (int i = 1; i < dp.length; i++) {

			int min = Integer.MAX_VALUE;
			for (int j = 1; j * j <= i; j++) {
				int rem = i - (j * j);
				min = Math.min(min, dp[rem]);
			}
			dp[i] = min + 1;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void countMaxRussianDollEnvelopes(int[][] arr) {

		Pair[] parr = new Pair[arr.length];
		for (int i = 0; i < arr.length; i++) {
			parr[i] = new Pair(arr[i][0], arr[i][1]);
		}
		Arrays.sort(parr);

		int dp[] = new int[arr.length];
		dp[0] = 1;
		int ans = 1;

		for (int i = 1; i < arr.length; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (parr[i].second > parr[j].second) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
			ans = Math.max(ans, dp[i]);
		}

		System.out.println("Ans: " + ans);
		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void maxNonOverlappingBridges(int[][] arr) {

		Pair[] parr = new Pair[arr.length];
		for (int i = 0; i < arr.length; i++) {
			parr[i] = new Pair(arr[i][0], arr[i][1]);
		}
		Arrays.sort(parr);

		int dp[] = new int[arr.length];
		dp[0] = 1;
		int ans = 1;

		for (int i = 1; i < parr.length; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (parr[i].second > parr[j].second) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
			ans = Math.max(ans, dp[i]);
		}

		System.out.println("Ans: " + ans);
		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static class Pair implements Comparable<Pair> {

		int first;
		int second;

		public Pair(int first, int second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public int compareTo(Pair o) {
			return this.first - o.first;
		}
	}

	private static void longestBitonicSubsequence(int[] arr) {

		int dp1[] = new int[arr.length];
		dp1[0] = 1;

		for (int i = 1; i < arr.length; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					max = Math.max(max, dp1[j]);
				}
			}
			dp1[i] = max + 1;
		}

		int dp2[] = new int[arr.length];
		dp2[arr.length - 1] = 1;

		for (int i = arr.length - 2; i >= 0; i--) {
			int max = 0;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					max = Math.max(max, dp2[j]);
				}
			}
			dp2[i] = max + 1;
		}

		int ans = 0;
		for (int i = 0; i < dp1.length; i++) {
			System.out.println(dp1[i] + " " + dp2[i]);
			ans = Math.max(ans, dp1[i] + dp2[i] - 1);
		}
		System.out.println("LongestBitonicSubsequence: " + ans);
	}

}
