package com.javacode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph_1_1_10 {

	public static void main(String[] args) {

//		List<List<Edge>> graph = buildGraph();
//		boolean visited[] = new boolean[graph.size()];
//		boolean hasPath = hasPath(graph, 0, 6, visited);
//		System.out.println("Has Path: " + hasPath);
//		printAllPaths(graph, 0, 6, visited, "");

//		smallestPath(graph, 0, 6, visited, "", 0, 3);
//		System.out.println("Smallest path: " + smallestPath);
//		System.out.println("Largest path: " + largestPath);
//		System.out.println("biggerThan40Path: " + biggerThan40Path);
//		System.out.println("smallerThan40Path: " + smallerThan40Path);
//		System.out.println("nLargestPath: " + pq.peek().path);

//		List<List<Integer>> ans = new ArrayList<>();
//		for (int i = 0; i < graph.size(); i++) {
//
//			int src = graph.get(i).get(0).src;
//			if (visited[src] == false) {
//				List<Integer> list = new ArrayList<>();
//				getConnectedComponents(graph, src, visited, list);
//				ans.add(list);
//			}
//		}
//		System.out.println(ans);

//		System.out.println("Is Graph Connected: " + isGraphConnected(graph));

//		int count = 0;
//		int arr[][] = { { 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 1 }, { 1, 0, 0, 1, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 1, 0, 1 } };
//		boolean vis[][] = new boolean[arr.length][arr.length];
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[0].length; j++) {
//				if (vis[i][j] == false && arr[i][j] == 1) {
//					countNumberOfIsland(arr, i, j, vis);
//					count++;
//				}
//			}
//		}
//		System.out.println("No. Of Island: " + count);

//		List<List<Edge>> graph = buildGraph2();
//		printPerfectFriends(graph);

//		List<List<Edge>> graph = buildHamiltonianGraph();
//		boolean visited[] = new boolean[graph.size()];
//		printHamiltonianPath(graph, 0, 0, visited, "0", 1);

//		int n = 5;
//		int chess[][] = new int[n][n];
//		knightsTour(chess, 0, 0, 1);
//		System.out.println("Total path: " + totalCount);

//		breadthFirstSearch(graph, visited);

//		for (int i = 0; i < graph.size(); i++) {
//			int src = graph.get(i).get(0).src;
//
//			if (visited[src] == false) {
//				boolean isCycle = isGraphCyclic(graph, src, visited);
//				if (isCycle) {
//					System.out.println("Cycle found: " + true);
//				}
//			}
//		}

//		int vis[] = new int[graph.size()];
//		Arrays.fill(vis, -1);
//
//		boolean isBipartite = true;
//		for (int i = 0; i < graph.size(); i++) {
//
//			int src = graph.get(i).get(0).src;
//			if (vis[i] == -1) {
//				boolean flag = isBipartite(graph, src, vis);
//				System.out.println("Flag: " + flag);
//				if (flag == true) {
//					isBipartite = false;
//				}
//			}
//		}
//		System.out.println("Graph is Bipartite: " + isBipartite);

//		int count = countInfectedPeopleInKTime(graph, 5, 3, visited);
//		System.out.println("Infected person: " + count);

//		List<List<Edge>> graph = buildDijkstraGraph();
//		boolean visited[] = new boolean[graph.size()];
//
//		findShortestPath(graph, 0, 6, visited);

//		List<List<Edge>> graph = buildDijkstraGraph();
//		boolean visited[] = new boolean[graph.size()];
//
//		minimumSpanningTree(graph, 0, visited);

//		List<List<Edge>> graph = buildTopologicalGraph();
//		boolean visited[] = new boolean[graph.size()];
//
//		Stack<Integer> stack = new Stack<>();
//		for (int i = 0; i < graph.size(); i++) {
//			if (visited[i] == false) {
//				topologicalSort(graph, graph.get(i).get(0).src, visited, stack);
//			}
//		}
//
//		System.out.println(stack);
//		while(stack.size() > 0) {
//			System.out.println(stack.pop());
//		}

		List<List<Edge>> graph = buildGraph();
		boolean visited[] = new boolean[graph.size()];

		iterativeDFS(graph, 0, visited);
	}

	private static void iterativeDFS(List<List<Edge>> graph, int src, boolean[] visited) {

		Stack<Pair2> stack = new Stack<>();
		stack.push(new Pair2(src, src + ""));

		while (stack.size() > 0) {

			Pair2 mp = stack.pop();
			if (visited[mp.v] == false) {
				visited[mp.v] = true;

				System.out.println(mp.v + "@" + mp.path);

				for (Edge e : graph.get(mp.v)) {
					if (visited[e.nbr] == false) {
						stack.push(new Pair2(e.nbr, mp.path + e.nbr));
					}
				}
			}
		}
	}

	private static void topologicalSort(List<List<Edge>> graph, int src, boolean[] visited, Stack<Integer> stack) {

		visited[src] = true;
		for (Edge e : graph.get(src)) {
			if (visited[e.nbr] == false) {
				topologicalSort(graph, e.nbr, visited, stack);
			}
		}
		stack.push(src);
	}

	private static void minimumSpanningTree(List<List<Edge>> graph, int src, boolean[] visited) {

		PriorityQueue<Pair4> pq = new PriorityQueue<>();
		pq.add(new Pair4(src, src + "", 0));

		while (pq.size() > 0) {

			Pair4 mp = pq.remove();

			if (visited[mp.v] == false) {
				visited[mp.v] = true;

				System.out.println(mp.v + "-" + mp.path + "@" + mp.wt);

				for (Edge e : graph.get(mp.v)) {
					if (visited[e.nbr] == false) {
						pq.add(new Pair4(e.nbr, mp.v + "", e.wt));
					}
				}
			}
		}
	}

	private static void findShortestPath(List<List<Edge>> graph, int src, int dest, boolean[] visited) {

		PriorityQueue<Pair4> pq = new PriorityQueue<>();
		pq.add(new Pair4(src, src + "", 0));

		String shortestPath = "";
		int min = Integer.MAX_VALUE;
		while (pq.size() > 0) {

			Pair4 mp = pq.remove();

			if (visited[mp.v] == false) {
				visited[mp.v] = true;

				System.out.println(mp.v + "@" + mp.path + "@" + mp.wt);
				if (mp.v == dest) {
					System.out.println("Shortest Path: " + mp.v + "@" + mp.path + "@" + mp.wt);
				}

				for (Edge e : graph.get(mp.v)) {
					if (visited[e.nbr] == false) {
						pq.add(new Pair4(e.nbr, mp.path + e.nbr, mp.wt + e.wt));
					}
				}
			}
		}
	}

	private static class Pair4 implements Comparable<Pair4> {

		int v;
		String path;
		int wt;

		public Pair4(int v, String path, int wt) {
			this.v = v;
			this.path = path;
			this.wt = wt;
		}

		@Override
		public int compareTo(Pair4 o) {
			return this.wt - o.wt;
		}
	}

	private static int countInfectedPeopleInKTime(List<List<Edge>> graph, int src, int k, boolean vis[]) {

		Queue<Pair3> queue = new LinkedList<>();
		queue.add(new Pair3(src, "", 0));

		int count = 0;
		while (queue.size() > 0) {

			Pair3 mp = queue.remove();
			if (mp.lvl >= k) {
				break;
			}

			if (vis[mp.v] == false) {
				vis[mp.v] = true;
				count++;

				for (Edge e : graph.get(mp.v)) {
					if (vis[e.nbr] == false) {
						queue.add(new Pair3(e.nbr, "", mp.lvl + 1));
					}
				}
			}
		}

		return count;
	}

	private static boolean isBipartite(List<List<Edge>> graph, int src, int vis[]) {

		Queue<Pair3> queue = new LinkedList<>();
		queue.add(new Pair3(src, src + "", 0));

		while (queue.size() > 0) {

			Pair3 top = queue.remove();
			if (vis[top.v] == -1) {

				vis[top.v] = top.lvl;
				System.out.println(top.v + "@" + top.path + "@" + top.lvl + "-" + vis[top.v]);

				for (Edge e : graph.get(top.v)) {
					if (vis[e.nbr] == -1) {
						queue.add(new Pair3(e.nbr, top.path + e.nbr, top.lvl + 1));
					}
				}
			} else {
				System.out.println("Cycle found: " + top.v + "@" + top.path + "@" + top.lvl + "-" + vis[top.v]);
				return top.lvl == vis[top.v];
			}
		}

		return true;
	}

	private static class Pair3 {

		int v;
		String path;
		int lvl;

		public Pair3(int v, String path, int lvl) {
			this.v = v;
			this.path = path;
			this.lvl = lvl;
		}
	}

	private static boolean isGraphCyclic(List<List<Edge>> graph, int src, boolean visited[]) {

		Queue<Pair2> queue = new LinkedList<>();
		queue.add(new Pair2(src, src + ""));

		while (queue.size() > 0) {

			Pair2 mp = queue.remove();
			if (visited[mp.v] == false) {
				visited[mp.v] = true;

				System.out.println(mp.v + "@" + mp.path);
				for (Edge e : graph.get(mp.v)) {
					if (visited[e.nbr] == false) {
						queue.add(new Pair2(e.nbr, mp.path + e.nbr));
					}
				}
			} else {
				System.out.println("Cycle: " + mp.v + "@" + mp.path);
				return true;
			}
		}
		return false;
	}

	private static void breadthFirstSearch(List<List<Edge>> graph, boolean[] visited) {

		int src = 2;
		Queue<Pair2> queue = new LinkedList<>();
		Pair2 pair = new Pair2(src, src + "");
		queue.add(pair);

		while (queue.size() > 0) {

			Pair2 mp = queue.remove();
			if (visited[mp.v] == false) {
				visited[mp.v] = true;

				System.out.println(mp.v + "@" + mp.path);

				for (Edge e : graph.get(mp.v)) {
					if (visited[e.nbr] == false) {
						queue.add(new Pair2(e.nbr, mp.path + e.nbr));
					}
				}
			}
		}

	}

	private static class Pair2 {

		String path;
		int v;

		public Pair2(int v, String path) {
			this.v = v;
			this.path = path;
		}
	}

	private static int totalCount = 0;

	private static void knightsTour(int chess[][], int i, int j, int count) {

		if (i < 0 || i >= chess.length || j < 0 || j >= chess[0].length || chess[i][j] != 0) {
			return;
		}

		if (count == chess.length * chess.length) {
			chess[i][j] = count + 1;
			display(chess);
			chess[i][j] = 0;
			totalCount++;
			return;
		}

		chess[i][j] = count;
		knightsTour(chess, i - 2, j + 1, count + 1);
		knightsTour(chess, i - 1, j + 2, count + 1);
		knightsTour(chess, i + 1, j + 2, count + 1);
		knightsTour(chess, i + 2, j + 1, count + 1);

		knightsTour(chess, i + 2, j - 1, count + 1);
		knightsTour(chess, i + 1, j - 2, count + 1);
		knightsTour(chess, i - 1, j - 2, count + 1);
		knightsTour(chess, i - 2, j - 1, count + 1);

		chess[i][j] = 0;
	}

	public static void display(int chess[][]) {

		for (int i = 0; i < chess.length; i++) {
			for (int j = 0; j < chess.length; j++) {
				System.out.print(chess[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("-------------");
	}

	private static void printHamiltonianPath(List<List<Edge>> graph, int src, int osrc, boolean[] visited, String ans,
			int count) {

		if (count == graph.size()) {

			boolean isCycle = false;
			for (Edge e : graph.get(src)) {
				if (e.nbr == osrc) {
					isCycle = true;
				}
			}
			if (isCycle) {
				System.out.println(ans + "*");
			} else {
				System.out.println(ans + ".");
			}
			return;
		}

		visited[src] = true;
		for (Edge e : graph.get(src)) {
			if (visited[e.nbr] == false) {
				printHamiltonianPath(graph, e.nbr, osrc, visited, ans + e.nbr, count + 1);
			}
		}
		visited[src] = false;
	}

	private static void printPerfectFriends(List<List<Edge>> graph) {

		List<List<Integer>> ans = new ArrayList<>();
		boolean vis[] = new boolean[graph.size()];

		for (int i = 0; i < graph.size(); i++) {
			int src = graph.get(i).get(0).src;
			if (vis[src] == false) {
				List<Integer> list = new ArrayList<>();
				getConnectedComponents(graph, src, vis, list);
				ans.add(list);
			}
		}
		System.out.println(ans);

		int ways = 0;
		for (int i = 0; i < ans.size(); i++) {
			for (int j = i + 1; j < ans.size(); j++) {
				List<Integer> l1 = ans.get(i);
				List<Integer> l2 = ans.get(j);

				ways = ways + (l1.size() * l2.size());
			}
		}
		System.out.println("Total Friends: " + ways);
	}

	private static void countNumberOfIsland(int arr[][], int i, int j, boolean vis[][]) {

		if (i == arr.length || i < 0 || j == arr[0].length || j < 0 || arr[i][j] == 0 || vis[i][j] == true) {
			return;
		}

		vis[i][j] = true;
		countNumberOfIsland(arr, i + 1, j, vis);
		countNumberOfIsland(arr, i, j + 1, vis);
		countNumberOfIsland(arr, i - 1, j, vis);
		countNumberOfIsland(arr, i, j - 1, vis);
	}

	private static boolean isGraphConnected(List<List<Edge>> graph) {

		boolean visited[] = new boolean[graph.size()];
		List<List<Integer>> ans = new ArrayList<>();
		for (int i = 0; i < graph.size(); i++) {

			int src = graph.get(i).get(0).src;
			if (visited[src] == false) {
				List<Integer> list = new ArrayList<>();
				getConnectedComponents(graph, src, visited, list);
				ans.add(list);
			}
		}
		System.out.println(ans);
		return ans.size() == 1;
	}

	private static void getConnectedComponents(List<List<Edge>> graph, int src, boolean[] visited, List<Integer> list) {

		visited[src] = true;
		list.add(src);
		for (Edge e : graph.get(src)) {
			if (visited[e.nbr] == false) {
				getConnectedComponents(graph, e.nbr, visited, list);
			}
		}
	}

	private static String smallestPath = "";
	private static int smallestPathNumber = Integer.MAX_VALUE;

	private static String largestPath = "";
	private static int largestPathNumber = Integer.MIN_VALUE;

	private static String biggerThan40Path = "";
	private static int biggerThan40PathNumber = 0;

	private static String smallerThan40Path = "";
	private static int smallerThan40PathNumber = 0;

	private static PriorityQueue<Pair> pq = new PriorityQueue<>();

	private static void smallestPath(List<List<Edge>> graph, int src, int dest, boolean[] visited, String path, int sum,
			int n) {

		if (src == dest) {
			path = path + src;

			if (sum < smallestPathNumber) {
				smallestPath = path + "@" + sum;
				smallestPathNumber = sum;
			}

			if (sum > largestPathNumber) {
				largestPath = path + "@" + sum;
				largestPathNumber = sum;
			}

			if (sum > 40) {
				if (biggerThan40PathNumber == 0 || sum < biggerThan40PathNumber) {
					biggerThan40Path = path + "@" + sum;
					biggerThan40PathNumber = sum;
				}
			}

			if (sum < 40) {
				if (smallerThan40PathNumber < 40 || sum > smallerThan40PathNumber) {
					smallerThan40Path = path + "@" + sum;
					smallerThan40PathNumber = sum;
				}
			}

			if (pq.size() < n) {
				pq.add(new Pair(sum, path + "@" + sum));
			} else {
				if (pq.peek().sum < sum) {
					pq.remove();
					pq.add(new Pair(sum, path + "@" + sum));
				}
			}

			return;
		}

		visited[src] = true;
		for (Edge e : graph.get(src)) {
			if (visited[e.nbr] == false) {
				smallestPath(graph, e.nbr, dest, visited, path + src, sum + e.wt, n);
			}
		}
		visited[src] = false;
	}

	private static class Pair implements Comparable<Pair> {

		int sum;
		String path;

		public Pair(int sum, String path) {
			this.sum = sum;
			this.path = path;
		}

		@Override
		public int compareTo(Pair o) {
			return this.sum - o.sum;
		}
	}

	private static void printAllPaths(List<List<Edge>> graph, int src, int dest, boolean[] visited, String ans) {

		if (src == dest) {
			ans += dest;
			System.out.println(ans);
			return;
		}

		visited[src] = true;
		for (Edge e : graph.get(src)) {
			if (visited[e.nbr] == false) {
				printAllPaths(graph, e.nbr, dest, visited, ans + e.src + " ");
			}
		}
		visited[src] = false;
	}

	private static boolean hasPath(List<List<Edge>> graph, int src, int dest, boolean[] visited) {

		if (src == dest) {
			return true;
		}

		visited[src] = true;
		for (Edge edge : graph.get(src)) {

			if (visited[edge.nbr] == false) {
				boolean flag = hasPath(graph, edge.nbr, dest, visited);
				if (flag == true) {
					return true;
				}
			}
		}

		return false;
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

	public static List<List<Edge>> buildHamiltonianGraph() {

		List<List<Edge>> list = new ArrayList<List<Edge>>();

		List<Edge> e1 = new ArrayList<Edge>();
		e1.add(new Edge(0, 1, 10));
		e1.add(new Edge(0, 3, 40));
		list.add(e1);

		List<Edge> e2 = new ArrayList<Edge>();
		e2.add(new Edge(1, 0, 10));
		e2.add(new Edge(1, 2, 10));
		list.add(e2);

		List<Edge> e3 = new ArrayList<Edge>();
		e3.add(new Edge(2, 1, 10));
		e3.add(new Edge(2, 3, 10));
		e3.add(new Edge(2, 5, 10));
		list.add(e3);

		List<Edge> e4 = new ArrayList<Edge>();
		e4.add(new Edge(3, 0, 40));
		e4.add(new Edge(3, 2, 10));
		e4.add(new Edge(3, 4, 2));
		list.add(e4);

		List<Edge> e5 = new ArrayList<Edge>();
		e5.add(new Edge(4, 3, 2));
		e5.add(new Edge(4, 5, 3));
		e5.add(new Edge(4, 6, 8));
		list.add(e5);

		List<Edge> e6 = new ArrayList<Edge>();
		e6.add(new Edge(5, 4, 3));
		e6.add(new Edge(5, 6, 3));
		e6.add(new Edge(5, 2, 3));
		list.add(e6);

		List<Edge> e7 = new ArrayList<Edge>();
		e7.add(new Edge(6, 4, 8));
		e7.add(new Edge(6, 5, 3));
		list.add(e7);

		return list;
	}

	public static List<List<Edge>> buildGraph2() {

		List<List<Edge>> list = new ArrayList<List<Edge>>();

		List<Edge> e1 = new ArrayList<Edge>();
		e1.add(new Edge(0, 1, 10));
		list.add(e1);

		List<Edge> e2 = new ArrayList<Edge>();
		e2.add(new Edge(1, 0, 10));
		list.add(e2);

		List<Edge> e3 = new ArrayList<Edge>();
		e3.add(new Edge(2, 3, 10));
		list.add(e3);

		List<Edge> e4 = new ArrayList<Edge>();
		e4.add(new Edge(3, 2, 10));
		list.add(e4);

		List<Edge> e5 = new ArrayList<Edge>();
		e5.add(new Edge(4, 5, 3));
		e5.add(new Edge(4, 6, 8));
		list.add(e5);

		List<Edge> e6 = new ArrayList<Edge>();
		e6.add(new Edge(5, 4, 3));
		e6.add(new Edge(5, 6, 3));
		list.add(e6);

		List<Edge> e7 = new ArrayList<Edge>();
		e7.add(new Edge(6, 4, 8));
		e7.add(new Edge(6, 5, 3));
		list.add(e7);

		return list;
	}

	public static List<List<Edge>> buildGraph() {

		List<List<Edge>> list = new ArrayList<List<Edge>>();

		List<Edge> e1 = new ArrayList<Edge>();
		e1.add(new Edge(0, 1, 10));
		e1.add(new Edge(0, 3, 40));
		list.add(e1);

		List<Edge> e2 = new ArrayList<Edge>();
		e2.add(new Edge(1, 0, 10));
		e2.add(new Edge(1, 2, 10));
		list.add(e2);

		List<Edge> e3 = new ArrayList<Edge>();
		e3.add(new Edge(2, 1, 10));
		e3.add(new Edge(2, 3, 10));
		list.add(e3);

		List<Edge> e4 = new ArrayList<Edge>();
		e4.add(new Edge(3, 0, 40));
		e4.add(new Edge(3, 2, 10));
		e4.add(new Edge(3, 4, 2));
		list.add(e4);

		List<Edge> e5 = new ArrayList<Edge>();
		e5.add(new Edge(4, 3, 2));
		e5.add(new Edge(4, 5, 3));
		e5.add(new Edge(4, 6, 8));
		list.add(e5);

		List<Edge> e6 = new ArrayList<Edge>();
		e6.add(new Edge(5, 4, 3));
		e6.add(new Edge(5, 6, 3));
		list.add(e6);

		List<Edge> e7 = new ArrayList<Edge>();
		e7.add(new Edge(6, 4, 8));
		e7.add(new Edge(6, 5, 3));
		list.add(e7);

		return list;
	}

	public static List<List<Edge>> buildDijkstraGraph() {

		List<List<Edge>> list = new ArrayList<List<Edge>>();

		List<Edge> e1 = new ArrayList<Edge>();
		e1.add(new Edge(0, 1, 10));
		e1.add(new Edge(0, 3, 25));
		list.add(e1);

		List<Edge> e2 = new ArrayList<Edge>();
		e2.add(new Edge(1, 0, 10));
		e2.add(new Edge(1, 2, 10));
		list.add(e2);

		List<Edge> e3 = new ArrayList<Edge>();
		e3.add(new Edge(2, 1, 10));
		e3.add(new Edge(2, 3, 10));
		list.add(e3);

		List<Edge> e4 = new ArrayList<Edge>();
		e4.add(new Edge(3, 0, 25));
		e4.add(new Edge(3, 2, 10));
		e4.add(new Edge(3, 4, 2));
		list.add(e4);

		List<Edge> e5 = new ArrayList<Edge>();
		e5.add(new Edge(4, 3, 2));
		e5.add(new Edge(4, 5, 3));
		e5.add(new Edge(4, 6, 8));
		list.add(e5);

		List<Edge> e6 = new ArrayList<Edge>();
		e6.add(new Edge(5, 4, 3));
		e6.add(new Edge(5, 6, 3));
		list.add(e6);

		List<Edge> e7 = new ArrayList<Edge>();
		e7.add(new Edge(6, 4, 8));
		e7.add(new Edge(6, 5, 3));
		list.add(e7);

		return list;
	}

	public static List<List<Edge>> buildPrimsGraph() {

		List<List<Edge>> list = new ArrayList<List<Edge>>();

		List<Edge> e1 = new ArrayList<Edge>();
		e1.add(new Edge(0, 1, 10));
		e1.add(new Edge(0, 3, 25));
		list.add(e1);

		List<Edge> e2 = new ArrayList<Edge>();
		e2.add(new Edge(1, 0, 10));
		e2.add(new Edge(1, 2, 10));
		list.add(e2);

		List<Edge> e3 = new ArrayList<Edge>();
		e3.add(new Edge(2, 1, 10));
		e3.add(new Edge(2, 3, 10));
		list.add(e3);

		List<Edge> e4 = new ArrayList<Edge>();
		e4.add(new Edge(3, 0, 25));
		e4.add(new Edge(3, 2, 10));
		e4.add(new Edge(3, 4, 2));
		list.add(e4);

		List<Edge> e5 = new ArrayList<Edge>();
		e5.add(new Edge(4, 3, 2));
		e5.add(new Edge(4, 5, 3));
		e5.add(new Edge(4, 6, 8));
		list.add(e5);

		List<Edge> e6 = new ArrayList<Edge>();
		e6.add(new Edge(5, 4, 3));
		e6.add(new Edge(5, 6, 3));
		list.add(e6);

		List<Edge> e7 = new ArrayList<Edge>();
		e7.add(new Edge(6, 4, 8));
		e7.add(new Edge(6, 5, 3));
		list.add(e7);

		return list;
	}

	public static List<List<Edge>> buildTopologicalGraph() {

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
