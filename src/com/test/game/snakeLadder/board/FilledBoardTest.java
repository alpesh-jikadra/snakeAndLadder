package com.test.game.snakeLadder.board;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;

import com.game.snakeLadder.board.FilledBoard;
import com.game.snakeLadder.player.Player;


public class FilledBoardTest {

	
	
	@Test
	public void testValidBoardCreation(){
		FilledBoard filledBoard = new FilledBoard();	
		boolean actual = filledBoard.createBoard(10, 10);
		Assert.assertTrue("Create Game board fail, error while creating Game Board",actual);
	}
	
	@Test
	public void testInValidBoardCreation(){
		FilledBoard filledBoard = new FilledBoard();
		boolean actual = filledBoard.createBoard(100, 10);
		Assert.assertFalse("Create 1Game board fail, error while creating Game Board",actual);
		actual = filledBoard.createBoard(10, 100);
		Assert.assertFalse("Create 1Game board fail, error while creating Game Board",actual);
	}
	
	@Test
	public void testValidTotalLadders(){
		FilledBoard filledBoard = new FilledBoard();
		boolean actual = filledBoard.setTotalLadder(5);
		Assert.assertTrue("Error while setting total ladder in filled board" , actual);
	}
	@Test
	public void testInValidTotalLadders(){
		FilledBoard filledBoard = new FilledBoard();
		boolean actual = filledBoard.setTotalLadder(1);
		Assert.assertFalse("Error while setting total ladder in filled board" , actual);
	}
	
	@Test
	public void testSetTotalValidSnakes(){
		FilledBoard filledBoard = new FilledBoard();
		boolean actual = filledBoard.setTotalSnakes(5);
		Assert.assertTrue("Error while setting total Snakes in filled board" , actual);
	}
	@Test
	public void testSetTotalInValidSnakes(){
		FilledBoard filledBoard = new FilledBoard();
		boolean actual = filledBoard.setTotalLadder(1);
		Assert.assertFalse("Error while setting total Snakes in filled board" , actual);
	}
	@Test
	public void testSnakeHeadAtWinningPosition(){
		FilledBoard filledBoard = new FilledBoard();
		filledBoard.createBoard(10, 10);
		filledBoard.setTotalSnakes(5);
		boolean actual = filledBoard.addSnakes(100, 5);
		Assert.assertFalse("Snake Head position can not be at Winning position",actual);
	}
	@Test
	public void testSnakeTailAtStartPosition(){
		FilledBoard filledBoard = new FilledBoard();
		filledBoard.createBoard(10, 10);
		filledBoard.setTotalSnakes(5);
		boolean actual = filledBoard.addSnakes(10, 1);
		Assert.assertFalse("Snake Tail can not be at start position",actual);
	}
	@Test
	public void testAddValidLadderOnFilledBoard(){
		FilledBoard filledBoard = new FilledBoard();
		
		boolean actual = filledBoard.createBoard(10, 10);
		actual = actual && filledBoard.setTotalLadder(8);
		actual = actual && filledBoard.addLadder(4, 14);
		actual = actual && filledBoard.addLadder(9, 31);
		actual = actual && filledBoard.addLadder(20, 38);
		actual = actual && filledBoard.addLadder(28, 84);
		actual = actual && filledBoard.addLadder(40, 59);
		actual = actual && filledBoard.addLadder(51, 67);
		actual = actual && filledBoard.addLadder(63, 81);
		actual = actual && filledBoard.addLadder(71, 91);
		
		Assert.assertTrue("Error while adding Ladders",actual);
	}
	
	@Test
	public void testAddInValidLadderOnFilledBoard(){
		FilledBoard filledBoard = new FilledBoard();
		
		boolean actual = filledBoard.createBoard(10, 10);
		actual = actual && filledBoard.setTotalLadder(8);
		actual = actual && filledBoard.addLadder(4, 14);
		actual = actual && filledBoard.addLadder(9, 31);
		actual = actual && filledBoard.addLadder(20, 38);
		actual = actual && filledBoard.addLadder(28, 84);
		actual = actual && filledBoard.addLadder(40, 40);
		actual = actual && filledBoard.addLadder(51, 67);
		actual = actual && filledBoard.addLadder(63, 81);
		actual = actual && filledBoard.addLadder(71, 91);
		
		Assert.assertFalse("Error while adding Ladders",actual);
	}
	@Test
	public void testAddValidSnakeOnFilledBoardWithLadderPresent(){
		FilledBoard filledBoard = new FilledBoard();
		
		boolean actual = filledBoard.createBoard(10, 10);
		actual = actual && filledBoard.setTotalLadder(8);
		actual = actual && filledBoard.addLadder(4, 14);
		actual = actual && filledBoard.addLadder(9, 31);
		actual = actual && filledBoard.addLadder(20, 38);
		actual = actual && filledBoard.addLadder(28, 84);
		actual = actual && filledBoard.addLadder(40, 59);
		actual = actual && filledBoard.addLadder(51, 67);
		actual = actual && filledBoard.addLadder(63, 81);
		actual = actual && filledBoard.addLadder(71, 91);
		
		actual = actual && filledBoard.setTotalSnakes(7);
		actual = actual && filledBoard.addSnakes(17, 7);
		actual = actual && filledBoard.addSnakes(54, 34);
		actual = actual && filledBoard.addSnakes(62, 19);
		actual = actual && filledBoard.addSnakes(64, 60);
		actual = actual && filledBoard.addSnakes(93, 73);
		actual = actual && filledBoard.addSnakes(95, 75);
		actual = actual && filledBoard.addSnakes(99, 78);
		Assert.assertTrue("Error while adding Snake if Ladder Exist",actual);
	}
	
