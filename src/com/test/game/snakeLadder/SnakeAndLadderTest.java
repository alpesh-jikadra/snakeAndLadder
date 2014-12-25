package com.test.game.snakeLadder;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Scanner;

import junit.framework.Assert;

import org.junit.Test;

import com.game.play.SnakeAndLadder;
import com.game.snakeLadder.board.Board;
import com.game.snakeLadder.ladder.Ladder;
import com.game.snakeLadder.snake.Snake;

public class SnakeAndLadderTest {

	@Test
	public void testMoveMethod() throws Exception {
		SnakeAndLadder instance = getInstance();
		Class[] types = new Class[] { Integer.class };
		boolean isMoved = changePlayerPosition(instance, 1);
		Assert.assertTrue("Change Player position fail ", isMoved);

		Method method = getMethod("move", types);
		isMoved = (Boolean) method.invoke(instance, 6);
		Assert.assertTrue("Move valid postion fail", isMoved);
	}

	// Try to test using loop for winner, this will take one by one player and process throwDie
	@Test
	public void testPlayGameWithThrowDie() throws Exception {
		SnakeAndLadder instance = getInstance();
		Class[] types = new Class[] { Integer.class };

		Method method = getMethod("play", types);
		Boolean retVal = (Boolean) method.invoke(instance, new Object[] { 1 }); // Throw
																				// Die

		boolean isGameWon = false;

		while (!isGameWon) {
			isGameWon = (Boolean) method.invoke(instance, new Object[] { 1 });
		}
		Assert.assertTrue("Throw die to win game fail", isGameWon);
	}

	@Test
	public void testSnakeMove() throws Exception {
		SnakeAndLadder instance = getInstance();

		Field privateField = SnakeAndLadder.class.getDeclaredField("snakes");
		privateField.setAccessible(true);
		Snake snake = (Snake) privateField.get(instance);

		Map<Integer, Integer> snakes = snake.getSnakes();// instance.getSnakes().getSnakes();

		Integer snakeHead = snakes.keySet().iterator().next();

		Class[] types = new Class[] {};

		boolean isMoved = changePlayerPosition(instance, snakeHead);
		Assert.assertTrue("Change Player position at snake head fail", isMoved);

		Method method = getMethod("checkSnakeMove", types);
		isMoved = (Boolean) method.invoke(instance, null);

		Assert.assertTrue("Snake head to snake tail position move fail",
				isMoved);
	}

	@Test
	public void testLadderMove() throws Exception {
		SnakeAndLadder instance = getInstance();

		Field privateField = SnakeAndLadder.class.getDeclaredField("ladders");
		privateField.setAccessible(true);
		Ladder ladder = (Ladder) privateField.get(instance);

		Map<Integer, Integer> ladders = ladder.getLadders();

		Integer snakeHead = ladders.keySet().iterator().next();

		Class[] types = new Class[] {};

		boolean isMoved = changePlayerPosition(instance, snakeHead);
		Assert.assertTrue("Change Player position at snake bottom fail",
				isMoved);

		Method method = getMethod("checkLadderMove", types);
		isMoved = (Boolean) method.invoke(instance, null);

		Assert.assertTrue("Snake head to snake top position move fail", isMoved);
	}

	@Test
	public void testIsWinner() throws Exception {
		SnakeAndLadder instance = getInstance();

		Class[] types = new Class[] {};

		Field privateField = SnakeAndLadder.class.getDeclaredField("board");
		privateField.setAccessible(true);
		Board board = (Board) privateField.get(instance);

		boolean isMoved = changePlayerPosition(instance,
				board.getDestinationNumber());

		Assert.assertTrue("Test case fail for moving to winner position",
				isMoved);

		Method method = getMethod("isWinner", types);
		isMoved = (Boolean) method.invoke(instance, null);

		Assert.assertTrue("Test case fail for checking winner position",
				isMoved);

	}

	@Test
	public void testValidMove() throws Exception {
		SnakeAndLadder instance = getInstance();

		Class[] types = new Class[] { Integer.class };
		Method method = getMethod("isValidMove", types);
		Boolean isMoved = (Boolean) method.invoke(instance, 6);

		Assert.assertTrue("Valid position move fail", isMoved);

		isMoved = (Boolean) method.invoke(instance, 7);

		Assert.assertFalse("InValid position test fail", isMoved);
	}

	// To test each case of play method

	@Test
	public void testPlayMethodWithInput() throws Exception {
		SnakeAndLadder instance = getInstance();
		Class[] types = new Class[] { Integer.class };
		Method method = getMethod("play", types);
		method.invoke(instance, 1); // Throw Die
		method.invoke(instance, 2); // Print Board
		method.invoke(instance, 3); // Pring User
		method.invoke(instance, 4); // Print Snakes
		method.invoke(instance, 5); // print Ladders
		method.invoke(instance, 6); // Quit
	}

	@Test
	public void testPlayMethod() throws Exception{
		SnakeAndLadder instance = getInstance();

		Field privateField = SnakeAndLadder.class.getDeclaredField("in");
		privateField.setAccessible(true);
		Scanner in = (Scanner) privateField.get(instance);
		//firstplayer choice \n second player choice \n third player choice
		//untill player not choose option 1 player will not change
		String input = "1\n2\n3\n4\n5\n1\n6";

		privateField.set(instance, new Scanner(input));
		
		instance.play();
	}
	
