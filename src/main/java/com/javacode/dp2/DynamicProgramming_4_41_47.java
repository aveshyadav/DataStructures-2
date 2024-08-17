package com.javacode.dp2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DynamicProgramming_4_41_47 {

	public static void main(String[] args) {

//		int arr[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
//		longestIncreasingSubsequence(arr);
//
//		int dp[][] = new int[arr.length][arr.length + 1];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		System.out.println(longestIncreasingSubsequenceRec(arr, 0, -1, dp));

//		int arr[] = { 1, 16, 7, 8, 4 };
//		longestDivisibleSubset(arr);

//		String words[] = { "x", "xx", "y", "xyx" };
//		String words[] = { "a", "b", "ba", "bca", "bda", "bdca" };
//		longestStringChain(words);

//		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
//		longestBitonicSubsequence(arr);

//		int arr[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
//		countLongestIncreasingSubsequence(arr);

//		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
//		printLongestIncreasingSubsequence(arr);
	}

	private static void printLongestIncreasingSubsequence(int[] arr) {

		int dp[] = new int[arr.length];
		dp[0] = 1;
		int max = 0;

		for (int i = 1; i < dp.length; i++) {
			int maxi = 0;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					maxi = Math.max(maxi, dp[j]);
				}
			}
			dp[i] = maxi + 1;
			max = Math.max(max, dp[i]);
		}
		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();
		System.out.println("Ans: " + max);

		Queue<Pair> queue = new LinkedList<>();
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] == max) {
				queue.add(new Pair(i, arr[i] + ""));
			}
		}

		while (queue.size() > 0) {

			Pair mp = queue.remove();
			if (mp.idx == 0) {
				System.out.println(mp.path);
			}

			for (int i = 0; i < mp.idx; i++) {
				if (dp[i] == dp[mp.idx] - 1) {
					queue.add(new Pair(i, arr[i] + "->" + mp.path));
				}
			}
		}
	}

	private static class Pair {

		int idx;
		String path;

		public Pair(int idx, String path) {
			this.idx = idx;
			this.path = path;
		}
	}

	private static void countLongestIncreasingSubsequence(int arr[]) {

		int dp[] = new int[arr.length];
		dp[0] = 1;
		int max = 0;

		for (int i = 1; i < dp.length; i++) {
			int maxi = 0;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					maxi = Math.max(maxi, dp[j]);
				}
			}
			dp[i] = maxi + 1;
			max = Math.max(max, dp[i]);
		}

		int count = 0;
		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
			if (dp[i] == max) {
				count++;
			}
		}
		System.out.println();
		System.out.println(count);
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
		dp2[dp2.length - 1] = 1;

		for (int i = arr.length - 2; i >= 0; i--) {
			int max = 0;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					max = Math.max(max, dp2[j]);
				}
			}
			dp2[i] = max + 1;
		}

		int max = 0;
		for (int i = 0; i < dp1.length; i++) {
			System.out.println(dp1[i] + " " + dp2[i]);
			max = Math.max(max, dp1[i] + dp2[i]);
		}
		System.out.println("LBS: " + (max - 1));
	}

	private static void longestStringChain(String words[]) {

		int dp[] = new int[words.length];
		dp[0] = 1;

		for (int i = 1; i < dp.length; i++) {

			int max = 0;
			for (int j = 0; j < i; j++) {
				if (words[i].length() == words[j].length() + 1) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();
	}

	private static void longestDivisibleSubset(int arr[]) {

		Arrays.sort(arr);

		int dp[] = new int[arr.length];
		dp[0] = 1;

		for (int i = 1; i < arr.length; i++) {

			int max = 0;
			for (int j = 0; j < i; j++) {
				if (arr[i] % arr[j] == 0) {
					max = Math.max(max, dp[j]);
				}
			}
			dp[i] = max + 1;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();
	}

	private static int longestIncreasingSubsequenceRec(int arr[], int i, int prev, int dp[][]) {

		if (i == arr.length) {
			return 0;
		}

		if (dp[i][prev + 1] != -1) {
			return dp[i][prev + 1];
		}

		int l1 = longestIncreasingSubsequenceRec(arr, i + 1, prev, dp);

		int l2 = 0;
		if (prev == -1 || arr[i] > arr[prev]) {
			l2 = 1 + longestIncreasingSubsequenceRec(arr, i + 1, i, dp);
		}

		return dp[i][prev + 1] = Math.max(l1, l2);
	}

	private static void longestIncreasingSubsequence(int arr[]) {

		int dp[] = new int[arr.length];
		dp[0] = 1;

		for (int i = 1; i < arr.length; i++) {

			int maxi = 0;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					maxi = Math.max(maxi, dp[j]);
				}
			}

			dp[i] = maxi + 1;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();
	}

}
