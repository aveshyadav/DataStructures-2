package com.javacode.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapDSA_2_19_39 {

	public static void main(String[] args) {

//		String s1 = "cbaebabacd";
//		String s2 = "abc";
//		findAllAnagramInString(s1, s2);

//		String s1 = "geeks";
//		String s2 = "eggkf";
//		checkAnagram(s1, s2, 2);

//		int arr1[] = { 1, 2, 3, 4, 5, 2 };
//		int arr2[] = { 4, 3, 2, 1, 5, 2 };
//		findAnagramMappings(arr1, arr2);

//		validAnagrams("pepcoding", "codingpep");

//		String str[] = { "eat", "tea", "tan", "ate", "nat", "bat" };
//		groupAnagrams(str);

//		String str[] = { "acd", "dfg", "wyz", "yab", "mop", "bdfh", "a", "x", "moqs" };
//		groupShiftedStrings(str);

//		isIsomorphicString("abacba", "xyxzyx");

//		String s1 = "abba";
//		String s2 = "dog cat cat dog";
//		wordPattern(s1, s2);

//		int arr[] = { 3, 9, -2, 4, 1, -7, 2, 6, -5, 8, -3, -7, 6, 2, 1 };
//		subArraySumsEqualsK(arr, 5);

//		int arr[] = { -3, -9, -4, 8, 5, 4, 2, 6 };
//		int arr[] = { 2, 4, 8, 1, 7, 3, 6, 1, 9, 2, 7, 3 };
//		longestSubarrayWithSumDivisibleByK(arr, 7);

//		int arr[] = { 2, 3, 5, 4, 5, 3, 4 };
//		countSubarraySumDivisibleByK(arr, 7);

//		int arr[] = { 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 1, 1 };
//		largestContiguousArrayWith01(arr);

//		int arr[] = { 1, 0, 0, 1, 1, 0, 0, 1 };
//		countContiguousArrayWith01(arr);

//		int arr[] = { 0, 1, 0, 2, 0, 1, 0 };
//		largestContiguousArrayWith012(arr);
//		countArrayWith012(arr);

//		int arr[] = { 2, 9, 3, 5, 8, 6, 4 };
//		pairsWithEqualSum(arr);

		fractionToRecurringDecimal(7, -12);

//		int arr[] = { 1, 0, 1, 0, 0 };
//		int arr[] = { 2, 2, 3, 1, 0, 2, 2, 3, 1 };
//		rabbitsInForest(arr);

//		int arr[] = { 3, 1, 3, 6 };
//		doublePairArray(arr);

//		int arr[] = { 17, 9, 5, 29, 1, 25, 13, 37, 21, 33 };
//		arithmeticSequence(arr);
	}

	private static void arithmeticSequence(int[] arr) {

		Set<Integer> set = new HashSet<>();
		int min = Integer.MAX_VALUE;
		int max = 0;

		for (int i = 0; i < arr.length; i++) {

			set.add(arr[i]);
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}

		boolean flag = true;
		int d = (max - min) / (arr.length - 1);

		while (min <= max) {

			if (!set.contains(min)) {
				flag = false;
				break;
			}
			min += d;
		}

		System.out.println(flag);
	}

	private static void doublePairArray(int[] arr) {

		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		Collections.sort(list, (a, b) -> Math.abs(a) - Math.abs(b));
		for (int i = 0; i < arr.length; i++) {
			arr[i] = list.get(i);
		}

		boolean flag = true;
		for (int i = 0; i < arr.length; i++) {

			if (map.containsKey(arr[i])) {

				if (map.get(arr[i]) == 1) {
					map.remove(arr[i]);
				} else {
					map.put(arr[i], map.get(arr[i]) - 1);
				}

				if (map.containsKey(2 * arr[i])) {

					if (map.get(2 * arr[i]) == 1) {
						map.remove(2 * arr[i]);
					} else {
						map.put(2 * arr[i], map.get(2 * arr[i]) - 1);
					}
				} else {
					flag = false;
					break;
				}
			}
		}

		System.out.println(map);
		System.out.println("Can Pairs: " + flag);
	}

	private static void rabbitsInForest(int[] arr) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}
		System.out.println(map);

		int count = 0;
		for (int key : map.keySet()) {

			int val = map.get(key);
			key = key + 1;
			int rep = (val / key);
			if (val % key > 0) {
				rep++;
			}

			count += (rep * key);

		}
		System.out.println("Count: " + count);
	}

	private static void fractionToRecurringDecimal(int num, int denom) {

		StringBuilder sb = new StringBuilder();
		Map<Integer, Integer> map = new HashMap<>();

		if (num < 0 || denom < 0) {
			denom = Math.abs(denom);
			sb.append("-");
		}

		int quot = num / denom;
		sb.append(quot);

		int rem = num % denom;
		if (rem != 0) {
			sb.append(".");

			map.put(rem, sb.length());
			while (rem != 0) {

				while (rem < denom) {
					rem = rem * 10;
				}

				quot = rem / denom;
				sb.append(quot);
				rem = rem % denom;

				if (map.containsKey(rem)) {
					sb.insert(map.get(rem), "(");
					sb.insert(sb.length(), ")");
					break;
				}

				map.put(rem, sb.length());
			}
		}

		System.out.println("Result: " + sb.toString());
	}

	private static void pairsWithEqualSum(int[] arr) {

		Map<Integer, Boolean> map = new HashMap<>();

		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {

				int sum = arr[i] + arr[j];
				if (map.containsKey(sum)) {
					flag = true;
					break;
				}
				map.put(sum, true);
			}

			if (flag == true) {
				break;
			}
		}
		System.out.println("Pair exists: " + flag);
	}

	private static void countArrayWith012(int[] arr) {

		Map<String, Integer> map = new HashMap<>();
		map.put("0#0", 1);

		int zero = 0;
		int one = 0;
		int two = 0;
		int count = 0;

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == 0) {
				zero++;
			} else if (arr[i] == 1) {
				one++;
			} else {
				two++;
			}

			String delta = (one - zero) + "#" + (two - one);
			if (map.containsKey(delta)) {
				count += map.get(delta);
				map.put(delta, map.get(delta) + 1);
			} else {
				map.put(delta, 1);
			}
		}

		System.out.println("Count: " + count);

	}

	private static void largestContiguousArrayWith012(int[] arr) {

		Map<String, Integer> map = new HashMap<>();
		map.put("0#0", -1);

		int zero = 0;
		int one = 0;
		int two = 0;
		int max = 0;

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == 0) {
				zero++;
			} else if (arr[i] == 1) {
				one++;
			} else {
				two++;
			}

			String delta = (one - zero) + "#" + (two - one);
			if (map.containsKey(delta)) {
				max = Math.max(max, i - map.get(delta));
			} else {
				map.put(delta, i);
			}
		}

		System.out.println("Max: " + max);
	}

	private static void countContiguousArrayWith01(int[] arr) {

		int sum = 0;
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i] == 0 ? -1 : 1;
			if (map.containsKey(sum)) {
				count += map.get(sum);
				map.put(sum, map.get(sum) + 1);
			} else {
				map.put(sum, 1);
			}
		}

		System.out.println("Count: " + count);
	}

	private static void largestContiguousArrayWith01(int[] arr) {

		int sum = 0;
		int max = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i] == 0 ? -1 : 1;
			if (map.containsKey(sum)) {
				max = Math.max(max, i - map.get(sum));
			} else {
				map.put(sum, i);
			}
		}
		System.out.println("Max: " + max);
	}

	private static void countSubarraySumDivisibleByK(int[] arr, int k) {

		int sum = 0;
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			int mod = sum % k;
			mod = mod < 0 ? mod + k : mod;

			if (map.containsKey(mod)) {
				count += map.get(mod);
				map.put(mod, map.get(mod) + 1);
			} else {
				map.put(mod, 1);
			}
		}

		System.out.println("Count: " + count);
	}

	private static void longestSubarrayWithSumDivisibleByK(int[] arr, int k) {

		int sum = 0;
		int max = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			int mod = sum % k;
			mod = mod < 0 ? mod + k : mod;

			if (map.containsKey(mod)) {
				max = Math.max(max, i - map.get(mod));
			} else {
				map.put(mod, i);
			}

			System.out.println(map);
		}

		System.out.println("Ans: " + max);
	}

	private static void subArraySumsEqualsK(int[] arr, int k) {

		int sum = 0;
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];

			int find = sum - k;
			if (map.containsKey(find)) {
				count += map.get(find);
			}

			System.out.println(sum + " " + count);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}

		System.out.println("Ans: " + count);
	}

	private static void wordPattern(String s1, String s2) {

		boolean flag = true;

		String sarr[] = s2.split("\\s+");
		Map<Character, String> map1 = new HashMap<>();
		Map<String, Boolean> map2 = new HashMap<>();

		for (int i = 0; i < s1.length(); i++) {

			char c1 = s1.charAt(i);
			String str = sarr[i];

			if (map1.containsKey(c1)) {
				if (!map1.get(c1).equals(str)) {
					flag = false;
					break;
				}
			} else {
				if (map2.containsKey(str)) {
					flag = false;
					break;
				}
				map1.put(c1, str);
				map2.put(str, true);
			}
		}

		System.out.println("Is Word pattern matched: " + flag);
	}

	private static void isIsomorphicString(String s1, String s2) {

		Map<Character, Character> map1 = new HashMap<>();
		Map<Character, Boolean> map2 = new HashMap<>();

		boolean flag = true;
		for (int i = 0; i < s1.length(); i++) {

			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);

			if (map1.containsKey(c1)) {
				if (map1.get(c1) != c2) {
					flag = false;
					break;
				}
			} else {
				if (map2.containsKey(c2)) {
					flag = false;
					break;
				}
				map1.put(c1, c2);
				map2.put(c2, true);
			}
		}
		System.out.println("Is Isomorphic: " + flag);
	}

	private static void groupShiftedStrings(String[] str) {

		Map<String, List<String>> map = new HashMap<>();

		for (int i = 0; i < str.length; i++) {

			String key = "";
			for (int j = 0; j < str[i].length() - 1; j++) {

				char c1 = str[i].charAt(j);
				char c2 = str[i].charAt(j + 1);

				int diff = c2 - c1;
				if (diff < 0) {
					diff += 26;
				}

				key = key + diff + "#";
			}
			map.putIfAbsent(key, new ArrayList<>());
			map.get(key).add(str[i]);
		}

		for (String key : map.keySet()) {
			System.out.println(map.get(key));
		}
	}

	private static void groupAnagrams(String[] str) {

		Map<Map<Character, Integer>, List<String>> map = new HashMap<>();

		for (int i = 0; i < str.length; i++) {

			Map<Character, Integer> map2 = new HashMap<>();
			for (int j = 0; j < str[i].length(); j++) {
				char ch = str[i].charAt(j);
				map2.put(ch, map2.getOrDefault(ch, 0) + 1);
			}

			map.putIfAbsent(map2, new ArrayList<>());
			map.get(map2).add(str[i]);
		}

		for (Map<Character, Integer> key : map.keySet()) {
			System.out.println(map.get(key));
		}

	}

	private static void validAnagrams(String s1, String s2) {

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s1.length(); i++) {
			char ch = s1.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		boolean flag = true;
		for (int i = 0; i < s2.length(); i++) {

			char ch = s2.charAt(i);
			if (map.containsKey(ch)) {
				if (map.get(ch) == 1) {
					map.remove(ch);
				} else {
					map.put(ch, map.get(ch) - 1);
				}
			} else {
				flag = false;
				break;
			}
		}

		System.out.println("Valid Anagram: " + flag);
	}

	private static void findAnagramMappings(int[] arr1, int[] arr2) {

		int ans[] = new int[arr1.length];
		Map<Integer, Pair> map = new HashMap<>();

		for (int i = 0; i < arr2.length; i++) {

			map.putIfAbsent(arr2[i], new Pair(0, new ArrayList<>()));
			map.get(arr2[i]).list.add(i);
		}

		for (int i = 0; i < arr1.length; i++) {

			Pair mp = map.get(arr1[i]);
			int idx = mp.list.get(mp.idx);
			ans[i] = idx;
			mp.idx++;
		}

		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
	}

	private static class Pair {

		int idx;
		List<Integer> list;

		public Pair(int idx, List<Integer> list) {
			this.idx = idx;
			this.list = list;
		}
	}

	private static void checkAnagram(String s1, String s2, int k) {

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s1.length(); i++) {
			char ch = s1.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		int count = 0;
		for (int i = 0; i < s2.length(); i++) {

			char ch = s2.charAt(i);
			if (map.containsKey(ch)) {
				if (map.get(ch) == 1) {
					map.remove(ch);
				} else {
					map.put(ch, map.get(ch) - 1);
				}
			} else {
				count++;
			}
		}
		System.out.println("Count: " + count + " " + map);
		if (count <= k) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}

	private static void findAllAnagramInString(String s1, String s2) {

		Map<Character, Integer> pmap = new HashMap<>();
		for (int i = 0; i < s2.length(); i++) {

			char ch = s2.charAt(i);
			pmap.put(ch, pmap.getOrDefault(ch, 0) + 1);
		}

		int j = 0;
		Map<Character, Integer> cmap = new HashMap<>();
		for (int i = 0; i < s1.length(); i++) {

			char ch = s1.charAt(i);
			if (i < s2.length() - 1) {
				cmap.put(ch, cmap.getOrDefault(ch, 0) + 1);
			} else {

				cmap.put(ch, cmap.getOrDefault(ch, 0) + 1);

				boolean flag = true;
				for (int k = 0; k < s2.length(); k++) {
					char ch2 = s2.charAt(k);
					if (cmap.get(ch2) != pmap.get(ch2)) {
						flag = false;
						break;
					}
				}

				if (flag == true) {
					System.out.println(j + " " + cmap + " " + pmap);
				}

				char ch2 = s1.charAt(j);
				if (cmap.get(ch2) == 1) {
					cmap.remove(ch2);
				} else {
					cmap.put(ch2, cmap.get(ch2) - 1);
				}
				j++;
			}
		}
	}

}
