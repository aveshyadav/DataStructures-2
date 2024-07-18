package com.javacode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GenericTree {

	public static void main(String[] args) {

		int arr[] = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1,
				-1 };

//		int arr[] = { 10, 20, -1, 30, 50, -1, 60, -1, -1, 40, -1, -1 };
		Node root = constructTree(arr);
//		displayTree(root);
//		System.out.println(size(root));
//		System.out.println(maxNode(root));
//		System.out.println(height(root));

//		traversal(root);
//		levelOrderTraversal(root);
//		levelOrderTraversalLineWise(root);
//		levelOrderTraversalLineWiseZigZag(root);
//		levelOrderTraversal2(root);
//		mirror(root);
//		removeLeaves(root);
//		linearize(root);

//		System.out.println(findElement(root, 10));
//		List<Integer> path = nodeToRootPath(root, 110);
//		System.out.println(path);
//		lowestCommonAncestor(root, 10, 120);
//		distanceBetweenTwoNodes(root, 70, 110);

//		System.out.println(areTreeSimilar(root, root));
//		System.out.println(areTreeMirror(root, root));
//		System.out.println(isSymmetric(root));

//		traverseMultiSolver(root, 1);
//		System.out.println("size: " + size);
//		System.out.println("min: " + min);
//		System.out.println("max: " + max);
//		System.out.println("height: " + height);

//		findPredecessorSuccessor(root, 110);
//		System.out.println("Pre: " + pre.data);
//		System.out.println("Post: " + post.data);

//		findCeilnFloor(root, 65);
//		System.out.println("ceil: " + ceil);
//		System.out.println("floor: " + floor);
//		findKthLargestElement(root, 3);

//		maxSubtreeSum(root);
//		System.out.println("MaxNode: " + maxNode.data + " maxSum: " + maxSum);

//		diamterOfTree(root);
//		System.out.println("diameter: " + diameter);

		iterativePrePostOrderTravesral(root);
	}

	private static void iterativePrePostOrderTravesral(Node root) {

		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(root, -1));

		StringBuilder pre = new StringBuilder();
		StringBuilder post = new StringBuilder();

		while (!stack.isEmpty()) {

			Pair top = stack.peek();
			if (top.state == -1) {
				pre.append(top.node.data + " ");
				top.state++;
			} else if (top.state == top.node.childs.size()) {
				post.append(top.node.data + " ");
				stack.pop();
			} else {
				Pair cp = new Pair(top.node.childs.get(top.state), -1);
				stack.push(cp);
				top.state++;
			}
		}

		System.out.println(pre);
		System.out.println(post);
	}

	private static class Pair {

		Node node;
		int state;

		public Pair(Node node, int state) {
			this.node = node;
			this.state = state;
		}
	}

	private static int diameter = 0;

	private static int diamterOfTree(Node root) {

		int dch = -1;
		int sdch = -1;

		for (Node node : root.childs) {
			int ch = diamterOfTree(node);
			if (ch > dch) {
				sdch = dch;
				dch = ch;
			} else if (ch > sdch) {
				sdch = ch;
			}
		}
		int dia = dch + sdch + 2;
		if (diameter < dia) {
			diameter = dia;
		}
		return dch + 1;
	}

	private static Node maxNode = null;
	private static int maxSum = Integer.MIN_VALUE;

	private static int maxSubtreeSum(Node root) {

		int sum = 0;
		for (Node node : root.childs) {
			sum += maxSubtreeSum(node);
		}
		sum += root.data;
		if (sum > maxSum) {
			maxSum = sum;
			maxNode = root;
		}
		return sum;
	}

	private static void findKthLargestElement(Node root, int k) {

		floor = Integer.MIN_VALUE;
		int max = Integer.MAX_VALUE;
		for (int i = 0; i < k; i++) {

			findCeilnFloor(root, max);
			max = floor;
			System.out.println(max);
			floor = Integer.MIN_VALUE;
		}
		System.out.println(k + "th highest number is: " + max);
	}

	private static int ceil = Integer.MAX_VALUE;
	private static int floor = Integer.MIN_VALUE;

	private static void findCeilnFloor(Node root, int i) {

		if (i > root.data) {
			floor = Math.max(floor, root.data);
		} else if (i < root.data) {
			ceil = Math.min(ceil, root.data);
		}

		for (Node node : root.childs) {
			findCeilnFloor(node, i);
		}
	}

	private static Node pre = null;
	private static Node post = null;
	private static int state = 0;

	private static void findPredecessorSuccessor(Node root, int i) {

		if (root.data == i) {
			state++;
		} else if (state == 0) {
			pre = root;
		} else if (state == 1 && post == null) {
			post = root;
		}

		for (Node node : root.childs) {
			findPredecessorSuccessor(node, i);
		}
	}

	private static int size = 0;
	private static int min = Integer.MAX_VALUE;
	private static int max = Integer.MIN_VALUE;
	private static int height = 0;

	private static void traverseMultiSolver(Node root, int i) {

		size++;
		min = Math.min(min, root.data);
		max = Math.max(max, root.data);
		height = Math.max(height, i);
		for (Node node : root.childs) {
			traverseMultiSolver(node, i + 1);
		}
	}

	private static boolean isSymmetric(Node root) {

		return areTreeMirror(root, root);
	}

	private static boolean areTreeMirror(Node root, Node root2) {

		if (root.childs.size() != root2.childs.size()) {
			return false;
		}

		for (int i = 0, j = root2.childs.size() - 1; i < root.childs.size() && j >= 0; i++, j--) {
			boolean flag = areTreeMirror(root.childs.get(i), root2.childs.get(j));
			if (flag == false) {
				return false;
			}
		}

		return true;
	}

	private static boolean areTreeSimilar(Node root, Node root2) {

		if (root.childs.size() != root2.childs.size()) {
			return false;
		}

		for (int i = 0; i < root.childs.size(); i++) {
			boolean flag = areTreeSimilar(root.childs.get(i), root2.childs.get(i));
			if (flag == false) {
				return false;
			}
		}

		return true;
	}

	private static void distanceBetweenTwoNodes(Node root, int n1, int n2) {

		List<Integer> p1 = nodeToRootPath(root, n1);
		List<Integer> p2 = nodeToRootPath(root, n2);

		int i = p1.size() - 1;
		int j = p2.size() - 1;
		int lca = root.data;

		while (i >= 0 && j >= 0) {
			if (p1.get(i) == p2.get(j)) {
				lca = p1.get(i);
			} else {
				break;
			}

			i--;
			j--;
		}
		int distance = i + j + 2;
		System.out.println(distance);
	}

	private static void lowestCommonAncestor(Node root, int d1, int d2) {

		List<Integer> p1 = nodeToRootPath(root, d1);
		List<Integer> p2 = nodeToRootPath(root, d2);

		int i = p1.size() - 1;
		int j = p2.size() - 1;
		int lca = root.data;

		while (i >= 0 && j >= 0) {
			if (p1.get(i) == p2.get(j)) {
				lca = p1.get(i);
			} else {
				break;
			}
			i--;
			j--;
		}

		System.out.println("LCA: " + lca);
	}

	private static List<Integer> nodeToRootPath(Node root, int n) {

		if (root.data == n) {
			List<Integer> list = new ArrayList<>();
			list.add(n);
			return list;
		}

		for (Node nn : root.childs) {
			List<Integer> ans = nodeToRootPath(nn, n);
			if (ans != null) {
				ans.add(root.data);
				return ans;
			}
		}

		return null;
	}

	private static boolean findElement(Node root, int n) {

		if (root.data == n) {
			return true;
		}

		for (Node node : root.childs) {
			boolean flag = findElement(node, n);
			if (flag == true) {
				return true;
			}
		}

		return false;
	}

	private static void linearize(Node root) {

		for (Node node : root.childs) {
			linearize(node);
		}

		while (root.childs.size() > 1) {
			Node last = root.childs.remove(root.childs.size() - 1);
			Node tail = getTail(root.childs.get(root.childs.size() - 1));
			tail.childs.add(last);
		}
	}

	private static Node getTail(Node node) {

		while (node.childs.size() == 1) {
			node = node.childs.get(0);
		}
		return node;
	}

	private static void removeLeaves(Node root) {

		for (int i = root.childs.size() - 1; i >= 0; i--) {
			if (root.childs.get(i).childs.size() == 0) {
				root.childs.remove(i);
			} else {
				removeLeaves(root.childs.get(i));
			}
		}
	}

	private static void mirror(Node root) {

		for (Node node : root.childs) {
			mirror(node);
		}
		Collections.reverse(root.childs);
	}

	private static void levelOrderTraversal2(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node node = queue.remove();
				System.out.print(node.data + " ");

				for (Node nn : node.childs) {
					queue.add(nn);
				}
			}
			System.out.println();
		}
	}

	private static void levelOrderTraversalLineWiseZigZag(Node root) {

		Stack<Node> ms = new Stack<>();
		Stack<Node> cs = new Stack<>();
		ms.push(root);

		int level = 1;
		while (!ms.isEmpty()) {

			Node node = ms.pop();
			System.out.print(node.data + " ");

			if (level % 2 == 0) {
				for (int i = node.childs.size() - 1; i >= 0; i++) {
					cs.push(node.childs.get(i));
				}
			} else {
				for (Node nn : node.childs) {
					cs.push(nn);
				}
			}

			ms = cs;
			cs = new Stack<>();
			level++;
			System.out.println();
		}
	}

	private static void levelOrderTraversalLineWise(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		queue.add(null);

		while (!queue.isEmpty()) {

			Node node = queue.poll();
			if (node == null) {
				if (queue.size() > 0) {
					System.out.println();
					queue.add(null);
				}
			} else {
				System.out.print(node.data + " ");
				for (Node nn : node.childs) {
					queue.add(nn);
				}
			}
		}

	}

	private static void levelOrderTraversal(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {

			Node node = queue.poll();
			System.out.print(node.data + " ");

			for (Node n : node.childs) {
				queue.add(n);
			}
		}

	}

	private static void traversal(Node root) {

		System.out.println("Node Pre: " + root.data);
		for (Node node : root.childs) {
			System.out.println("Edge Pre: " + root.data + "--" + node.data);
			traversal(node);
			System.out.println("Edge Post: " + root.data + "--" + node.data);
		}
		System.out.println("Node Post: " + root.data);
	}

	private static int height(Node root) {

		int height = 0;
		for (Node node : root.childs) {
			height = Math.max(height, height(node));
		}
		return height + 1;
	}

	private static int maxNode(Node root) {

		int max = root.data;
		for (Node node : root.childs) {
			max = Math.max(max, maxNode(node));
		}
		return max;
	}

	private static int size(Node root) {

		int size = 1;
		for (Node node : root.childs) {
			size = size + size(node);
		}
		return size;
	}

	private static void displayTree(Node root) {

		System.out.print(root.data + "->");
		for (Node node : root.childs) {
			System.out.print(node.data + ",");
		}
		System.out.println(".");
		for (Node node : root.childs) {
			displayTree(node);
		}
	}

	private static Node constructTree(int[] arr) {

		Node root = null;
		Stack<Node> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] != -1) {
				Node node = new Node(arr[i]);
				if (stack.isEmpty()) {
					root = node;
				} else {
					stack.peek().childs.add(node);
				}
				stack.push(node);
			} else {
				stack.pop();
			}
		}

		return root;
	}

	private static class Node {

		int data;
		List<Node> childs = new ArrayList<>();

		public Node(int data) {
			this.data = data;
		}
	}

}
