package com.javacode.stack;

import java.util.Stack;

public class MinStack2 {

	public static void main(String[] args) {

		MinStack2 stack = new MinStack2();
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
		System.out.println(stack.min() + " " + stack.pop());
		System.out.println(stack.min() + " " + stack.pop());
		System.out.println(stack.min() + " " + stack.pop());
		System.out.println(stack.min() + " " + stack.pop());
		System.out.println(stack.min() + " " + stack.pop());
		System.out.println(stack.min() + " " + stack.pop());

		System.out.println(stack.min() + " " + stack.pop());
		System.out.println(stack.min() + " " + stack.pop());
		System.out.println(stack.min() + " " + stack.pop());
		System.out.println(stack.min() + " " + stack.pop());
	}

	private Stack<Integer> stack;
	private int min;

	public MinStack2() {
		this.min = Integer.MAX_VALUE;
		this.stack = new Stack<>();
	}

	public void push(int val) {

		if (stack.size() == 0) {
			stack.push(val);
			min = val;
		} else {
			if (val < min) {
				int nv = val + (val - min);
				min = val;
				stack.push(nv);
			} else {
				stack.push(val);
			}
		}
	}

	public int pop() {

		if (stack.size() == 0) {
			System.out.println("Stack is Empty");
			return -1;
		} else {

			int top = stack.pop();
			if (top < min) {
				int temp = min;
				min = (2 * min) - top;
				return temp;
			}
			return top;
		}
	}

	public int min() {
		return min;
	}

	public void display() {
		System.out.println(stack);
	}
}
