package com.javacode.stack;

import java.util.Stack;

public class MaxStack1 {

	public static void main(String[] args) {

		MaxStack1 ms = new MaxStack1();
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

	private Stack<Integer> stack;
	private Stack<Integer> maxStack;

	public MaxStack1() {
		this.stack = new Stack<>();
		this.maxStack = new Stack<>();
	}

	private void push(int d) {

		stack.push(d);
		if (maxStack.size() == 0 || maxStack.peek() <= d) {
			maxStack.push(d);
		}
	}

	private int pop() {

		int d = stack.pop();
		if (d == maxStack.peek()) {
			maxStack.pop();
		}
		return d;
	}

	private int popMax() {

		int d = maxStack.pop();

		Stack<Integer> temp = new Stack<>();
		while (stack.peek() != d) {
			temp.push(stack.pop());
		}
		stack.pop();

		while (temp.size() > 0) {
			stack.push(temp.pop());
			if (maxStack.size() == 0 || maxStack.peek() <= stack.peek()) {
				maxStack.push(stack.peek());
			}
		}

		return d;
	}

	private int peek() {
		return stack.peek();
	}

	private int peekMax() {
		return maxStack.peek();
	}

}
