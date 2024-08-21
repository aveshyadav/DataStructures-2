package com.javacode.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointers {

	public static void main(String[] args) {

		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);

		n1.next = n2;
		n1.random = n1;

		n2.next = n3;
		n2.random = n5;

		n3.next = n4;
		n3.random = n6;

		n4.next = n5;
		n4.random = null;

		n5.next = n6;
		n5.random = n1;

		n6.next = null;
		n6.random = null;

		Node head = n1;
		traverse(head);
//		copyList(head);
		copyList2(head);
	}

	private static void copyList2(Node head) {

		Node curr = head;

		while (curr != null) {

			Node next = curr.next;

			Node node = new Node(curr.data);
			curr.next = node;
			node.next = next;

			curr = next;
		}

		curr = head;
		while (curr != null) {

			curr.next.random = curr.random;
			curr = curr.next.next;
		}

		Node nh = new Node(-1);
		Node nt = nh;
		curr = head;

		while (curr != null) {

			Node next = curr.next.next;
			
			nt.next = curr.next;
			curr.next = curr.next.next;
			curr = next;
			nt = nt.next;
		}

		traverse(head);
		traverse(nh.next);
	}

	private static void copyList(Node head) {

		Node curr = head;
		Node nh = new Node(-1);
		Node nt = nh;

		Map<Node, Node> map = new HashMap<>();
		while (curr != null) {

			Node node = new Node(curr.data);
			nt.next = node;

			map.put(curr, node);
			curr = curr.next;
			nt = nt.next;
		}

		curr = head;
		nt = nh.next;
		while (curr != null) {

			nt.random = map.get(curr.random);
			curr = curr.next;
			nt = nt.next;
		}

		traverse(nh.next);
	}

	private static void traverse(Node head) {

		Node curr = head;

		while (curr != null) {
			if (curr.random == null) {
				System.out.println(curr.data + " " + null);
			} else {
				System.out.println(curr.data + " " + curr.random.data);
			}
			curr = curr.next;
		}
		System.out.println();
	}

	private static class Node {

		int data;
		Node next;
		Node random;

		public Node(int data) {
			this.data = data;
		}
	}
}
