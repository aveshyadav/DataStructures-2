package com.javacode.linkedlist;

import com.javacode.linkedlist.LinkedListDSA.Node;

public class LinkedLIst_20_27 {

	public static void main(String[] args) {

//		Node n1 = new Node(1);
//		Node n2 = new Node(2);
//		Node n3 = new Node(3);
//		Node n4 = new Node(4);
//		Node n5 = new Node(5);
//		Node n6 = new Node(6);
//
//		n1.next = n2;
//		n2.next = n3;
//		n3.next = n4;
//		n4.next = n5;
//		n5.next = n6;
//		n6.next = null;
//
//		Node n11 = new Node(-1);
//		Node n12 = new Node(-2);
//		Node n13 = new Node(-3);
//		n11.next = n12;
//		n12.next = n13;
//		n13.next = n4;
//
//		traverse(n1);
//		traverse(n11);
//
//		findIntersectingNode1(n1, n11);
//		findIntersectingNode2(n1, n11);

//		LinkedListDSA list1 = new LinkedListDSA();
//		list1.addLast(9);
//		list1.addLast(9);
//		list1.addLast(8);
//		list1.addLast(7);
//		list1.addLast(3);
//		list1.addLast(2);
//
//		LinkedListDSA list2 = new LinkedListDSA();
//		list2.addLast(2);
//		list2.addLast(2);
//		list2.addLast(6);
//		list2.addLast(8);
//
//		traverse(list1.head);
//		traverse(list2.head);
//
//		Node nhead = addition(list1.head, list2.head);
//		traverse(nhead);

//		LinkedListDSA list1 = new LinkedListDSA();
//		list1.addLast(1);
//		list1.addLast(2);
//		list1.addLast(3);
//		list1.addLast(4);
//		list1.addLast(5);
//		list1.addLast(6);
//		list1.addLast(7);
//
//		LinkedListDSA list2 = new LinkedListDSA();
//		list2.addLast(7);
//		list2.addLast(8);
//		list2.addLast(9);
//
//		Node nHead = subtraction(list1.head, list2.head);
//		traverse(list1.head);
//		traverse(list2.head);
//		System.out.println("-----------");
//		traverse(nHead);

//		LinkedListDSA list1 = new LinkedListDSA();
//		list1.addLast(8);
//		list1.addLast(3);
//		list1.addLast(5);
//
//		LinkedListDSA list2 = new LinkedListDSA();
//		list2.addLast(3);
//		list2.addLast(2);
//
//		traverse(list1.head);
//		traverse(list2.head);
//
//		multiplication(list1.head, list2.head);

		LinkedListDSA list = new LinkedListDSA();
		list.addLast(1);
		list.addLast(1);
		list.addLast(1);
		list.addLast(4);
		list.addLast(5);
		list.addLast(6);
		list.addLast(6);
		list.addLast(7);
		list.addLast(8);
		list.addLast(9);
		list.addLast(9);
		list.addLast(9);

		traverse(list.head);
//		removeDuplicates(list.head);
		removeAllDuplicates(list.head);
	}

	private static void removeAllDuplicates(Node head) {

		Node ph = new Node(-1);
		Node pt = ph;
		Node curr = head;

		while (curr != null) {

			int count = 0;
			Node temp = curr;
			while (curr != null && curr.data == temp.data) {
				curr = curr.next;
				count++;
			}

			if (count == 1) {
				pt.next = temp;
				pt = pt.next;
			}
		}
		pt.next = null;
		
		traverse(ph.next);
	}

	private static void removeDuplicates(Node head) {

		Node ph = new Node(-1);
		Node pt = ph;
		Node curr = head;

		while (curr != null) {

			if (curr.data != pt.data) {
				pt.next = curr;
				pt = pt.next;
			}
			curr = curr.next;
		}
		pt.next = null;
		traverse(ph.next);
	}

