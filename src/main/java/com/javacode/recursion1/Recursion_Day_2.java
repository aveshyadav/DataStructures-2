package com.javacode.recursion1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Recursion_Day_2 {

	public static void main(String[] args) {

//		printAbbreviations("pep", "");

//		int n = 4;
//		int chess[][] = new int[n][n];
//		boolean cols[] = new boolean[n];
//		boolean diag[] = new boolean[2 * n - 1];
//		boolean rdiag[] = new boolean[2 * n - 1];
//		nQueensBranchAndBound(chess, 0, cols, diag, rdiag, "");

//		String words[] = { "dog", "cat", "dad", "good" };
//		char arr[] = { 'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o' };
//		int score[] = { 1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
//
//		int farr[] = new int[26];
//		for (int i = 0; i < arr.length; i++) {
//			farr[arr[i] - 'a']++;
//		}
//
//		int sc = maxScoreOfWords(words, 0, farr, score);
//		System.out.println("Score: " + sc);

//		int ans = gameOfExecution(5, 2);
//		System.out.println(ans + 1);

//		for (int i = 1; i < 10; i++) {
//			List<Integer> list = new ArrayList<>();
//			printLexicographically(i, list);
//			System.out.println(list);
//		}

//		int arr[][] = { { 1, 0, 7 }, { 2, 0, 6 }, { 3, 4, 5 }, { 0, 3, 0 }, { 9, 0, 20 } };
//		boolean vis[][] = new boolean[arr.length][arr[0].length];
//
//		int maxGold = Integer.MIN_VALUE;
//		for (int i = 0; i < arr.length; i++) {
//			for (int j = 0; j < arr[0].length; j++) {
//
//				List<Integer> bag = new ArrayList<>();
//				goldMine2(arr, vis, i, j, bag);
//
//				int gold = 0;
//				for (int n : bag) {
//					gold += n;
//				}
//				maxGold = Math.max(maxGold, gold);
//			}
//		}
//		System.out.println("Max Gold: " + maxGold);

//		int board[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
//				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
//				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
//
//		sudokuSolver(board, 0, 0);

//		char arr[][] = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
//				{ '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
//				{ '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
//				{ '+', '-', '-', '-', '-', '-', '+', '+', '+', '+' },
//				{ '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
//				{ '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
//				{ '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
//				{ '+', '+', '-', '-', '-', '-', '-', '-', '+', '+' },
//				{ '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
//				{ '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' } };
//
//		String words[] = { "DELHI", "ICELAND", "ANKARA", "LONDON" };
//		
//		char arr[][] = { { '+', '-', '+' }, 
//				{ '-', '-', '-' },
//				{ '+', '-', '+' } };
//
//		String words[] = { "AND", "ANT" };
//		solveCrossWorldPuzzle(arr, words, 0);

//		String s1 = "send";
//		String s2 = "more";
//		String s3 = "money";
//
//		String uniqueString = "";
//		Map<Character, Integer> map = new HashMap<>();
//		for (int i = 0; i < s1.length(); i++) {
//			char ch = s1.charAt(i);
//			if (!map.containsKey(ch)) {
//				map.put(ch, -1);
//				uniqueString += ch;
//			}
//		}
//		for (int i = 0; i < s2.length(); i++) {
//			char ch = s2.charAt(i);
//			if (!map.containsKey(ch)) {
//				map.put(ch, -1);
//				uniqueString += ch;
//			}
//		}
//		for (int i = 0; i < s3.length(); i++) {
//			char ch = s3.charAt(i);
//			if (!map.containsKey(ch)) {
//				map.put(ch, -1);
//				uniqueString += ch;
//			}
//		}
//
//		boolean vis[] = new boolean[10];
//		solveCryptarithmeticPuzzle(uniqueString, 0, map, vis, s1, s2, s3);

//		int n = 4;
//		boolean visited[] = new boolean[n + 1];
//		friendsPairing(1, n, visited, "");

	}

	public static void friendsPairing(int curr, int max, boolean vis[], String ans) {

		if (curr > max) {
			System.out.println(ans);
			return;
		}

		if (vis[curr] == true) {
			friendsPairing(curr + 1, max, vis, ans);
		} else {
			friendsPairing(curr + 1, max, vis, ans + "(" + curr + ")");
			for (int i = curr + 1; i <= max; i++) {

				if (vis[i] == false) {
					vis[i] = true;
					friendsPairing(curr + 1, max, vis, ans + "(" + curr + "," + i + ")");
					vis[i] = false;
				}
			}
		}
	}

	public static void solveCryptarithmeticPuzzle(String uniqueString, int idx, Map<Character, Integer> map,
			boolean[] vis, String s1, String s2, String s3) {

		if (idx == uniqueString.length()) {

			String c1 = "";
			for (int i = 0; i < s1.length(); i++) {
				c1 += map.get(s1.charAt(i));
			}
			String c2 = "";
			for (int i = 0; i < s2.length(); i++) {
				c2 += map.get(s2.charAt(i));
			}
			String c3 = "";
			for (int i = 0; i < s3.length(); i++) {
				c3 += map.get(s3.charAt(i));
			}
			int a1 = Integer.parseInt(c1);
			int a2 = Integer.parseInt(c2);
			int a3 = Integer.parseInt(c3);

			if (a1 + a2 == a3) {
				char arr[] = uniqueString.toCharArray();
				Arrays.sort(arr);

				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i] + "-" + map.get(arr[i]) + " ");
				}
				System.out.println();
			}

			return;
		}

		char ch = uniqueString.charAt(idx);
		for (int i = 0; i < 10; i++) {

			if (vis[i] == false) {
				vis[i] = true;
				map.put(ch, i);
				solveCryptarithmeticPuzzle(uniqueString, idx + 1, map, vis, s1, s2, s3);
				map.put(ch, -1);
				vis[i] = false;
			}
		}
	}

	public static void solveCrossWorldPuzzle(char arr[][], String words[], int idx) {

		if (idx == words.length) {
			displayBoardChar(arr);
			return;
		}

		String word = words[idx];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] == '-' || arr[i][j] == word.charAt(0)) {

					if (canPlaceHorizontally(arr, i, j, word)) {

						boolean vis[] = placeHorizontally(arr, i, j, word);
						solveCrossWorldPuzzle(arr, words, idx + 1);
						unPlaceHorizontally(arr, i, j, vis);
					}

					if (canPlaceVertically(arr, i, j, word)) {

						boolean vis[] = placeVertically(arr, i, j, word);
						solveCrossWorldPuzzle(arr, words, idx + 1);
						unPlaceVertically(arr, i, j, vis);
					}
				}
			}
		}

	}

	public static void unPlaceVertically(char arr[][], int row, int col, boolean vis[]) {

		for (int i = 0; i < vis.length; i++) {
			if (vis[i] == true) {
				arr[row + i][col] = '-';
			}
		}
	}

	public static void unPlaceHorizontally(char arr[][], int row, int col, boolean vis[]) {

		for (int j = 0; j < vis.length; j++) {
			if (vis[j] == true) {
				arr[row][col + j] = '-';
			}
		}
	}

	public static boolean[] placeVertically(char arr[][], int i, int j, String word) {

		boolean vis[] = new boolean[arr[0].length];
		for (int ii = 0; ii < word.length(); ii++) {

			if (arr[i + ii][j] == '-') {
				arr[i + ii][j] = word.charAt(ii);
				vis[ii] = true;
			}
		}
		return vis;
	}

	public static boolean[] placeHorizontally(char arr[][], int i, int j, String word) {

		boolean vis[] = new boolean[arr[0].length];
		for (int jj = 0; jj < word.length(); jj++) {

			if (arr[i][j + jj] == '-') {
				arr[i][j + jj] = word.charAt(jj);
				vis[jj] = true;
			}
		}
		return vis;
	}

	public static boolean canPlaceHorizontally(char arr[][], int i, int j, String word) {

		if (j - 1 >= 0 && arr[i][j - 1] != '+') {
			return false;
		} else if (j + word.length() < arr[0].length && arr[i][j + word.length()] != '+') {
			return false;
		}

		for (int jj = 0; jj < word.length(); jj++) {

			if (j + jj >= arr[0].length) {
				return false;
			}
			if (arr[i][j + jj] == '-' || arr[i][j + jj] == word.charAt(jj)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public static boolean canPlaceVertically(char arr[][], int i, int j, String word) {

		if (i - 1 >= 0 && arr[i - 1][j] != '+') {
			return false;
		} else if (i + word.length() < arr.length && arr[i + word.length()][j] != '+') {
			return false;
		}

		for (int ii = 0; ii < word.length(); ii++) {

			if (i + ii >= arr.length) {
				return false;
			}
			if (arr[i + ii][j] == '-' || arr[i + ii][j] == word.charAt(ii)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}

	public static void displayBoardChar(char board[][]) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void sudokuSolver(int board[][], int row, int col) {

		if (row == 9) {
			displayBoard(board);
			return;
		}

		int nrow = 0;
		int ncol = 0;

		if (col == 8) {
			nrow = row + 1;
			ncol = 0;
		} else {
			nrow = row;
			ncol = col + 1;
		}

		if (board[row][col] > 0) {
			sudokuSolver(board, nrow, ncol);
		} else {

			for (int i = 1; i <= 9; i++) {
				if (isValid(board, row, col, i)) {
					board[row][col] = i;
					sudokuSolver(board, nrow, ncol);
					board[row][col] = 0;
				}
			}
		}
	}

	public static boolean isValid(int board[][], int row, int col, int val) {

		for (int i = 0; i < 9; i++) {
			if (board[i][col] == val) {
				return false;
			}
		}
		for (int i = 0; i < 9; i++) {
			if (board[row][i] == val) {
				return false;
			}
		}

		int sr = (row / 3) * 3;
		int sc = (col / 3) * 3;

		for (int i = sr; i < sr + 3; i++) {
			for (int j = sc; j < sc + 3; j++) {
				if (board[i][j] == val) {
					return false;
				}
			}
		}

		return true;
	}

	public static void displayBoard(int board[][]) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void goldMine2(int arr[][], boolean vis[][], int row, int col, List<Integer> bag) {

		if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length || arr[row][col] == 0
				|| vis[row][col] == true) {
			return;
		}

		bag.add(arr[row][col]);
		vis[row][col] = true;
		goldMine2(arr, vis, row + 1, col, bag);
		goldMine2(arr, vis, row - 1, col, bag);
		goldMine2(arr, vis, row, col + 1, bag);
		goldMine2(arr, vis, row, col - 1, bag);
		vis[row][col] = false;
	}

	public static void printLexicographically(int num, List<Integer> list) {

		if (num > 1000) {
			return;
		}

		list.add(num);
		for (int i = 0; i < 10; i++) {
			printLexicographically(num * 10 + i, list);
		}
	}

	public static int gameOfExecution(int n, int k) {

		if (n == 1) {
			return 0;
		}

		int x = gameOfExecution(n - 1, k);
		return (x + k) % n;
	}

	public static int maxScoreOfWords(String[] words, int idx, int[] farr, int[] score) {

		if (idx == words.length) {
			return 0;
		}

		int sno = maxScoreOfWords(words, idx + 1, farr, score);

		int sword = 0;
		boolean flag = true;
		String word = words[idx];
		for (int i = 0; i < word.length(); i++) {

			char ch = word.charAt(i);

			farr[ch - 'a']--;
			sword += score[ch - 'a'];

			if (farr[ch - 'a'] < 0) {
				flag = false;
			}
		}
		int syes = 0;
		if (flag) {
			syes = sword + maxScoreOfWords(words, idx + 1, farr, score);
		}
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			farr[ch - 'a']++;
		}

		return Math.max(sno, syes);
	}

	public static void nQueensBranchAndBound(int chess[][], int row, boolean cols[], boolean diag[], boolean rdiag[],
			String ans) {

		if (row == chess.length) {
			System.out.println(ans);
			return;
		}

		for (int col = 0; col < chess.length; col++) {

			if (chess[row][col] == 0 && cols[col] == false && diag[row + col] == false
					&& rdiag[row - col + chess.length - 1] == false) {

				chess[row][col] = 1;
				cols[col] = true;
				diag[row + col] = true;
				rdiag[row - col + chess.length - 1] = true;

				nQueensBranchAndBound(chess, row + 1, cols, diag, rdiag, ans + row + col + ",");

				chess[row][col] = 0;
				cols[col] = false;
				diag[row + col] = false;
				rdiag[row - col + chess.length - 1] = false;
			}
		}

	}

	public static void printAbbreviations(String str, String ans) {

		if (str.length() == 0) {
			System.out.println(ans);
			return;
		}

		printAbbreviations(str.substring(1), ans + str.charAt(0));
		if (ans.length() > 0) {
			char ch = ans.charAt(ans.length() - 1);
			if (ch == '1') {
				printAbbreviations(str.substring(1), ans.substring(0, ans.length() - 1) + "2");
			} else if (ch == '2') {
				printAbbreviations(str.substring(1), ans.substring(0, ans.length() - 1) + "3");
			} else {
				printAbbreviations(str.substring(1), ans + "1");
			}

		} else {
			printAbbreviations(str.substring(1), ans + "1");
		}

	}
}
