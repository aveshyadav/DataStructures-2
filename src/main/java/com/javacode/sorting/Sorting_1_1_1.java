package com.javacode.sorting;

import java.util.Arrays;

public class Sorting_1_1_1 {

	public static void main(String[] args) {

//		int arr[] = { 5, 9, 8, 1, 2, 3 };
//		bubbleSort(arr);
//		selectionSort(arr);
//		insertionSort(arr);

//		int arr1[] = { 2, 5, 12, 18, 20 };
//		int arr2[] = { 7, 9, 11, 15, 25, 28, 30, 35 };
//		mergeTwoSortedArray(arr1, arr2);

//		int arr[] = { 7, 4, 1, 3, 6, 8, 2, 5 };
//		int res[] = mergeSort(arr, 0, arr.length - 1);
//		display(res);

//		int arr[] = { 7, 9, 4, 8, 3, 6, 2, 1, 5 };
//		partitionArray(arr, 5);

//		int arr[] = { 8, 5, 1, 3, 7, 2, 9, 6 };
//		quickSort(arr, 0, arr.length - 1);
//		display(arr);

//		int arr[] = { 8, 3, 5, 7, 6, 1, 4, 2 };
//		int ans = quickSelect(arr, 0, arr.length - 1, 5);
//		System.out.println("Ans: " + ans);

//		int arr[] = { 9, 6, 3, 5, 3, 4, 3, 9, 6, 4, 6, 5, 8, 2, 9 };
//		countSort(arr);

//		int arr[] = { 213, 97, 718, 123, 37, 443, 982, 64, 375, 683 };
//		radixSort(arr);

//		String arr[] = { "12041996", "20101996", "05061997", "12041989", "11081987" };
//		sortDates(arr);

//		int arr[] = { 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1 };
//		sort01(arr);

//		int arr[] = { 1, 1, 2, 2, 0, 1, 2, 2, 1, 0, 1, 2, 0, 2, 1 };
//		sort012(arr);

//		int arr[] = { 9, -48, 100, 43, 84, 74, 86, 34, -37, 60, -29, 44 };
//		targetSumPair(arr, 160);

//		int arr[] = { 30, 40, 50, 10, 20, 25 };
//		int pi = pivotOfSortedRotatedArray(arr, 0, arr.length - 1);
//		System.out.println("Pivot: " + pi);
	}

	private static int pivotOfSortedRotatedArray(int arr[], int lo, int hi) {

		int mid = (lo + hi) / 2;
		if (arr[mid] < arr[hi]) {
			return pivotOfSortedRotatedArray(arr, lo, mid - 1);
		} else if (arr[mid] > arr[lo]) {
			return pivotOfSortedRotatedArray(arr, mid + 1, hi);
		}
		return mid;
	}

	private static void targetSumPair(int[] arr, int target) {

		Arrays.sort(arr);

		int i = 0;
		int j = arr.length - 1;

		while (i < j) {

			int sum = arr[i] + arr[j];
			if (sum == target) {
				System.out.println(arr[i] + " " + arr[j]);
				i++;
				j--;
			} else if (sum > target) {
				j--;
			} else {
				i++;
			}

		}
	}

	private static void sort012(int[] arr) {

		int i = 0;
		int j = 0;
		int k = arr.length - 1;

		while (i <= k) {

			if (arr[i] == 0) {
				arr[j] = 0;
				arr[i] = 1;
				i++;
				j++;
			} else if (arr[i] == 1) {
				i++;
			} else if (arr[i] == 2) {
				arr[i] = arr[k];
				arr[k] = 2;
				k--;
			}
		}

		display(arr);
	}

	private static void sort01(int[] arr) {

		int i = 0;
		int j = 0;

		while (i < arr.length) {

			if (arr[i] == 0) {
				arr[j] = 0;
				arr[i] = 1;
				j++;
			}
			i++;
		}

		display(arr);
	}

