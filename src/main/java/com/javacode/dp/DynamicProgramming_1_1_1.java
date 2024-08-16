package com.javacode.dp;

import java.util.Arrays;

public class DynamicProgramming_1_1_1 {

	public static void main(String[] args) {

//		int n = 10;
//		int dp[] = new int[n + 1];
//		System.out.println(fibonacci(n, dp));

//		climbingStairs(6);

//		int n = 10;
//		int arr[] = { 3, 3, 0, 2, 1, 2, 4, 2, 0, 0 };
//		climbingStairsWithJumps(n, arr);

//		int n = 10;
//		int arr[] = { 3, 3, 0, 2, 1, 2, 4, 2, 0, 0 };
//		climbingStairsWithMinMoves(n, arr);

//		int cost[][] = { { 2, 8, 4, 1, 6, 4, 2 }, { 6, 0, 9, 5, 3, 8, 5 }, { 1, 4, 3, 4, 0, 6, 5 },
//				{ 6, 4, 7, 2, 4, 6, 1 }, { 1, 0, 3, 7, 1, 2, 7 }, { 1, 5, 3, 2, 3, 0, 9 }, { 2, 2, 5, 1, 9, 8, 2 } };
//		minCostPath(cost);

//		int arr[][] = { { 0, 1, 4, 2, 8, 2 }, { 4, 3, 6, 5, 0, 4 }, { 1, 2, 4, 1, 4, 6 }, { 2, 0, 7, 3, 2, 2 },
//				{ 3, 1, 5, 9, 2, 4 }, { 2, 7, 0, 8, 5, 1 } };
//		collectMaxGold(arr);

//		int arr[] = { 4, 2, 7, 1, 3 };
//		targetSumSubset(arr, 10);

//		int arr[] = { 2, 3, 5, 6 };
//		int target = 10;
//		coinChangeCombination(arr, target);

//		int arr[] = { 2, 3, 5, 6 };
//		int target = 10;
//		coinChangePermutations(arr, target);

//		int wt[] = { 15, 14, 10, 45, 30 };
//		int val[] = { 2, 5, 1, 3, 4 };
//		zeroOneKnapsack(wt, val, 7);

//		int wt[] = { 15, 14, 10, 45, 30 };
//		int val[] = { 2, 5, 1, 3, 4 };
//		unboundedKnapsackPermutation(wt, val, 7);
//		unboundedKnapsackCombination(wt, val, 7);

//		countBinaryString(6);
//		arrangeBuildings(5);

//		String str = "231011";
//		decodeWays(str);

//		countSubsequence("abcabc");
//		int arr[] = { 5, 10, 10, 100, 5, 6 };
//		maxSumNonAdjacent(arr);

//		int red[] = { 1, 5, 3, 1 };
//		int blue[] = { 5, 8, 2, 2 };
//		int green[] = { 7, 4, 9, 4 };
//		paintHouse(red, blue, green, 4);

//		int arr[][] = { { 1, 5, 7, 2, 1, 4 }, { 5, 8, 4, 3, 6, 1 }, { 3, 2, 9, 7, 2, 3 }, { 1, 2, 4, 9, 1, 7 } };
//		int n = 4;
//		int c = 6;
//		paintHouse2(arr, n, c);
//		paintFence(5, 3);

//		countTilesWaysMx1(4);
//		countTilesWaysMxN(3, 8);
//		friendsPairing(4);
//		partitionInkSubset(5, 4);

//		int arr[] = { 11, 6, 7, 19, 4, 1, 6, 18, 4 };
//		int arr[] = { 1, 2, 3, 4, 5 };
//		maxProfitOneTrans(arr);
//		maxProfitMultiTrans(arr);

		int arr[] = { 10, 15, 17, 20, 16, 18, 22, 20, 22, 20, 23, 25 };
//		int fee = 3;
//		maxProfitWithTransFee(arr, fee);
		
//		int arr[] = { 11, 6, 7, 19, 4, 1, 6, 18, 4 };
		maxProfitMultiTransCoolDown(arr);

//		int arr[] = { 30, 40, 43, 50, 45, 20, 26, 40, 80, 50, 30, 15, 10, 20, 40, 45, 71, 50, 55 };
//		maxProfitTwoTrans(arr);

//		int arr[] = { 9, 6, 7, 6, 3, 8 };
//		int k = 3;
//		maxProfitKTrans(arr, k);
	}

