package com.javacode.stack;

import java.util.Stack;

public class MinStack1 {

	public static void main(String[] args) {

		MinStack1 stack = new MinStack1();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(5);
		stack.push(8);
		stack.push(12);
		stack.push(3);
		stack.push(16);
		stack.push(2);
		stack.push(14);

		stack.display();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
	}

	private Stack<Integer> mainStack = new Stack<>();
	private Stack<Integer> minStack = new Stack<>();

	private void push(int val) {

		mainStack.push(val);
		if (minStack.isEmpty()) {
			minStack.push(val);
		} else {
			if (minStack.peek() >= val) {
				minStack.push(val);
			}
		}
	}

	private void pop() {

		if (mainStack.size() > 0) {
			int val = mainStack.pop();
			if (minStack.peek() == val) {
				minStack.pop();
			}
		} else {
			System.out.println("Stack is Empty");
		}
	}

	private int min() {

		if (mainStack.size() > 0) {
			return minStack.peek();
		}
		return -1;
	}

	private void display() {

		System.out.println(mainStack);
	}
}
