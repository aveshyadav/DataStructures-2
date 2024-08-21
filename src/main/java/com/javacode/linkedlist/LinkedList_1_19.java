package com.javacode.linkedlist;

import java.util.PriorityQueue;

import com.javacode.linkedlist.LinkedListDSA.Node;

public class LinkedList_1_19 {

	public static void main(String[] args) {

		LinkedListDSA list = new LinkedListDSA();
		list.addLast(1);
		list.addLast(2);
		list.addLast(3);
		list.addLast(4);
		list.addLast(5);
		list.addLast(6);
		list.addLast(7);
		list.addLast(8);
		list.addLast(9);
		list.addLast(10);

		traverse(list.head);
//		Node node = reverse(list.head);
//		traverse(node);

//		getMidNode(list.head);
//		isPalindrome(list.head);
//		foldList(list.head);
//		unfoldList(list.head);

		list.addLast(10);
		list.addLast(20);
		list.addLast(30);
		list.addLast(40);
		list.addLast(50);
		list.addLast(60);
		list.addLast(70);

		LinkedListDSA list2 = new LinkedListDSA();
		list2.addLast(1);
		list2.addLast(2);
		list2.addLast(31);
		list2.addLast(45);
		list2.addLast(56);
		list2.addLast(71);

		LinkedListDSA list3 = new LinkedListDSA();
		list3.addLast(-1);
		list3.addLast(100);

//		Node ans = mergeTwoSortedList(list.head, list2.head);
//		traverse(ans);

		Node nodes[] = new Node[3];
		nodes[0] = list.head;
		nodes[1] = list2.head;
		nodes[2] = list3.head;

//		Node node = mergeKSortedList(nodes, 0, nodes.length - 1);
//		traverse(node);
//		mergeKSortedListPQ(nodes);

//		Node node = mergeSort(list.head);
//		traverse(node);

//		evenOddList(list.head);
//		reverseInKGroups(list.head, 3);

		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		Node n9 = new Node(9);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n7;
		n7.next = n8;
		n8.next = n9;
		n9.next = n6;

		detectCycle(n1);
		findCycleStartNode(n1);
	}

	private static void findCycleStartNode(Node head) {

		Node slow = head;
		Node fast = head;

		while (fast != null && fast.next != null) {

			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				break;
			}
		}

		slow = head;
		Node prev = head;

		while (slow != fast) {

			prev = fast;
			slow = slow.next;
			fast = fast.next;
		}

		System.out.println("Cycle Node: " + slow.data + " Prev Node: " + prev.data);
		prev.next = null;
		traverse(head);
	}

	private static void detectCycle(Node n1) {

		Node slow = n1;
		Node fast = n1;

		boolean cycle = false;
		while (fast.next != null && fast.next.next != null) {

			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast) {
				cycle = true;
				break;
			}
		}

		System.out.println("Is Cycle Present: " + cycle);

	}

	private static void reverseInKGroups(Node head, int k) {

		int size = size(head);
		int factor = size / k;

		Node nh = null;
		Node nt = null;

		Node ph = null;
		Node pt = null;

		while (factor-- > 0) {

			for (int i = 0; i < k; i++) {

				Node node = head;
				head = head.next;

				if (ph == null) {
					ph = pt = node;
				} else {
					node.next = ph;
					ph = node;
				}
			}

			if (nh == null) {
				nh = ph;
				nt = pt;
			} else {
				nt.next = ph;
				nt = pt;
			}

			ph = pt = null;
		}

		if (size % k != 0) {
			nt.next = head;
		}

		traverse(nh);
	}

	private static int size(Node head) {

		int size = 0;
		Node curr = head;
		while (curr != null) {
			curr = curr.next;
			size++;
		}

		return size;
	}

	private static void evenOddList(Node head) {

		Node evenHead = new Node(-1);
		Node evenTail = evenHead;
		Node oddHead = new Node(-1);
		Node oddTail = oddHead;

		Node curr = head;

		while (curr != null) {

			if (curr.data % 2 == 0) {

				evenTail.next = curr;
				evenTail = evenTail.next;
			} else {

				oddTail.next = curr;
				oddTail = oddTail.next;
			}
			curr = curr.next;
		}

		evenTail.next = oddHead.next;
		oddTail.next = null;

		traverse(evenHead.next);
	}

	private static Node mergeSort(Node head) {

		if (head == null || head.next == null) {
			return head;
		}

		Node mid = getMidNode(head);
		Node next = mid.next;
		mid.next = null;

		Node left = mergeSort(head);
		Node right = mergeSort(next);

		Node node = mergeTwoSortedList(left, right);
		return node;
	}

	private static void mergeKSortedListPQ(Node[] nodes) {

		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.data - b.data));
		for (Node node : nodes) {
			pq.add(node);
		}

		Node head = new Node(-1);
		Node curr = head;

		while (pq.size() > 0) {

			Node node = pq.remove();
			curr.next = node;
			curr = curr.next;

			if (node.next != null) {
				pq.add(node.next);
			}
		}

		traverse(head.next);
	}

	private static Node mergeKSortedList(Node[] nodes, int i, int j) {

		if (i > j) {
			return null;
		}
		if (i == j) {
			return nodes[i];
		}

		int mid = (i + j) / 2;
		Node left = mergeKSortedList(nodes, i, mid);
		Node right = mergeKSortedList(nodes, mid + 1, j);

		Node node = mergeTwoSortedList(left, right);

		return node;
	}

	private static Node mergeTwoSortedList(Node l1, Node l2) {

		Node head = new Node(-1);
		Node temp = head;
		while (l1 != null && l2 != null) {

			if (l1.data < l2.data) {
				head.next = l1;
				l1 = l1.next;
			} else {
				head.next = l2;
				l2 = l2.next;
			}
			head = head.next;
		}

		while (l1 != null) {
			head.next = l1;
			l1 = l1.next;
			head = head.next;
		}
		while (l2 != null) {
			head.next = l2;
			l2 = l2.next;
			head = head.next;
		}

		return temp.next;
	}

	private static void unfoldList(Node head) {

		Node curr1 = head;
		Node right = head.next;
		Node curr2 = head.next;

		while (curr2 != null) {

			curr1.next = curr2.next;
			if (curr1.next != null) {
				curr2.next = curr1.next.next;
			} else {
				curr2.next = null;
			}

			if (curr1.next != null) {
				curr1 = curr1.next;
			}
			curr2 = curr2.next;
		}

		right = reverse(right);
		curr1.next = right;
		traverse(head);
	}

	private static void foldList(Node head) {

		Node mid = getMidNode(head);
		Node right = reverse(mid.next);
		mid.next = null;

		Node curr1 = head;
		Node curr2 = right;

		while (curr2 != null) {

			Node next1 = curr1.next;
			Node next2 = curr2.next;

			curr1.next = curr2;
			curr2.next = next1;

			curr1 = next1;
			curr2 = next2;
		}
		traverse(head);
	}

	private static void isPalindrome(Node head) {

		Node mid = getMidNode(head);
		Node right = reverse(mid.next);
		mid.next = null;

		boolean flag = true;
		Node left = head;
		while (left != null && right != null) {
			if (left.data != right.data) {
				flag = false;
			}
			left = left.next;
			right = right.next;
		}

		System.out.println("Is Palindrome: " + flag);
	}

	private static Node getMidNode(Node head) {

		Node slow = head;
		Node fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		System.out.println("Mid Node: " + slow.data);
		return slow;
	}

	private static Node reverse(Node head) {

		Node curr = head;
		Node prev = null;

		while (curr != null) {

			Node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}

		return prev;
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
