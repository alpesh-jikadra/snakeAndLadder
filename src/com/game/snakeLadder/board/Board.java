package com.game.snakeLadder.board;

public class Board {

	private static final int MIN_ROW = 10;
	private static final int MAX_ROW = 12;
	
	private static final int MIN_COL = 10;
	private static final int MAX_COL = 12;
	
	
	private Integer row = 10;
	private Integer col = 10;

	private Integer BOARD_SIZE= -1;;

	private Integer[] board = null;

	public Board(){}
	public Board(int row, int col) {
		this.row = row;
		this.col = col;
		BOARD_SIZE = this.row * this.col;
		board =  new Integer[BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			board[i] = i + 1;
		}
	}

	public boolean setRowIfValid(int row){
		if(row >= MIN_ROW && row <= MAX_ROW){
			this.row = row;
			return true;
		}
		return false;
	}
	public boolean setColIfValid(int col){
		if(col >= MIN_COL && col <= MAX_COL){
			this.col= col;
			return true;
		}
		return false;
	}
	public int getDestinationNumber(){
		return BOARD_SIZE;
	}
	public void printBoard() {
		System.out.println("-------------------------------------------------");
		int j = (row * col) - 1;
		int s = 0;
		while (j > 0) {
			s = (j % col);
			int i = 0;
			for (i = j; i >= (j - s); i--) {
				System.err.printf("%4d",board[i]);
			}
			s = i % col;
			System.err.println();
			for (int k = i - s; k <= i; k++) {
				System.err.printf("%4d",board[k]);
			}
			j = (i - s) - 1;
			System.err.println();
		}
		System.out.println("-------------------------------------------------");
	}
	public boolean isValidRow(){
		return (this.row>=10 && this.row<=12);
	}
	public boolean isValidCol(){
		return (this.col>=10 && this.col<=12);
	}
	public boolean isValidBoard(){
		return isValidRow() && isValidCol();
	}
	
	public int getMinRow(){
		return Board.MIN_ROW;
	}
	public int getMaxRow(){
		return Board.MAX_ROW;
	}
	
	public int getMinCol(){
		return Board.MIN_COL;
	}
	public int getMaxCol(){
		return Board.MAX_COL;
	}
}
