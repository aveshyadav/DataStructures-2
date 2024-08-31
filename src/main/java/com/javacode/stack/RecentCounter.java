package com.javacode.stack;

import java.util.LinkedList;
import java.util.Queue;

public class RecentCounter {

	public static void main(String[] args) {

		RecentCounter recentCounter = new RecentCounter();
		recentCounter.ping(1); // requests = [1], range is [-2999,1], return 1
		recentCounter.ping(100); // requests = [1, 100], range is [-2900,100], return 2
		recentCounter.ping(3001); // requests = [1, 100, 3001], range is [1,3001], return 3
		recentCounter.ping(3002); // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
	}

	private Queue<Integer> queue = new LinkedList<>();

	public void ping(int d) {

		int rem = d - 3000;
		while (queue.size() > 0 && queue.peek() <= rem) {
			queue.remove();
		}
		queue.add(d);

		System.out.println(queue);
	}
}
