package com.javacode.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class MapDSA_4_55_65 {

	public static void main(String[] args) {

//		int arr[] = { 3, 7, 9, 16, 19 };
//		randomPickWithBlackList(20, arr);

//		int arr[] = { 1, 2, 0, 0, 1, 2 };
//		avoidFlood(arr);

//		int x[] = { 1, 2, 1 };
//		int y[] = { 2, 3, 3 };
//		numOfPairs(x, y, 3);

//		lengthOfLongestSubstringTwoDistinct("eceba");

//		int arr[][] = { { -3, 2 }, { -1, 4 }, { 5, 4 }, { 2, -3 }, { 2, 3 }, { 1, 1 }, { 3, 1 }, { 7, 2 } };
//		isReflected(arr);

//		int arr[] = { 4, 3, 2, 6 };
//		minCostToConnectSticks(arr);

//		int arr[] = { 3, 1, 2, 8, 9, 4, 7, 5, 6 };
//		trickySortCost(arr);

		reorganizeString("aaabc");
	}

	private static void reorganizeString(String str) {

		StringBuilder sb = new StringBuilder();

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (char ch : map.keySet()) {
			pq.add(new Pair(ch, map.get(ch)));
		}

		Pair block = pq.remove();
		sb.append(block.ch);
		block.freq--;
		boolean flag = true;

		while (pq.size() > 0) {

			Pair mp = pq.remove();

			sb.append(mp.ch);
			mp.freq--;

			if (block.freq > 0) {
				pq.add(block);
			}
			block = mp;
		}

		if (block.freq > 0) {
			flag = false;
		}

		System.out.println("Is Possible: " + flag);
		System.out.println(sb.toString());
	}

	private static class Pair implements Comparable<Pair> {

		int freq;
		char ch;

		public Pair(char ch, int freq) {
			this.ch = ch;
			this.freq = freq;
		}

		@Override
		public int compareTo(Pair o) {
			return o.freq - this.freq;
		}

	}

	private static void trickySortCost(int[] arr) {

		int ans = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {

			if (map.containsKey(arr[i] - 1)) {
				map.put(arr[i], map.get(arr[i] - 1) + 1);
			} else {
				map.put(arr[i], 1);
			}

			ans = Math.max(ans, map.get(arr[i]));
		}

		System.out.println("Ans: " + (arr.length - ans));
	}

	private static void minCostToConnectSticks(int[] arr) {

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			pq.add(arr[i]);
		}

		int cost = 0;
		while (pq.size() > 1) {

			int v1 = pq.remove();
			int v2 = pq.remove();

			int val = v1 + v2;

			pq.add(val);
			cost += val;
		}

		System.out.println("Min cost: " + cost);
	}

	private static void isReflected(int[][] arr) {

		Set<Integer> set = new HashSet();
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; i++) {

			min = Math.min(arr[i][0], min);
			max = Math.max(arr[i][0], max);

			int hash = arr[i][0] * 100000000 + arr[i][1];
			set.add(hash);
		}

		int mid = (min + max) / 2;
		System.out.println("Mid: " + mid);

		boolean flag = true;
		for (int i = 0; i < arr.length; i++) {

			int x = arr[i][0];
			int y = arr[i][1];
			int ximg = (2 * mid) - x;

			int hash = ximg * 100000000 + y;
			if (!set.contains(hash)) {
				flag = false;
				break;
			}
		}

		System.out.println("IsReflected: " + flag);
	}

	private static void lengthOfLongestSubstringTwoDistinct(String str) {

		Map<Character, Integer> map = new HashMap<>();
		int j = 0;
		int max = 0;

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			while (map.size() > 2) {

				char ch2 = str.charAt(j);
				if (map.get(ch2) == 1) {
					map.remove(ch2);
				} else {
					map.put(ch2, map.get(ch2) - 1);
				}
				j++;
			}

			if (map.size() <= 2) {
				max = Math.max(max, i - j + 1);
			}
		}

		System.out.println(max);
	}

	private static void numOfPairs(int[] x, int[] y, int n) {

		Map<Integer, Integer> xmap = new HashMap<>();
		Map<Integer, Integer> ymap = new HashMap<>();
		Map<String, Integer> xymap = new HashMap<>();

		int count = 0;
		for (int i = 0; i < x.length; i++) {

			String xy = x[i] + "-" + y[i];

			int xf = xmap.getOrDefault(x[i], 0);
			int yf = ymap.getOrDefault(y[i], 0);
			int xyf = xymap.getOrDefault(xy, 0);

			int sum = xf + yf - (2 * xyf);
			count += sum;

			xmap.put(x[i], xf + 1);
			ymap.put(y[i], yf + 1);
			xymap.put(xy, xyf + 1);

		}

		System.out.println("Count: " + count);
	}

	private static void avoidFlood(int[] arr) {

		int ans[] = new int[arr.length];

		Set<Integer> set = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		int dryIndex = 0;

		boolean flag = true;
		for (int i = 0; i < arr.length; i++) {

			ans[i] = -1;
			if (arr[i] == 0) {
				list.add(i);
			} else {
				if (set.contains(arr[i])) {
					if (dryIndex < list.size()) {
						ans[list.get(dryIndex++)] = arr[i];
					} else {
						flag = false;
						break;
					}
				} else {
					set.add(arr[i]);
				}
			}
		}

		System.out.println("Avoid flood: " + flag);
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
	}

	private static void randomPickWithBlackList(int n, int[] arr) {

		int idx = n - arr.length;
		Set<Integer> set = new HashSet<>();

		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], idx++);
		}

		System.out.println(map);
		System.out.println(idx);

		for (int i = 0; i < 10; i++) {

			int rand = (int) (Math.random() * n);
			if (map.containsKey(rand)) {
				System.out.println(map.get(rand));
			} else {
				System.out.println(rand);
			}
		}
	}

}
