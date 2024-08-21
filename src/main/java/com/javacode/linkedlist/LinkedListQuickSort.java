package com.javacode.linkedlist;

import com.javacode.linkedlist.LinkedListDSA.Node;

public class LinkedListQuickSort {

	public static void main(String[] args) {

		LinkedListDSA list = new LinkedListDSA();
//		list.addLast(0);
//		list.addLast(0);
//		list.addLast(1);
//		list.addLast(2);// 2
//		list.addLast(0);
//		list.addLast(2);// 2
//		list.addLast(0);
//		list.addLast(2);// 2
//		list.addLast(0);
//		list.addLast(1);
//		list.addLast(1);
//		list.addLast(1);

//		traverse(list.head);
//		Node ans = segregate01(list.head);
//		traverse(ans);

//		Node ans = segregate012(list.head);
//		traverse(ans);

		list.addLast(1);
		list.addLast(5);
		list.addLast(2);
		list.addLast(9);
		list.addLast(5);
		list.addLast(14);
		list.addLast(11);
		list.addLast(1);
		list.addLast(10);
		list.addLast(10);
		list.addLast(1);
		list.addLast(3);

		traverse(list.head);
//		Node ans = segregateNodeOverLastIndex(list.head);
//		traverse(ans);

//		Node ans = segregateNodeOverPivot(list.head, 2);
//		traverse(ans);

		Node sorted = quickSort(list.head);
		traverse(sorted);
	}

	private static Node quickSort(Node head) {

		if (head == null || head.next == null) {
			return head;
		}

		Node nodes[] = segregateQuickSort(head);

		Node left = quickSort(nodes[0]);
		Node right = quickSort(nodes[2]);

		Node pivot = nodes[1];
		pivot.next = right;

		Node node = mergeTwoSortedList(left, pivot);

		return node;
	}

	private static Node mergeTwoSortedList(Node h1, Node h2) {

		Node nh = new Node(-1);
		Node nt = nh;

		Node c1 = h1;
		Node c2 = h2;

		while (c1 != null && c2 != null) {

			if (c1.data < c2.data) {
				nt.next = c1;
				c1 = c1.next;
			} else {
				nt.next = c2;
				c2 = c2.next;
			}
			nt = nt.next;
		}
		if (c1 != null) {
			nt.next = c1;
		}
		if (c2 != null) {
			nt.next = c2;
		}

		return nh.next;
	}

	private static Node[] segregateQuickSort(Node head) {

		Node tail = getTail(head);

		Node sh = new Node(-1);
		Node st = sh;

		Node lh = new Node(-1);
		Node lt = lh;

		Node curr = head;
		while (curr != null && curr != tail) {

			if (curr.data <= tail.data) {
				st.next = curr;
				st = st.next;
			} else {
				lt.next = curr;
				lt = lt.next;
			}
			curr = curr.next;
		}

		st.next = null;
		lt.next = null;
		tail.next = null;

		Node nodes[] = new Node[3];
		nodes[0] = sh.next;
		nodes[1] = tail;
		nodes[2] = lh.next;

		return nodes;
	}

	private static Node segregateNodeOverPivot(Node head, int n) {

		Node sh = new Node(-1);
		Node st = sh;

		Node lh = new Node(-1);
		Node lt = lh;

		Node pivot = null;
		Node curr = head;
		while (curr != null) {

			if (curr.data < n) {
				st.next = curr;
				st = st.next;
			} else if (curr.data > n) {
				lt.next = curr;
				lt = lt.next;
			} else {
				pivot = curr;
			}
			curr = curr.next;
		}
		st.next = pivot;
		pivot.next = lh.next;
		lt.next = null;

		return sh.next;

	}

	private static Node segregateNodeOverLastIndex(Node head) {

		Node tail = getTail(head);

		Node sh = new Node(-1);
		Node st = sh;

		Node lh = new Node(-1);
		Node lt = lh;

		Node curr = head;
		while (curr != null && curr != tail) {

			if (curr.data <= tail.data) {
				st.next = curr;
				st = st.next;
			} else {
				lt.next = curr;
				lt = lt.next;
			}
			curr = curr.next;
		}
		st.next = tail;
		tail.next = lh.next;
		lt.next = null;

		return sh.next;
	}

	private static Node getTail(Node head) {

		Node curr = head;
		while (curr.next != null) {
			curr = curr.next;
		}
		return curr;
	}

	private static Node segregate012(Node head) {

		Node oh = new Node(-1);
		Node ot = oh;

		Node zh = new Node(-1);
		Node zt = zh;

		Node th = new Node(-1);
		Node tt = th;

		Node curr = head;

		while (curr != null) {

			if (curr.data == 0) {
				zt.next = curr;
				zt = zt.next;
			} else if (curr.data == 1) {
				ot.next = curr;
				ot = ot.next;
			} else {
				tt.next = curr;
				tt = tt.next;
			}
			curr = curr.next;
		}
		zt.next = oh.next;
		ot.next = th.next;
		tt.next = null;

		return zh.next;
	}

	private static Node segregate01(Node head) {

		Node oh = new Node(-1);
		Node ot = oh;

		Node zh = new Node(-1);
		Node zt = zh;

		Node curr = head;
		while (curr != null) {

			if (curr.data == 0) {
				zt.next = curr;
				zt = zt.next;
			} else {
				ot.next = curr;
				ot = ot.next;
			}
			curr = curr.next;
		}
		zt.next = oh.next;
		ot.next = null;

		return zh.next;
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
