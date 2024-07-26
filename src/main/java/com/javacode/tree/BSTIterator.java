package com.javacode.tree;

import java.util.Stack;

public class BSTIterator {

	Stack<Node> stack;

	public BSTIterator(Node root) {

		stack = new Stack<>();
		while (root != null) {
			stack.push(root);
			root = root.left;
		}
	}

	public boolean hasNext() {
		return stack.size() > 0;
	}

	public int next() {

		Node node = stack.pop();
		if (node.right != null) {

			Node top = node.right;
			while (top != null) {
				stack.push(top);
				top = top.left;
			}
		}

		return node.data;
	}
}
