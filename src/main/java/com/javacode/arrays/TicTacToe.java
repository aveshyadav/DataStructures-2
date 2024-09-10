package com.javacode.arrays;

public class TicTacToe {

	public static void main(String[] args) {

		TicTacToe tt = new TicTacToe(3);
		tt.move(0, 0, 1);
		tt.move(0, 2, 2);
		tt.move(2, 2, 1);
		tt.move(1, 1, 2);
		tt.move(2, 0, 1);
		tt.move(1, 0, 2);
		tt.move(2, 1, 1);
	}

	private int rows[];
	private int cols[];
	private int diag;
	private int rdiag;
	private int n;

	public TicTacToe(int n) {

		this.n = n;
		this.rows = new int[n];
		this.cols = new int[n];
		this.diag = this.rdiag = 0;
	}

	private void move(int r, int c, int player) {

		int val = (player == 1) ? 1 : -1;

		rows[r] += val;
		if (rows[r] == n || rows[r] == -n) {
			System.out.println("Player: " + player + " win");
		}

		cols[c] += val;
		if (cols[c] == n || cols[c] == -n) {
			System.out.println("Player: " + player + " win");
		}

		if (r == c) {
			diag += val;
		}

		if (diag == n || diag == -n) {
			System.out.println("Player: " + player + " win");
		}

		if (r + c == n - 1) {
			rdiag += val;
		}

		if (rdiag == n || rdiag == -n) {
			System.out.println("Player: " + player + " win");
		}
	}

}
