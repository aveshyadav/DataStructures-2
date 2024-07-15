package com.javacode.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Recursion_Day_3 {

	public static void main(String[] args) {

//		int n = 4;
//		int k = 3;
//		List<List<Integer>> list = new ArrayList<>();
//		for (int i = 0; i < k; i++) {
//			list.add(new ArrayList<>());
//		}
//
//		paritionInKSubset(1, n, k, 0, list);

//		String str = "aabbcadad";
//		Map<Character, Integer> map = new HashMap<>();
//		for (int i = 0; i < str.length(); i++) {
//			map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
//		}
//
//		int count = 0;
//		char odd = '-';
//		int cc = 0;
//		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
//
//			char ch = entry.getKey();
//			int val = entry.getValue();
//
//			if (val % 2 != 0) {
//				odd = ch;
//				count++;
//			}
//
//			map.put(ch, val / 2);
//			cc += val/2;
//		}
//
//		if (count < 2) {
//			palindromicPermutation(map, odd, "", cc);
//		} else {
//			System.out.println("Invalid Data");
//		}

//		String str = "abaaba";
//		palindromicPartitioning(str, "");

//		int k = 3;
//		int arr[] = { 1, 2, 3, 4, 5, 6 };
//		int arr[] = { 9, 5, 4, 9 };

//		List<List<Integer>> list = new ArrayList<>();
//		for (int i = 0; i < k; i++) {
//			list.add(new ArrayList<>());
//		}
//		equalSumSubsets(arr, 0, k, 0, list);

//		String str = "graphtreesgraph";
//		String pattern = "pep";
//
//		Map<Character, String> map = new HashMap<>();
//		wordPatternMatching(str, pattern, map);

//		String word = "i like pep coding pepper eating mango man go in pepcoding";
//		String ques = "ilikepeppereatingmangoinpepcoding";
//
//		Set<String> set = new HashSet<>();
//		for (String str : word.split(" ")) {
//			set.add(str);
//		}
//		wordBreakProblem(ques, "", set);

//		String str = "(a)())()((";
//		int min = minRemove(str);
//		Set<String> set = new HashSet<>();
//		removeInvalidParenthesis(str, min, set);
//		System.out.println(set);

//		String str = "1234567";
//		ans = str;
//		maxNumberAfterKSwaps(str, 4);
//		System.out.println("Ans: " + ans);

		int arr[] = { 1, 2, 3, 4, 5, 6 };
		minSubsetSumDifference(arr, 0, new ArrayList<>(), new ArrayList<>());

		System.out.println(l1);
		System.out.println(l2);
		System.out.println(diffNum);
	}

	private static List<Integer> l1 = null;
	private static List<Integer> l2 = null;
	private static int diffNum = Integer.MAX_VALUE;

	public static void minSubsetSumDifference(int arr[], int idx, List<Integer> list1, List<Integer> list2) {

		if (idx == arr.length) {

			int s1 = list1.stream().reduce((a, b) -> a + b).get();
			int s2 = list2.stream().reduce((a, b) -> a + b).get();

			int diff = Math.abs(s1 - s2);
			if (diff < diffNum) {

				diffNum = diff;
				l1 = new ArrayList<>(list1);
				l2 = new ArrayList<>(list2);
			}
			return;
		}

		if (list1.size() < (arr.length + 1) / 2) {
			list1.add(arr[idx]);
			minSubsetSumDifference(arr, idx + 1, list1, list2);
			list1.remove(list1.size() - 1);
		}

		if (list2.size() < (arr.length + 1) / 2) {
			list2.add(arr[idx]);
			minSubsetSumDifference(arr, idx + 1, list1, list2);
			list2.remove(list2.size() - 1);
		}
	}

	public static String ans = "";

	public static void maxNumberAfterKSwaps(String str, int max) {

		if (Integer.parseInt(str) > Integer.parseInt(ans)) {
			ans = str;
		}
		if (max == 0) {
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j < str.length(); j++) {
				if (str.charAt(j) > str.charAt(i)) {

					char arr[] = str.toCharArray();
					char temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;

					maxNumberAfterKSwaps(new String(arr), max - 1);
				}
			}
		}
	}

	public static void removeInvalidParenthesis(String str, int min, Set<String> set) {

		if (min == 0) {
			if (minRemove(str) == 0) {
				set.add(str);
			}
			return;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == '(' || ch == ')') {
				String ros = str.substring(0, i) + str.substring(i + 1);
				removeInvalidParenthesis(ros, min - 1, set);
			}
		}

	}

	public static int minRemove(String str) {

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {
				if (stack.size() > 0 && stack.peek() == '(') {
					stack.pop();
				} else {
					stack.push(ch);
				}
			}
		}
		return stack.size();
	}

	public static void wordBreakProblem(String ques, String ans, Set<String> set) {

		if (ques.length() == 0) {
			System.out.println(ans);
			return;
		}

		for (int i = 0; i < ques.length(); i++) {
			String sub = ques.substring(0, i + 1);
			if (set.contains(sub)) {
				wordBreakProblem(ques.substring(i + 1), ans + sub + " ", set);
			}
		}
	}

	public static void wordPatternMatching(String str, String pattern, Map<Character, String> map) {

		if (pattern.length() == 0) {
			if (str.length() == 0) {
				System.out.println(map);
			}
			return;
		}

		char ch = pattern.charAt(0);
		String rop = pattern.substring(1);

		if (map.get(ch) != null) {
			String ep = map.get(ch);
			if (ep.length() <= str.length() && ep.equals(str.substring(0, ep.length()))) {
				wordPatternMatching(str.substring(ep.length()), rop, map);
			}
		} else {
			for (int i = 0; i < str.length(); i++) {

				String sub = str.substring(0, i + 1);
				map.put(ch, sub);
				wordPatternMatching(str.substring(i + 1), rop, map);
				map.remove(ch);
			}
		}
	}

	public static void equalSumSubsets(int arr[], int idx, int k, int csf, List<List<Integer>> ans) {

		if (idx == arr.length) {
			if (csf == k) {

				boolean flag = true;
				int sum = ans.get(0).stream().reduce((a, b) -> a + b).get();
				for (int i = 1; i < ans.size(); i++) {

					int sum2 = ans.get(i).stream().reduce((a, b) -> a + b).get();
					if (sum != sum2) {
						flag = false;
					}
				}

				if (flag) {
					System.out.println(ans);
				}
			}
			return;
		}

		for (int i = 0; i < ans.size(); i++) {

			if (ans.get(i).size() > 0) {
				ans.get(i).add(arr[idx]);
				equalSumSubsets(arr, idx + 1, k, csf, ans);
				ans.get(i).remove(ans.get(i).size() - 1);
			} else {
				ans.get(i).add(arr[idx]);
				equalSumSubsets(arr, idx + 1, k, csf + 1, ans);
				ans.get(i).remove(ans.get(i).size() - 1);
				break;
			}
		}
	}

	public static void palindromicPartitioning(String ques, String ans) {

		if (ques.length() == 0) {
			System.out.println(ans);
			return;
		}

		for (int i = 0; i < ques.length(); i++) {

			String sub = ques.substring(0, i + 1);
			String rev = new StringBuilder(sub).reverse().toString();

			if (sub.equals(rev)) {
				palindromicPartitioning(ques.substring(i + 1), ans + "(" + sub + ")");
			}
		}
	}

	public static void palindromicPermutation(Map<Character, Integer> map, char odd, String ans, int count) {

		if (count == ans.length()) {

			StringBuilder sb = new StringBuilder();
			sb.append(ans);
			if (odd != '-') {
				sb.append(odd);
			}
			sb.append(new StringBuilder(ans).reverse());
			System.out.println(sb);

			return;
		}

		for (Character key : map.keySet()) {
			if (map.get(key) > 0) {
				map.put(key, map.get(key) - 1);
				palindromicPermutation(map, odd, ans + key, count);
				map.put(key, map.get(key) + 1);
			}
		}
	}

	public static void paritionInKSubset(int curr, int max, int k, int i, List<List<Integer>> list) {

		if (curr > max) {
			if (i == k) {
				System.out.println(list);
			}
			return;
		}

		for (int j = 0; j < list.size(); j++) {

			if (list.get(j).size() > 0) {
				list.get(j).add(curr);
				paritionInKSubset(curr + 1, max, k, i, list);
				list.get(j).remove(list.get(j).size() - 1);
			} else {
				list.get(j).add(curr);
				paritionInKSubset(curr + 1, max, k, i + 1, list);
				list.get(j).remove(list.get(j).size() - 1);
				break;
			}
		}
	}

}
