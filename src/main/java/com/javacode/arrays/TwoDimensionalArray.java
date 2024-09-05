package com.javacode.arrays;

public class TwoDimensionalArray {

	public static void main(String[] args) {

//		int arr[][] = new int[3][4];
//		displayBoard(arr);

//		int arr1[][] = { { 1, 1 }, { 2, 2 }, { 3, 3 } };
//		int arr2[][] = { { 1, 1, 1 }, { 2, 2, 2 } };
//		matrixMultiplication(arr1, arr2);

//		int arr[][] = { { 11, 12, 13, 14 }, { 21, 22, 23, 24 }, { 31, 32, 33, 34 } };
//		waveTraversal(arr);

//		int arr[][] = { { 11, 12, 13, 14 }, { 21, 22, 23, 24 }, { 31, 32, 33, 34 }, { 41, 42, 43, 44 } };
//		spiralTraversal(arr);

//		int arr[][] = { { 0, 0, 1, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 1 }, { 1, 0, 1, 0 } };
//		exitPointOfMatrix(arr);
//		diagonalTraversal(arr);

//		char arr[][] = { { 'a', 'b', 'c', 'd' }, { 'e', 'f', 'g', 'h' }, { 'i', 'j', 'k', 'l' },
//				{ 'm', 'n', 'o', 'p' } };
//		rotateMatrix(arr);

//		int arr[][] = { { 11, 12, 13, 14 }, { 21, 22, 23, 24 }, { 31, 32, 33, 34 }, { 41, 42, 43, 44 } };
//		saddlePoint(arr);
//		searchInSortedArray(arr, 34);
//		searchInSortedArray2BinarySearch(arr, 34);

		int arr[][] = { { 11, 12, 13, 14, 15, 16 }, { 21, 22, 23, 24, 25, 26 }, { 31, 32, 33, 34, 35, 36 },
				{ 41, 42, 43, 44, 45, 46 }, { 51, 52, 53, 54, 55, 56 }, { 61, 62, 63, 64, 65, 66 } };
		shellRotate(arr, 2, 1);
	}

	private static void shellRotate(int arr[][], int s, int r) {

		displayBoard(arr);
		int res[] = fetchOneDArray(arr, s);

		rotateArray(res, r);
		placeArray(arr, res, s);

		System.out.println("----");
		displayBoard(arr);
	}

	private static void placeArray(int arr[][], int res[], int s) {

		int minr = s - 1;
		int minc = s - 1;
		int maxr = arr.length - s;
		int maxc = arr[0].length - s;
		int id = 0;

		for (int i = minr; i <= maxr; i++) {
			arr[i][minc] = res[id++];
		}

		for (int i = minc + 1; i <= maxc; i++) {
			arr[maxr][i] = res[id++];
		}

		for (int i = maxr - 1; i >= minr; i--) {
			arr[i][maxc] = res[id++];
		}

		for (int i = maxc - 1; i >= minc + 1; i--) {
			arr[minr][i] = res[id++];
		}
	}

	private static void rotateArray(int res[], int r) {

		r = res.length - r;
		r = r % res.length;
		if (r < 0) {
			r += res.length;
		}
		reverseArray(res, 0, r - 1);
		reverseArray(res, r, res.length - 1);
		reverseArray(res, 0, res.length - 1);

		System.out.println();
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void reverseArray(int arr[], int i, int j) {

		while (i < j) {

			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}
	}

	private static int[] fetchOneDArray(int arr[][], int s) {

		int minr = s - 1;
		int minc = s - 1;
		int maxr = arr.length - s;
		int maxc = arr[0].length - s;

		int len = (maxr - minr) * 4;
		int res[] = new int[len];

		int id = 0;
		for (int i = minr; i <= maxr; i++) {
			System.out.println(arr[i][minc]);
			res[id++] = arr[i][minc];
		}

		for (int i = minc + 1; i <= maxc; i++) {
			System.out.println(arr[maxr][i]);
			res[id++] = arr[maxr][i];
		}

		for (int i = maxr - 1; i >= minr; i--) {
			System.out.println(arr[i][maxc]);
			res[id++] = arr[i][maxc];
		}

		for (int i = maxc - 1; i >= minc + 1; i--) {
			System.out.println(arr[minr][i]);
			res[id++] = arr[minr][i];
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
		return res;
	}

	private static void searchInSortedArray2BinarySearch(int[][] arr, int num) {

		int row = binarySearchRowSelect(arr, num);
		System.out.println("Row found: " + row);
		if (row >= 0) {
			int col = binarySearch(arr[row], num);
			System.out.println("Col: " + col);
		}
	}

	private static int binarySearch(int arr[], int num) {

		int lo = 0;
		int hi = arr.length - 1;
		int col = -1;

		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid] == num) {
				col = mid;
				break;
			}
			if (arr[mid] > num) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return col;
	}

