package com.javacode.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Sorting_1_1_16 {

	public static void main(String[] args) {

//		int phy[] = { 4, 1, 10, 4, 4, 4, 1, 10, 1, 10 };
//		int chem[] = { 5, 2, 9, 6, 3, 10, 2, 9, 14, 10 };
//		int math[] = { 12, 3, 6, 5, 2, 10, 16, 32, 10, 4 };
//		marksOfPCM(phy, chem, math, 10);

//		int arr1[] = { 1, 3, 4, 5, 7 };
//		int arr2[] = { 2, 3, 5, 6 };
//		unionOfTwoSortedArrays(arr1, arr2);

//		int arr[][] = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 60 } };
//		searchInMatrix(arr, 35);

//		int arr[][] = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
//				{ 18, 21, 23, 26, 30 } };
//		searchInMatrix2(arr, 21);

//		int arr[] = { 1, 7, 3, 6, 5, 6 };
//		findPivotIndex(arr);

//		int arr[] = { 10, 20, 51, 52, 53, 59 };
//		findClosestElement(arr, 59, 3);
//		findClosestElement2(arr, 59, 3);

//		int arr[] = { 5, 2, 3, 80, 5, 20 };
//		findPairWithGivenDifference(arr, 17);

//		int arr[] = { 1, 4, 5, 7, 3, 2, 4 };
//		getRoofTop(arr);

//		int arr[] = { 2, 9, 4, -1, 6 };
//		maximizeSum(arr);

//		int arr[] = { 2, 4, 1, 3, 5 };
//		int arr[] = { 8, 5, 3, 4, 1, 6, 2 };
//		getCountInversion(arr);

//		int arr[] = { 5, 7, 7, 8, 8, 10 };
//		findFirstAndLastOccurrence(arr, 8);

//		int arr[] = { 8, 3, 1, 2 };
//		maxSumInConfiguration(arr);

//		int arr[] = { 4, 5, 6, 7, 8, 9, 10, 1, 2 };
//		searchInRotatedSortedArray(arr, 2);

//		int arr[] = { 4, 5, 6, 7, 8, 0, 1, 3 };
//		findMinInRotatedSortedArray(arr);
//		findRotationCount(arr);
	}

	private static void findRotationCount(int[] arr) {

		int idx = findMinInRotatedSortedArray(arr);
		int count = arr.length - idx;
		System.out.println("Count: " + count);

	}

	private static int findMinInRotatedSortedArray(int[] arr) {

		int lo = 0;
		int hi = arr.length - 1;

		if (arr[lo] <= arr[hi]) {
			System.out.println("Pivot: " + lo);
			return arr[lo];
		}

		int min = -1;
		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid] > arr[mid + 1]) {
				min = mid + 1;
				break;
			}

			if (arr[mid] < arr[mid - 1]) {
				min = mid;
				break;
			}

			if (arr[mid] >= arr[lo]) {
				lo = mid + 1;
			} else {
				hi = mid - 1;
			}
		}

		System.out.println("Pivot: " + min);
		return min;
	}

	private static void searchInRotatedSortedArray(int[] arr, int num) {

		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid] == num) {
				System.out.println("Index: " + mid);
				return;
			}

			if (arr[lo] <= arr[mid]) {
				if (arr[lo] <= num && arr[mid] >= num) {
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else {
				if (arr[mid] <= num && arr[hi] >= num) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
		}

		System.out.println("Not found");
	}

	private static void maxSumInConfiguration(int[] arr) {

		int max = 0;
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			max += arr[i] * i;
			sum += arr[i];
		}

		for (int i = 0; i < arr.length; i++) {

			int temp = max + sum - (arr.length * arr[arr.length - i - 1]);
			max = Math.max(max, temp);
		}

		System.out.println("Max: " + max);
	}

	private static void findFirstAndLastOccurrence(int[] arr, int num) {

		int lo = 0;
		int hi = arr.length - 1;
		int mid = 0;
		int first = 0;

		while (lo <= hi) {

			mid = (lo + hi) / 2;
			if (arr[mid] == num) {
				hi = mid - 1;
				first = mid;
			} else if (arr[mid] > num) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println("First: " + first);

		lo = 0;
		hi = arr.length - 1;
		int last = 0;

		while (lo <= hi) {

			mid = (lo + hi) / 2;
			if (arr[mid] == num) {
				lo = mid + 1;
				last = mid;
			} else if (arr[mid] > num) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println("Last: " + last);
	}

	private static int count = 0;

	private static void getCountInversion(int[] arr) {

		count = 0;
		int res[] = mergeSort(arr, 0, arr.length - 1);

		System.out.println("Count: " + count);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static int[] mergeSort(int arr[], int lo, int hi) {

		if (lo == hi) {
			int res[] = { arr[lo] };
			return res;
		}

		int mid = (lo + hi) / 2;
		int left[] = mergeSort(arr, lo, mid);
		int right[] = mergeSort(arr, mid + 1, hi);

		int res[] = mergeTwoSortedArray(left, right);
		return res;
	}

	private static int[] mergeTwoSortedArray(int left[], int right[]) {

		int res[] = new int[left.length + right.length];

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < left.length && j < right.length) {

			if (left[i] < right[j]) {
				res[k] = left[i++];
			} else {
				res[k] = right[j++];
				count += left.length - i;
			}
			k++;
		}

		while (i < left.length) {
			res[k++] = left[i++];
		}

		while (j < right.length) {
			res[k++] = right[j++];
		}

		return res;
	}

	private static void maximizeSum(int[] arr) {

		Arrays.sort(arr);

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += (arr[i] * i);
		}

		System.out.println("Ans: " + sum);
	}

	private static void getRoofTop(int[] arr) {

		int max = 0;
		int count = 0;

		for (int i = 0; i < arr.length - 1; i++) {

			if (arr[i] < arr[i + 1]) {
				count++;
			} else {
				count = 0;
			}

			max = Math.max(max, count);
		}
		System.out.println("Max: " + max);
	}

	private static void findPairWithGivenDifference(int[] arr, int target) {

		Arrays.sort(arr);
		int i = 0;
		int j = 0;

		while (i < arr.length && j < arr.length) {

			int diff = arr[j] - arr[i];
			if (diff == target) {
				System.out.println(arr[i] + " " + arr[j]);
				return;
			} else if (diff > target) {
				i++;
			} else {
				j++;
			}
		}

		System.out.println("Not found");
	}

	private static void findClosestElement2(int[] arr, int num, int k) {

		int lo = 0;
		int hi = arr.length - 1;
		int mid = 0;

		while (lo <= hi) {

			mid = (lo + hi) / 2;
			if (arr[mid] == num) {
				break;
			}
			if (arr[mid] > num) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		int left = mid - 1;
		int right = mid;

		List<Integer> list = new ArrayList<>();
		while (left >= 0 && right < arr.length) {

			int diff1 = Math.abs(arr[left] - num);
			int diff2 = Math.abs(arr[right] - num);

			if (diff1 < diff2) {
				list.add(arr[left]);
				left--;
			} else {
				list.add(arr[right]);
				right++;
			}
			k--;
		}
		while (k > 0) {

			if (left >= 0) {
				list.add(arr[left]);
				left--;
			}
			if (right < arr.length) {
				list.add(arr[right]);
				right++;
			}
			k--;
		}

		Collections.sort(list);
		System.out.println(list);
	}

	private static void findClosestElement(int[] arr, int num, int k) {

		PriorityQueue<Pair2> pq = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {

			int diff = Math.abs(arr[i] - num);
			if (pq.size() < k) {
				pq.add(new Pair2(arr[i], diff));
			} else {
				if (pq.peek().diff > diff) {
					pq.remove();
					pq.add(new Pair2(arr[i], diff));
				}
			}
		}

		List<Integer> list = new ArrayList<>();
		while (pq.size() > 0) {
			list.add(pq.remove().num);
		}
		Collections.sort(list);
		System.out.println(list);
	}

	private static class Pair2 implements Comparable<Pair2> {

		int diff;
		int num;

		public Pair2(int num, int diff) {
			this.num = num;
			this.diff = diff;
		}

		@Override
		public int compareTo(Pair2 o) {
			return o.diff - this.diff;
		}
	}

	private static void findPivotIndex(int[] arr) {

		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}

		int lsum = 0;
		int rsum = sum;
		for (int i = 0; i < arr.length; i++) {

			rsum -= arr[i];
			if (lsum == rsum) {
				System.out.println("Pivot Index: " + i);
			}
			lsum += arr[i];
		}
	}

	private static void searchInMatrix2(int[][] arr, int target) {

		int row = 0;
		int col = arr[0].length - 1;

		boolean flag = false;
		while (row < arr.length && col > 0) {

			if (arr[row][col] == target) {
				flag = true;
				break;
			} else if (arr[row][col] < target) {
				row++;
			} else {
				col--;
			}
		}

		System.out.println("Found at: " + row + "-" + col + ", " + flag);
	}

	private static void searchInMatrix(int[][] arr, int target) {

		boolean flag = false;
		int row = binarySearchRowSelect(arr, 0, arr.length - 1, target);
		System.out.println("Row: " + row);
		if (row >= 0) {
			int col = binarySearchFindElement(arr, row, 0, arr[0].length - 1, target);
			System.out.println("Col: " + col);
			if (col >= 0) {
				flag = true;
			}
		}
		System.out.println("Found: " + flag);
	}

	private static int binarySearchRowSelect(int arr[][], int lo, int hi, int target) {

		if (lo > hi) {
			return -1;
		}

		int mid = (lo + hi) / 2;
		if (arr[mid][0] <= target && arr[mid][arr[0].length - 1] >= target) {
			return mid;
		} else if (arr[mid][0] > target) {
			return binarySearchRowSelect(arr, lo, mid - 1, target);
		} else {
			return binarySearchRowSelect(arr, mid + 1, hi, target);
		}
	}

	private static int binarySearchFindElement(int arr[][], int row, int lo, int hi, int target) {

		if (lo > hi) {
			return -1;
		}

		int mid = (lo + hi) / 2;
		if (arr[row][mid] == target) {
			return mid;
		} else if (arr[row][mid] > target) {
			return binarySearchFindElement(arr, row, lo, mid - 1, target);
		} else {
			return binarySearchFindElement(arr, row, mid + 1, hi, target);
		}
	}

	private static void unionOfTwoSortedArrays(int[] arr1, int[] arr2) {

		List<Integer> list = new ArrayList<>();

		int i = 0;
		int j = 0;

		while (i < arr1.length && j < arr2.length) {

			int min = Math.min(arr1[i], arr2[j]);
			if (list.size() == 0 || list.get(list.size() - 1) != min) {
				list.add(min);
			}

			if (arr1[i] == min) {
				i++;
			} else if (arr2[j] == min) {
				j++;
			}
		}

		while (i < arr1.length) {
			if (list.size() == 0 || list.get(list.size() - 1) != arr1[i]) {
				list.add(arr1[i]);
			}
			i++;
		}

		while (j < arr2.length) {
			if (list.size() == 0 || list.get(list.size() - 1) != arr2[j]) {
				list.add(arr2[j]);
			}
			j++;
		}

		System.out.println(list);
	}

	private static void marksOfPCM(int[] phy, int[] chem, int[] math, int n) {

		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < phy.length; i++) {
			list.add(new Pair(phy[i], chem[i], math[i]));
		}

		Collections.sort(list);

		for (Pair mp : list) {
			System.out.println(mp.phy + " " + mp.chem + " " + mp.math);
		}
	}

	private static class Pair implements Comparable<Pair> {

		int phy;
		int chem;
		int math;

		public Pair(int phy, int chem, int math) {
			this.phy = phy;
			this.chem = chem;
			this.math = math;
		}

		@Override
		public int compareTo(Pair o) {

			if (this.phy != o.phy) {
				return this.phy - o.phy;
			}
			if (this.chem != o.chem) {
				return o.chem - this.chem;
			}

			return this.math - o.math;
		}
	}

}
