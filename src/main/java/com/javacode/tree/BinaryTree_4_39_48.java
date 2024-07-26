package com.javacode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree_4_39_48 {

	public static void main(String[] args) {

		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 70, null,
				null, 87, null, null };

//		Integer arr[] = { 50, 25, 12, null, null, 37, -25, null, null, -25, null, null, 75, 62, -100, null, null, 70, null,
//		null, 87, null, null };

//		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
//				null };

		Node root = constructTree(arr);
		inOrderTraversal(root);
		System.out.println();

//		BSTIteratorMorris itr = new BSTIteratorMorris(root);
//		while (itr.hasNext()) {
//			System.out.print(itr.next()+" ");
//		}

//		BSTIterator itr = new BSTIterator(root);
//		while (itr.hasNext()) {
//			System.out.print(itr.next() + " ");
//		}

//		List<Integer> ans = nodeToRootPath(root, 62);
//		System.out.println(ans);

//		List<Integer> list = new ArrayList<>();
//		nodeToRootPath2(root, 62, list);
//		System.out.println(list);.

//		List<Integer> list = new ArrayList<>();
//		singleChildNode(root, list);
//		System.out.println(list);

//		int count = countSingleChildNode(root);
//		System.out.println(count);

//		printKLevelFarElements(root, 60, 3);
//
//		List<Integer> ans = new ArrayList<>();
//		printKLevelFarElements2(root, 60, 3, ans);
//		System.out.println(ans);

//		burningTree(root, 25);
//		burningTree2(root, 25);
//		System.out.println("Burning Tree: " + count);

//		ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
//		burningTreeInterval(root, 37, ans);
//		for (int i = 0; i < ans.size(); i++) {
//			System.out.println(i + ": " + ans.get(i));
//		}

//		Node root = new Node(1);
//		root.left = new Node(2);
//		root.right = new Node(3);
//		root.left.left = new Node(4);
//		root.left.right = new Node(5);
//		root.right.right = new Node(8);
//		root.right.right.left = new Node(6);
//		root.right.right.right = new Node(7);
//
//		maxWidthOfBinaryTree(root);

//		convertBinaryTreeToDoublyLinkedListDFS(root);
//
//		while (curr != null) {
//			System.out.print(curr.data + " ");
//			curr = curr.right;
//		}

//		convertBinaryTreeToDoublyLinkedListBFS(root);
		convertBinaryTreeToDoublyLinkedListMorris(root);

		Node node = convertDoublyLinkedListToBST(curr);
		inOrderTraversal(node);
	}

	private static Node convertDoublyLinkedListToBST(Node curr) {

		if (curr == null) {
			return null;
		}

		Node node = getMidNode(curr);

		Node prev = node.left;
		Node next = node.right;

		if (node != null) {
			node.left = null;
			node.right = null;
		}

		if (prev != null) {
			prev.right = null;
		}
		if (next != null) {
			next.left = null;
		}

		node.left = convertDoublyLinkedListToBST(curr);
		node.right = convertDoublyLinkedListToBST(next);

		return node;
	}

	private static Node getMidNode(Node root) {

		if (root == null || root.right == null) {
			return root;
		}

		Node slow = root;
		Node fast = root;

		while (fast.right != null && fast.right.right != null) {
			slow = slow.right;
			fast = fast.right.right;
		}

		return slow;
	}

	private static void convertBinaryTreeToDoublyLinkedListMorris(Node root) {

		Node curr = root;

		Node prev = null;

		while (curr != null) {

			Node left = curr.left;
			if (left == null) {
				System.out.print(curr.data + " ");
				if (prev != null) {
					prev.right = curr;
				}
				curr.left = prev;
				prev = curr;

				curr = curr.right;
			} else {
				Node rightMost = getRightMostNode(left, curr);
				if (rightMost.right == null) {

					rightMost.right = curr;
					curr = curr.left;
				} else {

					rightMost.right = null;
					System.out.print(curr.data + " ");

					if (prev != null) {
						prev.right = curr;
					}
					curr.left = prev;
					prev = curr;
					curr = curr.right;
				}
			}
		}

		while (root.left != null) {
			root = root.left;
		}

		System.out.println();
		while (root != null) {
			System.out.print(root.data + " ");
			root = root.right;
		}
	}

	private static Node getRightMostNode(Node left, Node curr) {

		while (left.right != null && left.right != curr) {
			left = left.right;
		}
		return left;
	}

	private static void convertBinaryTreeToDoublyLinkedListBFS(Node root) {

		Node node = root;
		Stack<Node> stack = new Stack<>();
		while (node != null) {
			stack.push(node);
			node = node.left;
		}

		Node curr = null;
		Node prev = null;
		while (!stack.isEmpty()) {

			Node top = stack.pop();
			System.out.print(top.data + " ");

			if (prev == null) {
				curr = top;
			} else {
				prev.right = top;
			}

			top.left = prev;
			prev = top;

			top = top.right;
			while (top != null) {
				stack.push(top);
				top = top.left;
			}
		}

		System.out.println();
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.right;
		}
	}

	private static Node prev = null;
	private static Node curr = null;

	private static void convertBinaryTreeToDoublyLinkedListDFS(Node root) {

		if (root == null) {
			return;
		}

		convertBinaryTreeToDoublyLinkedListDFS(root.left);

		if (prev == null) {
			curr = root;
		} else {
			prev.right = root;
		}

		root.left = prev;
		prev = root;

		convertBinaryTreeToDoublyLinkedListDFS(root.right);
	}

	private static void maxWidthOfBinaryTree(Node root) {

		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		int maxWidth = 0;

		while (queue.size() > 0) {

			int size = queue.size();
			int right = 0;
			int count = 0;

			for (int i = 0; i < size; i++) {

				Node top = queue.remove();

				if (top == null) {
					System.out.print("null ");
				} else {
					right = i;
					System.out.print(top.data + " ");
				}

				if (top != null) {
					if (top.left != null) {
						queue.add(top.left);
						count++;
					} else {
						if (count > 0) {
							queue.add(top.left);
							count++;
						}
					}

					if (top.right != null) {
						queue.add(top.right);
						count++;
					} else {
						if (count > 0) {
							queue.add(top.right);
							count++;
						}
					}
				}
			}

			System.out.println();
			maxWidth = Math.max(maxWidth, right + 1);
		}

		System.out.println("Max width: " + maxWidth);
	}

	private static int burningTreeInterval(Node root, int data, ArrayList<ArrayList<Integer>> ans) {

		if (root == null) {
			return -1;
		}

		if (root.data == data) {
			burningTime(root, 0, null, ans);
			return 1;
		}

		int lh = burningTreeInterval(root.left, data, ans);
		if (lh != -1) {
			burningTime(root, lh, root.left, ans);
			return lh + 1;
		}

		int rh = burningTreeInterval(root.right, data, ans);
		if (rh != -1) {
			burningTime(root, rh, root.right, ans);
			return rh + 1;
		}

		return -1;
	}

	private static void burningTime(Node root, int lvl, Node block, ArrayList<ArrayList<Integer>> ans) {

		if (root == null || root == block) {
			return;
		}

		if (ans.size() == lvl) {
			ans.add(new ArrayList<>());
		}

		ans.get(lvl).add(root.data);

		burningTime(root.left, lvl + 1, block, ans);
		burningTime(root.right, lvl + 1, block, ans);
	}

	private static int count = 0;

	private static int burningTree2(Node root, int data) {

		if (root == null) {
			return -1;
		}

		if (root.data == data) {
			int h = height(root, null);
			count = Math.max(count, h);
			return 1;
		}

		int lh = burningTree2(root.left, data);
		if (lh != -1) {
			int h = height(root, root.left);
			count = Math.max(count, h);
			return 1;
		}

		int rh = burningTree2(root.right, data);
		if (rh != -1) {
			int h = height(root, root.right);
			count = Math.max(count, h);
			return 1;
		}

		return -1;
	}

	private static void burningTree(Node root, int data) {

		List<Node> path = nodeToRootPath3(root, data);
		int max = 0;
		for (int i = 0; i < path.size(); i++) {
			int h = height(path.get(i), i == 0 ? null : path.get(i - 1));
			h = Math.max(max, h);
			max = Math.max(max, h);
		}

		System.out.println("Burning Tree: " + max);
	}

	private static int height(Node root, Node block) {

		if (root == null || root == block) {
			return 0;
		}

		int lh = height(root.left, block);
		int rh = height(root.right, block);

		return Math.max(lh, rh) + 1;
	}

	private static int printKLevelFarElements2(Node root, int data, int k, List<Integer> ans) {

		if (root == null) {
			return -1;
		}

		if (root.data == data) {
			printKlevelDown(root, k, null, ans);
			return 1;
		}

		int lh = printKLevelFarElements2(root.left, data, k, ans);
		if (lh != -1) {
			printKlevelDown(root, k - lh, root.left, ans);
			return lh + 1;
		}

		int rh = printKLevelFarElements2(root.right, data, k, ans);
		if (rh != -1) {
			printKlevelDown(root, k - rh, root.right, ans);
			return rh + 1;
		}

		return -1;
	}

	private static void printKLevelFarElements(Node root, int data, int k) {

		List<Integer> list = new ArrayList<>();
		List<Node> path = nodeToRootPath3(root, data);
		for (int i = 0; i < path.size(); i++) {
			printKlevelDown(path.get(i), k - i, i > 0 ? path.get(i - 1) : null, list);
		}
		System.out.println(list);
	}

	private static void printKlevelDown(Node root, int lvl, Node block, List<Integer> ans) {

		if (root == null || lvl < 0 || root == block) {
			return;
		}

		if (lvl == 0) {
			ans.add(root.data);
		}

		printKlevelDown(root.left, lvl - 1, block, ans);
		printKlevelDown(root.right, lvl - 1, block, ans);
	}

	private static List<Node> nodeToRootPath3(Node root, int data) {

		if (root == null) {
			return null;
		}
		if (root.data == data) {
			List<Node> list = new ArrayList<>();
			list.add(root);
			return list;
		}

		List<Node> left = nodeToRootPath3(root.left, data);
		if (left != null) {
			left.add(root);
			return left;
		}

		List<Node> right = nodeToRootPath3(root.right, data);
		if (right != null) {
			right.add(root);
			return right;
		}

		return null;
	}

	private static int countSingleChildNode(Node root) {

		if (root == null) {
			return 0;
		}

		int flag = 0;
		int count = 0;
		if (root.left != null) {
			count++;
		}
		if (root.right != null) {
			count++;
		}
		if (count == 1) {
			flag = 1;
		}

		int ls = countSingleChildNode(root.left);
		int rs = countSingleChildNode(root.right);

		return ls + rs + flag;
	}

	private static void singleChildNode(Node root, List<Integer> list) {

		if (root == null) {
			return;
		}

		int count = root.left != null ? 1 : 0;
		count = root.right != null ? count + 1 : count;
		if (count == 1) {
			list.add(root.data);
		}

		singleChildNode(root.left, list);
		singleChildNode(root.right, list);
	}

	private static int nodeToRootPath2(Node root, int data, List<Integer> list) {

		if (root == null) {
			return -1;
		}

		if (root.data == data) {
			list.add(data);
			return 0;
		}

		int ls = nodeToRootPath2(root.left, data, list);
		if (ls == 0) {
			list.add(root.data);
			return 0;
		}

		int rs = nodeToRootPath2(root.right, data, list);
		if (rs == 0) {
			list.add(root.data);
			return 0;
		}

		return -1;
	}

	private static List<Integer> nodeToRootPath(Node root, int data) {

		if (root == null) {
			return null;
		}
		if (root.data == data) {
			List<Integer> list = new ArrayList<>();
			list.add(data);
			return list;
		}

		List<Integer> left = nodeToRootPath(root.left, data);
		if (left != null) {
			left.add(root.data);
			return left;
		}

		List<Integer> right = nodeToRootPath(root.right, data);
		if (right != null) {
			right.add(root.data);
			return right;
		}

		return null;
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

	private static Node constructTree(Integer[] arr) {

		if (idx == arr.length || arr[idx] == null) {
			idx++;
			return null;
		}

		Node node = new Node(arr[idx++]);
		node.left = constructTree(arr);
		node.right = constructTree(arr);

		return node;
	}

}
