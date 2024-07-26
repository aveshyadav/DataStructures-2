package com.javacode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BinaryTree_2_10_21 {

	public static void main(String[] args) {

//		int pre[] = { 0, 1, 3, 7, 8, 4, 9, 10, 2, 5, 11, 6 };
//		int post[] = { 7, 8, 3, 9, 10, 4, 1, 11, 5, 6, 2, 0 };
//		int in[] = { 7, 3, 8, 1, 9, 4, 10, 0, 11, 5, 2, 6 };
//		int level[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

//		Node root = buildTreeFromPreOrderAndInOrder(pre, 0, pre.length - 1, in, 0, in.length - 1);
//		preOrderTraversal(root);

//		Node root = buildTreeFromPostOrderAndInOrder(post, 0, post.length - 1, in, 0, in.length - 1);
//		postOrderTraversal(root);

//		Node root = buildTreeFromLevelOrderAndInOrder(level, in, 0, in.length - 1);
//		inOrderTraversal(root);

//		Node root = buildTreeFromPreOrderAndPostOrder(pre, 0, pre.length - 1, post, 0, post.length - 1);
//		preOrderTraversal(root);

//		int in[] = { 9, 12, 14, 17, 19, 23, 50, 54, 67, 72, 76 };
//		Node root = buildBSTFromInOrder(in, 0, in.length - 1);
//		inOrderTraversal(root);

//		int pre[] = { 23, 14, 9, 12, 17, 19, 67, 50, 54, 72, 76 };
//		Node root = buildBSTFromPreOrder(pre, 0, pre.length - 1);
//		preOrderTraversal(root);

//		int post[] = { 12, 9, 19, 17, 14, 54, 50, 76, 72, 67, 23 };
//		Node root = buildBSTFromPostOrder(post, 0, post.length - 1);
//		postOrderTraversal(root);

		int level[] = { 23, 14, 67, 9, 17, 50, 72, 12, 19, 54, 76 };
		Node root = buildBSTFromLevelOrder(level);
		preOrderTraversal(root);
	}

	private static Node buildBSTFromLevelOrder(int[] level) {

		if (level.length == 0) {
			return null;
		}

		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();

		for (int i = 1; i < level.length; i++) {
			if (level[i] < level[0]) {
				l1.add(level[i]);
			} else {
				l2.add(level[i]);
			}
		}
		int left[] = new int[l1.size()];
		for (int i = 0; i < l1.size(); i++) {
			left[i] = l1.get(i);
		}
		int right[] = new int[l2.size()];
		for (int i = 0; i < l2.size(); i++) {
			right[i] = l2.get(i);
		}

		Node node = new Node(level[0]);
		node.left = buildBSTFromLevelOrder(left);
		node.right = buildBSTFromLevelOrder(right);

		return node;
	}

	private static Node buildBSTFromPostOrder(int[] post, int psi, int pei) {

		if (psi > pei) {
			return null;
		}

		int idx = psi;
		while (idx < pei && post[idx] < post[pei]) {
			idx++;
		}

		Node node = new Node(post[pei]);
		node.left = buildBSTFromPostOrder(post, psi, idx - 1);
		node.right = buildBSTFromPostOrder(post, idx, pei - 1);

		return node;
	}

	private static Node buildBSTFromPreOrder(int[] pre, int psi, int pei) {

		if (psi > pei) {
			return null;
		}

		int idx = psi + 1;
		while (idx < pei && pre[idx] < pre[psi]) {
			idx++;
		}

		Node node = new Node(pre[psi]);
		node.left = buildBSTFromPreOrder(pre, psi + 1, idx - 1);
		node.right = buildBSTFromPreOrder(pre, idx, pei);

		return node;
	}

	private static Node buildBSTFromInOrder(int[] in, int lo, int hi) {

		if (lo > hi) {
			return null;
		}

		int mid = (lo + hi) / 2;
		Node node = new Node(in[mid]);
		node.left = buildBSTFromInOrder(in, lo, mid - 1);
		node.right = buildBSTFromInOrder(in, mid + 1, hi);

		return node;
	}

	private static Node buildTreeFromPreOrderAndPostOrder(int[] pre, int psi, int pei, int[] post, int posi, int poei) {

		if (psi > pei) {
			return null;
		}
		Node node = new Node(pre[psi]);
		if (psi == pei) {
			return node;
		}

		int idx = posi;
		while (post[idx] != pre[psi + 1]) {
			idx++;
		}
		int colse = idx - posi + 1;

		node.left = buildTreeFromPreOrderAndPostOrder(pre, psi + 1, psi + colse, post, posi, idx);
		node.right = buildTreeFromPreOrderAndPostOrder(pre, psi + colse + 1, pei, post, idx + 1, poei - 1);

		return node;
	}

	private static Node buildTreeFromLevelOrderAndInOrder(int[] level, int[] in, int isi, int iei) {

		if (isi > iei) {
			return null;
		}

		Set<Integer> set = new HashSet<>();
		int idx = isi;
		while (in[idx] != level[0]) {
			set.add(in[idx]);
			idx++;
		}

		int left[] = new int[set.size()];
		int right[] = new int[level.length - set.size()];

		int li = 0;
		int ri = 0;
		for (int i = 1; i < level.length; i++) {
			if (set.contains(level[i])) {
				left[li++] = level[i];
				set.remove(level[i]);
			} else {
				right[ri++] = level[i];
			}
		}

		Node node = new Node(level[0]);
		node.left = buildTreeFromLevelOrderAndInOrder(left, in, isi, idx - 1);
		node.right = buildTreeFromLevelOrderAndInOrder(right, in, idx + 1, iei);

		return node;
	}

	private static Node buildTreeFromPostOrderAndInOrder(int[] post, int posi, int poei, int[] in, int isi, int iei) {

		if (isi > iei) {
			return null;
		}

		int idx = isi;
		int len = 0;
		while (in[idx] != post[poei]) {
			idx++;
			len++;
		}

		Node node = new Node(post[poei]);
		node.left = buildTreeFromPostOrderAndInOrder(post, posi, posi + len - 1, in, isi, idx - 1);
		node.right = buildTreeFromPostOrderAndInOrder(post, posi + len, poei - 1, in, idx + 1, iei);

		return node;
	}

	private static Node buildTreeFromPreOrderAndInOrder(int pre[], int psi, int pei, int in[], int isi, int iei) {

		if (isi > iei) {
			return null;
		}

		int idx = isi;
		int len = 0;
		while (in[idx] != pre[psi]) {
			len++;
			idx++;
		}

		Node node = new Node(pre[psi]);
		node.left = buildTreeFromPreOrderAndInOrder(pre, psi + 1, psi + len, in, isi, idx - 1);
		node.right = buildTreeFromPreOrderAndInOrder(pre, psi + len + 1, pei, in, idx + 1, iei);

		return node;
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

	private static class Node {

		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
		}
	}
}
