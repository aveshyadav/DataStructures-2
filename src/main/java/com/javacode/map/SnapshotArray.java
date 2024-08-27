package com.javacode.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SnapshotArray {

	public static void main(String[] args) {

		SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
		snapshotArr.set(0, 5); // Set array[0] = 5
		snapshotArr.snap(); // Take a snapshot, return snap_id = 0
		snapshotArr.set(0, 6);
		snapshotArr.get(0, 0); // Get the value of array[0] with snap_id = 0, return 5
	}

	private List<Map<Integer, Integer>> list;
	int sid;

	public SnapshotArray(int n) {
		sid = 0;
		list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new HashMap<>());
		}
	}

	public void set(int i, int val) {
		list.get(i).put(sid, val);
	}

	public void get(int idx, int snap_id) {
		while (snap_id >= 0 && !list.get(snap_id).containsKey(idx)) {
			snap_id--;
		}

		if (snap_id < 0) {
			System.out.println("Not found");
		} else {
			System.out.println(list.get(snap_id).get(idx));
		}
	}

	private void snap() {
		sid++;
	}

}