	/**
	 * Test custom game
	 * */
	@Test
	public void testPlayMethodWithPlayerInput() throws Exception{

		
		// First we need to pass y as custom game option
		// 1. need to provide input for Board
		// 2. need to provide input for snakes
		// 3. need to provide input for ladder
		// 4. need to provide input for 2-3 players
		// 5. Player play input
		
		SnakeAndLadder instance = new SnakeAndLadder();
		
		// This is for creating board
		StringBuffer input = new StringBuffer("y\n");
		
//		1. need to provide input for Board
		input.append("5\n" + //row
				"10\n" +//col
				"y\n" + //do you want to try again
				"10\n" +
				"10\n");

		// 2. need to provide input for snakes
		input.append("4\n" +// wrong total sanke
				"y\n" + //continue
				"7\n" + //valid total snakes
				"17\n" + //first snake
				"7\n" + 
				"54\n" + //second snake
				"34\n" +
				"62\n" + // third snake
				"19\n" +
				"64\n" + //fourth snake
				"60\n" +
				"93\n" + // fifth snake
				"73\n" +
				"95\n" + //sixth snake
				"75\n" +
				"100\n" + // invalid snake
				"1\n" +
				"y\n"+ // continue
				"99\n" + // seventh snake
				"78\n");

		// 3. need to provide input for ladder

		input.append("4\n" + // invalid no of ladders
				"y\n" + // continue
				"8\n" + // valid no.of ladders
				"4\n" + // first ladder
				"14\n" +
				"9\n" + // second ladder
				"31\n" +
				"20\n" + // Third ladder
				"38\n" +
				"28\n" + //Fourth Ladder
				"84\n" +
				"40\n" + // Fifth Ladder
				"59\n" +
				"51\n" + //sixth ladder
				"67\n" +
				"1\n" + // invalid ladder
				"100\n" +
				"y\n" +
				"73\n" + // Snake head, tail,ladder bottom and ladder start same i.e invalid ladder
				"93\n" +
				"y\n" +
				"63\n" + //seventh ladder
				"81\n" +
				"71\n" + //eighth ladder
				"91\n" +
				"");
		
//		 4. need to provide input for 2-3 players
		
		input.append("Alpesh\n" + //First player
				"Jack\n" + // Second player
				"n\n"// add more player
				);
		
//		5. Player play input
		
		input.append("1\n" + // throw die
				"7\n"+ // Wrong input
				"1\n" + // Throw Die
				"1\n"+ // Throw Die
				"1\n"+ // Throw Die
				"1\n"+ // Throw Die
				"1\n"+ // Throw Die
				"2\n" + // print board
				"3\n"+ // print player detail
				"4\n" + // print snakes
				"5\n"+ // print ladders
				"1\n"+ // Throw Die
				"2\n" + // print board
				"3\n"+ // print player detail
				"4\n" + // print snakes
				"5\n"+ // print ladders
				"6\n" + // quit
				"");
		Field privateField = SnakeAndLadder.class.getDeclaredField("in");
		privateField.setAccessible(true);
		Scanner in = (Scanner) privateField.get(instance);
		privateField.set(instance, new Scanner(input.toString()));
		
		// setup game
		instance.setUpGame();
		
		Assert.assertTrue("Game is not setup as per player input ", instance.isPlayable());

		
		
		// play game
		instance.play();
	}
	
	
	private boolean changePlayerPosition(SnakeAndLadder s, int move)
			throws Exception {
		Class[] types = new Class[] { Integer.class };
		Method method = getMethod("changePlayerPosition", types);
		Boolean isMoved = (Boolean) method.invoke(s, move);
		return isMoved;
	}

	private SnakeAndLadder getInstance() throws Exception {
		SnakeAndLadder instance = new SnakeAndLadder();
		setPlayerInput(instance, "n");
		instance.setUpGame();

		Method changePlayer = getMethod("changePlayer", null);
		changePlayer.setAccessible(true);
		changePlayer.invoke(instance, null);
		return instance;
	}

	private Method getMethod(String methodName, Class[] types) throws Exception {
		Method method = SnakeAndLadder.class.getDeclaredMethod(methodName,
				types);
		method.setAccessible(true);
		return method;
	}

	private void setPlayerInput(SnakeAndLadder instance, String input)
			throws Exception {
		ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
		Field privateField = SnakeAndLadder.class.getDeclaredField("in");
		privateField.setAccessible(true);
		Scanner str = (Scanner) privateField.get(instance);
		str = new Scanner(in);
		privateField.set(instance, str);
	}
	
	@Test
	public void testUserInput() throws Exception{
		SnakeAndLadder s = new SnakeAndLadder();
		/*		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
*/
		Field privateField = SnakeAndLadder.class.getDeclaredField("in");
		privateField.setAccessible(true);
		Scanner in = (Scanner) privateField.get(s);
		
		String input = "100\n2";
		
		privateField.set(s, new Scanner(input));
		int a = s.testUserInput();
		System.out.println(a);
	}
}
