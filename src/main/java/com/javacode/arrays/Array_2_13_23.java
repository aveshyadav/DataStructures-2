package com.javacode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array_2_13_23 {

	public static void main(String[] args) {

//		int arr[] = { 1, 0, 2, 4, 3, 5 };
//		maxChunksToSorted(arr);

//		int arr[] = { 2, 1, 3, 4, 4 };
//		int arr[] = { 30, 10, 20, 40, 60, 50, 75, 70 };
//		maxChunksToSorted2(arr);

//		minJumpsWithMoves(20);

//		int arr[] = { 1, 2, 3, -7, 4, 5, 6, -8 };
//		maxProductOfThreeNum(arr);

//		int arr[] = { 9, 3, 8, 7, 6, 2, 3 };
//		sortArrayByParity(arr);

//		int arr[][] = { { 1, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 } };
//		bestMeetingPoint(arr);

//		sieveOfEratosthenes(711);
//		segmentatedSieveAlgo(22, 51);

//		int arr[][] = { { 1, 2, 3 }, { 4, 5, 6 } };
//		transposeOfMatrixMxN(arr);

//		int arr[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
//		transposeOfMatrixMxM(arr);

//		int arr[] = { 2, 6, 4, 8, 10, 9, 15 };
//		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8 };
//		findUnsortedSubarray(arr);

	}

	private static void findUnsortedSubarray(int[] arr) {

		int right = 0;
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {

			if (arr[i] < max) {
				right = i;
			}
			max = Math.max(max, arr[i]);
		}

		int left = arr.length - 1;
		int min = arr[left];

		for (int i = arr.length - 2; i >= 0; i--) {

			if (arr[i] > min) {
				left = i;
			}
			min = Math.min(min, arr[i]);
		}

		System.out.println(left + " " + right);
		int len = right - left + 1;
		if (right == 0 && left == arr.length - 1) {
			len = 0;
		}
		System.out.println("Len: " + len);
	}

	private static void transposeOfMatrixMxM(int[][] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr[0].length; j++) {

				int temp = arr[i][j];
				arr[i][j] = arr[j][i];
				arr[j][i] = temp;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void transposeOfMatrixMxN(int[][] arr) {

		int res[][] = new int[arr[0].length][arr.length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				res[j][i] = arr[i][j];
			}
		}

		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {
				System.out.print(res[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void segmentatedSieveAlgo(int low, int high) {

		int n = high - low + 1;
		boolean arr[] = new boolean[n];
		Arrays.fill(arr, true);

		int sqrt = (int) Math.sqrt(high + 1);
		List<Integer> list = sieveOfEratosthenes(sqrt);

		for (int i = 0; i < list.size(); i++) {

			int d = list.get(i);

			int multiple = (int) Math.ceil((low * 1.0) / d);
			if (multiple == 1) {
				multiple++;
			}

			int idx = (d * multiple) - low;
			for (int j = idx; j < arr.length; j += d) {
				arr[j] = false;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == true) {
				System.out.print((i + low) + " ");
			}
		}
	}

	private static List<Integer> sieveOfEratosthenes(int n) {

		boolean arr[] = new boolean[n + 1];
		Arrays.fill(arr, true);

		for (int i = 2; i * i <= arr.length; i++) {

			if (arr[i] == true) {
				int fact = 2;
				while ((i * fact) < arr.length) {
					arr[i * fact] = false;
					fact++;
				}
			}
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 2; i < arr.length; i++) {
			if (arr[i]) {
				list.add(i);
			}
		}
		System.out.println(list);
		return list;
	}

	private static void bestMeetingPoint(int[][] arr) {

		List<Integer> xlist = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1) {
					xlist.add(i);
				}
			}
		}

		List<Integer> ylist = new ArrayList<>();
		for (int i = 0; i < arr[0].length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[j][i] == 1) {
					ylist.add(i);
				}
			}
		}

		System.out.println(xlist);
		System.out.println(ylist);

		int xmid = xlist.get(xlist.size() / 2);
		int ymid = ylist.get(ylist.size() / 2);

		System.out.println(xmid + " " + ymid);

		int distance = 0;
		for (int x : xlist) {
			distance += Math.abs(x - xmid);
		}
		for (int y : ylist) {
			distance += Math.abs(y - ymid);
		}
		System.out.println("Best Meeting Point: " + distance);
	}

	private static void sortArrayByParity(int[] arr) {

		int i = 0;
		int j = 0;

		while (i < arr.length) {

			if (arr[i] % 2 == 0) {

				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
				j++;
			}
			i++;
		}

		for (i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	private static void maxProductOfThreeNum(int[] arr) {

		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;

		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] > max1) {
				max3 = max2;
				max2 = max1;
				max1 = arr[i];
			} else if (arr[i] > max2) {
				max3 = max2;
				max2 = arr[i];
			} else if (arr[i] > max3) {
				max3 = arr[i];
			}

			if (arr[i] < min1) {
				min2 = min1;
				min1 = arr[i];
			} else if (arr[i] < min2) {
				min2 = arr[i];
			}
		}

		System.out.println(min1 + " " + min2);
		System.out.println(max1 + " " + max2 + " " + max3);

		int ans = Math.max(max1 * max2 * max3, max1 * min1 * min2);
		System.out.println("Ans: " + ans);
	}

	private static void minJumpsWithMoves(int n) {

		int jump = 1;
		int num = 0;
		while (num < n) {
			num += jump;
			jump += 1;
		}
		System.out.println(num + " " + jump);

		int d1 = num - n;
		if (d1 % 2 == 0) {
			jump = jump - 1;
		} else {
			num += jump;
			int d2 = num - n;
			if (d2 % 2 != 0) {
				jump++;
			}
		}
		System.out.println("Min Jumps: " + jump);
	}

	private static void maxChunksToSorted2(int[] arr) {

		int right[] = new int[arr.length];
		right[arr.length - 1] = arr[arr.length - 1];

		for (int i = arr.length - 2; i >= 0; i--) {
			right[i] = Math.min(arr[i], right[i + 1]);
		}

		int lmax = arr[0];
		int count = 1;
		for (int i = 1; i < arr.length; i++) {

			if (lmax <= right[i]) {
				lmax = arr[i];
				count++;
			} else {
				lmax = Math.max(lmax, arr[i]);
			}
		}
		System.out.println("Max Chunks: " + count);
	}

	private static void maxChunksToSorted(int[] arr) {

		int count = 1;
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {

			if (i > max) {
				max = arr[i];
				count++;
			} else {
				max = Math.max(max, arr[i]);
			}
		}

		System.out.println("Max Chunks: " + count);
	}

}
