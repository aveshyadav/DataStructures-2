package com.javacode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Array_6_64_82 {

	public static void main(String[] args) {

//		char arr[][] = { { 'X', '.', '.', 'X' }, { '.', '.', '.', 'X' }, { '.', '.', '.', 'X' } };
//		battleshipInABoard(arr);

//		int arr[] = { 8, 6, 7, 1, 0 };
//		largestMultipleOfThree(arr);

//		int arr[][] = { { 1, 3 }, { 2, 6 }, { 7, 8 }, { 8, 10 }, { 9, 15 } };
//		mergeIntervals(arr);

//		int[] arr = { 900, 940, 950, 1100, 1500, 1800 };
//		int[] dep = { 910, 1200, 1120, 1130, 1900, 2000 };

//		int arr[] = { 100, 300, 500 };
//		int dep[] = { 900, 400, 600 };
//		minNumberOfPlatform(arr, dep);

//		int arr[][] = { { 1, 3 }, { 2, 6 }, { 7, 8 }, { 8, 10 }, { 10, 15 } };
//		meetingRooms(arr);

//		int arr[][] = { { 1, 3 }, { 2, 4 }, { 6, 8 }, { 7, 14 }, { 7, 9 } };
//		meetingRooms2PQ(arr);
//		meetingRooms2(arr);

//		int list1[][] = { { 0, 2 }, { 5, 10 }, { 13, 23 }, { 24, 25 } };
//		int list2[][] = { { 1, 5 }, { 8, 12 }, { 15, 24 }, { 25, 26 } };

//		int list1[][] = { { 0, 2 }, { 5, 10 }, { 13, 23 }, { 24, 25 } };
//		int list2[][] = { { 1, 5 }, { 8, 12 }, { 15, 24 }, { 25, 26 } };
//		intervalListIntersection(list1, list2);

//		int arr1[][] = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
//		int arr2[] = { 4, 8 };
//		insertInterval(arr1, arr2);

//		int arr[][] = { { 10, 16 }, { 2, 8 }, { 1, 6 }, { 7, 12 } };
//		int arr[][] = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
//		findMinArrowShots(arr);

//		int arr[][] = { { 1, 5, 1 }, { 4, 8, 1 }, { 11, 15, 1 } };
//		carPooling(arr, 2);

//		int arr[] = { 2, 3, 1, 2, 4, 3 };
//		minSubArrayLen(arr, 7);

//		int arr[] = { 1, 12, -5, -6, 50, 3 };
//		maxAverageSubarray(arr, 4);

//		String str = "aaabbcdaoddcccbaa";
//		minimumLength(str);

//		String words[] = { "This", "is", "an", "example", "of", "text", "justification." };
//		String words[] = { "Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a",
//				"computer.", "Art", "is", "everything", "else", "we", "do" };

//		String words[] = { "What", "must", "be", "acknowledgment", "shall", "be" };
//		textJustification(words, 16);

//		int arr[][] = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
//		int arr[][] = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
//		int arr[][] = { { 1, 0, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
//		setMatrixZero(arr);

//		int arr[] = { 1, 3, 5 };
//		numOfSubArrayWithOddSum(arr);

//		int arr[] = { 1, 1, 1, 2, 2, 3, 3, 3, 3, 3 };
//		topKFrequent(arr, 2);

//		char arr[][] = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
//		wordSearch(arr, "ABFDEESCCE");
	}

	private static void wordSearch(char[][] arr, String str) {

		boolean found = false;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				boolean flag = wordSearchHelper(arr, i, j, str, 0);
				if (flag == true) {
					found = true;
					break;
				}
			}
		}
		System.out.println("Word found: " + found);
	}

	private static boolean wordSearchHelper(char arr[][], int row, int col, String str, int idx) {

		if (idx == str.length()) {
			return true;
		}

		if (row < 0 || col < 0 || row == arr.length || col == arr[0].length) {
			return false;
		}

		char ch = arr[row][col];
		if (ch == str.charAt(idx)) {

			char ch2 = (char) (ch + 32);
			arr[row][col] = ch2;

			int dirs[][] = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
			for (int i = 0; i < dirs.length; i++) {

				int nr = dirs[i][0] + row;
				int nc = dirs[i][1] + col;

				boolean flag = wordSearchHelper(arr, nr, nc, str, idx + 1);
				if (flag == true) {
					return true;
				}
			}
			arr[row][col] = ch;
		}
		return false;
	}

	private static void topKFrequent(int[] arr, int k) {

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		PriorityQueue<Pair2> pq = new PriorityQueue<>();
		for (int key : map.keySet()) {

			if (pq.size() < k) {
				pq.add(new Pair2(key, map.get(key)));
			} else {
				if (pq.peek().freq < map.get(key)) {
					pq.remove();
					pq.add(new Pair2(key, map.get(key)));
				}
			}
		}

		while (pq.size() > 0) {
			Pair2 mp = pq.remove();
			System.out.println("Data: " + mp.data + ", Freq: " + mp.freq);
		}
	}

	private static class Pair2 implements Comparable<Pair2> {

		int data;
		int freq;

		public Pair2(int data, int freq) {
			this.data = data;
			this.freq = freq;
		}

		@Override
		public int compareTo(Pair2 o) {
			return this.freq - o.freq;
		}
	}

	private static void numOfSubArrayWithOddSum(int[] arr) {

		int ans = 0;
		int sum = 0;
		int odd = 0;
		int even = 0;

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			if (sum % 2 == 0) {
				ans += odd;
				even++;
			} else {
				ans += 1 + even;
				odd++;
			}
		}

		System.out.println("Count: " + ans);
	}

	private static void setMatrixZero(int[][] arr) {

		boolean row = false;
		boolean col = false;
		for (int i = 0; i < arr.length; i++) {
			if (arr[0][i] == 0) {
				row = true;
				break;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i][0] == 0) {
				col = true;
				break;
			}
		}

		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[0].length; j++) {

				if (arr[i][j] == 0) {
					arr[i][0] = 0;
					arr[0][j] = 0;
				}
			}
		}

		for (int i = 1; i < arr.length; i++) {
			if (arr[i][0] == 0) {
				for (int j = 1; j < arr[0].length; j++) {
					arr[i][j] = 0;
				}
			}
		}

		for (int i = 1; i < arr[0].length; i++) {
			if (arr[0][i] == 0) {
				for (int j = 1; j < arr.length; j++) {
					arr[j][i] = 0;
				}
			}
		}

		System.out.println(row + " " + col);

		if (row == true) {
			for (int i = 0; i < arr.length; i++) {
				arr[0][i] = 0;
			}
		}

		if (col == true) {
			for (int i = 0; i < arr.length; i++) {
				arr[i][0] = 0;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void textJustification(String[] words, int maxWidth) {

		List<String> ans = new ArrayList<>();

		int i = 0;
		while (i < words.length) {

			int start = i;
			int wc = words[i].length();
			i++;

			while (i < words.length && wc + words[i].length() + 1 <= maxWidth) {
				wc += words[i].length() + 1;
				i++;
			}

			int end = i - 1;
			int tw = end - start + 1;
			int sprq = maxWidth - wc;

			StringBuilder sb = new StringBuilder();
			if (end == words.length - 1) {
				sb.append(words[start++]);
				while (start <= end) {
					sb.append(" ");
					sb.append(words[start++]);
				}
			} else {

				if (tw == 1) {
					sb.append(words[start]);
					while (sprq-- > 0) {
						sb.append(" ");
					}
				} else if (tw == 2) {
					sb.append(words[start]);
					while (sprq-- >= 0) {
						sb.append(" ");
					}
					sb.append(words[end]);
				} else {

					sb.append(words[start]);
					int div = sprq / (tw - 1);
					if (sprq % tw != 0) {
						int temp = sprq % (tw - 1);
						while (temp-- > 0) {
							sb.append(" ");
						}
					}
					start++;

					while (start <= end) {
						int temp = div;
						while (temp-- > 0) {
							sb.append(" ");
						}
						sb.append(" ");
						sb.append(words[start++]);
					}
				}
			}
			ans.add(sb.toString());
		}

		for (String str : ans) {
			System.out.println(str);
		}
	}

	private static void minimumLength(String str) {

		int i = 0;
		int j = str.length() - 1;

		while (i < j) {

			if (str.charAt(i) == str.charAt(j)) {
				i++;
				j--;

				while (i < str.length() && str.charAt(i) == str.charAt(i - 1)) {
					i++;
				}
				while (j >= 0 && str.charAt(j) == str.charAt(j + 1)) {
					j--;
				}
			} else {
				break;
			}
		}

		System.out.println(i + " " + j);
		int ans = j - i + 1;
		System.out.println("Min Len: " + ans);
	}

	private static void maxAverageSubarray(int[] arr, int k) {

		int j = 0;
		double max = 0;
		double sum = 0;
		for (int i = 0; i < arr.length; i++) {

			if (i < k - 1) {
				sum += arr[i];
			} else {

				sum += arr[i];
				double avg = (sum / k);
				max = Math.max(max, avg);

				sum -= arr[j];
				j++;
			}
		}
		System.out.println("Max Average: " + max);
	}

	private static void minSubArrayLen(int arr[], int target) {

		int j = 0;
		int sum = 0;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < arr.length; i++) {

			sum += arr[i];
			while (sum >= target) {

				int len = i - j + 1;
				min = Math.min(min, len);
				sum -= arr[j];
				j++;
			}
		}

		System.out.println("Min Length: " + min);
	}

	private static void carPooling(int[][] arr, int cap) {

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i][1]);
		}

		boolean flag = true;
		int temp[] = new int[max + 1];
		for (int i = 0; i < arr.length; i++) {
			temp[arr[i][0]] += arr[i][2];
			temp[arr[i][1]] -= arr[i][2];
		}

		int sum = 0;
		for (int i = 0; i < temp.length; i++) {

			sum += temp[i];
			if (sum > cap) {
				flag = false;
				break;
			}
		}

		System.out.println("Is Possible: " + flag);
	}

	private static void findMinArrowShots(int[][] arr) {

		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(new Pair(arr[i][0], arr[i][1]));
		}
		Collections.sort(list, (a, b) -> a.et - b.et);

		int et = list.get(0).et;
		int count = 1;
		for (int i = 1; i < list.size(); i++) {

			if (list.get(i).st <= et) {
				continue;
			} else {
				count++;
				et = list.get(i).et;
			}
		}

		System.out.println("Min Arrow: " + count);
	}

	private static void insertInterval(int[][] arr1, int[] arr2) {

		boolean flag = false;
		List<Pair> ans = new ArrayList<>();

		for (int i = 0; i < arr1.length; i++) {

			if (arr1[i][0] < arr2[0]) {
				ans.add(new Pair(arr1[i][0], arr1[i][1]));
			} else {

				if (flag == false) {
					if (ans.size() == 0) {
						ans.add(new Pair(arr2[0], arr2[1]));
					} else {

						Pair lp = ans.get(ans.size() - 1);
						if (arr2[0] <= lp.et) {
							lp.et = Math.max(lp.et, arr2[1]);
						} else {
							ans.add(new Pair(arr2[0], arr2[1]));
						}
					}
					flag = true;
				} else {
					Pair lp = ans.get(ans.size() - 1);
					if (arr1[i][0] <= lp.et) {
						lp.et = Math.max(lp.et, arr1[i][1]);
					} else {
						ans.add(new Pair(arr1[i][0], arr1[i][1]));
					}
				}
			}
		}

		for (Pair mp : ans) {
			System.out.println(mp.st + " " + mp.et);
		}
	}

	private static void intervalListIntersection(int[][] list1, int[][] list2) {

		List<Pair> ans = new ArrayList<>();

		int i = 0;
		int j = 0;

		while (i < list1.length && j < list2.length) {

			int st = Math.max(list1[i][0], list2[j][0]);
			int et = Math.min(list1[i][1], list2[j][1]);

			if (st <= et) {
				ans.add(new Pair(st, et));
			}

			if (list1[i][1] < list2[j][1]) {
				i++;
			} else {
				j++;
			}
		}

		for (Pair mp : ans) {
			System.out.println(mp.st + " " + mp.et);
		}
	}

	private static void meetingRooms2(int[][] arr) {

		int st[] = new int[arr.length];
		int et[] = new int[arr.length];

		for (int i = 0; i < arr.length; i++) {
			st[i] = arr[i][0];
			et[i] = arr[i][1];
		}
		Arrays.sort(st);
		Arrays.sort(et);

		int max = 0;
		int curr = 0;

		int i = 0;
		int j = 0;

		while (i < st.length && j < et.length) {

			if (st[i] < et[j]) {
				curr++;
				i++;
			} else {
				curr--;
				j++;
			}
			max = Math.max(max, curr);
		}

		System.out.println("Min meeting rooms required: " + max);
	}

	private static void meetingRooms2PQ(int[][] arr) {

		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(new Pair(arr[i][0], arr[i][1]));
		}
		Collections.sort(list);

		int max = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (Pair mp : list) {

			if (pq.size() == 0) {
				pq.add(mp.et);
			} else {

				if (mp.st >= pq.peek()) {
					pq.remove();
				}
				pq.add(mp.et);
			}
			max = Math.max(max, pq.size());
		}

		System.out.println("Min meeting rooms required: " + max);
	}

	private static void meetingRooms(int[][] arr) {

		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(new Pair(arr[i][0], arr[i][1]));
		}
		Collections.sort(list);

		boolean flag = true;
		for (int i = 1; i < list.size(); i++) {

			Pair mp = list.get(i);
			Pair pp = list.get(i - 1);

			if (mp.st < pp.et) {
				flag = false;
				break;
			}
		}

		System.out.println("Is Meeting Possible: " + flag);
	}

	private static void minNumberOfPlatform(int[] arr, int[] dep) {

		Arrays.sort(arr);
		Arrays.sort(dep);

		int plateform = 0;
		int maxTrain = 0;

		int i = 0;
		int j = 0;

		while (i < arr.length && j < dep.length) {

			if (arr[i] < dep[j]) {
				maxTrain++;
				i++;
			} else {
				maxTrain--;
				j++;
			}

			plateform = Math.max(maxTrain, plateform);
		}
		System.out.println("Min Plateform Required: " + plateform);
	}

	private static void mergeIntervals(int[][] arr) {

		List<Pair> ans = new ArrayList<>();
		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(new Pair(arr[i][0], arr[i][1]));
		}
		Collections.sort(list);

		for (Pair mp : list) {

			if (ans.size() == 0) {
				ans.add(mp);
			} else {

				Pair pp = ans.get(ans.size() - 1);
				if (mp.st < pp.et) {
					pp.et = Math.max(pp.et, mp.et);
				} else {
					ans.add(mp);
				}
			}
		}

		for (Pair mp : ans) {
			System.out.println(mp.st + "-" + mp.et);
		}
	}

	private static class Pair implements Comparable<Pair> {

		int st;
		int et;

		public Pair(int st, int et) {
			this.st = st;
			this.et = et;
		}

		@Override
		public int compareTo(Pair o) {
			return this.st - o.st;
		}
	}

	private static void largestMultipleOfThree(int[] arr) {

		Arrays.sort(arr);
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}

		if (sum % 3 == 0) {

			StringBuilder sb = new StringBuilder();
			for (int i = arr.length - 1; i >= 0; i--) {
				sb.append(arr[i] + "");
			}
			System.out.println(sb.toString());

		} else if (sum % 3 == 1) {

			boolean oneFound = false;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] % 3 == 1) {
					arr[i] = -1;
					oneFound = true;
					break;
				}
			}
			if (oneFound == true) {
				StringBuilder sb = new StringBuilder();
				for (int i = arr.length - 1; i >= 0; i--) {
					if (arr[i] != -1) {
						sb.append(arr[i] + "");
					}
				}
				System.out.println(sb.toString());
			} else {

				int idx1 = -1;
				int idx2 = -1;
				for (int i = 0; i < arr.length; i++) {
					if (arr[i] % 3 == 2) {
						if (idx1 == -1) {
							idx1 = i;
						} else if (idx2 == -1) {
							idx2 = i;
							break;
						}
					}
				}

				if (idx2 == -1) {
					System.out.println("No Answer");
				} else {
					StringBuilder sb = new StringBuilder();
					for (int i = arr.length - 1; i >= 0; i--) {
						if (arr[i] != idx1 && arr[i] != idx2) {
							sb.append(arr[i] + "");
						}
					}
					System.out.println(sb.toString());
				}
			}

		} else {
			boolean twoFound = false;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] % 3 == 2) {
					arr[i] = -1;
					twoFound = true;
					break;
				}
			}
			if (twoFound == true) {
				StringBuilder sb = new StringBuilder();
				for (int i = arr.length - 1; i >= 0; i--) {
					if (arr[i] != -1) {
						sb.append(arr[i] + "");
					}
				}
				System.out.println(sb.toString());
			} else {
				int idx1 = -1;
				int idx2 = -1;
				for (int i = 0; i < arr.length; i++) {
					if (arr[i] % 3 == 1) {
						if (idx1 == -1) {
							idx1 = i;
						} else if (idx2 == -1) {
							idx2 = i;
							break;
						}
					}
				}

				if (idx2 == -1) {
					System.out.println("No Answer");
				} else {
					StringBuilder sb = new StringBuilder();
					for (int i = arr.length - 1; i >= 0; i--) {
						if (arr[i] != idx1 && arr[i] != idx2) {
							sb.append(arr[i] + "");
						}
					}
					System.out.println(sb.toString());
				}
			}
		}
	}

	private static void battleshipInABoard(char[][] arr) {

		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 'X') {

					if (i == 0 && j == 0) {
						count++;
					} else if (i == 0) {
						if (arr[i][j - 1] != 'X') {
							count++;
						}
					} else if (j == 0) {
						if (arr[i - 1][j] != 'X') {
							count++;
						}
					} else {
						if (arr[i][j - 1] != 'X' && arr[i - 1][j] != 'X') {
							count++;
						}
					}
				}
			}
		}

		System.out.println("Count: " + count);
	}

}
