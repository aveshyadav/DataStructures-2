package com.javacode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree_6_61_72 {

	public static void main(String[] args) {

		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 70, null,
				null, 87, null, null };

//		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
//				null };

		Node root = constructTree(arr);
		inOrderTraversal(root);
		System.out.println();

//		List<Node> ans = uniqueBST2(1, 3);
//		for (Node n : ans) {
//			inOrderTraversal(n);
//			System.out.println();
//		}

//		System.out.println(isFullBinaryTree(root));

//		List<Node> ans = fullBinaryTree(5);
//		for (Node n : ans) {
//			inOrderTraversal(n);
//			System.out.println();
//		}

//		levelOrderTraversal(root);
//		Node nroot = addOneRowToBinaryTree(root, 4);
//		levelOrderTraversal(nroot);

//		List<Integer> list = new ArrayList<>();
//		pathInZigZagTree(12, list);
//		System.out.println(list);

//		completeBinaryTreeInserter(root);

//		int arr1[] = { 25, 37 };
//		deleteNodeAndReturnForest(root, arr1);

//		int count = countGoodNodesHelper(root, Integer.MIN_VALUE);
//		System.out.println("Good node: " + count);

//		isEvenOddTree(root);
		
//		Pair2 mp = longestUnivaluePath(root);
//		System.out.println("Max path: " + mp.max);

//		btreeGameWinningMove(root, 75, 11);
	}

	private static void isEvenOddTree(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		int lvl = 0;
		boolean isEvenOdd = true;
		while (queue.size() > 0) {

			int prev = lvl % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			int size = queue.size();
			for (int i = 0; i < size; i++) {

				Node top = queue.remove();
				System.out.print(top.data + " ");

				if (lvl % 2 == 0) {
					if (top.data % 2 == 0 && prev > top.data) {
						isEvenOdd = false;
					}
				}

				if (lvl % 2 == 1) {
					if (top.data % 2 == 1 && prev < top.data) {
						isEvenOdd = false;
					}
				}

				prev = top.data;
				if (top.left != null) {
					queue.add(top.left);
				}

				if (top.right != null) {
					queue.add(top.right);
				}
			}
			lvl++;
			System.out.println();
		}

		System.out.println("Is Even Odd tree: " + isEvenOdd);
	}

	private static int countGoodNodesHelper(Node root, int max) {

		if (root == null) {
			return 0;
		}

		int ls = countGoodNodesHelper(root.left, Math.max(max, root.data));
		int rs = countGoodNodesHelper(root.right, Math.max(max, root.data));

		int mc = ls + rs;
		if (root.data > max) {
			mc += 1;
		}

		return mc;
	}

	private static void completeBinaryTreeInserter(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		Queue<Node> ans = new LinkedList<>();

		while (queue.size() > 0) {

			int size = queue.size();
			while (size-- > 0) {

				Node top = queue.remove();

				if (top.left == null || top.right == null) {
					ans.add(top);
				}

				if (top.left != null) {
					queue.add(top.left);
				}

				if (top.right != null) {
					queue.add(top.right);
				}
			}
		}

		while (ans.size() > 0) {
			System.out.print(ans.remove().data + " ");
		}

	}

	private static void pathInZigZagTree(int data, List<Integer> list) {

	}

	private static Node addOneRowToBinaryTree(Node root, int lvl) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (queue.size() > 0 && lvl > 2) {

			int size = queue.size();
			while (size-- > 0) {

				Node top = queue.remove();
				if (top.left != null) {
					queue.add(top.left);
				}
				if (top.right != null) {
					queue.add(top.right);
				}
			}
			lvl--;
		}

		while (queue.size() > 0) {

			Node top = queue.poll();
			if (top.left != null) {

				Node node = new Node(0);
				node.left = top.left;
				top.left = node;
			}

			if (top.right != null) {

				Node node = new Node(0);
				node.right = top.right;
				top.right = node;
			}
		}

		return root;
	}

	private static void levelOrderTraversal(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (queue.size() > 0) {

			int size = queue.size();
			while (size-- > 0) {

				Node top = queue.remove();
				System.out.print(top.data + " ");

				if (top.left != null) {
					queue.add(top.left);
				}
				if (top.right != null) {
					queue.add(top.right);
				}
			}
			System.out.println();
		}
	}

	private static List<Node> fullBinaryTree(int n) {

		if (n == 1) {
			List<Node> list = new ArrayList<>();
			list.add(new Node(0));
			return list;
		}

		List<Node> ans = new ArrayList<>();
		for (int i = 1; i < n; i += 2) {

			List<Node> ls = fullBinaryTree(i);
			List<Node> rs = fullBinaryTree(n - i - 1);

			for (Node ll : ls) {
				for (Node rr : rs) {

					Node nn = new Node(0);
					nn.left = ll;
					nn.right = rr;

					ans.add(nn);
				}
			}
		}

		return ans;
	}

	private static boolean isFullBinaryTree(Node root) {

		if (root == null) {
			return true;
		}

		boolean left = isFullBinaryTree(root.left);
		boolean right = isFullBinaryTree(root.right);

		int count = 0;
		if (root.left != null) {
			count++;
		}
		if (root.right != null) {
			count++;
		}

		if (left == false || right == false || count == 1) {
			return false;
		}

		return true;
	}

	private static List<Node> uniqueBST2(int start, int end) {

		if (start > end) {
			List<Node> list = new ArrayList<>();
			list.add(null);
			return list;
		}

		List<Node> ans = new ArrayList<>();
		for (int i = start; i <= end; i++) {

			List<Node> ls = uniqueBST2(start, i - 1);
			List<Node> rs = uniqueBST2(i + 1, end);

			for (Node ll : ls) {
				for (Node rr : rs) {
					Node node = new Node(i);
					node.left = ll;
					node.right = rr;

					ans.add(node);
				}
			}
		}

		return ans;
	}

	public static void inOrderTraversal(Node root) {

		if (root == null) {
			return;
		}

		inOrderTraversal(root.left);
		System.out.print(root.data + " ");
		inOrderTraversal(root.right);
	}

	public static Node constructTree(Integer arr[]) {

		Stack<Pair> stack = new Stack<Pair>();
		Node root = new Node(arr[0]);
		stack.push(new Pair(root, 1));

		int idx = 0;
		while (stack.size() > 0) {

			Pair top = stack.peek();
			if (top.state == 1) {
				idx++;
				if (arr[idx] != null) {
					top.node.left = new Node(arr[idx]);
					stack.push(new Pair(top.node.left, 1));
				} else {
					top.node.left = null;
				}
				top.state++;
			} else if (top.state == 2) {
				idx++;
				if (arr[idx] != null) {
					top.node.right = new Node(arr[idx]);
					stack.push(new Pair(top.node.right, 1));
				} else {
					top.node.right = null;
				}
				top.state++;
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

}
