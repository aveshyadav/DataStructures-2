package com.javacode.tree;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import javafx.scene.layout.Priority;

public class BinaryTree_3_22_38 {

	public static void main(String[] args) {

		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 70, null,
				null, 87, null, null };

//		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
//				null };

		Node root = constructTree(arr);
//		inOrderTraversal(root);
//		System.out.println();

//		leftViewOfBinaryTree(root);
//		rightViewOfBinaryTree(root);

//		int widthArr[] = new int[2];
//		widthArr[0] = Integer.MAX_VALUE;
//		widthArr[1] = Integer.MIN_VALUE;
//		widthOfBinaryTree(root, 0, widthArr);
//		int width = widthArr[1] - widthArr[0] + 1;
//		System.out.println("Width: " + width);

//		verticalOrderOfBinaryTree(root);
//		verticalOrderOfBinaryTree2(root);

//		bottomViewOfBinaryTree(root);
//		topViewOfBinaryTree(root);

//		diagonalOrderOfBinaryTree(root);
//		diagonalOrderAntiClockOfBinaryTree(root);

//		verticalOrderSumUsingLevelOrder(root);
//		verticalOrderSumUsingRecursion(root);

//		verticalOrderOfBinaryTreeUsingQueue(root);

//		diagonalOrderSumOfBinaryTreeUsingBFS(root);

//		List<Integer> list = new ArrayList<>();
//		diagonalOrderSumOfBinaryTreeUsingDFS(root, 0, list);
//		System.out.println(list);

//		Node list = new Node(0);
//		verticalOrderSumUsingShadowTechnique(root, list);
//
//		while (list != null && list.left != null) {
//			list = list.left;
//		}
//
//		while (list != null) {
//			System.out.println(list.data);
//			list = list.right;
//		}

		Node list = new Node(0);
		diagonalOrderSumUsingShadowTechnique(root, list);
		
		while (list != null && list.right != null) {
			list = list.right;
		}

