package com.javacode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTree_1_1_9 {

	public static void main(String[] args) {

		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 70, null,
				null, 87, null, null };

//		Integer arr[] = { 50, 25, 12, null, null, 37, 62, null, null, 40, null, null, 75, 30, 60, null, null, 70, null,
//				null, 87, null, null };

//		Integer arr[] = { 50, 25, 12, null, null, 70, 30, null, null, 40, null, null, 75, 62, 60, null, null, 37, null,
//				null, 87, null, null };

		Node root = constructTree(arr);
//		inOrderTraversal(root);
//		System.out.println();
//		morrisInOrderTraversal(root);

//		preOrderTraversal(root);
//		System.out.println();
//		morrisPreOrderTraversal(root);

//		camerasInBinaryTree(root);
//		System.out.println("Number of Camera required: " + cameraCount);

//		HouseRobber rob = maxRobbery(root);
//		System.out.println("With: " + rob.withRobbery + " Without: " + rob.withoutRobbery);

//		Pair2 mp = longestZigZagPath(root);
//		System.out.println("Longest Zig Zag path: " + (mp.max - 1));

//		System.out.println("Is BST: " + isBST(root));
//		isBstMorrisTraversal(root);

//		recoverBSTUsingMorrisTraversal(root);

//		StringBuilder sb = new StringBuilder();
//		searlizeTree(root, sb);
//		String tree = sb.substring(0, sb.length() - 1).toString();
//		System.out.println(tree);
//
//		Node node = desearlizeTree(tree.split(","));
//		inOrderTraversal(node);
	}

	private static int idx = 0;
	private static Node desearlizeTree(String[] arr) {

		if(idx == arr.length || arr[idx].equals("null")) {
			idx++;
			return null;
		}
		
		Node node = new Node(Integer.parseInt(arr[idx++]));
		node.left = desearlizeTree(arr);
		node.right = desearlizeTree(arr);
		
		return node;
	}

	private static void searlizeTree(Node root, StringBuilder sb) {

		if (root == null) {
			sb.append("null,");
			return;
		}

		sb.append(root.data + ",");
		searlizeTree(root.left, sb);
		searlizeTree(root.right, sb);
	}

	private static void recoverBSTUsingMorrisTraversal(Node root) {

		Node curr = root;

		Node prev = null;
		Node first = null;
		Node second = null;

		while (curr != null) {
			Node left = curr.left;
			if (left == null) {

				if (prev != null) {
					if (prev.data > curr.data) {
						if (first == null) {
							first = prev;
						} else {
							second = curr;
						}
					}
				}

				prev = curr;
				curr = curr.right;
			} else {
				Node rightMost = getRightMostNode(left, curr);
				if (rightMost.right == null) {
					rightMost.right = curr;
					curr = curr.left;
				} else {
					rightMost.right = null;
					if (prev != null) {
						if (prev.data > curr.data) {
							if (first == null) {
								first = prev;
							} else {
								second = curr;
							}
						}
					}
					prev = curr;
					curr = curr.right;
				}
			}
		}

		int temp = first.data;
		first.data = second.data;
		second.data = temp;

		System.out.println();

		inOrderTraversal(root);
	}

	private static void isBstMorrisTraversal(Node root) {

		List<Integer> list = new ArrayList<>();
		Node curr = root;
		int prev = Integer.MIN_VALUE;
		boolean flag = true;

		while (curr != null) {

			Node left = curr.left;
			if (left == null) {
				list.add(curr.data);
				if (curr.data < prev) {
					flag = false;
					break;
				}
				prev = curr.data;
				curr = curr.right;
			} else {
				Node rightNode = getRightMostNode(left, curr);
				if (rightNode.right == null) {
					rightNode.right = curr;
					curr = curr.left;
				} else {
					rightNode.right = null;
					list.add(curr.data);
					if (curr.data < prev) {
						flag = false;
						break;
					}
					prev = curr.data;
					curr = curr.right;
				}
			}
		}

		System.out.println("Is BST using Morris Traversal: " + flag);
		System.out.println(list);
	}

	private static int prev = Integer.MIN_VALUE;

	private static boolean isBST(Node root) {

		if (root == null) {
			return true;
		}

		boolean flag = isBST(root.left);
		if (flag == false) {
			return false;
		}

		if (root.data < prev) {
			return false;
		} else {
			prev = root.data;
		}

		flag = isBST(root.right);
		if (flag == false) {
			return false;
		}
		return true;
	}

	private static Pair2 longestZigZagPath(Node root) {

		if (root == null) {
			return new Pair2();
		}

		Pair2 lp = longestZigZagPath(root.left);
		Pair2 rp = longestZigZagPath(root.right);

		Pair2 mp = new Pair2();
		mp.lc = lp.rc + 1;
		mp.rc = rp.lc + 1;
		mp.max = Math.max(Math.max(lp.max, rp.max), Math.max(mp.lc, mp.rc));

		return mp;
	}

	private static class Pair2 {

		int lc;
		int rc;
		int max;
	}

	private static HouseRobber maxRobbery(Node root) {

		if (root == null) {
			return new HouseRobber();
		}

		HouseRobber lr = maxRobbery(root.left);
		HouseRobber rr = maxRobbery(root.right);

		HouseRobber mr = new HouseRobber();
		mr.withoutRobbery = Math.max(lr.withoutRobbery, lr.withRobbery) + Math.max(rr.withoutRobbery, rr.withRobbery);
		mr.withRobbery = root.data + lr.withoutRobbery + rr.withoutRobbery;

		return mr;
	}

	private static class HouseRobber {

		int withRobbery;
		int withoutRobbery;
	}

	private static int cameraCount = 0;

	private static int camerasInBinaryTree(Node root) {

		if (root == null) {
			return 0;
		}

		int ls = camerasInBinaryTree(root.left);
		int rs = camerasInBinaryTree(root.right);

		int state = 0;
		if (ls == -1 || rs == -1) {
			cameraCount++;
			state = 1;
		} else if (ls == 1 || rs == 1) {
			state = 0;
		} else {
			state = -1;
		}

		return state;
	}

	private static void morrisPreOrderTraversal(Node root) {

		List<Integer> list = new ArrayList<>();
		Node curr = root;

		while (curr != null) {

			Node left = curr.left;
			if (left == null) {
				list.add(curr.data);
				curr = curr.right;
			} else {
				Node rightNode = getRightMostNode(left, curr);
				if (rightNode.right == null) {
					list.add(curr.data);
					rightNode.right = curr;
					curr = curr.left;
				} else {
					rightNode.right = null;
					curr = curr.right;
				}
			}
		}

		System.out.println(list);
	}

	private static void morrisInOrderTraversal(Node root) {

		Node curr = root;
		List<Integer> list = new ArrayList<>();
		while (curr != null) {

			Node leftNode = curr.left;
			if (leftNode == null) {
				list.add(curr.data);
				curr = curr.right;
			} else {
				Node rightNode = getRightMostNode(leftNode, curr);
				if (rightNode.right == null) {
					rightNode.right = curr;
					curr = curr.left;
				} else {
					rightNode.right = null;
					list.add(curr.data);
					curr = curr.right;
				}
			}
		}

		System.out.println(list);
	}

	public static Node getRightMostNode(Node left, Node curr) {

		while (left.right != null && left.right != curr) {
			left = left.right;
		}
		return left;
	}

	public static void inOrderTraversal(Node root) {

		if (root == null) {
			return;
		}

		inOrderTraversal(root.left);
		System.out.print(root.data + " ");
		inOrderTraversal(root.right);
	}

	public static void preOrderTraversal(Node root) {

		if (root == null) {
			return;
		}

		System.out.print(root.data + " ");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}

	public static void postOrderTraversal(Node root) {

		if (root == null) {
			return;
		}

		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.print(root.data + " ");
	}

	private static Node constructTree(Integer[] arr) {

		Stack<Pair> stack = new Stack<>();
		Node root = new Node(arr[0]);
		stack.push(new Pair(root, 0));

		int i = 1;
		while (!stack.isEmpty()) {

			Pair top = stack.peek();
			if (top.state == 0) {
				if (arr[i] != null) {
					Node node = new Node(arr[i]);
					top.node.left = node;
					stack.push(new Pair(node, 0));
				}
				top.state++;
				i++;
			} else if (top.state == 1) {
				if (arr[i] != null) {
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
}