	private static void multiplication(Node h1, Node h2) {

		h1 = reverse(h1);
		h2 = reverse(h2);

		Node c1 = h2;
		Node c2 = h1;

		Node ph = null;

		int count = 0;
		while (c1 != null) {

			Node nh = new Node(-1);
			Node nt = nh;
			c2 = h1;

			int carry = 0;

			for (int i = 0; i < count; i++) {
				Node node = new Node(0);
				nt.next = node;
				nt = nt.next;
			}
			count++;

			while (c2 != null || carry > 0) {

				int d1 = c1.data;

				int d = carry;
				if (c2 != null) {

					int d2 = c2.data;
					d = d + (d1 * d2);
					c2 = c2.next;
				}

				carry = 0;
				if (d > 9) {
					carry = d / 10;
					d = d % 10;
				}

				Node node = new Node(d);
				nt.next = node;
				nt = nt.next;

			}

			nh = reverse(nh.next);
			traverse(nh);

			if (ph == null) {
				ph = nh;
			} else {
				ph = addition(ph, nh);
			}

			c1 = c1.next;
		}

		h1 = reverse(h1);
		h2 = reverse(h2);
		traverse(ph);
	}

	private static Node subtraction(Node h1, Node h2) {

		h1 = reverse(h1);
		h2 = reverse(h2);

		Node c1 = h1;
		Node c2 = h2;

		Node nh = new Node(-1);
		Node nt = nh;

		int borrow = 0;
		while (c1 != null || c2 != null) {

			int data = c1.data - borrow;
			borrow = 0;

			if (data < 0) {
				data = data + 10;
				borrow = 1;
			}

			if (c2 != null) {
				if (data >= c2.data) {
					data = data - c2.data;
				} else {
					data = data + 10 - c2.data;
					borrow = 1;
				}
				c2 = c2.next;
			}

			Node node = new Node(data);
			nt.next = node;
			nt = nt.next;

			c1 = c1.next;
		}

		h1 = reverse(h1);
		h2 = reverse(h2);
		nh = reverse(nh.next);
		while (nh.data == 0) {
			nh = nh.next;
		}

		return nh;
	}

	private static Node addition(Node h1, Node h2) {

		h1 = reverse(h1);
		h2 = reverse(h2);

		Node c1 = h1;
		Node c2 = h2;

		Node nh = new Node(-1);
		Node nt = nh;

		int carry = 0;
		while (c1 != null || c2 != null || carry > 0) {

			int sum = carry;
			if (c1 != null) {
				sum += c1.data;
				c1 = c1.next;
			}
			if (c2 != null) {
				sum += c2.data;
				c2 = c2.next;
			}

			carry = 0;
			if (sum >= 10) {
				sum = sum - 10;
				carry = 1;
			}

			Node node = new Node(sum);
			nt.next = node;
			nt = nt.next;
		}

		nh = nh.next;
		nh = reverse(nh);

		h1 = reverse(h1);
		h2 = reverse(h2);

		return nh;
	}

	private static Node reverse(Node head) {

		Node prev = null;
		Node curr = head;

		while (curr != null) {

			Node next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		return prev;
	}

	private static void findIntersectingNode2(Node n1, Node n11) {

		Node c1 = n1;
		while (c1.next != null) {
			c1 = c1.next;
		}
		c1.next = n11;
		Node temp = c1;

		Node slow = n1;
		Node fast = n1;

		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				break;
			}
		}

		slow = n1;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		c1.next = null;
		System.out.println(slow.data);

		traverse(n1);
		traverse(n11);
	}

	private static void findIntersectingNode1(Node h1, Node h2) {

		int size1 = size(h1);
		int size2 = size(h2);

		Node c1 = h1;
		Node c2 = h2;

		if (size1 > size2) {
			int diff = size1 - size2;
			while (diff-- > 0) {
				c1 = c1.next;
			}
		} else if (size2 > size1) {
			int diff = size2 - size1;
			while (diff-- > 0) {
				c2 = c2.next;
			}
		}

		while (c1 != c2) {
			c1 = c1.next;
			c2 = c2.next;
		}

		System.out.println(c1.data);
	}

	private static int size(Node head) {

		Node curr = head;
		int size = 0;
		while (curr != null) {
			curr = curr.next;
			size++;
		}

		return size;
	}

	private static void traverse(Node head) {

		Node curr = head;
		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}

}
