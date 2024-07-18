package com.javacode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {

	public static void main(String[] args) {

//		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 70, null,
//				null, 87, null, null };
		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
				null };

		Node root = constructTree(arr);
//		display(root);
//		System.out.println();

//		System.out.println("size: " + size(root));
//		System.out.println("sum: " + sum(root));
//		System.out.println("max: " + max(root));
//		System.out.println("height: " + height(root));

//		preOrderTraversals(root);
//		System.out.println();
//		postOrderTraversals(root);
//		System.out.println();
//		inOrderTraversals(root);

//		printPathToLeafNodes(root, "");
//		levelOrderTraversals(root);
//		printBinaryTreeIterative(root);

//		printNodeToRootPath(root, 70, "");
//		List<Integer> paths = nodeToRootPath(root, 70);
//		System.out.println(paths);

//		printKLevelElementsIterative(root, 2);
//		printKLevelElements(root, 2, 0);
//		printKLevelFarElements(root, 62, 1);
//		printPathToLeafNodeInRange(root, "", 0, 150, 250);

//		levelOrderTraversals(root);
//		transformToLeftClonedTree(root);
//		levelOrderTraversals(root);
//		transformBackFromLeftClonedTree(root);
//		levelOrderTraversals(root);
//		printSingleChildNode(root, null);

//		removeLeaves(root);
//		levelOrderTraversals(root);

//		Node nn = new Node(80);
//		root.right.left.right.left = nn;
//
//		diameterTravelAndChange(root);
//		System.out.println("Diameter: " + dia);
//
//		DiaPair dia = diameter(root);
//		System.out.println("Diameter: " + dia.diam);

//		tiltOfBinaryTree(root);
//		System.out.println("Tilt: " + tilt);

//		BSTPair bp = isBST(root);
//		System.out.println("IsBST: " + bp.isBST);
//
//		isBalanced(root);
//		System.out.println("IsBalanced: " + isBalanced);
//
//		BSTPair2 pair = largestBST(root);
//		System.out.println("Largest BST: " + pair.size);
	}

	private static BSTPair2 largestBST(Node root) {

		if (root == null) {
			BSTPair2 bp = new BSTPair2();
			bp.isBST = true;
			bp.height = 0;
			bp.min = Integer.MAX_VALUE;
			bp.max = Integer.MIN_VALUE;
			return bp;
		}

		BSTPair2 lp = largestBST(root.left);
		BSTPair2 rp = largestBST(root.right);

		BSTPair2 mp = new BSTPair2();
		mp.height = lp.height + rp.height + 1;
		mp.min = Math.min(root.data, Math.min(lp.min, rp.min));
		mp.max = Math.max(root.data, Math.max(lp.max, rp.max));
		mp.isBST = lp.isBST && rp.isBST && root.data > lp.max && root.data < rp.min;

		if (mp.isBST) {
			mp.node = root.data;
			mp.size = mp.height;
		}

		return mp;
	}

	private static class BSTPair2 {

		boolean isBST;
		int min;
		int max;
		int height;

		int size;
		int node;
	}

	private static boolean isBalanced = true;

	private static int isBalanced(Node root) {

		if (root == null) {
			return -1;
		}

		int lh = isBalanced(root.left);
		int rh = isBalanced(root.right);

		int delta = Math.abs(lh - rh);
		if (delta > 1) {
			isBalanced = false;
		}
		return lh + rh + 1;
	}

	private static BSTPair isBST(Node root) {

		if (root == null) {
			BSTPair bp = new BSTPair();
			bp.min = Integer.MAX_VALUE;
			bp.max = Integer.MIN_VALUE;
			bp.isBST = true;
			return bp;
		}

		BSTPair lp = isBST(root.left);
		BSTPair rp = isBST(root.right);

		BSTPair mp = new BSTPair();
		mp.max = Math.max(Math.max(lp.max, rp.max), root.data);
		mp.min = Math.min(Math.min(lp.min, rp.min), root.data);
		mp.isBST = lp.isBST && rp.isBST && root.data > lp.max && root.data < rp.min;

		return mp;
	}

	private static class BSTPair {
		boolean isBST;
		int max;
		int min;
	}

	private static int tilt = 0;

	private static int tiltOfBinaryTree(Node root) {

		if (root == null) {
			return 0;
		}

		int ls = tiltOfBinaryTree(root.left);
		int rs = tiltOfBinaryTree(root.right);

		tilt += Math.abs(ls - rs);
		;
		return ls + rs + root.data;
	}

	private static DiaPair diameter(Node root) {

		if (root == null) {
			return new DiaPair(-1, 0);
		}

		DiaPair lp = diameter(root.left);
		DiaPair rp = diameter(root.right);

		DiaPair mp = new DiaPair();
		mp.height = Math.max(lp.height, rp.height) + 1;
		mp.diam = Math.max(Math.max(lp.diam, rp.diam), lp.height + rp.height + 2);

		return mp;
	}

	private static class DiaPair {

		int height;
		int diam;

		public DiaPair() {

		}

		public DiaPair(int height, int diam) {
			this.height = height;
			this.diam = diam;
		}
	}

	private static int dia = 0;

	private static int diameterTravelAndChange(Node root) {

		if (root == null) {
			return -1;
		}

		int lh = diameterTravelAndChange(root.left);
		int rh = diameterTravelAndChange(root.right);

		int md = lh + rh + 2;
		dia = Math.max(md, dia);

		return Math.max(lh, rh) + 1;
	}

	private static Node removeLeaves(Node root) {

		if (root == null) {
			return null;
		}
		if (root.left == null && root.right == null) {
			return null;
		}

		root.left = removeLeaves(root.left);
		root.right = removeLeaves(root.right);

		return root;
	}

	private static void printSingleChildNode(Node root, Node parent) {

		if (root == null) {
			return;
		}

		int count = 0;
		if (parent != null && parent.left != null) {
			count++;
		}
		if (parent != null && parent.right != null) {
			count++;
		}
		if (count == 1) {
			System.out.println(root.data);
		}

		printSingleChildNode(root.left, root);
		printSingleChildNode(root.right, root);
	}

	private static void transformBackFromLeftClonedTree(Node root) {

		if (root == null) {
			return;
		}

		root.left = root.left.left;
		transformBackFromLeftClonedTree(root.left);
		transformBackFromLeftClonedTree(root.right);
	}

	private static void transformToLeftClonedTree(Node root) {

		if (root == null) {
			return;
		}

		Node node = new Node(root.data);
		node.left = root.left;
		root.left = node;

		transformToLeftClonedTree(root.left.left);
		transformToLeftClonedTree(root.right);
	}

	private static void printPathToLeafNodeInRange(Node root, String ans, int sum, int lo, int hi) {

		if (root.left == null && root.right == null) {
			ans = ans + root.data;
			sum += root.data;
			if (sum > lo && sum < hi) {
				System.out.println(ans);
			}
			return;
		}

		printPathToLeafNodeInRange(root.left, ans + root.data + " ", sum + root.data, lo, hi);
		printPathToLeafNodeInRange(root.right, ans + root.data + " ", sum + root.data, lo, hi);
	}

	private static void printKLevelFarElements(Node root, int n, int k) {

		List<Node> path = getNodeToRootPath(root, n);
		for (int i = 0; i < path.size(); i++) {
			printKLevelElements2(path.get(i), k - i, i == 0 ? -1 : path.get(i - 1).data);
		}
	}

	private static void printKLevelElements2(Node root, int k, int block) {

		if (root == null || k < 0 || block == root.data) {
			return;
		}
		if (k == 0) {
			System.out.print(root.data + " ");
			return;
		}

		printKLevelElements2(root.left, k - 1, block);
		printKLevelElements2(root.right, k - 1, block);
	}

	private static List<Node> getNodeToRootPath(Node root, int node) {
		if (root == null) {
			return null;
		}
		if (root.data == node) {
			List<Node> list = new ArrayList<>();
			list.add(root);
			return list;
		}

		List<Node> lp = getNodeToRootPath(root.left, node);
		if (lp != null) {
			lp.add(root);
			return lp;
		}
		List<Node> rp = getNodeToRootPath(root.right, node);
		if (rp != null) {
			rp.add(root);
			return rp;
		}
		return null;
	}

	private static void printKLevelElements(Node root, int k, int n) {

		if (root == null) {
			return;
		}
		if (k == n) {
			System.out.print(root.data + " ");
		}

		printKLevelElements(root.left, k, n + 1);
		printKLevelElements(root.right, k, n + 1);
	}

	private static void printKLevelElementsIterative(Node root, int k) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		int level = 0;
		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int i = 0; i < size; i++) {

				Node node = queue.remove();

				if (level == k) {
					System.out.print(node.data + " ");
				}
				if (node.left != null) {
					queue.add(node.left);
				}

				if (node.right != null) {
					queue.add(node.right);
				}
			}

			level++;
		}
	}

	private static List<Integer> nodeToRootPath(Node root, int n) {

		if (root == null) {
			return null;
		}
		if (root.data == n) {
			List<Integer> list = new ArrayList<>();
			list.add(n);
			return list;
		}

		List<Integer> lp = nodeToRootPath(root.left, n);
		if (lp != null) {
			lp.add(root.data);
			return lp;
		}

		List<Integer> rp = nodeToRootPath(root.right, n);
		if (rp != null) {
			rp.add(root.data);
			return rp;
		}

		return null;
	}

	private static void printNodeToRootPath(Node root, int n, String ans) {

		if (root == null) {
			return;
		}
		if (root.data == n) {
			ans = ans + root.data;
			System.out.println(ans);
			return;
		}

		printNodeToRootPath(root.left, n, ans + root.data + " ");
		printNodeToRootPath(root.right, n, ans + root.data + " ");
	}

	private static void printBinaryTreeIterative(Node root) {

		StringBuilder pre = new StringBuilder();
		StringBuilder post = new StringBuilder();
		StringBuilder in = new StringBuilder();

		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(root, 0));

		while (!stack.isEmpty()) {

			Pair top = stack.peek();
			if (top.state == 0) {
				pre.append(top.node.data).append(" ");
				if (top.node.left != null) {
					stack.push(new Pair(top.node.left, 0));
				}
				top.state++;
			} else if (top.state == 1) {
				in.append(top.node.data).append(" ");

				if (top.node.right != null) {
					stack.push(new Pair(top.node.right, 0));
				}
				top.state++;
			} else {
				post.append(top.node.data).append(" ");
				stack.pop();
			}
		}

		System.out.println("Pre: " + pre);
		System.out.println("In: " + in);
		System.out.println("Post: " + post);
	}

	private static void levelOrderTraversals(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (!queue.isEmpty()) {

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node node = queue.poll();

				System.out.print(node.data + " ");
				if (node.left != null)
					queue.add(node.left);

				if (node.right != null)
					queue.add(node.right);
			}
			System.out.println();
		}
	}

	private static void printPathToLeafNodes(Node root, String ans) {

		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			System.out.println(ans + root.data);
			return;
		}

		printPathToLeafNodes(root.left, ans + root.data + " ");
		printPathToLeafNodes(root.right, ans + root.data + " ");
	}

	private static void inOrderTraversals(Node root) {
		if (root == null) {
			return;
		}

		inOrderTraversals(root.left);
		System.out.print(root.data + " ");
		inOrderTraversals(root.right);
	}

	private static void postOrderTraversals(Node root) {
		if (root == null) {
			return;
		}

		postOrderTraversals(root.left);
		postOrderTraversals(root.right);
		System.out.print(root.data + " ");
	}

	private static void preOrderTraversals(Node root) {
		if (root == null) {
			return;
		}

		System.out.print(root.data + " ");
		preOrderTraversals(root.left);
		preOrderTraversals(root.right);
	}

	private static int height(Node root) {
		if (root == null) {
			return 0;
		}

		int lh = height(root.left);
		int rh = height(root.right);

		int mh = Math.max(lh, rh);
		return mh + 1;
	}

	private static int max(Node root) {
		if (root == null) {
			return Integer.MIN_VALUE;
		}

		int lmax = max(root.left);
		int rmax = max(root.right);
		int max = Math.max(lmax, rmax);

		return Math.max(max, root.data);
	}

	private static int sum(Node root) {

		if (root == null) {
			return 0;
		}
		return sum(root.left) + sum(root.right) + root.data;
	}

	private static int size(Node root) {

		if (root == null) {
			return 0;
		}

		int ls = size(root.left);
		int rs = size(root.right);

		return ls + rs + 1;
	}

	private static void display(Node root) {

		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		display(root.left);
		display(root.right);
	}

	private static Node constructTree(Integer[] arr) {

		Stack<Pair> stack = new Stack<>();
		stack.push(new Pair(new Node(arr[0]), 0));
		Node root = stack.peek().node;

		int i = 1;
		while (!stack.isEmpty()) {

			Pair top = stack.peek();
			if (top.state == 0) {
				if (arr[i] == null) {
					top.node.left = null;
				} else {
					Node node = new Node(arr[i]);
					top.node.left = node;
					stack.push(new Pair(node, 0));
				}
				top.state++;
				i++;
			} else if (top.state == 1) {
				if (arr[i] == null) {
					top.node.right = null;
				} else {
					Node node = new Node(arr[i]);
					top.node.right = node;
					stack.push(new Pair(node, 0));
				}
				top.state++;
				i++;
			} else {
				stack.pop();
			}
		}

		return root;
	}

	private static class Pair {

		Node node;
		int state;

		public Pair(Node node, int state) {
			this.node = node;
			this.state = state;
		}
	}

	private static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
		}
	}

}
