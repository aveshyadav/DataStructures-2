package com.javacode.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Stack_2_15_26 {

	public static void main(String[] args) {

//		List<String> logs = Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6");
//		List<String> logs = Arrays.asList("0:start:0", "0:start:2", "0:end:5", "0:start:6", "0:end:6", "0:end:7");
//		List<String> logs = Arrays.asList("0:start:0", "0:start:2", "0:end:5", "1:start:6", "1:end:6", "0:end:7");
//		exclusiveTime(2, logs);

//		int arr[] = { 1, 2, 3, 4 };
//		int arr[] = { 3, 1, 4, 2 };
//		int arr[] = { -1, 3, 2, 0 };
//		int arr[] = { 5, 7, 6, 10, 4, 9, 3, 2 };
//		find132pattern(arr);

//		int arr[] = { -1, -2, 1, 3, 1, 2, -3, 3 };
//		asteroidCollision(arr);

//		String str = "1432219";
//		removeKdigits(str, 3);

//		String str = "cbacdcbc";
//		removeDuplicateLetters(str);

//		int arr[] = { 4, 2, 0, 3, 2, 5 };
//		int arr[] = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
//		trappingRainWater(arr);
//		trappingRainWater2(arr);

//		int arr[][] = { { 1, 4, 3, 1, 3, 2 }, { 3, 2, 1, 3, 2, 4 }, { 2, 3, 3, 2, 3, 1 } };
//		int arr[][] = { { 3, 3, 3, 3, 3 }, { 3, 2, 2, 2, 3 }, { 3, 2, 1, 2, 3 }, { 3, 2, 2, 2, 3 }, { 3, 3, 3, 3, 3 } };
//		trappingRainWater2d(arr);

//		int arr[] = { 2, 4, 2, 1, 3 };
//		validSubarrays(arr);

//		String str = "(1+(4+5+2)-3)+(6+8)";
//		String str = "3-(1+(4+5+2)-3)+(6+8)";
//		basicCalculator1(str);

//		String str = "3+5/ 2 ";
//		String str = "31+22 *4 /3 -2/2 -1*3";
//		basicCalculator2(str);

		String str = "2*(5+5*2)/3+(6/2+8)";
		basicCalculator3(str);
	}

	private static void basicCalculator3(String str) {

		Stack<Pair3> pStack = new Stack<>();
		Stack<Integer> stack = new Stack<>();
		char sign = '+';

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {

				int val = 0;
				while (i < str.length() && Character.isDigit(str.charAt(i))) {
					val = (val * 10) + (str.charAt(i) - '0');
					i++;
				}

				calculate(stack, sign, val);
				sign = '+';
				i--;
			} else {

				if (ch == '(') {
					pStack.push(new Pair3(stack, sign));
					stack = new Stack<>();
					sign = '+';
				} else if (ch == ')') {

					int val = 0;
					while (stack.size() > 0) {
						val += stack.pop();
					}

					Pair3 mp = pStack.pop();
					stack = mp.stack;
					sign = mp.sign;

					calculate(stack, sign, val);
					sign = '+';

				} else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
					sign = ch;
				}
			}
		}

		int sum = 0;
		while (stack.size() > 0) {
			sum += stack.pop();
		}

		System.out.println("Ans: " + sum);
	}

	private static void calculate(Stack<Integer> stack, char sign, int val) {

		if (sign == '+') {
			stack.push(val);
		} else if (sign == '-') {
			stack.push(-val);
		} else if (sign == '*') {
			val = stack.pop() * val;
			stack.push(val);
		} else if (sign == '/') {
			val = stack.pop() / val;
			stack.push(val);
		}
	}

	private static class Pair3 {

		Stack<Integer> stack;
		char sign;

		public Pair3(Stack<Integer> stack, char sign) {
			this.stack = stack;
			this.sign = sign;
		}
	}

	private static void basicCalculator2(String str) {

		char sign = '+';
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {

				int val = 0;
				while (i < str.length() && Character.isDigit(str.charAt(i))) {
					val = (val * 10) + (str.charAt(i) - '0');
					i++;
				}

				if (sign == '-') {
					val = -val;
				} else if (sign == '*') {
					val = stack.pop() * val;
				} else if (sign == '/') {
					val = stack.pop() / val;
				}

				stack.push(val);
				sign = '+';
				i--;
			} else {

				if (ch == '*' || ch == '/' || ch == '-' || ch == '+') {
					sign = ch;
				}
			}
		}

		int sum = 0;
		while (stack.size() > 0) {
			sum += stack.pop();
		}
		System.out.println("Ans: " + sum);
	}

	private static void basicCalculator1(String str) {

		int sum = 0;
		int sign = 1;
		Stack<Integer> stack = new Stack<>();

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (Character.isDigit(ch)) {

				int val = 0;
				while (i < str.length() && Character.isDigit(str.charAt(i))) {
					val += (val * 10) + (str.charAt(i) - '0');
					i++;
				}

				sum += (val * sign);
				sign = 1;
				i--;
			} else {
				if (ch == '(') {
					stack.push(sum);
					stack.push(sign);
					sum = 0;
					sign = 1;

				} else if (ch == ')') {

					sum = sum * stack.pop();
					sum = sum + stack.pop();

				} else if (ch == '-') {
					sign = sign * -1;
				}
			}
		}

		System.out.println("Ans: " + sum);
	}

	private static void validSubarrays(int[] arr) {

		int res[] = new int[arr.length];
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		res[0] = arr.length;

		for (int i = 0; i < arr.length; i++) {

			res[i] = arr.length;
			while (stack.size() > 0 && arr[stack.peek()] > arr[i]) {
				res[stack.pop()] = i;
			}

			stack.push(i);
		}

		int ans = 0;
		for (int i = 0; i < res.length; i++) {

			ans += res[i] - i;
			System.out.print(res[i] + " ");
		}
		System.out.println();
		System.out.println("Ans: " + ans);
	}

	private static void trappingRainWater2d(int[][] arr) {

		boolean vis[][] = new boolean[arr.length][arr[0].length];
		PriorityQueue<Pair2> pq = new PriorityQueue<>();

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (i == 0 || j == 0 || i == arr.length - 1 || j == arr[0].length - 1) {
					pq.add(new Pair2(i, j, arr[i][j]));
					vis[i][j] = true;
				}
			}
		}

		int water = 0;
		int dirs[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		while (pq.size() > 0) {

			Pair2 mp = pq.remove();
			for (int i = 0; i < dirs.length; i++) {

				int nr = mp.row + dirs[i][0];
				int nc = mp.col + dirs[i][1];

				if (nr >= 0 && nc >= 0 && nr < arr.length && nc < arr[0].length && vis[nr][nc] == false) {

					if (arr[nr][nc] < mp.ht) {
						water += mp.ht - arr[nr][nc];
					}
					vis[nr][nc] = true;
					pq.add(new Pair2(nr, nc, Math.max(arr[nr][nc], mp.ht)));

				}

			}
		}

		System.out.println("Total Water: " + water);
	}

	private static class Pair2 implements Comparable<Pair2> {

		int row;
		int col;
		int ht;

		public Pair2(int row, int col, int ht) {
			this.row = row;
			this.col = col;
			this.ht = ht;
		}

		@Override
		public int compareTo(Pair2 o) {
			return this.ht - o.ht;
		}
	}

	private static void trappingRainWater2(int[] arr) {

		int i = 1;
		int j = arr.length - 2;
		int water = 0;

		int lmax = arr[0];
		int rmax = arr[arr.length - 1];

		while (i <= j) {

			int min = Math.min(lmax, rmax);
			if (lmax == min) {

				int ht = lmax - arr[i];
				if (ht > 0) {
					water += ht;
				}
				lmax = Math.max(lmax, arr[i]);
				i++;
			} else {

				int ht = rmax - arr[j];
				if (ht > 0) {
					water += ht;
				}

				rmax = Math.max(rmax, arr[j]);
				j--;
			}
		}
		System.out.println("Total water: " + water);
	}

	private static void trappingRainWater(int[] arr) {

		int water = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(0);

		for (int i = 1; i < arr.length; i++) {

			while (stack.size() > 0 && arr[stack.peek()] < arr[i]) {

				int curr = stack.pop();
				if (stack.size() > 0) {

					int left = stack.peek();
					int right = i;

					int width = right - left - 1;
					int val = width * (Math.min(arr[right], arr[left]) - arr[curr]);

					water += val;
				}
			}

			stack.push(i);
		}

		System.out.println("Total water: " + water);
	}

	private static void removeDuplicateLetters(String str) {

		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0) + 1);
		}

		Stack<Character> stack = new Stack<>();
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			if (!set.contains(ch)) {
				while (stack.size() > 0 && map.get(stack.peek()) > 0) {
					set.remove(stack.pop());
				}

				map.put(ch, map.get(ch) - 1);
				stack.push(ch);
				set.add(ch);
			}
		}

		System.out.println(stack);
	}

	private static void removeKdigits(String str, int k) {

		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			while (stack.size() > 0 && stack.peek() > ch && k > 0) {
				stack.pop();
				k--;
			}
			stack.push(ch);
		}

		while (k-- > 0) {
			stack.pop();
		}

		System.out.println(stack);
	}

	private static void asteroidCollision(int[] arr) {

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {

			if (arr[i] > 0 || stack.size() == 0) {
				stack.push(arr[i]);
			} else {

				while (stack.size() > 0 && stack.peek() < -arr[i] && stack.peek() > 0) {
					stack.pop();
				}

				if (stack.size() > 0) {
					if (stack.peek() == -arr[i]) {
						stack.pop();
					} else {
						stack.push(arr[i]);
					}
				} else {
					stack.push(arr[i]);
				}
			}
		}

		System.out.println(stack);
	}

	private static void find132pattern(int[] arr) {

		int ps[] = new int[arr.length];
		ps[0] = arr[0];
		for (int i = 1; i < arr.length; i++) {
			arr[i] = Math.min(arr[i], arr[i - 1]);
		}

		Stack<Integer> stack = new Stack<>();
		stack.push(arr[arr.length - 1]);

		boolean flag = false;
		for (int i = arr.length - 2; i >= 0; i--) {

			while (stack.size() > 0 && stack.peek() >= arr[i]) {
				stack.pop();
			}

			int min = ps[i];
			if (stack.size() > 0 && min < arr[i] && min < stack.peek() && arr[i] > stack.peek()) {
				flag = true;
				break;
			}
			stack.push(arr[i]);
		}

		System.out.println("Is 132 pattern: " + flag);
	}

	private static void exclusiveTime(int n, List<String> logs) {

		Stack<Pair> stack = new Stack<>();
		Map<Integer, Integer> map = new HashMap<>();

		for (String str : logs) {

			String arr[] = str.split(":");
			if (arr[1].equals("start")) {
				stack.push(new Pair(Integer.parseInt(arr[0]), Integer.parseInt(arr[2]), 0));
			} else {

				int time = Integer.parseInt(arr[2]) - stack.peek().st + 1;
				Pair mp = stack.pop();
				mp.time += time;
				map.put(mp.id, map.getOrDefault(mp.id, 0) + mp.time);

				if (stack.size() > 0) {
					stack.peek().time -= time;
				}
			}
		}
		System.out.println(map);
	}

	private static class Pair {

		int id;
		int st;
		int time;

		public Pair(int id, int st, int time) {
			this.id = id;
			this.st = st;
			this.time = time;
		}
	}

}
