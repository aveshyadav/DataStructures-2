package com.javacode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph_3_25_32 {

	public static void main(String[] args) {

//		int arr[][] = { { 1, 2 }, { 3, 4 }, { 1, 3 }, { 5, 6 } };
//		dsuAlgo(arr, 6);

//		int arr[][] = { { 3, 1, 2 }, { 1, 1, 3 }, { 1, 2, 4 }, { 2, 3, 4 }, { 1, 1, 2 }, { 3, 2, 3 } };
//		removeMaxEdges(arr, 4);

//		int arr[][] = { { 0, 3 }, { 1, 2 }, { 0, 2 }, { 1, 4 }, { 2, 3 }, { 1, 3 } };
//		numberOfIsland2(arr, 5);

//		int arr[][] = { { 0, 1, 10 }, { 0, 2, 13 }, { 0, 3, 16 }, { 1, 0, 10 }, { 1, 2, 12 }, { 2, 0, 13 },
//				{ 2, 1, 12 }, { 2, 3, 14 }, { 3, 0, 16 }, { 3, 2, 14 } };
//		kruskalAlgo(arr, 4);

//		String grid[] = { "-/", "/-" };
//		String grid[] = { "/\\/", "/\\-", "/\\-" };
//		regionCutBySlashes(grid);

//		int arr[][] = { { 20, -21, 14 }, { -19, 4, 19 }, { 22, -47, 24 }, { -19, 4, 19 } };
//		rankTransformOfMatrix(arr);

		int source[] = { 1, 2, 3, 4 };
		int target[] = { 2, 1, 4, 5 };
		int allowedSwaps[][] = { { 0, 1 }, { 2, 3 } };

		minimumHammingDistance(source, target, allowedSwaps);

//		accountsMerge();
	}

	private static void minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {

		int parent[] = new int[source.length];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < allowedSwaps.length; i++) {

			int lx = findParentHD(allowedSwaps[i][0], parent);
			int ly = findParentHD(allowedSwaps[i][1], parent);

			if (lx != ly) {
				parent[lx] = ly;
			}
		}

		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
		for (int i = 0; i < source.length; i++) {

			int root = findParentHD(i, parent);
			int num = source[i];

			map.putIfAbsent(root, new HashMap<>());
			Map<Integer, Integer> map2 = map.get(root);
			map2.put(num, map2.getOrDefault(num, 0) + 1);
		}

		int hd = 0;
		for (int i = 0; i < target.length; i++) {

			int root = findParentHD(i, parent);
			int num = target[i];

			Map<Integer, Integer> map2 = map.get(root);
			if (map2.containsKey(num)) {
				if (map2.get(num) > 0) {
					map2.put(num, map2.get(num) - 1);
				} else {
					hd++;
				}
			} else {
				hd++;
			}
		}

		System.out.println("Min hamming distance: " + hd);
	}

	private static int findParentHD(int x, int parent[]) {

		if (parent[x] == x) {
			return x;
		}

		int temp = findParentHD(parent[x], parent);
		parent[x] = temp;
		return temp;
	}

	private static int rows[];
	private static int cols[];

	private static void rankTransformOfMatrix(int[][] arr) {

		int n = arr.length;
		int m = arr[0].length;
		rows = new int[n];
		cols = new int[m];

		Pair3 parr[] = new Pair3[n * m];

		int idx = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				parr[idx++] = new Pair3(i, j, arr[i][j]);
			}
		}
		Arrays.sort(parr);

		int prev = Integer.MIN_VALUE;
		List<Pair3> al = new ArrayList<>();
		for (int i = 0; i < parr.length; i++) {

			int val = parr[i].val;
			if (val != prev) {

				process(al, arr);
				al = new ArrayList<>();
				prev = val;
			}
			al.add(parr[i]);
		}

		process(al, arr);
		displayBoard(arr);
	}

	private static void process(List<Pair3> list, int arr[][]) {

		int n = arr.length;
		int m = arr[0].length;

		int parent[] = new int[n + m];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}

		for (Pair3 mp : list) {

			int i = mp.row;
			int j = mp.col;

			int p1 = findParent(i, parent);
			int p2 = findParent(n + j, parent);

			if (p1 != p2) {
				int maxRank = Math.min(parent[p1], Math.min(parent[p2], -Math.max(rows[i], cols[j]) - 1));
				parent[p2] = maxRank;
				parent[p1] = p2;
			}
		}

		for (Pair3 mp : list) {

			int i = mp.row;
			int j = mp.col;

			int p = findParent(i, parent);
			int rank = -parent[p];

			arr[i][j] = rank;
			rows[i] = rank;
			cols[j] = rank;
		}
	}

	private static int findParent(int x, int parent[]) {

		if (parent[x] < 0) {
			return x;
		}

		int temp = findParent(parent[x], parent);
		parent[x] = temp;
		return temp;
	}

	private static class Pair3 implements Comparable<Pair3> {

		int row;
		int col;
		int val;

		public Pair3(int row, int col, int val) {
			this.row = row;
			this.col = col;
			this.val = val;
		}

		@Override
		public int compareTo(Pair3 o) {
			return this.val - o.val;
		}
	}

	private static void regionCutBySlashes(String[] grid) {

		int n = grid.length + 1;
		int parent[] = new int[n * n];
		int rank[] = new int[n * n];

		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
			rank[i] = 1;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0 || i == n - 1 || j == n - 1) {
					int idx = i * n + j;
					regionCutUnion(0, idx, parent, rank);
				}
			}
		}

		int count = 1;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length(); j++) {

				char ch = grid[i].charAt(j);
				if (ch == '/') {

					int x1 = i;
					int x2 = j + 1;

					int y1 = i + 1;
					int y2 = j;

					int x = x1 * n + x2;
					int y = y1 * n + y2;

					boolean flag = regionCutUnion(x, y, parent, rank);
					if (flag) {
						count++;
					}

				} else if (ch == '\\') {

					int x1 = i;
					int x2 = j;

					int y1 = i + 1;
					int y2 = j + 1;

					int x = x1 * n + x2;
					int y = y1 * n + y2;

					boolean flag = regionCutUnion(x, y, parent, rank);
					if (flag) {
						count++;
					}
				}
			}
		}

		System.out.println("Total Regions: " + count);
	}

	private static boolean regionCutUnion(int x, int y, int parent[], int rank[]) {

		int lx = regionCutFind(x, parent);
		int ly = regionCutFind(y, parent);

		if (lx != ly) {
			if (rank[lx] > rank[ly]) {
				parent[ly] = lx;
			} else if (rank[ly] > rank[lx]) {
				parent[lx] = ly;
			} else {
				parent[lx] = ly;
				rank[ly]++;
			}
			return false;
		}
		return true;
	}

	private static int regionCutFind(int x, int parent[]) {
		if (parent[x] == x) {
			return x;
		}

		int temp = regionCutFind(parent[x], parent);
		parent[x] = temp;
		return temp;
	}

	private static void kruskalAlgo(int arr[][], int n) {

		List<Pair2> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(new Pair2(arr[i][0], arr[i][1], arr[i][2]));
		}
		Collections.sort(list);

		int parent[] = new int[n + 1];
		int rank[] = new int[n + 1];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
			rank[i] = 1;
		}

		int minCost = 0;
		for (int i = 0; i < list.size(); i++) {

			int src = list.get(i).src;
			int dest = list.get(i).dest;
			int wt = list.get(i).wt;

			int lx = findkruskal(src, parent);
			int ly = findkruskal(dest, parent);

			if (lx != ly) {
				if (rank[lx] > rank[ly]) {
					parent[ly] = lx;
				} else if (rank[ly] > rank[lx]) {
					parent[lx] = ly;
				} else {
					parent[lx] = ly;
					rank[ly]++;
				}
				minCost += wt;
			}
		}

		System.out.println("Min Cost: " + minCost);
	}

	private static int findkruskal(int x, int parent[]) {
		if (parent[x] == x) {
			return x;
		}

		int temp = findkruskal(parent[x], parent);
		parent[x] = temp;
		return temp;
	}

	private static class Pair2 implements Comparable<Pair2> {

		int src;
		int dest;
		int wt;

		public Pair2(int src, int dest, int wt) {
			this.src = src;
			this.dest = dest;
			this.wt = wt;
		}

		@Override
		public int compareTo(Pair2 o) {
			return this.wt - o.wt;
		}
	}

	private static void numberOfIsland2(int[][] arr, int n) {

		int board[][] = new int[n][n];
		displayBoard(board);

		int parent[] = new int[n * n];
		int rank[] = new int[n * n];

		int count = 0;
		int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		for (int i = 0; i < arr.length; i++) {

			int row = arr[i][0];
			int col = arr[i][1];
			int cell = (row * n) + col;
			board[row][col] = 1;
			parent[cell] = cell;
			count++;

			for (int d = 0; d < dirs.length; d++) {

				int nr = row + dirs[d][0];
				int nc = col + dirs[d][1];

				if (nr >= 0 && nc >= 0 && nr < board.length && nc < board[0].length && board[nr][nc] == 1) {

					int cell2 = nr * n + nc;

					int lx = findNoi(cell, parent);
					int ly = findNoi(cell2, parent);

					if (lx != ly) {
						if (rank[lx] > rank[ly]) {
							parent[ly] = lx;
						} else if (rank[ly] > rank[lx]) {
							parent[lx] = ly;
						} else {
							parent[lx] = ly;
							rank[ly]++;
						}
						count--;
					}
				}
			}

			System.out.println("Total Number of Island: " + count);
			displayBoard(board);
		}

		System.out.println("Total Number of Island: " + count);
	}

	private static int findNoi(int x, int parent[]) {

		if (parent[x] == x) {
			return x;
		}

		int temp = findNoi(parent[x], parent);
		parent[x] = temp;
		return temp;
	}

	private static void displayBoard(int board[][]) {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------------------------");
	}

	private static void removeMaxEdges(int[][] arr, int n) {

		List<Pair> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(new Pair(arr[i][0], arr[i][1], arr[i][2]));
		}
		Collections.sort(list);

		int aliceParent[] = new int[n + 1];
		int aliceRank[] = new int[n + 1];
		int bobParent[] = new int[n + 1];
		int bobRank[] = new int[n + 1];

		for (int i = 0; i < aliceParent.length; i++) {
			aliceParent[i] = i;
			aliceRank[i] = 1;
			bobParent[i] = i;
			bobRank[i] = 1;
		}

		int removeCountEdge = 0;
		int mergeda = 0;
		int mergedb = 0;

		for (int i = 0; i < list.size(); i++) {

			Pair mp = list.get(i);
			if (mp.type == 3) {

				boolean a1 = removeMaxEdgesUnion(mp.src, mp.dest, aliceParent, aliceRank);
				if (a1 == true) {
					mergeda++;
				}

				boolean a2 = removeMaxEdgesUnion(mp.src, mp.dest, bobParent, bobRank);
				if (a2 == true) {
					mergedb++;
				}

				if (a1 == false && a2 == false) {
					removeCountEdge++;
				}

			} else if (mp.type == 2) {

				boolean a2 = removeMaxEdgesUnion(mp.src, mp.dest, bobParent, bobRank);
				if (a2 == true) {
					mergedb++;
				} else {
					removeCountEdge++;
				}

			} else {
				boolean a1 = removeMaxEdgesUnion(mp.src, mp.dest, aliceParent, aliceRank);
				if (a1 == true) {
					mergeda++;
				} else {
					removeCountEdge++;
				}
			}
		}

		if (mergeda == n - 1 && mergedb == n - 1) {
			System.out.println("Removed Edge: " + -1);
		} else {
			System.out.println("Removed Edge: " + removeCountEdge);
		}
	}

	private static boolean removeMaxEdgesUnion(int x, int y, int parent[], int rank[]) {

		int lx = removeMaxEdgesFind(x, parent);
		int ly = removeMaxEdgesFind(y, parent);

		if (lx != ly) {

			if (rank[lx] > rank[ly]) {
				parent[ly] = lx;
			} else if (rank[ly] > rank[lx]) {
				parent[lx] = ly;
			} else {
				parent[lx] = ly;
				rank[ly]++;
			}

			return true;
		}
		return false;
	}

	private static int removeMaxEdgesFind(int x, int parent[]) {

		if (parent[x] == x) {
			return x;
		}

		int temp = removeMaxEdgesFind(parent[x], parent);
		parent[x] = temp;
		return temp;
	}

	private static class Pair implements Comparable<Pair> {

		int type;
		int src;
		int dest;

		public Pair(int type, int src, int dest) {
			this.type = type;
			this.src = src;
			this.dest = dest;
		}

		@Override
		public int compareTo(Pair o) {
			return o.type - this.type;
		}
	}

	private static void dsuAlgo(int[][] arr, int n) {

		parent = new int[n + 1];
		rank = new int[n + 1];

		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
			rank[i] = 1;
		}

		for (int i = 0; i < arr.length; i++) {
			unionDSU(arr[i][0], arr[i][1]);
		}

		for (int i = 0; i < parent.length; i++) {
			System.out.print(parent[i] + " ");
		}
	}

	private static int findDSU(int x) {

		if (parent[x] == x) {
			return x;
		}

		int temp = findDSU(parent[x]);
		parent[x] = temp;
		return temp;
	}

	private static void unionDSU(int x, int y) {

		int lx = findDSU(x);
		int ly = findDSU(y);

		if (lx != ly) {
			if (rank[lx] > rank[ly]) {
				parent[ly] = lx;
			} else if (rank[ly] > rank[lx]) {
				parent[lx] = ly;
			} else {
				parent[lx] = ly;
				rank[ly]++;
			}
		}
	}

	private static int parent[];
	private static int rank[];

}
