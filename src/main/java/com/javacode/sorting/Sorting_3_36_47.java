package com.javacode.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Sorting_3_36_47 {

	public static void main(String[] args) {

//		int mat[][] = { { 0, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } };
//		findMaxOnesRow(mat);

//		int arr1[] = { 3, 9, 2, 4, 11 };
//		int arr2[] = { 1, 8, 5 };
//		findRadius(arr1, arr2);

//		int roll[] = { 3, 2, 4, 1, 5 };
//		int marks[] = { 50, 67, 89, 79, 58 };
//		punishTheStudents(roll, marks, 68);

//		int arr[] = { 3, 30, 34, 5, 9 };
//		largestNumber(arr);

//		int arr[] = { 1, 2, 1, 10 };
//		int arr[] = { 25, 6, 9, 11, 8, 12, 10, 3, 2 };
//		largestPerimeter(arr);

//		int arr[] = { 16, 17, 5, 4, 3, 5, 2 };
//		leadersInArray(arr);

//		int arr[] = { 2, 2, 2, 9, 2, 2, 2, 2, 2 };
//		int arr[] = { 5, 3, 2, 3, 6, 3, 3 };
//		ishaanAndSticks(arr);

//		int arr[] = { 2, 2, 1, 3, 1 };
//		toppersOfClass(arr, 3);

//		allCellsDistOrder(2, 2, 0, 1);
//		sequentialDigits(1000, 13000);

//		int arr[] = { 3, 2, 4, 1 };
//		pancakeSort(arr);
	}

	private static void pancakeSort(int arr[]) {

		List<Integer> list = new ArrayList<>();

		for (int i = arr.length - 1; i > 0; i--) {

			int max = i;
			for (int j = 0; j < i; j++) {
				if (arr[j] > arr[max]) {
					max = j;
				}
			}

			if (max != i) {
				list.add(max + 1);
				list.add(i + 1);
				reverse(arr, 0, max);
				reverse(arr, 0, i);
			}
		}
		System.out.println(list);
	}
	

	private static void reverse(int arr[], int si, int hi) {

		int i = si;
		int j = hi;

		while (i < j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}

	private static void sequentialDigits(int lo, int hi) {

		int arr[] = { 12, 23, 34, 45, 56, 67, 89, 123, 234, 345, 456, 567, 678, 789, 1234, 2345, 3456, 4567, 5678, 6789,
				12345, 23456, 34567, 45678, 56789, 123456, 234567, 345678, 456789, 1234567, 2345678, 3456789, 12345678,
				23456789, 123456789 };

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			if (lo <= arr[i] && hi >= arr[i]) {
				list.add(arr[i]);
			}
		}
		System.out.println(list);
	}


	private static void toppersOfClass(int[] arr, int k) {

		PriorityQueue<Pair> pq = new PriorityQueue<>();

		for (int i = 0; i < arr.length; i++) {

			if (pq.size() < k) {
				pq.add(new Pair(i, arr[i]));
			} else {
				if (pq.peek().marks < arr[i]) {
					pq.remove();
					pq.add(new Pair(i, arr[i]));
				}
			}
		}

		while (pq.size() > 0) {
			Pair mp = pq.remove();
			System.out.println(mp.idx + " " + mp.marks);
		}

	}

	private static class Pair implements Comparable<Pair> {

		int marks;
		int idx;

		public Pair(int idx, int marks) {
			this.idx = idx;
			this.marks = marks;
		}

		@Override
		public int compareTo(Pair o) {

			if (this.marks != o.marks) {
				return this.marks - o.marks;
			} else {
				return o.idx - this.idx;
			}
		}
	}

	private static void ishaanAndSticks(int[] arr) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		int max = 0;
		int maxArea = 0;
		System.out.println(map);
		for (int key : map.keySet()) {

			if (map.get(key) >= 4) {
				int len = map.get(key) / 4;
				int area = key * key;
				maxArea = Math.max(area, maxArea);
				max = Math.max(max, len);
			}

		}
		System.out.println("Max Area: " + maxArea + " Max Squares: " + max);
	}

	private static void leadersInArray(int[] arr) {

		int j = arr.length - 1;
		List<Integer> list = new ArrayList<>();
		list.add(arr[j]);

		for (int i = arr.length - 2; i >= 0; i--) {

			if (arr[i] >= arr[j]) {
				list.add(arr[i]);
				j = i;
			}
		}

		System.out.println(list);
	}
	

	private static void largestPerimeter(int[] arr) {

		Arrays.sort(arr);

		int max = 0;
		for (int k = arr.length - 1; k >= 2; k--) {

			int j = k - 1;
			int i = k - 2;

			if (arr[i] + arr[j] > arr[k]) {
				max = arr[i] + arr[j] + arr[k];
				break;
			}
		}
		System.out.println("Largest Permieter: " + max);
	}

	private static void findRadius(int[] arr1, int[] arr2) {

	}

	private static void findMaxOnesRow(int[][] mat) {

		int max = 0;
		for (int i = 0; i < mat.length; i++) {

			int idx = binarySearch(mat[i]);
			int len = idx == -1 ? mat[i].length : idx;
			max = Math.max(max, len);
		}

		System.out.println("Max: " + max);
	}

	private static int binarySearch(int arr[]) {

		int lo = 0;
		int hi = arr.length - 1;
		int idx = -1;

		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid] == 1) {
				idx = mid;
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		return idx;
	}
}
