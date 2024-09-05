package com.javacode.arrays;

public class FunctionsAndArray {

	public static void main(String[] args) {

//		nPr(5, 3);
//		countDigit(97695439, 9);

//		decimalToAnyBase(634, 8);
//		anyBaseToDecimal(1172, 8);
//		anyBaseToAnyBase(172, 8, 2);
//		anyBaseAddition(236, 754, 8);

//		int arr[] = { 15, 30, 40, 4, 11, 9 };
//		spanOfArray(arr);

//		int arr[] = { 3, 1, 0, 7, 5 };
//		printBar(arr);

//		int arr1[] = { 9, 9, 9 };
//		int arr2[] = { 2, 8 };
//		sumOfTwoArray(arr1, arr2);

//		int arr1[] = { 1, 0, 0, 0, 0 };
//		int arr2[] = { 1 };
//		subtractionOfTwoArray(arr1, arr2);

//		int arr[] = { 10, 20, 30, 40, 50 };
//		reverseArray(arr, 0, arr.length - 1);

//		int arr[] = { 1, 2, 3, 4, 5, 6 };
//		rotateArray(arr, -1);

//		int arr[] = { 3, 4, 1, 2, 0 };
//		inverseArray(arr);

//		int arr[] = { 1, 2, 3 };
//		printAllSubArray(arr);
//		printAllSubArray(arr, 0, "");

//		int arr[] = { 1, 2, 3, 4, 5, 6, 8 };
//		binarySearch(arr, 8);

//		int arr[] = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
//		ceilAndFloor(arr, 12);

		int arr[] = { 10, 10, 10, 20, 20, 30, 30, 30, 40, 40 };
		firstAndLastIndex(arr, 20);
	}

	private static void firstAndLastIndex(int[] arr, int num) {

		int lo = 0;
		int hi = arr.length - 1;
		int first = -1;

		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid] == num) {
				first = mid;
				hi = mid - 1;
			} else if (arr[mid] > num) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println("First Occurrence: " + first);

		lo = 0;
		hi = arr.length - 1;
		int last = -1;

		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid] == num) {
				last = mid;
				lo = mid + 1;
			} else if (arr[mid] > num) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}

		System.out.println("Last Occurrence: " + last);
	}

	private static void ceilAndFloor(int[] arr, int num) {

		int lo = 0;
		int hi = arr.length - 1;
		int ceil = -1;
		int floor = -1;

		while (lo <= hi) {

			int mid = (lo + hi) / 2;
			if (arr[mid] == num) {
				ceil = arr[mid];
				floor = arr[mid];
				break;
			}
			if (arr[mid] < num) {
				ceil = arr[mid];
				lo = mid + 1;
			} else {
				floor = arr[mid];
				hi = mid - 1;
			}
		}
		System.out.println("Ceil: " + ceil + ", Floor: " + floor);

	}

	private static void binarySearch(int[] arr, int ele) {

		int lo = 0;
		int hi = arr.length - 1;

		int idx = -1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (arr[mid] == ele) {
				idx = mid;
				break;
			}
			if (arr[mid] > ele) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		System.out.println("Element found at Index: " + idx);
	}

	private static void printAllSubArray(int arr[], int idx, String ans) {

		if (idx == arr.length) {
			System.out.println(ans);
			return;
		}

		printAllSubArray(arr, idx + 1, ans);
		printAllSubArray(arr, idx + 1, ans + arr[idx] + " ");
	}

	private static void printAllSubArray(int[] arr) {

		String res = "";
		for (int i = 0; i < arr.length; i++) {

			res = arr[i] + " ";
			System.out.println(res);
			for (int j = i + 1; j < arr.length; j++) {
				res = res + arr[j] + " ";
				System.out.println(res);
			}
		}
	}

	private static void inverseArray(int[] arr) {

		int res[] = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[arr[i]] = i;
		}

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void rotateArray(int[] arr, int k) {

		k = k % arr.length;
		if (k < 0) {
			k += arr.length;
		}

		reverseArray(arr, 0, k - 1);
		System.out.println();
		reverseArray(arr, k, arr.length - 1);
		System.out.println();
		reverseArray(arr, 0, arr.length - 1);
	}

	private static void reverseArray(int[] arr, int i, int j) {

		while (i < j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			j--;
		}

		for (i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	private static void subtractionOfTwoArray(int[] arr1, int[] arr2) {

		int res[] = new int[arr1.length];
		int i = arr1.length - 1;
		int j = arr2.length - 1;
		int k = res.length - 1;
		int borrow = 0;

		while (i >= 0 || j >= 0) {

			int val = arr1[i] - borrow;
			val = j >= 0 ? val - arr2[j] : val;
			borrow = 0;

			if (val < 0) {
				val += 10;
				borrow = 1;
			}

			res[k] = val;
			i--;
			j--;
			k--;
		}

		for (i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void sumOfTwoArray(int[] arr1, int[] arr2) {

		int res[] = new int[Math.max(arr1.length, arr2.length) + 1];
		int k = res.length - 1;

		int i = arr1.length - 1;
		int j = arr2.length - 1;
		int carry = 0;

		while (i >= 0 || j >= 0 || carry > 0) {

			int sum = carry;
			sum += i >= 0 ? arr1[i] : 0;
			sum += j >= 0 ? arr2[j] : 0;
			carry = 0;

			if (sum > 9) {
				sum = sum % 10;
				carry = 1;
			}

			res[k] = sum;
			i--;
			j--;
			k--;
		}

		for (i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	private static void printBar(int[] arr) {

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}

		for (int i = 1; i <= max; i++) {
			for (int j = 0; j < arr.length; j++) {

				int diff = max - arr[j];
				if (i > diff) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

	private static void spanOfArray(int[] arr) {

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			min = Math.min(min, arr[i]);
			max = Math.max(max, arr[i]);
		}
		int span = max - min;
		System.out.println("Span: " + span);
	}

	private static void anyBaseAddition(int n1, int n2, int b) {

		int v1 = anyBaseToDecimal(n1, b);
		int v2 = anyBaseToDecimal(n2, b);

		int res = v1 + v2;
		int v3 = decimalToAnyBase(res, b);
		System.out.println(v3);

	}

	private static void anyBaseToAnyBase(int num, int b1, int b2) {

		int v1 = anyBaseToDecimal(num, b1);
		System.out.println(v1);
		int v2 = decimalToAnyBase(v1, b2);
		System.out.println(v2);
	}

	private static int anyBaseToDecimal(int num, int b) {

		int ans = 0;
		int pow = 1;
		while (num > 0) {

			int rem = num % 10;
			num = num / 10;
			ans += rem * pow;

			pow = pow * b;
		}

		return ans;
	}

	private static int decimalToAnyBase(int num, int b) {

		int res = 0;
		int exp = 1;
		while (num > 0) {

			int rem = num % b;
			num = num / b;

			res = (rem * exp) + res;
			exp = exp * 10;
		}

		return res;
	}

	private static void countDigit(int num, int k) {

		int count = 0;
		while (num > 0) {

			int rem = num % 10;
			num = num / 10;
			if (rem == k) {
				count++;
			}
		}

		System.out.println("Count: " + count);
	}

	private static void nPr(int n, int r) {

		int nf = fact(n);
		int nrf = fact(n - r);

		int ans = nf / nrf;
		System.out.println("NPR: " + ans);
	}

	private static int fact(int num) {

		if (num <= 1) {
			return 1;
		}

		return fact(num - 1) * num;
	}

}
