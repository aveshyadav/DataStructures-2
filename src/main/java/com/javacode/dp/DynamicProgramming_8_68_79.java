package com.javacode.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DynamicProgramming_8_68_79 {

	public static void main(String[] args) {

//		int arr[] = { 0, 1, 3, 5, 6, 8, 12, 17 };
//		frogJump(arr);

//		String s1 = "aabcc";
//		String s2 = "dbbca";
//		String s3 = "aadbbcbcac";
//		Boolean dp[][] = new Boolean[s1.length() + 1][s2.length() + 1];
//		boolean flag = interleavingString(s1, s2, s3, 0, 0, dp);
//		System.out.println("Is InterLeaving String:  " + flag);

//		interleavingStringTab(s1, s2, s3);

//		int arr1[] = { 5, 4, 3, 2, 1 };
//		int arr2[] = { 7, 8, 4, 3, 2, 5 };
//		maxLengthOfRepeatedSubArray(arr1, arr2);

//		String str = "leetcodeleetcode";
//		String str = "abcabcabc";
//		distinctEchoSubstring(str);

		int n = 7;
		int cuts[] = { 1, 3, 4, 5 };
		Arrays.sort(cuts);
		int dp[][] = new int[cuts.length + 1][cuts.length + 1];
		int min = minCostToCutSticks(n, cuts, 0, cuts.length, dp);
		System.out.println("Min Cost: " + min);

//		int arr[] = { 3, 2, 4, 2, 3, 3 };
//		deleteAndEarn(arr);

//		String str = "aacaba";
//		countGoodWaysOfSplitString(str);

//		minASCIIDeleteSumDP("delete", "leet");

//		int days[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31 };
//		int cost[] = { 2, 7, 15 };
//
//		int dp[] = new int[days[days.length - 1] + 1];
//		int min = minCostForTickets(0, days, cost, dp);
//		System.out.println("Min Cost: " + min);

//		int arr[] = { 4, 2, 5, 3 };
//		int dp[][] = new int[arr.length + 1][2];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		int ans = maxAlternativeSubsequenceSum(arr, 0, 0, dp);
//		System.out.println("Max Sum: " + ans);
//
//		maxAlternativeSubsequenceSumTab(arr);

//		int arr[] = { 5, 3, 1, 4, 2 };
//		int dp[][] = new int[arr.length + 1][arr.length + 1];
//		for (int i = 0; i < dp.length; i++) {
//			Arrays.fill(dp[i], -1);
//		}
//		int ps[] = new int[arr.length];
//		ps[0] = arr[0];
//		for (int i = 1; i < arr.length; i++) {
//			ps[i] = ps[i - 1] + arr[i];
//		}
//
//		int ans = stoneGameRecursive(arr, 0, arr.length - 1, ps, dp);
//		System.out.println("Ans: " + ans);
//
//		stoneGameTab(arr, ps);
	}

	private static void stoneGameTab(int[] arr, int ps[]) {

		int dp[][] = new int[arr.length + 1][arr.length + 1];

		for (int i = dp.length - 1; i >= 0; i--) {
			for (int j = 0; j < arr.length; j++) {

				if (i >= j) {
					dp[i][j] = 0;
				}

				else if (i + 1 == j) {
					dp[i][j] = Math.max(arr[i], arr[j]);
				} else {

					int iscore = ps[j] - ps[i] - dp[i + 1][j];
					int jscore = ps[j - 1] - (i == 0 ? 0 : ps[i - 1]) - dp[i][j - 1];

					int ms = Math.max(iscore, jscore);
					dp[i][j] = ms;
				}
			}
		}

		System.out.println("Ans: " + dp[0][arr.length - 1]);
	}

	private static int stoneGameRecursive(int[] arr, int i, int j, int[] ps, int dp[][]) {

		if (i >= j) {
			return dp[i][j] = 0;
		}

		if (i + 1 == j) {
			return dp[i][j] = Math.max(arr[i], arr[j]);
		}

		if (dp[i][j] != -1) {
			return dp[i][j];
		}

		int iscore = ps[j] - ps[i] - stoneGameRecursive(arr, i + 1, j, ps, dp);
		int jscore = ps[j - 1] - (i == 0 ? 0 : ps[i - 1]) - stoneGameRecursive(arr, i, j - 1, ps, dp);

		int ms = Math.max(iscore, jscore);
		dp[i][j] = ms;

		return ms;
	}

	private static void maxAlternativeSubsequenceSumTab(int arr[]) {

		int even = arr[0];
		int odd = 0;

		for (int i = 1; i < arr.length; i++) {

			even = Math.max(even, odd + arr[i]);
			odd = Math.max(odd, even - arr[i]);
		}

		int ans = Math.max(even, odd);
		System.out.println("Ans: " + ans);
	}

	private static int maxAlternativeSubsequenceSum(int[] arr, int idx, int p, int[][] dp) {

		if (idx == arr.length) {
			return 0;
		}

		if (dp[idx][p] != -1) {
			return dp[idx][p];
		}

		int ans = 0;
		if (p == 0) {
			ans = maxAlternativeSubsequenceSum(arr, idx + 1, 1, dp) + arr[idx];
		} else {
			ans = maxAlternativeSubsequenceSum(arr, idx + 1, 0, dp) - arr[idx];
		}

		ans = Math.max(ans, maxAlternativeSubsequenceSum(arr, idx + 1, p, dp));
		dp[idx][p] = ans;

		return ans;
	}

	private static int minCostForTickets(int cday, int days[], int cost[], int dp[]) {

		if (cday > days[days.length - 1]) {
			return 0;
		}

		if (dp[cday] > 0) {
			return dp[cday];
		}

		int res = Integer.MAX_VALUE;
		if (willITravelToday(days, cday)) {

			int v1 = cost[0] + minCostForTickets(cday + 1, days, cost, dp);
			int v2 = cost[1] + minCostForTickets(cday + 7, days, cost, dp);
			int v3 = cost[2] + minCostForTickets(cday + 30, days, cost, dp);

			res = Math.min(v1, Math.min(v2, v3));
		} else {
			res = minCostForTickets(cday + 1, days, cost, dp);
		}

		dp[cday] = res;
		return res;
	}

	private static boolean willITravelToday(int days[], int day) {

		for (int i : days) {
			if (i == day) {
				return true;
			}
		}
		return false;
	}

	private static void minASCIIDeleteSumDP(String s1, String s2) {

		int dp[][] = new int[s1.length() + 1][s2.length() + 1];

		for (int i = dp.length - 1; i >= 0; i--) {
			for (int j = dp[0].length - 1; j >= 0; j--) {

				if (i == dp.length - 1 && j == dp[0].length - 1) {
					dp[i][j] = 0;
				} else if (i == dp.length - 1) {
					dp[i][j] = dp[i][j + 1] + s2.charAt(j);
				} else if (j == dp[0].length - 1) {
					dp[i][j] = dp[i + 1][j] + s1.charAt(i);
				} else {
					if (s1.charAt(i) == s2.charAt(j)) {
						dp[i][j] = dp[i + 1][j + 1];
					} else {
						int v1 = s1.charAt(i) + dp[i + 1][j];
						int v2 = s2.charAt(j) + dp[i][j + 1];

						dp[i][j] = Math.min(v1, v2);
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

	private static void countGoodWaysOfSplitString(String str) {

		Set<Character> set = new HashSet<>();
		int dp1[] = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			set.add(str.charAt(i));
			dp1[i] = set.size();
		}

		set.clear();
		int dp2[] = new int[str.length()];
		for (int i = str.length() - 1; i >= 0; i--) {
			set.add(str.charAt(i));
			dp2[i] = set.size();
		}

		int count = 0;
		for (int i = 1; i < str.length(); i++) {

			String left = str.substring(0, i);
			String right = str.substring(i);

			if (dp1[i - 1] == dp2[i]) {
				System.out.println(left + " " + right);
				count++;
			}
		}

		System.out.println("Count: " + count);
	}

	private static void deleteAndEarn(int[] arr) {

		int dp[] = new int[10001];
		for (int i : arr) {
			dp[i]++;
		}

		int in = 0;
		int ex = 0;

		for (int i = 0; i < dp.length; i++) {

			int ni = ex + dp[i] * i;
			ex = Math.max(in, ex);

			in = ni;
		}

		System.out.println("Ans: " + Math.max(in, ex));
	}

	private static int minCostToCutSticks(int n, int cuts[], int si, int ei, int dp[][]) {

		if (si >= ei) {
			return 0;
		}

		if (dp[si][ei] > 0) {
			return dp[si][ei];
		}

		int min = Integer.MAX_VALUE;
		for (int i = si; i < ei; i++) {

			int le = si == 0 ? 0 : cuts[si - 1];
			int re = ei == cuts.length ? n : cuts[ei];

			int lcost = minCostToCutSticks(le, cuts, si, i, dp);
			int rcost = minCostToCutSticks(re, cuts, i + 1, ei, dp);

			int mcost = re - le;
			int tcost = lcost + rcost + mcost;

			min = Math.min(min, tcost);
		}

		dp[si][ei] = min;
		return min;
	}

	private static void distinctEchoSubstring(String str) {

		Set<String> set = new HashSet<>();

		for (int len = 1; len <= str.length() / 2; len++) {

			int count = 0;
			for (int l = 0, r = len; r < str.length(); l++, r++) {

				if (str.charAt(l) == str.charAt(r)) {
					count++;
				} else {
					count = 0;
				}
				
				if (count == len) {
					set.add(str.substring(l, r + 1));
					count--;
				}
			}
		}

		System.out.println(set);
	}

	private static void maxLengthOfRepeatedSubArray(int[] arr1, int[] arr2) {

		int dp[][] = new int[arr1.length + 1][arr2.length + 1];

		int max = 0;
		for (int i = 1; i < arr1.length; i++) {
			for (int j = 1; j < arr2.length; j++) {

				if (arr1[i - 1] == arr2[j - 1]) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}

				max = Math.max(max, dp[i][j]);
			}
		}

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("Max Length: " + max);
	}

	private static void interleavingStringTab(String s1, String s2, String s3) {

		boolean dp[][] = new boolean[s1.length() + 1][s2.length() + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0 && j == 0) {
					dp[i][j] = true;
				} else if (i == 0) {
					if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
						dp[i][j] = dp[i][j - 1];
					}
				} else if (j == 0) {
					if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
						dp[i][j] = dp[i - 1][j];
					}
				} else {

					if (s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
						dp[i][j] = dp[i - 1][j];
					}
					if (s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
						dp[i][j] = dp[i][j] || dp[i][j - 1];
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

	private static boolean interleavingString(String s1, String s2, String s3, int i, int j, Boolean dp[][]) {

		if (s1.length() == i && s2.length() == j) {
			dp[i][j] = true;
			return true;
		}

		if (dp[i][j] != null) {
			return dp[i][j];
		}

		int k = i + j;
		boolean flag = false;
		if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
			flag = flag || interleavingString(s1, s2, s3, i + 1, j, dp);
		}
		if (j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
			flag = flag || interleavingString(s1, s2, s3, i, j + 1, dp);
		}

		dp[i][j] = flag;
		return flag;
	}

	private static void frogJump(int[] arr) {

		Map<Integer, HashSet<Integer>> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], new HashSet<>());
		}
		map.get(arr[0]).add(1);

		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {

			HashSet<Integer> jumps = map.get(arr[i]);
			System.out.println(arr[i] + " " + jumps);
			for (int j : jumps) {

				int ns = arr[i] + j;
				if (map.containsKey(ns)) {

					if (j - 1 > 0) {
						map.get(ns).add(j - 1);
					}
					map.get(ns).add(j);
					map.get(ns).add(j + 1);
				}

				if (ns == arr[arr.length - 1]) {
					flag = true;
				}
			}
		}

		System.out.println("Can Cross River: " + flag);
	}

}
