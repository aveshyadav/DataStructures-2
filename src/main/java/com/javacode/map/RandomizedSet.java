package com.javacode.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {

	public static void main(String[] args) {

		RandomizedSet randomizedSet = new RandomizedSet();
		randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
		randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
		randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
		randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
		randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
		randomizedSet.insert(2); // 2 was already in the set, so return false.
		randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
	}

	private Map<Integer, Integer> map;
	private List<Integer> list;
	private Random random;

	public RandomizedSet() {

		map = new HashMap<>();
		list = new ArrayList<>();
		random = new Random();
	}

	private void insert(int i) {

		if (!map.containsKey(i)) {
			map.put(i, list.size());
			list.add(i);
		}
	}

	private void remove(int i) {

		if (map.containsKey(i)) {
			int idx = map.remove(i);

			if (idx == list.size() - 1) {
				list.remove(idx);
			} else {
				list.set(idx, list.get(list.size() - 1));
				list.remove(list.size() - 1);
			}
		} else {
			System.out.println(false);
		}
	}

	private void getRandom() {

		int randIdx = random.nextInt(list.size());
		System.out.println("Val: " + list.get(randIdx));
	}
}