	private static void sortDates(String[] arr) {

		countSortDateHelper(arr, 1000000, 100, 32);
		countSortDateHelper(arr, 10000, 100, 12);
		countSortDateHelper(arr, 1, 1000, 2501);

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}

	private static void countSortDateHelper(String[] arr, int div, int mod, int size) {

		int farr[] = new int[size];
		for (int i = 0; i < arr.length; i++) {
			int id = Integer.parseInt(arr[i]) / div % mod;
			farr[id]++;
		}

		for (int i = 1; i < farr.length; i++) {
			farr[i] += farr[i - 1];
		}

		String res[] = new String[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {

			int val = Integer.parseInt(arr[i]) / div % mod;
			int id = farr[val];
			res[id - 1] = arr[i];
			farr[val]--;
		}

		for (int i = 0; i < arr.length; i++) {
			arr[i] = res[i];
		}
	}

	private static void radixSort(int[] arr) {

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}

		int exp = 1;
		while (max > 1) {
			countSortRadixHelper(arr, exp);
			exp = exp * 10;
			max = max / 10;
		}
		display(arr);
	}

	private static void countSortRadixHelper(int[] arr, int exp) {

		int arr1[] = new int[10];
		for (int i = 0; i < arr.length; i++) {
			int id = arr[i] / exp % 10;
			arr1[id]++;
		}

		for (int i = 1; i < arr1.length; i++) {
			arr1[i] += arr1[i - 1];
		}

		int res[] = new int[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {

			int val = arr[i] / exp % 10;
			int id = arr1[val];
			res[id - 1] = arr[i];
			arr1[val]--;
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = res[i];
		}
	}

	private static void countSort(int[] arr) {

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}

		int size = max - min + 1;
		int arr2[] = new int[size];
		for (int i = 0; i < arr.length; i++) {
			int id = arr[i] - min;
			arr2[id]++;
		}

		for (int i = 1; i < arr2.length; i++) {
			arr2[i] += arr2[i - 1];
		}

		int res[] = new int[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {
			int id = arr2[arr[i] - min] - 1;

			res[id] = arr[i];
			arr2[arr[i] - min]--;
		}

		display(res);
	}

	private static int quickSelect(int arr[], int lo, int hi, int id) {

		int pi = partitionArray(arr, arr[hi], lo, hi);
		if (pi == id) {
			return arr[pi];
		} else if (pi > id) {
			return quickSelect(arr, lo, pi - 1, id);
		} else {
			return quickSelect(arr, pi + 1, hi, id);
		}
	}

	private static void quickSort(int arr[], int lo, int hi) {

		if (lo >= hi) {
			return;
		}

		int pivotIndex = partitionArray(arr, arr[hi], lo, hi);
		quickSort(arr, lo, pivotIndex - 1);
		quickSort(arr, pivotIndex + 1, hi);
	}

	private static int partitionArray(int[] arr, int p, int lo, int hi) {

		int i = lo;
		int j = lo;

		while (i <= hi) {

			if (arr[i] <= p) {

				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j++;
			}
			i++;
		}

		return j - 1;
	}

	private static void partitionArray(int[] arr, int p) {

		int i = 0;
		int j = 0;

		while (i < arr.length) {

			if (arr[i] <= p) {

				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j++;
			}
			i++;
		}

		display(arr);
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

	private static int[] mergeTwoSortedArray(int[] arr1, int[] arr2) {

		int res[] = new int[arr1.length + arr2.length];

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < arr1.length && j < arr2.length) {

			if (arr1[i] < arr2[j]) {
				res[k] = arr1[i];
				i++;
			} else {
				res[k] = arr2[j];
				j++;
			}
			k++;
		}

		while (i < arr1.length) {
			res[k++] = arr1[i++];
		}

		while (j < arr2.length) {
			res[k++] = arr2[j++];
		}

//		display(res);
		return res;
	}

	private static void insertionSort(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		display(arr);
	}

	private static void selectionSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {

			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}

			int temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}

		display(arr);
	}

	private static void bubbleSort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {

				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		display(arr);
	}

	private static void display(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
