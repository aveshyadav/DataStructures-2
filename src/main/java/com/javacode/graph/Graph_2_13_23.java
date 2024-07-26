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
import java.util.Stack;

public class Graph_2_13_23 {

	public static void main(String[] args) {

//		String words[] = { "baa", "abcd", "abca", "cab", "cad" };
//		String words[] = { "caa", "aaa", "aab" };
//		String words[] = { "wrt", "wrf", "er", "ett", "rftt" };
//		alienDictionary(words);

//		int n = 3;
//		int wells[] = { 1, 2, 2 };
//		int pipes[][] = { { 1, 2, 1 }, { 2, 3, 1 } };
//		optimizeWaterDistribution(n, wells, pipes);

//		int arr[][] = { { 0, 1, 2, 3, 4 }, { 24, 23, 22, 21, 5 }, { 12, 13, 14, 15, 16 }, { 11, 17, 18, 19, 20 },
//				{ 10, 9, 8, 7, 6 } };
//		swimInRisingWater(arr);

//		int arr[][] = { { 0, 1, 10 }, { 1, 2, 10 }, { 2, 3, 10 }, { 0, 3, 40 }, { 3, 4, 2 }, { 4, 5, 3 }, { 5, 6, 3 },
//				{ 4, 6, 8 } };
//		connectingCitiesWithMinCost(arr);

//		int arr[][] = { { 4, 5, 3 }, { 3, 4, 2 }, { 2, 5, -2 }, { 2, 3, -8 }, { 1, 2, 7 }, { 0, 3, 6 }, { 0, 1, 4 } };
//		bellmanFordShortestPathAlgo(arr, 6);

//		int arr[][] = { { 4, 5, 3 }, { 3, 4, 2 }, { 2, 5, -2 }, { 2, 3, -8 }, { 1, 2, 7 }, { 0, 3, 6 }, { 0, 1, 4 } };
//		detectNegativeWeightCycle(arr, 6);

//		int arr[][] = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 }, { 2, 4 }, { 4, 5 }, { 5, 6 }, { 6, 7 } };
//		kosaraujuAlgo(arr);

//		int arr[][] = { { 7, 1 }, { 7, 3 }, { 7, 8 }, { 7, 6 }, { 7, 5 }, { 5, 6 }, { 2, 3 }, { 3, 4 } };
//		motherVertex(arr);

		int arr[][] = { { 1, 8 }, { 8, 5 }, { 5, 2 }, { 2, 1 }, { 2, 4 }, { 4, 3 }, { 3, 6 }, { 6, 7 }, { 7, 3 } };
		articulationPoint(arr, 8);
	}

	private static void articulationPoint(int[][] arr, int n) {

		List<List<Integer>> graph = buildGraph2(arr);
		System.out.println(graph);

		par = new int[n];
		disc = new int[n];
		low = new int[n];
		ap = new boolean[n];
		vis = new boolean[n];

		par[0] = -1;
		time = 0;
		articulationPointHelper(graph, 0);

		for (int i = 0; i < ap.length; i++) {
			System.out.println(ap[i]);
		}
	}

	private static int time = 0;
	private static int par[];
	private static int disc[];
	private static int low[];
	private static boolean ap[];
	private static boolean vis[];

	private static void articulationPointHelper(List<List<Integer>> graph, int src) {

		disc[src] = time;
		low[src] = time;
		time++;

		vis[src] = true;
		int count = 0;
		for (int nbr : graph.get(src)) {

			if (par[src] == nbr) {
				continue;
			} else if (vis[nbr] == true) {
				low[src] = Math.min(low[src], disc[nbr]);
			} else {

				count++;
				par[nbr] = src;
				articulationPointHelper(graph, nbr);

				low[src] = Math.min(low[src], low[nbr]);
				if (par[src] == -1) {
					if (count >= 2) {
						ap[src] = true;
					}
				} else {
					if (low[nbr] >= disc[src]) {
						ap[src] = true;
					}
				}
			}
		}
	}

	private static List<List<Integer>> buildGraph2(int arr[][]) {

		List<List<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			ans.add(new ArrayList<>());
		}

		for (int i = 0; i < arr.length; i++) {

			int u = arr[i][0] - 1;
			int v = arr[i][1] - 1;

			ans.get(u).add(v);
			ans.get(v).add(u);
		}

