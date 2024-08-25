package com.javacode.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class MaxFrequencyStack {

	public static void main(String[] args) {

		MaxFrequencyStack freqStack = new MaxFrequencyStack();
		freqStack.push(5); // The stack is [5]
		freqStack.push(7); // The stack is [5,7]
		freqStack.push(5); // The stack is [5,7,5]
		freqStack.push(7); // The stack is [5,7,5,7]
		freqStack.push(4); // The stack is [5,7,5,7,4]
		freqStack.push(5); // The stack is [5,7,5,7,4,5]
		freqStack.pop(); // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
		freqStack.pop(); // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The
							// stack becomes [5,7,5,4].
		freqStack.pop(); // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
		freqStack.pop();
	}

	Map<Integer, Stack<Integer>> map;
	Map<Integer, Integer> fmap;
	int maxFreq;

	public MaxFrequencyStack() {

		maxFreq = 0;
		map = new HashMap<>();
		fmap = new HashMap<>();
	}

	private void push(int num) {

		fmap.put(num, fmap.getOrDefault(num, 0) + 1);
		int freq = fmap.get(num);
		if (freq > maxFreq) {
			maxFreq = freq;
		}

		if (!map.containsKey(freq)) {
			map.put(freq, new Stack<>());
		}
		map.get(freq).push(num);
	}

	private void pop() {

		int val = map.get(maxFreq).pop();
		if (map.get(maxFreq).size() == 0) {
			map.remove(maxFreq);
			maxFreq--;
		}

		if (fmap.get(val) == 1) {
			fmap.remove(val);
		} else {
			fmap.put(val, fmap.get(val) - 1);
		}

		System.out.println(val);
	}

}
