package com.javacode.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MaxStack2 {

	public static void main(String[] args) {

		MaxStack2 ms = new MaxStack2();
		ms.push(5);
		ms.push(1);
		ms.push(5);
		System.out.println(ms.peek());
		ms.push(3);
		System.out.println(ms.popMax());
		System.out.println(ms.peek());
		System.out.println(ms.peekMax());
		System.out.println(ms.popMax());
		System.out.println(ms.pop());
		System.out.println(ms.peekMax());
		System.out.println(ms.pop());
	}

	private class Node {

		int data;
		Node prev;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	private Node head;
	private Node tail;
	private TreeMap<Integer, List<Node>> map;

	public MaxStack2() {
		this.head = this.tail = null;
		this.map = new TreeMap<>();
	}

	private void push(int d) {

		Node node = new Node(d);
		if (this.head == null) {
			this.head = this.tail = node;
		} else {
			node.prev = this.tail;
			this.tail.next = node;
			this.tail = node;
		}

		if (!map.containsKey(d)) {
			map.put(d, new ArrayList<>());
		}
		map.get(d).add(node);
	}

	private int pop() {

		int d = this.tail.data;
		if (this.head == this.tail) {
			this.head = this.tail = null;
		} else {
			this.tail = this.tail.prev;
			this.tail.next = null;
		}

		if (map.get(d).size() == 1) {
			map.remove(d);
		} else {
			List<Node> list = map.get(d);
			list.remove(list.size() - 1);
		}

		return d;
	}

	private int popMax() {

		int d = map.lastKey();
		List<Node> list = map.get(d);
		Node node = list.remove(list.size() - 1);
		if (list.size() == 0) {
			map.remove(d);
		}

		if (this.head == node) {
			if (this.head == this.tail) {
				this.head = this.tail = null;
			} else {
				this.head = this.head.next;
			}
		} else if (this.tail == node) {
			this.tail = this.tail.prev;
			this.tail.next = null;
		} else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
		return d;
	}

	private int peek() {
		return this.tail.data;
	}

	private int peekMax() {

		int val = 0;
		List<Node> list = map.lastEntry().getValue();
		val = list.get(list.size() - 1).data;
		return val;
	}

	private void display() {
		Node temp = this.head;
		while (temp != null) {
			System.out.print(temp + " ");
			temp = temp.next;
		}
		System.out.println();
	}
}
