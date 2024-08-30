package com.javacode.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Stack_2_1_14 {

	public static void main(String[] args) {

//		int arr[] = { 1, 3, 4, 2 };
//		int query[] = { 4, 1, 2 };
//		nextGreaterElementRight(arr, query);

//		int arr[] = { 2, 5, 9, 3, 1, 12, 6, 8, 7 };
//		nextGreaterElementLeft(arr);
//		nextSmallerElementRight(arr);
//		nextSmallerElementLeft(arr);

//		int arr[] = { 3, 8, 4, 1, 2 };
//		nextGreaterElement2(arr);

//		int arr[] = { 2, 1, 5, 6, 2, 3 };
//		int arr[] = { 6, 2, 5, 4, 5, 1, 6 };
//		int arr[] = { 3, 1, 3, 2, 2 };
//		largestRectangleHistogram(arr);

//		int arr[][] = { { 1, 0, 1, 0, 0 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 0, 0, 1, 0 } };
//		maximalRectangle(arr);

//		int pushed[] = { 1, 2, 3, 4, 5 };
//		int poped[] = { 4, 3, 5, 1, 2 };
//		int pushed[] = { 7, 2, 9, 4, 5 };
//		int poped[] = { 4, 5, 9, 2, 7 };
//		validateStackSequences(pushed, poped);

//		String str = "()))((";
//		minAddToMakeValid(str);

//		String str = "(()())(())";
//		removeOuterParentheses(str);

//		String str = "(()(()))";
//		scoreOfParentheses(str);

//		String str = "(ed(et(oc))el)";
//		reverseParentheses(str);
//		reverseParentheses2(str);

//		String str = "le((e(t(c)o)de)";
//		minRemoveToMakeValid(str);

//		int arr[] = { 100, 80, 60, 70, 60, 75, 85 };
//		onlineStockSpan(arr);
	}

	private static void onlineStockSpan(int[] arr) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		res[0] = 1;

		for (int i = 1; i < arr.length; i++) {

			while (stack.size() > 0 && arr[stack.peek()] < arr[i]) {
				stack.pop();
			}

			if (stack.size() == 0) {
				res[i] = i + 1;
			} else {
				res[i] = i - stack.peek();
			}

			stack.push(i);
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void minRemoveToMakeValid(String str) {

		char arr[] = str.toCharArray();
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			if (arr[i] == '(') {
				stack.push(i);
			} else if (arr[i] == ')') {
				if (stack.size() > 0) {
					stack.pop();
				} else {
					arr[i] = '-';
				}
			}
		}

		while (stack.size() > 0) {
			arr[stack.pop()] = '-';
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != '-') {
				sb.append(arr[i]);
			}
		}
		System.out.println(sb.toString());
	}

	private static void reverseParentheses2(String str) {

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (ch == ')') {

				Queue<Character> queue = new LinkedList<>();
				while (stack.peek() != '(') {
					queue.add(stack.pop());
				}
				stack.pop();

				while (queue.size() > 0) {
					stack.push(queue.remove());
				}
			} else {
				stack.push(ch);
			}
		}

		String ans = "";
		while (stack.size() > 0) {
			ans = stack.pop() + ans;
		}
		System.out.println(ans);
	}

	private static void reverseParentheses(String str) {

		Stack<String> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (ch == ')') {
				StringBuilder sb = new StringBuilder();
				while (!stack.peek().equals("(")) {

					String ss = stack.pop();
					ss = new StringBuilder(ss).reverse().toString();
					sb.append(ss);
				}

				stack.pop();
				stack.push(sb.toString());
			} else {
				stack.push(ch + "");
			}
		}

		System.out.println(stack);
	}

	private static void scoreOfParentheses(String str) {

		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (ch == '(') {
				stack.push(-1);
			} else {

				int sum = 0;
				while (stack.peek() != -1) {
					sum += stack.pop();
				}
				stack.pop();

				sum = sum == 0 ? 1 : (2 * sum);
				stack.push(sum);
			}
		}

		int score = 0;
		while (stack.size() > 0) {
			score += stack.pop();
		}
		System.out.println(score);
	}

	private static void removeOuterParentheses(String str) {

		StringBuilder ans = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (ch == '(') {
				if (stack.size() > 0) {
					ans.append(ch);
				}
				stack.push(ch);
			} else {
				stack.pop();
				if (stack.size() > 0) {
					ans.append(ch);
				}
			}
		}

		System.out.println(ans.toString());
	}

	private static void minAddToMakeValid(String str) {

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (ch == '(') {
				stack.push(ch);
			} else {
				if (stack.size() > 0 && stack.peek() == '(') {
					stack.pop();
				} else {
					stack.push(ch);
				}
			}
		}

		System.out.println("Min add required:  " + stack.size());
	}

	private static void validateStackSequences(int[] pushed, int[] poped) {

		Stack<Integer> stack = new Stack<>();
		int j = 0;
		for (int i = 0; i < pushed.length; i++) {

			stack.push(pushed[i]);
			if (stack.size() != 0) {
				while (j < poped.length && stack.peek() == poped[j]) {
					stack.pop();
					j++;
				}
			}
		}

		System.out.println(stack);
		System.out.println("Is Valid sequence: " + (stack.size() == 0));
	}

	private static void maximalRectangle(int[][] arr) {

		int row[] = arr[0];
		int max = largestRectangleHistogram(row);

		for (int i = 1; i < arr.length; i++) {

			for (int j = 0; j < arr[0].length; j++) {
				row[j] += arr[i][j];
			}
			max = Math.max(max, largestRectangleHistogram(row));
		}
		System.out.println("Max Area2: " + max);
	}

	private static int largestRectangleHistogram(int[] arr) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		int maxArea = 0;

		for (int i = 1; i < arr.length; i++) {

			while (stack.size() > 0 && arr[stack.peek()] > arr[i]) {

				int top = stack.pop();
				int left = stack.size() > 0 ? stack.peek() : -1;
				int right = i - 1;

				int area = (right - left) * arr[top];
				maxArea = Math.max(maxArea, area);
				res[top] = area;
			}

			stack.push(i);
		}

		while (stack.size() > 0) {

			int top = stack.pop();
			int left = stack.size() > 0 ? stack.peek() : -1;
			int right = arr.length - 1;

			int area = (right - left) * arr[top];
			maxArea = Math.max(maxArea, area);
			res[top] = area;
		}

		System.out.println("Max Area: " + maxArea);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(res[i] + " ");
