package com.javacode.stack;

import java.util.LinkedList;
import java.util.Queue;

public class HitCounter {

	public static void main(String[] args) {

		HitCounter hc = new HitCounter();
		hc.hit(1);
		hc.hit(2);
		hc.hit(3);
		hc.getHit(4);
		hc.hit(300);
		hc.getHit(300);
		hc.getHit(301);
	}

	private Queue<Integer> queue = new LinkedList<>();

	private void hit(int i) {
		queue.add(i);
	}

	private void getHit(int i) {

		int start = i - 300;
		while (queue.peek() <= start) {
			queue.remove();
		}
		System.out.println(queue.size());
	}

}
