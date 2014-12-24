package com.game.play;

import java.util.Scanner;

import com.game.snakeLadder.board.Board;
import com.game.snakeLadder.die.Die;
import com.game.snakeLadder.ladder.Ladder;
import com.game.snakeLadder.playable.Playable;
import com.game.snakeLadder.player.Player;
import com.game.snakeLadder.player.Players;
import com.game.snakeLadder.snake.Snake;
import com.game.snakeLadder.validator.Validator;

public class SnakeAndLadder implements Playable {

	private static final int MIN_TOTAL_PLAYER = 2;
	public Scanner in = new Scanner(System.in);
	private Ladder ladders = null;
	private Snake snakes = null;
	private Die die = Die.getDie();
	private Players players = new Players();
	private Board board = null;
	
	private Player currentPlayer = null;
	private boolean exit = false;
	private boolean playable = false;
	private boolean isGameWon = false;
	
	
	
	@Override
	public boolean isPlayable() {
		if (!playable) {
			closeResource();
		}
		return playable;
	}

	@Override
	public void setUpGame() {
		if (customGameOption()) {
			if (createBoard()) {
				if (setUpSnakes()) {
					if (setUpLadders()) {
						if (addPlayers()) {
							playable = true;
							return;
						}
					}
				}
			}
			closeResource();
		} else {
			playable = setUpDefaultGame();
		}
	}
	
	private boolean createBoard() {
		boolean isValidBoard = false;
		try {
			
			while (!exit && !isValidBoard) {
				int row = getNextNumberFromPlayer("Enter Row For Board ->");
				int col = getNextNumberFromPlayer("Enter Columns For Board ->");

				board = Board.createBoard(row, col);
				if (board != null) {
					isValidBoard = true;
				} else {
					String option = getNextStringFromPlayer("Do you want to try (Y/N) ->");
					if ("n".equalsIgnoreCase(option)) {
						exit = true;
					}
				}
			}
			isValidBoard = false;
		} catch (Exception e) {
			System.out.println("Error while creating board, Please restart Game again...");
			System.out.println(e.getMessage());
		}
		return isValidBoard;
	}
	
