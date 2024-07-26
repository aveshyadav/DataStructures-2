package com.javacode.tree;

public class BSTIteratorMorris {

	Node curr;

	public BSTIteratorMorris(Node node) {
		this.curr = node;
	}

	public boolean hasNext() {
		return curr != null;
	}

	public int next() {

		int data = 0;
		while (curr != null) {

			Node left = curr.left;
			if (left == null) {
				data = curr.data;
				curr = curr.right;
				break;
			} else {
				Node rightNode = getRigtMostNode(left, curr);
				if (rightNode.right == null) {
					rightNode.right = curr;
					curr = curr.left;
				} else {
					rightNode.right = null;
					data = curr.data;
					curr = curr.right;
					break;
				}
			}

		}
		return data;
	}

	public Node getRigtMostNode(Node left, Node curr) {

		while (left.right != null && left.right != curr) {
			left = left.right;
		}
		return left;
	}
}
