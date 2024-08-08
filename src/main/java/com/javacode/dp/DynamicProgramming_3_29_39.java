package com.javacode.dp;

import java.util.HashMap;
import java.util.Map;

public class DynamicProgramming_3_29_39 {

	public static void main(String[] args) {

//		longestCommonSubsequence("abcd", "aebd");

//		longestPalindromicSubsequence("abkccbc");
//		countPalindromicSubsequences("abccbc");

//		longestPalindromicSubstring("abccbc");
//		countPalindromicSubstring("abccbc");

//		countDistinctSubsequences("abcbac");
//		countDistinctPalindromicSubsequences("abacdaea");

//		wildcardMatching("baaabab", "ba*a?");
		regularExpressionMatching("mississippi", "mis*i.*p*i");

//		longestCommonSubstring("pqabcxy", "xyzabcp");
//		longestRepeatingSubsequence("abacbc");
	}

	private static void longestRepeatingSubsequence(String str) {

		int dp[][] = new int[str.length() + 1][str.length() + 1];

		for (int i = dp.length - 2; i >= 0; i--) {
			for (int j = dp[0].length - 2; j >= 0; j--) {

				if (str.charAt(i) == str.charAt(j) && i != j) {
					dp[i][j] = dp[i + 1][j + 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
				}
			}
		}

		display(dp);
	}

	private static void longestCommonSubstring(String s1, String s2) {

		int dp[][] = new int[s1.length()][s2.length()];
		int max = 0;

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0 || j == 0) {
					dp[i][j] = s1.charAt(i) == s2.charAt(j) ? 1 : 0;
				} else {
					if (s1.charAt(i) == s2.charAt(j)) {
						dp[i][j] = dp[i - 1][j - 1] + 1;
					}
				}

				max = Math.max(max, dp[i][j]);
			}
		}

		display(dp);
		System.out.println("Longest common substring: " + max);
	}

	private static void regularExpressionMatching(String s1, String s2) {

		boolean dp[][] = new boolean[s2.length() + 1][s1.length() + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0 && j == 0) {
					dp[i][j] = true;
				} else if (i == 0) {
					if (s2.charAt(i) == '*' && i - 2 >= 0) {
						dp[i][j] = dp[i - 2][j];
					}
				} else if (j == 0) {
					dp[i][j] = false;
				} else {
					char ch1 = s2.charAt(i - 1);
					char ch2 = s1.charAt(j - 1);

					if (ch1 == '*') {
						dp[i][j] = dp[i - 2][j];
						if (s2.charAt(i - 2) == '.' || s2.charAt(i - 2) == ch2) {
							dp[i][j] = dp[i][j] || dp[i][j - 1];
						}
					} else if (ch1 == '.') {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						if (ch1 == ch2) {
							dp[i][j] = dp[i - 1][j - 1];
						}
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

	private static void wildcardMatching(String s1, String s2) {

		boolean dp[][] = new boolean[s2.length() + 1][s1.length() + 1];

		for (int i = dp.length - 1; i >= 0; i--) {
			for (int j = dp[0].length - 1; j >= 0; j--) {

				if (i == dp.length - 1 && j == dp[0].length - 1) {
					dp[i][j] = true;
				} else if (i == dp.length - 1) {
					dp[i][j] = false;
				} else if (j == dp[0].length - 1) {
					if (s1.charAt(i) == '*') {
						dp[i][j] = dp[i + 1][j];
					} else {
						dp[i][j] = false;
					}
				} else {
					char ch = s2.charAt(i);
					if (ch == '*') {
						dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
					} else if (ch == '?') {
						dp[i][j] = dp[i + 1][j + 1];
					} else {
						if (s1.charAt(j) == s2.charAt(i)) {
							dp[i][j] = dp[i + 1][j + 1];
						}
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

	private static void countDistinctPalindromicSubsequences(String str) {

		Map<Character, Integer> map = new HashMap<>();
		int prev[] = new int[str.length()];
		int next[] = new int[str.length()];

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			prev[i] = map.get(ch) == null ? -1 : map.get(ch);
			map.put(ch, i);
		}

		map.clear();
		for (int i = str.length() - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			next[i] = map.get(ch) == null ? -1 : map.get(ch);
			map.put(ch, i);
		}

		int dp[][] = new int[str.length()][str.length()];
		for (int g = 0; g < dp.length; g++) {
			for (int i = 0, j = g; j < dp.length; i++, j++) {

				if (g == 0) {
					dp[i][j] = 1;
				} else if (g == 1) {
					dp[i][j] = 2;
				} else {

					if (str.charAt(i) != str.charAt(j)) {
						dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
					} else {

						int n = next[i];
						int p = prev[j];

						if (n > p) {
							dp[i][j] = 2 * dp[i + 1][j - 1] + 2;
						} else if (n == p) {
							dp[i][j] = 2 * dp[i + 1][j - 1] + 1;
						} else {
							dp[i][j] = 2 * dp[i + 1][j - 1] - dp[n + 1][p - 1];
						}
					}
				}
			}
		}

		display(dp);
	}

	private static void countDistinctSubsequences(String str) {

		Map<Character, Integer> map = new HashMap<>();
		int dp[] = new int[str.length() + 1];
		dp[0] = 1;

		for (int i = 1; i <= str.length(); i++) {

			char ch = str.charAt(i - 1);
			if (map.containsKey(ch)) {
				dp[i] = 2 * dp[i - 1] - dp[map.get(ch) - 1];
			} else {
				dp[i] = 2 * dp[i - 1];
			}
			map.put(ch, i);
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
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
