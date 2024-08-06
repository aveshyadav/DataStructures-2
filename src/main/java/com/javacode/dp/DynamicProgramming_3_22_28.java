package com.javacode.dp;

public class DynamicProgramming_3_22_28 {

	public static void main(String[] args) {

		int arr[] = { 1, 5, 8, 9, 10, 17, 17, 20 };
		rodCutting1(arr);

//		String str = "abccbc";
//		minPalindromicPartitioningCut1(str);
//		minPalindromicPartitioningCut2(str);
	}

	private static void minPalindromicPartitioningCut1(String str) {

	}

	private static void rodCutting1(int[] arr1) {

	}

	private static void display(int arr[][]) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
