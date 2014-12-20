package com.game.snakeLadder.board;

import com.game.snakeLadder.die.Die;
import com.game.snakeLadder.ladder.Ladder;
import com.game.snakeLadder.player.Player;
import com.game.snakeLadder.player.PlayerList;
import com.game.snakeLadder.snake.Snake;
import com.game.snakeLadder.validator.Validator;

public class FilledBoard {

	private Ladder ladders = null;
	private Snake snakes = null;
	private Player currentPlayer = null;
	private boolean isGameWon = false;
	private Board board = null;

	private PlayerList players = new PlayerList();

	private Die die = Die.getDie();

	public boolean createBoard(int row, int col) {

		board = new Board();
		if (!board.setRowIfValid(row)) {
			System.out.println("Not a valid rows Min is " + board.getMinRow()
					+ " And Max is " + board.getMaxRow());
			board = null;
			return false;
		}
		if (!board.setColIfValid(col)) {
			System.out.println("Column is too large Min is "
					+ board.getMinCol() + " And Max is " + board.getMaxCol());
			board = null;
			return false;
		}
		board = new Board(row, col);
		return true;
	}

	public boolean setTotalLadder(int noOfLadder) {
		boolean isValid = true;
		ladders = new Ladder();
		if (!ladders.setNoOfLaddersIfValid(noOfLadder)) {
			System.out.println("Not A valid Ladder count min "
					+ Ladder.getMinLadderCount() + " and max "
					+ Ladder.getMaxLadderCount());
			ladders = null;
			isValid = false;
		}
		return isValid;
	}

	public boolean setTotalSnakes(int noOfSnakes) {
		boolean isValid = true;
		snakes = new Snake();
		if (!snakes.setNoOfSnakesIfValid(noOfSnakes)) {
			System.out.println("Not A valid Snakes count min "
					+ Snake.getMinSnakeCount() + " and max "
					+ Snake.getMaxSnakeCount());
			snakes = null;
			isValid = false;
		}
		return isValid;
	}

	public boolean addLadder(int bottom, int top) {
		if (bottom == 1) {
			System.out.println("Ladder can not be set to 1");
			return false;
		}
		if (top == board.getDestinationNumber()) {
			System.out
					.println("Ladder top position can not be at winning position");
			return false;
		}
		if (Validator.isValidateLadderEntry(bottom, top, snakes, board)) {
			if (!this.ladders.addNewLadder(bottom, top)) {
				System.out.println("Please try again...");
				return false;
			} else {
				return true;
			}
		}
		return false;
	}

	public void throwDie() {
		if (!isWinner()) {
			int number = die.throwDie();
			move(number);
		}
	}

	private void move(int nextMove) {
		if (!isWinner()) {
			System.out.println(currentPlayer.getName() + " You got " + nextMove);

			if (isValidMove(nextMove)) {

				int oldPosition = currentPlayer.getCurrentPosition();
				changePlayerPosition(nextMove);
				printPosition(oldPosition, currentPlayer.getCurrentPosition());

				checkSnakeMove();
				checkLadderMove();
				if (isWinner()) {
					System.out.println(currentPlayer.getName()
							+ " You won the game ");
				}
			} else {
				System.out.println(currentPlayer.getName()
						+ " Your current Position is "
						+ currentPlayer.getCurrentPosition()
						+ " So this move is not possible");
			}
		}

	}

	public boolean isValidMove(int nextMove) {
		return currentPlayer.getCurrentPosition() + nextMove <= board
				.getDestinationNumber() && nextMove <= die.getMaxMovePosition();
	}

	private boolean changePlayerPosition(Integer nextMove) {
		currentPlayer.setCurrentPosition(currentPlayer.getCurrentPosition()
				+ nextMove);
		return true;
	}

	private void checkSnakeMove() {

		if (!isWinner() && isPlayerAtSnakeHead()) {
			int oldPosition = currentPlayer.getCurrentPosition();
			System.out.println("Oh you are at snake head ["
					+ currentPlayer.getCurrentPosition() + "->"
					+ snakes.getSnakeTail(currentPlayer.getCurrentPosition())
					+ "]");
			changePlayerPositionIfSnakeHead();
			printPosition(oldPosition, currentPlayer.getCurrentPosition());
		}
	}

	public boolean isPlayerAtSnakeHead() {
		return snakes.isSnakeHead(currentPlayer.getCurrentPosition());
	}

	public boolean changePlayerPositionIfSnakeHead() {
		if (isPlayerAtSnakeHead()) {
			currentPlayer.setCurrentPosition(snakes.getSnakeTail(currentPlayer
					.getCurrentPosition()));
			return true;
		}
		return false;
	}

	private void checkLadderMove() {
		if (!isWinner() && isPlayerAtLadderStart()) {
			int oldPosition = currentPlayer.getCurrentPosition();
			System.out.println("WOW you are at Ladder Start ["
					+ currentPlayer.getCurrentPosition() + "->"
					+ ladders.getLadderTop(currentPlayer.getCurrentPosition())
					+ "]");
			changePlayerPositionIfLadderStart();
			printPosition(oldPosition, currentPlayer.getCurrentPosition());

		}
	}

	public boolean isPlayerAtLadderStart() {
		return ladders.isLadderBottom(currentPlayer.getCurrentPosition());
	}

	public boolean changePlayerPositionIfLadderStart() {
		if (isPlayerAtLadderStart()) {
			currentPlayer.setCurrentPosition(ladders.getLadderTop(currentPlayer
					.getCurrentPosition()));
			return true;
		}
		return false;
	}

	public boolean addSnakes(int head, int tail) {
		if (head == board.getDestinationNumber()) {
			System.out.println("Snake Head can not be at Destination position");
			return false;
		}
		if (tail == 1) {
			System.out.println("Snake Tail can not be set at first Position");
			return false;
		}
		if (Validator.isValidateSnakeEntry(head, tail, ladders)) {
			if (!snakes.addNewSnake(head, tail)) {
				System.out.println("Please try again...");
				return false;
			} else {
				return true;
			}
		}

		return false;
	}

	public boolean addPlayer(String name) {
		return players.addPlayer(new Player(name));
	}

	public boolean isWinner() {
		isGameWon =currentPlayer.getCurrentPosition() == board
				.getDestinationNumber();
		return isGameWon;
				
	}

	public boolean changeCurrentPlayer() {
		if (players != null) {
			currentPlayer = players.getNextPlayer();
		}
		return currentPlayer != null;
	}

	public Ladder getLadders() {
		return ladders;
	}

	public Snake getSnakes() {
		return snakes;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public boolean isAllSnakeSet() {
		return snakes.isAllSnakesComplete();
	}

	public boolean isAllLaddersSet() {
		return ladders.isAllLadderComplete();
	}

	public boolean isMaxPlayerOver() {
		return this.players.isMaxPlayerCompleted();
	}

	public void printBoard() {
		board.printBoard();
	}

	public void printPlayerDetails() {
		currentPlayer.printDetails();
	}

	public void showSnakes() {
		snakes.showSnakes();
	}

	public void showLadders() {
		ladders.showLadders();
	}

	private void printPosition(int oldPos, int newPos) {
		if (oldPos == 0) {
			System.out.println(currentPlayer.getName() + " You move to ["
					+ newPos + "]");
		} else {
			System.out.println(currentPlayer.getName()
					+ " Your position change [" + oldPos + " to " + newPos
					+ "]");
		}
	}
	public boolean isGameWon(){
		return this.isGameWon;
	}
}
