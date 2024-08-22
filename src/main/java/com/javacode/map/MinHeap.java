package com.javacode.map;

import java.util.ArrayList;
import java.util.List;

public class MinHeap {

	public static void main(String[] args) {

		MinHeap heap = new MinHeap();
		int arr[] = { 10, 20, 30, 40, 45, 35, 42, 55, 5 };
		for (int i = 0; i < arr.length; i++) {
			heap.add(arr[i]);
		}

		heap.display();

		while (heap.size() > 0) {
			System.out.print(heap.remove() + " ");
			heap.display();
		}

	}

	public MinHeap(int arr[]) {

		list = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}

		for (int i = list.size() / 2 - 1; i >= 0; i--) {
			downHeapify(i);
		}
	}

	private List<Integer> list;

	public MinHeap() {
		list = new ArrayList<>();
	}

	public void add(int data) {

		list.add(data);
		upHeapify();
	}

	private void upHeapify() {

		int idx = list.size() - 1;
		int pi = (idx - 1) / 2;

		while (list.get(pi) > list.get(idx) && idx > 0) {
			swap(idx, pi);
			idx = pi;
			pi = (idx - 1) / 2;
		}
	}

	private int remove() {

		int data = list.get(0);
		swap(0, list.size() - 1);
		list.remove(list.size() - 1);
		downHeapify(0);

		return data;
	}

	private void downHeapify(int pi) {

		int min = pi;
		int li = (2 * pi) + 1;
		if (li < list.size() && list.get(li) < list.get(min)) {
			min = li;
		}

		int ri = (2 * pi) + 2;
		if (ri < list.size() && list.get(ri) < list.get(min)) {
			min = ri;
		}

		if (min != pi) {
			swap(min, pi);
			downHeapify(min);
		}
	}

	private void swap(int idx1, int idx2) {

		int temp = list.get(idx1);
		list.set(idx1, list.get(idx2));
		list.set(idx2, temp);
	}

	private int peek() {
		if (list.size() == 0) {
			return -1;
		}
		return list.get(0);
	}

	private int size() {
		return list.size();
	}

	private void display() {
		System.out.println(list);
	}
}
