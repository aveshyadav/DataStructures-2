package com.javacode.trie;

public class ReplaceWords {

	public static void main(String[] args) {

		ReplaceWords trie = new ReplaceWords();

		String dict[] = { "cat", "bat", "rat" };
		String sentence = "the cattle was rattled by the battery";

		for (String str : dict) {
			trie.addWord(str);
		}

		StringBuilder sb = new StringBuilder();
		for (String str : sentence.split("\\s+")) {
			String ans = trie.search(str);
			sb.append(ans).append(" ");
		}
		System.out.println(sb.toString());
	}

	private Node root;

	public ReplaceWords() {
		this.root = new Node();
	}

	public void addWord(String str) {

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

	public String search(String str) {

		int i = 0;
		Node curr = root;
		String ans = "";
		for (i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			int idx = ch - 'a';

			if (curr.childs[idx] != null) {
				ans += ch;
				curr = curr.childs[idx];
			} else {
				break;
			}
		}
		if (curr.isEnds == true) {
			return ans;
		} else {
			return str;
		}
	}
}
