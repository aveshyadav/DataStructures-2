package com.javacode.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MapDSA_1_1_1 {

	public static void main(String[] args) {

//		highestFrequenceyCharacter("abracadabra");

//		int arr1[] = { 1, 1, 2, 2, 2, 3, 5 };
//		int arr2[] = { 1, 1, 1, 2, 2, 4, 5 };
//		getCommonElement1(arr1, arr2);
//		getCommonElement2(arr1, arr2);

//		int arr[] = { 10, 5, 9, 1, 11, 8, 6, 3, 12, 2 };
//		longestConsecutiveSequence(arr);

//		int arr[] = { 2, 10, 5, 17, 7, 18, 6, 4 };
//		findkLargestElement(arr, 3);

//		int arr[] = { 2, 3, 1, 4, 6, 7, 5, 8, 9 };
//		sortNearlySortedArray(arr, 2);

		List<Integer> l1 = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			l1.add(i * 10);
		}

		List<Integer> l4 = new ArrayList<>();
		l4.add(5);
		l4.add(7);
		l4.add(9);
		l4.add(11);
		l4.add(19);
		l4.add(55);
		l4.add(57);

		List<Integer> l2 = new ArrayList<>();
		l2.add(1);
		l2.add(2);
		l2.add(3);

		List<Integer> l3 = new ArrayList<>();
		l3.add(32);
		l3.add(36);

		List<List<Integer>> list = new ArrayList<>();
		list.add(l1);
		list.add(l2);
		list.add(l3);
		list.add(l4);

		mergeKSortedList(list);
	}

	private static void mergeKSortedList(List<List<Integer>> list) {

		PriorityQueue<Pair> pq = new PriorityQueue<>();

		for (int i = 0; i < list.size(); i++) {
			pq.add(new Pair(list.get(i).get(0), i, 0));
		}

		while (pq.size() > 0) {

			Pair mp = pq.remove();
			System.out.print(mp.data + " ");

			List<Integer> ll = list.get(mp.li);
			if (mp.di < ll.size() - 1) {
				pq.add(new Pair(list.get(mp.li).get(mp.di + 1), mp.li, mp.di + 1));
			}
		}
	}

	private static class Pair implements Comparable<Pair> {

		int li;
		int di;
		int data;

		public Pair(int data, int li, int di) {

			this.li = li;
			this.di = di;
			this.data = data;
		}

		@Override
		public int compareTo(Pair o) {
			return this.data - o.data;
		}
	}

	private static void sortNearlySortedArray(int[] arr, int k) {

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int idx = 0;
		for (int i = 0; i < arr.length; i++) {

			if (i < k) {
				pq.add(arr[i]);
			} else {
				pq.add(arr[i]);
				arr[idx++] = pq.remove();
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	private static void findkLargestElement(int[] arr, int k) {

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (int i = 0; i < arr.length; i++) {
			if (pq.size() < k) {
				pq.add(arr[i]);
			} else {
				if (arr[i] > pq.peek()) {
					pq.remove();
					pq.add(arr[i]);
				}
			}
		}

		while (pq.size() > 0) {
			System.out.print(pq.remove() + " ");
		}
	}

	private static void longestConsecutiveSequence(int[] arr) {

		Map<Integer, Boolean> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], true);
		}

		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i] - 1)) {
				map.put(arr[i], false);
			}
		}

		int max = 0;
		int start = 0;
		for (int i : map.keySet()) {

			if (map.get(i) == true) {

				int count = 0;
				int idx = i;
				while (map.containsKey(idx++)) {
					count++;
				}

				if (count > max) {
					max = count;
					start = i;
				}
			}
		}
		System.out.println("Start: " + start + ", Max: " + max);
	}

	private static void getCommonElement2(int[] arr1, int[] arr2) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr1.length; i++) {
			map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
		}

		for (int i = 0; i < arr2.length; i++) {
			if (map.containsKey(arr2[i]) && map.get(arr2[i]) > 0) {
				System.out.print(arr2[i] + " ");
				map.put(arr2[i], map.get(arr2[i]) - 1);
			}
		}
	}

	private static void getCommonElement1(int[] arr1, int[] arr2) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr1.length; i++) {
			map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
		}

		for (int i = 0; i < arr2.length; i++) {
			if (map.containsKey(arr2[i])) {
				System.out.print(arr2[i] + " ");
			}
			map.remove(arr2[i]);
		}
	}

	private static void highestFrequenceyCharacter(String str) {

		Map<Character, Integer> map = new HashMap<>();
		char maxChar = 'a';
		int freq = 0;

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			if (freq < map.get(ch)) {
				maxChar = ch;
				freq = map.get(ch);
			}
		}

		System.out.println(map);
		System.out.println("Char : " + maxChar + ", Freq: " + freq);
	}

}