	private boolean addSnakes(Integer head, Integer tail) {
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
	
	private void throwDie() {
		if (!isGameWon()) {
			int number = die.throwDie();
			if(move(number)){
				
				checkSnakeMove();
				
				checkLadderMove();
				
				if (isWinner()) {
					System.out.println(currentPlayer.getName()
							+ " You won the game ");
				}
			}
			changePlayer();
		}
	}
	
	private boolean play(Integer option) {
		try {
			switch (option) {
			case 1: // Throw Die
				throwDie();
				break;
			case 2: // Print board
				board.printBoard();
				break;
			case 3: // Print current Player
				currentPlayer.printDetails();
				break;
			case 4: // print snakes
				snakes.showSnakes();
				break;
			case 5: // print ladders
				ladders.showLadders();
				break;
			case 6: // Quit
				System.out.println(currentPlayer.getName() + " Lose the Game.");
				changePlayer();
				System.out.println(currentPlayer.getName() + " Won the Game.");
				System.out.println("Game is Over");
				isGameWon = true;
				return isGameWon;
			default:
				System.out.println("Wrong Input tyr again");
			}

			if (isGameWon()) {
				System.out.println("Game is won");
				System.out.println("Game is Over");
				return isGameWon;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return isGameWon;
	}

	@Override
	public void play(){
		changePlayer();
		try {
			while (!isGameWon()) {
				
				System.out.println("--------------------");
				System.out.println(currentPlayer.getName() + " : your turn -->");
				int input = getNextNumberFromPlayer("\n" +
						"1 for Throw Die.\n" +
						"2 for Print Board.\n" +
						"3 for Current Player.\n" +
						"4 Show Snakes \n" +
						"5 Show Ladders \n" +
						"6 Quit\n" +
						"Choose your option-->");
				play(input);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeResource();
		}
	}

	private boolean setUpSnakes() {
		try{
			boolean isNoOfSnakesValid = false;
			
			while(!isNoOfSnakesValid && !exit){
				
				int noOfSnakes = getNextNumberFromPlayer("Enter No of Snakes on a Board ->");
				isNoOfSnakesValid = setTotalSnakes(noOfSnakes);
				
				if(isNoOfSnakesValid){
					while(!exit && !snakes.isAllSnakesComplete()){
						int head = getNextNumberFromPlayer("Enter Snake Head Position ->");
						int tail = getNextNumberFromPlayer("Enter Snake Tail Position ->");
						boolean isNewSnakeAdded = addSnakes(head, tail);
						if(!isNewSnakeAdded){
							String option = getNextStringFromPlayer("Do you want to try again (Y/N) ->");
							if("n".equalsIgnoreCase(option)){
								exit = true;
							}	
						}else{
							System.out.println("Snake added successfully on Board at ["+head+"->"+tail+"]");
						}
						if(snakes.isAllSnakesComplete()){
							System.out.println("All Sankes are Set On Board");
							System.out.println("Proceed Next");
						}
					}
					if(snakes.isAllSnakesComplete()){
						return true;
					}
				}else{
					String option = getNextStringFromPlayer("Do you want to try again (Y/N) ->");
					if("n".equalsIgnoreCase(option)){
						exit = true;
					}					
				}
			}
			return false;
		}catch(Exception e){
			System.out.println("Error while creating board, Please restart Game again...");
			System.out.println(e.getMessage());
		}
		return false;
	}
	private boolean setTotalSnakes(Integer noOfSnakes) {
		boolean isValid = true;
		snakes = new Snake();
		if (!snakes.setNoOfSnakesIfValid(noOfSnakes)) {
			System.out.println("Not A valid Snakes count min "+ Snake.getMinSnakeCount() + " and max "+ Snake.getMaxSnakeCount());
			snakes = null;
			isValid = false;
		}
		return isValid;
	}
	private boolean setUpLadders() {
		try{
			boolean isNoOfLaddersValid = false;
			while(!isNoOfLaddersValid && !exit){
				
				int noOfLadders = getNextNumberFromPlayer("Enter No of Ladders on a Board ->");
				isNoOfLaddersValid = setTotalLadder(noOfLadders);
				
				if(isNoOfLaddersValid){
					while(!exit && !ladders.isAllLadderComplete()){
						int bottom = getNextNumberFromPlayer("Enter Ladder Bottom Position ->");
						int top = getNextNumberFromPlayer("Enter Ladder Top Position ->");
						boolean isNewLadderAdded = addLadder(bottom, top);
						if(!isNewLadderAdded){
							String option = getNextStringFromPlayer("Do you want to try again (Y/N) ->");
							if("n".equalsIgnoreCase(option)){
								exit = true;
							}	
						}else{
							System.out.println("Ladder added successfully on Board at ["+bottom+"->"+top+"]");
						}
					}
					if(ladders.isAllLadderComplete()){
						System.out.println("All Ladders are set on Board");
						System.out.println("Proceed Next...");
						return true;
					}
				}else{
					String option = getNextStringFromPlayer("Do you want to try again (Y/N) ->");
					if("n".equalsIgnoreCase(option)){
						exit = true;
					}					
				}
			}
			return false;
		}catch(Exception e){
			System.out.println("Error while creating board, Please restart Game again...");
			System.out.println(e.getMessage());
		}
		return false;
	}
	private boolean setTotalLadder(int noOfLadder) {
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
	private boolean addLadder(Integer bottom, Integer top) {
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
	private boolean addPlayers() {
		try {
			boolean isPlayerAdded = false;
			boolean needMorePlayer = true;
			int totalPlayer = 0;
			while (needMorePlayer && !exit && !players.isMaxPlayerCompleted()) {
				
				String playerName = getNextStringFromPlayer("Enter Player Name ->");
				isPlayerAdded = players.addPlayer(new Player(playerName));
				if (isPlayerAdded) {
					totalPlayer++;
					
					if(totalPlayer > MIN_TOTAL_PLAYER){
						needMorePlayer=false;
						break;
					}
					if(totalPlayer>=MIN_TOTAL_PLAYER){
						String option = getNextStringFromPlayer("Do you want to Add more player (Y/N) ->");
						if ("n".equalsIgnoreCase(option)) {
							needMorePlayer= false;
						}
					}
				} else {
					String option = getNextStringFromPlayer("Do you want to Exit (Y/N) ->");
					if ("y".equalsIgnoreCase(option)) {
						exit = true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			System.out
					.println("Error while creating board, Please restart Game again...");
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	private String getNextStringFromPlayer(String message){
		String input = "";
		try{
			System.out.print(message);
			input = in.next();
		}catch(Exception e){
			e.printStackTrace();
		}
		return input;
		
	}
	private int getNextNumberFromPlayer(String message){
		int input =-1;
		try{
			System.out.print(message);
			input = in.nextInt(10);
		}catch(Exception e){
			e.printStackTrace();
		}
		return input;
	}
	private void closeResource(){
		in.close();
	}
	private boolean customGameOption(){
		String answer = getNextStringFromPlayer("Do you want to customize Game (Y/N) ? \n");
		return ("y".equals(answer));
	}
	private boolean setUpDefaultGame(){
		boolean isValid = true;
		this.board = Board.createBoard(10, 12);
		if(board != null){
			isValid = isValid && setTotalSnakes(7);
			isValid = isValid && addSnakes(17, 7);
			isValid = isValid && addSnakes(54, 34);
			isValid = isValid && addSnakes(62,19);
			isValid = isValid && addSnakes(64,60);
			isValid = isValid && addSnakes(93,73);
			isValid = isValid && addSnakes(95,75);
			isValid = isValid && addSnakes(99,78);
			
			isValid = isValid && setTotalLadder(8);
			
			isValid = isValid && addLadder(4,14);
			isValid = isValid && addLadder(9,31);
			isValid = isValid && addLadder(20,38);
			isValid = isValid && addLadder(28,84);
			isValid = isValid && addLadder(40,59);
			isValid = isValid && addLadder(51,67);
			isValid = isValid && addLadder(63,81);
			isValid = isValid && addLadder(71,91);
			
			isValid = isValid && players.addPlayer(new Player("Alpesh"));
			isValid = isValid && players.addPlayer(new Player("Jack"));
		}else{
			isValid = false;
		}
		return (playable = isValid);
	}
	private boolean isWinner() {
		isGameWon =currentPlayer.getCurrentPosition() == board
				.getDestinationNumber();
		return isGameWon;
				
	}

	private boolean isGameWon() {
		return isGameWon;
	}
	
	private boolean move(Integer nextMove) {
		boolean isMoved = false;
		if (!isWinner()) {
			System.out.println(currentPlayer.getName() + " You got " + nextMove);

			if (isValidMove(nextMove)) {

				int oldPosition = currentPlayer.getCurrentPosition();
				isMoved = changePlayerPosition(nextMove);
				printPosition(oldPosition, currentPlayer.getCurrentPosition());

				/*checkSnakeMove();
				checkLadderMove();
				if (isWinner()) {
					System.out.println(currentPlayer.getName()
							+ " You won the game ");
				}*/
			} else {
				System.out.println(currentPlayer.getName()
						+ " Your current Position is "
						+ currentPlayer.getCurrentPosition()
						+ " So this move is not possible");
			}
		}
		return isMoved;
	}
	private boolean isValidMove(Integer nextMove) {
		return currentPlayer.getCurrentPosition() + nextMove <= board
				.getDestinationNumber() && nextMove <= die.getMaxMovePosition();
	}
	private boolean changePlayerPosition(Integer nextMove) {
		currentPlayer.setCurrentPosition(currentPlayer.getCurrentPosition()
				+ nextMove);
		return true;
	}
	private boolean changePlayer(){
		this.currentPlayer = players.getNextPlayer();
		return true;
	}
	private boolean checkSnakeMove() {
		boolean isSnakeMoved = false;
		if (!isWinner() && isPlayerAtSnakeHead()) {
			int oldPosition = currentPlayer.getCurrentPosition();
			System.out.println("Oh you are at snake head ["
					+ currentPlayer.getCurrentPosition() + "->"
					+ snakes.getSnakeTail(currentPlayer.getCurrentPosition())
					+ "]");
			isSnakeMoved = changePlayerPositionIfSnakeHead();
			printPosition(oldPosition, currentPlayer.getCurrentPosition());
		}
		return isSnakeMoved;
	}
	private boolean isPlayerAtSnakeHead() {
		return snakes.isSnakeHead(currentPlayer.getCurrentPosition());
	}

	private boolean changePlayerPositionIfSnakeHead() {
		if (isPlayerAtSnakeHead()) {
			currentPlayer.setCurrentPosition(snakes.getSnakeTail(currentPlayer
					.getCurrentPosition()));
			return true;
		}
		return false;
	}

	private boolean checkLadderMove() {
		boolean isLadderModed = false;
		if (!isWinner() && isPlayerAtLadderStart()) {
			int oldPosition = currentPlayer.getCurrentPosition();
			System.out.println("WOW you are at Ladder Start ["
					+ currentPlayer.getCurrentPosition() + "->"
					+ ladders.getLadderTop(currentPlayer.getCurrentPosition())
					+ "]");
			isLadderModed = changePlayerPositionIfLadderStart();
			printPosition(oldPosition, currentPlayer.getCurrentPosition());

		}
		return isLadderModed;
	}

	private boolean isPlayerAtLadderStart() {
		return ladders.isLadderBottom(currentPlayer.getCurrentPosition());
	}

	private boolean changePlayerPositionIfLadderStart() {
		if (isPlayerAtLadderStart()) {
			currentPlayer.setCurrentPosition(ladders.getLadderTop(currentPlayer
					.getCurrentPosition()));
			return true;
		}
		return false;
	}
	private void printPosition(Integer oldPos, Integer newPos) {
		if (oldPos == 0) {
			System.out.println(currentPlayer.getName() + " You move to ["
					+ newPos + "]");
		} else {
			System.out.println(currentPlayer.getName()
					+ " Your position change [" + oldPos + " to " + newPos
					+ "]");
		}
	}

	public Ladder getLadders() {
		return ladders;
	}

	public Snake getSnakes() {
		return snakes;
	}

	public Board getBoard() {
		return board;
	}
	

		
		
		
		
}