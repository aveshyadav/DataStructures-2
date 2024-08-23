package com.javacode.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapDSA_1_1_18 {

	public static void main(String[] args) {

//		Map<String, String> map = new HashMap<>();
//		map.put("A", "C");
//		map.put("B", "C");
//		map.put("C", "F");
//		map.put("D", "E");
//		map.put("E", "F");
//		map.put("F", "F");
//		findCount(map);

//		String arr[][] = { { "MUC", "LHR" }, { "JFK", "MUC" }, { "SFO", "SJC" }, { "LHR", "SFO" } };
//		findItinerary(arr);

//		int arr[] = { 1, 2, 3, 4, 5, 10, 6, 7, 8, 9 };
//		canPairs(arr, 5);

//		int arr[] = { 1, 2, 1, 3, 4, 2, 3 };
//		countDistinctElement(arr, 4);

//		int arr[] = { 2, 8, -3, -5, 2, -4, 6, 1, 2, 1, -3, 4 };
//		int arr[] = { 15, -2, 2, -8, 1, 7, 10, 23 };
//		int arr[] = { 0, 0, 1, 1, 0, 0 };
//		largestSubarrayWith0Sum(arr);
//		countZeroSumSubArray(arr);

//		int arr[] = { 7, 2, 5, 6, 23, 24, 22, 23, 19, 17, 16, 18, 39, 0 };
//		largestSubarrayWithContigousElement(arr);

//		String s1 = "ADOBECODEBANC";
//		String s2 = "ABC";
//		minimumWindowSubstring(s1, s2);

//		String s1 = "aabcbcdbca";
//		String s1 = "bbacacdcbbcaadcdca";
//		minimumWindowSubstring2(s1);

//		String s1 = "abcabcdbb";
//		longestSubstringWithoutRepeatingCharacter(s1);

//		String s1 = "aabcbcdbca"; // 24
//		countSubstringWithoutRepeatingCharacter(s1);

//		String s1 = "aabcbcdbca";
//		longestSubstringWithExactlyKCharacters(s1, 2);

//		String s1 = "aabcbcdbca";
//		countSubstringWithKDistinctCharacter(s1, 2);

//		int arr[] = { 2, 5, 3, 5, 2, 4, 1, 3, 1, 4 };
//		int arr[] = { 2, 1, 3, 2, 3 };
//		countEquivalentSubarray(arr);

//		int arr[] = { 1, 1, 0, 0, 1, 1 };
//		int arr[] = { 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1 };
//		maxConsecutiveOnes(arr, 2);
//		maxConsecutiveOnes2(arr);

//		String s1 = "aabcbcdbca";
//		longestSubstringAtMostKCharacter(s1, 2);
//		countSubstringAtMostKCharacter(s1, 2)

	}

	private static void countSubstringAtMostKCharacter(String str, int k) {

		int j = 0;
		int count = 0;
		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			while (map.size() > k) {

				char ch2 = str.charAt(j);
				if (map.get(ch2) == 1) {
					map.remove(ch2);
				} else {
					map.put(ch2, map.get(ch2) - 1);
				}
				j++;
			}

			count += i - j + 1;
		}
		System.out.println("Count: " + count);
	}

	private static void longestSubstringAtMostKCharacter(String str, int k) {

		int j = 0;
		int longest = 0;
		String ans = "";
		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			while (map.size() > k) {

				char ch2 = str.charAt(j);
				if (map.get(ch2) == 1) {
					map.remove(ch2);
				} else {
					map.put(ch2, map.get(ch2) - 1);
				}
				j++;
			}

			if (map.size() <= k) {
				String sub = str.substring(j, i + 1);
				if (sub.length() > ans.length()) {
					ans = sub;
				}
				longest = Math.max(longest, i - j + 1);
			}
		}

		System.out.println("Ans: " + longest + ", " + ans);
	}

	private static void maxConsecutiveOnes2(int[] arr) {

		int j = 0;
		int ans = 0;
		int zero = 0;

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == 1) {
				ans = Math.max(ans, i - j + 1);
			} else {

				zero++;
				if (zero == 1) {
					ans = Math.max(ans, i - j + 1);
				} else if (zero > 1) {
					while (arr[j] != 0) {
						j++;
					}
					j++;
					zero--;
				}
			}

		}

		System.out.println("Ans: " + ans);
	}

	private static void maxConsecutiveOnes(int[] arr, int k) {

		int j = 0;
		int zero = 0;
		int ans = 0;
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == 1) {
				ans = Math.max(ans, i - j + 1);
			} else if (arr[i] == 0) {
				zero++;
				if (zero <= k) {
					ans = Math.max(ans, i - j + 1);
				} else {
					while (arr[j] != 0) {
						j++;
					}
					j++;
					zero--;
				}
			}
		}

		System.out.println("Ans: " + ans);
	}

	private static void countEquivalentSubarray(int[] arr) {

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}

		int j = 0;
		int count = 0;
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {

			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

			while (map.size() == set.size()) {

				count += arr.length - i;
				if (map.get(arr[j]) == 1) {
					map.remove(arr[j]);
				} else {
					map.put(arr[j], map.get(arr[j]) - 1);
				}
				j++;
			}
		}

		System.out.println("Count: " + count);
	}

	private static void countSubstringWithKDistinctCharacter(String str, int k) {

		int atMostKDistinct = countSubstringWithKDistinctCharacterHelper(str, k);
		int atMostKm1Distinct = countSubstringWithKDistinctCharacterHelper(str, k - 1);

		int ans = atMostKDistinct - atMostKm1Distinct;
		System.out.println("Ans: " + ans);
	}

	private static int countSubstringWithKDistinctCharacterHelper(String str, int k) {

		int j = 0;
		int count = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			while (map.size() > k) {

				char ch2 = str.charAt(j);
				if (map.get(ch2) == 1) {
					map.remove(ch2);
				} else {
					map.put(ch2, map.get(ch2) - 1);
				}
				j++;
			}

			count += i - j + 1;
		}
		return count;
	}

	private static void longestSubstringWithExactlyKCharacters(String str, int k) {

		int j = 0;
		int ans = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			while (map.size() > k) {

				char ch2 = str.charAt(j);
				if (map.get(ch2) == 1) {
					map.remove(ch2);
				} else {
					map.put(ch2, map.get(ch2) - 1);
				}
				j++;
			}

			if (map.size() == k) {
				ans = Math.max(ans, i - j + 1);
			}
		}

		System.out.println("Ans: " + ans);
	}

	private static void countSubstringWithoutRepeatingCharacter(String str) {

		int j = 0;
		int count = 0;
		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			if (map.get(ch) == 1) {
				count += i - j + 1;
			} else {

				while (map.get(ch) == 2) {

					char ch2 = str.charAt(j++);
					if (map.get(ch2) == 1) {
						map.remove(ch2);
					} else {
						map.put(ch2, map.get(ch2) - 1);
					}
				}
				count += i - j + 1;
			}
		}

		System.out.println("Count: " + count);
	}

	private static void longestSubstringWithoutRepeatingCharacter(String str) {

		int j = 0;
		String ans = "";
		Map<Character, Integer> map = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			if (map.get(ch) == 1) {

				String sub = str.substring(j, i + 1);
				if (sub.length() > ans.length()) {
					ans = sub;
				}
			} else {
				while (map.get(ch) == 2) {

					char ch2 = str.charAt(j);
					if (map.get(ch2) == 1) {
						map.remove(ch2);
					} else {
						map.put(ch2, map.get(ch2) - 1);

					}
					j++;

				}
			}
		}

		System.out.println("Ans: " + ans);
	}

	private static void minimumWindowSubstring2(String str) {

		Set<Character> set = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			set.add(str.charAt(i));
		}

		Map<Character, Integer> map = new HashMap<>();
		String ans = str;
		int j = 0;

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);

			while (map.size() == set.size()) {

				String sub = str.substring(j, i + 1);
				System.out.println(sub);
				if (sub.length() < ans.length()) {
					ans = sub;
				}

				char ch2 = str.charAt(j);
				if (map.get(ch2) == 1) {
					map.remove(ch2);
				} else {
					map.put(ch2, map.get(ch2) - 1);
				}

				j++;
			}
		}

		System.out.println("Ans: " + ans);
	}

	private static void minimumWindowSubstring(String s1, String s2) {

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s2.length(); i++) {
			char ch = s2.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		int count = 0;
		int ans = Integer.MAX_VALUE;
		int j = 0;

		Map<Character, Integer> map2 = new HashMap<>();
		for (int i = 0; i < s1.length(); i++) {

			char ch = s1.charAt(i);
			map2.put(ch, map2.getOrDefault(ch, 0) + 1);
			if (map.containsKey(ch)) {
				if (map.get(ch) >= map2.get(ch)) {
					count++;
				}
			}

			while (count == s2.length()) {

				ans = Math.min(ans, i - j + 1);
				System.out.println(s1.substring(j, i + 1));

				char ch2 = s1.charAt(j);
				int freq = map2.get(ch2);
				if (freq == 0) {
					map2.remove(ch2);
					if (map.containsKey(ch2)) {
						count--;
					}
				} else {
					map2.put(ch2, freq - 1);
					if (map.containsKey(ch2) && map.get(ch2) > map2.get(ch2)) {
						count--;
					}
				}

				j++;
			}

		}

		System.out.println(ans);
	}

	private static void largestSubarrayWithContigousElement(int[] arr) {

		int count = 1;
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {

			int min = arr[i];
			int max = arr[i];
			set = new HashSet<>();

			for (int j = i + 1; j < arr.length; j++) {

				if (set.contains(arr[j])) {
					break;
				}

				set.add(arr[j]);
				min = Math.min(min, arr[j]);
				max = Math.max(max, arr[j]);

				if (max - min == (j - i)) {

					System.out.println(i + " " + j);
					count = Math.max(count, j - i + 1);
				}
			}
		}

		System.out.println("Ans: " + count);
	}

	private static void countZeroSumSubArray(int[] arr) {

		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		int sum = 0;
		int count = 0;

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			if (map.containsKey(sum)) {
				count += map.get(sum);
				map.put(sum, map.get(sum) + 1);
			} else {
				map.put(sum, 1);
			}
		}
		System.out.println("Count: " + count);
	}

	private static void largestSubarrayWith0Sum(int[] arr) {

		Map<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int sum = 0;
		int max = 0;

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			if (map.containsKey(sum)) {
				max = Math.max(max, i - map.get(sum));
			} else {
				map.put(sum, i);
			}
		}

		System.out.println("Max: " + max);
	}

	private static void countDistinctElement(int[] arr, int k) {

		Map<Integer, Integer> map = new HashMap<>();

		int j = 0;
		for (int i = 0; i < arr.length; i++) {

			if (i < k - 1) {
				map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
			} else {
				map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
				System.out.print(map.size() + " ");

				int count = map.get(arr[j]);
				if (count == 1) {
					map.remove(arr[j]);
				} else {
					map.put(arr[j], count - 1);
				}
				j++;
			}
		}

	}

	private static void canPairs(int[] arr, int k) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {

			int rem = arr[i] % k;
			map.put(rem, map.getOrDefault(rem, 0) + 1);
		}
		System.out.println(map);

		boolean flag = true;
		for (int key : map.keySet()) {

			if (key == 0) {
				if (map.get(key) % 2 != 0) {
					flag = false;
				}
				break;
			} else {

				int find = k - key;
				if (find == k) {
					if (map.get(key) % 2 != 0) {
						flag = false;
					}
					break;
				} else {
					if (map.get(key) != map.get(find)) {
						flag = false;
						break;
					}
				}
			}
		}
		System.out.println("Can Pairs: " + flag);
	}

	private static void findItinerary(String[][] arr) {

		Map<String, String> map = new HashMap<>();
		Map<String, Boolean> map2 = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {

			String src = arr[i][0];
			String dest = arr[i][1];

			map.put(src, dest);

			map2.putIfAbsent(src, true);
			map2.put(dest, false);
		}

		String start = null;
		for (String key : map2.keySet()) {
			if (map2.get(key) == true) {
				start = key;
			}
		}

		StringBuilder sb = new StringBuilder();
		while (start != null) {
			sb.append(start).append("->");
			start = map.get(start);
		}

		System.out.println(sb.toString());
	}

	private static void findCount(Map<String, String> map) {

		String ceo = null;
		Map<String, List<String>> tree = new HashMap<>();
		for (String key : map.keySet()) {

			if (key.equals(map.get(key))) {
				ceo = key;
			} else {
				tree.putIfAbsent(map.get(key), new ArrayList<>());
				tree.get(map.get(key)).add(key);
			}
		}

		System.out.println(tree);

		Map<String, Integer> res = new HashMap<>();
		dfs(tree, ceo, res);
		System.out.println(res);

	}

	private static int dfs(Map<String, List<String>> tree, String ceo, Map<String, Integer> res) {

		if (tree.containsKey(ceo) == false) {
			res.put(ceo, 0);
			return 1;
		}

		int sz = 0;
		for (String child : tree.get(ceo)) {
			int cs = dfs(tree, child, res);
			sz += cs;
		}

		res.put(ceo, sz);
		return sz + 1;
	}

}
