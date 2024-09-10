package com.javacode.arrays;

import java.util.HashMap;
import java.util.Map;

public class SnapshotArray {

	public static void main(String[] args) {

		SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
		snapshotArr.set(0, 5); // Set array[0] = 5
		snapshotArr.snap(); // Take a snapshot, return snap_id = 0
		snapshotArr.set(0, 6);
		snapshotArr.get(0, 0); // Get the value of array[0] with snap_id = 0, return 5

	}

	private int snapId;
	private int arr[];
	private Map<Integer, int[]> map;

	public SnapshotArray(int n) {

		this.snapId = 0;
		this.arr = new int[n];
		map = new HashMap<>();
		map.put(snapId, arr);
	}

	public void set(int idx, int val) {
		map.get(snapId)[idx] = val;
	}

	public void snap() {
		snapId++;
		arr = new int[arr.length];
		map.put(snapId, arr);
	}

	public void get(int idx, int snid) {
		int val = map.get(snid)[idx];
		System.out.println(val);
	}
}
