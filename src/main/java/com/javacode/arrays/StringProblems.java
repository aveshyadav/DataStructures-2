package com.javacode.arrays;

import java.util.ArrayList;
import java.util.List;

public class StringProblems {

	public static void main(String[] args) {

//		String str = "abccbc";
//		printAllPalindromicSubstring(str);

//		String str = "aaabbcccadde";
//		stringCompression(str);

//		List<Integer> list = new ArrayList<>();
//		list.add(7);
//		list.add(18);
//		list.add(9);
//		list.add(14);
//		list.add(19);
//		list.add(31);
//		list.add(72);

//		removeOddElements(list);
//		removePrime(list);

//		String str = "pepCODing";
//		toggleCaseCharacter(str);

//		String str = "abecd";
//		printAscii(str);

		String str = "abc";
		stringPermutation(str, "");
	}

	private static void stringPermutation(String str, String ans) {

		if (str.length() == 0) {
			System.out.println(ans);
			return;
		}

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			String ros = str.substring(0, i) + str.substring(i + 1);
			stringPermutation(ros, ans + ch);
		}
	}

	private static void printAscii(String str) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length() - 1; i++) {

			char c1 = str.charAt(i);
			char c2 = str.charAt(i + 1);

			int diff = c2 - c1;
			sb.append(c1);
			sb.append(diff);

		}
		sb.append(str.charAt(str.length() - 1));

		System.out.println(sb.toString());
	}

	private static void toggleCaseCharacter(String str) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (ch >= 'a') {
				ch = (char) (ch - 32);
				sb.append(ch);
			} else {
				ch = (char) (ch + 32);
				sb.append(ch);
			}
		}

		System.out.println(sb.toString());

	}

	private static void removePrime(List<Integer> list) {

		for (int i = list.size() - 1; i >= 0; i--) {

			int num = list.get(i);
			boolean flag = true;
			for (int j = 2; j < num / 2; j++) {
				if (num % j == 0) {
					flag = false;
					break;
				}
			}

			if (flag) {
				list.remove(i);
			}
		}

		System.out.println(list);
	}

	private static void removeOddElements(List<Integer> list) {

		for (int i = list.size() - 1; i >= 0; i--) {
			if (list.get(i) % 2 != 0) {
				list.remove(i);
			}
		}
		System.out.println(list);
	}

	private static void stringCompression(String str) {

		StringBuilder sb = new StringBuilder();
		sb.append(str.charAt(0));
		int count = 1;

		for (int i = 1; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (ch == str.charAt(i - 1)) {
				count++;
			} else {
				if (count > 1) {
					sb.append(count);
				}
				sb.append(str.charAt(i));
				count = 1;
			}
		}
		if (count > 1) {
			sb.append(count);
		}

		System.out.println(sb.toString());
	}

	private static void printAllPalindromicSubstring(String str) {

		for (int i = 0; i < str.length(); i++) {
			for (int j = i; j < str.length(); j++) {
				if (isPalindrome(str, i, j)) {
					System.out.println(str.substring(i, j + 1));
				}
			}
		}

	}

	private static boolean isPalindrome(String str, int i, int j) {

		boolean flag = true;
		while (i < j) {
			if (str.charAt(i) != str.charAt(j)) {
				flag = false;
				break;
			}
			i++;
			j--;
		}
		return flag;
	}

}
