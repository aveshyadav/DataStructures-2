package com.javacode.stack;

public class BuildNormalStack {

	int stack[];
	int tos;

	public BuildNormalStack(int n) {
		this.stack = new int[n];
		this.tos = -1;
	}

	public void push(int d) {

		if (tos + 1 == stack.length) {
			System.out.println("Stack Overflow");
		} else {
			tos++;
			stack[tos] = d;
		}
	}

	public int pop() {

		int d = -1;
		if (tos >= 0) {
			d = stack[tos];
			tos--;
		} else {
			System.out.println("Stack is Empty");
		}
		return d;
	}

	public int top() {
		
		int d = -1;
		if (tos >= 0) {
			d = stack[tos];
		} else {
			System.out.println("Stack is Empty");
		}
		return d;
		
	}

	public int size() {
		return tos + 1;
	}

	public void display() {

		for (int i = tos; i >= 0; i--) {
			System.out.print(stack[i] + " ");
		}
		System.out.println();
	}
}
