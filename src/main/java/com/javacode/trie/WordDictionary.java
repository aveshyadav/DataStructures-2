package com.javacode.trie;

public class WordDictionary {

	public static void main(String[] args) {

		WordDictionary wordDictionary = new WordDictionary();
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

	public WordDictionary() {
		root = new Node();
	}

	private void addWord(String str) {

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
		return find(root, str, 0);
	}

	private boolean find(Node root, String str, int i) {

		if (i == str.length()) {
			return root.isEnds;
		}

		char ch = str.charAt(i);
		int idx = ch - 'a';

		if (ch == '.') {
			for (int j = 0; j < root.childs.length; j++) {
				if (root.childs[j] != null) {
					boolean flag = find(root.childs[j], str, i + 1);
					if (flag == true) {
						return true;
					}
				}
			}
		} else {
			if (root.childs[idx] != null) {
				return find(root.childs[idx], str, i + 1);
			} else {
				return false;
			}
		}

		return false;
	}

}
