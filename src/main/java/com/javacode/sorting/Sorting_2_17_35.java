package com.javacode.sorting;

public class Sorting_2_17_35 {

	public static void main(String[] args) {

		int arr1[] = { 3, 5, 7, 10, 15 };
		int arr2[] = { 2, 4, 12 };
		medianOfTwoSortedArray(arr1, arr2);

//		int arr1[] = { 3, 9, 12, 13, 14, 19 };
//		int arr2[] = { 7, 17, 20, 24, 26, 28, 30, 32 };
//		medianOfTwoSortedArray2(arr1, arr2);
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
