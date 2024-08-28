package com.javacode.stack;

import java.util.Arrays;
import java.util.Stack;

public class Stack_1_0 {

	public static void main(String[] args) {

//		String str = "((a+b)+(c+d))";
//		String str = "(a+b)+((c+d))";
//		System.out.println(isDuplicateBrackets(str));

//		String str = "[(a+b) + {(c+d) * (e/f)]}";
//		String str = "[(a+b) + {(c+d) * (e/f)}]";
//		System.out.println(isBalancedBrackets(str));

//		int arr[] = { 2, 5, 9, 3, 1, 12, 6, 8, 7 };
//		int res[] = nextGreaterElementOnRight(arr);
//		for (int i = 0; i < res.length; i++) {
//			System.out.print(res[i] + " ");
//		}
//		System.out.println();
//
//		res = nextGreaterElementOnRight2(arr);
//		for (int i = 0; i < res.length; i++) {
//			System.out.print(res[i] + " ");
//		}

//		int res[] = spanOfStocks(arr);
//		for (int i = 0; i < res.length; i++) {
//			System.out.print(res[i] + " ");
//		}

//		int arr[] = { 2, 1, 5, 6, 2, 3 };
//		largestHistogram(arr);

//		int arr[] = { 2, 9, 3, 8, 1, 7, 12, 6, 14, 4, 32, 0, 7, 19, 8, 12, 6 };
//		slidingWindowMaximum(arr, 4);

//		int arr[][] = { { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 } };
//		celebrityProblem(arr);

//		String str1 = "((2+((6*4)/8))-3)";
//		infixEvaluation(str1);

//		String str2 = "((2+((6*4)/8))-3)";
//		infixToPrefixPostfixConversion(str2);

//		String str3 = "264*8/+3-";
//		postfixEvaluationConversation(str3);

//		String str4 = "-+2/*6483";
//		prefixEvaluationConversation(str4);

//		int arr[][] = { { 22, 28 }, { 1, 8 }, { 25, 27 }, { 14, 19 }, { 27, 30 }, { 5, 12 } };
//		mergeOverlappingIntervals(arr);

		String str = "ddidddid";
		findSmallestNumberPattern(str);
	}

	private static void findSmallestNumberPattern(String str) {

		int count = 0;
		int num = 1;
		String ans = "";

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (ch == 'd') {
				count++;
			} else {

				String temp = "";
				while (count-- >= 0) {
					temp = num + temp;
					num++;
				}
				ans += temp;
				count = 0;
			}
		}

		String temp = "";
		while (count-- >= 0) {
			temp = num + temp;
			num++;
		}
		ans += temp;