	private static int binarySearchRowSelect(int arr[][], int num) {

		int lo = 0;
		int hi = arr.length - 1;
		int row = -1;

		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid][0] <= num && arr[mid][arr[0].length - 1] >= num) {
				row = mid;
				break;
			}

			if (arr[mid][0] > num) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		return row;
	}

	private static void searchInSortedArray(int[][] arr, int num) {

		int row = 0;
		int col = arr[0].length - 1;

		while (row < arr.length && col >= 0) {

			if (arr[row][col] == num) {
				break;
			}

			if (arr[row][col] > num) {
				col--;
			} else {
				row++;
			}
		}

		System.out.println(row + " " + col);
	}

	private static void saddlePoint(int[][] arr) {

		int ans = -1;
		for (int i = 0; i < arr.length; i++) {

			int col = 0;
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] < arr[i][col]) {
					col = j;
				}
			}

			int row = 0;
			for (int k = 0; k < arr.length; k++) {
				if (arr[k][col] > arr[row][col]) {
					row = k;
				}
			}

			System.out.println(col + " " + row);

			if (arr[i][col] == arr[col][i]) {
				ans = arr[row][col];
			}
		}

		System.out.println("Saddle Point: " + ans);
	}

	private static void rotateMatrix(char[][] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				if (i > j) {
					char c1 = arr[i][j];
					char c2 = arr[j][i];

					arr[i][j] = c2;
					arr[j][i] = c1;
				}
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0, k = arr.length - 1; j < arr[0].length / 2; j++, k--) {

				char c1 = arr[i][j];
				char c2 = arr[i][k];

				arr[i][j] = c2;
				arr[i][k] = c1;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

	}

	private static void diagonalTraversal(int[][] arr) {

		for (int d = 0; d < arr.length; d++) {
			for (int i = 0, j = d; j < arr[0].length; i++, j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void exitPointOfMatrix(int[][] arr) {

		int dir = 0;
		int row = 0;
		int col = 0;

		while (row >= 0 && col >= 0 && row < arr.length && col < arr[0].length) {

			if (arr[row][col] == 0) {
				if (dir == 0) {
					col++;
				} else if (dir == 1) {
					row++;
				} else if (dir == 2) {
					col--;
				} else {
					row--;
				}
			} else {
				if (dir == 0) {
					row++;
				} else if (dir == 1) {
					col--;
				} else if (dir == 2) {
					row--;
				} else {
					col++;
				}
				dir++;
				dir = dir % 4;
			}
		}
		System.out.println(row + " " + col);
	}

	private static void spiralTraversal(int[][] arr) {

		int minr = 0;
		int minc = 0;
		int maxr = arr.length - 1;
		int maxc = arr[0].length - 1;

		while (true) {

			// Left
			for (int i = minr; i <= maxr; i++) {
				System.out.println(arr[i][minc]);
			}

			// Bottom
			for (int i = minc + 1; i <= maxc; i++) {
				System.out.println(arr[maxr][i]);
			}

			// Right
			for (int i = maxr - 1; i >= minr; i--) {
				System.out.println(arr[i][maxc]);
			}

			// Top
			for (int i = maxc - 1; i > minc; i--) {
				System.out.println(arr[minr][i]);
			}

			minr = minr + 1;
			minc = minc + 1;
			maxr = maxr - 1;
			maxc = maxc - 1;

			if (minr > maxr || minc > maxc) {
				break;
			}
		}
	}

	private static void waveTraversal(int[][] arr) {

		for (int j = 0; j < arr[0].length; j++) {
			if (j % 2 == 0) {
				for (int i = 0; i < arr.length; i++) {
					System.out.print(arr[i][j] + " ");
				}
			} else {
				for (int i = arr.length - 1; i >= 0; i--) {
					System.out.print(arr[i][j] + " ");
				}
			}
		}
	}

	private static void matrixMultiplication(int[][] arr1, int[][] arr2) {

		int res[][] = new int[arr1.length][arr2[0].length];
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[0].length; j++) {

				int sum = 0;
				for (int k = 0; k < arr1[0].length; k++) {
					sum += arr1[i][k] * arr2[k][j];
				}
				res[i][j] = sum;
			}
		}

		displayBoard(res);
	}

	private static void displayBoard(int[][] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

}
