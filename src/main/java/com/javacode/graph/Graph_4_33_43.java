package com.javacode.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

public class Graph_4_33_43 {

	public static void main(String[] args) {

//		int arr[][] = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } };
//		redundantConnection(arr, 4);

//		int arr[][] = { { 2, 1 }, { 4, 2 }, { 3, 1 }, { 1, 4 } };
//		redundantConnection2(arr, 5);

//		String arr[] = { "a==b", "c==d", "b!=d", "b==c" };
//		satisfiabilityOfEqualityEquation(arr);

//		String arr1[] = { "treat", "fight", "miss" };
//		String arr2[] = { "like", "train", "lost" };
//		String match[][] = { { "treat", "catch" }, { "train", "fight" }, { "like", "catch" }, { "miss", "lost" } };
//		sentenceSimilarity(arr1, arr2, match);

//		int arr[][] = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
//		int arr[][] = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
//		numberOfProvinces(arr);

//		int n = 7;
//		int arr[][] = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 3, 4 }, { 4, 5 }, { 3, 5 } };
//		numberOfOperationsToMakeNetwordConnected(arr, n);

		String arr[][] = { { "MUC", "LHR" }, { "JFK", "MUC" }, { "SFO", "SJC" }, { "LHR", "SFO" } };
		reconstructItinerary(arr);
	}

	private static void reconstructItinerary(String[][] arr) {

		Map<String, PriorityQueue<String>> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			String src = arr[i][0];
			String dest = arr[i][1];

			map.putIfAbsent(src, new PriorityQueue<>());
			PriorityQueue<String> pq = map.get(src);
			pq.add(dest);
			map.put(src, pq);
		}

		System.out.println(map);
		Stack<String> ans = new Stack<>();
		dfs(map, "JFK", ans);
		System.out.println(ans);
	}

	private static void dfs(Map<String, PriorityQueue<String>> map, String src, Stack<String> ans) {

		if (map.get(src) == null) {
			ans.push(src);
			return;
		}

		for (String str : map.get(src)) {
			map.get(src).remove();
			dfs(map, str, ans);
		}
		ans.push(src);
	}

	private static void numberOfOperationsToMakeNetwordConnected(int[][] arr, int n) {

		int parent[] = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		int count = 0;
		for (int i = 0; i < arr.length; i++) {

			count++;
			boolean flag = union(arr[i][0], arr[i][1], parent);
			if (flag == true) {
				count--;
			}
		}
		System.out.println(count);
	}

	private static void numberOfProvinces(int arr[][]) {

		int n = arr.length;
		int m = arr[0].length;
		int parent[] = new int[n * m];

		int count = 0;
		int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				int idx = i * n + j;
				parent[idx] = idx;
				count++;

				for (int k = 0; k < dirs.length; k++) {

					int nr = i + dirs[k][0];
					int nc = j + dirs[k][1];

					if (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr[0].length && arr[nr][nc] == 1) {

						int idx2 = nr * n + nc;
						boolean flag = union(idx, idx2, parent);
						if (flag == true) {
							count--;
						}
					}

				}
			}
		}
		System.out.println("No of provinces: " + count);
	}

	private static void sentenceSimilarity(String[] arr1, String[] arr2, String[][] match) {

		Map<String, String> map = new HashMap<>();
		for (int i = 0; i < match.length; i++) {
			map.putIfAbsent(match[i][0], match[i][0]);
			map.putIfAbsent(match[i][1], match[i][1]);
		}

		System.out.println(map);

		for (int i = 0; i < match.length; i++) {
			mergeStrings(match[i][0], match[i][1], map);
		}

		boolean flag = true;
		for (int i = 0; i < arr1.length; i++) {

			flag = mergeStrings(arr1[i], arr2[i], map);
			if (flag == false) {
				break;
			}
		}
		System.out.println("Sentence Similarity: " + flag);
	}

	private static boolean mergeStrings(String x, String y, Map<String, String> parent) {

		String lx = findParent(x, parent);
		String ly = findParent(y, parent);

		if (!lx.equals(ly)) {
			parent.put(lx, ly);
			return false;
		}
		return true;
	}

	private static String findParent(String x, Map<String, String> map) {

		if (map.get(x).equals(x)) {
			return x;
		}

		String temp = findParent(map.get(x), map);
		map.put(x, temp);
		return temp;
	}

	private static void satisfiabilityOfEqualityEquation(String[] arr) {

		int parent[] = new int[26];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < arr.length; i++) {

			int ch1 = arr[i].charAt(0) - 'a';
			int ch2 = arr[i].charAt(arr[i].length() - 1) - 'a';

			char sym = arr[i].charAt(1);
			if (sym == '=') {
				union(ch1, ch2, parent);
			}
		}

		for (int i = 0; i < arr.length; i++) {

			int ch1 = arr[i].charAt(0) - 'a';
			int ch2 = arr[i].charAt(arr[i].length() - 1) - 'a';

			char sym = arr[i].charAt(1);
			if (sym == '!') {
				boolean flag = union(ch1, ch2, parent);
				if (flag == false) {
					System.out.println("Bad Equation");
					return;
				}
			}
		}
		System.out.println("Right Equation");
	}

	private static void redundantConnection2(int[][] arr, int n) {

		int parent[] = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		int indegree[] = new int[n];
		Arrays.fill(indegree, -1);

		int b1 = -1;
		int b2 = -1;
		for (int i = 0; i < arr.length; i++) {

			if (indegree[arr[i][1]] == -1) {
				indegree[arr[i][1]] = i;
			} else {
				b1 = i;
				b2 = indegree[arr[i][1]];
				break;
			}
		}

		for (int i = 0; i < arr.length; i++) {

			if (b1 == i) {
				continue;
			} else {

				int u = arr[i][0];
				int v = arr[i][1];

				boolean flag = union(u, v, parent);
				if (flag == false) {
					if (b1 == -1) {
						System.out.println(arr[i][0] + " " + arr[i][1]);
					} else {
						System.out.println(arr[b2][0] + " " + arr[b2][1]);
					}
				}
			}
		}

		System.out.println(arr[b1][0] + " " + arr[b1][1]);
	}

	private static boolean union(int x, int y, int parent[]) {

		int lx = find(x, parent);
		int ly = find(y, parent);

		if (lx != ly) {
			parent[lx] = ly;
			return true;
		}
		return false;
	}

	private static void redundantConnection(int[][] arr, int n) {

		int parent[] = new int[n];
		int rank[] = new int[n];

		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
			rank[i] = 1;
		}

		for (int i = 0; i < arr.length; i++) {

			int lx = find(arr[i][0], parent);
			int ly = find(arr[i][1], parent);

			if (lx != ly) {
				if (rank[lx] > rank[ly]) {
					parent[ly] = lx;
				} else if (rank[ly] > rank[lx]) {
					parent[lx] = ly;
				} else {
					parent[lx] = ly;
					rank[ly]++;
				}
			} else {
				System.out.println(arr[i][0] + "-" + arr[i][1]);
				break;
			}
		}

	}

	private static int find(int x, int parent[]) {

		if (parent[x] == x) {
			return x;
		}

		int temp = find(parent[x], parent);
		parent[x] = temp;
		return temp;
	}
}
