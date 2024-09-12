package com.javacode.trie;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary2 {

	public static void main(String[] args) {

		WordDictionary2 wordDictionary = new WordDictionary2();
		wordDictionary.addWord("bad");
		wordDictionary.addWord("dad");
		wordDictionary.addWord("mad");
		System.out.println(wordDictionary.search("pad")); // return False
		System.out.println(wordDictionary.search("bad")); // return True
		System.out.println(wordDictionary.search(".ad")); // return True
		System.out.println(wordDictionary.search("b..")); // return True

		wordDictionary.addWord("at");
		wordDictionary.addWord("and");
		wordDictionary.addWord("an");
		wordDictionary.addWord("add");

		System.out.println(wordDictionary.search("a"));
		System.out.println(wordDictionary.search(".at"));

		wordDictionary.addWord("bat");

		System.out.println(wordDictionary.search(".at"));
		System.out.println(wordDictionary.search("an."));
		System.out.println(wordDictionary.search("a.d."));
		System.out.println(wordDictionary.search("b."));
		System.out.println(wordDictionary.search("a.d"));
		System.out.println(wordDictionary.search("."));
	}

	private Node root;

	public WordDictionary2() {
		this.root = new Node();
	}

	private void addWord(String str) {

		Node curr = root;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (!curr.childs.containsKey(ch)) {
				curr.childs.put(ch, new Node());
			}
			curr = curr.childs.get(ch);
		}

		curr.isEnds = true;
	}

	private boolean search(String str) {
		return find(root, str, 0);
	}

	private boolean find(Node root, String str, int idx) {

		if (idx == str.length()) {
			return root.isEnds;
		}

		char ch = str.charAt(idx);
		if (ch == '.') {
			for (char ch2 : root.childs.keySet()) {
				boolean flag = find(root.childs.get(ch2), str, idx + 1);
				if (flag == true) {
					return true;
				}
			}
		} else {
			if (root.childs.containsKey(ch)) {
				return find(root.childs.get(ch), str, idx + 1);
			} else {
				return false;
			}
		}
		return false;
	}

	private static class Node {

		boolean isEnds;
		Map<Character, Node> childs;

		public Node() {
			this.childs = new HashMap<>();
		}
	}
}
