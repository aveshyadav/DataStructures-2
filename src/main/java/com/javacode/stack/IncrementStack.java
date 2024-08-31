package com.javacode.stack;

public class IncrementStack {

	public static void main(String[] args) {

		IncrementStack stk = new IncrementStack(3); // Stack is Empty []
		stk.push(1); // stack becomes [1]
		stk.push(2); // stack becomes [1, 2]
		System.out.println(stk.pop()); // return 2 --> Return top of the stack 2, stack becomes [1]
		stk.push(2); // stack becomes [1, 2]
		stk.push(3); // stack becomes [1, 2, 3]
		stk.push(4); // stack still [1, 2, 3], Do not add another elements as size is 4
		stk.increment(5, 100); // stack becomes [101, 102, 103]
		stk.increment(2, 100); // stack becomes [201, 202, 103]
		System.out.println(stk.pop()); // return 103 --> Return top of the stack 103, stack becomes [201, 202]
		System.out.println(stk.pop()); // return 202 --> Return top of the stack 202, stack becomes [201]
		System.out.println(stk.pop()); // return 201 --> Return top of the stack 201, stack becomes []
		System.out.println(stk.pop()); // return -1 --> Stack is empty return -1.
	}

	private int top;
	private int arr[];
	private int inc[];

	public IncrementStack(int n) {

		this.top = -1;
		this.arr = new int[n];
		this.inc = new int[n];
	}

	private void push(int data) {

		if (top == arr.length - 1) {
			System.out.println("Stack Overflow");
		} else {
			top++;
			arr[top] = data;
		}
	}

	private int pop() {

		if (top == -1) {
			System.out.println("Stack Underflow");
			return -1;
		} else {
			int data = arr[top];
			data += inc[top];
			top--;
			if (top >= 0) {
				inc[top] += inc[top + 1];
			}
			inc[top + 1] = 0;
			return data;
		}
	}

	private void increment(int id, int val) {
		if (id > arr.length) {
			inc[arr.length - 1] = val;
		} else {
			inc[id - 1] += val;
		}
	}

}
