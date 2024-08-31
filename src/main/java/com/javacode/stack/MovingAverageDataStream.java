package com.javacode.stack;

import java.util.LinkedList;
import java.util.Queue;

public class MovingAverageDataStream {

	public static void main(String[] args) {

		MovingAverageDataStream ds = new MovingAverageDataStream(3);

		ds.next(1);
		ds.next(10);
		ds.next(12);
		ds.next(15);
		ds.next(100);
	}

	private int size;
	private double sum;
	private Queue<Integer> queue;

	public MovingAverageDataStream(int n) {

		this.queue = new LinkedList<>();
		this.size = n;
		this.sum = 0;
	}

	public void next(int d) {

		sum += d;
		queue.add(d);

		if (queue.size() > size) {
			sum -= queue.remove();
		}

		double avg = (double)(sum / queue.size());
		System.out.println("Avg: "+avg);
	}
}
