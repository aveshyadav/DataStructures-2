package com.javacode.map;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianPriorityQueue {

	public static void main(String[] args) {

		MedianPriorityQueue q = new MedianPriorityQueue();
		q.add(10);
		q.add(20);
		q.add(30);
		System.out.println(q.peek());
		q.add(40);
		System.out.println(q.peek());
		q.add(50);
		System.out.println(q.peek());
	}

	PriorityQueue<Integer> lq;
	PriorityQueue<Integer> rq;

	public MedianPriorityQueue() {

		lq = new PriorityQueue<>(Collections.reverseOrder());
		rq = new PriorityQueue<>();
	}

	private void add(int data) {

		if (rq.size() > 0 && rq.peek() < data) {
			rq.add(data);
		} else {
			lq.add(data);
		}

		if (lq.size() - rq.size() > 1) {
			rq.add(lq.remove());
		} else if (rq.size() - lq.size() > 1) {
			lq.add(rq.remove());
		}
		
		System.out.println(lq+" "+rq);
	}

	private int peek() {

		if (rq.size() > lq.size()) {
			return rq.peek();
		}

		return lq.peek();
	}

}
