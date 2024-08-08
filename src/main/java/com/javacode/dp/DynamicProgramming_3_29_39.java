package com.javacode.dp;

public class DynamicProgramming_3_29_39 {

	public static void main(String[] args) {

//		longestCommonSubsequence("abcd", "aebd");

//		longestPalindromicSubsequence("abkccbc");
//		countPalindromicSubsequences("abccbc");

//		longestPalindromicSubstring("abccbc");
//		countPalindromicSubstring("abccbc");

		countDistinctSubsequences("abcbac");
//		countDistinctPalindromicSubsequences("abacdaea");

//		wildcardMatching("baaabab", "ba*a?");
//		regularExpressionMatching("mississippi", "mis*i.*p*i");

//		longestCommonSubstring("pqabcxy", "xyzabcp");
//		longestRepeatingSubsequence("abacbc");
	}

	private static void countDistinctSubsequences(String str) {
		
		
	}

	private static void countPalindromicSubstring(String str) {

		boolean dp[][] = new boolean[str.length()][str.length()];
		int count = 0;

		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 0) {
					dp[i][j] = true;
				} else if (g == 1) {
					dp[i][j] = str.charAt(i) == str.charAt(j) ? true : false;
				} else {
					if (str.charAt(i) == str.charAt(j)) {
						dp[i][j] = dp[i + 1][j - 1];
					}
				}

				if (dp[i][j] == true) {
					count++;
				}
			}
		}

		System.out.println("Count of PS: " + count);
	}
	
	private static void longestPalindromicSubstring(String str) {

		boolean dp[][] = new boolean[str.length()][str.length()];
		int lps = 0;

		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 0) {
					dp[i][j] = true;
				} else if (g == 1) {
					dp[i][j] = str.charAt(i) == str.charAt(j) ? true : false;
				} else {
					if (str.charAt(i) == str.charAt(j)) {
						dp[i][j] = dp[i + 1][j - 1];
					}
				}

				if (dp[i][j] == true) {
					lps = Math.max(lps, j - i + 1);
				}
			}
		}

		System.out.println("LPS:  " + lps);
	}

	private static void countPalindromicSubsequences(String str) {

		int dp[][] = new int[str.length()][str.length()];

		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 0) {
					dp[i][j] = 1;
				} else if (g == 1) {
					dp[i][j] = str.charAt(i) == str.charAt(j) ? 3 : 2;
				} else {
					if (str.charAt(i) == str.charAt(j)) {
						dp[i][j] = dp[i][j - 1] + dp[i + 1][j] + 1;
					} else {
						dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
					}
				}
			}
		}

		display(dp);
	}

	private static void longestPalindromicSubsequence(String str) {

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

		display(dp);
	}

	private static void longestCommonSubsequence(String s1, String s2) {

		int dp[][] = new int[s1.length() + 1][s2.length() + 1];

		for (int i = dp.length - 2; i >= 0; i--) {
			for (int j = dp[0].length - 2; j >= 0; j--) {

				if (s1.charAt(i) == s2.charAt(j)) {
					dp[i][j] = dp[i + 1][j + 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}

		display(dp);

	}

	private static void display(int arr[][]) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}
}