	@Test
	public void testAddInValidSnakeOnFilledBoardWithLadderPresent(){
		FilledBoard filledBoard = new FilledBoard();
		
		boolean actual = filledBoard.createBoard(10, 10);
		actual = actual && filledBoard.setTotalLadder(8);
		actual = actual && filledBoard.addLadder(4, 14);
		actual = actual && filledBoard.addLadder(9, 31);
		actual = actual && filledBoard.addLadder(20, 38);
		actual = actual && filledBoard.addLadder(28, 84);
		actual = actual && filledBoard.addLadder(40, 59);
		actual = actual && filledBoard.addLadder(51, 67);
		actual = actual && filledBoard.addLadder(63, 81);
		actual = actual && filledBoard.addLadder(71, 91);
		
		actual = actual && filledBoard.setTotalSnakes(7);
		actual = actual && filledBoard.addSnakes(17, 7);
		actual = actual && filledBoard.addSnakes(54, 34);
		actual = actual && filledBoard.addSnakes(62, 19);
		actual = actual && filledBoard.addSnakes(64, 60);
		actual = actual && filledBoard.addSnakes(93, 73);
		actual = actual && filledBoard.addSnakes(95, 75);
		//actual = actual && filledBoard.addSnakes(99, 78);
		actual = actual && filledBoard.addSnakes(91, 71);
		
		Assert.assertFalse("Error while adding Snake if Ladder Exist",actual);
	}
	
	@Test
	public void testAddValidLadderOnFilledBoardWithSnakePresent(){
		FilledBoard filledBoard = new FilledBoard();
		
		boolean actual = filledBoard.createBoard(10, 10);
		
		actual = actual && filledBoard.setTotalSnakes(7);
		actual = actual && filledBoard.addSnakes(17, 7);
		actual = actual && filledBoard.addSnakes(54, 34);
		actual = actual && filledBoard.addSnakes(62, 19);
		actual = actual && filledBoard.addSnakes(64, 60);
		actual = actual && filledBoard.addSnakes(93, 73);
		actual = actual && filledBoard.addSnakes(95, 75);
		actual = actual && filledBoard.addSnakes(99, 78);
		
		actual = actual && filledBoard.setTotalLadder(8);
		actual = actual && filledBoard.addLadder(4, 14);
		actual = actual && filledBoard.addLadder(9, 31);
		actual = actual && filledBoard.addLadder(20, 38);
		actual = actual && filledBoard.addLadder(28, 84);
		actual = actual && filledBoard.addLadder(40, 59);
		actual = actual && filledBoard.addLadder(51, 67);
		actual = actual && filledBoard.addLadder(63, 81);
		actual = actual && filledBoard.addLadder(71, 91);
		
		Assert.assertTrue("Error while adding Ladder if Snakes Exist",actual);
	}
	@Test
	public void testAddInValidLadderOnFilledBoardWithSnakePresent(){
		FilledBoard filledBoard = new FilledBoard();
		
		boolean actual = filledBoard.createBoard(10, 10);
		
		actual = actual && filledBoard.setTotalSnakes(7);
		actual = actual && filledBoard.addSnakes(17, 7);
		actual = actual && filledBoard.addSnakes(54, 34);
		actual = actual && filledBoard.addSnakes(62, 19);
		actual = actual && filledBoard.addSnakes(64, 60);
		actual = actual && filledBoard.addSnakes(93, 73);
		actual = actual && filledBoard.addSnakes(95, 75);
		actual = actual && filledBoard.addSnakes(99, 78);
		
		actual = actual && filledBoard.setTotalLadder(8);
		actual = actual && filledBoard.addLadder(4, 14);
		actual = actual && filledBoard.addLadder(9, 31);
		actual = actual && filledBoard.addLadder(20, 38);
		actual = actual && filledBoard.addLadder(28, 84);
		actual = actual && filledBoard.addLadder(40, 59);
		actual = actual && filledBoard.addLadder(51, 67);
		actual = actual && filledBoard.addLadder(63, 81);
		actual = actual && filledBoard.addLadder(78, 99);
		
		Assert.assertFalse("Error while adding Ladder if Snakes Exist",actual);
	}
	