//		}

		return maxArea;
	}

	private static void nextGreaterElement2(int[] arr) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		res[0] = -1;

		for (int i = 1; i < arr.length; i++) {

			res[i] = -1;
			while (stack.size() > 0 && arr[stack.peek()] < arr[i]) {
				res[stack.pop()] = arr[i];
			}
			stack.push(i);
		}

		System.out.println(stack);
		for (int i = 0; i < arr.length; i++) {

			while (stack.size() > 0 && arr[stack.peek()] < arr[i]) {
				res[stack.pop()] = arr[i];
			}
			if (res[i] == -1) {
				stack.push(i);
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void nextSmallerElementLeft(int[] arr) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(arr[0]);
		res[0] = -1;

		for (int i = 1; i < arr.length; i++) {

			while (stack.size() > 0 && stack.peek() > arr[i]) {
				stack.pop();
			}

			if (stack.size() == 0) {
				res[i] = -1;
			} else {
				res[i] = stack.peek();
			}
			stack.push(arr[i]);
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(res[i] + " ");
		}

	}

	private static void nextSmallerElementRight(int[] arr) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		res[0] = -1;

		for (int i = 1; i < arr.length; i++) {

			res[i] = -1;
			while (stack.size() > 0 && arr[stack.peek()] > arr[i]) {
				res[stack.pop()] = arr[i];
			}
			stack.push(i);
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void nextGreaterElementLeft(int[] arr) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(arr[0]);
		res[0] = -1;

		for (int i = 1; i < arr.length; i++) {

			while (stack.size() > 0 && stack.peek() < arr[i]) {
				stack.pop();
			}

			if (stack.size() == 0) {
				res[i] = -1;
			} else {
				res[i] = stack.peek();
			}
			stack.push(arr[i]);
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void nextGreaterElementRight(int[] arr, int[] query) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		res[0] = -1;

		for (int i = 1; i < arr.length; i++) {

			res[i] = -1;
			while (stack.size() > 0 && arr[stack.peek()] < arr[i]) {
				res[stack.pop()] = arr[i];
			}
			stack.push(i);
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

}
