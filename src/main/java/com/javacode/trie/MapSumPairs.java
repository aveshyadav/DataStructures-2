package com.javacode.trie;

import java.util.HashMap;
import java.util.Map;

public class MapSumPairs {

	public static void main(String[] args) {

		MapSumPairs sp = new MapSumPairs();
		sp.insert("card", 2);
		sp.insert("cot", 2);
		sp.insert("cards", 3);
		System.out.println(sp.sum("car"));
		sp.insert("tried", 4);
		sp.insert("cards", 1);
		sp.insert("cots", 3);
		System.out.println(sp.sum("c"));

	}

	private Node root;
	private Map<String, Integer> map;

	public MapSumPairs() {
		this.root = new Node();
		this.map = new HashMap<>();
	}

	private void insert(String str, int num) {

		if (map.containsKey(str)) {
			num -= map.get(str);
		}

		Node curr = root;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			int idx = ch - 'a';

			if (curr.childs[idx] == null) {
				curr.childs[idx] = new Node();
			}
			curr = curr.childs[idx];
			curr.count += num;
		}

		map.put(str, num);
	}

	private int sum(String str) {

		Node curr = root;
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			int idx = ch - 'a';

			if (curr.childs[idx] != null) {
				curr = curr.childs[idx];
			} else {
				return -1;
			}
		}
		return curr.count;
	}

	private static class Node {

		int count;
		Node childs[] = new Node[26];
	}
}
