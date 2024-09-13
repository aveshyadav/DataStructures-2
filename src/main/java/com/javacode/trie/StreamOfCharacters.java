package com.javacode.trie;

public class StreamOfCharacters {

	public static void main(String[] args) {

		String words[] = { "cd", "f", "kl" };
		StreamOfCharacters trie = new StreamOfCharacters(words);

		char arr[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l' };

		StringBuilder sb = new StringBuilder();
		for (char ch : arr) {

			sb.append(ch);
			boolean res = false;
			for (int i = sb.length() - 1; i >= 0; i--) {
				res = trie.query(sb.toString(), i);
				if (res == true) {
					break;
				}
			}
			System.out.println(res);
		}
	}

	private Node root;

	public StreamOfCharacters(String words[]) {

		this.root = new Node();
		for (String str : words) {
			insert(str);
		}
	}

	public boolean query(String str, int k) {

		Node curr = root;
		for (int i = str.length() - 1; i >= k; i--) {

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

	public void insert(String str) {

		Node curr = root;
		for (int i = str.length() - 1; i >= 0; i--) {

			char ch = str.charAt(i);
			int idx = ch - 'a';

			if (curr.childs[idx] == null) {
				curr.childs[idx] = new Node();
			}
			curr = curr.childs[idx];
		}
		curr.isEnds = true;
	}

	private static class Node {

		boolean isEnds;
		Node childs[] = new Node[26];
	}

}
