package com.javacode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Array_3_24_35 {

	public static void main(String[] args) {

//		int arr[][] = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
//		rotateImage(arr);

//		String str = "healloi";
//		reverseVowel(str);

//		int arr[] = { 3, 5, 2, 1, 6, 4 };
//		wiggleSort1(arr);

//		int arr[] = { 1, 5, 1, 1, 6, 4 };
//		wiggleSort2(arr);

//		String s1 = "456";
//		String s2 = "777";
//		addStrings(s1, s2);

//		String s1 = "123";
//		String s2 = "456";
//		multiplyString(s1, s2);

//		String s1 = "1+-1i";
//		String s2 = "1+-1i";
//		complexNumberMultiplication(s1, s2);

//		String str = "abca";
//		palidPalindrome2(str);

//		int pos[] = { 10, 8, 0, 5, 3 };
//		int sp[] = { 2, 4, 1, 1, 3 };
//		carFleet(12, pos, sp);

//		int arr[][] = { { 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
//				{ 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 },
//				{ 0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0 } };
//		maxAreaOfIsland(arr);

//		int arr[] = { 2, 9, 2, 5, 6 };
//		numSubarrayBoundedMax(arr, 2, 8);

//		maxSwap("9987");
//		maxSwap("998743261792");
//		maxSwap("998742393");
//		maxSwap("88432532156");
	}

	private static void maxSwap(String str) {

		char ch = str.charAt(0);
		int ri = -1;
		for (int i = 1; i < str.length(); i++) {

			if (ri == -1) {
				if (str.charAt(i) <= ch) {
					ch = str.charAt(i);
				} else {
					ri = i;
				}
			} else {
				if (str.charAt(i) > str.charAt(ri)) {
					ri = i;
				}
			}
		}

		if (ri == -1) {
			System.out.println(str);
		} else {

			int li = 0;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) < str.charAt(ri)) {
					li = i;
					break;
				}
			}

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < str.length(); i++) {

				if (i == li) {
					sb.append(str.charAt(ri));
				} else if (i == ri) {
					sb.append(str.charAt(li));
				} else {
					sb.append(str.charAt(i));
				}
			}

			System.out.println(sb.toString());
		}
	}

	private static void numSubarrayBoundedMax(int[] arr, int lo, int hi) {

		int prev = 0;
		int count = 0;
		int j = 0;

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] >= lo && arr[i] <= hi) {
				prev = i - j + 1;
				count += prev;
			} else if (arr[i] < lo) {
				count += prev;
			} else {
				j = i + 1;
				prev = 0;
			}
		}

		System.out.println("Count: " + count);
	}

	private static void maxAreaOfIsland(int[][] arr) {

		boolean vis[][] = new boolean[arr.length][arr[0].length];

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1 && vis[i][j] == false) {
					count = 0;
					int ans = maxAreaDfs(arr, vis, i, j);

					System.out.println("Count: " + count + " " + ans);
					max = Math.max(max, count);
				}
			}
		}
		System.out.println("Max Area: " + max);
	}

	private static int count = 0;

	private static int maxAreaDfs(int arr[][], boolean vis[][], int i, int j) {

		if (i < 0 || j < 0 || i == arr.length || j == arr[0].length || arr[i][j] == 0 || vis[i][j] == true) {
			return 0;
		}

		vis[i][j] = true;
		count++;
		int ans = 0;
		ans += maxAreaDfs(arr, vis, i + 1, j);
		ans += maxAreaDfs(arr, vis, i, j + 1);
		ans += maxAreaDfs(arr, vis, i - 1, j);
		ans += maxAreaDfs(arr, vis, i, j - 1);

		return ans + 1;
	}

	private static void carFleet(int target, int[] pos, int[] sp) {

		double cars[][] = new double[pos.length][2];
		for (int i = 0; i < pos.length; i++) {
			cars[i][0] = pos[i] * 1d;
			cars[i][1] = (target - (pos[i] * 1d)) / sp[i];
		}

		Arrays.sort(cars, (a, b) -> Double.compare(a[0], b[0]));
		int fleet = 1;
		double tt = cars[cars.length - 1][1];

		for (int i = cars.length - 2; i >= 0; i--) {

			if (cars[i][1] > tt) {
				fleet++;
				tt = cars[i][1];
			}
		}

		System.out.println("Fleet: " + fleet);
	}

	private static void palidPalindrome2(String str) {

		int i = 0;
		int j = str.length() - 1;

		boolean flag = true;
		while (i < j) {

			if (str.charAt(i) != str.charAt(j)) {
				flag = isPalindrome(str, i + 1, j) || isPalindrome(str, i, j + 1);
				break;
			}
			i++;
			j--;
		}

		System.out.println("Is Valid Palindrome: " + flag);
	}

	private static boolean isPalindrome(String str, int i, int j) {

		while (i < j) {
			if (str.charAt(i) != str.charAt(j)) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}

	private static void complexNumberMultiplication(String s1, String s2) {

		int idx = s1.indexOf('+');
		int idx2 = s2.indexOf('+');
		int a = Integer.parseInt(s1.substring(0, idx));
		int b = Integer.parseInt(s1.substring(idx + 1, s1.length() - 1));
		int c = Integer.parseInt(s2.substring(0, idx2));
		int d = Integer.parseInt(s2.substring(idx2 + 1, s2.length() - 1));

		String ans = ((a * c) - (b * d)) + "+" + ((a * d) + (b * c)) + "i";
		System.out.println(ans);
	}

	private static void multiplyString(String s1, String s2) {

		int j = s2.length() - 1;

		String res = "0";
		int count = 0;

		while (j >= 0) {

			int i = s1.length() - 1;
			int v1 = s2.charAt(j) - '0';
			StringBuilder sb = new StringBuilder();
			int carry = 0;

			while (i >= 0 || carry > 0) {

				int val = carry;
				if (i >= 0) {
					val += (v1 * (s1.charAt(i) - '0'));
				}
				carry = 0;
				if (val > 9) {
					val = val % 10;
					carry = 1;
				}

				sb.append(val);
				i--;
			}

			sb = sb.reverse();
			for (int k = 0; k < count; k++) {
				sb.append(0);
			}
			System.out.println(sb.toString());
			res = addStrings(res, sb.toString());
			count++;
			j--;
		}

		System.out.println("-------------");
		System.out.println(res);
	}

	private static String addStrings(String s1, String s2) {

		int i = s1.length() - 1;
		int j = s2.length() - 1;
		int carry = 0;

		StringBuilder sb = new StringBuilder();

		while (i >= 0 || j >= 0 || carry > 0) {

			int sum = carry;
			sum += i >= 0 ? s1.charAt(i) - '0' : 0;
			sum += j >= 0 ? s2.charAt(j) - '0' : 0;
			carry = 0;

			if (sum > 9) {
				sum = sum % 10;
				carry = 1;
			}
			i--;
			j--;
			sb.append(sum);
		}

//		System.out.println(sb.reverse().toString());
		return sb.reverse().toString();
	}

	private static void wiggleSort2(int[] arr) {

		Arrays.sort(arr);
		int res[] = new int[arr.length];

		int i = 0;
		int j = arr.length - 1;
		int k = 0;

		while (i < j) {

			res[k++] = arr[i++];
			res[k++] = arr[j--];
		}

		for (i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void wiggleSort1(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {

			if (i % 2 == 0) {

				if (arr[i + 1] < arr[i]) {
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			} else {

				if (arr[i + 1] > arr[i]) {
					int temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	private static void reverseVowel(String str) {

		char arr[] = str.toCharArray();
		int i = 0;
		int j = arr.length - 1;

		while (i < j) {

			if (!isVowel(arr[i])) {
				i++;
			} else if (!isVowel(arr[j])) {
				j--;
			} else {

				char ch = arr[i];
				arr[i] = arr[j];
				arr[j] = ch;

				i++;
				j--;
			}
		}

		System.out.println(new String(arr));
	}

	private static boolean isVowel(char ch) {

		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
			return true;
		}
		return false;
	}

	private static void rotateImage(int[][] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr[0].length; j++) {

				int temp = arr[i][j];
				arr[i][j] = arr[j][i];
				arr[j][i] = temp;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0, k = arr[0].length - 1; j < arr[0].length / 2; j++, k--) {

				int temp = arr[i][j];
				arr[i][j] = arr[i][k];
				arr[i][k] = temp;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}

}
