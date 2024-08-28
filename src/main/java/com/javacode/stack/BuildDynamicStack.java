package com.javacode.stack;

public class BuildDynamicStack {

	int stack[];
	int tos;

	public BuildDynamicStack(int n) {

		this.stack = new int[n];
		this.tos = -1;
	}

	public void push(int d) {

		if (tos + 1 == stack.length) {

			int temp[] = new int[2 * stack.length];
			for (int i = 0; i < stack.length; i++) {
				temp[i] = stack[i];
			}
			stack = temp;
			System.out.println("Stack Resized");
		}

		tos++;
		stack[tos] = d;
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
