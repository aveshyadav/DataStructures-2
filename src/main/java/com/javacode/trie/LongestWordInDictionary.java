package com.javacode.trie;

public class LongestWordInDictionary {

	public static void main(String[] args) {

		LongestWordInDictionary trie = new LongestWordInDictionary();
//		String arr[] = { "a", "banana", "app", "appl", "ap", "apply", "apple" };
		String arr[] = { "w", "wo", "wor", "worl", "world" };

		for (String str : arr) {
			trie.insert(str);
		}

		trie.dfs(trie.root);
		System.out.println("Ans: " + trie.ans);
	}

	private Node root;

	public LongestWordInDictionary() {
		this.root = new Node();
	}

	public void insert(String str) {

		Node curr = root;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			int idx = ch - 'a';

			if (curr.childs[idx] == null) {
				curr.childs[idx] = new Node();
			}
			curr = curr.childs[idx];
		}
		curr.str = str;
		curr.isEnds = true;
	}

	private String ans = "";

	public void dfs(Node root) {

		for (Node node : root.childs) {
			if (node != null && node.str != "" && node.isEnds) {
				if (node.str.length() > ans.length()) {
					ans = node.str;
				}
				dfs(node);
			}
		}
	}

	private static class Node {

		boolean isEnds;
		String str;
		Node childs[] = new Node[26];
	}

}
