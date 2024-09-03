package com.javacode.sorting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Sorting_2_17_35 {

	public static void main(String[] args) {

//		int arr1[] = { 3, 5, 7, 10, 15 };
//		int arr2[] = { 2, 4, 12 };
//		medianOfTwoSortedArray(arr1, arr2);

//		int arr1[] = { 3, 9, 12, 13, 14, 19 };
//		int arr2[] = { 7, 17, 20, 24, 26, 28, 30, 32 };
//		medianOfTwoSortedArray2(arr1, arr2);

//		int arr[] = { 3, 6, 7, 11 };
//		kokoEatingBanana(arr, 8);

//		int arr[] = { 1, 2, 5, 9 };
//		findSmallestDivisor(arr, 6);

//		int arr[] = { 3, 4, 1, 9, 56, 7, 9, 12 };
//		chocolateDistribution(arr, 5);

//		int arr[] = { 10, 20, 30, 40 };
//		allocateMinPagesBook(arr, 3);

//		int arr[] = { 7, 2, 5, 10, 8 };
//		splitArrayLargestSum(arr, 2);

//		int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
//		shipWithinDays(arr, 5);

//		int arr[] = { 1, 3, 5, 2, 7, 4 };
//		countTriplets(arr);

//		int arr[] = { 4, 6, 3, 7 };
//		countPossibleTraingle(arr);

//		int arr[][] = { { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 1 }, { 0, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };
//		countZerosInASortedMatrix(arr);

//		int arr1[] = { 1, 2, 3, 4, 7, 9 };
//		int arr2[] = { 0, 1, 2, 4, 1, 1 };
//		countElementInTwoArrays(arr1, arr2);
//		countElementInTwoArrays2(arr1, arr2);

//		int arr[] = { 3, 1, 3, 1, 2, 1, 2 };
//		countZerosXorPairs(arr);

//		int arr[] = { 7, 4, 8, 2, 9, 6 };
//		sunFacingBuildings(arr);

//		int arr[] = { -3, -3, -2, -1, 1, 3, 4, 5, 5 };
//		distinctAbsoluteArrayElement(arr);

//		int arr[] = { 0, 0, 0, 0, 1, 1 };
//		findTransitionPoint(arr);

		int arr[] = { 1, 1, 3, 3, 4, 5, 5, 7, 7, 8, 8 };
		findElementAppearsOnce(arr);
	}

	private static void findElementAppearsOnce(int[] arr) {

		int lo = 0;
		int hi = arr.length - 1;
		int ans = -1;

		if (arr[0] != arr[1]) {
			ans = arr[0];
		} else if (arr[hi] != arr[hi - 1]) {
			ans = arr[hi];
		} else {

			while (lo <= hi) {

				int mid = (lo + hi) / 2;

				if (arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]) {
					ans = arr[mid];
					break;
				}

				if (arr[mid] == arr[mid - 1]) {

					int lc = mid - lo + 1;
					if (lc % 2 == 0) {
						lo = mid + 1;
					} else {
						hi = mid - 2;
					}
				} else {

					int rc = hi - mid + 1;
					if (rc % 2 == 0) {
						hi = mid - 1;
					} else {
						lo = mid + 2;
					}
				}
			}
		}

		System.out.println("Ans: " + ans);
	}

	private static void findTransitionPoint(int[] arr) {

		int lo = 0;
		int hi = arr.length - 1;

		int idx = 0;
		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid] == 1) {
				idx = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println("Transistion Point: " + idx);
	}

	private static void distinctAbsoluteArrayElement(int[] arr) {

		int count = 0;
		int i = 0;
		int j = arr.length - 1;

		while (i <= j) {

			count++;
			while (i < arr.length - 1 && arr[i] == arr[i + 1]) {
				i++;
			}

			while (j > 1 && arr[j] == arr[j - 1]) {
				j--;
			}

			if (-arr[i] == arr[j]) {
				i++;
				j--;
			} else if (-arr[i] > arr[j]) {
				i++;
			} else {
				j--;
			}

		}

		System.out.println("Count: " + count);
	}

	private static void sunFacingBuildings(int[] arr) {

		int lmax = arr[0];
		int count = 1;
		for (int i = 1; i < arr.length; i++) {

			if (arr[i] > lmax) {
				lmax = arr[i];
				count++;
			}
		}

		System.out.println("Count: " + count);
	}

	private static void countZerosXorPairs(int[] arr) {

		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {

			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
			if (map.get(arr[i]) > 1) {
				count += map.get(arr[i]) - 1;
			}
		}

		System.out.println("Count: " + count);
	}

	private static void countElementInTwoArrays2(int[] arr1, int[] arr2) {

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr1.length; i++) {
			max = Math.max(arr1[i], max);
		}
		for (int i = 0; i < arr2.length; i++) {
			max = Math.max(arr2[i], max);
		}

		int farr[] = new int[max + 1];
		for (int i = 0; i < arr2.length; i++) {
			farr[arr2[i]]++;
		}

		for (int i = 1; i < farr.length; i++) {
			farr[i] += farr[i - 1];
		}

		int res[] = new int[arr1.length];
		for (int i = 0; i < arr1.length; i++) {
			res[i] = farr[arr1[i]];
			System.out.print(res[i] + " ");
		}
		System.out.println();
	}

	private static void countElementInTwoArrays(int[] arr1, int[] arr2) {

		Arrays.sort(arr2);
		int res[] = new int[arr1.length];
		for (int i = 0; i < arr1.length; i++) {

			int idx = binarySearch(arr2, arr1[i]);
			res[i] = idx + 1;
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
		System.out.println();
	}

	private static int binarySearch(int arr[], int num) {

		int lo = 0;
		int hi = arr.length - 1;
		int idx = -1;

		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid] <= num) {
				lo = mid + 1;
				idx = mid;
			} else {
				hi = mid - 1;
			}
		}
		return idx;
	}

	private static void countZerosInASortedMatrix(int[][] arr) {

		int row = 0;
		int col = arr[0].length - 1;

		int count = 0;
		while (row < arr.length && col >= 0) {

			if (arr[row][col] == 0) {
				count += col + 1;
				row++;
			} else {
				col--;
			}
		}

		System.out.println("Count: " + count);
	}

	private static void countPossibleTraingle(int[] arr) {

		int count = 0;
		Arrays.sort(arr);
		for (int k = arr.length - 1; k >= 0; k--) {

			int i = 0;
			int j = k - 1;

			while (i < j) {

				int sum = arr[i] + arr[j];
				if (sum > arr[k]) {
					count += j - i;
					j--;
				} else {
					i++;
				}
			}
		}
		System.out.println("Count: " + count);
	}

	private static void countTriplets(int[] arr) {

		Arrays.sort(arr);
		for (int k = arr.length - 1; k >= 0; k--) {

			int i = 0;
			int j = k - 1;

			while (i < j) {

				int sum = arr[i] + arr[j];
				if (sum == arr[k]) {
					System.out.println(arr[i] + "+" + arr[j] + "=" + arr[k]);
					i++;
					j--;
				} else if (sum > arr[k]) {
					j--;
				} else {
					i++;
				}
			}
		}
	}

	private static void shipWithinDays(int[] arr, int d) {

		int lo = Integer.MIN_VALUE;
		int hi = 0;
		for (int i = 0; i < arr.length; i++) {
			lo = Math.max(arr[i], lo);
			hi += arr[i];
		}

		int ans = -1;
		while (lo <= hi) {

			int mid = lo + (hi - lo) / 2;
			boolean flag = isLoadPossible(arr, mid, d);
			if (flag == true) {
				ans = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println("Ans: " + ans);
	}

	private static boolean isLoadPossible(int arr[], int max, int n) {

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			if (sum > max) {
				sum = arr[i];
				n--;
			}
		}

		return n > 0;
	}

	private static void splitArrayLargestSum(int[] arr, int n) {

		int lo = Integer.MIN_VALUE;
		int hi = 0;
		for (int i = 0; i < arr.length; i++) {
			lo = Math.max(arr[i], lo);
			hi += arr[i];
		}

		int ans = -1;
		while (lo <= hi) {

			int mid = lo + (hi - lo) / 2;
			boolean flag = isSplitPossible(arr, mid, n);
			if (flag == true) {
				ans = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println("Largest Sum: " + ans);
	}

	private static boolean isSplitPossible(int arr[], int max, int n) {

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			if (sum > max) {
				sum = arr[i];
				n--;
			}
		}
		return n > 0;
	}

	private static void allocateMinPagesBook(int[] arr, int n) {

		int lo = Integer.MIN_VALUE;
		int hi = 0;
		for (int i = 0; i < arr.length; i++) {
			lo = Math.max(arr[i], lo);
			hi += arr[i];
		}

		int ans = -1;
		while (lo <= hi) {

			int mid = lo + (hi - lo) / 2;
			boolean flag = isAllocationPossible(arr, mid, n);
			if (flag == true) {
				ans = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		System.out.println("Min pages: " + ans);
	}

	private static boolean isAllocationPossible(int arr[], int max, int n) {

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			if (sum > max) {
				sum = arr[i];
				n--;
			}
		}

		return n > 0;
	}

	private static void chocolateDistribution(int[] arr, int n) {

		Arrays.sort(arr);

		int ans = Integer.MAX_VALUE;
		int j = n - 1;

		for (int i = 0; i <= arr.length - n; i++) {
			int diff = arr[j] - arr[i];
			ans = Math.min(ans, diff);
			j++;
			System.out.println(diff);
		}

		System.out.println("Min Difference: " + ans);
	}

	private static void findSmallestDivisor(int[] arr, int threshold) {

		int lo = Integer.MAX_VALUE;
		int hi = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			lo = Math.min(lo, arr[i]);
			hi = Math.max(hi, arr[i]);
		}

		int min = hi;

		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			int sd = smallestDivisor(arr, mid);
			if (sd <= threshold) {
				min = Math.min(min, mid);
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		System.out.println("Smallest Divisor: " + min);
	}

	private static int smallestDivisor(int arr[], int div) {

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			count += (arr[i] / div);
			if (arr[i] % div > 0) {
				count++;
			}
		}
		return count;
	}

	private static void kokoEatingBanana(int[] arr, int n) {

		int lo = Integer.MAX_VALUE;
		int hi = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			lo = Math.min(lo, arr[i]);
			hi = Math.max(hi, arr[i]);
		}

		int min = hi;
		while (lo <= hi) {

			int speed = (lo + hi) / 2;
			int hr = hoursRequiredToEatBanana(arr, speed);

			if (hr <= n) {
				min = Math.min(min, speed);
				hi = speed - 1;
			} else {
				lo = speed + 1;
			}
		}
		System.out.println("Min Speed: " + min);
	}

	private static int hoursRequiredToEatBanana(int arr[], int speed) {

		int count = 0;
		for (int i = 0; i < arr.length; i++) {

			count += arr[i] / speed;
			if (arr[i] % speed > 0) {
				count++;
			}
		}

		return count;
	}

	private static void medianOfTwoSortedArray2(int[] arr1, int[] arr2) {

		if (arr1.length > arr2.length) {
			int temp[] = arr1;
			arr1 = arr2;
			arr2 = temp;
		}

		int lo = 0;
		int hi = arr1.length;
		int n = arr1.length + arr2.length;

		int a = 0;
		int b = 0;

		while (lo <= hi) {

			int aleft = (lo + hi) / 2;
			int bleft = (n + 1) / 2 - aleft;

			int a1m1 = aleft > 0 ? arr1[aleft - 1] : Integer.MIN_VALUE;
			int a1 = aleft < arr1.length ? arr1[aleft] : Integer.MAX_VALUE;
			int b1m1 = bleft > 0 ? arr2[bleft - 1] : Integer.MIN_VALUE;
			int b1 = bleft < arr2.length ? arr2[bleft] : Integer.MAX_VALUE;

			if (a1m1 <= b1 && b1m1 <= a1) {
				a = aleft;
				b = bleft;
				break;
			}

			if (a1m1 > b1) {
				hi = aleft - 1;
			} else if (b1m1 > a1) {
				lo = aleft + 1;
			}
		}

		System.out.println(a + " " + b);
		double median = Math.max(arr1[a], arr2[b]);
		if (n % 2 == 0) {
			median = (Math.min(arr1[a], arr2[b]) + Math.max(arr1[a - 1], arr2[b - 1])) / 2;
		}

		System.out.println("Median: " + median);
	}

	private static void medianOfTwoSortedArray(int[] arr1, int[] arr2) {

		int arr[] = new int[arr1.length + arr2.length];

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < arr1.length && j < arr2.length) {

			if (arr1[i] < arr2[j]) {
				arr[k++] = arr1[i++];
			} else {
				arr[k++] = arr2[j++];
			}
		}

		while (i < arr1.length) {
			arr[k++] = arr1[i++];
		}

		while (j < arr2.length) {
			arr[k++] = arr2[j++];
		}

		int median = 0;
		if (arr.length % 2 == 0) {
			int mid = arr.length / 2;
			median = (arr[mid] + arr[mid - 1]) / 2;
		} else {
			median = arr[arr.length / 2];
		}

		System.out.println("Median: " + median);

		for (i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}

	}

}
