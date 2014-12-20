package com.game.play;

import java.util.Scanner;

import com.game.snakeLadder.board.FilledBoard;
import com.game.snakeLadder.playable.Playable;

public class SnakeAndLadder implements Playable {

	private Scanner in = new Scanner(System.in);
	FilledBoard filledBoard = new FilledBoard();
	private static final int MIN_TOTAL_PLAYER = 2;
	private boolean exit = false;
	private boolean playable = false;
	@Override
	public boolean isPlayable() {
		if(!playable){
			closeResource();
		}
		return playable;
	}

	@Override
	public void play() {
		filledBoard.changeCurrentPlayer();
		try {
			while (true && !filledBoard.isWinner() &&  !filledBoard.isGameWon()) {
				
				System.out.println("--------------------");
				System.out.println(filledBoard.getCurrentPlayer().getName() + " : your turn -->");
				int input = getNextNumberFromPlayer("\n1 for Throw Die.\n2 for Print Board.\n3 for Current Player.\n4 Show Snakes \n5 Show Ladders \n6 Quit\n Choose your option-->");
				switch (input) {
				case 1: // Throw Die
					filledBoard.throwDie();
					filledBoard.changeCurrentPlayer();
					break;
				case 2: // Print board
					filledBoard.printBoard();
					break;
				case 3: // Print current Player
					filledBoard.printPlayerDetails();
					break;
				case 4:
					filledBoard.showSnakes();
					break;
				case 5:
					filledBoard.showLadders();
					break;
				case 6: // Quit
					System.out.println(filledBoard.getCurrentPlayer().getName()+" Lose the Game.");
					filledBoard.changeCurrentPlayer();
					System.out.println(filledBoard.getCurrentPlayer().getName()+" Won the Game.");
					System.out.println("Game is Over");
					return;
				default:
					System.out.println("Wrong Input tyr again");
				}
				
				if(filledBoard.isGameWon()){
					System.out.println("Game is won");
					System.out.println("Game is Over");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeResource();
		}
	}

	@Override
	public void setUpGame() {
		if(customGameOption()){
			if(createBoard()){
				if(addSnakesOnBoard()){
					if(addLaddersOnBoard()){
						if(addPlayersOnBoard()){
							playable = true;
							return;
						}
					}
				}
			}
			closeResource();
		}else{
			playable = setUpDefaultGame();
		}
		
		
	}
	private boolean createBoard(){
		
		try{
			boolean isValidBoard = false;
			while(true && !exit){
				int row = getNextNumberFromPlayer("Enter Row For Board ->");
				int col = getNextNumberFromPlayer("Enter Columns For Board ->");
				
				isValidBoard = filledBoard.createBoard(row, col);
				if(isValidBoard){
					return true;
				}else{
					String option = getNextStringFromPlayer("Do you want to try (Y/N) ->");
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

	private boolean addSnakesOnBoard() {
		try{
			boolean isNoOfSnakesValid = false;
			while(!isNoOfSnakesValid && !exit){
				
				int noOfSnakes = getNextNumberFromPlayer("Enter No of Snakes on a Board ->");
				isNoOfSnakesValid = filledBoard.setTotalSnakes(noOfSnakes);
				
				if(isNoOfSnakesValid){
					while(true && !exit && !filledBoard.isAllSnakeSet()){
						int head = getNextNumberFromPlayer("Enter Snake Head Position ->");
						int tail = getNextNumberFromPlayer("Enter Snake Tail Position ->");
						boolean isNewSnakeAdded = filledBoard.addSnakes(head, tail);
						if(!isNewSnakeAdded){
							String option = getNextStringFromPlayer("Do you want to try again (Y/N) ->");
							if("n".equalsIgnoreCase(option)){
								exit = true;
							}	
						}else{
							System.out.println("Snake added successfully on Board at ["+head+"->"+tail+"]");
						}
						if(filledBoard.isAllSnakeSet()){
							System.out.println("All Sankes are Set On Board");
							System.out.println("Proceed Next");
						}
					}
					if(filledBoard.isAllSnakeSet()){
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

	private boolean addLaddersOnBoard() {
		try{
			boolean isNoOfLaddersValid = false;
			while(!isNoOfLaddersValid && !exit){
				
				int noOfLadders = getNextNumberFromPlayer("Enter No of Ladders on a Board ->");
				isNoOfLaddersValid = filledBoard.setTotalLadder(noOfLadders);
				
				if(isNoOfLaddersValid){
					while(true && !exit && !filledBoard.isAllLaddersSet()){
						int bottom = getNextNumberFromPlayer("Enter Ladder Bottom Position ->");
						int top = getNextNumberFromPlayer("Enter Ladder Top Position ->");
						boolean isNewLadderAdded = filledBoard.addLadder(bottom, top);
						if(!isNewLadderAdded){
							String option = getNextStringFromPlayer("Do you want to try again (Y/N) ->");
							if("n".equalsIgnoreCase(option)){
								exit = true;
							}	
						}else{
							System.out.println("Ladder added successfully on Board at ["+bottom+"->"+top+"]");
						}
					}
					if(filledBoard.isAllLaddersSet()){
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

	private boolean addPlayersOnBoard() {
		try {
			boolean isPlayerAdded = false;
			boolean needMorePlayer = true;
			int totalPlayer = 0;
			while (needMorePlayer && !exit && !filledBoard.isMaxPlayerOver()) {
				
				String playerName = getNextStringFromPlayer("Enter Player Name ->");
				isPlayerAdded = filledBoard.addPlayer(playerName);
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
	public void closeResource(){
		in.close();
		filledBoard = null;
	}
	private boolean customGameOption(){
		String answer = getNextStringFromPlayer("Do you want to customize Game (Y/N) ?");
		return ("y".equals(answer));
	}
	private boolean setUpDefaultGame(){
		boolean isValid = true;
		isValid = filledBoard.createBoard(10, 12);
		
		isValid = isValid && filledBoard.setTotalSnakes(7);
		isValid = isValid && filledBoard.addSnakes(17, 7);
		isValid = isValid && filledBoard.addSnakes(54, 34);
		isValid = isValid && filledBoard.addSnakes(62,19);
		isValid = isValid && filledBoard.addSnakes(64,60);
		isValid = isValid && filledBoard.addSnakes(93,73);
		isValid = isValid && filledBoard.addSnakes(95,75);
		isValid = isValid && filledBoard.addSnakes(99,78);
		
		isValid = isValid && filledBoard.setTotalLadder(8);
		
		isValid = isValid && filledBoard.addLadder(4,14);
		isValid = isValid && filledBoard.addLadder(9,31);
		isValid = isValid && filledBoard.addLadder(20,38);
		isValid = isValid && filledBoard.addLadder(28,84);
		isValid = isValid && filledBoard.addLadder(40,59);
		isValid = isValid && filledBoard.addLadder(51,67);
		isValid = isValid && filledBoard.addLadder(63,81);
		isValid = isValid && filledBoard.addLadder(71,91);
		
		isValid = isValid && filledBoard.addPlayer("Alpesh");
		isValid = isValid && filledBoard.addPlayer("Jack");
		
		return isValid;
	}
}
