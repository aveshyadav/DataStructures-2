package com.javacode.stack;

import java.util.Stack;

public class Stack_2_27_35 {

	public static void main(String[] args) {

//		int arr[] = { 5, 1, 4, 3, 4, 3, 5, 9, 6 };
//		int arr[] = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
//		mostCompetitive(arr, 4);

		String str = "abcbc";
		isValidAfterInsertion(str);
	}

	private static void isValidAfterInsertion(String str) {

		boolean flag = true;
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (ch == 'c') {

				if (stack.size() > 0 && stack.peek() == 'b') {
					
					stack.pop();
					if (stack.size() > 0 && stack.peek() == 'a') {
						stack.peek();
					} else {
						flag = false;
						break;
					}
				} else {
					flag = false;
					break;
				}

			} else {
				stack.push(ch);
			}
		}

		flag = flag && stack.size() == 0;
		System.out.println(stack);

		System.out.println("Is Valid: " + flag);
	}

	private static void mostCompetitive(int[] arr, int k) {

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < arr.length; i++) {
			while (stack.size() > 0 && stack.peek() > arr[i] && (stack.size() + arr.length - i) > k) {
				stack.pop();
			}
			stack.push(arr[i]);
		}

		while (stack.size() > k) {
			stack.pop();
		}
		System.out.println(stack);
	}

}
