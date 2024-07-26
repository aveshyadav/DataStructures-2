package com.javacode.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree_5_52_60 {

	public static void main(String[] args) {

		Integer arr[] = { 50, 25, 12, null, null, 37, 30, null, null, 40, null, null, 75, 62, 60, null, null, 70, null,
				null, 87, null, null };

		Node root = constructTree(arr);
		inOrderTraversal(root);
		System.out.println();

//		boolean status = rootToLeafPathSum(root, 212, "");
//		System.out.println("Status: " + status);

//		List<List<Integer>> ans = new ArrayList<>();
//		List<Integer> list = new ArrayList<>();
//		pathSum2(root, 247, ans, list);
//
//		System.out.println(ans);

//		int diam[] = diameterOfTree(root);
//		System.out.println("Height: " + diam[0]);
//		System.out.println("Diameter: " + diam[1]);

//		Pair2 mp = maxPathSum(root);
//		System.out.println("Max Path Sum: " + mp.leafToLeafSum);
//
//		maxPathSum2(root);
//		System.out.println("Max Path Sum2: " + maxSum);

//		lowestCommonAncestor(root, 60, 87);
//		System.out.println(lca.data);

//		Pair3 mp = anyNodeToAnyNodePathSum(root);
//		System.out.println("Max path sum: " + Math.max(mp.NTNPathSum, mp.RTNPathSum));
	}

	private static Pair3 anyNodeToAnyNodePathSum(Node root) {

		if (root == null) {
			return new Pair3();
		}

		Pair3 lp = anyNodeToAnyNodePathSum(root.left);
		Pair3 rp = anyNodeToAnyNodePathSum(root.right);

		Pair3 mp = new Pair3();
		int RTNPathSum = Math.max(lp.RTNPathSum, rp.RTNPathSum) + root.data;
		int NTNPathSum = getMaxPathSum(lp.NTNPathSum, rp.NTNPathSum, root.data,
				lp.RTNPathSum + root.data + rp.RTNPathSum, RTNPathSum);

		mp.RTNPathSum = Math.max(RTNPathSum, root.data);
		mp.NTNPathSum = NTNPathSum;
		return mp;
	}

	private static int getMaxPathSum(int... arr) {

		int max = Integer.MIN_VALUE;
		for (int i : arr) {
			max = Math.max(max, i);
		}
		return max;
	}

	private static class Pair3 {

		int NTNPathSum;
		int RTNPathSum;
	}

	private static Node lca = null;

	private static boolean lowestCommonAncestor(Node root, int d1, int d2) {

		if (root == null) {
			return false;
		}

		boolean self = root.data == d1 || root.data == d2;

		boolean ls = lowestCommonAncestor(root.left, d1, d2);
		boolean rs = lowestCommonAncestor(root.right, d1, d2);

		if ((self && ls) || (self && rs) || (ls && rs)) {
			lca = root;
		}

		return self || ls || rs;
	}

	private static int maxSum = 0;

	private static int maxPathSum2(Node root) {

		if (root == null) {
			return 0;
		}

		if (root.left == null && root.right == null) {
			return root.data;
		}

		int ls = maxPathSum2(root.left);
		int rs = maxPathSum2(root.right);

		int sum = Math.max(ls, rs) + root.data;
		if (root.left != null && root.right != null) {
			maxSum = Math.max(maxSum, ls + rs + root.data);
		}

		return sum;
	}

	private static Pair2 maxPathSum(Node root) {

		if (root == null) {
			return new Pair2();
		}

		if (root.left == null && root.right == null) {
			Pair2 mp = new Pair2();
			mp.leafToNodeSum = root.data;
			return mp;
		}

		Pair2 lp = maxPathSum(root.left);
		Pair2 rp = maxPathSum(root.right);

		Pair2 mp = new Pair2();
		mp.leafToNodeSum = Math.max(lp.leafToNodeSum, rp.leafToNodeSum) + root.data;
		mp.leafToLeafSum = Math.max(lp.leafToLeafSum, rp.leafToLeafSum);

		if (root.left != null && root.right != null) {
			mp.leafToLeafSum = Math.max(mp.leafToLeafSum, lp.leafToNodeSum + rp.leafToNodeSum + root.data);
		}

		return mp;
	}

	private static class Pair2 {

		int leafToNodeSum;
		int leafToLeafSum;
	}

	private static int[] diameterOfTree(Node root) {

		if (root == null) {
			int arr[] = new int[2];
			arr[0] = -1;
			return arr;
		}

		int ld[] = diameterOfTree(root.left);
		int rd[] = diameterOfTree(root.right);

		int md[] = new int[2];
		md[0] = Math.max(ld[0], rd[0]) + 1;
		md[1] = Math.max(Math.max(ld[1], rd[1]), ld[0] + rd[0] + 2);

		return md;
	}

	private static void pathSum2(Node root, int target, List<List<Integer>> ans, List<Integer> list) {

		if (root == null) {
			return;
		}

		if (root.left == null && root.right == null) {
			target = target - root.data;
			list.add(root.data);
			if (target == 0) {
				List<Integer> temp = new ArrayList<>();
				for (int i : list) {
					temp.add(i);
				}
				ans.add(temp);
			}
			list.remove(list.size() - 1);
			return;
		}

		list.add(root.data);
		pathSum2(root.left, target - root.data, ans, list);
		pathSum2(root.right, target - root.data, ans, list);
		list.remove(list.size() - 1);
	}

	private static boolean rootToLeafPathSum(Node root, int target, String ans) {

		if (root == null) {
			if (target == 0) {
				System.out.println(ans.substring(1));
				return true;
			}
			return false;
		}

		boolean ls = rootToLeafPathSum(root.left, target - root.data, ans + "+" + root.data);
		if (ls == true) {
			return true;
		}

		boolean rs = rootToLeafPathSum(root.right, target - root.data, ans + "+" + root.data);
		if (rs == true) {
			return true;
		}

		return false;
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
