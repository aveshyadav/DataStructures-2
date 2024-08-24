package com.javacode.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import javafx.scene.layout.Priority;

public class DynamicProgramming_7_57_67 {

	public static void main(String[] args) {

//		int arr[] = { 3, 8, 1, 3, 2, 1, 8, 9, 0 };
//		maxSumOfTwoNonOverlappingSubarrays(arr, 2, 3);

//		int arr[] = { 5, 2, 1, 4, 3, 4, 2, 1, 3, 6, 3, 3, 6 };
//		maxSumOfThreeNonOverlappingSubarrays(arr, 3);

//		int arr[] = { 3, 2, 7, 9, 5, 4, 2, 8, 1, 11, 10, 8, 6 };
//		int dp[][] = new int[arr.length][3];
//		int sum = maxSumOfMNonOverlappingSubarraysOfSizeK(arr, 0, 2, 3, dp);
//		System.out.println("Sum: " + sum);

//		int arr[] = { 2, 5, 9, 12, 15, 18, 22, 26, 30, 34, 36, 38, 40, 41 };
//		arithmeticSlices1(arr);

		int arr[] = { 4, 2, 3, 5, 2, 6, 7, 9, 8, 11 };
		arithmeticSlices2(arr);

//		String str = "loves pep coding pepcoding ice cream icecream man go mango";
//		String ques = "pepcodinglovesmangoicecream";
//
//		Set<String> set = new HashSet<>();
//		String sarr[] = str.split("\\s+");
//		for (int i = 0; i < sarr.length; i++) {
//			set.add(sarr[i]);
//		}

//		wordBreakRecursive(ques, set, "");
//		wordBreakTabulation(ques, set);

//		int arr[] = { 1, 3, 2, 5, 2, 1 };
//		minTempleOffering(arr);

//		minInsertionsToMakePalindrome("abbcabda");

//		uglyNumbers(15);

//		int arr[] = { 3, 5, 7, 11 };
//		superUglyNumbers(arr, 13);
//		superUglyNumbers2(arr, 13);

//		findWaterInGlass(7, 3, 1);
	}

