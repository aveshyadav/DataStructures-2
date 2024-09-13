package com.javacode.trie;

public class CountPairsWithXorInRange {

	public static void main(String[] args) {

		CountPairsWithXorInRange trie = new CountPairsWithXorInRange();

		int nums[] = { 9, 8, 4, 2, 1 };
		int low = 5;
		int high = 14;

		int count = 0;
		for (int num : nums) {

			count += trie.query(trie.root, high, num, 14);
			count -= trie.query(trie.root, low - 1, num, 14);
			trie.insert(num);
		}

		System.out.println("Count: " + count);
	}

	private Node root;

	public CountPairsWithXorInRange() {
		this.root = new Node();
	}

	public int query(Node root, int high, int val, int idx) {

		if (root == null) {
			return 0;
		}

		if (idx == -1) {
			return getCount(root);
		}

		int bitV = (val & (1 << idx)) > 0 ? 1 : 0;
		int bitH = (high & (1 << idx)) > 0 ? 1 : 0;

		if (bitV == 0) {
			if (bitH == 0) {
				return query(root.left, high, val, idx - 1);
			} else {
				return getCount(root.left) + query(root.right, high, val, idx - 1);
			}
		} else {
			if (bitH == 0) {
				return query(root.right, high, val, idx - 1);
			} else {
				return getCount(root.right) + query(root.left, high, val, idx - 1);
			}
		}
	}

	public static int getCount(Node node) {
		return node == null ? 0 : node.count;
	}

	public void insert(int num) {

		int bitIndex = 14;
		Node curr = root;

		while (bitIndex >= 0) {

			int mask = 1 << bitIndex;
			int bit = (num & mask) > 0 ? 1 : 0;

			if (bit == 0) {
				if (curr.left == null) {
					curr.left = new Node();
				}
				curr = curr.left;
			} else {
				if (curr.right == null) {
					curr.right = new Node();
				}
				curr = curr.right;
			}
			curr.count++;
			bitIndex--;
		}

	}

	private static class Node {

		Node left;
		Node right;
		int count;
	}

}