		System.out.println("Ans: " + ans);
	}

	private static void mergeOverlappingIntervals(int[][] arr) {

		Pair parr[] = new Pair[arr.length];
		for (int i = 0; i < arr.length; i++) {
			parr[i] = new Pair(arr[i][0], arr[i][1]);
		}

		Arrays.sort(parr, (a, b) -> a.st - b.st);

		Stack<Pair> stack = new Stack<>();
		stack.push(parr[0]);

		for (int i = 1; i < parr.length; i++) {
			System.out.println(parr[i].st + " " + parr[i].et);

			if (parr[i].st <= stack.peek().et) {
				stack.peek().et = Math.max(stack.peek().et, parr[i].et);
			} else {
				stack.push(parr[i]);
			}
		}

		System.out.println("-------------------------");
		while (stack.size() > 0) {
			Pair mp = stack.pop();
			System.out.println(mp.st + " " + mp.et);
		}
	}

	private static class Pair {
		int st;
		int et;

		public Pair(int st, int et) {
			this.st = st;
			this.et = et;
		}
	}

	private static void prefixEvaluationConversation(String str) {

		Stack<Integer> stack = new Stack<>();
		Stack<String> post = new Stack<>();
		Stack<String> in = new Stack<>();

		for (int i = str.length() - 1; i >= 0; i--) {

			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {
				int c = ch - '0';
				stack.push(c);
				post.push(ch + "");
				in.push(ch + "");
			} else {

				int v1 = stack.pop();
				int v2 = stack.pop();

				int res = operations(v1, v2, ch);
				stack.push(res);

				String s1 = post.pop();
				String s2 = post.pop();
				String ss = s1 + s2 + ch;
				post.push(ss);

				s1 = in.pop();
				s2 = in.pop();
				ss = "(" + s1 + ch + s2 + ")";
				in.push(ss);

			}
		}

		System.out.println(stack.pop());
		System.out.println(post.peek());
		System.out.println(in.peek());
	}

	private static void postfixEvaluationConversation(String str) {

		Stack<Integer> stack = new Stack<>();
		Stack<String> pre = new Stack<>();
		Stack<String> in = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {
				int c = ch - '0';
				stack.push(c);
				pre.push(ch + "");
				in.push(ch + "");
			} else {

				int v2 = stack.pop();
				int v1 = stack.pop();

				int res = operations(v1, v2, ch);
				stack.push(res);

				String s2 = pre.pop();
				String s1 = pre.pop();
				String ss = ch + s1 + s2;
				pre.push(ss);

				s2 = in.pop();
				s1 = in.pop();
				ss = "(" + s1 + ch + s2 + ")";
				in.push(ss);

			}
		}

		System.out.println(stack.pop());
		System.out.println(pre.peek());
		System.out.println(in.peek());
	}

	private static void infixToPrefixPostfixConversion(String str) {

		Stack<Character> stack = new Stack<>();
		Stack<String> pre = new Stack<>();
		Stack<String> post = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);

			if (Character.isDigit(ch)) {
				pre.push(ch + "");
				post.push(ch + "");
			} else {
				if (ch == '(') {
					stack.push(ch);
				} else if (ch == ')') {
					while (stack.peek() != '(') {

						String v2 = pre.pop();
						String v1 = pre.pop();
						char op = stack.pop();

						String preStr = op + v1 + v2;
						pre.push(preStr);

						v2 = post.pop();
						v1 = post.pop();

						String postStr = v1 + v2 + op;
						post.push(postStr);
					}
					stack.pop();
				} else {
					while (stack.size() > 0 && precedence(stack.peek()) >= precedence(ch)) {

						String v2 = pre.pop();
						String v1 = pre.pop();
						char op = stack.pop();

						String preStr = op + v1 + v2;
						pre.push(preStr);

						v2 = post.pop();
						v1 = post.pop();

						String postStr = v1 + v2 + op;
						post.push(postStr);
					}
					stack.push(ch);
				}
			}
		}

		while (stack.size() > 0) {
			String v2 = pre.pop();
			String v1 = pre.pop();
			char op = stack.pop();

			String preStr = op + v1 + v2;
			pre.push(preStr);

			v2 = post.pop();
			v1 = post.pop();

			String postStr = v1 + v2 + op;
			post.push(postStr);
		}

		System.out.println("Prefix: " + pre.peek());
		System.out.println("Postfix: " + post.peek());
	}

	private static void infixEvaluation(String str) {

		Stack<Character> operators = new Stack<>();
		Stack<Integer> operands = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {
				int c = ch - '0';
				operands.push(c);
			} else {

				if (ch == '(') {
					operators.push(ch);
				} else if (ch == ')') {
					while (operators.peek() != '(') {

						int v2 = operands.pop();
						int v1 = operands.pop();

						int val = operations(v1, v2, operators.pop());
						operands.push(val);
					}
					operators.pop();
				} else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
					while (operators.size() > 0 && precedence(operators.peek()) >= precedence(ch)) {
						int v2 = operands.pop();
						int v1 = operands.pop();

						int val = operations(v1, v2, operators.pop());
						operands.push(val);
					}
					operators.push(ch);
				}
			}
		}

		while (operators.size() > 0) {
			int v2 = operands.pop();
			int v1 = operands.pop();

			int val = operations(v1, v2, operators.pop());
			operands.push(val);
		}

		System.out.println(operands.peek());
	}

	private static int operations(int v1, int v2, char op) {

		if (op == '+') {
			return v1 + v2;
		} else if (op == '-') {
			return v1 - v2;
		} else if (op == '*') {
			return v1 * v2;
		} else if (op == '/') {
			return v1 / v2;
		}
		return 0;
	}

	private static int precedence(char op) {

		if (op == '+' || op == '-') {
			return 1;
		} else if (op == '/' || op == '*') {
			return 2;
		}
		return 0;
	}

	private static void celebrityProblem(int[][] arr) {

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			stack.push(i);
		}

		while (stack.size() > 1) {

			int p1 = stack.pop();
			int p2 = stack.pop();

			if (arr[p1][p2] == 0) {
				stack.push(p1);
			}

			if (arr[p2][p1] == 0) {
				stack.push(p2);
			}
		}

		if (stack.size() > 0) {
			System.out.println("Celebrity found: " + stack.peek());
		} else {
			System.out.println("No Celebrity found");
		}
	}

	private static void slidingWindowMaximum(int[] arr, int k) {

		int nge[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		nge[0] = arr.length;

		for (int i = 1; i < arr.length; i++) {

			nge[i] = arr.length;
			while (stack.size() > 0 && arr[stack.peek()] < arr[i]) {
				nge[stack.pop()] = i;
			}
			stack.push(i);
		}

		for (int i = 0; i < nge.length; i++) {
			System.out.print(nge[i] + " ");
		}

		int j = 0;
		int res[] = new int[arr.length];
		for (int i = 0; i <= arr.length - k; i++) {

			if (j < i) {
				j = i;
			}

			while (nge[j] < i + k) {
				j = nge[j];
			}

			res[i] = arr[j];
		}

		System.out.println();
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}

	}

	private static void largestHistogram(int[] arr) {

		int res1[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		res1[0] = -1;

		for (int i = 0; i < arr.length; i++) {

			while (stack.size() > 0 && arr[stack.peek()] >= arr[i]) {
				stack.pop();
			}

			if (stack.size() == 0) {
				res1[i] = -1;
			} else {
				res1[i] = stack.peek();
			}
			stack.push(i);
		}

		int res2[] = new int[arr.length];
		stack = new Stack<>();
		stack.push(0);
		res2[0] = arr.length;

		for (int i = 1; i < arr.length; i++) {

			res2[i] = arr.length;
			while (stack.size() > 0 && arr[stack.peek()] > arr[i]) {
				res2[stack.pop()] = i;
			}
			stack.push(i);
		}

		int max = 0;
		for (int i = 0; i < arr.length; i++) {

			int left = res1[i] + 1;
			int right = res2[i] - 1;

			int area = (right - left + 1) * arr[i];
			System.out.print(area + " ");
			max = Math.max(max, area);
		}

		System.out.println();
		System.out.println("Largest Histogram: " + max);
	}

	private static int[] spanOfStocks(int[] arr) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		res[0] = 1;

		for (int i = 1; i < arr.length; i++) {

			while (stack.size() > 0 && arr[stack.peek()] <= arr[i]) {
				stack.pop();
			}

			if (stack.size() == 0) {
				res[i] = i + 1;
			} else {
				res[i] = i - stack.peek();
			}
			stack.push(i);
		}

		return res;
	}

	private static int[] nextGreaterElementOnRight2(int[] arr) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		res[0] = -1;

		for (int i = 1; i < arr.length; i++) {

			res[i] = -1;
			while (stack.size() > 0 && arr[stack.peek()] < arr[i]) {
				res[stack.peek()] = arr[i];
				stack.pop();
			}

			stack.push(i);
		}

		return res;
	}

	private static int[] nextGreaterElementOnRight(int[] arr) {

		int res[] = new int[arr.length];

		Stack<Integer> stack = new Stack<>();
		res[arr.length - 1] = -1;
		stack.push(arr[arr.length - 1]);

		for (int i = arr.length - 2; i >= 0; i--) {

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

		return res;
	}

	private static boolean isBalancedBrackets(String str) {

		boolean flag = true;
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (ch == '(' || ch == '{' || ch == '[') {
				stack.push(ch);
			} else {

				System.out.println(stack);
				if (ch == ')') {
					if (stack.pop() != '(') {
						flag = false;
					}
				} else if (ch == '}') {
					if (stack.pop() != '{') {
						flag = false;
					}
				} else if (ch == ']') {
					if (stack.pop() != '[') {
						flag = false;
					}
				}
			}
		}

		return flag;
	}

	private static boolean isDuplicateBrackets(String str) {

		boolean flag = false;
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);

			if (ch == ')') {

				int count = 0;
				while (stack.size() > 0 && stack.peek() != '(') {
					count++;
					stack.pop();
				}
				stack.pop();

				if (count == 0) {
					flag = true;
				}
			} else {
				stack.push(ch);
			}
		}

		return flag;
	}

}
