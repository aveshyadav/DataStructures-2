package com.javacode.recursion1;

import java.util.ArrayList;
import java.util.List;

public class BasicRecursion {

	public static void main(String[] args) {

//		System.out.println("Basic Recursion Level");

//		printDeccreasing(5);
//		printIncreasing(5);
//		printDecreasingIncreasing(5);
//		System.out.println(factorial(5));
//		System.out.println(powerLinear(4, 5));
//		System.out.println(powerLog(4, 5));
//		printZigZag(2);
//		towerOfHanoi(3, "A", "B", "C");

//		int arr[] = { 10, 20, 30, 40, 50 };
//		printArray(arr, 0);
//		printArrayReverse(arr, 0);

//		int arr[] = { 13, 1, 114, 7, 2, 10 };
//		int res = maxInArray(arr, 0);
//		System.out.println("Max in Array: " + res);

//		int arr[] = { 2, 3, 9, 8, 7, 6, 4, 12, 7, 3, 8 };
//		int idx = firstOccurrence(arr, 0, 8);
//		System.out.println(idx);

//		int arr[] = { 2, 3, 9, 8, 7, 6, 4, 12, 7, 3, 8 };
//		int idx = lastOccurrence(arr, 0, 8);
//		System.out.println(idx);

//		int arr[] = { 2, 3, 9, 3, 7, 3, 4, 12, 7, 3, 8 };
//		int res[] = allOccurrence(arr, 0, 3, 0);
//		for (int i = 0; i < res.length; i++) {
//			System.out.println(res[i]);
//		}

//		List<String> list = getSubsequence("abc");
//		System.out.println(list);

//		String codes[] = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
//		List<String> list = getKeyPadCombination(codes, "78");
//		System.out.println(list);

//		List<String> list = getStairsPath(4);
//		System.out.println(list);

//		List<String> list = getMazePath(2, 2, 0, 0);
//		System.out.println(list);

//		List<String> list = getMazePathWithJumps(3, 3, 1, 1);
//		System.out.println(list);

//		printSubsequence("abc", "");

//		String codes[] = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };
//		printKeyPadCombinations(codes, "78", "");

//		printStairPath(4, "");
//		printMazePath(2, 2, 0, 0, "");

//		printMazePathWithJumps(3, 3, 1, 1, "");
//		printPermutation("abc", "");

//		printEncodings("123", "");

//		int arr[] = { 10, 20, 30, 40, 50, 60 };
//		printTargetSumSubset(arr, 0, 60, "");

//		int arr[][] = { { 0, 1, 1 }, { 0, 0, 1 }, { 1, 0, 0 }, { 0, 1, 0 } };
//		boolean vis[][] = new boolean[arr.length][arr[0].length];
//		floodFill(arr, 0, 0, vis, "");

//		int chess[][] = new int[4][4];
//		printNQueens(chess, 0, "");

//		int board[][] = new int[5][5];
//		printKnightsTour(board, 2, 0, 1);
	}

	public static void printKnightsTour(int board[][], int row, int col, int count) {

		if (row < 0 || col < 0 || row >= board.length || col >= board.length || board[row][col] > 0) {
			return;
		}

		if (count == board.length * board.length) {

			board[row][col] = count + 1;
			displayBoard(board);
			board[row][col] = 0;
			return;
		}

		board[row][col] = count;

		printKnightsTour(board, row - 2, col + 1, count + 1);
		printKnightsTour(board, row - 1, col + 2, count + 1);

		printKnightsTour(board, row + 1, col + 2, count + 1);
		printKnightsTour(board, row + 2, col + 1, count + 1);

		printKnightsTour(board, row + 2, col - 1, count + 1);
		printKnightsTour(board, row + 1, col - 2, count + 1);

		printKnightsTour(board, row - 1, col - 2, count + 1);
		printKnightsTour(board, row - 2, col - 1, count + 1);

		board[row][col] = 0;
	}

	public static void displayBoard(int board[][]) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void printNQueens(int chess[][], int row, String ans) {

		if (row == chess.length) {
			System.out.println(ans);
			return;
		}

		for (int i = 0; i < chess.length; i++) {
			if (isSafe(chess, row, i)) {
				chess[row][i] = 1;
				printNQueens(chess, row + 1, ans + row + i + ",");
				chess[row][i] = 0;
			}
		}

	}

	public static boolean isSafe(int chess[][], int row, int col) {

		for (int i = row - 1, j = col; i >= 0; i--) {
			if (chess[i][j] == 1) {
				return false;
			}
		}

		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (chess[i][j] == 1) {
				return false;
			}
		}

