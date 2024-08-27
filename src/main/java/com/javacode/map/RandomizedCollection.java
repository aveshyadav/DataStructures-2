package com.javacode.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RandomizedCollection {

	public static void main(String[] args) {

		RandomizedCollection randomizedCollection = new RandomizedCollection();
		randomizedCollection.insert(1); // return true since the collection does not contain 1.
										// Inserts 1 into the collection.
		randomizedCollection.insert(1); // return false since the collection contains 1.
										// Inserts another 1 into the collection. Collection now contains [1,1].
		randomizedCollection.insert(2); // return true since the collection does not contain 2.
										// Inserts 2 into the collection. Collection now contains [1,1,2].
		randomizedCollection.getRandom(); // getRandom should:
											// - return 1 with probability 2/3, or
											// - return 2 with probability 1/3.
		randomizedCollection.remove(1); // return true since the collection contains 1.
										// Removes 1 from the collection. Collection now contains [1,2].
		randomizedCollection.getRandom(); // getRandom should return 1 or 2, both equally likely.

		randomizedCollection.getRandom();
		randomizedCollection.getRandom();
		randomizedCollection.getRandom();
		randomizedCollection.getRandom();
		randomizedCollection.getRandom();
	}

	private Map<Integer, Set<Integer>> map = new HashMap<>();
	private List<Integer> list = new ArrayList<>();

	private void insert(int i) {

		if (!map.containsKey(i)) {
			map.put(i, new HashSet<>());
		}

		map.get(i).add(list.size());
		list.add(i);
	}

	private void remove(int i) {

		if (map.containsKey(i)) {

			Set<Integer> set = map.get(i);
			int idx = 0;
			for (int d : set) {
				idx = d;
				break;
			}
			if (set.size() == 0) {
				map.remove(i);
			} else {
				set.remove(idx);
			}

			if (idx == list.size() - 1) {
				list.remove(list.size() - 1);
			} else {
				list.set(idx, list.size() - 1);
				list.remove(list.size() - 1);
			}
		}

	}

	private void getRandom() {

		int idx = (int) Math.random() * list.size();
		System.out.println(list.get(idx));
		System.out.println(map);
	}

}
