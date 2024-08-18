package com.javacode.linkedlist;

public class LinkedListDSA {

	Node head;
	Node tail;
	int size;

	public void display() {

		Node curr = head;

		while (curr != null) {
			System.out.print(curr.data + " ");
			curr = curr.next;
		}
		System.out.println();
	}

	public void reverseRecursivePR() {

		reverseRecursivePRHelper(head);

		Node temp = this.head;
		this.head = this.tail;
		this.tail = temp;
		this.tail.next = null;
	}

	public void foldList() {

		this.left = head;
		foldListHelper(head, 0);

	}

	public void foldListHelper(Node head, int size) {

		if (head == null) {
			return;
		}

		foldListHelper(head.next, size + 1);

		if (size > this.size / 2) {
			Node temp = left.next;
			left.next = head;
			head.next = temp;
			left = temp;
		} else if (size == this.size / 2) {
			this.tail = head;
			head.next = null;
		}
	}

	public void isPalindrome() {

		this.left = head;
		System.out.println(isPalindromeHelper(head));
	}

	public boolean isPalindromeHelper(Node curr) {

		if (curr == null) {
			return true;
		}

		boolean flag = isPalindromeHelper(curr.next);

		if (this.left.data == curr.data && flag == true) {
			left = left.next;
			return true;
		}
		return false;
	}

	Node left = null;

	public void reverseRecursiveData() {

		this.left = head;
		reverseRecursiveDataHelper(head, 0);
	}

	public void reverseRecursiveDataHelper(Node curr, int size) {

		if (curr == null) {
			return;
		}

		reverseRecursiveDataHelper(curr.next, size + 1);
		if (size > this.size / 2) {
			int temp = left.data;
			left.data = curr.data;
			curr.data = temp;
			left = left.next;
		}
	}

	public void reverseRecursivePRHelper(Node curr) {

		if (curr == null) {
			return;
		}
		reverseRecursivePRHelper(curr.next);

		if (curr != tail) {
			curr.next.next = curr;
		}
	}

	public void displayReverse(Node curr) {

		if (curr == null) {
			return;
		}

		displayReverse(curr.next);
		System.out.print(curr.data + " ");
	}


	public LinkedListDSA oddEvenList() {

		Node evenHead = null;
		Node oddHead = null;
		Node evenTail = null;
		Node oddTail = null;

		Node curr = head;
		while (curr != null) {

			if (curr.data % 2 == 0) {
				if (evenHead == null) {
					evenHead = evenTail = curr;
				} else {
					evenTail.next = curr;
					evenTail = evenTail.next;
				}
			} else {
				if (oddHead == null) {
					oddHead = oddTail = curr;
				} else {
					oddTail.next = curr;
					oddTail = oddTail.next;
				}
			}
			curr = curr.next;
		}

		oddTail.next = evenHead;
		evenTail.next = null;
		LinkedListDSA list = new LinkedListDSA();
		list.head = oddHead;
		list.tail = evenTail;

		return list;
	}

	public void removeDuplicates() {

		Node curr = head.next;
		Node prev = head;

		while (curr != null) {

			if (prev.data == curr.data) {
				prev.next = curr.next;
			} else {
				prev = prev.next;
			}
			curr = curr.next;
		}
	}

	public LinkedListDSA mergeSort(Node head, Node tail) {

		if (head == tail) {
			LinkedListDSA list = new LinkedListDSA();
			list.addFirst(head.data);
			return list;
		}

		Node mid = getMidNode(head, tail);

		LinkedListDSA l1 = mergeSort(head, mid);
		LinkedListDSA l2 = mergeSort(mid.next, tail);

		LinkedListDSA list = mergeSortedList(l1, l2);
		return list;
	}

	public Node getMidNode(Node head, Node tail) {

		Node slow = head;
		Node fast = head;

		while (fast != tail && fast.next != tail) {
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	public LinkedListDSA mergeSortedList(LinkedListDSA list1, LinkedListDSA list2) {

		LinkedListDSA curr = new LinkedListDSA();

		Node l1 = list1.head;
		Node l2 = list2.head;

		while (l1 != null && l2 != null) {

			if (l1.data < l2.data) {
				curr.addLast(l1.data);
				l1 = l1.next;
			} else {
				curr.addLast(l2.data);
				l2 = l2.next;
			}
		}

		while (l1 != null) {
			curr.addLast(l1.data);
			l1 = l1.next;
		}

		while (l2 != null) {
			curr.addLast(l2.data);
			l2 = l2.next;
		}

		return curr;
	}

	public int getMidElement() {

		Node slow = head;
		Node fast = head;

		while (fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;
		}

		return slow.data;
	}

	public int getKthNodeFromEnd(int k) {

		Node slow = head;
		Node fast = head;
		while (k-- >= 0) {
			fast = fast.next;
		}

		while (fast != null) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow.data;
	}

	public void removeAt(int i) {

		Node node = getNodeAt(i - 1);
		node.next = node.next.next;
		size--;
	}

	public void reversePI() {

		Node prev = null;
		Node curr = head;

		while (curr != null) {

			Node temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}

		head = prev;
	}

	public void reverseDI() {

		int i = 0;
		int j = size - 1;

		while (i < j) {

			Node t1 = getNodeAt(i);
			Node t2 = getNodeAt(j);

			int temp = t1.data;
			t1.data = t2.data;
			t2.data = temp;

			i++;
			j--;
		}
	}

	public Node getNodeAt(int i) {

		Node curr = head;
		while (i-- > 0) {
			curr = curr.next;
		}
		return curr;
	}

	public void removeLast() {

		if (size == 0) {
			System.out.println("Invalid Input");
		} else {
			if (size == 1) {
				removeFirst();
			} else {

				Node curr = head;
				while (curr.next.data != tail.data) {
					curr = curr.next;
				}
				curr.next = null;
				tail = curr;
				size--;
			}
		}
	}

	public void addDataAtIndex(int d, int i) {

		if (i > size || i < 0) {
			System.out.println("Invalid Input");
		} else {

			if (i == 0) {
				addLast(d);
			} else if (i == size) {
				addLast(d);
			} else {
				Node curr = head;
				while (i-- > 1) {
					curr = curr.next;
				}

				Node node = new Node(d);
				node.next = curr.next;
				curr.next = node;
			}
		}
	}

	public int getAtIndex(int i) {

		if (i >= size || i < 0) {
			return -1;
		} else {

			Node curr = head;
			while (i-- > 0) {
				curr = curr.next;
			}
			return curr.data;
		}
	}

	public void addFirst(int data) {

		if (size == 0) {
			addLast(data);
		} else {
			Node node = new Node(data);
			node.next = head;
			head = node;
			size++;
		}
	}

	public int getFirst() {
		if (size == 0) {
			return -1;
		} else {
			return head.data;
		}
	}

	public int getLast() {
		if (size == 0) {
			return -1;
		} else {
			return tail.data;
		}
	}

	public void removeFirst() {

		if (size == 0) {
			System.out.println("List is Empty");
		} else if (size == 1) {
			head = tail = null;
			size--;
		} else {
			head = head.next;
			size--;
		}
	}

	public void size() {
		System.out.println(size);
	}

	public void addLast(int d) {

		Node node = new Node(d);

		if (head == null) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
		size++;
	}

	public static class Node {

		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

}
