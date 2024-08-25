package com.javacode.map;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

	public static void main(String[] args) {

		LRUCache cache = new LRUCache(3);
		cache.put(1, 10);
		cache.put(2, 20);
		cache.put(3, 30);
		cache.get(1);
		cache.put(1, 15);
		cache.get(1);
		cache.get(2);
		cache.put(4, 40);
		cache.get(3);
		cache.get(4);

	}

	private int capacity;
	private Node head;
	private Node tail;
	private int size;

	private Map<Integer, Node> map;

	public LRUCache(int cap) {
		this.capacity = cap;
		this.head = null;
		this.tail = null;
		this.size = 0;

		map = new HashMap<>();
	}

	public void put(int key, int val) {

		Node node = map.get(key);
		if (node != null) {
			removeNode(node);
			node = addFirst(node.key, val);
			map.put(key, node);
		} else {
			if (this.size == this.capacity) {
				node = removeLast();
				map.remove(node.key);
			}
			node = addFirst(key, val);
			map.put(key, node);
		}

	}

	public int get(int key) {

		Node node = map.get(key);

		if (node == null) {
			System.out.println(-1);
			return -1;
		} else {
			removeNode(node);
			node = addFirst(node.key, node.data);
			map.put(key, node);
		}
		System.out.println(node.data);

		return node.data;
	}

	private Node addFirst(int key, int data) {

		Node node = new Node(key, data);
		if (head == null) {
			this.head = this.tail = node;
		} else {
			node.next = head;
			head.prev = node;
			head = node;
		}
		this.size++;
		return node;
	}

	private Node removeLast() {

		Node node = tail;
		if (head == tail) {
			this.head = this.tail = null;
		} else {
			tail = tail.prev;
			tail.next = null;
		}
		this.size--;
		return node;
	}

	private void removeNode(Node node) {

		if (head == node) {
			if (head == tail) {
				this.head = this.tail = null;
			} else {
				head = head.next;
				head.prev = null;
			}
		} else if (tail == node) {
			tail = tail.prev;
			tail.next = null;
		} else {

			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
		this.size--;
	}

	private static class Node {

		int key;
		int data;
		Node prev;
		Node next;

		public Node(int key, int data) {
			this.key = key;
			this.data = data;
		}
	}
}
