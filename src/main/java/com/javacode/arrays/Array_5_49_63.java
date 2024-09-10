package com.javacode.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Array_5_49_63 {

	public static void main(String[] args) {

//		int arr[] = { 2, 3, -2, 4 };
//		int arr[] = { 2, -10, 3, 0, -9, -4, 19, 8, 0, 5 };
//		maxProduct(arr);

//		int arr[] = { 2, 1, 3 };
//		sumOfSubsequenceWidth(arr);

//		int arr[] = { 9, 3, 5 };
//		constructTargetArray(arr);

//		int arr[] = { 4, 2, 7, 6, 9, 14, 12 };
//		furthestBuilding(arr, 5, 1);

//		int arr[][] = { { 1, 1, 1, -1, -1 }, { 1, 1, 1, -1, -1 }, { -1, -1, -1, 1, 1 }, { 1, 1, 1, 1, -1 },
//				{ -1, -1, -1, -1, -1 } };
//		findBall(arr);

//		int arr[] = { 2, 1, 6, 4 };
//		waysToMakeFair(arr);

//		orderlyQueue("baaca", 3);

//		int arr[][] = { { 3, 3, 1, 1 }, { 2, 2, 1, 2 }, { 1, 1, 1, 2 } };
//		diagonalSort(arr);

//		reorganizeString("aaabc");
//		bulbSwitcher("001011101");

//		int arr[] = { 3, 8, 1, 3, 2, 1, 8, 9, 0 };
//		maxSumOfTwoNonOverlappingSubarrays(arr, 2, 3);

//		int arr[][] = { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 1, 1, 0, 0 } };
//		islandPerimeter(arr);
	}

	private static void islandPerimeter(int[][] arr) {

		int count = 0;

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				if (arr[i][j] == 1) {
					count += 4;

					if (i > 0 && arr[i - 1][j] == 1) {
						count -= 2;
					}
					if (j > 0 && arr[i][j - 1] == 1) {
						count -= 2;
					}
				}
			}
		}

		System.out.println("Permieter: " + count);
	}

	private static void maxSumOfTwoNonOverlappingSubarrays(int[] arr, int x, int y) {

		int sum = 0;
		int left[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {

			if (i < x) {
				sum += arr[i];
				if (i == x - 1) {
					left[i] = sum;
				}
			} else {
				sum += arr[i] - arr[i - x];
				left[i] = Math.max(sum, left[i - 1]);
			}
		}

		for (int i = 0; i < left.length; i++) {
			System.out.print(left[i] + " ");
		}

		sum = 0;
		int right[] = new int[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {

			if (arr.length - i <= y) {
				sum += arr[i];
				if (y == arr.length - i) {
					right[i] = sum;
				}
			} else {
				sum += arr[i] - arr[i + y];
				right[i] = Math.max(sum, right[i + 1]);
			}
		}

		System.out.println();

		for (int i = 0; i < right.length; i++) {
			System.out.print(right[i] + " ");
		}

		int max = 0;
		for (int i = 0; i < left.length - 1; i++) {
			max = Math.max(max, left[i] + right[i + 1]);
		}
		System.out.println();
		System.out.println("Max Sum: " + max);
	}

	private static void bulbSwitcher(String str) {

		char future = '0';
		int count = 0;
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) != future) {
				future = future == '1' ? '0' : '1';
				count++;
			}
		}

		System.out.println("Count: " + count);
	}

	private static void reorganizeString(String str) {

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (char ch : map.keySet()) {
			pq.add(new Pair(ch, map.get(ch)));
		}

		StringBuilder sb = new StringBuilder();
		Pair block = pq.remove();
		sb.append(block.ch);
		block.freq--;

		while (pq.size() > 0) {

			Pair top = pq.remove();
			sb.append(top.ch);
			top.freq--;

			if (block.freq > 0) {
				pq.add(block);
			}
			block = top;
		}
		if (block.freq > 0) {
			System.out.println("No Answer");
		} else {
			System.out.println(sb.toString());
		}
	}

	private static class Pair implements Comparable<Pair> {

		char ch;
		int freq;

		public Pair(char ch, int freq) {
			this.ch = ch;
			this.freq = freq;
		}

		@Override
		public int compareTo(Pair o) {
			return o.freq - this.freq;
		}
	}

	private static void diagonalSort(int[][] arr) {

		for (int i = 0; i < arr[0].length; i++) {
			countSort(arr, 0, i);
		}

		for (int i = 0; i < arr[0].length; i++) {
			countSort(arr, i, 0);
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void countSort(int arr[][], int row, int col) {

		int i = row;
		int j = col;
		int carr[] = new int[101];

		while (i < arr.length && j < arr[0].length) {
			carr[arr[i][j]]++;
			i++;
			j++;
		}

		i = row;
		j = col;
		for (int k = 1; k < carr.length; k++) {

			while (carr[k] > 0) {
				arr[i][j] = k;
				carr[k]--;
				i++;
				j++;
			}
		}
	}

	private static void orderlyQueue(String str, int k) {

		char arr[] = str.toCharArray();
		if (k > 1) {
			Arrays.sort(arr);
			System.out.println(new String(arr));
		} else {

			String ans = str;
			for (int i = 0; i < arr.length; i++) {

				rotateArray(arr);
				String ns = new String(arr);
				if (ans.compareTo(ns) == 1) {
					ans = ns;
				}
			}

			System.out.println("Ans: " + ans);
		}
	}

	private static void rotateArray(char arr[]) {

		char ch = arr[0];
		for (int i = 0; i < arr.length - 1; i++) {
			arr[i] = arr[i + 1];
		}
		arr[arr.length - 1] = ch;
	}

	private static void waysToMakeFair(int[] arr) {

		int odd[] = new int[arr.length];
		int even[] = new int[arr.length];

		int po = 0;
		int pe = 0;
		for (int i = arr.length - 1; i >= 0; i--) {

			if (i % 2 == 0) {
				even[i] = pe + arr[i];
				odd[i] = po;
				pe = even[i];
			} else {
				odd[i] = po + arr[i];
				po = odd[i];
				even[i] = pe;
			}
		}

		int os = 0;
		int es = 0;
		int count = 0;

		for (int i = 0; i < arr.length; i++) {

			int esum = es + (i != arr.length - 1 ? odd[i + 1] : 0);
			int osum = os + (i != arr.length - 1 ? even[i + 1] : 0);

			if (esum == osum) {
				count++;
			}

			if (i % 2 == 0) {
				es += arr[i];
			} else {
				os += arr[i];
			}
		}

		System.out.println("Count: " + count);
	}

	private static void findBall(int[][] arr) {

		int res[] = new int[arr[0].length];
		for (int k = 0; k < arr[0].length; k++) {

			int i = 0;
			int j = k;

			while (i < arr.length) {

				int nc = j + arr[i][j];
				if (nc < 0 || nc == arr.length) {
					j = -1;
					break;
				}

				if ((arr[i][nc] == 1 && arr[i][j] == -1) || (arr[i][nc] == -1 && arr[i][j] == 1)) {
					j = -1;
					break;
				}

				i++;
				j = nc;
			}

			res[k] = j;
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void furthestBuilding(int[] arr, int bricks, int ladders) {

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int i = 0;
		for (i = 0; i < arr.length - 1; i++) {

			int diff = arr[i + 1] - arr[i];
			if (diff > 0) {

				pq.add(diff);
				if (pq.size() > ladders) {
					bricks -= pq.remove();
				}

				if (bricks < 0) {
					break;
				}
			}
		}
		System.out.println("Ans: " + i);
	}

	private static void constructTargetArray(int[] arr) {

		int sum = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < arr.length; i++) {
			pq.add(arr[i]);
			sum += arr[i];
		}

		boolean flag = true;
		while (pq.peek() != 1) {

			int top = pq.remove();
			sum -= top;
			if (sum <= 0 || top <= sum) {
				flag = false;
				break;
			} else {

				top = top % sum;
				pq.add(top > 0 ? top : sum);
				sum += top;
			}
		}

		System.out.println(flag);
	}

	private static void sumOfSubsequenceWidth(int[] arr) {

		long ans = 0;
		Arrays.sort(arr);
		int mod = 1000000007;
		long pow[] = new long[arr.length];
		pow[0] = 1;
		for (int i = 1; i < pow.length; i++) {
			pow[i] = 2 * pow[i - 1];
		}

		for (int i = 0; i < arr.length; i++) {
			ans = (ans + arr[i] * (pow[i] - pow[arr.length - i - 1])) % mod;
		}

		System.out.println("Ans: " + ans);
	}

	private static void maxProduct(int[] arr) {

		int max = 0;
		int prod = 1;

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] == 0) {
				prod = 1;
			} else {
				prod = prod * arr[i];
			}
			max = Math.max(max, prod);
		}

		prod = 1;
		for (int i = arr.length - 1; i >= 0; i--) {

			if (arr[i] == 0) {
				prod = 1;
			} else {
				prod = prod * arr[i];
			}
			max = Math.max(max, prod);
		}

		System.out.println("Max Product: " + max);
	}

}
