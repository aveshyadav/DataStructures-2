package com.javacode.trie;

public class MaxXorTwoNumbers {

	public static void main(String[] args) {

		MaxXorTwoNumbers trie = new MaxXorTwoNumbers();
		int arr[] = { 3, 10, 5, 25, 2, 8 };

		for (int num : arr) {
			trie.insert(num);
		}

		int max = 0;
		for (int num : arr) {

			int res = trie.find(num);
			int nv = (res ^ num);

			max = Math.max(max, nv);
			System.out.println(nv);
		}
		System.out.println("Max: " + max);
	}

	private Node root;

	public MaxXorTwoNumbers() {
		this.root = new Node();
	}

	public void insert(int num) {

		int bitIndex = 30;
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
			bitIndex--;
		}
	}

	public int find(int num) {

		int ans = 0;
		int bitIndex = 30;
		Node curr = root;

		while (bitIndex >= 0) {

			int mask = 1 << bitIndex;
			int bit = (mask & num) > 0 ? 1 : 0;

			if (bit == 0) {
				if (curr.right != null) {
					curr = curr.right;
					ans |= mask;
				} else {
					curr = curr.left;
				}
			} else {
				if (curr.left != null) {
					curr = curr.left;
				} else {
					curr = curr.right;
					ans |= mask;
				}
			}
			bitIndex--;
		}

		return ans;
	}

	private static class Node {

		boolean isEnd;
		Node left;
		Node right;
	}

}
