package com.javacode.stack;

public class BuildNormalQueue {

	int data[];
	int front;
	int size;

	public BuildNormalQueue(int n) {

		this.data = new int[n];
		this.front = 0;
		this.size = 0;
	}

	public void add(int val) {

		if (size == data.length) {
			System.out.println("Queue overflow");
		} else {
			int idx = (front + size) % data.length;
			data[idx] = val;
			size++;
		}

	}

	public int remove() {

		if (size == 0) {
			System.out.println("Queue is Empty");
			return -1;
		} else {

			int d = data[front];
			front = (front + 1) % data.length;
			size--;
			return d;
		}
	}

	public int peek() {
		if (size == 0) {
			System.out.println("Queue is Empty");
			return -1;
		} else {
			return data[front];
		}
	}

	public int size() {
		return size;
	}

	public void display() {

		for (int i = 0; i < data.length; i++) {
			int idx = (front + i) % data.length;
			System.out.print(data[idx] + " ");
		}
		System.out.println();
	}
}