		while (list != null) {
			System.out.println(list.data);
			list = list.left;
		}
	}

	private static void diagonalOrderSumUsingShadowTechnique(Node root, Node list) {

		if (root == null) {
			return;
		}

		list.data += root.data;
		Node left = list.left;
		if (left == null) {
			left = new Node(0);
			left.right = list;
			list.left = left;
		}

		diagonalOrderSumUsingShadowTechnique(root.left, left);
		diagonalOrderSumUsingShadowTechnique(root.right, list);
	}

	private static void verticalOrderSumUsingShadowTechnique(Node root, Node list) {

		if (root == null) {
			return;
		}

		list.data += root.data;

		Node left = list.left;
		if (left == null) {
			left = new Node(0);
			left.right = list;
			list.left = left;
		}

		Node right = list.right;
		if (right == null) {
			right = new Node(0);
			right.left = list;
			list.right = right;
		}

		verticalOrderSumUsingShadowTechnique(root.left, left);
		verticalOrderSumUsingShadowTechnique(root.right, right);

	}

	private static void diagonalOrderSumOfBinaryTreeUsingDFS(Node root, int hl, List<Integer> list) {

		if (root == null) {
			return;
		}

		if (list.size() == hl) {
			list.add(0);
		}
		int sum = list.get(hl) + root.data;
		list.set(hl, sum);

		diagonalOrderSumOfBinaryTreeUsingDFS(root.left, hl + 1, list);
		diagonalOrderSumOfBinaryTreeUsingDFS(root.right, hl, list);
	}

	private static void diagonalOrderSumOfBinaryTreeUsingBFS(Node root) {

		List<Integer> list = new ArrayList<>();

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (queue.size() > 0) {

			int sum = 0;
			int size = queue.size();
			while (size-- > 0) {
				Node top = queue.remove();

				while (top != null) {
					if (top.left != null) {
						queue.add(top.left);
					}

					sum += top.data;
					top = top.right;
				}
			}
			list.add(sum);
		}

		System.out.println(list);
	}

	private static void verticalOrderOfBinaryTreeUsingQueue(Node root) {

		int widthArr[] = new int[2];
		widthArr[0] = Integer.MAX_VALUE;
		widthArr[1] = Integer.MIN_VALUE;
		widthOfBinaryTree(root, 0, widthArr);
		int width = widthArr[1] - widthArr[0] + 1;

		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			list.add(new ArrayList<>());
		}

		PriorityQueue<VerticalPair> pq = new PriorityQueue<>();
		PriorityQueue<VerticalPair> cq = new PriorityQueue<>();
		pq.add(new VerticalPair(root, Math.abs(widthArr[0])));

		while (pq.size() > 0) {

			int size = pq.size();
			for (int i = 0; i < size; i++) {

				VerticalPair top = pq.remove();
				list.get(top.hl).add(top.node.data);

				if (top.node.left != null) {
					cq.add(new VerticalPair(top.node.left, top.hl - 1));
				}
				if (top.node.right != null) {
					cq.add(new VerticalPair(top.node.right, top.hl + 1));
				}
			}

			pq = cq;
			cq = new PriorityQueue<>();
		}

		for (List<Integer> ll : list) {
			System.out.println(ll);
		}
	}

	private static void verticalOrderSumUsingRecursion(Node root) {

		int widthArr[] = new int[2];
		widthArr[0] = Integer.MAX_VALUE;
		widthArr[1] = Integer.MIN_VALUE;
		widthOfBinaryTree(root, 0, widthArr);
		int width = widthArr[1] - widthArr[0] + 1;

		int res[] = new int[width];
		verticalOrderSumUsingRecursionHelper(root, Math.abs(widthArr[0]), res);

		for (int i : res) {
			System.out.println(i);
		}
	}

	private static void verticalOrderSumUsingRecursionHelper(Node root, int hl, int res[]) {

		if (root == null) {
			return;
		}

		res[hl] += root.data;

		verticalOrderSumUsingRecursionHelper(root.left, hl - 1, res);
		verticalOrderSumUsingRecursionHelper(root.right, hl + 1, res);
	}

	private static void verticalOrderSumUsingLevelOrder(Node root) {

		int widthArr[] = new int[2];
		widthArr[0] = Integer.MAX_VALUE;
		widthArr[1] = Integer.MIN_VALUE;
		widthOfBinaryTree(root, 0, widthArr);
		int width = widthArr[1] - widthArr[0] + 1;

		int res[] = new int[width];
		Queue<VerticalPair> queue = new LinkedList<>();
		queue.add(new VerticalPair(root, Math.abs(widthArr[0])));

		while (queue.size() > 0) {

			VerticalPair top = queue.remove();
			res[top.hl] += top.node.data;

			if (top.node.left != null) {
				queue.add(new VerticalPair(top.node.left, top.hl - 1));
			}
			if (top.node.right != null) {
				queue.add(new VerticalPair(top.node.right, top.hl + 1));
			}
		}

		for (int i : res) {
			System.out.println(i);
		}
	}

	private static void diagonalOrderAntiClockOfBinaryTree(Node root) {

		List<List<Integer>> list = new ArrayList<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (queue.size() > 0) {

			list.add(new ArrayList<>());
			int size = queue.size();

			while (size-- > 0) {

				Node top = queue.remove();
				while (top != null) {

					list.get(list.size() - 1).add(top.data);
					if (top.right != null) {
						queue.add(top.right);
					}
					top = top.left;
				}
			}
		}

		for (List<Integer> ll : list) {
			System.out.println(ll);
		}
	}

	private static void diagonalOrderOfBinaryTree(Node root) {

		List<List<Integer>> list = new ArrayList<>();
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (queue.size() > 0) {

			int size = queue.size();
			list.add(new ArrayList<>());
			while (size-- > 0) {

				Node top = queue.remove();

				while (top != null) {

					list.get(list.size() - 1).add(top.data);
					if (top.left != null) {
						queue.add(top.left);
					}
					top = top.right;
				}
			}
		}

		for (List<Integer> ll : list) {
			System.out.println(ll);
		}
	}

	private static void topViewOfBinaryTree(Node root) {

		int widthArr[] = new int[2];
		widthArr[0] = Integer.MAX_VALUE;
		widthArr[1] = Integer.MIN_VALUE;
		widthOfBinaryTree(root, 0, widthArr);
		int width = widthArr[1] - widthArr[0] + 1;

		Integer res[] = new Integer[width];
		Queue<VerticalPair> queue = new LinkedList<>();
		queue.add(new VerticalPair(root, Math.abs(widthArr[0])));

		while (queue.size() > 0) {

			VerticalPair top = queue.remove();
			if (res[top.hl] == null) {
				res[top.hl] = top.node.data;
			}

			if (top.node.left != null) {
				queue.add(new VerticalPair(top.node.left, top.hl - 1));
			}
			if (top.node.right != null) {
				queue.add(new VerticalPair(top.node.right, top.hl + 1));
			}
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void bottomViewOfBinaryTree(Node root) {

		int widthArr[] = new int[2];
		widthArr[0] = Integer.MAX_VALUE;
		widthArr[1] = Integer.MIN_VALUE;
		widthOfBinaryTree(root, 0, widthArr);
		int width = widthArr[1] - widthArr[0] + 1;

		int res[] = new int[width];
		Queue<VerticalPair> queue = new LinkedList<>();
		queue.add(new VerticalPair(root, Math.abs(widthArr[0])));

		while (queue.size() > 0) {

			VerticalPair top = queue.remove();
			res[top.hl] = top.node.data;

			if (top.node.left != null) {
				queue.add(new VerticalPair(top.node.left, top.hl - 1));
			}
			if (top.node.right != null) {
				queue.add(new VerticalPair(top.node.right, top.hl + 1));
			}
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void verticalOrderOfBinaryTree2(Node root) {

		int widthArr[] = new int[2];
		widthArr[0] = Integer.MAX_VALUE;
		widthArr[1] = Integer.MIN_VALUE;
		widthOfBinaryTree(root, 0, widthArr);
		int width = widthArr[1] - widthArr[0] + 1;

		List<List<Integer>> list = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			list.add(new ArrayList<>());
		}

		Queue<VerticalPair> queue = new LinkedList<>();
		queue.add(new VerticalPair(root, Math.abs(widthArr[0])));

		while (queue.size() > 0) {

			VerticalPair top = queue.remove();
			list.get(top.hl).add(top.node.data);

			if (top.node.left != null) {
				queue.add(new VerticalPair(top.node.left, top.hl - 1));
			}
			if (top.node.right != null) {
				queue.add(new VerticalPair(top.node.right, top.hl + 1));
			}
		}

		for (List<Integer> ll : list) {
			System.out.println(ll);
		}
	}

	private static void verticalOrderOfBinaryTree(Node root) {

		Queue<VerticalPair> queue = new LinkedList<>();
		queue.add(new VerticalPair(root, 0));
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();

		int min = 0;
		int max = 0;
		while (queue.size() > 0) {

			int size = queue.size();
			while (size-- > 0) {

				VerticalPair top = queue.remove();

				if (!map.containsKey(top.hl)) {
					map.put(top.hl, new ArrayList<>());
				}
				map.get(top.hl).add(top.node.data);

				if (top.node.left != null) {
					min = Math.min(min, top.hl - 1);
					queue.add(new VerticalPair(top.node.left, top.hl - 1));
				}

				if (top.node.right != null) {
					max = Math.max(max, top.hl + 1);
					queue.add(new VerticalPair(top.node.right, top.hl + 1));
				}
			}
		}

		for (int i = min; i <= max; i++) {
			System.out.println(map.get(i));
		}
	}

	private static class VerticalPair implements Comparable<VerticalPair> {

		Node node;
		int hl;

		public VerticalPair(Node node, int hl) {
			this.node = node;
			this.hl = hl;
		}

		@Override
		public int compareTo(VerticalPair vp) {
			return this.node.data - vp.node.data;
		}
	}

	private static void widthOfBinaryTree(Node root, int hl, int widthArr[]) {

		if (root == null) {
			return;
		}

		widthArr[0] = Math.min(widthArr[0], hl);
		widthArr[1] = Math.max(widthArr[1], hl);

		widthOfBinaryTree(root.left, hl - 1, widthArr);
		widthOfBinaryTree(root.right, hl + 1, widthArr);
	}

	private static void rightViewOfBinaryTree(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (queue.size() > 0) {

			int size = queue.size();
			while (size-- > 0) {

				Node top = queue.remove();
				if (size == 0) {
					System.out.print(top.data + " ");
				}

				if (top.left != null) {
					queue.add(top.left);
				}
				if (top.right != null) {
					queue.add(top.right);
				}
			}
		}

	}

	private static void leftViewOfBinaryTree(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);

		while (queue.size() > 0) {

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				Node node = queue.remove();

				if (i == 0) {
					System.out.print(node.data + " ");
				}
				if (node.left != null) {
					queue.add(node.left);
				}

				if (node.right != null) {
					queue.add(node.right);
				}
			}
			System.out.println();
		}

	}

	private static void inOrderTraversal(Node root) {
		if (root == null) {
			return;
		}

		inOrderTraversal(root.left);
		System.out.print(root.data + " ");
		inOrderTraversal(root.right);

	}

	private static int idx = 0;

	private static Node constructTreeRecursive(Integer arr[]) {

		if (arr[idx] == null || idx == arr.length) {
			idx++;
			return null;
		}

		Node node = new Node(arr[idx++]);
		node.left = constructTreeRecursive(arr);
		node.right = constructTreeRecursive(arr);

		return node;
	}

	private static Node constructTree(Integer[] arr) {

		Stack<Pair> stack = new Stack<>();
		Node root = new Node(arr[0]);
		stack.push(new Pair(root, 0));

		int idx = 1;
		while (!stack.isEmpty()) {

			Pair top = stack.peek();
			if (top.state == 0) {
				if (arr[idx] != null) {
					Node node = new Node(arr[idx]);
					top.node.left = node;
					stack.push(new Pair(node, 0));
				}
				top.state++;
				idx++;
			} else if (top.state == 1) {
				if (arr[idx] != null) {
					Node node = new Node(arr[idx]);
					top.node.right = node;
					stack.push(new Pair(node, 0));
				}
				top.state++;
				idx++;
			} else {
				stack.pop();
			}
		}

		return root;
	}

	private static class Pair {

		int state;
		Node node;

		public Pair(Node node, int state) {
			this.node = node;
			this.state = state;
		}
	}
}
