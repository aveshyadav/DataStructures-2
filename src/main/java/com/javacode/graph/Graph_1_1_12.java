package com.javacode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Graph_1_1_12 {

	public static void main(String[] args) {

//		int arr[][] = { { 2, 3, 4, 3, 1 }, { 4, 2, 2, 2, 1 }, { 1, 2, 2, 2, 1 }, { 5, 2, 2, 2, 1 }, { 6, 7, 0, 1, 1 } };
//		display(arr);
//
//		int color = 3;
//		int d = arr[1][1];
//		coloringBorder(arr, 1, 1, d);
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[0].length; j++) {
//				if (arr[i][j] == -d) {
//					arr[i][j] = color;
//				}
//			}
//		}
//
//		System.out.println();
//		display(arr);

//		int arr[][] = { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
//		int arr[][] = { { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } };

//		display(arr);
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[0].length; j++) {
//				if (i == 0 || j == 0 || i == arr.length - 1 || j == arr[0].length - 1) {
//					countNoOfEnclave(arr, i, j);
//				}
//			}
//		}
//
//		int count = 0;
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[0].length; j++) {
//				if (arr[i][j] == 1) {
//					count++;
//				}
//			}
//		}
//		System.out.println("No of Enclave: " + count);

//		int arr[][] = { { 1, 1, 0, 0, 0 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 0, 0 } };
//		display(arr);
//
//		Set<String> set = new HashSet<>();
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[0].length; j++) {
//				if (arr[i][j] == 1) {
//					StringBuilder sb = new StringBuilder("S");
//					numberOfDistinctIsland(arr, i, j, sb, "");
//					set.add(sb.toString());
//				}
//			}
//		}
//		System.out.println(set);
//		System.out.println("No of Distinct Island: " + set.size());

//		int arr[][] = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
//		zeroOneMatrix(arr);

//		int arr[][] = { { 2, 0, 1, 0, 1 }, { 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 1, 1, 1 }, { 0, 1, 1, 2, 0 } };
//		rottenOranges(arr);

//		int arr[][] = { { 1, 0, 0, 1 }, { 0, 0, 0, 0 }, { 1, 0, 0, 1 } };
//		asFarLandAsPossible(arr);

//		int arr[][] = { { 1, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1 }, { 1, 1, 0, 0, 1 } };
//		shortestBridge(arr);

//		int arr[][] = { { 1, 2, 3 }, { 1, 4, 5 }, { 5, 8, 7 }, { 3, 6, 7 } };
//		busRoutes(arr, 1, 7);

		int arr[][] = { { 4, 1, 2 }, { 5, 0, 3 } };
		slidingPuzzle(arr);

//		int arr[] = { 6, 4, 2, 14, 8, 10, 12, 16 };
//		minSwapsRequired(arr);

//		List<List<Edge>> graph = buildGraph();
//		boolean visited[] = new boolean[graph.size()];
//		zeroOneBFS(graph, visited, 1, 7);

//		List<List<Edge>> graph = buildKahnsGraph();
//		kahnsAlgo(graph);
	}

	private static void kahnsAlgo(List<List<Edge>> graph) {

		int inDegree[] = new int[graph.size()];
		for (int i = 0; i < graph.size(); i++) {
			List<Edge> es = graph.get(i);
			for (Edge e : es) {
				inDegree[e.nbr]++;
			}
		}

		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				queue.add(i);
			}
		}

		while (queue.size() > 0) {

			int top = queue.remove();
			System.out.println(top);

			for (Edge e : graph.get(top)) {
				inDegree[e.nbr]--;
				if (inDegree[e.nbr] == 0) {
					queue.add(e.nbr);
				}
			}
		}
	}

	private static void zeroOneBFS(List<List<Edge>> graph, boolean[] visited, int src, int dest) {

		Set<String> set = new HashSet<>();
		for (int i = 0; i < graph.size(); i++) {
			List<Edge> list = graph.get(i);
			for (Edge e : list) {
				set.add(e.src + "-" + e.nbr);
				String np = e.nbr + "-" + e.src;
				if (!set.contains(np)) {
					set.add(np);
					graph.get(e.nbr - 1).add(new Edge(e.nbr, e.src, 1));
				}
			}
		}

		for (int i = 0; i < graph.size(); i++) {
			for (Edge e : graph.get(i)) {
				System.out.print(e.src + " " + e.nbr + " " + e.wt + ",");
			}
			System.out.println();
		}

		PriorityQueue<Pair4> pq = new PriorityQueue<>();
		pq.add(new Pair4(src, src + "", 0));

		while (pq.size() > 0) {

			Pair4 mp = pq.remove();
			if (visited[mp.src - 1] == false) {

				visited[mp.src - 1] = true;

				if (mp.src == dest) {
					System.out.println("Ans: " + mp.src + "@" + mp.path + "@" + mp.wt);
					return;
				}
				System.out.println(mp.src + "@" + mp.path + "@" + mp.wt);

				for (Edge e : graph.get(mp.src - 1)) {
					if (visited[e.nbr - 1] == false) {
						pq.add(new Pair4(e.nbr, mp.path + e.nbr, mp.wt + e.wt));
					}
				}
			}
		}
	}

	private static class Pair4 implements Comparable<Pair4> {

		int src;
		String path;
		int wt;

		public Pair4(int src, String path, int wt) {
			this.src = src;
			this.path = path;
			this.wt = wt;
		}

		@Override
		public int compareTo(Pair4 o) {
			return this.wt - o.wt;
		}
	}

	private static void minSwapsRequired(int[] arr) {

		Pair2 parr[] = new Pair2[arr.length];
		for (int i = 0; i < arr.length; i++) {
			parr[i] = new Pair2(arr[i], i);
		}

		Arrays.sort(parr);

		boolean vis[] = new boolean[arr.length];

		int count = 0;
		for (int i = 0; i < arr.length; i++) {

			if (vis[parr[i].idx] == false && parr[i].idx != i) {

				int clen = 0;
				int j = i;
				while (vis[j] == false) {
					vis[j] = true;
					clen++;
					j = parr[j].idx;
				}
				count += clen - 1;
			}
		}

		System.out.println("Min swaps required: " + count);
	}

	private static class Pair2 implements Comparable<Pair2> {

		int val;
		int idx;

		public Pair2(int val, int idx) {
			this.val = val;
			this.idx = idx;
		}

		@Override
		public int compareTo(Pair2 o) {
			return this.val - o.val;
		}
	}

	private static void slidingPuzzle(int[][] arr) {

		int swapIdx[][] = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };
		String str = "";
		String ans = "123450";

		Set<String> set = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				str = str + arr[i][j];
			}
		}

		set.add(str);
		Queue<String> queue = new LinkedList<>();
		queue.add(str);

		int lvl = -1;
		while (queue.size() > 0) {

			lvl++;
			int size = queue.size();
			while (size-- > 0) {

				String top = queue.remove();

				if (top.equals(ans)) {
					System.out.println("level: " + lvl);
					return;
				}

				int idx = 0;
				for (int i = 0; i < top.length(); i++) {
					if (top.charAt(i) == '0') {
						idx = i;
						break;
					}
				}

				int ll[] = swapIdx[idx];
				for (int i : ll) {

					char carr[] = top.toCharArray();
					carr[idx] = carr[i];
					carr[i] = '0';

					String newStr = new String(carr);
					if (!set.contains(newStr)) {
						set.add(newStr);
						queue.add(newStr);
					}
				}
			}
		}

		System.out.println("level: " + lvl);
	}

	private static void busRoutes(int[][] arr, int src, int dest) {

		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				int d = arr[i][j];
				map.putIfAbsent(d, new ArrayList<>());
				map.get(d).add(i);
			}
		}

		System.out.println(map);

		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);

		boolean bus[] = new boolean[arr.length];
		Set<Integer> busStops = new HashSet<>();
		busStops.add(src);

		int lvl = -1;
		while (queue.size() > 0) {

			lvl++;
			int size = queue.size();
			while (size-- > 0) {

				int top = queue.remove();
				if (dest == top) {
					System.out.println("Min bus required: " + lvl);
					return;
				}

				List<Integer> stops = map.get(top);
				for (int i : stops) {

					if (bus[i] == false) {

						bus[i] = true;
						for (int j = 0; j < arr[0].length; j++) {
							if (!busStops.contains(arr[i][j])) {

								busStops.add(arr[i][j]);
								queue.add(arr[i][j]);
							}
						}
					}
				}
			}

		}

		System.out.println("Min bus required: " + lvl);
	}

	private static void shortestBridge(int[][] arr) {

		Queue<Pair> queue = new LinkedList<>();
		boolean vis[][] = new boolean[arr.length][arr[0].length];

		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1) {
					shortestBridgeDFS(arr, i, j, vis, queue);
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}

		int count = 0;
		while (queue.size() > 0) {

			Pair mp = queue.remove();

			int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
			for (int i = 0; i < dirs.length; i++) {

				int nr = mp.row + dirs[i][0];
				int nc = mp.col + dirs[i][1];

				if (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr[0].length && vis[nr][nc] == false) {
					vis[nr][nc] = true;
					queue.add(new Pair(nr, nr, mp.dist + 1));
					count = Math.max(count, mp.dist + 1);
					if (arr[nr][nc] == 1) {
						break;
					}
				}
			}
		}

		System.out.println("Shortest Bridge: " + count);
	}

	private static void shortestBridgeDFS(int arr[][], int row, int col, boolean vis[][], Queue<Pair> queue) {

		if (row < 0 || col < 0 || row == arr.length || col == arr[0].length || vis[row][col] == true
				|| arr[row][col] == 0) {
			return;
		}

		vis[row][col] = true;
		queue.add(new Pair(row, col, 0));
		shortestBridgeDFS(arr, row - 1, col, vis, queue);
		shortestBridgeDFS(arr, row, col + 1, vis, queue);
		shortestBridgeDFS(arr, row, col - 1, vis, queue);
		shortestBridgeDFS(arr, row + 1, col, vis, queue);
	}

	private static void asFarLandAsPossible(int[][] arr) {

		Queue<Pair> queue = new LinkedList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1) {
					queue.add(new Pair(i, j, 0));
				}
			}
		}

		int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (queue.size() > 0) {

			Pair mp = queue.remove();

			for (int i = 0; i < dirs.length; i++) {

				int nr = mp.row + dirs[i][0];
				int nc = mp.col + dirs[i][1];

				if (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr[0].length && arr[nr][nc] == 0) {
					queue.add(new Pair(nr, nc, mp.dist + 1));
					arr[nr][nc] = mp.dist + 1;
				}
			}
		}

		display(arr);

	}

	private static void rottenOranges(int[][] arr) {

		Queue<Pair> queue = new LinkedList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 2) {
					queue.add(new Pair(i, j, 0));
				}
			}
		}

		int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (queue.size() > 0) {

			int size = queue.size();
			while (size-- > 0) {

				Pair mp = queue.remove();
				arr[mp.row][mp.col] = mp.dist;

				for (int i = 0; i < dirs.length; i++) {

					int nr = mp.row + dirs[i][0];
					int nc = mp.col + dirs[i][1];

					if (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr[0].length && arr[nr][nc] == 1) {
						queue.add(new Pair(nr, nc, mp.dist + 1));
					}
				}
			}
		}

		display(arr);

		int max = 0;
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 1) {
					flag = true;
				} else if (arr[i][j] > 1) {
					max = Math.max(max, arr[i][j]);
				}
			}
		}

		if (flag) {
			System.out.println("Total time taken to Rot oranges: " + -1);
		} else {
			System.out.println("Total time taken to Rot oranges: " + max);
		}

	}

	private static void zeroOneMatrix(int[][] arr) {

		Queue<Pair> queue = new LinkedList<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == 0) {
					queue.add(new Pair(i, j, 0));
				} else {
					arr[i][j] = -1;
				}
			}
		}

		int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (queue.size() > 0) {

			Pair mp = queue.remove();
			arr[mp.row][mp.col] = mp.dist;

			for (int i = 0; i < dirs.length; i++) {

				int nr = mp.row + dirs[i][0];
				int nc = mp.col + dirs[i][1];

				if (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr[0].length && arr[nr][nc] == -1) {
					queue.add(new Pair(nr, nc, mp.dist + 1));
				}
			}

		}

		display(arr);
	}

	private static class Pair {

		int row;
		int col;
		int dist;

		public Pair(int row, int col, int dist) {
			this.row = row;
			this.col = col;
			this.dist = dist;
		}
	}

	private static void numberOfDistinctIsland(int arr[][], int row, int col, StringBuilder sb, String d) {

		if (row < 0 || col < 0 || row == arr.length || col == arr[0].length || arr[row][col] != 1) {
			return;
		}

		arr[row][col] = -1;
		sb.append(d);
		numberOfDistinctIsland(arr, row - 1, col, sb, "T");
		numberOfDistinctIsland(arr, row + 1, col, sb, "D");
		numberOfDistinctIsland(arr, row, col - 1, sb, "L");
		numberOfDistinctIsland(arr, row, col + 1, sb, "R");
	}

	private static void countNoOfEnclave(int arr[][], int row, int col) {

		if (row < 0 || col < 0 || row == arr.length || col == arr[0].length || arr[row][col] != 1) {
			return;
		}

		arr[row][col] = 0;
		countNoOfEnclave(arr, row - 1, col);
		countNoOfEnclave(arr, row + 1, col);
		countNoOfEnclave(arr, row, col - 1);
		countNoOfEnclave(arr, row, col + 1);
	}

	private static void coloringBorder(int arr[][], int row, int col, int d) {

		if (row < 0 || col < 0 || row == arr.length || col == arr[0].length || arr[row][col] != d) {
			return;
		}

		int count = 0;
		if (row > 0 && col > 0 && row < arr.length && col < arr[0].length && Math.abs(arr[row - 1][col]) == d
				&& Math.abs(arr[row + 1][col]) == d && Math.abs(arr[row][col - 1]) == d
				&& Math.abs(arr[row][col + 1]) == d) {
			count = 4;
		}

		arr[row][col] = -arr[row][col];
		coloringBorder(arr, row - 1, col, d);
		coloringBorder(arr, row, col + 1, d);
		coloringBorder(arr, row + 1, col, d);
		coloringBorder(arr, row, col - 1, d);

		if (count == 4) {
			arr[row][col] = -arr[row][col];
		}
	}

	private static void display(int arr[][]) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static class Edge {

		int src;
		int nbr;
		int wt;

		public Edge(int src, int nbr, int wt) {
			this.src = src;
			this.nbr = nbr;
			this.wt = wt;
		}
	}

	public static List<List<Edge>> buildGraph() {

		List<List<Edge>> list = new ArrayList<List<Edge>>();

		List<Edge> e1 = new ArrayList<Edge>();
		e1.add(new Edge(1, 2, 0));
		list.add(e1);

		List<Edge> e2 = new ArrayList<Edge>();
		list.add(e2);

		List<Edge> e3 = new ArrayList<Edge>();
		e3.add(new Edge(3, 2, 0));
		e3.add(new Edge(3, 4, 0));
		list.add(e3);

		List<Edge> e4 = new ArrayList<Edge>();
		e4.add(new Edge(4, 3, 0));
		list.add(e4);

		List<Edge> e5 = new ArrayList<Edge>();
		e5.add(new Edge(5, 6, 0));
		list.add(e5);

		List<Edge> e6 = new ArrayList<Edge>();
		e6.add(new Edge(6, 2, 0));
		list.add(e6);

		List<Edge> e7 = new ArrayList<Edge>();
		e7.add(new Edge(7, 4, 0));
		e7.add(new Edge(7, 5, 0));
		list.add(e7);

		return list;
	}

	public static List<List<Edge>> buildKahnsGraph() {

		List<List<Edge>> list = new ArrayList<List<Edge>>();

		List<Edge> e1 = new ArrayList<Edge>();
		e1.add(new Edge(0, 1, 10));
		e1.add(new Edge(0, 3, 40));
		list.add(e1);

		List<Edge> e2 = new ArrayList<Edge>();
//		e2.add(new Edge(1, 0, 10));
		e2.add(new Edge(1, 2, 10));
		list.add(e2);

		List<Edge> e3 = new ArrayList<Edge>();
//		e3.add(new Edge(2, 1, 10));
		e3.add(new Edge(2, 3, 10));
		list.add(e3);

		List<Edge> e4 = new ArrayList<Edge>();
//		e4.add(new Edge(3, 0, 40));
//		e4.add(new Edge(3, 2, 10));
//		e4.add(new Edge(3, 4, 2));
		list.add(e4);

		List<Edge> e5 = new ArrayList<Edge>();
		e5.add(new Edge(4, 3, 2));
		e5.add(new Edge(4, 5, 3));
		e5.add(new Edge(4, 6, 8));
		list.add(e5);

		List<Edge> e6 = new ArrayList<Edge>();
//		e6.add(new Edge(5, 4, 3));
		e6.add(new Edge(5, 6, 3));
		list.add(e6);

		List<Edge> e7 = new ArrayList<Edge>();
//		e7.add(new Edge(6, 4, 8));
//		e7.add(new Edge(6, 5, 3));
		list.add(e7);

		return list;
	}
}
