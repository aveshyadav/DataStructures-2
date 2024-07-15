package com.javacode.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Recursion_Day_4 {

	public static void main(String[] args) {

//		int n = 3;
//		int r = 2;
//		int boxes[] = new int[n];
//		printPermutation(boxes, 1, r);

//		int n = 4;
//		int r = 2;
//		int boxes[] = new int[n];
//		printCombination(boxes, 0, r, 0);

//		int n = 3;
//		int r = 2;
//		int boxes[] = new int[n];
//		permute2(boxes, 0, 0, r, new HashSet<>());

//		int n = 4;
//		int r = 2;
//		int boxes[] = new int[n];
//		combination2(boxes, 0, r, -1);

//		String str = "aabb";
//		Map<Character, Integer> map = new HashMap<>();
//		for (int i = 0; i < str.length(); i++) {
//			char ch = str.charAt(i);
//			map.put(ch, map.getOrDefault(ch, 0) + 1);
//		}
//		permutationString1(map, "", str.length());

//		String str = "aabb";
//		char boxes[] = new char[str.length()];
//		Arrays.fill(boxes, '-');
//		Map<Character, Integer> map = new HashMap<>();
//		for (int i = 0; i < str.length(); i++) {
//			map.put(str.charAt(i), -1);
//		}
//		permutationString2(boxes, str, map);

//		String str = "aabbbccdde";
//		Set<Character> set = new HashSet<>();
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < str.length(); i++) {
//			if (!set.contains(str.charAt(i))) {
//				set.add(str.charAt(i));
//				sb.append(str.charAt(i));
//			}
//		}
//
//		String ustr = sb.toString();
//		wordsSelection1(ustr, 3, "");
//		wordsSelection2(ustr, 3, "", -1);

//		String str = "abcabc";
//		Set<Character> set = new HashSet<>();
//		StringBuilder sb = new StringBuilder();
//		for (int i = 0; i < str.length(); i++) {
//			if (!set.contains(str.charAt(i))) {
//				set.add(str.charAt(i));
//				sb.append(str.charAt(i));
//			}
//		}
//
//		String ustr = sb.toString();
//		klengthWords1(ustr, 2, "");
//		Character arr[] = new Character[2];
//		klengthWords2(arr, ustr, 0, 2);
	}

	// Using Combination
	public static void klengthWords2(Character arr[], String ustr, int ci, int k) {

		if (ustr.length() == 0) {
			if (ci == k) {
				StringBuilder sb = new StringBuilder();
				for (char ch : arr) {
					sb.append(ch);
				}
				System.out.println(sb);
			}

			return;
		}

		char ch = ustr.charAt(0);
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == null) {
				arr[i] = ch;
				klengthWords2(arr, ustr.substring(1), ci + 1, k);
				arr[i] = null;
			}
		}
		klengthWords2(arr, ustr.substring(1), ci, k);
	}

	// Using Permutation
	public static void klengthWords1(String ustr, int k, String ans) {

		if (ans.length() == k) {
			System.out.println(ans);
			return;
		}
		for (int i = 0; i < ustr.length(); i++) {
			String ros = ustr.substring(0, i) + ustr.substring(i + 1);
			klengthWords1(ros, k, ans + ustr.charAt(i));
		}
	}

	// Using Permutation
	public static void wordsSelection2(String str, int n, String ans, int li) {

		if (ans.length() == n) {
			System.out.println(ans);
			return;
		}

		for (int i = li + 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			wordsSelection2(str, n, ans + ch, i);
		}

	}

	// Using Combination
	public static void wordsSelection1(String str, int n, String ans) {

		if (str.length() == 0) {
			if (ans.length() == n) {
				System.out.println(ans);
			}
			return;
		}

		wordsSelection1(str.substring(1), n, ans);
		wordsSelection1(str.substring(1), n, ans + str.charAt(0));
	}

	// Using Permutation
	public static void permutationString2(char boxes[], String str, Map<Character, Integer> map) {

		if (str.length() == 0) {
			for (int i = 0; i < boxes.length; i++) {
				System.out.print(boxes[i]);
			}
			System.out.println();
			return;
		}

		char ch = str.charAt(0);
		int li = map.get(ch);
		for (int i = li + 1; i < boxes.length; i++) {

			if (boxes[i] == '-') {
				boxes[i] = ch;
				map.put(ch, i);
				permutationString2(boxes, str.substring(1), map);
				map.put(ch, li);
				boxes[i] = '-';
			}
		}
	}

	// Using Combination
	public static void permutationString1(Map<Character, Integer> map, String ans, int ti) {

		if (ans.length() == ti) {
			System.out.println(ans);
			return;
		}

		for (char ch : map.keySet()) {
			if (map.get(ch) > 0) {
				map.put(ch, map.get(ch) - 1);
				permutationString1(map, ans + ch, ti);
				map.put(ch, map.get(ch) + 1);
			}
		}
	}

	public static void combination2(int boxes[], int ssf, int ti, int li) {

		if (ssf == ti) {
			for (int i = 0; i < boxes.length; i++) {
				System.out.print(boxes[i]);
			}
			System.out.println();
			return;
		}

		for (int i = li + 1; i < boxes.length; i++) {

			if (boxes[i] == 0) {
				boxes[i] = 1;
				combination2(boxes, ssf + 1, ti, i);
				boxes[i] = 0;
			}
		}
	}

	public static void permute2(int boxes[], int idx, int ssf, int ti, Set<Integer> set) {

		if (idx == boxes.length) {
			if (ssf == ti) {
				for (int i = 0; i < boxes.length; i++) {
					System.out.print(boxes[i]);
				}
				System.out.println();
			}
			return;
		}

		permute2(boxes, idx + 1, ssf, ti, set);
		for (int i = 1; i <= ti; i++) {
			if (boxes[idx] == 0 && !set.contains(i)) {
				boxes[idx] = i;
				set.add(i);
				permute2(boxes, idx + 1, ssf + 1, ti, set);
				boxes[idx] = 0;
				set.remove(i);

			}
		}
	}

	public static void printCombination(int boxes[], int idx, int ti, int ssf) {

		if (idx == boxes.length) {
			if (ssf == ti) {
				for (int i = 0; i < boxes.length; i++) {
					System.out.print(boxes[i]);
				}
				System.out.println();
			}
			return;
		}

		printCombination(boxes, idx + 1, ti, ssf);

		boxes[idx] = 1;
		printCombination(boxes, idx + 1, ti, ssf + 1);
		boxes[idx] = 0;
	}

	public static void printPermutation(int boxes[], int ci, int ti) {

		if (ci > ti) {
			for (int i = 0; i < boxes.length; i++) {
				System.out.print(boxes[i]);
			}
			System.out.println();
			return;
		}

		for (int i = 0; i < boxes.length; i++) {

			if (boxes[i] == 0) {
				boxes[i] = ci;
				printPermutation(boxes, ci + 1, ti);
				boxes[i] = 0;
			}
		}
	}
}