	@Test
	public void testAddPlayer(){
		FilledBoard filledBoard = new FilledBoard();
		boolean actual = filledBoard.addPlayer("Alpesh");
		Assert.assertTrue("Error while adding First player",actual);
		actual = filledBoard.addPlayer("Piyush");
		Assert.assertTrue("Error while adding Second player",actual);
	}
	
	@Test
	public void testNextMove() throws Exception {
		FilledBoard filledBoard = new FilledBoard();
		filledBoard.createBoard(10, 10);
		filledBoard.addPlayer("Alpesh");
		filledBoard.addPlayer("Piyush");
		
		filledBoard.changeCurrentPlayer();
		
		Integer nextMove = 6;
		boolean actual = filledBoard.isValidMove(nextMove);
		Assert.assertTrue("Not a valid move on Filled Board",actual);
		Player currentPlayer = filledBoard.getCurrentPlayer();
		Assert.assertTrue("Player Initial position is not set to 0", 0 == currentPlayer.getCurrentPosition());
		
	
		actual = changeCurrentPlayerPosition(filledBoard, nextMove); // To move player at specific position
		Assert.assertTrue("Problem while moving valid moves", actual);
		Assert.assertTrue("Player Initial position is not set to 0", nextMove == currentPlayer.getCurrentPosition());
		
	}
	private boolean changeCurrentPlayerPosition(FilledBoard filledBoard, int nextMove) throws Exception{
		Class c = filledBoard.getClass();
		Class[] types = new Class[1];
		types[0]=Integer.class;
		Method method = FilledBoard.class.getDeclaredMethod("changePlayerPosition",types);
		method.setAccessible(true);
		Boolean retVal =(Boolean)method.invoke(filledBoard, new Integer[]{nextMove});
		return retVal;
	}
	@Test
	public void testSnakeHeadAfterCurrentMove() throws Exception{
		FilledBoard filledBoard = new FilledBoard();
		filledBoard.createBoard(10, 10);
		filledBoard.addPlayer("Alpesh");
		filledBoard.addPlayer("Piyush");
		
		filledBoard.setTotalSnakes(7);
		filledBoard.addSnakes(17, 7);
		filledBoard.addSnakes(54, 34);
		filledBoard.addSnakes(62, 19);
		filledBoard.addSnakes(64, 60);
		filledBoard.addSnakes(93, 73);
		filledBoard.addSnakes(95, 75);
		filledBoard.addSnakes(99, 78);
		
		filledBoard.changeCurrentPlayer();
		
		Player currentPlayer = filledBoard.getCurrentPlayer();
		Integer nextPosition = 17;
		Integer snakeTail = 7;
		changeCurrentPlayerPosition(filledBoard, nextPosition);// To move player at specific position
		Assert.assertTrue("Player can not move to next Position", nextPosition == currentPlayer.getCurrentPosition());
		
		boolean actual = filledBoard.isPlayerAtSnakeHead();
		Assert.assertTrue("Player is not a snake head",actual);
		actual = filledBoard.changePlayerPositionIfSnakeHead();
		
		Assert.assertTrue("Player position not move from Snake Head", snakeTail == currentPlayer.getCurrentPosition());
		
		
		
	}
	
	@Test
	public void testLadderMoves() throws Exception{
		FilledBoard filledBoard = new FilledBoard();
		filledBoard.createBoard(10, 10);
		filledBoard.addPlayer("Alpesh");
		filledBoard.addPlayer("Piyush");
		
		filledBoard.setTotalLadder(8);
		filledBoard.addLadder(4, 14);
		filledBoard.addLadder(9, 31);
		filledBoard.addLadder(20, 38);
		filledBoard.addLadder(28, 84);
		filledBoard.addLadder(40, 59);
		filledBoard.addLadder(51, 67);
		filledBoard.addLadder(63, 81);
		filledBoard.addLadder(78, 99);
		
		filledBoard.changeCurrentPlayer();
		
		Player currentPlayer = filledBoard.getCurrentPlayer();
		Integer nextPosition = 9;
		Integer ladderTop= 31;
		changeCurrentPlayerPosition(filledBoard, nextPosition);// To move player at specific position
		Assert.assertTrue("Player can not move to Ladder Top", nextPosition == currentPlayer.getCurrentPosition());
		
		boolean actual = filledBoard.isPlayerAtLadderStart();
		Assert.assertTrue("Player is not at Ladder Start",actual);
		actual = filledBoard.changePlayerPositionIfLadderStart();
		
		Assert.assertTrue("Player position not move from Ladder Top", ladderTop == currentPlayer.getCurrentPosition());
		
	}
	
	@Test
	public void testWinner()throws Exception{
		FilledBoard filledBoard = new FilledBoard();
		filledBoard.createBoard(10, 10);
		filledBoard.addPlayer("Alpesh");
		filledBoard.addPlayer("Piyush");
		filledBoard.changeCurrentPlayer();
		Integer winningPosition = 100;
		changeCurrentPlayerPosition(filledBoard, winningPosition);
		boolean actual = filledBoard.isWinner();
		Assert.assertTrue("Player not win even if at last position",actual);
		
	}
	
	
	
}