	private static void findWaterInGlass(int k, int r, int c) {

		double dp[][] = new double[r + 1][r + 1];
		dp[0][0] = k;

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (dp[i][j] > 1) {

					dp[i + 1][j] += (dp[i][j] - 1) / 2;
					dp[i + 1][j + 1] += (dp[i][j] - 1) / 2;

					dp[i][j] = 1;
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + "  ");
			}
			System.out.println();
		}
	}

	private static void superUglyNumbers2(int[] arr, int n) {

		int dp[] = new int[n];
		dp[0] = 1;

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			pq.add(new Pair(0, arr[i], arr[i]));
		}

		for (int i = 1; i < dp.length; i++) {

			Pair mp = pq.remove();
			dp[i] = mp.val;
			pq.add(new Pair(mp.idx + 1, mp.num * dp[mp.idx + 1], mp.num));

			while (pq.peek().val == mp.val) {
				Pair mp2 = pq.remove();
				pq.add(new Pair(mp2.idx + 1, mp2.num * dp[mp2.idx + 1], mp2.num));
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static class Pair implements Comparable<Pair> {

		int idx;
		int val;
		int num;

		public Pair(int idx, int val, int num) {
			this.idx = idx;
			this.val = val;
			this.num = num;
		}

		@Override
		public int compareTo(Pair o) {
			return this.val - o.val;
		}

	}

	private static void superUglyNumbers(int[] arr, int n) {

		int dp[] = new int[n];
		dp[0] = 1;
		int pf[] = new int[arr.length];

		for (int i = 1; i < dp.length; i++) {

			int min = Integer.MAX_VALUE;
			for (int j = 0; j < pf.length; j++) {
				min = Math.min(min, arr[j] * dp[pf[j]]);
			}

			dp[i] = min;

			for (int j = 0; j < pf.length; j++) {
				if (min == arr[j] * dp[pf[j]]) {
					pf[j]++;
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void uglyNumbers(int n) {

		int dp[] = new int[n];
		dp[0] = 1;

		int two = 0;
		int three = 0;
		int five = 0;

		for (int i = 1; i < dp.length; i++) {

			int v1 = 2 * dp[two];
			int v2 = 3 * dp[three];
			int v3 = 5 * dp[five];

			int min = Math.min(v1, Math.min(v2, v3));
			dp[i] = min;

			if (min == v1) {
				two++;
			}
			if (min == v2) {
				three++;
			}
			if (min == v3) {
				five++;
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void minInsertionsToMakePalindrome(String str) {

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

		int ans = str.length() - dp[0][str.length() - 1];
		System.out.println("Ans: " + ans);

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void minTempleOffering(int[] arr) {

		int dp[] = new int[arr.length];
		dp[0] = 1;

		for (int i = 1; i < dp.length; i++) {
			if (arr[i] > arr[i - 1]) {
				dp[i] = dp[i - 1] + 1;
			} else {
				dp[i] = 1;
			}
		}

		int dp2[] = new int[arr.length];
		dp2[arr.length - 1] = 1;

		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				dp2[i] = dp2[i + 1] + 1;
			} else {
				dp2[i] = 1;
			}
		}

		int ans = 0;
		for (int i = 0; i < dp.length; i++) {
			ans += Math.max(dp[i], dp2[i]);
		}
		System.out.println("Ans: " + ans);
	}

	private static void wordBreakTabulation(String ques, Set<String> set) {

		int dp[] = new int[ques.length()];

		for (int i = 0; i < dp.length; i++) {

			for (int j = 0; j <= i; j++) {
				String sub = ques.substring(j, i + 1);

				if (set.contains(sub)) {
					if (j - 1 >= 0) {
						dp[i] += dp[j - 1];
					} else {
						dp[i] += 1;
					}
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void wordBreakRecursive(String ques, Set<String> set, String ans) {

		if (ques.length() == 0) {
			System.out.println(ans);
			return;
		}

		for (int i = 0; i < ques.length(); i++) {

			String sub = ques.substring(0, i + 1);
			if (set.contains(sub)) {
				wordBreakRecursive(ques.substring(i + 1), set, ans + sub + " ");
			}
		}
	}

	private static void arithmeticSlices2(int[] arr) {

		List<HashMap<Integer, Integer>> list = new ArrayList<>();
		list.add(new HashMap<>());
		int ans = 0;

		for (int i = 1; i < arr.length; i++) {

			HashMap<Integer, Integer> map = new HashMap<>();
			for (int j = 0; j < i; j++) {

				int diff = arr[i] - arr[j];
				if (diff <= Integer.MIN_VALUE || diff >= Integer.MAX_VALUE) {
					continue;
				}

				if (list.get(j).containsKey(diff)) {
					map.put(diff, list.get(j).get(diff) + 1);
					ans += list.get(j).get(diff);
				} else {
					map.put(diff, map.getOrDefault(diff, 0) + 1);
				}
			}
			list.add(map);
		}

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("Ans: " + ans);
	}

	private static void arithmeticSlices1(int[] arr) {

		int tap = 0;
		int dp[] = new int[arr.length];
		for (int i = 2; i < arr.length; i++) {

			if (arr[i] - arr[i - 1] == arr[i - 1] - arr[i - 2]) {
				dp[i] = dp[i - 1] + 1;
				tap += dp[i];
			}
		}

		System.out.println("Total AP: " + tap);
		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static int maxSumOfMNonOverlappingSubarraysOfSizeK(int[] arr, int i, int m, int k, int[][] dp) {

		if (i >= arr.length) {
			return 0;
		}

		if (m == 0) {
			return 0;
		}

		if (dp[i][m] != 0) {
			return dp[i][m];
		}

		int ts = 0;
		for (int j = i; j < i + k && j < arr.length; j++) {
			ts += arr[j];
		}

		int lsum = maxSumOfMNonOverlappingSubarraysOfSizeK(arr, i + 1, m, k, dp);
		int rsum = ts + maxSumOfMNonOverlappingSubarraysOfSizeK(arr, i + k, m - 1, k, dp);

		int msum = Math.max(lsum, rsum);
		dp[i][m] = msum;

		return msum;
	}

	private static void maxSumOfThreeNonOverlappingSubarrays(int[] arr, int k) {

		int dp[] = new int[arr.length];
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {

			if (i < k) {
				sum += arr[i];
				if (i == k - 1) {
					dp[i] = sum;
				}
			} else {
				sum += arr[i] - arr[i - k];
				dp[i] = Math.max(sum, dp[i - 1]);
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
		System.out.println();

		int psum[] = new int[arr.length];
		sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			psum[i] = sum;
		}

		for (int i = 0; i < psum.length; i++) {
			System.out.print(psum[i] + " ");
		}
		System.out.println();

		int dp2[] = new int[arr.length];
		sum = 0;

		for (int i = arr.length - 1; i >= 0; i--) {

			if (i >= arr.length - k) {
				sum += arr[i];
				if (i == arr.length - k) {
					dp2[i] = sum;
				}
			} else {
				sum += arr[i] - arr[i + k];
				dp2[i] = Math.max(sum, dp2[i + 1]);
			}
		}
		for (int i = 0; i < dp2.length; i++) {
			System.out.print(dp2[i] + " ");
		}
		System.out.println();

		int lsi = -1;
		int msi = -1;
		int rsi = -1;
		sum = 0;
		int lms = 0;
		int rms = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + k; j <= arr.length - 2 * k; j++) {

				int lsum = dp[i + k - 1];
				int msum = psum[i + (2 * k) - 1] - psum[i + k - 1];
				int rsum = dp2[i + (2 * k)];

				int tsum = lsum + rsum + msum;

				if (tsum > sum) {
					sum = tsum;
					msi = j;
					lms = lsum;
					rms = rsum;
				}
			}
		}

		for (int i = 0; i < msi; i++) {
			if (psum[i + k - 1] - (i > 0 ? psum[i - 1] : 0) == lms) {
				lsi = i;
				break;
			}
		}

		for (int i = msi + k; i < arr.length; i++) {
			if (psum[i + k - 1] - psum[i - 1] == rms) {
				rsi = i;
				break;
			}
		}

		System.out.println(sum + " " + lsi + " " + msi + " " + rsi);
	}

	private static void maxSumOfTwoNonOverlappingSubarrays(int[] arr, int x, int y) {

		int dp[] = new int[arr.length];
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {

			if (i < x) {
				sum += arr[i];
				if (i == x - 1) {
					dp[i] = sum;
				}
			} else {

				sum += arr[i] - arr[i - x];
				if (sum > dp[i - 1]) {
					dp[i] = sum;
				} else {
					dp[i] = dp[i - 1];
				}
			}
		}
		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}

		int dp2[] = new int[arr.length];
		sum = 0;
		for (int i = arr.length - 1; i >= 0; i--) {

			if (i >= arr.length - y) {
				sum += arr[i];
				if (i == arr.length - y) {
					dp2[i] = sum;
				}
			} else {
				sum += arr[i] - arr[i + y];
				if (sum > dp2[i + 1]) {
					dp2[i] = sum;
				} else {
					dp2[i] = dp2[i + 1];
				}
			}
		}

		System.out.println();
		for (int i = 0; i < dp2.length; i++) {
			System.out.print(dp2[i] + " ");
		}

		int ans = 0;
		for (int i = 0; i < dp.length - 1; i++) {
			ans = Math.max(ans, dp[i] + dp2[i + 1]);
		}

		System.out.println();
		System.out.println("Ans: " + ans);
	}

}