	private static void maxProfitKTrans(int[] arr, int k) {

		int dp[][] = new int[k + 1][arr.length];

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {

				dp[i][j] = dp[i][j - 1];
				for (int d = 0; d < j; d++) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][d] + arr[j] - arr[d]);
				}
			}
		}

		display(dp);
	}

	private static void maxProfitTwoTrans(int[] arr) {

		int dp1[] = new int[arr.length];
		int min = arr[0];

		for (int i = 1; i < arr.length; i++) {
			dp1[i] = Math.max(dp1[i - 1], arr[i] - min);
			min = Math.min(min, arr[i]);
		}

		int dp2[] = new int[arr.length];
		int max = arr[arr.length - 1];

		for (int i = dp2.length - 2; i >= 0; i--) {
			dp2[i] = Math.max(dp2[i + 1], max - arr[i]);
			max = Math.max(max, arr[i]);
		}

		for (int i = 0; i < dp1.length; i++) {
			System.out.println(dp1[i] + " " + dp2[i] + " " + (dp1[i] + dp2[i]));
		}
	}

	private static void maxProfitMultiTransCoolDown(int[] arr) {

		int bsp = -arr[0];
		int ssp = 0;
		int csp = 0;

		for (int i = 1; i < arr.length; i++) {

			int tssp = ssp;
			ssp = Math.max(ssp, bsp + arr[i]);
			bsp = Math.max(csp - arr[i], bsp);
			csp = Math.max(csp, tssp);
		}

		System.out.println("Profit: " + ssp);
	}

	private static void maxProfitWithTransFee(int[] arr, int fee) {

		int bsp = -arr[0];
		int ssp = 0;

		for (int i = 1; i < arr.length; i++) {

			int tssp = ssp;
			ssp = Math.max(ssp, bsp + arr[i] - fee);
			bsp = Math.max(bsp, tssp - arr[i]);
		}

		System.out.println("Profit: " + ssp);
	}

	private static void maxProfitMultiTrans(int[] arr) {

		int profit = 0;
		int buy = arr[0];

		for (int i = 1; i < arr.length; i++) {

			if (arr[i] < arr[i - 1]) {
				profit += arr[i - 1] - buy;
				buy = arr[i];
			}
			System.out.println("Max Profit: " + profit);
		}

		if (arr[arr.length - 1] > buy) {
			profit += arr[arr.length - 1] - buy;
		}

		System.out.println("Max Profit: " + profit);
	}

	private static void maxProfitOneTrans(int[] arr) {

		int min = arr[0];
		int maxProfit = 0;

		for (int i = 1; i < arr.length; i++) {

			int todaysProfit = arr[i] - min;
			min = Math.min(arr[i], min);
			maxProfit = Math.max(maxProfit, todaysProfit);
		}

		System.out.println("Max Profit: " + maxProfit);
	}

	private static void partitionInkSubset(int n, int k) {

		int dp[][] = new int[k + 1][n + 1];

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {

				if (i == 1) {
					dp[i][j] = 1;
				} else {
					if (i == j) {
						dp[i][j] = 1;
					} else if (j > i) {
						dp[i][j] = (i * dp[i][j - 1]) + dp[i - 1][j - 1];
					}
				}
			}
		}

		display(dp);
	}

	private static void friendsPairing(int n) {

		int dp[] = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i < dp.length; i++) {
			dp[i] = dp[i - 1] + ((i - 1) * dp[i - 2]);
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void countTilesWaysMxN(int m, int n) {

		int dp[] = new int[n + 1];
		for (int i = 1; i < dp.length; i++) {

			if (i < m) {
				dp[i] = 1;
			} else if (i == m) {
				dp[i] = 2;
			} else {
				dp[i] = dp[i - 1] + dp[i - m];
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void countTilesWaysMx1(int n) {

		int dp[] = new int[n + 1];
		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i < dp.length; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void paintFence(int n, int k) {

		int ii = k;
		int ij = k * (k - 1);

		for (int i = 3; i <= n; i++) {

			int tii = ij;
			int tij = (ii + ij) * (k - 1);

			ii = tii;
			ij = tij;

			System.out.println(ii + " " + ij);
		}

		System.out.println("Ans: " + (ii + ij));
	}

	private static void paintHouse2(int[][] arr, int n, int c) {

		int dp[][] = new int[n][c];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				if (i == 0) {
					dp[i][j] = arr[i][j];
				} else {
					int min = Integer.MAX_VALUE;
					for (int k = 0; k < arr[0].length; k++) {
						if (k != j) {
							min = Math.min(min, dp[i - 1][k]);
						}
					}
					dp[i][j] = min + arr[i][j];
				}
			}
		}

		display(dp);
	}

	private static void paintHouse(int[] red, int[] blue, int[] green, int n) {

		int c1 = red[0];
		int c2 = blue[0];
		int c3 = green[0];

		for (int i = 1; i < red.length; i++) {

			int c11 = c1;
			int c12 = c2;
			c1 = Math.min(c2, c3) + red[i];
			c2 = Math.min(c11, c3) + blue[i];
			c3 = Math.min(c12, c11) + green[i];

			System.out.println(c1 + " " + c2 + " " + c3);
		}
	}

	private static void maxSumNonAdjacent(int[] arr) {

		int in = 0;
		int ex = 0;

		for (int i = 0; i < arr.length; i++) {

			int temp = in;
			in = ex + arr[i];
			ex = Math.max(ex, temp);

			System.out.println(in + " " + ex);
		}
	}

	private static void countSubsequence(String str) {

		int a = 0;
		int b = 0;
		int c = 0;

		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == 'a') {
				a = 2 * a + 1;
			} else if (str.charAt(i) == 'b') {
				b = 2 * b + a;
			} else if (str.charAt(i) == 'c') {
				c = 2 * c + b;
			}
		}

		System.out.println(a + " " + b + " " + c);
	}

	private static void decodeWays(String str) {

		int dp[] = new int[str.length()];
		dp[0] = 1;

		for (int i = 1; i < str.length(); i++) {

			if (str.charAt(i) != '0') {
				dp[i] = dp[i - 1];
			}

			if (str.charAt(i - 1) != '0') {
				String sub = str.substring(i - 1, i + 1);
				int subi = Integer.parseInt(sub);
				if (subi <= 26) {
					if (i - 2 >= 0) {
						dp[i] += dp[i - 2];
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

	private static void arrangeBuildings(int n) {

		int bd = 1;
		int sp = 1;

		for (int i = 1; i < n; i++) {

			int temp = sp;
			sp = bd + sp;
			bd = temp;

			System.out.println(bd + " " + sp);
		}

		int ways = (bd + sp) * (bd + sp);
		System.out.println("Ans: " + ways);
	}

	private static void countBinaryString(int n) {

		int zero = 1;
		int one = 1;

		for (int i = 1; i < n; i++) {

			int temp = one;
			one = zero + one;
			zero = temp;

			System.out.println(zero + " " + one);
		}

		int ans = zero + one;
		System.out.println("Ans: " + ans);
	}

	private static void unboundedKnapsackCombination(int[] wt, int[] val, int n) {

		int dp[] = new int[n + 1];
		dp[0] = 0;

		for (int i = 0; i < val.length; i++) {
			for (int j = 1; j < dp.length; j++) {
				if (j - val[i] >= 0) {
					dp[j] = Math.max(dp[j], wt[i] + dp[j - val[i]]);
				}
			}
		}

		System.out.println();
		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void unboundedKnapsackPermutation(int[] wt, int[] val, int n) {

		int dp[] = new int[n + 1];
		dp[0] = 0;

		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < val.length; j++) {
				if (i - val[j] >= 0) {
					dp[i] = Math.max(dp[i], wt[j] + dp[i - val[j]]);
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void zeroOneKnapsack(int[] wt, int[] val, int target) {

		int n = val.length;
		int dp[][] = new int[n + 1][target + 1];

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {

				int max = dp[i - 1][j];
				if (j - val[i - 1] >= 0) {
					max = Math.max(max, dp[i - 1][j - val[i - 1]] + wt[i - 1]);
				}

				dp[i][j] = max;
			}
		}

		display(dp);
	}

	private static void coinChangePermutations(int[] arr, int target) {

		int dp[] = new int[target + 1];
		dp[0] = 1;

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (i - arr[j] >= 0) {
					dp[i] += dp[i - arr[j]];
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void coinChangeCombination(int[] arr, int target) {

		int dp[] = new int[target + 1];
		dp[0] = 1;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j < dp.length; j++) {
				if (j - arr[i] >= 0) {
					dp[j] += dp[j - arr[i]];
				}
			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void targetSumSubset(int[] arr, int target) {

		boolean dp[][] = new boolean[arr.length + 1][target + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = true;
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {

				if (dp[i - 1][j] == true) {
					dp[i][j] = true;
				} else {
					if (j - arr[i - 1] >= 0 && dp[i - 1][j - arr[i - 1]]) {
						dp[i][j] = true;
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

	private static void collectMaxGold(int[][] arr) {

		int n = arr.length;
		int m = arr[0].length;
		int dp[][] = new int[n][m];

		for (int j = m - 1; j >= 0; j--) {
			for (int i = 0; i < n; i++) {

				if (j == m - 1) {
					dp[i][j] = arr[i][j];
				} else {

					int max = dp[i][j + 1];
					if (i - 1 >= 0) {
						max = Math.max(dp[i - 1][j + 1], max);
					}
					if (i + 1 < n) {
						max = Math.max(dp[i + 1][j + 1], max);
					}
					dp[i][j] = max + arr[i][j];
				}
			}
		}

		display(dp);
	}

	private static void minCostPath(int[][] cost) {

		int n = cost.length;
		int m = cost[0].length;
		int dp[][] = new int[n][m];

		for (int i = cost.length - 1; i >= 0; i--) {
			for (int j = cost[0].length - 1; j >= 0; j--) {

				if (i == n - 1 && j == m - 1) {
					dp[i][j] = cost[i][j];
				} else if (i == n - 1) {
					dp[i][j] = dp[i][j + 1] + cost[i][j];
				} else if (j == m - 1) {
					dp[i][j] = dp[i + 1][j] + cost[i][j];
				} else {
					dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + cost[i][j];
				}
			}
		}

		display(dp);
	}

	private static void climbingStairsWithMinMoves(int n, int[] arr) {

		Integer dp[] = new Integer[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[n] = 0;

		for (int i = arr.length - 1; i >= 0; i--) {

			int min = Integer.MAX_VALUE;
			for (int j = 1; j <= arr[i]; j++) {

				if (i + j <= n) {
					if (dp[i + j] != Integer.MAX_VALUE) {
						min = Math.min(min, dp[i + j]) + 1;
					}
				}
			}
			dp[i] = min;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void climbingStairsWithJumps(int n, int[] arr) {

		int dp[] = new int[n + 1];
		dp[n] = 1;

		for (int i = dp.length - 2; i >= 0; i--) {

			int sum = 0;
			for (int j = 1; j <= arr[i]; j++) {
				if (i + j < dp.length) {
					sum += dp[i + j];
				}
			}
			dp[i] = sum;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static void climbingStairs(int n) {

		int dp[] = new int[n + 1];
		dp[0] = 1;

		for (int i = 1; i < dp.length; i++) {

			int sum = 0;
			if (i - 1 >= 0) {
				sum += dp[i - 1];
			}
			if (i - 2 >= 0) {
				sum += dp[i - 2];
			}
			if (i - 3 >= 0) {
				sum += dp[i - 3];
			}
			dp[i] = sum;
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.print(dp[i] + " ");
		}
	}

	private static int fibonacci(int n, int dp[]) {

		if (n <= 1) {
			return n;
		}

		if (dp[n] > 0) {
			return dp[n];
		}

		int n1 = fibonacci(n - 1, dp);
		int n2 = fibonacci(n - 2, dp);

		int mn = n1 + n2;
		dp[n] = mn;
		return mn;
	}

	private static void display(int arr[][]) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
