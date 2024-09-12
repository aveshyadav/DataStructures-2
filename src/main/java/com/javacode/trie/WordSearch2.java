package com.javacode.trie;

import java.util.HashSet;
import java.util.Set;

public class WordSearch2 {

	public static void main(String[] args) {

		WordSearch2 trie = new WordSearch2();

		char arr[][] = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' }, { 'i', 'h', 'k', 'r' },
				{ 'i', 'f', 'l', 'v' } };

		String words[] = { "oath", "pea", "eat", "rain", "oaths" };
		for (String str : words) {
			trie.addWord(str);
		}

		boolean vis[][] = new boolean[arr.length][arr[0].length];
		Set<String> ans = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				trie.searchWords(arr, i, j, trie.root, vis, ans, "");
			}
		}

		System.out.println(ans);
	}

	public void searchWords(char arr[][], int row, int col, Node root, boolean vis[][], Set<String> bag, String ans) {

		if (row < 0 || col < 0 || row == arr.length || col == arr[0].length || vis[row][col] == true) {
			return;
		}

		char ch = arr[row][col];
		int idx = ch - 'a';

		if (root.childs[idx] != null) {

			ans += ch;
			vis[row][col] = true;
			if (root.childs[idx].isEnds == true) {
				bag.add(ans);
			}

			int dirs[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
			for (int d[] : dirs) {

				int nr = d[0] + row;
				int nc = d[1] + col;

				if (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr[0].length && vis[nr][nc] == false) {
					searchWords(arr, nr, nc, root.childs[idx], vis, bag, ans);
				}
			}
			vis[row][col] = false;
		}
	}

	private Node root;

	public WordSearch2() {
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

	public boolean search(String str) {

		Node curr = root;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			int idx = ch - 'a';

			if (curr.childs[idx] == null) {
				return false;
			} else {
				curr = curr.childs[idx];
			}
		}

		return curr.isEnds;
	}
}
