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

	private Board(){}
	private Board(int row, int col) {
		this.row = row;
		this.col = col;
		BOARD_SIZE = this.row * this.col;
		board =  new Integer[BOARD_SIZE];
		for (int i = 0; i < BOARD_SIZE; i++) {
			board[i] = i + 1;
		}
	}

	private boolean setRowIfValid(int row){
		if(row >= MIN_ROW && row <= MAX_ROW){
			this.row = row;
			return true;
		}
		return false;
	}
	private boolean setColIfValid(int col){
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
		System.out.println("\n-------------------------------------------------\n");
		int j = (row * col) - 1;
		int s = 0;
		while (j > 0) {
			s = (j % col);
			int i = 0;
			for (i = j; i >= (j - s); i--) {
				System.out.printf("%4d",board[i]);
			}
			s = i % col;
			System.out.println();
			for (int k = i - s; k <= i; k++) {
				System.out.printf("%4d",board[k]);
			}
			j = (i - s) - 1;
			System.out.println();
		}
		System.out.println("\n-------------------------------------------------\n");
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
	
	public static int getMinRow(){
		return Board.MIN_ROW;
	}
	public static int getMaxRow(){
		return Board.MAX_ROW;
	}
	
	public static int getMinCol(){
		return Board.MIN_COL;
	}
	public static int getMaxCol(){
		return Board.MAX_COL;
	}
	
	public static Board createBoard(int row, int col){
		boolean isValid = true;
		Board newBoard = new Board();
		if (!newBoard.setRowIfValid(row)) {
			System.out.println("Not a valid rows Min is " + getMinRow()+ " And Max is " + getMaxRow());
			isValid = false;
		}
		if (!newBoard.setColIfValid(col)) {
			System.out.println("Not a valid Column Min is "+ getMinCol() + " And Max is " + getMaxCol());
			isValid = false;
		}
		if(isValid){
			newBoard.BOARD_SIZE = newBoard.row * newBoard.col;
			newBoard.board =  new Integer[newBoard.BOARD_SIZE];
			for (int i = 0; i < newBoard.BOARD_SIZE; i++) {
				newBoard.board[i] = i + 1;
			}
		}else{
			newBoard = null;
		}
		return newBoard;
				
	}
}
