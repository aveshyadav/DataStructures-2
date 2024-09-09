package com.javacode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Array_4_36_48 {

	public static void main(String[] args) {

//		int arr[][] = { { 4, 10, 15, 24, 26 }, { 0, 9, 12, 20 }, { 5, 18, 22, 30 } };
//		int arr[][] = { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } };
//		smallestRange(arr);

//		int tops[] = { 2, 1, 2, 4, 2, 2 };
//		int bottom[] = { 5, 2, 6, 2, 3, 2 };
//		minDominoRotations(tops, bottom);

//		int arr[] = { 3, 2, 2, 1 };
//		int arr[] = { 5, 1, 3,4, 2 };
//		numRescueBoats(arr, 6);

//		int arr[] = { 10, 10, 30, 40, 50, 20 };
//		int arr[] = { 2, 2, 4, 3, 1, 6, 6, 7, 5, 9, 1, 8, 9 };
//		twoSumUniquePairs(arr, 0, arr.length - 1, 10);

//		int arr[] = { -1, 0, 1, 2, -1, -4 };
//		threeSum(arr, 0, 0);

//		int arr[] = { 1, 0, -1, 0, -2, 2 };
//		int arr[] = { 10, 10, 5, 15, 6, 4, 20 }; // 40
//		fourSum(arr, 0);

//		int arr[] = { -1, 0, 1, 2, -1, -4 };
//		kSum(arr, 3, 0);

//		int arr[] = { 9, 12, 4, 1, 2, -1, 8 };
//		firstMissingPositive(arr);

//		pascalTraingle2(4);

//		int arr[] = { 4, 3, 2, 7, 8, 2, 3, 1 };
//		findAllDuplicatesInArray(arr);

//		String words[] = { "abc", "deq", "mee", "aqq", "dkd", "ccc" };
//		findAndReplacePattern(words, "abb");

//		consecutiveNumbersSum(15);

//		pushDominoes("RR.L");
		pushDominoes(".L.R...LR..L..");
	}

	private static void pushDominoes(String str) {

		char arr[] = new char[str.length() + 2];
		arr[0] = 'L';
		for (int i = 0; i < str.length(); i++) {
			arr[i + 1] = str.charAt(i);
		}
		arr[arr.length - 1] = 'R';

		int j = 0;
		int i = 1;

		while (i < arr.length) {

			while (i < arr.length && arr[i] == '.') {
				i++;
			}

			System.out.println(arr[j] + " " + arr[i] + " " + j + "-" + i);
			if (arr[j] == 'L' && arr[i] == 'L') {
				while (j < i) {
					arr[j++] = 'L';
				}
			} else if (arr[j] == 'R' && arr[i] == 'L') {

				int diff = i - j - 1;
				if (diff % 2 == 0) {

					diff = diff / 2;
					j++;
					while (diff-- > 0) {
						arr[j++] = 'R';
					}
					while (j < i) {
						arr[j++] = 'L';
					}
				} else {
					diff = diff / 2;
					j++;
					while (diff-- > 0) {
						arr[j++] = 'R';
					}
					arr[j++] = '.';
					while (j < i) {
						arr[j++] = 'L';
					}
				}

			} else if (arr[j] == 'R' && arr[i] == 'R') {
				while (j < i) {
					arr[j++] = 'R';
				}
			}
			j = i;
			i++;
		}

		StringBuilder sb = new StringBuilder();
		for (i = 1; i < arr.length - 1; i++) {
			sb.append(arr[i]);
		}
		System.out.println(sb.toString());
	}

	private static void consecutiveNumbersSum(int n) {

		int j = 1;
		int count = 0;
		int sum = 0;

		for (int i = 1; i <= n; i++) {

			sum += i;
			if (sum == n) {
				count++;
			} else {
				while (sum > n) {
					sum -= j;
					j++;
				}
				if (sum == n) {
					count++;
				}
			}
		}

		System.out.println("Count: " + count);
	}

	private static void findAndReplacePattern(String[] words, String pattern) {

		List<String> list = new ArrayList<>();
		for (int i = 0; i < words.length; i++) {

			String str = words[i];
			if (checkPattern(str, pattern)) {
				list.add(str);
			}
		}
		System.out.println(list);
	}

	private static boolean checkPattern(String s1, String s2) {

		boolean flag = true;
		Map<Character, Character> map = new HashMap<>();
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < s1.length(); i++) {

			char ch1 = s1.charAt(i);
			char ch2 = s2.charAt(i);

			if (map.containsKey(ch1)) {
				if (map.get(ch1) != ch2) {
					flag = false;
					break;
				}
			} else {
				if (set.contains(ch2)) {
					flag = false;
					break;
				}
				map.put(ch1, ch2);
				set.add(ch2);
			}
		}
		return flag;
	}

	private static void findAllDuplicatesInArray(int[] arr) {

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {

			int val = Math.abs(arr[i]) - 1;
			if (arr[val] < 0) {
				list.add(val + 1);
			} else {
				arr[val] = -arr[val];
			}
		}

		System.out.println(list);
	}

	private static void pascalTraingle2(int i) {

		List<Integer> list = new ArrayList<>();

		int val = 1;
		for (int j = 0; j <= i; j++) {
			list.add(val);
			val = val * (i - j) / (j + 1);
		}

		System.out.println(list);
	}

	private static void firstMissingPositive(int[] arr) {

		boolean one = false;
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == 1) {
				one = true;
			} else {
				if (arr[i] < 0 || arr[i] >= arr.length) {
					arr[i] = 1;
				}
			}
		}

		int missing = arr.length;
		if (one == false) {
			missing = 1;
		} else {
			for (int i = 0; i < arr.length; i++) {
				int val = Math.abs(arr[i]);
				arr[val] = -arr[val];
			}

			for (int i = 1; i < arr.length; i++) {
				if (arr[i] > 0) {
					missing = i;
					break;
				}
			}
		}
		System.out.println("Missing Number: " + missing);

	}

	private static void kSum(int[] arr, int k, int target) {

		Arrays.sort(arr);
		List<List<Integer>> ans = kSumHelper(arr, 0, k, target);
		System.out.println(ans);

	}

	private static List<List<Integer>> kSumHelper(int arr[], int i, int k, int target) {

		if (k == 2) {
			return twoSumUniquePairs(arr, i, arr.length - 1, target);
		}

		List<List<Integer>> res = new ArrayList<>();
		for (int idx = i; idx < arr.length; idx++) {

			if (idx > 0 && arr[idx] == arr[idx - 1]) {
				continue;
			}

			int find = target - arr[idx];
			List<List<Integer>> ans = kSumHelper(arr, idx + 1, k - 1, find);

			if (ans.size() > 0) {
				for (List<Integer> ll : ans) {
					ll.add(arr[idx]);
					res.add(ll);
				}
			}
		}

		return res;
	}

	private static void fourSum(int[] arr, int target) {

		Arrays.sort(arr);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {

			if (i > 0 && arr[i] == arr[i - 1]) {
				continue;
			}

			int find = target - arr[i];
			List<List<Integer>> ans = threeSum(arr, i + 1, find);
			createResult(res, ans, arr[i]);
		}
		System.out.println(res);
	}

	private static List<List<Integer>> threeSum(int[] arr, int i, int target) {

		Arrays.sort(arr);
		List<List<Integer>> res = new ArrayList<>();
		for (; i < arr.length; i++) {

			if (i > 0 && arr[i] == arr[i - 1]) {
				continue;
			}

			int find = target - arr[i];
			List<List<Integer>> ans = twoSumUniquePairs(arr, i + 1, arr.length - 1, find);
			createResult(res, ans, arr[i]);
		}
		return res;
	}

	private static void createResult(List<List<Integer>> res, List<List<Integer>> ans, int val) {

		for (List<Integer> ll : ans) {
			ll.add(val);
			res.add(ll);
		}
	}

	private static List<List<Integer>> twoSumUniquePairs(int[] arr, int i, int j, int target) {

		Arrays.sort(arr);
		List<List<Integer>> ans = new ArrayList<>();
		while (i < j) {

			int sum = arr[i] + arr[j];
			if (sum == target) {
				List<Integer> list = new ArrayList<>();
				list.add(arr[i]);
				list.add(arr[j]);
				ans.add(list);

				i++;
				j--;

				while (i < j && arr[i] == arr[i - 1]) {
					i++;
				}

				while (i < j && arr[j] == arr[j + 1]) {
					j--;
				}
			} else if (sum > target) {
				j--;
			} else {
				i++;
			}
		}
		return ans;
	}

	private static void numRescueBoats(int[] arr, int n) {

		Arrays.sort(arr);
		int i = 0;
		int j = arr.length - 1;

		int count = 0;
		while (i <= j) {

			int sum = arr[i] + arr[j];
			if (sum <= n) {
				i++;
				j--;
			} else {
				j--;
			}
			count++;
		}

		System.out.println("Min Boats: " + count);
	}

	private static void minDominoRotations(int[] tops, int[] bottom) {

		int n1 = tops[0];
		int c1 = 0;
		int c2 = 1;

		int n2 = bottom[0];
		int c3 = 0;
		int c4 = 1;

		for (int i = 1; i < tops.length; i++) {

			if (c1 != -1) {
				if (tops[i] == n1) {
					// do Nothing
				} else if (bottom[i] == n1) {
					c1++;
				} else {
					c1 = -1;
				}
			}

			if (c2 != -1) {
				if (bottom[i] == n1) {
					// do Nothing
				} else if (tops[i] == n1) {
					c2++;
				} else {
					c2 = -1;
				}
			}

			if (c3 != -1) {
				if (bottom[i] == n2) {
					// do Nothing
				} else if (tops[i] == n2) {
					c3++;
				} else {
					c3 = -1;
				}
			}

			if (c4 != -1) {
				if (tops[i] == n2) {
					// do Nothing
				} else if (bottom[i] == n2) {
					c4++;
				} else {
					c4 = -1;
				}
			}
		}

		System.out.println(c1 + " " + c2 + " " + c3 + " " + c4);
	}

	private static void smallestRange(int[][] arr) {

		int max = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			pq.add(new Pair(arr[i][0], i, 0));
			max = Math.max(max, arr[i][0]);
		}

		int range = Integer.MAX_VALUE;
		int a = 0;
		int b = 0;

		while (pq.size() == arr.length) {

			Pair mp = pq.remove();
			System.out.println(mp.val + " " + max);
			if (max - mp.val < range) {
				range = max - mp.val;
				a = mp.val;
				b = max;
			}

			if (mp.di < arr[mp.li].length - 1) {
				max = Math.max(max, arr[mp.li][mp.di + 1]);
				pq.add(new Pair(arr[mp.li][mp.di + 1], mp.li, mp.di + 1));
			}
		}

		System.out.println("Range: " + a + "-" + b);
	}

	private static class Pair implements Comparable<Pair> {

		int val;
		int li;
		int di;

		public Pair(int val, int li, int di) {
			this.val = val;
			this.li = li;
			this.di = di;
		}

		@Override
		public int compareTo(Pair o) {
			return this.val - o.val;
		}
	}

}