		return ans;
	}

	private static void motherVertex(int[][] arr) {

		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < arr.length; i++) {
			graph.get(arr[i][0] - 1).add(arr[i][1] - 1);
		}

		System.out.println(graph);
		Stack<Integer> stack = new Stack<>();
		boolean vis[] = new boolean[graph.size()];

		for (int i = 0; i < graph.size(); i++) {
			if (vis[i] == false) {
				motherVertexFillStack(graph, i, stack, vis);
			}
		}

		System.out.println(stack);
		Arrays.fill(vis, false);
		checkMotherVertex(graph, stack.peek(), vis);

		int count = 0;
		for (int i = 0; i < vis.length; i++) {
			if (vis[i] == true) {
				count++;
			}
		}
		if (count == vis.length - 1) {
			System.out.println("Mother vertex: " + (stack.peek() + 1));
		} else {
			System.out.println("No mother vertex found");
		}
	}

	private static void checkMotherVertex(List<List<Integer>> graph, int src, boolean vis[]) {

		vis[src] = true;
		for (int e : graph.get(src)) {
			if (vis[e] == false) {
				checkMotherVertex(graph, e, vis);
			}
		}
	}

	private static void motherVertexFillStack(List<List<Integer>> graph, int src, Stack<Integer> stack, boolean vis[]) {

		vis[src] = true;
		for (int e : graph.get(src)) {
			if (vis[e] == false) {
				motherVertexFillStack(graph, e, stack, vis);
			}
		}
		stack.push(src);
	}

	private static void kosaraujuAlgo(int[][] arr) {

		List<List<Integer>> graph = buildGraph(arr);
		boolean vis[] = new boolean[graph.size()];

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			if (vis[arr[i][0]] == false) {
				getConnectedCompnents(graph, arr[i][0], vis, stack);
			}
		}
		System.out.println(graph);
		graph = reverseEdge(graph);
		System.out.println(graph);
		System.out.println(stack);

		int count = 0;
		Arrays.fill(vis, false);
		while (stack.size() > 0) {

			int top = stack.pop();
			if (vis[top] == false) {
				getConnectedCompnents2(graph, top, vis);
				count++;
			}
		}

		System.out.println("No of strongly connected graph: " + count);
	}

	private static void getConnectedCompnents2(List<List<Integer>> graph, int src, boolean vis[]) {

		if (vis[src] == false) {
			vis[src] = true;

			for (int e : graph.get(src)) {
				if (vis[e] == false) {
					getConnectedCompnents2(graph, e, vis);
				}
			}
		}
	}

	private static List<List<Integer>> reverseEdge(List<List<Integer>> graph) {

		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < graph.size(); i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < graph.size(); i++) {
			for (int j : graph.get(i)) {
				list.get(j).add(i);
			}
		}
		return list;
	}

	private static void getConnectedCompnents(List<List<Integer>> graph, int src, boolean vis[], Stack<Integer> stack) {

		if (vis[src] == false) {
			vis[src] = true;

			for (int e : graph.get(src)) {
				if (vis[e] == false) {
					getConnectedCompnents(graph, e, vis, stack);
				}
			}

			stack.push(src);
		}
	}

	private static List<List<Integer>> buildGraph(int[][] arr) {

		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < arr.length; i++) {
			list.get(arr[i][0]).add(arr[i][1]);
		}

		return list;
	}

	private static void detectNegativeWeightCycle(int[][] arr, int n) {

		int path[] = new int[n];
		Arrays.fill(path, Integer.MAX_VALUE);
		path[0] = 0;

		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < arr.length; j++) {

				int u = arr[j][0];
				int v = arr[j][1];
				int w = arr[j][2];

				if (path[u] != Integer.MAX_VALUE) {
					if (path[u] + w < path[v]) {
						path[v] = path[u] + w;
					}
				}
			}
		}

		boolean flag = false;
		for (int j = 0; j < arr.length; j++) {

			int u = arr[j][0];
			int v = arr[j][1];
			int w = arr[j][2];

			if (path[u] != Integer.MAX_VALUE) {
				if (path[u] + w < path[v]) {
					path[v] = path[u] + w;
					flag = true;
					break;
				}
			}
		}
		System.out.println("Negative Edge weight cycle found: " + flag);
	}

	private static void bellmanFordShortestPathAlgo(int[][] arr, int n) {

		int path[] = new int[n];
		Arrays.fill(path, Integer.MAX_VALUE);
		path[0] = 0;

		for (int i = 0; i < n - 1; i++) {

			for (int j = 0; j < arr.length; j++) {

				int u = arr[j][0];
				int v = arr[j][1];
				int w = arr[j][2];

				if (path[u] != Integer.MAX_VALUE) {
					if (path[u] + w < path[v]) {
						path[v] = path[u] + w;
					}
				}
			}
		}

		for (int i = 0; i < path.length; i++) {
			System.out.print(path[i] + " ");
		}
	}

	private static void connectingCitiesWithMinCost(int[][] arr) {

		List<List<Edge>> graph = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < arr.length; i++) {

			int u = arr[i][0];
			int v = arr[i][1];
			int w = arr[i][2];

			graph.get(u).add(new Edge(u, v, w));
			graph.get(v).add(new Edge(v, u, w));
		}

		PriorityQueue<Pair3> pq = new PriorityQueue<>();
		pq.add(new Pair3(0, 0, "0"));
		boolean vis[] = new boolean[arr.length];
		int cost = 0;

		while (pq.size() > 0) {

			Pair3 mp = pq.remove();
			if (vis[mp.src] == false) {

				vis[mp.src] = true;
				System.out.println(mp.path + "@" + mp.wt);
				cost += mp.wt;

				for (Edge e : graph.get(mp.src)) {
					if (vis[e.nbr] == false) {
						pq.add(new Pair3(e.nbr, e.wt, mp.path + "-" + e.wt));
					}
				}
			}
		}

		System.out.println("Min cost: " + cost);
	}

	private static class Pair3 implements Comparable<Pair3> {

		int src;
		int wt;
		String path;

		public Pair3(int src, int wt, String path) {
			this.src = src;
			this.wt = wt;
			this.path = path;
		}

		@Override
		public int compareTo(Pair3 o) {
			return this.wt - o.wt;
		}
	}

	private static void swimInRisingWater(int[][] arr) {

		PriorityQueue<Pair2> pq = new PriorityQueue<>();
		pq.add(new Pair2(0, 0, arr[0][0], arr[0][0] + ""));
		boolean vis[][] = new boolean[arr.length][arr[0].length];

		while (pq.size() > 0) {

			Pair2 mp = pq.remove();
			if (vis[mp.row][mp.col] == false) {

				vis[mp.row][mp.col] = true;
				System.out.println(mp.path + "@" + mp.ht);
				if (mp.row == arr.length - 1 && mp.col == arr[0].length - 1) {
					return;
				}

				int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
				for (int i = 0; i < dirs.length; i++) {

					int nr = mp.row + dirs[i][0];
					int nc = mp.col + dirs[i][1];

					if (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr[0].length && vis[nr][nc] == false) {
						pq.add(new Pair2(nr, nc, Math.max(mp.ht, arr[nr][nc]), mp.path + "-" + arr[nr][nc]));
					}
				}
			}
		}
	}

	private static class Pair2 implements Comparable<Pair2> {

		int row;
		int col;
		int ht;
		String path;

		public Pair2(int row, int col, int ht, String path) {
			this.row = row;
			this.col = col;
			this.ht = ht;
			this.path = path;
		}

		@Override
		public int compareTo(Pair2 o) {
			return this.ht - o.ht;
		}
	}

	private static void optimizeWaterDistribution(int n, int[] wells, int[][] pipes) {

		ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < pipes.length; i++) {

			int u = pipes[i][0];
			int v = pipes[i][1];
			int w = pipes[i][2];

			graph.get(u).add(new Pair(v, w));
			graph.get(v).add(new Pair(u, w));
		}

		for (int i = 0; i < wells.length; i++) {

			graph.get(i + 1).add(new Pair(0, wells[i]));
			graph.get(0).add(new Pair(i + 1, wells[i]));
		}

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(0, 0));
		boolean vis[] = new boolean[wells.length + 1];

		int ans = 0;
		while (pq.size() > 0) {

			Pair top = pq.remove();

			if (vis[top.vtx] == false) {
				System.out.print(top.vtx + " ");
				vis[top.vtx] = true;
				ans += top.wt;

				for (Pair np : graph.get(top.vtx)) {
					if (vis[np.vtx] == false) {
						pq.add(new Pair(np.vtx, np.wt));
					}
				}
			}
		}
		System.out.println();
		System.out.println("Min Cost: " + ans);
	}

	private static class Pair implements Comparable<Pair> {

		int vtx;
		int wt;

		public Pair(int vtx, int wt) {
			this.vtx = vtx;
			this.wt = wt;
		}

		@Override
		public int compareTo(Pair o) {
			return this.wt - o.wt;
		}
	}

	private static void alienDictionary(String[] words) {

		Map<Character, Integer> indegree = new HashMap<>();
		Map<Character, Set<Character>> graph = new HashMap<>();

		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words[i].length(); j++) {
				indegree.put(words[i].charAt(j), 0);
			}
		}

		for (int i = 0; i < words.length - 1; i++) {

			String curr = words[i];
			String next = words[i + 1];
			int len = Math.min(curr.length(), next.length());

			for (int j = 0; j < len; j++) {

				char c1 = curr.charAt(j);
				char c2 = next.charAt(j);
				if (c1 != c2) {

					if (graph.containsKey(c1)) {
						Set<Character> set = graph.get(c1);
						if (!set.contains(c2)) {
							set.contains(c2);
							indegree.put(c2, indegree.get(c2) + 1);
						}

					} else {
						Set<Character> set = new HashSet<>();
						set.add(c2);
						indegree.put(c2, 1);
						graph.put(c1, set);
					}

					break;
				}
			}
		}

		System.out.println(graph);
		System.out.println(indegree);

		Queue<Character> queue = new LinkedList<>();
		for (char ch : indegree.keySet()) {
			if (indegree.get(ch) == 0) {
				queue.add(ch);
			}
		}

		while (queue.size() > 0) {

			char ch = queue.remove();
			System.out.print(ch + " ");

			if (graph.get(ch) != null) {
				for (char ch2 : graph.get(ch)) {

					indegree.put(ch2, indegree.get(ch2) - 1);
					if (indegree.get(ch2) == 0) {
						queue.add(ch2);
					}
				}
			}
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
}
