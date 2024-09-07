package com.javacode.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Array_1_1_12 {

	public static void main(String[] args) {

//		faultyKeyboard("aabbc", "aabbcccccccc");

//		int arr[][] = { { 1, 3, 2 }, { 2, 4, 3 }, { 0, 2, -2 } };
//		rangeAddition(arr, 5);

//		int arr[] = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
//		containerWithMostWater(arr);

//		int arr[] = { -7, -3, 2, 3, 11 };
//		squaresOfSortedArray(arr);

//		int arr[] = { 2, 2, 1, 1, 1, 2, 2 };
//		majorityElement1(arr);

//		int arr[] = { 1, 2, 1, 3, 2, 2, 1 };
//		majorityElement2(arr);

//		int arr[] = { 3, 1, 2, 2, 1, 2, 3, 3 };
//		majorityELement3(arr, 4);

//		String str = "452654";
//		nextGreaterElement3(str);

//		int arr[] = { 1, 2, 2, 3, 0, 1, 2 };
//		int arr[] = { -1, 1, 0, -3, 3 };
//		productOfArray(arr);

//		String str = "ababcbacadefegdehijhklij";
//		partitionLabels(str);

//		int arr[] = { 5, 0, 3, 8, 6 };
//		partitionArrayDisjoint(arr);

		int arr[] = { 7, 3, 9, 5, 10, 1, 15, 16, 19, 14, 30 };
		partitionArrayDisjoint2(arr);
	}

	private static void partitionArrayDisjoint2(int[] arr) {

		int lmax = arr[0];
		int greater = arr[0];
		int ans = 0;

		for (int i = 1; i < arr.length; i++) {

			if (arr[i] < lmax) {
				ans = i;
				lmax = greater;
			} else if (arr[i] > greater) {
				greater = arr[i];
			}
		}

		System.out.println("Ans: " + (ans + 1));
	}

	private static void partitionArrayDisjoint(int[] arr) {

		int right[] = new int[arr.length];
		right[arr.length - 1] = arr[arr.length - 1];
		for (int i = arr.length - 2; i >= 0; i--) {
			right[i] = Math.min(arr[i], right[i + 1]);
		}

		int lmax = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (lmax <= right[i]) {
				System.out.println("Partition Index: " + i);
				break;
			}
			lmax = Math.max(lmax, arr[i]);
		}
	}

	private static void partitionLabels(String str) {

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			map.put(ch, i);
		}

		int prev = 0;
		List<Integer> list = new ArrayList<>();
		int max = map.get(str.charAt(0));
		for (int i = 1; i < str.length(); i++) {

			if (i > max) {
				int size = i - prev;
				list.add(size);
				prev = i;
				max = map.get(str.charAt(i));
			} else {
				max = Math.max(max, map.get(str.charAt(i)));
			}
		}
		list.add(str.length() - prev);
		System.out.println(list);
	}

	private static void productOfArray(int[] arr) {

		int rp[] = new int[arr.length];
		rp[rp.length - 1] = arr[arr.length - 1];

		for (int i = arr.length - 2; i >= 0; i--) {
			rp[i] = arr[i] * rp[i + 1];
		}

		int prod = 1;
		int res[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {

			if (i == 0) {
				res[i] = rp[i + 1];
			} else if (i == arr.length - 1) {
				res[i] = prod;
			} else {
				res[i] = prod * rp[i + 1];
			}
			prod = prod * arr[i];
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void nextGreaterElement3(String str) {

		int idx = -1;
		char ch = str.charAt(str.length() - 1);

		for (int i = str.length() - 2; i >= 0; i--) {
			if (str.charAt(i) < ch) {
				idx = i;
				break;
			}
		}

		if (idx == -1) {
			System.out.println("Not Possible");
			return;
		}

		int idx2 = idx + 1;
		for (int i = idx + 2; i < str.length(); i++) {
			if (str.charAt(i) < str.charAt(idx2)) {
				idx2 = i;
			}
		}

		char arr[] = str.toCharArray();
		char temp = arr[idx];
		arr[idx] = arr[idx2];
		arr[idx2] = temp;

		int i = idx + 1;
		int j = arr.length - 1;
		while (i < j) {

			temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}

		System.out.println(new String(arr));
	}

	private static void majorityELement3(int[] arr, int n) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		System.out.println(map);
		for (int k : map.keySet()) {
			if (map.get(k) > arr.length / n) {
				System.out.println(k);
			}
		}
	}

	private static void majorityElement2(int[] arr) {

		int v1 = arr[0];
		int c1 = 1;
		int v2 = 0;
		int c2 = 0;

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == v1) {
				c1++;
			} else if (arr[i] == v2) {
				c2++;
			} else {
				if (c1 == 0) {
					v1 = arr[i];
					c1 = 1;
				} else if (c2 == 0) {
					v2 = arr[i];
					c2 = 1;
				} else {
					c1--;
					c2--;
				}
			}
		}

		System.out.println(v1 + " " + v2);

	}

	private static void majorityElement1(int[] arr) {

		int v1 = arr[0];
		int c1 = 1;

		for (int i = 1; i < arr.length; i++) {

			if (arr[i] == v1) {
				c1++;
			} else if (c1 > 0) {
				c1--;
			} else {
				v1 = arr[i];
				c1 = 1;
			}
		}
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == v1) {
				count++;
			}
		}
		if (count >= arr.length / 2) {
			System.out.println("Majority Element: " + v1);
		} else {
			System.out.println("No Majority Element found");
		}
	}

	private static void squaresOfSortedArray(int[] arr) {

		int i = 0;
		int j = arr.length - 1;
		int k = arr.length - 1;

		int res[] = new int[arr.length];
		while (i <= j) {

			if (Math.abs(arr[i]) < Math.abs(arr[j])) {
				res[k--] = arr[j] * arr[j];
				j--;
			} else {
				res[k--] = arr[i] * arr[i];
				i++;
			}
		}

		for (i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void containerWithMostWater(int[] arr) {

		int max = 0;
		int i = 0;
		int j = arr.length - 1;

		while (i < j) {

			int water = Math.min(arr[i], arr[j]) * (j - i);
			if (arr[i] <= arr[j]) {
				i++;
			} else {
				j--;
			}
			max = Math.max(max, water);
			System.out.println(water);
		}

		System.out.println("Max Water: " + max);
	}

	private static void rangeAddition(int[][] arr, int n) {

		int res[] = new int[n + 1];
		for (int i = 0; i < arr.length; i++) {

			int st = arr[i][0];
			int et = arr[i][1];
			int val = arr[i][2];

			res[st] += val;
			res[et + 1] -= val;
		}

		for (int i = 1; i < res.length - 1; i++) {
			res[i] = res[i] + res[i - 1];
		}

		for (int i = 0; i < res.length - 1; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void faultyKeyboard(String s1, String s2) {

		int i = 0;
		int j = 0;
		boolean flag = s1.charAt(0) != s2.charAt(0);

		while (i < s1.length()) {

			if (j == s2.length()) {
				flag = true;
				break;
			}

			if (s1.charAt(i) == s2.charAt(j)) {
				i++;
				j++;
			} else {
				if (s2.charAt(j) != s2.charAt(j - 1)) {
					flag = true;
					break;
				}
				j++;
			}
		}

		while (j < s2.length()) {
			if (s2.charAt(j) != s2.charAt(j - 1)) {
				flag = true;
				break;
			}
			j++;
		}

		System.out.println("Is faulty keyboard: " + flag);
	}
}
