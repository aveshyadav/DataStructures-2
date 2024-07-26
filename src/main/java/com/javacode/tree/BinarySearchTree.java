package com.javacode.tree;

public class BinarySearchTree {

	public static void main(String[] args) {

//		int arr[] = { 12, 25, 37, 50, 62, 75, 87 };
		int arr[] = { 12, 25, 30, 37, 40, 50, 60, 62, 70, 75, 87 };
		Node root = constructTree(arr, 0, arr.length - 1);
		inOrderTraversal(root);
		System.out.println();

//		System.out.println("size: " + size(root));
//		System.out.println("sum: " + sum(root));
//		System.out.println("max: " + max(root));
//		System.out.println("min: " + min(root));
//		System.out.println("find: " + find(root, 25));

//		addNode(root, 30);
//		inOrderTraversal(root);

//		removeNode(root, 50);
//		inOrderTraversal(root);

//		replaceSumOfLargerBST(root);
//		inOrderTraversal(root);

//		int lca = findLowestCommonAncestor(root, 12, 30);
//		System.out.println("LCA: " + lca);

//		printInRange(root, 27, 76);

//		printTargetSum(root, root, 100);
	}

	private static void printTargetSum(Node root, Node node, int target) {

		if (node == null) {
			return;
		}

		printTargetSum(root, node.left, target);

		int comp = target - node.data;
		if (comp > node.data) {
			boolean flag = find(root, comp);
			if (flag) {
				System.out.println(node.data + " " + comp);
			}
		}
		printTargetSum(root, node.right, target);

	}

	private static void printInRange(Node root, int lo, int hi) {

		if (root == null) {
			return;
		}

		if (root.data > lo) {
			printInRange(root.left, lo, hi);
		}
		if (root.data >= lo && root.data <= hi) {
			System.out.print(root.data + " ");
		}
		if (root.data < hi) {
			printInRange(root.right, lo, hi);
		}

	}

	private static int findLowestCommonAncestor(Node root, int d1, int d2) {

		if (root.data > d1 && root.data > d2) {
			return findLowestCommonAncestor(root.left, d1, d2);
		} else if (root.data < d1 && root.data < d2) {
			return findLowestCommonAncestor(root.right, d1, d2);
		} else {
			return root.data;
		}
	}

	private static int sum = 0;

	private static void replaceSumOfLargerBST(Node root) {

		if (root == null) {
			return;
		}

		replaceSumOfLargerBST(root.right);
		sum += root.data;
		root.data = sum - root.data;
		replaceSumOfLargerBST(root.left);

	}

	private static Node removeNode(Node root, int data) {

		if (root == null) {
			return null;
		}

		if (root.data > data) {
			root.left = removeNode(root.left, data);
		} else if (root.data < data) {
			root.right = removeNode(root.right, data);
		} else {

			if (root.left != null && root.right != null) {
				int max = max(root.left);
				root.data = max;
				removeNode(root.left, max);
			} else if (root.left != null) {
				return root.left;
			} else if (root.right != null) {
				return root.right;
			} else {
				return null;
			}
		}
		return root;
	}

	private static Node addNode(Node root, int data) {

		if (root == null) {
			return new Node(data);
		}
		if (data > root.data) {
			root.right = addNode(root.right, data);
		} else {
			root.left = addNode(root.left, data);
		}
		return root;
	}

	private static boolean find(Node root, int data) {

		if (root == null) {
			return false;
		}
		if (root.data == data) {
			return true;
		}

		if (data < root.data) {
			boolean flag = find(root.left, data);
			if (flag == true) {
				return true;
			}
		}
		if (data > root.data) {
			boolean flag = find(root.right, data);
			if (flag == true) {
				return true;
			}
		}

		return false;
	}

	private static int min(Node root) {
		if (root.left == null) {
			return root.data;
		}
		return min(root.left);
	}

	private static int max(Node root) {

		if (root.right == null) {
			return root.data;
		}
		return max(root.right);
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
		return size(root.left) + size(root.right) + 1;
	}

	private static void inOrderTraversal(Node root) {

		if (root == null) {
			return;
		}
		inOrderTraversal(root.left);
		System.out.print(root.data + " ");
		inOrderTraversal(root.right);
	}

	private static Node constructTree(int[] arr, int lo, int hi) {

		if (lo > hi) {
			return null;
		}

		int mid = (lo + hi) / 2;
		Node node = new Node(arr[mid]);
		Node left = constructTree(arr, lo, mid - 1);
		Node right = constructTree(arr, mid + 1, hi);

		node.left = left;
		node.right = right;
		return node;
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
