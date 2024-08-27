package com.javacode.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MapDSA_3_40_54 {

	public static void main(String[] args) {

//		int arr[] = { 1, 3, 2, 4, 2, 3, 4, 2, 5, 6, 5, 5, 7 };
//		smallestSubarrayWithHighestFrequency(arr);

//		int arr[] = { 2, 5, 6, 7, 9, 4 };
//		taskCompletion(arr, 15);

//		int arr[] = { 2, 9, 14, 17, 24, 32, 33, 38 };
//		pairsWithGivenSumArray(arr, 42);

//		int mat1[][] = { { 1, 5, 6 }, { 8, 10, 11 }, { 15, 16, 18 } };
//		int mat2[][] = { { 2, 4, 7 }, { 9, 10, 12 }, { 13, 16, 20 } };
//		pairsWithGivenSumMatrices(mat1, mat2, 21);

//		int arr[] = { 1, 0, -1, 0, -2, 2 };
//		quadrupletSum(arr, 0);

//		int a[] = { 1, 2 };
//		int b[] = { -2, -1 };
//		int c[] = { -1, 2 };
//		int d[] = { 0, 2 };
//		quadrupletSum2(a, b, c, d, 2);

//		powerfulNumbers(2, 3, 10);

//		String arr[] = { "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" };
//		subdomainVisits(arr);

//		firstNonRepeatingCharacter("abbcaddecfab");

//		int arr[] = { 1, 1, 1, 2, 2, 2, 3, 3, 3 };
//		hasGroupsSizeX(arr);

//		int arr[][] = { { 1, 2, 2, 1 }, { 3, 1, 2 }, { 1, 3, 2 }, { 2, 4 }, { 3, 1, 2 }, { 1, 3, 1, 1 } };
//		brickWall(arr);

		int arr[][] = { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };
//		int arr[][] = { { 0, 2, 3 }, { 2, 5, 3 } };
		getSkyline(arr);
	}

	private static void getSkyline(int[][] arr) {

		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {

			int st = arr[i][0];
			int et = arr[i][1];
			int ht = arr[i][2];

			list.add(new Pair(st, -ht));
			list.add(new Pair(et, ht));
		}

		Collections.sort(list);
		System.out.println(list);

		int cht = 0;
		List<Pair> ans = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		pq.add(0);

		for (int i = 0; i < list.size(); i++) {

			Pair mp = list.get(i);

			if (mp.ht < 0) {
				pq.add(-mp.ht);
			} else {
				pq.remove(mp.ht);
			}

			if (cht != pq.peek()) {
				cht = pq.peek();
				ans.add(new Pair(mp.idx, cht));
			}
		}

		System.out.println(ans);
	}

	private static class Pair implements Comparable<Pair> {

		int idx;
		int ht;

		public Pair(int idx, int ht) {
			this.idx = idx;
			this.ht = ht;
		}

		@Override
		public int compareTo(Pair o) {

			if (this.idx == o.idx) {
				return this.ht - o.ht;
			} else {
				return this.idx - o.idx;
			}
		}

		@Override
		public String toString() {
			return "[" + idx + "  " + ht + "]";
		}
	}

	private static void brickWall(int[][] arr) {

		int max = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {

			int val = arr[i][0];
			map.put(val, map.getOrDefault(val, 0) + 1);
			for (int j = 1; j < arr[i].length - 1; j++) {
				val += arr[i][j];
				map.put(val, map.getOrDefault(val, 0) + 1);
				max = Math.max(max, map.get(val));
			}
		}

		System.out.println(map);
		System.out.println("Ans: " + (arr.length - max));
	}

	private static void hasGroupsSizeX(int[] arr) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		int gcd = 0;
		for (int k : map.keySet()) {
			gcd = findGCD(gcd, map.get(k));
		}

		System.out.println(gcd);
		System.out.println(gcd > 1);
	}

	private static int findGCD(int a, int b) {

		if (b == 0) {
			return a;
		}
		return findGCD(b, a % b);
	}

	private static void firstNonRepeatingCharacter(String str) {

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		char ans = 'a';
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (map.get(ch) == 1) {
				ans = ch;
				break;
			}
		}

		System.out.println(ans);
	}

	private static void subdomainVisits(String[] arr) {

		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {

			String sarr[] = arr[i].split("\\s+");
			int count = Integer.parseInt(sarr[0]);
			String str = sarr[1];
			map.put(str, map.getOrDefault(str, 0) + count);

			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == '.') {
					String sub = str.substring(j + 1);
					map.put(sub, map.getOrDefault(sub, 0) + count);
				}
			}
		}

		System.out.println(map);
	}

	private static void powerfulNumbers(int x, int y, int bound) {

		Set<Integer> set = new HashSet<>();

		for (int i = 1; i < bound; i *= x) {
			for (int j = 1; i + j <= bound; j *= y) {

				set.add(i + j);
				if (y == 1) {
					break;
				}
			}
			if (x == 1) {
				break;
			}
		}

		System.out.println(set);
	}

	private static void quadrupletSum2(int[] a, int[] b, int[] c, int[] d, int target) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				int sum = a[i] + b[j];
				map.put(sum, map.getOrDefault(sum, 0) + 1);
			}
		}

		System.out.println(map);
		int count = 0;
		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < d.length; j++) {

				int sum = c[i] + d[j];
				if (map.containsKey(sum)) {

					if (map.get(sum) == 1) {
						map.remove(sum);
					} else {
						map.put(sum, map.get(sum) - 1);
					}
					count++;
				}
			}
		}

		System.out.println("Count: " + count);
	}

	private static void quadrupletSum(int[] arr, int target) {

		Arrays.sort(arr);
		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {

			if (i != 0 && arr[i] == arr[i - 1]) {
				continue;
			}

			int find = target - arr[i];
			List<List<Integer>> ans = threeSum(arr, i + 1, arr.length - 1, find);
			createResult(list, arr[i], ans);
		}

		System.out.println(list);
	}

	private static List<List<Integer>> threeSum(int arr[], int si, int ei, int target) {

		List<List<Integer>> list = new ArrayList<>();
		for (int i = si; i <= ei; i++) {

			if (i != si && arr[i] == arr[i - 1]) {
				continue;
			} else {

				int find = target - arr[i];
				List<List<Integer>> ans = twoSum(arr, i + 1, ei, find);
				createResult(list, arr[i], ans);
			}
		}

		return list;
	}

	private static List<List<Integer>> twoSum(int arr[], int si, int ei, int target) {

		List<List<Integer>> list = new ArrayList<>();

		while (si < ei) {

			int sum = arr[si] + arr[ei];
			if (sum == target) {

				List<Integer> temp = new ArrayList<>();
				temp.add(arr[si]);
				temp.add(arr[ei]);
				list.add(temp);

				si++;
				ei--;

				while (si < ei && arr[si - 1] == arr[si]) {
					si++;
				}
				while (si < ei && arr[ei + 1] == arr[ei]) {
					ei--;
				}
			} else if (sum < target) {
				si++;
			} else {
				ei--;
			}
		}

		return list;
	}

	private static void createResult(List<List<Integer>> list, int num, List<List<Integer>> ans) {

		for (List<Integer> ll : ans) {
			ll.add(num);
			list.add(ll);
		}

	}

	private static void pairsWithGivenSumMatrices(int[][] mat1, int[][] mat2, int target) {

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < mat1.length; i++) {
			for (int j = 0; j < mat1[0].length; j++) {
				set.add(mat1[i][j]);
			}
		}

		for (int i = 0; i < mat2.length; i++) {
			for (int j = 0; j < mat2[0].length; j++) {

				int v1 = target - mat2[i][j];
				int v2 = mat2[i][j];
				if (set.contains(v1)) {
					System.out.println(v1 + " " + v2);
				}
			}
		}
	}

	private static void pairsWithGivenSumArray(int[] arr, int target) {

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

	private static void taskCompletion(int[] arr, int n) {

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}

		int turn = 1;
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		for (int i = 1; i <= n; i++) {

			if (!set.contains(i)) {

				if (turn == 1) {
					l1.add(i);
					turn = 2;
				} else {
					l2.add(i);
					turn = 1;
				}
			}
		}

		System.out.println(l1);
		System.out.println(l2);
	}

	private static void smallestSubarrayWithHighestFrequency(int[] arr) {

		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, Integer> map2 = new HashMap<>();

		int max = 0;
		int si = 0;
		int ei = 0;
		int len = 0;

		for (int i = 0; i < arr.length; i++) {

			map2.putIfAbsent(arr[i], i);
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
			int freq = map.get(arr[i]);

			if (freq > max) {
				si = map2.get(arr[i]);
				ei = i;
				len = ei - si + 1;
				max = freq;
			} else if (freq == max) {

				int len2 = i - map2.get(arr[i]) + 1;
				if (len2 < len) {
					si = map2.get(arr[i]);
					ei = i;
					len = len2;
					max = freq;
				}
			}

		}

		System.out.println(max + " " + si + " " + ei + " " + len);
	}

}
