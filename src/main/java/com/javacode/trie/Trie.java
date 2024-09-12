package com.javacode.trie;

public class Trie {

	public static void main(String[] args) {

		Trie trie = new Trie();
		trie.insert("apple");
		System.out.println(trie.search("apple"));
		System.out.println(trie.search("app"));
		System.out.println(trie.startsWith("app"));
		trie.insert("app");
		System.out.println(trie.search("app"));
	}

	private Node root;

	public Trie() {
		root = new Node();
	}

	private void insert(String str) {

		Node curr = root;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			int idx = ch - 'a';
			if (curr.childs[idx] == null) {
				curr.childs[idx] = new Node();
			}
			curr = curr.childs[idx];
		}

		curr.isEnds = true;
	}

	private boolean search(String str) {

		Node curr = root;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			int idx = ch - 'a';
			if (curr.childs[idx] != null) {
				curr = curr.childs[idx];
			} else {
				return false;
			}
		}
		return curr.isEnds;
	}

	private boolean startsWith(String str) {

		Node curr = root;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			int idx = ch - 'a';
			if (curr.childs[idx] != null) {
				curr = curr.childs[idx];
			} else {
				return false;
			}

		}
		return true;
	}
}