		for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
			if (chess[i][j] == 1) {
				return false;
			}
		}

		return true;
	}

	public static void floodFill(int arr[][], int i, int j, boolean vis[][], String ans) {

		if (i < 0 || j < 0 || i >= arr.length || j >= arr[0].length || vis[i][j] == true || arr[i][j] == 1) {
			return;
		}
		if (i == arr.length - 1 && j == arr[0].length - 1) {
			System.out.println(ans);
			return;
		}

		vis[i][j] = true;

		floodFill(arr, i - 1, j, vis, ans + "T");
		floodFill(arr, i + 1, j, vis, ans + "D");
		floodFill(arr, i, j + 1, vis, ans + "R");
		floodFill(arr, i, j - 1, vis, ans + "L");

//		vis[i][j] = false;
	}

	public static void printTargetSumSubset(int arr[], int idx, int target, String ans) {

		if (idx == arr.length) {
			if (target == 0) {
				System.out.println(ans);
			}
			return;
		}

		printTargetSumSubset(arr, idx + 1, target, ans);
		printTargetSumSubset(arr, idx + 1, target - arr[idx], ans + " " + arr[idx]);

	}

	public static void printEncodings(String str, String ans) {

		if (str.length() == 0) {
			System.out.println(ans);
			return;
		}

		int num = str.charAt(0) - '0';
		char ch = (char) ('a' + num - 1);
		printEncodings(str.substring(1), ans + ch);

		if (str.length() > 1) {
			int num2 = Integer.parseInt(str.substring(0, 2));
			if (num2 <= 26) {
				char ch2 = (char) ('a' + num2 - 1);
				printEncodings(str.substring(2), ans + ch2);
			}
		}

	}

	public static void printPermutation(String str, String ans) {

		if (str.length() == 0) {
			System.out.println(ans);
			return;
		}

		for (int i = 0; i < str.length(); i++) {

			char ch = str.charAt(i);
			String newStr = str.substring(0, i) + str.substring(i + 1);
			printPermutation(newStr, ans + ch);
		}
	}

	public static void printMazePathWithJumps(int row, int col, int i, int j, String ans) {

		if (i > row || j > col) {
			return;
		}
		if (i == row && j == col) {
			System.out.println(ans);
			return;
		}

		for (int k = 1; k <= row; k++) {
			printMazePathWithJumps(row, col, i + k, j, ans + "h" + k);
			printMazePathWithJumps(row, col, i, j + k, ans + "v" + k);
			printMazePathWithJumps(row, col, i + k, j + k, ans + "d" + k);
		}

	}

	public static void printMazePath(int row, int col, int i, int j, String ans) {

		if (i > row || j > col) {
			return;
		}
		if (i == row && j == col) {
			System.out.println(ans);
			return;
		}

		printMazePath(row, col, i + 1, j, ans + "h");
		printMazePath(row, col, i, j + 1, ans + "v");
	}

	public static void printStairPath(int n, String ans) {

		if (n < 0) {
			return;
		}
		if (n == 0) {
			System.out.println(ans);
			return;
		}

		printStairPath(n - 1, ans + 1);
		printStairPath(n - 2, ans + 2);
		printStairPath(n - 3, ans + 3);
	}

	public static void printKeyPadCombinations(String codes[], String num, String ans) {

		if (num.length() == 0) {
			System.out.println(ans);
			return;
		}

		int nn = num.charAt(0) - '0';
		String code = codes[nn];

		for (char ch : code.toCharArray()) {
			printKeyPadCombinations(codes, num.substring(1), ans + ch);
		}
	}

	public static void printSubsequence(String str, String ans) {

		if (str.length() == 0) {
			System.out.println(ans);
			return;
		}

		printSubsequence(str.substring(1), ans);
		printSubsequence(str.substring(1), ans + str.charAt(0));
	}

	public static List<String> getMazePathWithJumps(int row, int col, int i, int j) {

		if (i > row || j > col) {
			return new ArrayList<String>();
		}

		if (i == row && j == col) {
			List<String> list = new ArrayList<String>();
			list.add("");
			return list;
		}

		List<String> res = new ArrayList<String>();
		for (int hr = 1; hr <= row; hr++) {

			List<String> r1 = getMazePathWithJumps(row, col, i + hr, j);
			List<String> r2 = getMazePathWithJumps(row, col, i, j + hr);
			List<String> r3 = getMazePathWithJumps(row, col, i + hr, j + hr);

			for (String str : r1) {
				res.add("h" + hr + str);
			}
			for (String str : r2) {
				res.add("v" + hr + str);
			}
			for (String str : r3) {
				res.add("d" + hr + str);
			}

		}
		return res;
	}

	public static List<String> getMazePath(int row, int col, int i, int j) {

		if (i > row || j > col) {
			return new ArrayList<String>();
		}

		if (i == row && j == col) {
			List<String> list = new ArrayList<String>();
			list.add("");
			return list;
		}

		List<String> hr = getMazePath(row, col, i + 1, j);
		List<String> vr = getMazePath(row, col, i, j + 1);

		List<String> res = new ArrayList<String>();

		for (String str : hr) {
			res.add("h" + str);
		}
		for (String str : vr) {
			res.add("v" + str);
		}

		return res;
	}

	public static List<String> getStairsPath(int n) {

		if (n < 0) {
			return new ArrayList<String>();
		} else if (n == 0) {
			List<String> list = new ArrayList<String>();
			list.add("");
			return list;
		}

		List<String> p1 = getStairsPath(n - 1);
		List<String> p2 = getStairsPath(n - 2);
		List<String> p3 = getStairsPath(n - 3);

		List<String> res = new ArrayList<String>();
		for (String str : p1) {
			res.add(1 + str);
		}
		for (String str : p2) {
			res.add(2 + str);
		}
		for (String str : p3) {
			res.add(3 + str);
		}

		return res;
	}

	public static List<String> getKeyPadCombination(String codes[], String num) {

		if (num.length() == 1) {
			List<String> res = new ArrayList<String>();
			int idx = num.charAt(0) - '0';
			String code = codes[idx];
			for (char ch : code.toCharArray()) {
				res.add(ch + "");
			}
			return res;
		}

		List<String> res = getKeyPadCombination(codes, num.substring(1));
		List<String> ans = new ArrayList<String>();
		int idx = num.charAt(0) - '0';

		String code = codes[idx];
		for (char ch : code.toCharArray()) {
			for (String st : res) {
				ans.add(ch + st);
			}
		}

		return ans;
	}

	public static List<String> getSubsequence(String str) {

		if (str.length() == 0) {
			List<String> list = new ArrayList<String>();
			list.add("");
			return list;
		}

		char ch = str.charAt(0);
		List<String> res = getSubsequence(str.substring(1));

		List<String> myRes = new ArrayList<String>();
		for (String st : res) {
			myRes.add(st);
			myRes.add(ch + st);
		}
		return myRes;
	}

	public static int[] allOccurrence(int arr[], int idx, int num, int fsf) {

		if (idx == arr.length) {
			return new int[fsf];
		}

		if (arr[idx] == num) {
			fsf++;
		}

		int res[] = allOccurrence(arr, idx + 1, num, fsf);
		if (arr[idx] == num) {
			res[fsf - 1] = idx;
		}

		return res;
	}

	public static int lastOccurrence(int arr[], int idx, int num) {

		if (idx == arr.length) {
			return -1;
		}

		int res = lastOccurrence(arr, idx + 1, num);
		if (arr[idx] == num) {
			return Math.max(res, idx);
		}

		return res;
	}

	public static int firstOccurrence(int arr[], int idx, int num) {

		if (idx == arr.length) {
			return -1;
		}

		if (arr[idx] == num) {
			return idx;
		}
		return firstOccurrence(arr, idx + 1, num);
	}

	public static int maxInArray(int arr[], int idx) {

		if (idx == arr.length) {
			return Integer.MIN_VALUE;
		}

		int max = maxInArray(arr, idx + 1);
		return Math.max(max, arr[idx]);
	}

	public static void printArrayReverse(int arr[], int index) {

		if (index == arr.length) {
			return;
		}

		printArrayReverse(arr, index + 1);
		System.out.println(arr[index]);
	}

	public static void printArray(int arr[], int index) {

		if (index == arr.length) {
			return;
		}

		System.out.println(arr[index]);
		printArray(arr, index + 1);
	}

	public static void towerOfHanoi(int n, String a, String b, String c) {

		if (n == 0) {
			return;
		}

		towerOfHanoi(n - 1, a, c, b);
		System.out.println("Disk-" + n + " from " + a + " to " + b);
		towerOfHanoi(n - 1, c, b, a);
	}

	public static void printZigZag(int n) {

		if (n == 0) {
			return;
		}

		System.out.println("Pre " + n);
		printZigZag(n - 1);
		System.out.println("In " + n);
		printZigZag(n - 1);
		System.out.println("Post " + n);
	}

	public static int powerLog(int x, int n) {

		if (n == 0) {
			return 1;
		}

		int xn = powerLog(x, n / 2);
		int res = xn * xn;

		if (n % 2 == 1) {
			res = res * x;
		}
		return res;
	}

	public static int powerLinear(int x, int n) {

		if (n == 1) {
			return x;
		}

		int res = powerLinear(x, n - 1);
		return x * res;
	}

	public static int factorial(int n) {

		if (n <= 1) {
			return 1;
		}

		int fact = factorial(n - 1);
		return n * fact;
	}

	public static void printDecreasingIncreasing(int n) {

		if (n == 0) {
			return;
		}
		System.out.println(n);
		printDecreasingIncreasing(n - 1);
		System.out.println(n);
	}

	public static void printIncreasing(int n) {

		if (n == 0) {
			return;
		}

		printIncreasing(n - 1);
		System.out.println(n);
	}

	public static void printDeccreasing(int n) {

		if (n == 0) {
			return;
		}

		System.out.println(n);
		printDeccreasing(n - 1);
	}
}
